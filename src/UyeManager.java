import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class UyeManager extends Veritabani {
    static Scanner scan = new Scanner(System.in);

    public static void uyeMenu() throws InterruptedException {
        String tercih = "";

        do { //TODO Kullanıcı Çıkış Yapmadığı Sürece, Menüyü Görmeye Devam Etsin...

            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                            "================= UYE MENU =================\n" +
                            "\n" +
                            "\t   1- Uye Listesi Yazdir\t\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Bilgilerini Girerek Uye Ekleme\n" +
                            "\t   5- Kimlik No Ile Kayit Silme \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS\n");

            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...

            tercih = scan.nextLine().toUpperCase();

            switch (tercih) {
                case "1":
                    uyeListesiYazdir();
                    break;
                case "2":
                    soyisimdenUyeBulma();
                    break;
                case "3":
                    sehreGoreUyeBulma();
                    break;
                case "4":
                    uyeEkleme();
                    break;
                case "5":
                    tcNoIleUyeSil();
                    break;
                case "A":
                    Helper.anaMenu();
                    break;
                case "Q":
                    Helper.projeDurdur();
                    break;
                default:
                    System.out.println("Lütfen geçerli bir tercih yapınız.");
                    break;
            }

            //   // Uye Listesi Yazdir
            //   uyeListesiYazdir();
            //   // Soyisimden Uye Bulma
            //   soyisimdenUyeBulma();
            //   // Sehre Gore Uye Bulma
            //   sehreGoreUyeBulma();
            //   // Bilgilerini Girerek Uye Ekleme
            //   uyeEkleme();
            //   // Kimlik No Ile Kayit Silme
            //   tcNoIleUyeSil();
            //   Helper.anaMenu();
            //   System.out.println("Lutfen gecerli tercih yapiniz: ");


        } while (!tercih.equals("Q"));
        Helper.projeDurdur();
    }

    public static void tcNoIleUyeSil() throws InterruptedException {

        //TODO Kullanıcıdan alacağınız kimlik no ile ilgili üyeyi kayıtlardan siliniz...
        System.out.println("Silinecek uyeye ait kimlik no giriniz: ");
        String tcNo = scan.nextLine();


        try {
            String silinecekUye = uyelerMap.get(tcNo);

            if (silinecekUye != null) {
                uyelerMap.remove(tcNo);

                System.out.print("silinecekUye" + " Siliniyor...");
                for (int i = 0; i < 20; i++) {
                    Thread.sleep(100);
                    System.out.print(">");
                }
                System.out.println("\n" + tcNo + " Tc numaralı üye silindi.");
            } else
                System.out.println("İstenilen Tc numarasına sahip üye bulunamadı.");

        } catch (Exception e) {
            System.out.println("Geçersiz Tc numarası girdiniz.");
        }
        //try {
        //    boolean sonuc = sonucValue.equals(silinecekValue);
        //} catch (Exception e) {
        //    System.out.println("Istediginiz Tc numarasi ile uye bulunamadi.");

    }

    public static void uyeEkleme() {
        System.out.println("Tc No: ");
        String tcNosu = scan.nextLine();
        System.out.println("Isim: ");
        String isim = scan.nextLine();
        System.out.println("Soyisim: ");
        String soyisim = scan.nextLine();
        System.out.println("Sehir: ");
        String sehir = scan.nextLine();
        System.out.println("Dogum Yili: ");
        String dogumYili = scan.nextLine();

        String uyeBilgileri = isim + " , " + soyisim + " , " + sehir + " , " + dogumYili;
        uyelerMap.put(tcNosu, uyeBilgileri);

        System.out.println("Yeni üye eklendi: " + tcNosu + " - " + uyeBilgileri);

    }

    //TODO Kullanıcıdan Tc no , Isim, Soyisim, Sehir, Dogum Yili alınız...
    //TODO Aldığınız değeri UyelerMap mapine uygun şekilde ekleyiniz...


    public static void sehreGoreUyeBulma() throws InterruptedException {

        //TODO Kullanıcıdan aldığınız girdiyle, UyelerMap'inde şehir araması yapın;
        //TODO Girilen şehire sahip tüm üyeleri map veya liste olarak döndürünüz...
        System.out.println("Aradiginiz Uyenin Sehrini Giriniz:");
        String arananSehir = scan.nextLine();
        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.println(
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                        "=============== SEHIR ILE UYE ARAMA ===============\n" +
                        "TcNo : Isim , Soyisim , Sehir, D.Yili");
        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir... Zorunlu değil...
        boolean uyelerBulundu = false;

        for (Map.Entry<String, String> uye : uyelerEntrySet) {
            String tcNo = uye.getKey();
            String uyeBilgileri = uye.getValue();
            String[] uyeBilgileriArray = uyeBilgileri.split(",");

            String sehir = uyeBilgileriArray[2].toLowerCase();

            if ( sehir.contains(arananSehir)) {
                System.out.println(tcNo + " : " + uyeBilgileri);
                uyelerBulundu = true;
            }

        }

        if (!uyelerBulundu) {
            System.out.println("Aradığınız şehre ait üye bulunamadı.");
        }
    }

    public static void soyisimdenUyeBulma() throws InterruptedException {
        //TODO Kullanıcıdan aldığınız girdiyle, UyelerMap'inde soyisim araması yapın;
        //TODO Girilen soyismine sahip tüm üyeleri map veya liste olarak döndürünüz...
        System.out.println("Aradiginiz uyenin soyisminin tamamini ya da birkismini giriniz: ");
        String arananSoyisim = scan.nextLine().toLowerCase();
        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.println(
                "\n========== TECHNO STUDY BOOTCAMP ===========\n" +
                        "=========== SOYISIM ILE UYE ARAMA ==========\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");
        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir. Şart değil, isteğe bağlı.

        //TODO Syisminin bir kısmı girilse bile bulunan üyeler listelensin...
        boolean uyelerBulundu = false;

        for (Map.Entry<String, String> uye : uyelerEntrySet) {
            String tcNo = uye.getKey();
            String uyeBilgileri = uye.getValue();
            String[] uyeBilgileriArray = uyeBilgileri.split(",");

            String soyisim = uyeBilgileriArray[1].toLowerCase();
            if (soyisim.contains(arananSoyisim)) {
                System.out.println(tcNo + " : " + uyeBilgileri);
                uyelerBulundu = true;
            }
        }

        if (!uyelerBulundu) {
            System.out.println("Aradığınız soyisime ait üye bulunamadı.");
        }
    }

    public static void uyeListesiYazdir() throws InterruptedException {
        //İPUCU METODU: Bu metodu değiştirmenize gerek yok...

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.print("Uye Listesi yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println(
                "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                        "=============== UYE LISTESI ================\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir...
        for (Map.Entry<String, String> each : uyelerEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            System.out.println(eachKey + " : " + eachValue + " | ");
        }
    }
}
