
# parking-api


### Build

	$ export DOCKER_HOST=unix:///var/run/docker.sock
	$ mvn clean package docker:build -DpushImageTags

### Run PostgreSQL

	$ docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres

### Run Parking API

	$ docker run -it --rm --link postgres:db -p 8080:8080 parking-api --spring.profiles.active=development
