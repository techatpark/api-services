INSERT INTO "audit_log" (
    active_flag,
    code,
    event,
    log1,
    log2,
    namespace_id,
    table_name,
    updated_at,
    updated_by
  )
VALUES(
    1,
    'code2',
    'event:character',
    'log1:character',
    'log2:character',
    1,
    'table_name',
    CURRENT_TIMESTAMP,
    1
  );
INSERT INTO "namespace" (
    active_flag,
    code,
    name,
    updated_at,
    updated_by
  )
VALUES
  (
    1,
    'namespacecode5',
    'namespacename5',
    CURRENT_TIMESTAMP,
    13
  );
INSERT INTO "practice_db"."public"."namespace" (
    active_flag,
    code,
    name,
    updated_at,
    updated_by
  )
VALUES
  (
    1,
    'namespacecode2',
    'namespacename2',
    CURRENT_TIMESTAMP,
    11
  ),
  (
    1,
    'namespacecode3',
    'namespacename4',
    CURRENT_TIMESTAMP,
    15
  ),
  (
    1,
    'namespacecode4',
    'namespacename4',
    CURRENT_TIMESTAMP,
    11
  );
SELECT
  *
FROM namespace;
INSERT INTO "practice_db"."public"."device" (
    active_flag,
    api_flag,
    code,
    device_imei_code,
    device_model_id,
    gsm_code,
    namespace_id,
    remarks,
    sensor,
    updated_at,
    updated_by
  )
VALUES
  (
    1,
    1,
    'dev5',
    'I4WDASD',
    7254,
    'gsmcode578',
    3,
    'device is active',
    'NULL',
    CURRENT_TIMESTAMP,
    145
  );
Select
  *
FROM namespace
ORDER BY
  updated_by,
  id;
INSERT INTO "practice_db"."public"."geo_location_address_details" (
    area,
    city,
    landmark,
    location_address_code,
    postal_code,
    road,
    state,
    updated_at,
    updated_by
  )
VALUES
  (
    'rspuram',
    'coimbatore',
    'Near Busstation7',
    'code755',
    'CBE236',
    'gtsfe road',
    'Tamil Nadu',
    CURRENT_TIMESTAMP,
    212
  );
SELECT
  *
FROM geo_location_address_details;
Select
  CONCAT(
    road,
    ',',
    area,
    ',',
    landmark,
    ',',
    city,
    ',',
    postal_code
  ) AS currentlocation
from geo_location_address_details;