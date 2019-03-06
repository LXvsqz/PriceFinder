public class Item {
    private String Name;
    private double currentPrice;
    private String description;
    private double originalPrice = getRandomNumber(100, 500.00);
    private String URL;
    private String dateAdded;


    //never use float or double when dealing with currency
    //use int or long//

    //TODO: Need to fix currency
    //TODO: Need to seperate methods for generating this currency and make originalPrice a final variable


    public Item(String name, double price, String description, String url, String date) {
        Name = name;
        price = getRandomNumber(100, 500);
        this.currentPrice = price;
        this.description = description;
        this.URL = url;
        this.dateAdded = date;
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
        return percentChange;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public double getOriginalPrice(){
        return originalPrice;
    }
    static double getRandomNumber(double min, double max){
        return (Math.random() * ((max - min) + 1)) + min;
    }


    public void displayInfo() {
        System.out.println("Item: " + this.Name);
        System.out.println("URL: " + this.URL);
        System.out.println("Current Price: " + this.currentPrice);
        System.out.println("Change: " + this.getChange() + "%");
        System.out.println("Added: 02/04/2018 (" + originalPrice + ")");
    }

    public void setURL(String newURL){ //not used now but will be needed later on
        this.URL = newURL;
    }

}



