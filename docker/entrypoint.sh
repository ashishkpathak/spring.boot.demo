#!/bin/sh
[ ${MICRO_SERVICE_ACTIVE_PROFILE} ] || { echo "need to set MICRO_SERVICE_ACTIVE_PROFILE"; exit 1; }

set -x

# Generate random password
STORE_PASSWORD=$(date +%s | sha256sum | base64 | head -c 16)
# Build a keystore with a random key for the https cert.
mkdir -p "${MICRO_SERVICE_DIR}/config"

# Optional facade level initialization script
if [[ -f "${MICRO_SERVICE_DIR}/config/startup.sh" ]]; then
    . "${MICRO_SERVICE_DIR}/config/startup.sh"
fi

# construct the list of parameters to pass to Java to start the microservice
if [[ -n "$JASYPT_ENCRYPTOR_SECRET" ]]; then
    JASYPT_JAVA_OPTION="-Djasypt.encryptor.password=$JASYPT_ENCRYPTOR_SECRET"
fi


# construct the list of parameters to pass to Java to start the microservice
read -d '' DAEMONISE << EOF
  -server
  ${JVM_MEMORY_SIZE:-"-Xms128m -Xmx256m"}
  -XX:+UseConcMarkSweepGC
  -XX:+CMSParallelRemarkEnabled
  -XX:+UseCMSInitiatingOccupancyOnly
  -XX:+HeapDumpOnOutOfMemoryError
  -XX:+PrintGCDateStamps
  -XX:+PrintGCDetails
  -XX:HeapDumpPath=${JVMLOG_DIR}/${MICRO_SERVICE}.hprof
  -Xloggc:${JVMLOG_DIR}/gc.log
  -Djava.security.egd=file:/dev/./urandom
  -verbose:gc
  -Dhttp.proxyPort=${PROXY_PORT}
  -Dhttp.proxyHost=${PROXY_HOST}
  -Dhttps.proxyPort=${PROXY_PORT}
  -Dhttps.proxyHost=${PROXY_HOST}
  -Dhttp.nonProxyHosts=${NO_PROXY//,/|}
  ${JASYPT_JAVA_OPTION}
  -classpath ${CLASSPATH}
  org.springframework.boot.loader.JarLauncher
  --spring.profiles.active=${MICRO_SERVICE_ACTIVE_PROFILE}
EOF

mkdir -p ${JVMLOG_DIR} ${LOG_FOLDER}

nohup java ${DAEMONISE} 2>&1

# Optional facade level cleanup script
if [[ -x "${MICRO_SERVICE_DIR}/config/stop.sh" ]]; then
    "${MICRO_SERVICE_DIR}/config/stop.sh"
fi
