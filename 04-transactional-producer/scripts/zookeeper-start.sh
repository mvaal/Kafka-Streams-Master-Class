#!/bin/bash

KAFKA_HOME="${KAFKA_HOME:-$(dirname $0)/../../00-kafka23}"

"$KAFKA_HOME/bin/zookeeper-server-start.sh" "$KAFKA_HOME/config/zookeeper.properties"