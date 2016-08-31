[![Build Status](https://travis-ci.org/gustajz/parking-api.svg?branch=master)](https://travis-ci.org/gustajz/parking-api) [![](https://images.microbadger.com/badges/image/gustajz/parking-api.svg)](https://microbadger.com/images/gustajz/parking-api)

# parking-api


### Build

	$ export DOCKER_HOST=unix:///var/run/docker.sock
	$ mvn clean package docker:build -DpushImageTags

### Run PostgreSQL

	$ docker run -d --name postgres1 -e POSTGRES_PASSWORD=postgres -p 5432:5432 \
	   -v $(pwd)/docker/compose/create-db.sh:/docker-entrypoint-initdb.d/create-db.sh postgres

### Run Parking API in Development Profile

    $ docker run -it --rm=true --name parking-api-ui --link postgres:db -p 8080:8080 gustajz/parking-api \
       --ldap.url=ldap://myad.mydomain.local --ldap.domain=mydomain.local --ldap.login_form=true \
       --spring.profiles.active=development,postgresql \
       --spring.datasource.url=jdbc:postgresql://db:5432/parking
	
### Run Parking API in Production Profile (Oracle)

    $ docker run -d --name parking-api -p 8080:8080 \
        -e DB_URL=jdbc:oracle:thin:@db.mydomain.local:1521:XE -e DB_PASS=mypassword \
        -e LDAP_URL=ldap://myad.mydomain.local -e LDAP_DOMAIN=mydomain.local gustajz/parking-api
        
### Run Parking API in Production Profile (PostgreSQL)

    $ docker run -d --name parking-api -p 8080:8080 \
        -e DB_URL=jdbc:postgresql://db.mydomain.local:5432/parking -e DB_PASS=mypassword \
        -e LDAP_URL=ldap://myad.mydomain.local -e LDAP_DOMAIN=mydomain.local gustajz/parking-api \
        --spring.profiles.active=postgresql 
        
