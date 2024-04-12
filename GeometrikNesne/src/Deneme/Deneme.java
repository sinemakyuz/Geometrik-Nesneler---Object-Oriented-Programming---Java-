package Deneme;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author sinem
 */
public class Deneme {

    public static void polymorphicYazdir(GeometrikNesne figure) {  //geometrik nesne bilgilerini cevre ve alanı yazdırır.
        if (figure instanceof Daire || figure instanceof Dikdortgen || figure instanceof Silindir) {  //cevre ve alan bilgisi tum nesneler icin istenmekte.
            
            System.out.println(figure.toString());  //figure değişkenine bağlı olarak ilgili classın toString methodu cagrilir.
            System.out.printf("Çevresi: %,.2f  %s %,.2f \n", figure.cevreHesapla(),
                    "Alanı:", figure.alanHesapla());}

        if (figure instanceof Silindir) {  //geometrik nesne silindire aitse ek olarak hacim de hesaplanır.
            System.out.printf("Hacmi: %,.2f\n", ((Silindir) figure).hacimHesapla());}
    }
 
    public static int karsilastir(GeometrikNesne figure1, GeometrikNesne figure2){

        return figure1.compareTo(figure2);}  //subclasslar içindeki compareTo, gelen nesneye bağlı olarak ilgili classın methodu cağrılır.

    
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner fileIn = null;  //dosya acilmadan once default null atanmıştır.

        try {   //dosya bulunamadi hatasina karsi try-catch blogu yazilmistir.
            fileIn = new Scanner(new FileInputStream("input.txt"));}   //dosya acilir.
        catch (FileNotFoundException e) {   //bulunamaz ise bu bloga gelir ve buradaki komutlar yurutulur.
            System.out.println("File not found.");   //istenilen hata mesaji yazdirilir.
            System.exit(0);   //sistemden cikilir.
        }

        GeometrikNesne figuresArr[] = new GeometrikNesne[50];  //geometrik nesnelerin tutuldugu dizi.
        GeometrikNesne copyObjects[] = new GeometrikNesne[3];  
        /* copy constructor ile if ler icinde olusturulan objelere while disinda erisebilmek
        ve dongu sonrasi figuresArr obje dizisinin son elemanina atayabilmek icin olusturuldu. */

        //DEGİSKENLER-------------------------------------------------------------------------
        int countObjects = 0;  //objelerin sayisini while dongusunun donus sayisina bagli olarak bulmak icin atanmistir.
        //compareTo methodu cagirilirken karsilastirmamiz istenilen nesnelerin indexlerini ileride atamak icin atanmistir.
        
        int firstCircle = -1, lastCircle = -1, firstRectangle = -1, lastRectangle = -1, firstCylinder = -1, lastCylinder = -1;
        //ortalama cevre, alan ve hacim hesabinda kullanilacagi icin atanmistir, index tutar.
        
        double tumSekillerCevre = 0, tumSekillerAlan = 0, silindirHacimTopla = 0;
        //max min degerleri atayabilmek icin onceden olusturulmustur(cevre ve alan tum nesneler icin, hacimse silindir icin).
        
        double cevre = 0, alan = 0, hacim = 0;
        
        /*max degerlerini bulmak ve tutmak icin 0 atanmistir uzunluk degeri en az 0 olabilir.
        min degerler icin hacimde ilk olusturulan nesnenin hacmi min yapilacaktir.
        min degerleri karsilastirabilmek icin cevre ve alanda ilk deger olarak POSITIVE_INFINITY kullanilmistir.*/       
        double maxCevre = 0, minCevre = Double.POSITIVE_INFINITY, maxAlan = 0,
                minAlan = Double.POSITIVE_INFINITY, maxHacim = 0, minHacim = Double.POSITIVE_INFINITY;
        
        //ortalaması istenen degerleri tutmak icin kullanilmistir.
        double cevreOrt = 0, alanOrt = 0, hacimOrt = 0, silindirSayisi = 0; 
        
