-- Migration 003: Create vito_drivers table
-- This table stores all driver information

BEGIN;

CREATE TABLE public.vito_drivers (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  username TEXT UNIQUE NOT NULL,
  pin_hash TEXT NOT NULL,
  plate_number TEXT NOT NULL,
  car_photo_url TEXT,
  car_photo_approved BOOLEAN NOT NULL DEFAULT false,
  is_online BOOLEAN NOT NULL DEFAULT false,
  service_ride BOOLEAN NOT NULL DEFAULT true,
  service_send BOOLEAN NOT NULL DEFAULT false,
  service_mart BOOLEAN NOT NULL DEFAULT false,
  current_lat DOUBLE PRECISION,
  current_lng DOUBLE PRECISION,
  rating DOUBLE PRECISION NOT NULL DEFAULT 5.0,
  total_ratings INT NOT NULL DEFAULT 0,
  earnings_total DOUBLE PRECISION NOT NULL DEFAULT 0.0,
  is_suspended BOOLEAN NOT NULL DEFAULT false,
  stripe_account_id TEXT,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

-- Enable Row Level Security
ALTER TABLE public.vito_drivers ENABLE ROW LEVEL SECURITY;

-- Create policies for vito_drivers
CREATE POLICY "Drivers can read all drivers"
  ON public.vito_drivers FOR SELECT
  USING (true);

CREATE POLICY "Service role can insert drivers"
  ON public.vito_drivers FOR INSERT
  WITH CHECK (auth.role() = 'service_role');

CREATE POLICY "Service role can update drivers"
  ON public.vito_drivers FOR UPDATE
  USING (auth.role() = 'service_role');

CREATE POLICY "Drivers can update their own data"
  ON public.vito_drivers FOR UPDATE
  USING (auth.uid() = id);

-- Add indexes
CREATE INDEX idx_vito_drivers_username ON public.vito_drivers(username);
CREATE INDEX idx_vito_drivers_online ON public.vito_drivers(is_online);
CREATE INDEX idx_vito_drivers_plate ON public.vito_drivers(plate_number);

COMMIT;