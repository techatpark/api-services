DROP TABLE IF EXISTS devices;
CREATE TABLE devices (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(12) NOT NULL,
  namespace_id INT NOT NULL,
  gsm_code VARCHAR(25) NOT NULL,
  device_imei_code VARCHAR(25) NOT NULL,
  device_model_id TINYINT NOT NULL,
  sensor VARCHAR(20),
  api_flag TINYINT NOT NULL,
  remarks VARCHAR(120),
  active_flag TINYINT NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at TIME NOT NULL
);