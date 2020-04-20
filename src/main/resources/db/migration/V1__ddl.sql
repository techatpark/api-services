DROP TABLE IF EXISTS device;
CREATE TABLE device (
  id SERIAL PRIMARY KEY,
  device_imei_code VARCHAR(20) UNIQUE NOT NULL,
  device_model_id VARCHAR(20) NOT NULL,
  active_flag SMALLINT DEFAULT 1 NOT NULL,
  remarks VARCHAR(50),
  updated_by INTEGER NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
  id SERIAL PRIMARY KEY,
  device_id INTEGER NOT NULL,
  vehicle_name VARCHAR(30),
  registration_number VARCHAR(15) UNIQUE NOT NULL,
  overspeed_limit INTEGER,
  active_flag SMALLINT DEFAULT 1 NOT NULL,
  remarks VARCHAR(50),
  updated_by INTEGER NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (device_id) REFERENCES device(id)
) DROP TABLE IF EXISTS vehicle_location;
CREATE TABLE vehicle_location (
  id SERIAL PRIMARY KEY,
  device_id INTEGER NOT NULL,
  vehicle_id INTEGER NOT NULL,
  latitude VARCHAR(30) NOT NULL,
  longitude VARCHAR(30) NOT NULL,
  road VARCHAR(50) NOT NULL,
  area VARCHAR(30) NOT NULL,
  landmark VARCHAR(30) NOT NULL,
  city VARCHAR(20) NOT NULL,
  state VARCHAR(20) NOT NULL,
  trip_driver VARCHAR(30) NOT NULL,
  vehicle_speed INTEGER NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (device_id) REFERENCES device(id),
  FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
)