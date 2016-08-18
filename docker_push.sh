#!/usr/bin/env sh
docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD" 
export REPO=parking-api 
export VERSION=`xml2 < pom.xml  | grep /project/version= | sed 's/.*=//'`
docker tag $REPO gustajz/$REPO 
docker tag $REPO gustajz/$VERSION 
docker push gustajz/$REPO:latest 
docker push gustajz/$REPO:$VERSION
