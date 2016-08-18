
[![Build Status](https://travis-ci.org/gustajz/parking-api.svg?branch=master)](https://travis-ci.org/gustajz/parking-api)

# parking-api


### Build

	$ export DOCKER_HOST=unix:///var/run/docker.sock
	$ mvn clean package docker:build -DpushImageTags

### Run PostgreSQL

	$ docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres

### Run Parking API Development

	$ docker run -it --rm=true --link postgres:db -p 8080:8080 parking-api \
	   --spring.profiles.active=development \
	   --ldap.url=ldap://10.0.100.20 \
	   --spring.datasource.url=jdbc:postgresql://db:5432/parking
	
### DockerHUB

    $ docker run -d --name parking --link postgres:db -p 8080:8080 gustajz/parking-api
