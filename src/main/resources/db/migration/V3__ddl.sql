-- Adminer 4.7.6 PostgreSQL dump
DROP TABLE IF EXISTS "accounts";
DROP SEQUENCE IF EXISTS accounts_id_seq;
DROP TABLE IF EXISTS "eppopay_plan_types";
DROP SEQUENCE IF EXISTS eppopay_plan_types_id_seq;
DROP TABLE IF EXISTS "eppopay_plans";
DROP SEQUENCE IF EXISTS eppopay_plans_id_seq;
DROP TABLE IF EXISTS "account_codes";
DROP SEQUENCE IF EXISTS account_codes_id_seq;
DROP TABLE IF EXISTS "account_configs";
DROP SEQUENCE IF EXISTS account_configs_id_seq;
DROP TABLE IF EXISTS "account_contract_offset";
DROP SEQUENCE IF EXISTS account_contract_offset_id_seq;
DROP TABLE IF EXISTS "account_customers";
DROP SEQUENCE IF EXISTS account_customers_id_seq;
DROP TABLE IF EXISTS "account_eppopay_plans";
DROP SEQUENCE IF EXISTS account_eppopay_plans_id_seq;
DROP TABLE IF EXISTS "account_fees";
DROP SEQUENCE IF EXISTS account_fees_id_seq;
DROP TABLE IF EXISTS "addresses";
DROP SEQUENCE IF EXISTS addresses_id_seq;
DROP TABLE IF EXISTS "admin_users";
DROP SEQUENCE IF EXISTS admin_users_id_seq;
DROP TABLE IF EXISTS "bank_account_verifications";
DROP SEQUENCE IF EXISTS bank_account_verifications_id_seq;
DROP TABLE IF EXISTS "bank_accounts";
DROP SEQUENCE IF EXISTS bank_accounts_id_seq;
DROP TABLE IF EXISTS "contract_account_customers";
DROP SEQUENCE IF EXISTS contract_account_customers_id_seq;
DROP TABLE IF EXISTS "contract_codes";
DROP SEQUENCE IF EXISTS contract_codes_id_seq;
DROP TABLE IF EXISTS "contract_payments";
DROP SEQUENCE IF EXISTS contract_payments_id_seq;
DROP TABLE IF EXISTS "countries";
DROP SEQUENCE IF EXISTS countries_id_seq;
DROP TABLE IF EXISTS "contracts";
DROP SEQUENCE IF EXISTS contracts_id_seq;
DROP TABLE IF EXISTS "customer_payments";
DROP SEQUENCE IF EXISTS customer_payments_id_seq;
DROP TABLE IF EXISTS "customers";
DROP SEQUENCE IF EXISTS customers_id_seq;
DROP TABLE IF EXISTS "global_payments";
DROP SEQUENCE IF EXISTS global_payments_id_seq;
DROP TABLE IF EXISTS "holidays";
DROP SEQUENCE IF EXISTS holidays_id_seq;
DROP TABLE IF EXISTS "invoice_items";
DROP SEQUENCE IF EXISTS invoice_items_id_seq;
DROP TABLE IF EXISTS "invoices";
DROP SEQUENCE IF EXISTS invoices_id_seq;
DROP TABLE IF EXISTS "logs";
DROP SEQUENCE IF EXISTS logs_id_seq;
DROP TABLE IF EXISTS "merchant_payment_receivables";
DROP SEQUENCE IF EXISTS merchant_payment_receivables_id_seq;
DROP TABLE IF EXISTS "merchant_payment_schedules";
DROP SEQUENCE IF EXISTS merchant_payment_schedules_id_seq;
DROP TABLE IF EXISTS "merchant_site_managers";
DROP SEQUENCE IF EXISTS merchant_site_managers_id_seq;
DROP TABLE IF EXISTS "migrations";
DROP SEQUENCE IF EXISTS migrations_id_seq;
DROP TABLE IF EXISTS "plan_change_workflows";
DROP SEQUENCE IF EXISTS plan_change_workflows_id_seq;
DROP TABLE IF EXISTS "roles";
DROP SEQUENCE IF EXISTS roles_id_seq;
DROP TABLE IF EXISTS "states";
DROP SEQUENCE IF EXISTS states_id_seq;
DROP TABLE IF EXISTS "status_details";
DROP SEQUENCE IF EXISTS status_details_id_seq;
DROP TABLE IF EXISTS "sub_entities";
DROP SEQUENCE IF EXISTS sub_entities_id_seq;
DROP TABLE IF EXISTS "sub_entity_plans";
DROP SEQUENCE IF EXISTS sub_entity_plans_id_seq;
DROP TABLE IF EXISTS "sub_entity_site_managers";
DROP SEQUENCE IF EXISTS sub_entity_site_managers_id_seq;
DROP TABLE IF EXISTS "sub_entity_units";
DROP SEQUENCE IF EXISTS sub_entity_units_id_seq;
DROP TABLE IF EXISTS "transactions";
DROP SEQUENCE IF EXISTS transactions_id_seq;
DROP TABLE IF EXISTS "unit_activations";
DROP SEQUENCE IF EXISTS unit_activations_id_seq;
DROP TABLE IF EXISTS "unit_groups";
DROP SEQUENCE IF EXISTS unit_groups_id_seq;
DROP TABLE IF EXISTS "unit_rents";
DROP SEQUENCE IF EXISTS unit_rents_id_seq;
DROP TABLE IF EXISTS "unit_types";
DROP SEQUENCE IF EXISTS unit_types_id_seq;
DROP TABLE IF EXISTS "units";
DROP SEQUENCE IF EXISTS units_id_seq;
DROP TABLE IF EXISTS "users";
DROP SEQUENCE IF EXISTS users_id_seq;
CREATE SEQUENCE accounts_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "accounts" (
    "id" bigint DEFAULT nextval('accounts_id_seq') NOT NULL,
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
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "accounts_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE eppopay_plan_types_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "eppopay_plan_types" (
    "id" bigint DEFAULT nextval('eppopay_plan_types_id_seq') NOT NULL,
    "plan_type_name" character varying(50) NOT NULL,
    "plan_type_description" text NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "eppopay_plan_types_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE eppopay_plans_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "eppopay_plans" (
    "id" bigint DEFAULT nextval('eppopay_plans_id_seq') NOT NULL,
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
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "eppopay_plans_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "eppopay_plans_eppopay_plan_type_id_foreign" FOREIGN KEY (eppopay_plan_type_id) REFERENCES eppopay_plan_types(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE countries_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "countries" (
    "id" bigint DEFAULT nextval('countries_id_seq') NOT NULL,
    "country_name" character varying(100) NOT NULL,
    "two_digit_code" character varying(50) NOT NULL,
    "three_digit_code" character varying(50) NOT NULL,
    "status" smallint NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "countries_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE states_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "states" (
    "id" bigint DEFAULT nextval('states_id_seq') NOT NULL,
    "state_name" character varying(100) NOT NULL,
    "state_code" character varying(50) NOT NULL,
    "status" smallint NOT NULL,
    "saas_sales_tax" numeric(10, 4) NOT NULL,
    "smoothing_fee_tax" numeric(10, 4) NOT NULL,
    "country_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "states_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "states_country_id_foreign" FOREIGN KEY (country_id) REFERENCES countries(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE roles_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "roles" (
    "id" bigint DEFAULT nextval('roles_id_seq') NOT NULL,
    "role_name" character varying(50) NOT NULL,
    "role_system" character varying(1) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "roles_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE bank_accounts_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "bank_accounts" (
    "id" bigint DEFAULT nextval('bank_accounts_id_seq') NOT NULL,
    "payment_type" smallint NOT NULL,
    "bank_account_nick_name" character varying(100) NOT NULL,
    "bank_account_holder_name" character varying(100) NOT NULL,
    "bank_account_type" character varying(1) NOT NULL,
    "bank_routing_number" character varying(9) NOT NULL,
    "bank_name" character varying(50),
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
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "bank_accounts_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "bank_accounts_address_id_foreign" FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false) CREATE SEQUENCE account_codes_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "account_codes" (
    "id" bigint DEFAULT nextval('account_codes_id_seq') NOT NULL,
    "account_code" character varying(3) NOT NULL,
    "code_used" character varying(1) NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "account_codes_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE account_configs_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "account_configs" (
    "id" bigint DEFAULT nextval('account_configs_id_seq') NOT NULL,
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
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "account_configs_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "account_configs_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE account_contract_offset_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "account_contract_offset" (
    "id" bigint DEFAULT nextval('account_contract_offset_id_seq') NOT NULL,
    "account_code" character varying(3) NOT NULL,
    "contract_offset" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "account_contract_offset_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE account_customers_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "account_customers" (
    "id" bigint DEFAULT nextval('account_customers_id_seq') NOT NULL,
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
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "account_customers_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "account_customers_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE account_eppopay_plans_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "account_eppopay_plans" (
    "id" bigint DEFAULT nextval('account_eppopay_plans_id_seq') NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "account_id" integer NOT NULL,
    "eppopay_plan_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "account_eppopay_plans_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "account_eppopay_plans_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "account_eppopay_plans_eppopay_plan_id_foreign" FOREIGN KEY (eppopay_plan_id) REFERENCES eppopay_plans(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE account_fees_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "account_fees" (
    "id" bigint DEFAULT nextval('account_fees_id_seq') NOT NULL,
    "account_id" integer NOT NULL,
    "ach_return_fee" numeric(10, 4) NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "account_fees_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "account_fees_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE addresses_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "addresses" (
    "id" bigint DEFAULT nextval('addresses_id_seq') NOT NULL,
    "address_1" character varying(100) NOT NULL,
    "address_2" character varying(100) NOT NULL,
    "city" character varying(50) NOT NULL,
    "zip_code" character varying(15) NOT NULL,
    "state_id" integer NOT NULL,
    "country_id" integer NOT NULL,
    "latitude" numeric(9, 6),
    "longitude" numeric(9, 6),
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "addresses_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "addresses_country_id_foreign" FOREIGN KEY (country_id) REFERENCES countries(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "addresses_state_id_foreign" FOREIGN KEY (state_id) REFERENCES states(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE admin_users_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "admin_users" (
    "id" bigint DEFAULT nextval('admin_users_id_seq') NOT NULL,
    "full_name" character varying(100) NOT NULL,
    "email_id" character varying(150) NOT NULL,
    "contact_number" character varying(20) NOT NULL,
    "status" smallint NOT NULL,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "role_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "admin_users_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "admin_users_role_id_foreign" FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE bank_account_verifications_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "bank_account_verifications" (
    "id" bigint DEFAULT nextval('bank_account_verifications_id_seq') NOT NULL,
    "subdollar_amount1" numeric(10, 4) NOT NULL,
    "subdollar_amount2" numeric(10, 4) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "bank_account_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "bank_account_verifications_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "bank_account_verifications_bank_account_id_foreign" FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
;
CREATE SEQUENCE contract_account_customers_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "contract_account_customers" (
    "id" bigint DEFAULT nextval('contract_account_customers_id_seq') NOT NULL,
    "account_customer_id" integer NOT NULL,
    "contract_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "contract_account_customers_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "contract_account_customers_account_customer_id_foreign" FOREIGN KEY (account_customer_id) REFERENCES account_customers(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "contract_account_customers_contract_id_foreign" FOREIGN KEY (contract_id) REFERENCES contracts(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE contract_codes_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "contract_codes" (
    "id" bigint DEFAULT nextval('contract_codes_id_seq') NOT NULL,
    "contract_code" character varying(4) NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "contract_codes_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE contract_payments_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "contract_payments" (
    "id" bigint DEFAULT nextval('contract_payments_id_seq') NOT NULL,
    "due_number" smallint NOT NULL,
    "rent_amount" numeric(10, 4) NOT NULL,
    "carried_over_rent_amount" numeric(10, 4) NOT NULL,
    "carried_over_rent_fee" numeric(10, 4) NOT NULL,
    "rent_relief_amount" numeric(10, 4) NOT NULL,
    "adjustment_amount" numeric(10, 4) NOT NULL,
    "merchant_due_date" date,
    "net_amount" numeric(10, 4) NOT NULL,
    "eppopay_plan_dates_json" json NOT NULL,
    "adjustment_comment" text NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "status" smallint NOT NULL,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "contract_id" integer NOT NULL,
    "eppopay_plan_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "contract_payments_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "contract_payments_contract_id_foreign" FOREIGN KEY (contract_id) REFERENCES contracts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "contract_payments_eppopay_plan_id_foreign" FOREIGN KEY (eppopay_plan_id) REFERENCES eppopay_plans(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE contracts_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "contracts" (
    "id" bigint DEFAULT nextval('contracts_id_seq') NOT NULL,
    "rent_amount" numeric(10, 4) NOT NULL,
    "unique_account_number" character varying(7) NOT NULL,
    "verification_pin" character varying(6) NOT NULL,
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
    "customer_preferred_bank_account_id" integer,
    "customer_preferred_global_payment_id" integer,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "contracts_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "contracts_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "contracts_customer_id_foreign" FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "contracts_customer_preferred_bank_account_id_foreign" FOREIGN KEY (customer_preferred_bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "contracts_customer_preferred_global_payment_id_foreign" FOREIGN KEY (customer_preferred_global_payment_id) REFERENCES global_payments(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "contracts_customer_preferred_payment_plan_id_foreign" FOREIGN KEY (customer_preferred_payment_plan_id) REFERENCES eppopay_plans(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "contracts_unit_id_foreign" FOREIGN KEY (unit_id) REFERENCES units(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE customer_payments_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "customer_payments" (
    "id" bigint DEFAULT nextval('customer_payments_id_seq') NOT NULL,
    "payment_date" date,
    "amount" numeric(10, 4) NOT NULL,
    "smoothing_fee" numeric(10, 4) NOT NULL,
    "eppopay_commission" numeric(10, 4) NOT NULL,
    "net_amount" numeric(10, 4) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "contract_payment_id" integer NOT NULL,
    "bank_account_id" integer NOT NULL,
    "global_payment_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "customer_payments_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "customer_payments_bank_account_id_foreign" FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "customer_payments_contract_payment_id_foreign" FOREIGN KEY (contract_payment_id) REFERENCES contract_payments(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "customer_payments_global_payment_id_foreign" FOREIGN KEY (global_payment_id) REFERENCES global_payments(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE customers_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "customers" (
    "id" bigint DEFAULT nextval('customers_id_seq') NOT NULL,
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
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "customers_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "customers_address_id_foreign" FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE global_payments_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "global_payments" (
    "id" bigint DEFAULT nextval('global_payments_id_seq') NOT NULL,
    "payment_name" character varying(50) NOT NULL,
    "description" text NOT NULL,
    "status" smallint NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "global_payments_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE holidays_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "holidays" (
    "id" bigint DEFAULT nextval('holidays_id_seq') NOT NULL,
    "holiday_date" date NOT NULL,
    "holiday" character varying(150) NOT NULL,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "holidays_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE invoice_items_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "invoice_items" (
    "id" bigint DEFAULT nextval('invoice_items_id_seq') NOT NULL,
    "amount" numeric(10, 4) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "mer_collected_amount" numeric(10, 4) NOT NULL,
    "customer_payment_id" integer NOT NULL,
    "invoice_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "invoice_items_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "invoice_items_customer_payment_id_foreign" FOREIGN KEY (customer_payment_id) REFERENCES customer_payments(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "invoice_items_invoice_id_foreign" FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE invoices_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "invoices" (
    "id" bigint DEFAULT nextval('invoices_id_seq') NOT NULL,
    "invoice_number" character varying(50) NOT NULL,
    "invoice_date" date,
    "customer_or_account" character varying(1) NOT NULL,
    "customer_or_account_id" integer NOT NULL,
    "amount" numeric(10, 4) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "mer_collected_amount" numeric(10, 4) NOT NULL,
    "invoice_comments" text NOT NULL,
    "invoice_notes_internal" text NOT NULL,
    "transaction_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "invoices_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "invoices_transaction_id_foreign" FOREIGN KEY (transaction_id) REFERENCES transactions(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE logs_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "logs" (
    "id" bigint DEFAULT nextval('logs_id_seq') NOT NULL,
    "log_event" character varying(50) NOT NULL,
    "reference_id" integer NOT NULL,
    "user_type" character varying(1) NOT NULL,
    "user_id" integer NOT NULL,
    "log_message" text NOT NULL,
    "log_data_json" json NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "logs_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE merchant_payment_receivables_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "merchant_payment_receivables" (
    "id" bigint DEFAULT nextval('merchant_payment_receivables_id_seq') NOT NULL,
    "day_of_month" character varying(50) NOT NULL,
    "percent_receivable" numeric(10, 4) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "account_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "merchant_payment_receivables_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "merchant_payment_receivables_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE merchant_payment_schedules_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "merchant_payment_schedules" (
    "id" bigint DEFAULT nextval('merchant_payment_schedules_id_seq') NOT NULL,
    "payment_date" date,
    "amount" numeric(10, 4) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "active_inactive" character varying(50) NOT NULL,
    "account_id" integer NOT NULL,
    "contract_payment_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "merchant_payment_schedules_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "merchant_payment_schedules_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "merchant_payment_schedules_contract_payment_id_foreign" FOREIGN KEY (contract_payment_id) REFERENCES contract_payments(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE merchant_site_managers_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "merchant_site_managers" (
    "id" bigint DEFAULT nextval('merchant_site_managers_id_seq') NOT NULL,
    "first_name" character varying(100) NOT NULL,
    "middle_name" character varying(100) NOT NULL,
    "last_name" character varying(100) NOT NULL,
    "phone_no" character varying(20) NOT NULL,
    "email_id" character varying(150) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "account_id" integer NOT NULL,
    "role_id" integer NOT NULL,
    "address_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "merchant_site_managers_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "merchant_site_managers_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "merchant_site_managers_address_id_foreign" FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "merchant_site_managers_role_id_foreign" FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE migrations_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "migrations" (
    "id" integer DEFAULT nextval('migrations_id_seq') NOT NULL,
    "migration" character varying(255) NOT NULL,
    "batch" integer NOT NULL,
    CONSTRAINT "migrations_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE plan_change_workflows_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "plan_change_workflows" (
    "id" bigint DEFAULT nextval('plan_change_workflows_id_seq') NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "contract_payment_id" integer NOT NULL,
    "eppopay_plan_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "plan_change_workflows_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "plan_change_workflows_contract_payment_id_foreign" FOREIGN KEY (contract_payment_id) REFERENCES contract_payments(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "plan_change_workflows_eppopay_plan_id_foreign" FOREIGN KEY (eppopay_plan_id) REFERENCES eppopay_plans(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE status_details_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "status_details" (
    "id" bigint DEFAULT nextval('status_details_id_seq') NOT NULL,
    "status_value" character varying(100) NOT NULL,
    "module_name" character varying(100) NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "status_details_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE sub_entities_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "sub_entities" (
    "id" bigint DEFAULT nextval('sub_entities_id_seq') NOT NULL,
    "sub_entity_name" character varying(150) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "legal_state_code" character varying(50) NOT NULL,
    "is_default" character varying(1) NOT NULL,
    "account_id" integer NOT NULL,
    "bank_account_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "sub_entities_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "sub_entities_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "sub_entities_bank_account_id_foreign" FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE sub_entity_plans_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "sub_entity_plans" (
    "id" bigint DEFAULT nextval('sub_entity_plans_id_seq') NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "sub_entity_id" integer NOT NULL,
    "eppopay_plan_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "sub_entity_plans_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "sub_entity_plans_eppopay_plan_id_foreign" FOREIGN KEY (eppopay_plan_id) REFERENCES eppopay_plans(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "sub_entity_plans_sub_entity_id_foreign" FOREIGN KEY (sub_entity_id) REFERENCES sub_entities(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE sub_entity_site_managers_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "sub_entity_site_managers" (
    "id" bigint DEFAULT nextval('sub_entity_site_managers_id_seq') NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "sub_entity_id" integer NOT NULL,
    "merchant_site_manager_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "sub_entity_site_managers_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "sub_entity_site_managers_merchant_site_manager_id_foreign" FOREIGN KEY (merchant_site_manager_id) REFERENCES merchant_site_managers(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "sub_entity_site_managers_sub_entity_id_foreign" FOREIGN KEY (sub_entity_id) REFERENCES sub_entities(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE sub_entity_units_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "sub_entity_units" (
    "id" bigint DEFAULT nextval('sub_entity_units_id_seq') NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "sub_entity_id" integer NOT NULL,
    "unit_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "sub_entity_units_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "sub_entity_units_sub_entity_id_foreign" FOREIGN KEY (sub_entity_id) REFERENCES sub_entities(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "sub_entity_units_unit_id_foreign" FOREIGN KEY (unit_id) REFERENCES units(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE transactions_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "transactions" (
    "id" bigint DEFAULT nextval('transactions_id_seq') NOT NULL,
    "transaction_date" date,
    "payment_type" character varying(50) NOT NULL,
    "amount" numeric(10, 4) NOT NULL,
    "payment_provider" character varying(150) NOT NULL,
    "provider_transaction_id" integer NOT NULL,
    "provider_transaction_status" character varying(50) NOT NULL,
    "parent_transaction_id" integer NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "customer_payment_id" integer NOT NULL,
    "bank_account_id" integer NOT NULL,
    "global_payment_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "transactions_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "transactions_bank_account_id_foreign" FOREIGN KEY (bank_account_id) REFERENCES bank_accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "transactions_customer_payment_id_foreign" FOREIGN KEY (customer_payment_id) REFERENCES customer_payments(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "transactions_global_payment_id_foreign" FOREIGN KEY (global_payment_id) REFERENCES global_payments(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE unit_activations_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "unit_activations" (
    "id" bigint DEFAULT nextval('unit_activations_id_seq') NOT NULL,
    "start_date" date NOT NULL,
    "end_date" date NOT NULL,
    "active_inactive" character varying(50) NOT NULL,
    "delete" character varying(20) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "unit_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "unit_activations_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "unit_activations_unit_id_foreign" FOREIGN KEY (unit_id) REFERENCES units(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE unit_groups_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "unit_groups" (
    "id" bigint DEFAULT nextval('unit_groups_id_seq') NOT NULL,
    "unit_group_name" character varying(150) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "account_id" integer NOT NULL,
    "address_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "unit_groups_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "unit_groups_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "unit_groups_address_id_foreign" FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE unit_rents_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "unit_rents" (
    "id" bigint DEFAULT nextval('unit_rents_id_seq') NOT NULL,
    "rent_amount" numeric(10, 4) NOT NULL,
    "rent_start_date" date NOT NULL,
    "rent_end_date" date NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "rent_type" character varying(255) DEFAULT '0' NOT NULL,
    "unit_id" smallint NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "unit_rents_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "unit_rents_unit_id_foreign" FOREIGN KEY (unit_id) REFERENCES units(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE unit_types_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "unit_types" (
    "id" bigint DEFAULT nextval('unit_types_id_seq') NOT NULL,
    "unit_type_name" character varying(150) NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "unit_types_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
CREATE SEQUENCE units_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "units" (
    "id" bigint DEFAULT nextval('units_id_seq') NOT NULL,
    "unit_identifier" character varying(50) NOT NULL,
    "unit_name" character varying(100) NOT NULL,
    "building_name" character varying(100) NOT NULL,
    "square_feet" numeric(10, 4) NOT NULL,
    "no_of_bathrooms" integer NOT NULL,
    "no_of_bedrooms" integer NOT NULL,
    "other_information" text NOT NULL,
    "created_by" integer NOT NULL,
    "updated_by" integer,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "status" smallint NOT NULL,
    "unit_type_id" integer,
    "unit_group_id" integer,
    "address_id" integer,
    "account_id" integer NOT NULL,
    "sub_entity_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "units_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "units_account_id_foreign" FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "units_address_id_foreign" FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "units_sub_entity_id_foreign" FOREIGN KEY (sub_entity_id) REFERENCES sub_entities(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "units_unit_group_id_foreign" FOREIGN KEY (unit_group_id) REFERENCES unit_groups(id) ON DELETE CASCADE NOT DEFERRABLE,
    CONSTRAINT "units_unit_type_id_foreign" FOREIGN KEY (unit_type_id) REFERENCES unit_types(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
CREATE SEQUENCE users_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE "users" (
    "id" bigint DEFAULT nextval('users_id_seq') NOT NULL,
    "az_ad_id" character varying(50) NOT NULL,
    "user_ref_id" integer NOT NULL,
    "is_deleted" smallint DEFAULT '0' NOT NULL,
    "role_id" integer NOT NULL,
    "created_at" timestamp(0),
    "updated_at" timestamp(0),
    CONSTRAINT "users_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "users_role_id_foreign" FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE NOT DEFERRABLE
) WITH (oids = false);
-- 2020-06-17 14:28:16.292071+05:30