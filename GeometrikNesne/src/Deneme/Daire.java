package Deneme;

//GeometrikNesne abstract sinifindan bir subclass. Degisken ve methodlarini kalitim yoluyla kullanir.
public class Daire extends GeometrikNesne {  

    private double yaricap;
    final double pi = Math.PI;  //kullanimi kolaylastirip kod tekrari ve islem sayisini azaltmasi icin tanimlanmistir.

    public Daire() {   //parametresiz constructor
        super();  //etiket ve date degerleri icin geometriknesne classının parametsiz consturctorında değerler atanır.
        yaricap = 1.0;  //default değer ataması.
    }

    public Daire(String etiket, Date theDate, double yaricap) {
        super(etiket, theDate);  //etiket ve date degerleri icin geometriknesne classında değişkenlere atanır.
        setYaricap(yaricap);
    }

    public Daire(Daire theCircle) {
        super(theCircle);  //etiket ve date degerleri icin geometriknesne classının copy consturctorında atanır.
        yaricap = theCircle.yaricap;
        }

    public double getYaricap() {
        return yaricap;
    }

    public void setYaricap(double yaricap) {  //kucuk bir hata kontrolü yapılmıştır.
        if (yaricap <= 0) {
            System.out.println("UZUNLUK DEĞERİ POZİTİF OLMALIDIR.");
            System.exit(0);
        } else {
            this.yaricap = yaricap;
        }
    }

    @Override
    public double cevreHesapla() {
        return 2 * yaricap * pi;
    }

    @Override
    public double alanHesapla() {
        return pi * yaricap * yaricap;
    }

    @Override
    public int compareTo(Object theObject) {  //compareTo override edilerek daire yaricapina gore karşılaştırma yapılmıştır.
    
        if (this.getClass() == theObject.getClass()) {  //karşılaştırma iki aynı classa ait nesne arasında yapılmalıdır.
            Daire other = (Daire) theObject;
            if (this.yaricap > other.getYaricap()) {
                return 1;
            } else if (this.yaricap < other.getYaricap()) {
                return -1;
            } else {
                return 0;
            }
        }
        return 0;  // if bloğuna girmediği takdirde mutlaka int değer dondurmesi gerektiği için 0 yazılmıştır.
    }
    
    @Override
    public String toString() {
        return ("Etiket: " + getEtiket() + "  Oluşturulduğu tarih: " + getDateCreated() + "  Daire yarıçapı: " + getYaricap());
                
    }
}
 
