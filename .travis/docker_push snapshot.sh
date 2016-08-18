#!/usr/bin/env sh
docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD" 

export REPO=parking-api

echo docker tag $REPO gustajz/$REPO:snapshot
docker tag $REPO gustajz/$REPO:snapshot

echo docker push gustajz/$REPO:snapshot
docker push gustajz/$REPO:snapshot
