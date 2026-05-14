// vito-auth-login/index.ts
// PIN-based login Edge Function
import { serve } from "https://deno.land/std@0.168.0/http/server.ts"
import { createClient } from "https://esm.sh/@supabase/supabase-js@2"

const corsHeaders = {
  "Access-Control-Allow-Origin": "*",
  "Access-Control-Allow-Headers": "authorization, x-client-info, apikey, content-type",
}

const supabaseUrl = Deno.env.get("SUPABASE_URL")!
const supabaseServiceKey = Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")!
const supabase = createClient(supabaseUrl, supabaseServiceKey)

serve(async (req) => {
  if (req.method === "OPTIONS") {
    return new Response("ok", { headers: corsHeaders })
  }

  try {
    const { action, username, pin_hash, referral_driver_id, language, display_name } = await req.json()

    if (action === "signup") {
      // Check if username exists
      const existing = await supabase
        .from("vito_users")
        .select("id")
        .eq("username", username)
        .single()

      if (existing.data) {
        return new Response(JSON.stringify({ error: "Username taken" }), {
          status: 400,
          headers: { ...corsHeaders, "Content-Type": "application/json" },
        })
      }

      // Create user
      const { data, error } = await supabase
        .from("vito_users")
        .insert({
          username,
          pin_hash,
          display_name: display_name || username,
          referral_driver_id,
          language: language || "en",
        })
        .select()
        .single()

      if (error) throw error

      return new Response(JSON.stringify({ user: data, token: data.id }), {
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    if (action === "signin") {
      const { data, error } = await supabase
        .from("vito_users")
        .select("*")
        .eq("username", username)
        .eq("pin_hash", pin_hash)
        .single()

      if (error || !data) {
        return new Response(JSON.stringify({ error: "Invalid credentials" }), {
          status: 401,
          headers: { ...corsHeaders, "Content-Type": "application/json" },
        })
      }

      return new Response(JSON.stringify({ user: data, token: data.id }), {
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    return new Response(JSON.stringify({ error: "Invalid action" }), {
      status: 400,
      headers: { ...corsHeaders, "Content-Type": "application/json" },
    })
  } catch (error) {
    return new Response(JSON.stringify({ error: error.message }), {
      status: 500,
      headers: { ...corsHeaders, "Content-Type": "application/json" },
    })
  }
})