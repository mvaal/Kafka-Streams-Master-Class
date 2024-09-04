#!/bin/bash

KAFKA_HOME="${KAFKA_HOME:-$(dirname $0)/../../00-kafka23}"

"$KAFKA_HOME/bin/zookeeper-shell.sh" "localhost:2181"