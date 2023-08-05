import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KitapManager extends Veritabani {

    private static Map<String, String> oduncAlinanKitaplarMap = new HashMap<>();


    static Scanner scan = new Scanner(System.in);

    public static void kitapMenu() throws InterruptedException {
        String tercih = "";
        do {// TODO Kullanıcı Çıkış Yapmadığı sürece menüde kalalım...
            System.out.println(
                    "\n============ TECHNO STUDY BOOTCAMP ============\n" +
                            "================== KITAP MENU =================\n" +
                            "\t   1- Kitap Listesi Yazdir\n" +
                            "\t   2- Yazardan Kitap Bulma\n" +
                            "\t   3- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   4- Bilgilerini Girerek Kitap Ekleme\n" +
                            "\t   5- Kitap Ismi Ile Kayit Silme \t\n" +
                            "\t   6- Kitap Odunc Al \t\n" +
                            "\t   7- Kitap Iade Et \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");

            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...
            tercih = scan.nextLine().toLowerCase();

            //  {
            //      kitapListesiYazdir();
            //      // Yazar Ismiyle Kitap Bulma
            //      yazardanKitapBulma();
            //      // Kitap Turu veya Yayin Tarihi Ile Kitap Bulma
            //      turVeyaYayinTarihiIleKitapBulma();
            //      // Bilgilerini Girerek Kitap Ekleme
            //      kitapEkle();
            //      isimIleKitapSilme();
            //      kitapOduncAl();
            //      kitapIadeEt();
            //      Helper.anaMenu();
            //      Helper.projeDurdur();
            //      System.out.println("Lutfen gecerli bir tercih giriniz");
            //  }
            switch (tercih) {
                case "1":
                    kitapListesiYazdir();
                    break;
                case "2":
                    yazardanKitapBulma();
                    break;
                case "3":
                    turVeyaYayinTarihiIleKitapBulma();
                    break;
                case "4":
                    kitapEkle();
                    break;
                case "5":
                    isimIleKitapSilme();
                    break;
                case "6":
                    kitapOduncAl();
                    break;
                case "7":
                    kitapIadeEt();
                    break;
                case "a":
                    Helper.anaMenu();
                    break;
                case "q":
                    Helper.projeDurdur();
                    break;
                default:
                    System.out.println("Lütfen geçerli bir tercih giriniz.");
                    break;
            }
        } while (!tercih.equals("q"));
        Helper.projeDurdur();

    }

    public static void kitapOduncAl() {
        System.out.println("Odunc almak istediginiz kitabin ismini giriniz: ");
        String kitapIsmi = scan.nextLine();

        if (kitaplarMap.containsKey(kitapIsmi)) {
            String kitapDetaylari = kitaplarMap.get(kitapIsmi);
            // Move the book from kitaplarMap to oduncAlinanKitaplarMap
            oduncAlinanKitaplarMap.put(kitapIsmi, kitapDetaylari);
            kitaplarMap.remove(kitapIsmi);
            System.out.println(kitapIsmi + " odunc alindi.");
        } else {
            System.out.println("Bu isimde bir kitap bulunamadi.");
        }
        //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
        //TODO kitap ödünç alma metodunu tamamlayın...
        //NOT: Veritabanı'nda ödünç almayla alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
        //Ödünç alındığında kitaplarMap 'ten düşüp bu map e eklenecek...
    }


    public static void kitapIadeEt() {
        System.out.println("Iade etmek istediginiz kitabin ismini giriniz: ");

        String kitapIsmi = scan.nextLine();

        if (oduncAlinanKitaplarMap.containsKey(kitapIsmi)) {
            String kitapDetaylari = oduncAlinanKitaplarMap.get(kitapIsmi);
            // Move the book from oduncAlinanKitaplarMap to kitaplarMap
            kitaplarMap.put(kitapIsmi, kitapDetaylari);
            oduncAlinanKitaplarMap.remove(kitapIsmi);
            System.out.println(kitapIsmi + " iade edildi.");
        } else {
            System.out.println("Bu isimde odunc alinan bir kitap bulunamadi.");
        }

        //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
        //TODO kitap iade etme metodunu tamamlayın...
        //NOT: Veritabanı'nda iade etmeyle alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
        //Kitap iade edildiğinde,  kitaplarMap 'e geri eklenmeli...
    }


    private static void isimIleKitapSilme() throws InterruptedException {//İPUCU METODU... Bu metodu değiştirmenize gerek yok.
        System.out.println("Silinecek kitabin ismini giriniz");
        String silinecekKitap = scan.nextLine();

        String silinecekValue = kitaplarMap.get(silinecekKitap);
        String sonucValue = kitaplarMap.remove(silinecekKitap);

        System.out.print(silinecekKitap + " Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        //ARTIK UYGUN YERLERDE BEKLEMEDİĞİNİZ SONUÇLAR İÇİN TRY CATCH KULLANABİLİRSİNİZ...
        //////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println("Istediginiz kitap ismi bulunamadi");
        }/////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private static void kitapEkle() {
        //"A Tale of Two Cities", "Charles Dickens, Tarih, 1859" >> Kitap key,value su buna benzer şekilde...

        //TODO Kitap Adını, Yazar Adını, Kitap Türünü ve Yayınlanma Yılını Kullanıcıdan alarak,
        //TODO kitaplarMap'e ekleme yapınız...

        //TODO MÜMKÜNSE, kitap türünü, Enum olarak tanımlanan KitapTuru sınıfını kullanarak alınız...
        //KİTAP TÜRLERİ >>     TARIH, POLISIYE, KURGU, ROMAN, DESTAN, TANIMLANMAMIS_TUR
        //TODO Kullanıcı kitap türünü yanlış girdiği sürece , kullanıcıya
        //TODO "Hatali giris! Lutfen kitap turunu tekrar giriniz: " mesajı verin...
        //TODO while ve try-catch kullanılabilir... Giriş başarılı olursa try-catch bloğu kırılarak konsoldan
        //TODO yayınlama yılı alıp kitap ekleme işlemine devam edilebilir...
        //Kullanıcı tarafından girilen stringi, KitapTuru sınıfında tanımlanan türlerden birine çevirmeniz gerkecek...
        // kitapTuru değişkeni artık geçerli bir değere sahipse...
        // Diğer işlemlere devam edebilirsiniz.
        //System.out.println("Yayinlanma Tarihi: ");
        //String yayinTarihi = scan.nextLine();

        //TODO Ekleme işlemini tamamlayın...

        System.out.println("Kitap Adi: ");
        String kitapAdi = scan.nextLine();

        System.out.println("Yazar Adi: ");
        String yazarAdi = scan.nextLine();

        KitapTuru kitapTuru = null;
        while (kitapTuru == null) {
            System.out.println("Kitap Turu (Tarih, Polisiye, Kurgu, Roman, Destan): ");
            String tur = scan.nextLine();
            try {
                kitapTuru = KitapTuru.valueOf(tur.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Hatali giris! Lutfen kitap turunu tekrar giriniz.");
            }
        }

        System.out.println("Yayinlanma Yili: ");
        String yayinYili = scan.nextLine();

        String kitapDetaylari = yazarAdi + ", " + kitapTuru + ", " + yayinYili;
        kitaplarMap.put(kitapAdi, kitapDetaylari);
        System.out.println(kitapAdi + " kitabi eklendi.");
    }


    public static void turVeyaYayinTarihiIleKitapBulma() throws InterruptedException {

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
//
        //   System.out.println("Istediginiz kitabin turunu yaziniz: ");
        //   System.out.println("(Tarih, Polisiye, Kurgu, Roman, Destan)");
        //   //TODO Metodu kullanıcıdan alacağınız girdileri kullanarak tamamlayın...
//
        //   System.out.println(
        //           "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
        //                   "================= KITAP LISTESI ===============\n" +
        //                   "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");
//

        System.out.println("Arama yapmak istediginiz turu secin:\n1. Kitap Turu\n2. Yayin Yili");
        int secim = Integer.parseInt(scan.nextLine());

        System.out.println("\n============ TECHNO STUDY CONFLUENCE ==========");
        System.out.println("================= KITAP LISTESI ===============");
        System.out.println("Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");

        switch (secim) {
            case 1:
                System.out.println("Istediginiz kitap turunu yaziniz:");
                System.out.println("(Tarih, Polisiye, Kurgu, Roman, Destan)");
                String tur = scan.nextLine();

                // Iterate through the kitaplarMap and find books matching the specified tur
                for (Map.Entry<String, String> entry : kitaplarMap.entrySet()) {
                    String kitapIsmi = entry.getKey();
                    String kitapDetaylari = entry.getValue();
                    String[] kitapBilgileri = kitapDetaylari.split(",");
                    String kitapTuru = kitapBilgileri[1].trim(); // Kitap Turu, 2nd element after split

                    if (kitapTuru.equalsIgnoreCase(tur)) {
                        // Print the book matching the specified tur
                        System.out.println(kitapIsmi + " : " + kitapDetaylari);
                    }
                }
                break;
            case 2:
                System.out.println("Istediginiz yayin yilini yaziniz:");
                String yayinYili = scan.nextLine();

                // Iterate through the kitaplarMap and find books matching the specified yayinYili
                for (Map.Entry<String, String> entry : kitaplarMap.entrySet()) {
                    String kitapIsmi = entry.getKey();
                    String kitapDetaylari = entry.getValue();
                    String[] kitapBilgileri = kitapDetaylari.split(",");
                    String yayinYil = kitapBilgileri[2].trim(); // Yayin Yili, 3rd element after split

                    if (yayinYil.equalsIgnoreCase(yayinYili)) {
                        // Print the book matching the specified yayinYili
                        System.out.println(kitapIsmi + " : " + kitapDetaylari);
                    }
                }
                break;
            default:
                System.out.println("Gecersiz secim!");
        }
    }


    public static void yazardanKitapBulma() throws InterruptedException {

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
        //  System.out.println("Istediginiz yazar ismini yaziniz: ");
        //  //TODO Metodu kullanıcıdan alacağınız girdileri kullanarak tamamlayın...

        //  System.out.println(
        //          "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
        //                  "================= KITAP LISTESI ===============\n" +
        //                  "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");
        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir.
        // Şart değil, isteğe bağlı.

        System.out.println("Istediginiz yazar ismini yaziniz: ");
        String arananYazar = scan.nextLine();

        // Print header
        System.out.println("\n============ TECHNO STUDY CONFLUENCE ==========");
        System.out.println("================= KITAP LISTESI ===============");
        System.out.println("Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");

        // Iterate through the kitaplarMap and find books written by the specified author
        for (Map.Entry<String, String> entry : kitaplarMap.entrySet()) {
            String kitapIsmi = entry.getKey();
            String kitapDetaylari = entry.getValue();
            String[] kitapBilgileri = kitapDetaylari.split(",");
            String yazar = kitapBilgileri[0].trim(); // Yazar ismi, 1st element after split

            if (yazar.equalsIgnoreCase(arananYazar)) {
                // Print the book written by the specified author
                System.out.println(kitapIsmi + " : " + kitapDetaylari);
            }
        }

    }

    public static void kitapListesiYazdir() throws InterruptedException { //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
//
        //  System.out.println(
        //          "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
        //                  "================= KITAP LISTESI ===============\n" +
        //                  "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");
//
//
        //TODO Kitapları listeleyecek metodu oluşturun...
        //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...

        System.out.println("\n============ TECHNO STUDY CONFLUENCE ==========");
        System.out.println("================= KITAP LISTESI ===============");
        System.out.println("Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");

        // Iterate through the kitaplarMap and print all books
        for (Map.Entry<String, String> entry : kitaplarMap.entrySet()) {
            String kitapIsmi = entry.getKey();
            String kitapDetaylari = entry.getValue();
            System.out.println(kitapIsmi + " : " + kitapDetaylari);
        }
    }
}
