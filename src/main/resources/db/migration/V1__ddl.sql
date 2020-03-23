DROP TABLE IF EXISTS devices;
CREATE TABLE devices (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(12) NOT NULL,
  namespace_id VARCHAR(25) NOT NULL,
  gsm_code VARCHAR(25) NOT NULL,
  device_imei_code VARCHAR(25) UNIQUE NOT NULL,
  sensor VARCHAR(20),
  api_flag TINYINT NOT NULL,
  remarks VARCHAR(120),
  active_flag TINYINT CHECK(
    active_flag = 1
    OR active_flag = 0
  ) NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);
DROP TABLE IF EXISTS namespace;
CREATE TABLE namespace (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(20) NOT NULL,
  name VARCHAR(40) NOT NULL,
  active_flag TINYINT NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);
DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) NOT NULL,
  namespace_id INTEGER NOT NULL,
  device_id INTEGER,
  register_number VARCHAR(10) NOT NULL,
  mobile_number VARCHAR(15) NOT NULL,
  overspeed_limit TINYINT DEFAULT 0 NOT NULL,
  vehicle_type_id TINYINT DEFAULT 1 NOT NULL,
  active_flag TINYINT NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME NOT NULL,
  FOREIGN KEY (device_id) REFERENCES devices(id)
);