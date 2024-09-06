package guru.learningjournal.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dispatcher implements Runnable {
    private static final Logger logger = LogManager.getLogger();
    private final String fileLocation;
    private final String topicName;
    private final KafkaProducer<Integer, String> producer;

    Dispatcher(KafkaProducer<Integer, String> producer, String topicName, String fileLocation) {
        this.producer = producer;
        this.topicName = topicName;
        this.fileLocation = fileLocation;
    }

    @Override
    public void run() {
        logger.info("Start Processing {}", fileLocation);
        int counter = 0;
        try (InputStream inputStream = Files.newInputStream(Paths.get(ClassLoader.getSystemResource(fileLocation).toURI()))) {
            try (Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    producer.send(new ProducerRecord<>(topicName, null, line));
                    counter++;
                }
                logger.info("Finished Sending {} messages from {}", counter, fileLocation);
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
