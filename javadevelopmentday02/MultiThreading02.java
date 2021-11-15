public class MultiThreading02 {
    public static int counter = 0;

    // eğer elimizde bir obje varsa ve bu objeyi birden fazla yerde kullanıyorsak
    // syncronize kullanmıyoruz
    // ama elimizde 1 obje varsa bu objeyi birden fazla thread de kullanıp
    // syncronize etmemiz gerekiyor
    // Java'da senkronize bloklar, synchronized keyword ile işaretlenir. Java'da
    // senkronize edilmiş blok mekanizmasi aynı obje üzerinde tek zaman diliminde
    // tek thread calismasini saglar.. Blokda bir thread calismaya baslayinca, diger
    // tum thread’ler ilk thread’in islemi bitene kadar bekletilir.(
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
