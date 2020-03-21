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