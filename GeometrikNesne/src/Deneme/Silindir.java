package Deneme;


public class Silindir extends GeometrikNesne {

    private double yaricap;
    private double uzunluk;
    final double pi = Math.PI;

    public Silindir() {  //parametresiz constructor
        super();  //etiket ve date degerleri icin geometriknesne classının parametsiz consturctorında değerler atanır.
        yaricap = 1.0;  //default degerler
        uzunluk = 1.0;
    }

    public Silindir(String etiket, Date theDate, double yaricap, double uzunluk) {
        super(etiket, theDate);  //etiket ve date degerleri icin geometriknesne classında değişkenlere atanır.
        setYaricap(yaricap);
        setUzunluk(uzunluk);
        //deger kontrollu get set
    }

    public Silindir(Silindir theCylinder) {
        super(theCylinder);
        yaricap = theCylinder.yaricap;
        uzunluk = theCylinder.uzunluk;
    }

    public double getYaricap() {
        return yaricap;
    }

    public void setYaricap(double yaricap) {
        if (yaricap <= 0) {
            System.out.println("UZUNLUK DEĞERİ POZİTİF OLMALIDIR.");
            System.exit(0);}
        else {
            this.yaricap = yaricap;}
    }

    public double getUzunluk() {
        return uzunluk;
    }

    public void setUzunluk(double uzunluk) {
        if (uzunluk <= 0) {
            System.out.println("UZUNLUK DEĞERİ POZİTİF OLMALIDIR.");
            System.exit(0);
        } else {
            this.uzunluk = uzunluk;
        }
    }

    @Override
    public double cevreHesapla() {
        return (4 * yaricap * pi) + (2 * uzunluk);
    }

    @Override
    public double alanHesapla() {
        return 2 * yaricap * pi * (yaricap + uzunluk);
    }

    public double hacimHesapla() {
        return pi*yaricap*yaricap*uzunluk;
    }
    
    @Override
    public int compareTo(Object theObject) {   //compareTo silindir hacmi karşılaştırılması için override edilerek yazılmıştır.
        if (this.getClass() == theObject.getClass()) {  //karşılaştırma iki aynı classa ait nesne arasında yapılmalıdır.
            Silindir other = (Silindir) theObject;
            if (this.hacimHesapla() > other.hacimHesapla()) {
                return 1;
            } else if (this.hacimHesapla() < other.hacimHesapla()) {
                return -1;
            } else {
                return 0;
            }
        }
        return 0;  // if bloğuna girmediği takdirde mutlaka int değer dondurmesi gerektiği için 0 yazılmıştır.
    }
    
    @Override
    public String toString() {
        return ("Etiket: " + getEtiket() + "  Oluşturulduğu tarih: " + 
        getDateCreated() + "  Silindir yarıçapı: " + getYaricap() + "  Silindir uzunluğu " + getUzunluk());
    }
}


