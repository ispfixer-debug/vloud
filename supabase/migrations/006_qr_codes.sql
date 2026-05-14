-- Migration 006: Create vito_qr_codes table
-- QR codes for driver registration referrals and APK downloads

BEGIN;

CREATE TABLE public.vito_qr_codes (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  code TEXT UNIQUE NOT NULL,
  qr_type TEXT NOT NULL,
  -- 'referral' | 'download' | 'promo'
  driver_id UUID REFERENCES public.vito_drivers(id),
  expires_at TIMESTAMPTZ NOT NULL,
  is_used BOOLEAN NOT NULL DEFAULT false,
  used_at TIMESTAMPTZ,
  created_at TIMESTAMPTZ DEFAULT now()
);

-- Enable Row Level Security
ALTER TABLE public.vito_qr_codes ENABLE ROW LEVEL SECURITY;

-- Create policies
CREATE POLICY "Anyone can read valid QR codes"
  ON public.vito_qr_codes FOR SELECT
  USING (expires_at > now());

CREATE POLICY "Service role can do everything"
  ON public.vito_qr_codes FOR ALL
  USING (auth.role() = 'service_role');

-- Create indexes
CREATE INDEX idx_vito_qr_codes_code ON public.vito_qr_codes(code);
CREATE INDEX idx_vito_qr_codes_driver ON public.vito_qr_codes(driver_id);
CREATE INDEX idx_vito_qr_codes_expires ON public.vito_qr_codes(expires_at) WHERE (is_used = false);

COMMIT;