        //genel olarak dosyadan veri okuma, alinan verileri if bloklariyla siniflandirma icin while dongusu kullanilmistir.
        try {
            while (fileIn.hasNextLine()) {  //okunan dosyada next line oldukca doner.

                countObjects++;  //dosyada her yeni satir bir nesneyi ifade ettiginden her while dondugunde 1 arttırılır.
                String sekilAdi = fileIn.next();  //sekil adi stringine gore kalan veriler okunacagindan ilk olarak if disinda okunur.

                if (sekilAdi.equalsIgnoreCase("daire")) {  //okudugumuz veriler daireye aitse calisacak blok


                    //ilk daire objesinin indexini ayirt edebilmek icin yazilmistir. Bu index compareTo methodu cagirilirken kullanilacaktir.
                    if (firstCircle == -1) {firstCircle = countObjects-1;}  

                    lastCircle = countObjects-1;   //son copy olmayan daire objesinin indexini tutmak icin yazilmistir.

                    String daireEtiket = fileIn.next();
                    /*bilgisayarim metindeki double tipindeki degerleri string algilayip InputMismatchException
                    firlattigindan parseDouble methodu ile dosyadaki string degeri double tipine cevirdim.*/
                    double daireYaricap = Double.parseDouble(fileIn.next());  
                    String info = fileIn.next();  //istenmeyen veri sadece okunmus gerekmediği için kullanılmamıştır.
                    int daireAy = fileIn.nextInt();
                    int daireGun = fileIn.nextInt();
                    int daireYil = fileIn.nextInt();

                    //daire tipinde dosyadan okunan parametreleri alan objemiz olusturuldu.
                    Daire daire = new Daire(daireEtiket, new Date(daireAy, daireGun, daireYil), daireYaricap);

                    cevre = daire.cevreHesapla();  //cevre ve alan ortalamalar ve max min degerlerini bulmak icin tutuluyor.
                    alan = daire.alanHesapla();
                    tumSekillerCevre += cevre;  //tum nesnelerin cevre ve alanlarini toplar.
                    tumSekillerAlan += alan;

                    figuresArr[countObjects - 1] = daire; //olusturulan nesne diziye eklenir.

                    Daire daireCopy = new Daire(daire);  //copy constructor ile son oluşturulan daire nesnesinin kopyası new obje olarak oluşturulur.
                    copyObjects[0] =  daireCopy;  //kopya nesnelerini tuttuğum dizinin ilk indexine atandı.

                }  //daire icin yazilan if blogu sonu

                else if (sekilAdi.equalsIgnoreCase("dikdortgen")) {  //eger etiket dikdortgense veriler bu bloktan dikdortgen icin okunulmaya devam eder.

                    //ilk dikdortgen objesinin indexini ayirt edebilmek icin yazilmistir. Bu index compareTo methodu cagirilirken kullanilacaktir.
                    if (firstRectangle == -1) { firstRectangle = countObjects-1;} 

                    lastRectangle = countObjects-1;   //son copy olmayan dikdortgen objesinin indexini tutmak icin yazilmistir.

                    String dikdortgenEtiket = fileIn.next();
                    double dikdortgenEn = Double.parseDouble(fileIn.next());
                    double dikdortgenBoy = Double.parseDouble(fileIn.next());
                    String info = fileIn.next();  //istenmeyen veri sadece okunmus gerekmediği için kullanılmamıştır.
                    int dikdortgenAy = fileIn.nextInt();
                    int dikdortgenGun = fileIn.nextInt();
                    int dikdortgenYil = fileIn.nextInt();

                    Dikdortgen dikdortgen = new Dikdortgen(dikdortgenEtiket,
                            new Date(dikdortgenAy, dikdortgenGun, dikdortgenYil), dikdortgenEn, dikdortgenBoy);

                    figuresArr[countObjects - 1] = dikdortgen;

                    Dikdortgen dikdortgenCopy = new Dikdortgen(dikdortgen);
                    copyObjects[1] =  dikdortgenCopy;

                    cevre = dikdortgen.cevreHesapla();
                    alan = dikdortgen.alanHesapla();
                    tumSekillerCevre += cevre;
                    tumSekillerAlan += alan;

                } else if (sekilAdi.equalsIgnoreCase("silindir")) {

                    //ilk dikdortgen objesinin indexini ayirt edebilmek icin yazilmistir. Bu index compareTo methodu cagirilirken kullanilacaktir.
                    if (firstCylinder == -1) {firstCylinder = countObjects-1;}  

                    lastCylinder = countObjects-1;  //son copy olmayan silindir objesinin indexini tutmak icin yazilmistir.

                    String silindirEtiket = fileIn.next();
                    double silindirYaricap = Double.parseDouble(fileIn.next());
                    double silindirUzunluk = Double.parseDouble(fileIn.next());
                    String info = fileIn.next();  //istenmeyen veri sadece okunmus gerekmediği için kullanılmamıştır.
                    int silindirAy = fileIn.nextInt();
                    int silindirGun = fileIn.nextInt();
                    int silindirYil = fileIn.nextInt();

                    Silindir silindir = new Silindir(silindirEtiket,  //silindir nesnesi olusturuldu.
                            new Date(silindirAy, silindirGun, silindirYil), silindirYaricap, silindirUzunluk);

                    silindirSayisi++;  //hacim ortalaması icin kullanılacaktır.

                    figuresArr[countObjects - 1] = silindir;  //silindir nesnesi geometrik nesne listesine eklenir.

                    Silindir silindirCopy = new Silindir(silindir);  //son silindirin kopyası olusturuldu.
                    copyObjects[2] =  silindirCopy;  //copyObjects dizisine atandı.

                    hacim = silindir.hacimHesapla();  //silindirlerin hacim ortalaması hesaplanmasında kullanılır.
                    cevre = silindir.cevreHesapla();  //tum sekillerin cevre ortalaması hesaplanmasında kullanılıcaktır.
                    alan = silindir.alanHesapla();    //tum sekillerin alan ortalaması hesaplanmasında kullanılır.
                    tumSekillerCevre += cevre;
                    tumSekillerAlan += alan;
                    silindirHacimTopla += silindir.hacimHesapla();


                    if (hacim >= maxHacim) {maxHacim = hacim;}  //silindir objelerinin hacimine buradan kolayca erişilebildiği için-
                    if (hacim <= minHacim) {minHacim = hacim;}  //hacim max min bulma burada yazilmistir.
                }

                if (cevre >= maxCevre) {maxCevre = cevre;}  // tum geometrik nesnelerin karsılaştırılması yapılmıştır.

                if (cevre <= minCevre) {minCevre = cevre;}

                if (alan >= maxAlan) {maxAlan = alan;}

                if (alan <= minAlan) {minAlan = alan;}

            }  // while bitisi
            
        }catch (InputMismatchException e) {  //program yürütülürken birden fazla kez fırlatılan bir exception olduğu için-
            System.out.println("Okumak istediğiniz text dosyanın veri tipi sıralamasında bir hata oluşmuştur.");  //özel mesaj yazdıralarak sistemden çıkılır.
            System.exit(0);}
        
