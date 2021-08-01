#!/bin/sh

# Run script for running a container of a docker image.
# It loads up the latest image
# You probably don't need to change anything here.

# Load the environment variables.
source ./env.sh

# Find the latest image id associated with this MICROSERVICE
export IMAGEID=$(docker images | grep "${MICRO_SERVICE}"| head -1  | awk '{print $3}')
echo "Running container ${IMAGEID}"

export CONTAINER_ID=$(docker run -d -p ${MICRO_SERVICE_PORT}:${MICRO_SERVICE_PORT} \
        --env MICRO_SERVICE_ACTIVE_PROFILE="${MICRO_SERVICE_ACTIVE_PROFILE}" \
         --volume ${HOST_DIRECTORY}:${VOLUME_DIRECTORY}:rw \
        $IMAGEID)

# tail the logs, so you can use ^C to stop it.
echo "#######################################################################################################"
echo "#######################################################################################################"
echo "################### LOGS  #############################################################################"
echo "################### USE CTRL C TO TERMINATE ###########################################################"
echo "#######################################################################################################"

docker logs --details -f $CONTAINER_ID