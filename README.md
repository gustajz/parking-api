[![Build Status](https://travis-ci.org/gustajz/parking-api.svg?branch=master)](https://travis-ci.org/gustajz/parking-api) [![](https://images.microbadger.com/badges/image/gustajz/parking-api.svg)](https://microbadger.com/images/gustajz/parking-api) [![Project Stats](https://www.openhub.net/p/parking-api/widgets/project_thin_badge.gif)](https://www.openhub.net/p/parking-api)

# parking-api

API REST para auto-gerenciamento de estacionamento corporativo, onde cada espaço é muito bem aproveitado.

O Web App para esta aplicação está em [parking-app](https://github.com/AlexandreSNeto/parking-app/).

### Pré-requisitos

- Um servidor de LDAP
- Adicionar as configurações do LDAP no arquivo [Dockerfile](docker/Dockerfile)
- Docker 1.12+ instalado

### Compilação

	$ export DOCKER_HOST=unix:///var/run/docker.sock
	$ mvn clean package docker:build -DpushImageTags

### Execução do PostgreSQL

	$ docker run -d --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 \
	   -v $(pwd)/docker/compose/create-db.sh:/docker-entrypoint-initdb.d/create-db.sh postgres

### Execução em modo desenvolvimento

    $ docker run -it --rm=true --name parking-api-ui --link postgres:db -p 8080:8080 gustajz/parking-api \
       --ldap.url=ldap://myad.mydomain.local --ldap.domain=mydomain.local --ldap.login_form=true \
       --spring.profiles.active=development,postgresql \
       --spring.datasource.url=jdbc:postgresql://db:5432/parking
	
### Execução em produção com Oracle

    $ docker run -d --name parking-api -p 8080:8080 \
        -e DB_URL=jdbc:oracle:thin:@db.mydomain.local:1521:XE -e DB_PASS=mypassword \
        -e LDAP_URL=ldap://myad.mydomain.local -e LDAP_DOMAIN=mydomain.local gustajz/parking-api
        
### Execução em produção com PostgreSQL

    $ docker run -d --name parking-api -p 8080:8080 \
        -e DB_URL=jdbc:postgresql://db.mydomain.local:5432/parking -e DB_PASS=mypassword \
        -e LDAP_URL=ldap://myad.mydomain.local -e LDAP_DOMAIN=mydomain.local gustajz/parking-api \
        --spring.profiles.active=postgresql
        
### Documentação

Com aplicação em execução no modo desenvolvimento acesse o endereço do [Swagger UI](http://localhost:8080/api/swagger-ui.html).        
