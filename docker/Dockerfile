FROM anapsix/alpine-java

MAINTAINER Gustavo Jotz <gustavojotz@cwi.com.br>

RUN apk add --no-cache curl

ENV TZ America/Sao_Paulo

ENV DB_URL jdbc:postgresql://db:5432/parking
ENV DB_USER parking
ENV DB_PASS parking

ENV LDAP_URL ldap://s005.cwinet.local
ENV LDAP_DOMAIN cwinet.local

HEALTHCHECK --interval=5s --timeout=5s \
  CMD curl -f http://localhost:8080/api/health || exit 1

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "parking-api.jar"]

ARG VERSION

ADD /parking-api-${VERSION}.jar /parking-api.jar