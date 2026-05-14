-- Migration 001: Drop old GetRide schema
-- Drop old tables if they exist (back up data first if needed)
-- This clears the deck for the new VITO schema

BEGIN;

-- Drop all existing tables
DROP TABLE IF EXISTS public.rides CASCADE;
DROP TABLE IF EXISTS public.users CASCADE;
DROP TABLE IF EXISTS public.drivers CASCADE;
DROP TABLE IF EXISTS public.preferences CASCADE;

-- Drop any functions that might exist from old schema
DROP FUNCTION IF EXISTS public.handle_new_user();
DROP FUNCTION IF EXISTS public.handle_new_driver();
DROP FUNCTION IF EXISTS public.calculate_fare();

COMMIT;