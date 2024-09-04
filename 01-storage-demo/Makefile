.PHONY: all start-zookeeper start-kafka create-topic describe-topic run-storage-demo

all: start-zookeeper start-kafka-0 start-kafka-1 start-kafka-2 create-topic describe-topic run-storage-demo

start-zookeeper:
	cd scripts && ./zookeeper-start.sh

start-kafka-0:
	cd scripts && ./0-kafka-server-start.sh

start-kafka-1:
	cd scripts && ./1-kafka-server-start.sh

start-kafka-2:
	cd scripts && ./2-kafka-server-start.sh

create-topic:
	cd scripts && ./topic-create.sh

describe-topic:
	cd scripts && ./describe-topic.sh

run-storage-demo:
	mvn exec:java -Dexec.mainClass=guru.learningjournal.kafka.examples.StorageDemo