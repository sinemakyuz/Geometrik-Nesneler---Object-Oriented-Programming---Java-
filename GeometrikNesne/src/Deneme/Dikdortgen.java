package Deneme;


public class Dikdortgen extends GeometrikNesne {

    private double en;
    private double boy;

    public Dikdortgen() {   //parametresiz constructor
        super();  //etiket ve date degerleri icin geometriknesne classının parametsiz consturctorında değerler atanır.
        en = 1.0;  //default degerler
        boy = 1.0;
    }

    public Dikdortgen(String etiket, Date theDate, double en, double boy) {
        super(etiket, theDate);  //etiket ve date degerleri icin geometriknesne classında değişkenlere atanır.
        setEn(en);
        setBoy(boy);
    }

    public Dikdortgen(Dikdortgen theRectangle) {
        super(theRectangle);  //etiket ve date degerleri icin geometriknesne classının copy consturctorında atanır.
        en = theRectangle.en;
        boy = theRectangle.boy;
    }

    public double getEn() {
        return en;
    }

    public void setEn(double en) {
        if (en <= 0) {
            System.out.println("UZUNLUK DEĞERİ POZİTİF OLMALIDIR.");
            System.exit(0);
        } else {
            this.en = en;
        }
    }

    public double getBoy() {
        return boy;
    }

    public void setBoy(double boy) {
        if (boy <= 0) {
            System.out.println("UZUNLUK DEĞERİ POZİTİF OLMALIDIR.");
            System.exit(0);
        } else {
            this.boy = boy;
        }
    }

    @Override
    public double cevreHesapla() {
        return 2*(en+boy);
    }

    @Override
    public double alanHesapla() {
        return en*boy;
    }

    @Override
    public int compareTo(Object theObject) {   //compareTo dikdörtgen alanı karşılaştırılması için override edilerek yazılmıştır.
    
        if (this.getClass() == theObject.getClass()) {  //karşılaştırma iki aynı classa ait nesne arasında yapılmalıdır.
            Dikdortgen other = (Dikdortgen) theObject;
            if (this.alanHesapla() > other.alanHesapla()) {
                return 1;
            }
            else if (this.alanHesapla() < other.alanHesapla()) {
                return -1;
            } 
            else {
                return 0;}
        }
        return 0;  // if bloğuna girmediği takdirde mutlaka int değer dondurmesi gerektiği için 0 yazılmıştır.
    }
    
    @Override
    public String toString() {
        return ("Etiket: " + getEtiket() + "  Oluşturulduğu tarih: " + 
        getDateCreated() + "  Dikdörtgen Eni: " + getEn() + "  Dikdörtgen boyu: " + getBoy());
    }
    
}
