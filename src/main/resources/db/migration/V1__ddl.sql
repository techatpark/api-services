DROP TABLE IF EXISTS devices;
CREATE TABLE devices (
  id INTEGER IDENTITY PRIMARY KEY,
  code varchar(12) NOT NULL,
  namespace_id int(11) NOT NULL,
  gsm_code varchar(25) NOT NULL,
  device_imei_code varchar(25) NOT NULL,
  device_model_id tinyint(4) NOT NULL,
  sensor varchar(20) DEFAULT 'NULL',
  api_flag tinyint(4) NOT NULL DEFAULT '1',
  remarks varchar(120) DEFAULT NULL,
  active_flag tinyint(4) NOT NULL,
  updated_by int(11) NOT NULL,
  updated_at datetime NOT NULL
);