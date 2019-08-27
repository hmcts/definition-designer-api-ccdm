# Keep hub.Dockerfile aligned to this file as far as possible
FROM hmctspublic.azurecr.io/base/java:openjdk-8-distroless-1.0
LABEL maintainer="https://github.com/hmcts/ccd-definition-designer-api"

ENV JAVA_OPTS "-Djava.security.egd=file:/dev/./urandom"

COPY build/libs/case-definition-designer-api.jar /opt/app
HEALTHCHECK --interval=10s --timeout=10s --retries=10 CMD http_proxy="" curl --silent --fail http://localhost:4454/status/health
EXPOSE 4454
CMD [ "case-definition-designer-api.jar" ]
