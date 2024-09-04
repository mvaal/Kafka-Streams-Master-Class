#!/bin/bash

KAFKA_HOME="${KAFKA_HOME:-$(dirname $0)/../../00-kafka23}"

"$KAFKA_HOME/bin/kafka-topics.sh" --create --zookeeper localhost:2181 --topic invoice --partitions 5 --replication-factor 3 --config segment.bytes=1000000