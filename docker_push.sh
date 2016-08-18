#!/usr/bin/env sh
docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD" 

export REPO=parking-api
export VERSION=`xml2 < pom.xml  | grep /project/version= | sed 's/.*=//'`

echo docker tag $REPO gustajz/$REPO
docker tag $REPO gustajz/$REPO

echo docker tag $REPO gustajz/$REPO:$VERSION
docker tag $REPO gustajz/$REPO:$VERSION

echo docker push gustajz/$REPO:latest
docker push gustajz/$REPO:latest

echo docker push gustajz/$REPO:$VERSION
docker push gustajz/$REPO:$VERSION
