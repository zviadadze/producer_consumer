package practise.threads.ProducerConsumer;

import java.util.Queue;

public class ProducerThread extends Thread {

    private Boolean isActive = true;
    private Queue<Integer> storage;

    ProducerThread(Queue<Integer> storage) {
        this.storage = storage;
    }

    public void Disable() {
        isActive = false;
    }

    @Override
    public void run() {
        System.out.println("Producing Started!");

        while(isActive) {
            synchronized (storage) {
                while (storage.size() > 9) {
                    System.out.println("Storage is full!");
                    try {
                        storage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                storage.add((int) Math.random() * 10);
                System.out.println(" + Object Added [storage size :" + storage.size() + "]");
                storage.notify();
            }

            try {
                Thread.currentThread().sleep(1000 + (int)Math.random()*10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Producing Finished!");
    }

}
