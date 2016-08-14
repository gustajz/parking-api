FROM anapsix/alpine-java

MAINTAINER Gustavo Jotz <gustavojotz@cwi.com.br>

RUN apk add --no-cache curl

ADD /parking-api.jar //

ENV TZ America/Sao_Paulo

ENV DB_HOST db
ENV DB_PORT 5432
ENV DB_NAME parking
ENV DB_USER parking
ENV DB_PASS parking
ENV LDAP_URL ldap://ldap.renner.local/dc=renner,dc=local

HEALTHCHECK --interval=5s --timeout=5s \
  CMD curl -f http://localhost:8080/api/health || exit 1

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "parking-api.jar"]

#CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "parking-api.jar"]
