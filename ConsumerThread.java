package practise.threads.ProducerConsumer;

import java.util.Queue;

public class ConsumerThread extends Thread{
    private Boolean isActive = true;
    private Queue<Integer> storage;

    ConsumerThread(Queue<Integer> storage) {
        this.storage = storage;
    }

    public void Disable() {
        isActive = false;
    }

    @Override
    public void run() {
        System.out.println("Consumer Started!");

        while (isActive) {
            synchronized (storage) {
                while (storage.size() < 1) {
                    System.out.println("Storage is empty!");
                    try {
                        storage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                storage.poll();
                System.out.println(" - Object peeked [storage size :" + storage.size() + "]");
                storage.notify();
            }

            try {
                Thread.currentThread().sleep(1000 + (int)Math.random()*10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Consuming Finished!");
    }
}