        fileIn.close();   // while dongusunden cikinca okuma islemi bitiyor ve dosyayi kapatiyoruz.
        
        //copy nesneleri sırasıyla geometrik nesne dizisinin son indixlerine atanır.
        figuresArr[countObjects] = copyObjects[0];
        figuresArr[countObjects+1] = copyObjects[1];
        figuresArr[countObjects+2] = copyObjects[2];
        
        System.out.println ("\nOLUŞTURULAN NESNELERİN BİLGİLERİ\n---------------------------------");
        for (int i = 0; i <= countObjects + 2; i++){
            polymorphicYazdir(figuresArr[i]);}  //polymorphicYazdir methodu dizideki her nesne için çağırılır.
        
        //tum geometrik nesnelerin ortalamaları istendiği için copy constructor ile oluşturulan nesneler de toplama eklenir.
        silindirHacimTopla += hacim;
        
        tumSekillerCevre += copyObjects[0].cevreHesapla();  
        tumSekillerCevre += copyObjects[1].cevreHesapla();
        tumSekillerCevre += copyObjects[2].cevreHesapla();
        
        tumSekillerAlan += copyObjects[0].alanHesapla();
        tumSekillerAlan += copyObjects[1].alanHesapla();
        tumSekillerAlan += copyObjects[2].alanHesapla();
        
        
        
