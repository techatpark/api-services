DROP TABLE IF EXISTS namespace;
CREATE TABLE namespace (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(20) UNIQUE NOT NULL,
  name VARCHAR(40) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);
DROP TABLE IF EXISTS user_group;
CREATE TABLE user_group (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  name VARCHAR(45) NOT NULL,
  namespace_id INTEGER NOT NULL,
  level TINYINT DEFAULT 0 NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT name_UNIQUE UNIQUE(name, namespace_id),
  FOREIGN KEY (namespace_id) REFERENCES namespace(id)
);
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  username VARCHAR(45) NOT NULL,
  namespace_id INTEGER NOT NULL,
  user_group_id INTEGER DEFAULT NULL,
  authentication_zone_id TINYINT DEFAULT 1 NOT NULL,
  token VARCHAR(45) DEFAULT NULL,
  api_token VARCHAR(45) DEFAULT NULL,
  email VARCHAR(45) NOT NULL,
  mobile VARCHAR(15) DEFAULT NULL,
  name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT username_namespace_UNIQUE UNIQUE(username, namespace_id),
  FOREIGN KEY (namespace_id) REFERENCES namespace(id),
  FOREIGN KEY (user_group_id) REFERENCES user_group(id)
);
DROP TABLE IF EXISTS alert_notification;
CREATE TABLE alert_notification (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  namespace_id INTEGER NOT NULL,
  name VARCHAR(40) NOT NULL,
  mobile_numbers VARCHAR(250) NOT NULL,
  reference_codes VARCHAR(250) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id)
);
DROP TABLE IF EXISTS audit_log;
CREATE TABLE audit_log (
  id INTEGER IDENTITY PRIMARY KEY,
  namespace_id INTEGER NOT NULL,
  code VARCHAR(15) UNIQUE NOT NULL,
  table_name VARCHAR(60) NOT NULL,
  event VARCHAR(60) NOT NULL,
  log1 VARCHAR(250) DEFAULT NULL,
  log2 VARCHAR(250) DEFAULT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);
DROP TABLE IF EXISTS device;
CREATE TABLE device (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  namespace_id INTEGER NOT NULL,
  gsm_code VARCHAR(25) NOT NULL,
  device_imei_code VARCHAR(25) NOT NULL,
  device_model_id TINYINT NOT NULL,
  sensor VARCHAR(20) DEFAULT 'NULL',
  api_flag TINYINT DEFAULT 1 NOT NULL,
  remarks VARCHAR(120) DEFAULT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id),
  CONSTRAINT uq_device UNIQUE(namespace_id, device_imei_code),
  CONSTRAINT uq_device2 UNIQUE(namespace_id, gsm_code)
);
DROP TABLE IF EXISTS event_alert;
CREATE TABLE event_alert (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  namespace_id INTEGER NOT NULL,
  name VARCHAR(40) NOT NULL,
  vehicle_codes VARCHAR(250) NOT NULL,
  event_type_id TINYINT DEFAULT 0 NOT NULL,
  alert_notification_codes VARCHAR(250) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  day_of_week CHAR(7) DEFAULT '1111111' NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id)
);
DROP TABLE IF EXISTS geo_location_address;
CREATE TABLE geo_location_address (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(20) UNIQUE NOT NULL,
  latitude VARCHAR(20) NOT NULL,
  langitude VARCHAR(20) NOT NULL,
  address_flag TINYINT DEFAULT 1 NOT NULL,
  address VARCHAR(250) DEFAULT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT unx_geo_location_address_1 UNIQUE(latitude, langitude)
);
DROP TABLE IF EXISTS geo_location_address_details;
CREATE TABLE geo_location_address_details (
  id INTEGER IDENTITY PRIMARY KEY,
  location_address_code VARCHAR(20) UNIQUE NOT NULL,
  road VARCHAR(80) NOT NULL,
  area VARCHAR(30) NOT NULL,
  landmark VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  state VARCHAR(30) NOT NULL,
  postal_code VARCHAR(6) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);
DROP TABLE IF EXISTS login_history;
CREATE TABLE login_history (
  id INTEGER IDENTITY PRIMARY KEY,
  namespace_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  login_privider VARCHAR(2) CHECK(
    login_privider = 'EB'
    OR login_privider = 'FB'
    OR login_privider = 'GM'
  ) NOT NULL,
  device_medium VARCHAR(3) CHECK(
    device_medium = 'WEB'
    OR device_medium = 'API'
    OR device_medium = 'MOB'
    OR device_medium = 'APP'
  ) NOT NULL,
  auth_token VARCHAR(45),
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);
DROP TABLE IF EXISTS menu;
CREATE TABLE menu (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  name VARCHAR(40) NOT NULL,
  link VARCHAR(80) NOT NULL,
  action_code VARCHAR(25) DEFAULT NULL,
  lookup_id INTEGER DEFAULT NULL,
  default_flag TINYINT NOT NULL,
  display_flag TINYINT DEFAULT 0 NOT NULL,
  product_type_id TINYINT DEFAULT 1 NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);
