
FROM ubuntu:18.04

#
# Important Oracle JDK License Update
# The Oracle JDK License has changed for releases starting April 16, 2019.
# The new Oracle Technology Network License Agreement for Oracle Java SE is substantially different from prior Oracle JDK licenses.
# The new license permits certain uses, such as personal use and development use, at no cost -- but other uses authorized under prior Oracle JDK licenses may no longer be available.
# Please review the terms carefully before downloading and using this product. An FAQ is available here.
# Commercial license and support is available with a low cost Java SE Subscription.
# Oracle also provides the latest OpenJDK release under the open source GPL License at jdk.java.net.
#

RUN apt-get update
RUN apt-get install wget ca-certificates gnupg2 -y
RUN wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | apt-key add -
RUN sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ `lsb_release -cs`-pgdg main" >> /etc/apt/sources.list.d/pgdg.list'

RUN apt-get update && apt-get install openjdk-8-jdk && apt-get install postgresql postgresql-contrib
RUN /etc/init.d/postgresql start && postgres psql -c "ALTER USER postgres PASSWORD 'postgres';" && postgres psql -c "CREATE DATABASE testdb;"

MAINTAINER ashishkpathak

# Define variable that can be passed at build-time
# Mirco Service name to built in Docker image.
ARG MICRO_SERVICE

# Micro service service port
ARG PORT_EXPOSE


# Encrypted using password.
ARG JASYPT_ENCRYPTOR_SECRET

# AWS MS_JAR_LOCATION
ARG MS_JAR_LOCATION

ARG JAR_DIRECTORY

ARG RESOURCE_DIRECTORY

# Set the specified network ports where container listens on at runtime.
EXPOSE ${PORT_EXPOSE}

# Set Environment for Micro services
# ENV values are always persisted in the built image
ENV MICRO_SERVICE="${MICRO_SERVICE}" \
    MICRO_SERVICE_DIR="/opt/${MICRO_SERVICE}" \
    VOLUME_DIRECTORY="/var/log/${MICRO_SERVICE}"

ENV CLASSPATH="${MICRO_SERVICE_DIR}/lib/${MICRO_SERVICE}.jar:${MICRO_SERVICE_DIR}/config:" \
    JVMLOG_DIR="${VOLUME_DIRECTORY}/jvm/${MICRO_SERVICE}" \
    LOG_FOLDER="${VOLUME_DIRECTORY}/${MICRO_SERVICE}"

ENV BOOT_LOG=${LOG_FOLDER}/boot.log \
    MSVC_LOG=${LOG_FOLDER}/${MICRO_SERVICE}_ms.log

ENV LANG=en_AU.UTF-8

ENV JASYPT_ENCRYPTOR_SECRET="${JASYPT_ENCRYPTOR_SECRET}"

# Set Volume that is directory or file in the Docker host’s filesystem that is mounted directly into a container
VOLUME ["${VOLUME_DIRECTORY}"]

# Create working directory
RUN mkdir -p ${MICRO_SERVICE_DIR}/bin ${MICRO_SERVICE_DIR}/lib ${MICRO_SERVICE_DIR}/config ${VOLUME_DIRECTORY} ${LOG_FOLDER}

# Copy running script of Micro service
ENV ENTRYPOINT_SHELL="/opt/service/micro-service.sh"

COPY entrypoint.sh "${ENTRYPOINT_SHELL}"

RUN chmod +x "${ENTRYPOINT_SHELL}"

# Copy articatory of Micro service
COPY ${JAR_DIRECTORY}/${MICRO_SERVICE}.jar "${MICRO_SERVICE_DIR}/lib/${MICRO_SERVICE}.jar"

# Copy config files of Micro service
COPY ${RESOURCE_DIRECTORY}/* "${MICRO_SERVICE_DIR}/config/"

WORKDIR ${MICRO_SERVICE_DIR}

ENTRYPOINT ["/opt/service/micro-service.sh"]


