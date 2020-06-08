
DROP TABLE IF EXISTS "account_codes";


CREATE TABLE "account_codes"
(
    "id" serial,
    "account_code" character varying(3) NOT NULL,
    "code_used" character varying(1) NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "account_codes_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "account_configs";

CREATE TABLE "account_configs"
(
    "id" serial,
    "configurability_name" character varying(150),
    "configurability_double_value" character varying(150),
    "configurability_int_value" integer,
    "configurability_string_value_1" character varying(150),
    "configurability_char_value_1" character varying(150),
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "account_id" integer NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "account_configs_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "account_contract_offset";

CREATE TABLE "account_contract_offset"
(
    "id" serial,
    "account_code" character varying(3) NOT NULL,
    "contract_offset" integer NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "account_contract_offset_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "account_customers";

CREATE TABLE "account_customers"
(
    "id" serial,
    "first_name" character varying(50) NOT NULL,
    "middle_name" character varying(50) NOT NULL,
    "last_name" character varying(50) NOT NULL,
    "primary_phone_number" character varying(20) NOT NULL,
    "secondary_phone_number" character varying(20) NOT NULL,
    "email_id" character varying(100) NOT NULL,
    "dob" date NOT NULL,
    "account_id" integer NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "account_customers_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "account_eppopay_plans";

CREATE TABLE "account_eppopay_plans"
(
    "id" serial,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "account_id" integer NOT NULL,
    "eppopay_plan_id" integer NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "account_eppopay_plans_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "account_fees";

CREATE TABLE "account_fees"
(
    "id" serial,
    "account_id" integer NOT NULL,
    "ach_return_fee" numeric(10,4) NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "account_fees_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "accounts";

CREATE TABLE "accounts"
(
    "id" serial,
    "account_code" character varying(3) DEFAULT '0' NOT NULL,
    "account_name" character varying(200) NOT NULL,
    "phone_no" character varying(20) NOT NULL,
    "email_id" character varying(150) NOT NULL,
    "company_name" character varying(50),
    "tax_id" character varying(50),
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "accounts_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "addresses";

CREATE TABLE "addresses"
(
    "id" serial,
    "address_1" character varying(100) NOT NULL,
    "address_2" character varying(100) NOT NULL,
    "city" character varying(50) NOT NULL,
    "zip_code" character varying(15) NOT NULL,
    "state_id" integer NOT NULL,
    "country_id" integer NOT NULL,
    "latitude" numeric(9,6),
    "longitude" numeric(9,6),
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "addresses_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "admin_users";

CREATE TABLE "admin_users"
(
    "id" serial,
    "full_name" character varying(100) NOT NULL,
    "email_id" character varying(150) NOT NULL,
    "contact_number" character varying(20) NOT NULL,
    "status" smallint NOT NULL,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "role_id" integer NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "admin_users_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "bank_account_verifications";

CREATE TABLE "bank_account_verifications"
(
    "id" serial,
    "subdollar_amount1" numeric(10,4) NOT NULL,
    "subdollar_amount2" numeric(10,4) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "bank_account_id" integer NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "bank_account_verifications_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "bank_accounts";

CREATE TABLE "bank_accounts"
(
    "id" serial,
    "payment_type" smallint NOT NULL,
    "bank_account_nick_name" character varying(100) NOT NULL,
    "bank_account_holder_name" character varying(100) NOT NULL,
    "bank_account_type" character varying(1) NOT NULL,
    "bank_routing_number" character varying(9) NOT NULL,
    "bank_name" character varying(50) NOT NULL,
    "bank_account_number" character varying(20) NOT NULL,
    "customer_or_account" character varying(1) NOT NULL,
    "card_holder_first_name" character varying(100),
    "card_holder_last_name" character varying(100),
    "card_number" character varying(25),
    "card_type" character varying(50),
    "card_expiry" character varying(7),
    "credit_debit_type" character varying(2),
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "customer_or_account_id" integer NOT NULL,
    "address_id" integer,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "bank_accounts_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "contract_codes";

CREATE TABLE "contract_codes"
(
    "id" serial,
    "contract_code" character varying(4) NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "contract_codes_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "contract_payments";

CREATE TABLE "contract_payments"
(
    "id" serial,
    "due_number" smallint NOT NULL,
    "rent_amount" numeric(10,4) NOT NULL,
    "carried_over_rent_amount" numeric(10,4) NOT NULL,
    "carried_over_rent_fee" numeric(10,4) NOT NULL,
    "rent_relief_amount" numeric(10,4) NOT NULL,
    "adjustment_amount" numeric(10,4) NOT NULL,
    "merchant_due_date" date,
    "net_amount" numeric(10,4) NOT NULL,
    "eppopay_plan_dates_json" json NOT NULL,
    "adjustment_comment" text NOT NULL,
    "contract_id" integer NOT NULL,
    "eppopay_plan_id" integer NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "status" smallint NOT NULL,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "contract_payments_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "contracts";

CREATE TABLE "contracts"
(
    "id" serial,
    "rent_amount" numeric(10,4) NOT NULL,
    "unique_account_number" character varying(7) NOT NULL,
    "verification_pin" character varying(6) NOT NULL,
    "landlord_bank_account_id" integer NOT NULL,
    "customer_primary_bank_account_id" integer NOT NULL,
    "customer_secondary_bank_account_id" integer NOT NULL,
    "start_date" date,
    "end_date" date,
    "month_on_month" boolean NOT NULL,
    "customer_preferred_payment_plan_dates_json" json NOT NULL,
    "pause_customer_date" date,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "account_id" integer NOT NULL,
    "customer_id" integer NOT NULL,
    "unit_id" integer NOT NULL,
    "customer_preferred_payment_plan_id" integer NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "contracts_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "contracts_account_customers";

CREATE TABLE "contracts_account_customers"
(
    "id" serial,
    "account_customer_id" integer NOT NULL,
    "contract_id" integer NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "contracts_account_customers_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "countries";

CREATE TABLE "countries"
(
    "id" serial,
    "country_name" character varying(100) NOT NULL,
    "two_digit_code" character varying(50) NOT NULL,
    "three_digit_code" character varying(50) NOT NULL,
    "status" smallint NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "countries_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "customer_payments";

CREATE TABLE "customer_payments"
(
    "id" serial,
    "payment_date" date,
    "amount" numeric(10,4) NOT NULL,
    "smoothing_fee" numeric(10,4) NOT NULL,
    "eppopay_commission" numeric(10,4) NOT NULL,
    "net_amount" numeric(10,4) NOT NULL,
    "contract_payment_id" integer NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "active_inactive" character varying(50) NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "customer_payments_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "customers";

CREATE TABLE "customers"
(
    "id" serial,
    "first_name" character varying(50) NOT NULL,
    "middle_name" character varying(50),
    "last_name" character varying(50),
    "salutation" character varying(10),
    "email_id" character varying(150) NOT NULL,
    "primary_phone_number" character varying(20) NOT NULL,
    "secondary_phone_number" character varying(20) NOT NULL,
    "dob" date,
    "email_notif_preference" smallint DEFAULT '1' NOT NULL,
    "text_notif_preference" smallint DEFAULT '1' NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "address_id" integer,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "customers_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "eppopay_plan_types";

CREATE TABLE "eppopay_plan_types"
(
    "id" serial,
    "plan_type_name" character varying(50) NOT NULL,
    "plan_type_description" text NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "eppopay_plan_types_pkey" PRIMARY KEY ("id")
)

DROP TABLE IF EXISTS "eppopay_plans";

CREATE TABLE "eppopay_plans"
(
    "id" serial,
    "plan_name" character varying(150) NOT NULL,
    "frequency" character varying(50) NOT NULL,
    "rule_json" json NOT NULL,
    "fees_json" json NOT NULL,
    "plan_type" character varying(100) NOT NULL,
    "eppopay_fee_json" json NOT NULL,
    "approval_required" smallint NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "eppopay_plan_type_id" integer NOT NULL,
    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "eppopay_plans_pkey" PRIMARY KEY ("id")
)
