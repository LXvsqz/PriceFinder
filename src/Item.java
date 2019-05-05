import java.time.LocalDate;

public class Item{
    private String Name;
    private double currentPrice;
    private String description;
    private final double originalPrice;
    private String URL;
    private String dateAdded;
    private double percentChange;
    //private PriceFinder priceFinder = new PriceFinder();


    public Item(double currentPrice, String description, double originalPrice, String URL, String dateAdded, double percentChange) {
        this.currentPrice = currentPrice;
        this.description = description;
        this.originalPrice = originalPrice;
        this.URL = URL;
        this.dateAdded = dateAdded;
        this.percentChange = percentChange;
    }

    public Item(String description, String url) {
        this.originalPrice = getPrice(url);
        this.currentPrice = getPrice(url);
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
        currentPrice = getPrice(url);
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
    public Double getPrice(String  url){
        String host = Main.getHostName(url);
        switch(host){
            case("walmart.com"):
                WalmartPriceFinder temp = new WalmartPriceFinder();
                return temp.getCurrentPrice(url);
            case("elpaso.craigslist.org"):
                CraigslistPriceFinder temp2 = new CraigslistPriceFinder();
                return temp2.getCurrentPrice(url);
            case("frys.com"):
                FrysPriceFinder temp3 = new FrysPriceFinder();
                return temp3.getCurrentPrice(url);
        }
      return -1.1;
    }
}


