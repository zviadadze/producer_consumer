package practise.threads.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main (String[] args) throws InterruptedException {
        Queue storage = new LinkedList();

        ProducerThread producer = new ProducerThread(storage);
        ConsumerThread consumer = new ConsumerThread(storage);

        producer.start();
        consumer.start();

        Thread.sleep(10000);

        producer.Disable();
        consumer.Disable();
    }
}
