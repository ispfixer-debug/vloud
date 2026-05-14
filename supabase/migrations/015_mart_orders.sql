-- 015_mart_orders.sql
-- Quick Mart orders

CREATE TABLE IF NOT EXISTS vito_mart_orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id TEXT NOT NULL REFERENCES vito_users(id),
    driver_id TEXT REFERENCES vito_drivers(id),
    items JSONB NOT NULL,
    subtotal DOUBLE PRECISION NOT NULL,
    delivery_fee DOUBLE PRECISION DEFAULT 2.00,
    total DOUBLE PRECISION NOT NULL,
    delivery_address TEXT NOT NULL,
    delivery_lat DOUBLE PRECISION NOT NULL,
    delivery_lng DOUBLE PRECISION NOT NULL,
    status TEXT DEFAULT 'placed' CHECK (status IN ('placed', 'confirmed', 'preparing', 'ready', 'dispatched', 'delivered', 'cancelled')),
    delivery_photo_url TEXT,
    signature_data TEXT,
    payment_method TEXT DEFAULT 'wallet',
    is_paid BOOLEAN DEFAULT false,
    placed_at TIMESTAMPTZ DEFAULT NOW(),
    confirmed_at TIMESTAMPTZ,
    ready_at TIMESTAMPTZ,
    dispatched_at TIMESTAMPTZ,
    delivered_at TIMESTAMPTZ,
    cancelled_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_mart_orders_client ON vito_mart_orders(client_id, status);
CREATE INDEX IF NOT EXISTS idx_mart_orders_driver ON vito_mart_orders(driver_id, status);