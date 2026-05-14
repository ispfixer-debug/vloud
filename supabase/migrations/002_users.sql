-- Migration 002: Create vito_users table
-- This table stores all client and admin user information
-- Note: Run 003_drivers.sql before this migration

BEGIN;

CREATE TABLE public.vito_users (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  username TEXT UNIQUE NOT NULL,
  display_name TEXT NOT NULL,
  pin_hash TEXT NOT NULL,
  -- bcrypt hash of 6-digit PIN
  referral_driver_id UUID,
  -- FK to drivers - added after drivers table creation
  wallet_balance DOUBLE PRECISION NOT NULL DEFAULT 0.0,
  stripe_customer_id TEXT,
  is_suspended BOOLEAN NOT NULL DEFAULT false,
  language TEXT NOT NULL DEFAULT 'en',
  -- 'en' | 'es'
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now(),
  CONSTRAINT fk_referral_driver FOREIGN KEY (referral_driver_id)
    REFERENCES public.vito_drivers(id) ON DELETE SET NULL
);

-- Enable Row Level Security
ALTER TABLE public.vito_users ENABLE ROW LEVEL SECURITY;

-- Create policies for vito_users
CREATE POLICY "Users can read their own data"
  ON public.vito_users FOR SELECT
  USING (auth.uid() = id OR auth.role() = 'service_role');

CREATE POLICY "Service role can insert users"
  ON public.vito_users FOR INSERT
  WITH CHECK (auth.role() = 'service_role');

CREATE POLICY "Service role can update users"
  ON public.vito_users FOR UPDATE
  USING (auth.role() = 'service_role');

-- Add indexes for common queries
CREATE INDEX idx_vito_users_username ON public.vito_users(username);
CREATE INDEX idx_vito_users_referral_driver ON public.vito_users(referral_driver_id);

COMMIT;