#!/bin/sh
echo 'Stopping containers'
docker ps
docker ps | tail -1 | awk '{print $1}' | xargs docker container stop
echo 'Done.'
