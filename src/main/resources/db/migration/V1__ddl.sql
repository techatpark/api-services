DROP TABLE IF EXISTS devices;
CREATE TABLE devices (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(25),
  imeicode VARCHAR(25),
  label VARCHAR(255),
  description VARCHAR(255)
);