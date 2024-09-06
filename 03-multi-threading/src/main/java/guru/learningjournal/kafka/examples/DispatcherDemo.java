package guru.learningjournal.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class DispatcherDemo {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        Properties props = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get(ClassLoader.getSystemResource(AppConfigs.kafkaConfigFileLocation).toURI()))) {
            props.load(inputStream);
            props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try (KafkaProducer<Integer, String> producer = new KafkaProducer<>(props)) {
            logger.info("Starting Dispatcher threads...");
            Arrays.stream(AppConfigs.eventFiles)
                    .parallel()
                    .map(file -> new Dispatcher(producer, AppConfigs.topicName, file))
                    .forEach(Dispatcher::run);
            // This was the modified original code, but it was changed to the above code to use stream parallelization
//            Arrays.stream(AppConfigs.eventFiles)
//                    .map(file -> new Thread(new Dispatcher(producer, AppConfigs.topicName, file)))
//                    .peek(Thread::start)
//                    .forEach(thread -> {
//                        try {
//                            thread.join();
//                        } catch (InterruptedException e) {
//                            logger.error("Main Thread Interrupted");
//                        }
//                    });
        } finally {
            logger.info("Finished Dispatcher Demo");
        }
    }
}
