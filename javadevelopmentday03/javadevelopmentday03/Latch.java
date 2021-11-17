package javadevelopmentday03;

import java.util.concurrent.CountDownLatch;

public class Latch {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(6);
        ThreadUret thread1 = new ThreadUret(latch);
        ThreadUret thread2 = new ThreadUret(latch);
        ThreadUret thread3 = new ThreadUret(latch);
        ThreadUret thread4 = new ThreadUret(latch);
        ThreadUret thread5 = new ThreadUret(latch);
        ThreadUret thread6 = new ThreadUret(latch);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

        try {
            latch.await();
            System.out.println("Latch in mudahale etmesini bekliyorum");
        } catch (InterruptedException e) {
            System.out.println("Thread oluşumu tamamlandı, şimdi benim sıram");
        }

    }

}

class ThreadUret extends Thread {
    private CountDownLatch latch;

    @Override
    public void run() {
        System.out.println("Latch çalışmaya başladı..." + Thread.currentThread().getName());

        System.out.println("Latch çalışmayı durdurdu..." + Thread.currentThread().getName());

        System.out.println("*********************************");
    }

    public ThreadUret(CountDownLatch latch) {
        this.latch = latch;
    }
}