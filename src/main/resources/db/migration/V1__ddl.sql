DROP TABLE IF EXISTS devices;
CREATE TABLE devices (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(12) NOT NULL,
  namespace_id VARCHAR(25) NOT NULL,
  gsm_code VARCHAR(25) NOT NULL,
  device_imei_code VARCHAR(25) NOT NULL,
  sensor VARCHAR(20),
  api_flag TINYINT NOT NULL,
  remarks VARCHAR(120),
  active_flag TINYINT NOT NULL
);