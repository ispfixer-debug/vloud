// vito-stripe-webhook/index.ts
// Stripe webhook handler
import { serve } from "https://deno.land/std@0.168.0/http/server.ts"
import { createClient } from "https://esm.sh/@supabase/supabase-js@2"
import Stripe from "https://esm.sh/stripe@11?target=deno"

const corsHeaders = {
  "Access-Control-Allow-Origin": "*",
  "Access-Control-Allow-Headers": "authorization, x-client-info, apikey, content-type",
}

const supabaseUrl = Deno.env.get("SUPABASE_URL")!
const supabaseServiceKey = Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")!
const supabase = createClient(supabaseUrl, supabaseServiceKey)

const stripe = new Stripe(Deno.env.get("STRIPE_SECRET_KEY")!, {
  apiVersion: "2023-10-16",
})

const WEBHOOK_SECRET = Deno.env.get("STRIPE_WEBHOOK_SECRET")!

serve(async (req) => {
  if (req.method === "OPTIONS") {
    return new Response("ok", { headers: corsHeaders })
  }

  try {
    const body = await req.text()
    const signature = req.headers.get("stripe-signature")!

    let event
    try {
      event = stripe.webhooks.constructEvent(body, signature, WEBHOOK_SECRET)
    } catch {
      return new Response("Invalid signature", { status: 400 })
    }

    if (event.type === "payment_intent.succeeded") {
      const paymentIntent = event.data.object
      const { user_id, type } = paymentIntent.metadata

      if (type === "topup") {
        // Update user wallet balance
        const amount = paymentIntent.amount / 100

        const { data: user } = await supabase
          .from("vito_users")
          .select("wallet_balance")
          .eq("id", user_id)
          .single()

        if (user) {
          const newBalance = user.wallet_balance + amount

          await supabase
            .from("vito_users")
            .update({ wallet_balance: newBalance })
            .eq("id", user_id)

          // Create transaction
          await supabase
            .from("vito_transactions")
            .insert({
              user_id,
              type: "topup",
              amount,
              balance_before: user.wallet_balance,
              balance_after: newBalance,
              stripe_payment_id: paymentIntent.id,
            })
        }
      }
    }

    if (event.type === "transfer.created") {
      // Handle payout transfer
      const transfer = event.data.object
      const payoutId = transfer.metadata.payout_id

      if (payoutId) {
        await supabase
          .from("vito_payout_requests")
          .update({
            status: "completed",
            stripe_transfer_id: transfer.id,
            processed_at: new Date().toISOString(),
          })
          .eq("id", payoutId)
      }
    }

    return new Response(JSON.stringify({ received: true }), {
      headers: { ...corsHeaders, "Content-Type": "application/json" },
    })
  } catch (error) {
    return new Response(JSON.stringify({ error: error.message }), {
      status: 500,
      headers: { ...corsHeaders, "Content-Type": "application/json" },
    })
  }
})