        System.out.println ("\nKARŞILAŞTIRMA SONUÇLARI");
        System.out.println ("{1: İlk nesne bilgisi daha büyük}   {0: İki nesnenin bilgisi eşit}   {-1: İkinci nesnenin bilgisi daha küçük}");
        System.out.println ("----------------------------------------------------------------------------------------------------------------");
        
        System.out.println("İlk daire ile son daire yarıçapları karşılaştırılması: "+
                karsilastir(figuresArr[firstCircle], figuresArr[countObjects]));
        
        System.out.println("Son daire ile sondan bir önceki daire yarıçapları karşılaştırılması: " +
                karsilastir(figuresArr[lastCircle], figuresArr[countObjects]));
        
        System.out.println("İlk dikdörtgen ile son dikdörtgenin alanları karşılaştırılması: " +
                karsilastir(figuresArr[firstRectangle], figuresArr[countObjects+1]));
        
        System.out.println("Son dikdörtgen ile sondan bir önceki dikdörtgen alanları karşılaştırılması: " +
                karsilastir(figuresArr[lastRectangle], figuresArr[countObjects+1]));
        
        System.out.println("İlk silindir ile son silindir hacimleri karşılaştırılması: " +
                karsilastir(figuresArr[firstCylinder], figuresArr[countObjects+2]));
        
        System.out.println("Son silindir ile sondan bir önceki silindir hacimleri karşılaştırılması: " +
                karsilastir(figuresArr[lastCylinder], figuresArr[countObjects+2]));            

        
        double statistics[] = new double[9];  //istatistikler icin yaratilan 9 elemanlık dizi.
        
        statistics[0] = tumSekillerCevre/(countObjects+3);  //sırası ile indexlere atamalar projede istendiği şekilde yapılır.
        statistics[1] = tumSekillerAlan/(countObjects+3);
        statistics[2] = silindirHacimTopla/(silindirSayisi+1);
        statistics[3] = maxCevre;
        statistics[4] = minCevre;
        statistics[5] = maxAlan;
        statistics[6] = minAlan;
        statistics[7] = minHacim;
        statistics[8] = maxHacim;
        
        System.out.println ("\nİSTATİSTİKLER\n----------------");
        System.out.printf("Tüm geometrik nesnelerin  çevre  ortalaması = %,.2f\n", statistics[0]); 
        System.out.printf("Tüm geometrik nesnelerin  alan  ortalaması = %,.2f\n", statistics[1]); 
        System.out.printf("Silindirlerin hacim ortalaması= %,.2f\n", statistics[2]);
        System.out.printf("Tüm şekiller göz önüne alındığında en büyük çevre değeri  = %,.2f\n", statistics[3]); 
        System.out.printf("Tüm şekiller göz önüne alındığında en küçük çevre değeri  = %,.2f\n", statistics[4]);
        System.out.printf("Tüm şekiller göz önüne alındığında en büyük alan değeri   = %,.2f\n", statistics[5]); 
        System.out.printf("Tüm şekiller göz önüne alındığında en küçük alan değeri   = %,.2f\n", statistics[6]); 
        System.out.printf("Silindirler arasında en küçük hacim değeri  = %,.2f\n", statistics[7]); 
        System.out.printf("Silindirler arasında en büyük hacim değeri  = %,.2f\n", statistics[8]);
        
    }  // main bitisi
}  // class bitisi
