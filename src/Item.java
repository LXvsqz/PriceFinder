import java.time.LocalDate;

public class Item {
    private String Name;
    private double currentPrice;
    private String description;
    private double originalPrice;
    private String URL;
    private String dateAdded;


    //never use float or double when dealing with currency
    //use int or long//

    //TODO: Need to seperate methods for generating this currency and make originalPrice a final variable


    public Item(String name, String url) {
        Name = name;
        this.originalPrice = PriceFinder.getOriginalPrice(url);
        this.currentPrice = PriceFinder.getCurrentPrice(url);
        this.description = description;
        this.URL = url;
        this.dateAdded = "" + LocalDate.now();
    }

    public String getName() {
        return Name;
    }

    public String getURL() {
        return URL;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public double getChange() {
        double original = this.originalPrice;
        double current = this.currentPrice;
        double percentChange = ((original - current) / original) * 100;
        percentChange = Math.floor(percentChange*100)/100;
        return percentChange;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public double getOriginalPrice(){
        return originalPrice;
    }
    public double checkCurrentPrice(String url){
        return PriceFinder.getCurrentPrice(url);

    }




    public void setURL(String newURL){ //not used now but will be needed later on
        this.URL = newURL;
    }

}



