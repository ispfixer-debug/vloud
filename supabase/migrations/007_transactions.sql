-- Migration 007: Create vito_transactions table
-- Wallet transactions for users (top-ups, payments, payouts)

BEGIN;

CREATE TYPE transaction_type AS ENUM (
  'top_up',
  'payment',
  'payout',
  'refund',
  'bonus'
);

CREATE TYPE transaction_status AS ENUM (
  'pending',
  'completed',
  'failed',
  'cancelled'
);

CREATE TABLE public.vito_transactions (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL REFERENCES public.vito_users(id),
  -- For driver payouts, also reference driver
  driver_id UUID REFERENCES public.vito_drivers(id),
  ride_id UUID REFERENCES public.vito_rides(id),
  
  type transaction_type NOT NULL,
  amount DOUBLE PRECISION NOT NULL,
  -- In USD (dollars, not cents)
  currency TEXT NOT NULL DEFAULT 'USD',
  status transaction_status NOT NULL DEFAULT 'pending',
  
  stripe_payment_intent_id TEXT,
  stripe_transfer_id TEXT,
  
  description TEXT,
  
  created_at TIMESTAMPTZ DEFAULT now(),
  completed_at TIMESTAMPTZ,
  
  CONSTRAINT positive_amount CHECK (amount > 0)
);

-- Enable Row Level Security
ALTER TABLE public.vito_transactions ENABLE ROW LEVEL SECURITY;

-- Create policies
CREATE POLICY "Users can read their own transactions"
  ON public.vito_transactions FOR SELECT
  USING (user_id = auth.uid());

CREATE POLICY "Drivers can read their own transactions"
  ON public.vito_transactions FOR SELECT
  USING (driver_id = auth.uid());

CREATE POLICY "Service role can do everything"
  ON public.vito_transactions FOR ALL
  USING (auth.role() = 'service_role');

-- Create indexes
CREATE INDEX idx_vito_transactions_user ON public.vito_transactions(user_id);
CREATE INDEX idx_vito_transactions_driver ON public.vito_transactions(driver_id);
CREATE INDEX idx_vito_transactions_ride ON public.vito_transactions(ride_id);
CREATE INDEX idx_vito_transactions_status ON public.vito_transactions(status);
CREATE INDEX idx_vito_transactions_created ON public.vito_transactions(created_at DESC);

COMMIT;