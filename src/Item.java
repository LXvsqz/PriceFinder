import java.time.LocalDate;

public class Item{
    private String Name;
    private double currentPrice;
    private String description;
    private final double originalPrice;
    private String URL;
    private String dateAdded;
    private double percentChange;
    private PriceFinder priceFinder = new PriceFinder();


    public Item(String description, String url) {
        this.originalPrice = priceFinder.getOriginalPrice(url);
        this.currentPrice = priceFinder.getCurrentPrice(url);
        this.description = description;
        this.URL = url;
        this.dateAdded = "" + LocalDate.now();
        this.percentChange = Math.floor(((originalPrice - currentPrice) / originalPrice) * 10000) / 100;
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

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void checkCurrentPrice(String url) {
        currentPrice = priceFinder.getCurrentPrice(url);
        percentChange = Math.floor(((originalPrice - currentPrice) / originalPrice) * 10000) / 100;

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

    @Override
    public String toString() {
        return this.Name + this.getCurrentPrice() + this.getOriginalPrice();
    }
}