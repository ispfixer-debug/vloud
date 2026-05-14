// vito-qr-info/index.ts
// Public QR token info for landing page
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
    const url = new URL(req.url)
    const token = url.searchParams.get("token")

    if (!token) {
      return new Response(JSON.stringify({ error: "No token" }), {
        status: 400,
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    // Check token
    const { data, error } = await supabase
      .from("vito_qr_tokens")
      .select("*")
      .eq("token", token)
      .single()

    if (error || !data) {
      return new Response(JSON.stringify({ valid: false, error: "Token not found" }), {
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    // Check expiration
    const expiresAt = new Date(data.expires_at)
    const now = new Date()
    
    if (data.is_revoked || data.uses_count >= data.uses_total || expiresAt < now) {
      return new Response(JSON.stringify({ valid: false, error: "Token expired" }), {
        headers: { ...corsHeaders, "Content-Type": "application/json" },
      })
    }

    // Get driver name if client_referral
    let driverName = null
    if (data.type === "client_referral" && data.driver_id) {
      const driver = await supabase
        .from("vito_drivers")
        .select("display_name")
        .eq("id", data.driver_id)
        .single()
      
      driverName = driver.data?.display_name
    }

    return new Response(JSON.stringify({ 
      valid: true, 
      type: data.type,
      driver_name: driverName,
      expires_at: data.expires_at 
    }), {
      headers: { ...corsHeaders, "Content-Type": "application/json" },
    })
  } catch (error) {
    return new Response(JSON.stringify({ valid: false, error: error.message }), {
      status: 500,
      headers: { ...corsHeaders, "Content-Type": "application/json" },
    })
  }
})