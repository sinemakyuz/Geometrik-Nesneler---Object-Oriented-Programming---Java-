package Deneme;


public abstract class GeometrikNesne implements Comparable {

    private String etiket;
    private Date dateCreated;
    
    public GeometrikNesne() {   //parametresiz constructor
        this.etiket = "Henüz bir etiket girilmedi";
        dateCreated = new Date();
    }

    public GeometrikNesne(String etiket, Date theDate) {
        setEtiket(etiket);
        dateCreated = new Date (theDate);
    }
    
    public GeometrikNesne(GeometrikNesne theObject) {  //copy constructor
        etiket = theObject.etiket;
        dateCreated = new Date(theObject.dateCreated);  //date icin yeni nesne olusturularak privacy leak engellenir.
    }

    public String getEtiket() {
        return etiket;
    }

    public void setEtiket(String etiket) {
        this.etiket = etiket;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    
    @Override
    public String toString() {
        return ("Etiket: " + getEtiket() + " Oluşturulduğu tarih: " + dateCreated.toString());
    }
    
    //methodlar abstract bir classta olduğu için sadece method imzası yazılıp diğer subclasslarda tanımlanacaktır.
    public abstract double cevreHesapla();

    public abstract double alanHesapla();

    @Override
    public abstract int compareTo(Object theObject);


}