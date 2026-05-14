-- Migration 005: Create vito_admins table
-- Admin users for the admin dashboard

BEGIN;

CREATE TABLE public.vito_admins (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  username TEXT UNIQUE NOT NULL,
  pin_hash TEXT NOT NULL,
  display_name TEXT NOT NULL,
  role TEXT NOT NULL DEFAULT 'admin',
  -- 'super_admin' | 'admin' | 'support'
  is_active BOOLEAN NOT NULL DEFAULT true,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

-- Enable Row Level Security
ALTER TABLE public.vito_admins ENABLE ROW LEVEL SECURITY;

-- Create policies
CREATE POLICY "Service role can do everything"
  ON public.vito_admins FOR ALL
  USING (auth.role() = 'service_role');

CREATE INDEX idx_vito_admins_username ON public.vito_admins(username);

COMMIT;