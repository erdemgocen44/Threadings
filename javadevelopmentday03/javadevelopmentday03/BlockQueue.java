package javadevelopmentday03;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockQueue {
    static public int counter = 1;

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(10);

        Producer producer = new Producer(queue);
        Thread producThread = new Thread(producer);
        producThread.start();

        Consumer consumer = new Consumer(queue);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}

class Producer implements Runnable {
    private ArrayBlockingQueue<Integer> queue;

    // COSTRUCTOR
    public Producer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                queue.put(BlockQueue.counter);
                System.out.println("Counter degeri query arrayi : " + BlockQueue.counter);
                BlockQueue.counter++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private ArrayBlockingQueue<Integer> queue;
    //

    public Consumer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            queue.take();
            BlockQueue.counter--;
            System.out.println("Counter değerimiz Queue arrayinden azaltıldı : " + BlockQueue.counter);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}