-- Migration 004: Create vito_rides table
-- This stores all ride booking information

BEGIN;

CREATE TYPE ride_status AS ENUM (
  'pending',
  'accepted',
  'arriving',
  'in_progress',
  'completed',
  'cancelled'
);

CREATE TYPE service_type AS ENUM (
  'ride',
  'send',
  'mart'
);

CREATE TABLE public.vito_rides (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  client_id UUID NOT NULL REFERENCES public.vito_users(id),
  driver_id UUID REFERENCES public.vito_drivers(id),
  service_type service_type NOT NULL DEFAULT 'ride',
  
  -- Pickup location
  pickup_lat DOUBLE PRECISION NOT NULL,
  pickup_lng DOUBLE PRECISION NOT NULL,
  pickup_address TEXT NOT NULL,
  
  -- Destination
  destination_lat DOUBLE PRECISION NOT NULL,
  destination_lng DOUBLE PRECISION NOT NULL,
  destination_address TEXT NOT NULL,
  
  -- Fare
  fare_amount DOUBLE PRECISION NOT NULL DEFAULT 0.0,
  distance_km DOUBLE PRECISION NOT NULL DEFAULT 0.0,
  duration_minutes INT,
  
  -- Status
  status ride_status NOT NULL DEFAULT 'pending',
  
  -- Payment
  payment_method TEXT NOT NULL DEFAULT 'wallet',
  -- 'wallet' | 'stripe' | 'cash'
  payment_intent_id TEXT,
  is_paid BOOLEAN NOT NULL DEFAULT false,
  
  -- Timestamps
  requested_at TIMESTAMPTZ DEFAULT now(),
  accepted_at TIMESTAMPTZ,
  started_at TIMESTAMPTZ,
  completed_at TIMESTAMPTZ,
  cancelled_at TIMESTAMPTZ,
  cancel_reason TEXT,
  
  -- Ratings
  client_rating INT,
  client_rating_comment TEXT,
  driver_rating INT,
  
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

-- Enable Row Level Security
ALTER TABLE public.vito_rides ENABLE ROW LEVEL SECURITY;

-- Create policies
CREATE POLICY "Clients can read their own rides"
  ON public.vito_rides FOR SELECT
  USING (client_id = auth.uid());

CREATE POLICY "Drivers can read rides assigned to them"
  ON public.vito_rides FOR SELECT
  USING (driver_id = auth.uid() OR status = 'pending');

CREATE POLICY "Service role can do everything"
  ON public.vito_rides FOR ALL
  USING (auth.role() = 'service_role');

-- Add indexes
CREATE INDEX idx_vito_rides_client ON public.vito_rides(client_id);
CREATE INDEX idx_vito_rides_driver ON public.vito_rides(driver_id);
CREATE INDEX idx_vito_rides_status ON public.vito_rides(status);
CREATE INDEX idx_vito_rides_requested ON public.vito_rides(requested_at DESC);

COMMIT;