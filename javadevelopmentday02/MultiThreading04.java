public class MultiThreading04 {
    public static void main(String[] args) {

        Brackets2 obj3 = new Brackets2();
        Brackets2 obj4 = new Brackets2();
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i <= 7; i++) {
                    try {
                        obj3.generateBrachet();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 7; i++) {
                    try {
                        obj4.generateBrachet();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        thread2.start();
    }
}

class Brackets2 {
    public void generateBrachet() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i <= 10; i++) {
                if (i <= 5)
                    System.out.print("[");
                else
                    System.out.print("]");
            }
        }
        System.out.println();

        for (int i = 0; i <= 10; i++) {
            Thread.sleep(25);
        }
    }
}