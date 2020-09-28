#!/bin/sh

#########################################################
# Repository of all environment specific variables.
# Update the values are per your microservice requirement.
#########################################################


# Set the microserivce name here.
export MICRO_SERVICE=spring-boot-demo
echo $MICRO_SERVICE

# Set the port for microservice. use the one defined in application.properties.
export MICRO_SERVICE_PORT=8080
echo $MICRO_SERVICE_PORT

# Set the JASUPT password if any.
export JASYPT_ENCRYPTOR_SECRET=YourSecretKeyGoesHere
echo $JASYPT_ENCRYPTOR_SECRET

# Set the spring profile.
export MICRO_SERVICE_ACTIVE_PROFILE=docker
echo $MICRO_SERVICE_ACTIVE_PROFILE

export MS_ROOT_LOCATION=/opt/api/$MICRO_SERVICE
echo $MS_ROOT_LOCATION

export MS_JAR_LOCATION=$MS_ROOT_LOCATION/lib/
echo $MS_JAR_LOCATION

export MS_CONFIG_LOCATION=$MS_ROOT_LOCATION/config/
echo $MS_CONFIG_LOCATION

export MS_BIN_LOCATION=$MS_ROOT_LOCATION/bin/
echo $MS_BIN_LOCATION

export JAR_DIRECTORY=target
export RESOURCE_DIRECTORY=resource

export VOLUME_DIRECTORY="/var/log/${MICRO_SERVICE}"

export HOST_DIRECTORY="/private/var/log/api/${MICRO_SERVICE}"
