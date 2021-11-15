public class MultiThreading02 {
    public static int counter = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Counter.count();
                System.out.println("Tread1 tamamlandı...");
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Counter.count();
                System.out.println("Tread2 tamamlandı...");
            }
        });
        thread2.start();
    }
}

class Counter {
    synchronized public static void count() {
        for (int i = 0; i <= 1000; i++) {
            MultiThreading02.counter++;
        }
        System.out.println("Counter : " + MultiThreading02.counter);
    }
}
