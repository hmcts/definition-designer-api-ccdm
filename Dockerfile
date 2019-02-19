FROM hmcts/cnp-java-base:openjdk-8u191-jre-alpine3.9-1.0
LABEL maintainer="https://github.com/hmcts/ccd-definition-designer-api"

ENV JAVA_OPTS "-Djava.security.egd=file:/dev/./urandom"

COPY build/libs/case-definition-designer-api.jar /opt/app
HEALTHCHECK --interval=10s --timeout=10s --retries=10 CMD http_proxy="" curl --silent --fail http://localhost:4451/status/health
EXPOSE 4451
CMD [ "case-definition-designer-api.jar" ]