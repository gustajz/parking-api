FROM anapsix/alpine-java

MAINTAINER Gustavo Jotz <gustavojotz@cwi.com.br>

RUN apk add --no-cache curl

ADD /parking-api.jar //

HEALTHCHECK --interval=5s --timeout=5s \
  CMD curl -f http://localhost:8080/parking/api/health || exit 1
  
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "parking-api.jar"]
