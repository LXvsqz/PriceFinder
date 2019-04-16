import java.time.LocalDate;

public class Item {
    public void setName(String name) {
        Name = name;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    private String Name;
    private double currentPrice;
    private String description;
    private final double originalPrice;
    private String URL;
    private String dateAdded;
    private double percentChange;


    public Item(String description, String url){
        this.originalPrice = PriceFinder.getOriginalPrice(url);
        this.currentPrice = PriceFinder.getCurrentPrice(url);
        this.description = description;
        this.URL = url;
        this.dateAdded = "" + LocalDate.now();
        this.percentChange =  Math.floor(((originalPrice-currentPrice)/originalPrice)*10000) / 100;
    }

    public String getName() {
        return description;
    }

    public String getURL() {
        return URL;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public double getChange() {

        return percentChange;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public double getOriginalPrice(){
        return originalPrice;
    }
    public void checkCurrentPrice(String url){
        currentPrice =  PriceFinder.getCurrentPrice(url);
        percentChange = Math.floor(((originalPrice-currentPrice)/originalPrice)*10000) / 100;

    }

    public void setName(String name) {
        Name = name;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }


}



