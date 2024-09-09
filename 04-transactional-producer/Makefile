.PHONY: all start-zookeeper start-kafka-0 start-kafka-1 start-kafka-2 create-topic-1 create-topic-2 consumer-start

start-zookeeper:
	cd scripts && ./zookeeper-start.sh

start-kafka-0:
	cd scripts && ./0-kafka-server-start.sh

start-kafka-1:
	cd scripts && ./1-kafka-server-start.sh

start-kafka-2:
	cd scripts && ./2-kafka-server-start.sh

create-topic-1:
	cd scripts && ./topic-1-create.sh

create-topic-2:
	cd scripts && ./topic-2-create.sh

consumer-start:
	cd scripts && ./consumer-start.sh