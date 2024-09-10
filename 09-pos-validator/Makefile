.PHONY: all start-zookeeper start-kafka-0 start-kafka-1 start-kafka-2 create-topic consumer-start valid-pos-topic-create invalid-pos-topic-create valid-pos-consumer invalid-pos-consumer

start-zookeeper:
	cd ../05-pos-simulator/scripts && ./zookeeper-start.sh

start-kafka-0:
	cd ../05-pos-simulator/scripts && ./0-kafka-server-start.sh

start-kafka-1:
	cd ../05-pos-simulator/scripts && ./1-kafka-server-start.sh

start-kafka-2:
	cd ../05-pos-simulator/scripts && ./2-kafka-server-start.sh

create-topic:
	cd ../05-pos-simulator/scripts && ./create-topic.sh

valid-pos-topic-create:
	cd scripts && ./1.valid-pos-topic-create.sh

invalid-pos-topic-create:
	cd scripts && ./2.invalid-pos-topic-create.sh

valid-pos-consumer:
	cd scripts && ./3.valid-pos-consumer.sh

invalid-pos-consumer:
	cd scripts && ./4.invalid-pos-consumer.sh