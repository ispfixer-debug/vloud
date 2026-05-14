-- Migration 008: Create vito_app_settings table
-- Global app configuration settings

BEGIN;

CREATE TABLE public.vito_app_settings (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  key TEXT UNIQUE NOT NULL,
  value TEXT NOT NULL,
  description TEXT,
  is_public BOOLEAN NOT NULL DEFAULT true,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

-- Insert default settings
INSERT INTO public.vito_app_settings (key, value, description) VALUES
-- Base fare for rides
('base_fare', '3.50', 'Base fare in USD for any ride'),
-- Per-km rate
('rate_per_km', '1.50', 'Rate per kilometer in USD'),
-- Per-minute rate (when stuck in traffic)
('rate_per_minute', '0.25', 'Rate per minute when ride is slow/stopped'),
-- Minimum fare
('minimum_fare', '5.00', 'Minimum fare amount in USD'),
-- Maximum fare for Send service
('send_max_weight', '20.0', 'Maximum weight in kg for Send service'),
-- App version info
('min_app_version', '1.0.0', 'Minimum required app version'),
('latest_app_version', '1.0.0', 'Latest available app version'),
-- Promo code
('promo_enabled', 'false', 'Whether promo codes are enabled');

-- Enable Row Level Security
ALTER TABLE public.vito_app_settings ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Anyone can read public settings"
  ON public.vito_app_settings FOR SELECT
  USING (is_public = true);

CREATE POLICY "Service role can do everything"
  ON public.vito_app_settings FOR ALL
  USING (auth.role() = 'service_role');

CREATE INDEX idx_vito_app_settings_key ON public.vito_app_settings(key);

COMMIT;