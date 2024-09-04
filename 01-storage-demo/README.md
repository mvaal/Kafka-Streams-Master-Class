# Storage Demo

## Setup

1. **Start Zookeeper**

    ```shell
    make start-zookeeper
    ```

2. **Start Kafka Brokers**

    ```shell
    make start-kafka-0
    ```

    ```shell
    make start-kafka-1
    ```

    ```shell
    make start-kafka-2
    ```

3. **Create Kafka Topic**

    ```shell
    make create-topic
    ```

4. **Describe Kafka Topic**

    ```shell
    make describe-topic
    ```

5. **Run Storage Demo**

    ```shell
    make run-storage-demo
    ```