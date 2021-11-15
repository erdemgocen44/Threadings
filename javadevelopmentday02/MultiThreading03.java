public class MultiThreading03 {
    public static void main(String[] args) {
        Brackets obj1 = new Brackets();
        Brackets obj2 = new Brackets();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 7; i++) {
                    obj1.generateBrachet();
                }
            }
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 7; i++) {
                    obj2.generateBrachet();
                }
            }
        });
        thread2.start();
    }
}

class Brackets {
    public void generateBrachet() {
        for (int i = 0; i <= 10; i++) {
            if (i <= 5)
                System.out.print("[");
            else
                System.out.print("]");
        }
        System.out.println();
    }
}