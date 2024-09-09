package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.serde.JsonSerializer;
import guru.learningjournal.kafka.examples.types.PosInvoice;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PosSimulator {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please provide command line arguments: topicName noOfProducers produceSpeed");
            System.exit(-1);
        }
        String topicName = args[0];
        int noOfProducers = Integer.parseInt(args[1]);
        int produceSpeed = Integer.parseInt(args[2]);

        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        try (KafkaProducer<String, PosInvoice> kafkaProducer = new KafkaProducer<>(properties);
             ExecutorService executor = Executors.newFixedThreadPool(noOfProducers)) {
            List<CompletableFuture<Void>> futures = IntStream.range(0, noOfProducers)
                    .mapToObj(i -> new RunnableProducer(i, kafkaProducer, topicName, produceSpeed))
                    .map(runnableProducer -> CompletableFuture.runAsync(runnableProducer, executor))
                    .toList();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                futures.forEach(future -> future.cancel(true)); // Cancel all producer futures
                executor.shutdown();
                logger.info("Closing Executor Service");
                try {
                    executor.awaitTermination(produceSpeed * 2L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
            futures.forEach(CompletableFuture::join);
        }
    }
}
