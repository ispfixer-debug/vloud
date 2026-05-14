-- 011_transactions_trigger.sql  
-- Auto-create wallet transactions on balance changes

CREATE OR REPLACE FUNCTION create_transaction()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.wallet_balance <> OLD.wallet_balance THEN
        INSERT INTO vito_transactions (user_id, type, amount, balance_before, balance_after)
        VALUES (
            NEW.id,
            CASE 
                WHEN NEW.wallet_balance > OLD.wallet_balance THEN 'topup'
                ELSE 'ride_payment'
            END,
            ABS(NEW.wallet_balance - OLD.wallet_balance),
            OLD.wallet_balance,
            NEW.wallet_balance
        );
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER wallet_balance_change
AFTER UPDATE ON vito_users
FOR EACH ROW EXECUTE FUNCTION create_transaction();