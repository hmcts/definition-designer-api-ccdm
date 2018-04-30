provider "vault" {
  address = "https://vault.reform.hmcts.net:6200"
}

locals {
  env_ase_url = "${var.env}.service.${data.terraform_remote_state.core_apps_compute.ase_name[0]}.internal"

  app_full_name = "${var.product}-${var.component}"
}

data "vault_generic_secret" "definition_store_item_key" {
  path = "secret/${var.vault_section}/ccidam/service-auth-provider/api/microservice-keys/ccd-definition"
}

module "case-definition-store-api" {
  source   = "git@github.com:contino/moj-module-webapp?ref=master"
  product  = "${local.app_full_name}"
  location = "${var.location}"
  env      = "${var.env}"
  ilbIp    = "${var.ilbIp}"
  subscription = "${var.subscription}"

  app_settings = {
    DEFINITION_STORE_DB_HOST = "${var.use_uk_db != "true" ? module.postgres-case-definition-store.host_name : module.definition-store-db.host_name}"
    DEFINITION_STORE_DB_PORT = "${var.use_uk_db != "true" ? module.postgres-case-definition-store.postgresql_listen_port : module.definition-store-db.postgresql_listen_port}"
    DEFINITION_STORE_DB_NAME = "${var.use_uk_db != "true" ? module.postgres-case-definition-store.postgresql_database : module.definition-store-db.postgresql_database}"
    DEFINITION_STORE_DB_USERNAME = "${var.use_uk_db != "true" ? module.postgres-case-definition-store.user_name : module.definition-store-db.user_name}"
    DEFINITION_STORE_DB_PASSWORD = "${var.use_uk_db != "true" ? module.postgres-case-definition-store.postgresql_password : module.definition-store-db.postgresql_password}"
    IDAM_USER_URL = "${var.idam_api_url}"
    IDAM_S2S_URL = "${var.s2s_url}"
    DEFINITION_STORE_IDAM_KEY = "${data.vault_generic_secret.definition_store_item_key.data["value"]}"
    USER_PROFILE_HOST = "http://ccd-user-profile-api-${local.env_ase_url}"
  }

}

module "postgres-case-definition-store" {
  source              = "git@github.com:contino/moj-module-postgres?ref=master"
  product             = "${var.product}-definition-store"
  location            = "West Europe"
  env                 = "${var.env}"
  postgresql_user     = "ccd"
}

module "definition-store-db" {
  source = "git@github.com:hmcts/moj-module-postgres?ref=cnp-449-tactical"
  product = "${local.app_full_name}-postgres-db"
  location = "${var.location}"
  env = "${var.env}"
  postgresql_user = "${var.postgresql_user}"
  database_name = "${var.database_name}"
  sku_name = "GP_Gen5_2"
  sku_tier = "GeneralPurpose"
  storage_mb = "51200"
}
