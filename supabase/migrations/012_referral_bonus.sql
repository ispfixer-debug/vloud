-- 012_referral_bonus.sql
-- Referral system function

CREATE OR REPLACE FUNCTION apply_referral_bonus(referrer_id TEXT, new_user_id TEXT)
RETURNS VOID AS $$
DECLARE
    bonus_amount DOUBLE PRECISION := 5.00;
BEGIN
    -- Update referrer's wallet
    UPDATE vito_users 
    SET wallet_balance = wallet_balance + bonus_amount
    WHERE id = referrer_id;
    
    -- Create transaction
    INSERT INTO vito_transactions (user_id, type, amount, balance_before, reference_id, description)
    SELECT 
        referrer_id,
        'referral_bonus',
        bonus_amount,
        wallet_balance - bonus_amount,
        new_user_id,
        'Referral bonus'
    FROM vito_users WHERE id = referrer_id;
END;
$$ LANGUAGE plpgsql;