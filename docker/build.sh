#!/bin/sh

# Author: AP
# Build script for creating a docker image.
# You probably don't need to change anything here.


# Load the environment variables.
source ./env.sh

if [ -d $JAR_DIRECTORY ]
then
	echo "$JAR_DIRECTORY EXISTS. REMOVING IT NOW"
	rm -rf $JAR_DIRECTORY
fi

if [ -d $RESOURCE_DIRECTORY ]
then
	echo "$RESOURCE_DIRECTORY EXISTS. REMOVING IT NOW"
	rm -rf $RESOURCE_DIRECTORY
fi

# copy all artifacts

mkdir "$JAR_DIRECTORY"
mkdir "$RESOURCE_DIRECTORY"

echo "COPYING ALL RESOURCES TO ${RESOURCE_DIRECTORY}"
cp -R ../src/main/resources/* $RESOURCE_DIRECTORY

echo "COPYING ALL JARS TO ${JAR_DIRECTORY}"
cp -R ../target/*-SNAPSHOT.jar $JAR_DIRECTORY/$MICRO_SERVICE.jar

TS=$(date +%d%m%Y-%H%M%S)

docker build --no-cache \
   --build-arg MICRO_SERVICE="${MICRO_SERVICE}" \
   --build-arg JAR_DIRECTORY="${JAR_DIRECTORY}" \
   --build-arg JASYPT_ENCRYPTOR_SECRET="${JASYPT_ENCRYPTOR_SECRET}" \
   --build-arg RESOURCE_DIRECTORY="${RESOURCE_DIRECTORY}" \
   --build-arg MS_JAR_LOCATION="${MS_JAR_LOCATION}" \
   --build-arg PORT_EXPOSE="${MICRO_SERVICE_PORT}" \
   -t "${MICRO_SERVICE}:${TS}" \
   -f Dockerfile .

echo 'FINISHED CREATING DOCKER IMAGE.'