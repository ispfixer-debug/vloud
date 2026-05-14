-- Migration 009: Create driver_location_history table
-- Historical driver location data for analytics and audit

BEGIN;

CREATE TABLE public.driver_location_history (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  driver_id UUID NOT NULL REFERENCES public.vito_drivers(id),
  lat DOUBLE PRECISION NOT NULL,
  lng DOUBLE PRECISION NOT NULL,
  accuracy DOUBLE PRECISION,
  speed DOUBLE PRECISION,
  heading DOUBLE PRECISION,
  timestamp TIMESTAMPTZ DEFAULT now()
);

-- Enable Row Level Security
ALTER TABLE public.driver_location_history ENABLE ROW LEVEL SECURITY;

-- Create policies
CREATE POLICY "Anyone can read location history"
  ON public.driver_location_history FOR SELECT
  USING (true);

CREATE POLICY "Service role can insert location history"
  ON public.driver_location_history FOR INSERT
  WITH CHECK (auth.role() = 'service_role');

-- Create indexes
CREATE INDEX idx_driver_location_driver ON public.driver_location_history(driver_id);
CREATE INDEX idx_driver_location_timestamp ON public.driver_location_history(timestamp DESC);

-- Create index for last 24 hours query
CREATE INDEX idx_driver_location_recent 
  ON public.driver_location_history(timestamp DESC) 
  WHERE timestamp > now() - INTERval '24 hours';

-- Set up automatic cleanup (keep only 7 days of history)
-- This would need a cron job or trigger to clean up old data

COMMIT;

-- Migration 010: Create notification system tables
BEGIN;

CREATE TYPE notification_channel AS ENUM (
  'ride_request',
  'ride_update',
  'payment',
  'promo',
  'system'
);

CREATE TABLE public.notifications (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL REFERENCES public.vito_users(id),
  driver_id UUID REFERENCES public.vito_drivers(id),
  channel notification_channel NOT NULL DEFAULT 'system',
  title TEXT NOT NULL,
  body TEXT NOT NULL,
  data JSONB,
  is_read BOOLEAN NOT NULL DEFAULT false,
  sent_at TIMESTAMPTZ DEFAULT now(),
  read_at TIMESTAMPTZ
);

-- Enable Row Level Security
ALTER TABLE public.notifications ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Users can read their notifications"
  ON public.notifications FOR SELECT
  USING (user_id = auth.uid());

CREATE POLICY "Drivers can read their notifications"
  ON public.notifications FOR SELECT
  USING (driver_id = auth.uid());

CREATE POLICY "Service role can do everything"
  ON public.notifications FOR ALL
  USING (auth.role() = 'service_role');

CREATE INDEX idx_notifications_user ON public.notifications(user_id, sent_at DESC);
CREATE INDEX idx_notifications_driver ON public.notifications(driver_id, sent_at DESC);
CREATE INDEX idx_notifications_unread ON public.notifications(user_id, is_read) WHERE (is_read = false);

-- Real-time support: publications for Realtime
DROP PUBLICATION IF EXISTS supabase_realtime;
CREATE PUBLICATION supabase_realtime FOR TABLE public.notifications;
ALTER PUBLICATION supabase_realtime ADD TABLE public.vito_rides;
ALTER PUBLICATION supabase_realtime ADD TABLE public.vito_drivers;

COMMIT;