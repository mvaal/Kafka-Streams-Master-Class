#!/bin/bash

KAFKA_HOME="${KAFKA_HOME:-$(dirname $0)/../../00-kafka23}"

"$KAFKA_HOME/bin/kafka-topics.sh" --create --zookeeper localhost:2181 --topic hello-producer-1 --partitions 5 --replication-factor 3 --config min.insync.replicas=2