variable "product" {
  type    = "string"
}

variable "raw_product" {
  default = "ccd" // jenkins-library overrides product for PRs and adds e.g. pr-118-ccd
}

variable "component" {
  type    = "string"
}

variable "location" {
  type    = "string"
  default = "UK South"
}

variable "env" {
  type = "string"
}

variable "subscription" {
  type = "string"
}

variable "capacity" {
  default = "1"
}

variable "common_tags" {
  type = "map"
}

variable "ilbIp"{}

variable "asp_name" {
  type = "string"
  description = "App Service Plan (ASP) to use for the webapp, 'use_shared' to make use of the shared ASP"
  default = "use_shared"
}

variable "asp_rg" {
  type = "string"
  description = "App Service Plan (ASP) resource group for 'asp_name', 'use_shared' to make use of the shared resource group"
  default = "use_shared"
}

variable "tenant_id" {
  description = "(Required) The Azure Active Directory tenant ID that should be used for authenticating requests to the key vault. This is usually sourced from environemnt variables and not normally required to be specified."
}

variable "jenkins_AAD_objectId" {
  type = "string"
  description = "(Required) The Azure AD object ID of a user, service principal or security group in the Azure Active Directory tenant for the vault. The object ID must be unique for the list of access policies."
}

variable "frontend_url" {
  type = "string"
  default = ""
  description = "Optional front end URL to use for building redirect URI"
}

////////////////////////////////
// Database
////////////////////////////////

variable "postgresql_user" {
  default = "ccd"
}

variable "database_name" {
  default = "ccd_definition_designer"
}

////////////////////////////////
// IDAM
////////////////////////////////

variable "idam_api_url" {
}

////////////////////////////////
// S2S
////////////////////////////////

variable "authorised-services" {
  default = "ccd_gw,ccd_admin"
}

////////////////////////////////
// ELASTIC SEARCH
////////////////////////////////

variable "elastic_search_enabled" {
  default = "false"
}

variable "elastic_search_port" {
  default = "9200"
}

variable "elastic_search_scheme" {
  default = "http"
}

variable "elastic_search_index_shards" {
  default = "2"
}

variable "elastic_search_index_shards_replicas" {
  default = "1"
}

variable "elastic_search_fail_on_import" {
  default = "false"
}

variable "elastic_search_dynamc" {
  default = "false"
}

variable "elastic_search_case_index_name_format" {
  default = "%s_cases"
}
