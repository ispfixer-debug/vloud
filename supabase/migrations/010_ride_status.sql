-- 010_alter_ride_status.sql
-- Add additional ride status tracking

ALTER TABLE vito_rides ADD COLUMN IF NOT EXISTS client_location_lat DOUBLE PRECISION;
ALTER TABLE vito_rides ADD COLUMN IF NOT EXISTS client_location_lng DOUBLE PRECISION;

CREATE INDEX IF NOT EXISTS idx_rides_status_client ON vito_rides(status, client_id);
CREATE INDEX IF NOT EXISTS idx_rides_status_driver ON vito_rides(status, driver_id);