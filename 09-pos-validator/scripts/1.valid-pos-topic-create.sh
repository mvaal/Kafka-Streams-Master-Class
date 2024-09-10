#!/bin/bash

KAFKA_HOME="${KAFKA_HOME:-$(dirname $0)/../../00-kafka23}"

"$KAFKA_HOME/bin/kafka-topics.sh" --create --zookeeper localhost:2181 --replication-factor 3 --partitions 3 --topic valid-pos