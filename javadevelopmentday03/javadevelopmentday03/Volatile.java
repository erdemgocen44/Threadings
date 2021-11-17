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

    // ============================================= VOLATILE
    // ===============================================================
    // Volatile keyword'u, Bir degiskenin farklı threadler tarafından kullanılırken
    // degerinin degismesini saglamak icin
    // kullanılmaktadir. Aynı zamanda bir class'ı thread-safe yapmak icin de
    // kullanılır. Yani diger bir degisle, bir class
    // yada nesneyi farklı thread'lerin es zamanlı olarak problemiz kullanımına
    // olanak saglar.

    // Volatile keywordu sadece degiskenler ile (primitif veya non-primitif)
    // kullanılabilir. Nesne ve Class'lara konulmaz

    // Volatile keywordu kullanılan bir degiskenin degeri cache bellege saklanmaz.
    // Her defasında ilgili class'ın bellegi
    // (MAIN MEMORY) den okunur. Dolayısıyla farklı thread'ler degiskeni guncellese
    // de her defasında en guncel deger okunmus olur.
    // Bu özellikleri sayesinde Syncronization yönteminin daha iyi bir alternatifi
    // olarak düşünülebilir.

    /*
     * ============================ WAIT, NOTIFY ==========================
     * Object.wait() metodu bir thread'i suresiz olarak askıya alir. Diğer bir ifade
     * ile bu thread'in kilitlemiş (locked) olduğu bir kaynağı salıvermesini ve
     * askıya geçmesini sağlar. Thread bu durumdan bir başka thread Onu
     * bilgilendirirse (notify) çıkabilir.
     * 
     * Object.notify() metodu ise aynı nesne üzerinde askıya alınan bir thread'in
     * uyanmasini saglar. Object.notifyAll() metodu bir nesne üzerinde askıya alınan
     * tum thread'lerin uyandirilmasini saglar.
     * 
     * Bu metotlar, thread'ler arasi iletişim metodu olarak kullanılır.
     * 
     */

    /*
     * =============================================== THREAD POOL
     * ==================================================== Thread Pool,
     * ExecutorService interface'i yardımıyla oluşturulan bir Thread havuzudur.
     * Hizmet görecek olan thread'ler baştan bir kere oluşturulur sonrasında ise
     * kuyrukta (TaskQueue) sıranın ona gelmesine bekler. Havuzun kapasitesi
     * ölçüsünde thread'ler eş zamanlı hizmet görürler.
     * 
     * Bu yöntem baştan thread'lerin oluşturulmasını, belirli sayıda thread'in
     * eş-zamanlı çalıştırılmasını ve gerektiğinde tekrar kullanılmasını
     * sağlamaktadir. Böylece, sürekli Thread oluşturmak ve kaldırmak gibi ciddi iş
     * yükü getiren işlemlerin runtime sırasında yapılmasına gerek kalmaz. Bu da
     * önemli ölçüde performans artışına yol açar.
     * 
     * Özelikle web-sunucu ortamlari için elverişli bir metot sunar.
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