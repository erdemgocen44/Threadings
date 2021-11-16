package javadevelopmentday03;

public class WaitNotify {
    static public double bakiye = 0;

    public static void main(String[] args) {
        WaitNotify obj = new WaitNotify();
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                obj.paraCekme(800);
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                obj.paraYatir(3000);
            }
        });
        thread2.start();

    }

    public void paraCekme(double miktar) {
        synchronized (this) {
            if (bakiye <= 0 || bakiye < miktar)
                try {
                    System.out.println("Hesaba para yatırılması gerekiyor...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        bakiye = bakiye - miktar;
        System.out.println("Para çekme başarılı\nGüncel bakiyeniz : " + bakiye);
    }

    public void paraYatir(double miktar) {
        bakiye = bakiye + miktar;

        System.out.println("Bakiyemiz güncellendi\nGüncel bakiyeniz : " + bakiye);
        synchronized (this) {
            notify();
        }
    }
}
