#!/bin/bash

KAFKA_HOME="${KAFKA_HOME:-$(dirname $0)/../../00-kafka23}"

"$KAFKA_HOME/bin/kafka-console-consumer.sh" --bootstrap-server localhost:9092 --topic hello-producer-topic --from-beginning