DROP TABLE IF EXISTS menu_events;
CREATE TABLE menu_events (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  name VARCHAR(25) NOT NULL,
  operation_code VARCHAR(25) DEFAULT NULL,
  attr1_value VARCHAR(25) DEFAULT NULL,
  action_type TINYINT NOT NULL,
  menu_id INTEGER NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menu(id)
);
DROP TABLE IF EXISTS namespace_profile;
CREATE TABLE namespace_profile (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  namespace_id INTEGER NOT NULL,
  over_speed VARCHAR(25) NOT NULL,
  sender_mail_name VARCHAR(80) DEFAULT 'NA' NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id)
);
DROP TABLE IF EXISTS notification_log;
CREATE TABLE notification_log (
  id INTEGER IDENTITY PRIMARY KEY,
  namespace_id INTEGER NOT NULL,
  notification_mode VARCHAR(4) CHECK(
    notification_mode = 'MAIL'
    OR notification_mode = 'SMS'
  ) NOT NULL,
  notification_type_id TINYINT NOT NULL,
  participant_address VARCHAR(120) NOT NULL,
  refference_code VARCHAR(40) NOT NULL,
  transaction_count TINYINT NOT NULL,
  request_log VARCHAR(250) NOT NULL,
  response_log VARCHAR(250) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id)
);
DROP TABLE IF EXISTS report_query;
CREATE TABLE report_query (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(120) NOT NULL,
  query VARCHAR(120) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);
DROP TABLE IF EXISTS trip_details;
CREATE TABLE trip_details (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(30) NOT NULL,
  namespace_id INTEGER NOT NULL,
  device_code VARCHAR(15) DEFAULT NULL,
  bus_type VARCHAR(40) NOT NULL,
  schedule_code VARCHAR(15) UNIQUE NOT NULL,
  schedule_name VARCHAR(80) DEFAULT NULL,
  service_number VARCHAR(15) DEFAULT NULL,
  driver_details VARCHAR(200) NOT NULL,
  from_station_name VARCHAR(45) NOT NULL,
  to_station_name VARCHAR(45) NOT NULL,
  origin_time DATETIME NOT NULL,
  destination_time DATETIME NOT NULL,
  trip_date_time DATETIME NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT unx_trip_details_1 UNIQUE(code, namespace_id)
);
DROP TABLE IF EXISTS trip_station_point;
CREATE TABLE trip_station_point (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(20) DEFAULT NULL,
  namespace_id INTEGER NOT NULL,
  name VARCHAR(60) NOT NULL,
  trip_details_id INTEGER NOT NULL,
  station_code VARCHAR(20) DEFAULT NULL,
  station_name VARCHAR(45) NOT NULL,
  latitude VARCHAR(20) NOT NULL,
  longitude VARCHAR(20) NOT NULL,
  expected_time INTEGER NOT NULL,
  actual_time INTEGER NOT NULL,
  actual_distance double DEFAULT 0 NOT NULL,
  sequence INTEGER DEFAULT 0 NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (trip_details_id) REFERENCES trip_details(id)
);
DROP TABLE IF EXISTS user_app_store;
CREATE TABLE user_app_store (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  namespace_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  device_medium_id TINYINT NOT NULL,
  model VARCHAR(60) NOT NULL,
  os VARCHAR(60) NOT NULL,
  udid VARCHAR(100) NOT NULL,
  gcm_token VARCHAR(250) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);
DROP TABLE IF EXISTS user_menu;
CREATE TABLE user_menu (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  namespace_id INTEGER NOT NULL,
  menu_id INTEGER NOT NULL,
  refference_id INTEGER NOT NULL,
  refference_type VARCHAR(2) CHECK(
    refference_type = 'NS'
    OR refference_type = 'GR'
    OR refference_type = 'UR'
  ) NOT NULL,
  active_event_codes VARCHAR(250) DEFAULT NULL,
  active_event_codes_plus VARCHAR(250) DEFAULT NULL,
  menu_exception_flag TINYINT NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id),
  FOREIGN KEY (menu_id) REFERENCES menu(id)
);
DROP TABLE IF EXISTS user_vehicle_group_map;
CREATE TABLE user_vehicle_group_map (
  id INTEGER IDENTITY PRIMARY KEY,
  namespace_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  vehicle_group_codes VARCHAR(250) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);
DROP TABLE IF EXISTS vehicle;
CREATE TABLE vehicle (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  namespace_id INTEGER NOT NULL,
  name VARCHAR(40) NOT NULL,
  device_id INTEGER DEFAULT 0 NOT NULL,
  register_number VARCHAR(10) NOT NULL,
  mobile_number VARCHAR(15) NOT NULL,
  overspeed_limit TINYINT DEFAULT 0 NOT NULL,
  vehicle_type_id TINYINT DEFAULT 1 NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id)
);
DROP TABLE IF EXISTS vehicle_group;
CREATE TABLE vehicle_group (
  id INTEGER IDENTITY PRIMARY KEY,
  code VARCHAR(15) UNIQUE NOT NULL,
  namespace_id INTEGER NOT NULL,
  name VARCHAR(45) NOT NULL,
  vehicle_codes VARCHAR(250) NOT NULL,
  active_flag TINYINT DEFAULT 1 NOT NULL,
  updated_by INTEGER NOT NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (namespace_id) REFERENCES namespace(id)
);