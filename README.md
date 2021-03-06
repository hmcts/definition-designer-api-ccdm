# ccd-definition-designer-api ****** NOW REDUNDANT ******
[![API Docs](https://img.shields.io/badge/API%20Docs-site-e140ad.svg)](https://hmcts.github.io/reform-api-docs/swagger.html?url=https://hmcts.github.io/reform-api-docs/specs/ccd-definition-designer-api.json)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://api.travis-ci.org/hmcts/ccd-definition-designer-api.svg?branch=master)](https://travis-ci.org/hmcts/ccd-definition-designer-api)
[![codecov](https://codecov.io/gh/hmcts/ccd-definition-designer-api/branch/master/graph/badge.svg)](https://codecov.io/gh/hmcts/ccd-definition-designer-api)
[![HitCount](http://hits.dwyl.io/hmcts/ccd-definition-designer-api.svg)](#ccd-definition-designer-api)

Validation and persistence of definitions for field types, jurisdictions, case types and associated display elements.

## Overview

`CCD Definition Designer` is a service that allows CRUD operations on draft Definitions.

Spring Boot and Spring Data are used to persist the data in a PostgreSQL database. The database schema is created and maintained by Liquibase changesets applied during application startup.

## Getting started

### Prerequisites

- [Open JDK 8](https://openjdk.java.net/)
- [Docker](https://www.docker.com)

#### Environment variables

The following environment variables are required:

| Name | Default | Description |
|------|---------|-------------|
| DEFINITION_DESIGNER_DB_USERNAME | - | Username for database |
| DEFINITION_DESIGNER_DB_PASSWORD | - | Password for database |
| DEFINITION_DESIGNER_DB_USE_SSL | - | set to `true` if SSL is to be enabled. `false` recommended for local environments. |
| DEFINITION_DESIGNER_IDAM_KEY | - | Definition store's IDAM S2S micro-service secret key. This must match the IDAM instance it's being run against. |
| DEFINITION_DESIGNER_S2S_AUTHORISED_SERVICES | ccd_gw,ccd_admin | Authorised micro-service names for S2S calls |
| IDAM_USER_URL | - | Base URL for IdAM's User API service (idam-app). `http://localhost:4501` for the dockerised local instance or tunneled `dev` instance. |
| IDAM_S2S_URL | - | Base URL for IdAM's S2S API service (service-auth-provider). `http://localhost:4502` for the dockerised local instance or tunneled `dev` instance. |
| APPINSIGHTS_INSTRUMENTATIONKEY | - | secrets for Microsoft Insights logging, can be a dummy string in local |

### Building

The project uses [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html). 

To build project please execute the following command:

```bash
./gradlew clean build
```

### Running

If you want your code to become available to other Docker projects (e.g. for local environment testing), you need to build the image:

```bash
docker-compose build
```

The above will build both the application and database images.  
If you want to build only one of them just specify the name assigned in docker compose file, e.g.:

```bash
docker-compose build ccd-definition-designer-api
```

When the project has been packaged in `target/` directory, 
you can run it by executing following command:

```bash
docker-compose up
```

As a result the following containers will get created and started:

 - Database exposing port `5000`
 - API exposing ports `4454`

#### Database Handling

Database will get initiated when you run `docker-compose up` for the first time by execute all scripts from `database` directory.

You don't need to migrate database manually.

You can connect to the database at `http://localhost:5000` with the username and password set in the environment variables.

## Modules

The application is structured as a multi-module project. The modules are:

### repository

Data access layer.

### domain

Domain and model objects .

### rest-api

Secured RESTful API layer.

### application

Spring application entry point and configuration.

## LICENSE

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.
