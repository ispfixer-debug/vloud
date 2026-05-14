// vito-payout/index.ts
// Payout processing
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

serve(async (req) => {
  if (req.method === "OPTIONS") {
    return new Response("ok", { headers: corsHeaders })
  }

  try {
    const { payout_id } = await req.json()

    // Get payout request
    const { data: payout, error } = await supabase
      .from("vito_payout_requests")
      .select("*")
      .eq("id", payout_id)
      .single()

    if (error || !payout) {
      return new Response(JSON.stringify({ error: "Payout not found" }), {
        status: 404,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    if (payout.status !== "pending") {
      return new Response(JSON.stringify({ error: "Payout not pending" }), {
        status: 400,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    // Get driver stripe account
    const { data: driver } = await supabase
      .from("vito_drivers")
      .select("stripe_account_id")
      .eq("id", payout.driver_id)
      .single()

    if (!driver?.stripe_account_id) {
      return new Response(JSON.stringify({ error: "No stripe account" }), {
        status: 400,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    // Create Stripe transfer
    const transfer = await stripe.transfers.create({
      amount: Math.round(payout.amount * 100),
      currency: "usd",
      destination: driver.stripe_account_id,
      metadata: {
        payout_id,
      },
    })

    // Update payout status
    await supabase
      .from("vito_payout_requests")
      .update({
        status: "completed",
        stripe_transfer_id: transfer.id,
        processed_at: new Date().toISOString(),
      })
      .eq("id", payout_id)

    return new Response(JSON.stringify({ success: true, transfer_id: transfer.id }), {
      headers: { ...corsHeaders, "Content-Type": "application/json" },
    })
  } catch (error) {
    return new Response(JSON.stringify({ error: error.message }), {
      status: 500,
      headers: { ...corsHeaders, "Content-Type": "application/json" },
    })
  }
})