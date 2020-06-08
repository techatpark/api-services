
DROP TABLE IF EXISTS "account_codes";


CREATE TABLE "public"."account_codes"
(
    "id" serial,
    "account_code" character varying(3) NOT NULL,
    "code_used" character varying(1) NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "account_codes_pkey" PRIMARY KEY ("id")
)

