package javadevelopmentday03;

public class Volatile {
    // !BİLDİĞİN EL FRENİ
    /*
     * Yazdığımız değişkenin cihazn main-memory yani ram'inde saklnacağını Volatile
     * kullanımı ile halledyrz. Bunun bize faydası ne olacak diye düşünebilirsiniz,
     * ki bencede düşünün.. :)) Java'da değişkenler performansı arttırmk içn işlemci
     * cache belleğnde değişikliğe uğrar. Bu da farklı cachelrde farklı işlemlr
     * yapılablr demk. mesela; 4 çekirdekli bir bilgisayarınz olduğnu varsayn ve
     * oluşturduğunz bir değişken farklı thread'ler tarafındn erişime açık. Herhangi
     * bir thread bu değişken üzernde değişiklik yaptığnda diğer threadin bu
     * değişiklğn farkında olması için "volatile" keywordu kullanark yapılck tüm
     * değişikliklern ram üzernde yapılmasnı garanti altına alyrz. mesela; Thread 1
     * --> CPU 1 Cache -> Main Memory (RAM) Thread 2 --> CPU 2 Cache -> Main Memory
     * (RAM) -xyz adında bir int değişknmz olsn ve ilk değeri 5 diylm -Thread 1
     * xyz'yi ram'den okusn ve değerni 7 arttırsn. xyz'nin yeni değri 12 ancak ram'e
     * yazılmdı. Çünkü bu arttırma işlemi 1. cache bellekte gerçekleşti. -Thread 2
     * xyz'yi ram'den okuduğnda ise xyz'yi 12 değl 5 olark okur. Çünkü Thread1
     * xyz'nin yeni değerni rame hnz yzmdı. Thread2 de xyz'nin değerni 1 azaltsn ve
     * değerni 4 yapsn. Şu an elimizde 3 farklı xyz değeri var.. İlki ram'de bulunan
     * xyz=5, diğeri Thread1'de bulunan xyz=12, ve Thread2'de bulunn xyz=4 . işte
     * tam bu saçma sapan kaldığımız nokta da "volatile" kullanıyoruz gençlr :)) ...
     */
    volatile public static int flag = 0;

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (flag == 0)
                        System.out.println("thread1 çalışır durumdadır");
                    else
                        break;
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = 1;
                System.out.println("Flag variable inin degeri guncellendi");
            }
        });
        thread2.start();

    }
}