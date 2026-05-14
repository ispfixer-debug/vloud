-- 013_driver_earnings.sql
-- Driver earnings tracking

CREATE TABLE IF NOT EXISTS vito_payout_requests (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    driver_id TEXT NOT NULL REFERENCES vito_drivers(id),
    amount DOUBLE PRECISION NOT NULL,
    stripe_transfer_id TEXT,
    status TEXT DEFAULT 'pending' CHECK (status IN ('pending', 'processing', 'completed', 'failed')),
    requested_at TIMESTAMPTZ DEFAULT NOW(),
    processed_at TIMESTAMPTZ,
    failed_reason TEXT,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_payouts_driver ON vito_payout_requests(driver_id, status);