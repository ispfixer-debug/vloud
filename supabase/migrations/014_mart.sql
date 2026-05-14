-- 014_mart_products.sql
-- Quick Mart products

CREATE TABLE IF NOT EXISTS vito_mart_products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    description TEXT,
    image_url TEXT,
    price_usd DOUBLE PRECISION NOT NULL,
    stock_count INTEGER DEFAULT 0,
    category TEXT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- Seed data
INSERT INTO vito_mart_products (name, description, price_usd, stock_count, category) VALUES
('Water 500ml', 'Bottled water', 1.00, 100, 'beverages'),
('Soda 350ml', 'Soft drink', 1.50, 50, 'beverages'),
('Chips', 'Potato chips', 2.00, 30, 'snacks'),
('Chocolate', 'Chocolate bar', 2.50, 25, 'snacks'),
('Energy Drink', 'Energy drink', 3.00, 20, 'beverages');