
# parking-api


### Build


	$ export DOCKER_HOST=unix:///var/run/docker.sock
	$ mvn clean package docker:build -DpushImageTags


### Run CouchDB

	$ docker run -d --name couchdb -p 5984:5984 couchdb


### Run NeuroJ

	$ docker run -d --name parking-api -p 8080:8080 --link couchdb:couchdb -e COUCHDB_URL=http://couchdb:5984/ parking-api
