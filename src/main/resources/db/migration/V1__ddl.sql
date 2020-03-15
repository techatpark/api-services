DROP TABLE IF EXISTS devices;

CREATE TABLE devices (
  device_imei_code varchar(30) NOT NULL,
  device_code varchar(20) DEFAULT 'NA'
) ;