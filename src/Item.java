public class Item{
    private String Name;
    private double currentPrice;
    private String description;
    private double originalPrice= getRandomNumber(100,500.00);
    private String URL;
    private String dateAdded;


    //never use float or double when dealing with currency
    //use int or long//




    public Item(String name, double price, String description, String url, String date) {
        Name = name;
        price=getRandomNumber(100,500);
        this.currentPrice = price;
        this.description = description;
        this.URL= url;
        this.dateAdded= date;
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

    public double setChange(){
        double original= this.originalPrice;
        double current= this.currentPrice;
        double percentChange= ((original - current) / original) * 100;
        return percentChange;
    }

    public double getChange(){
        double original= this.originalPrice;
        double current= this.currentPrice;
        double percentChange= ((original - current) / original) * 100;
        return percentChange;
    }


    public void displayInfo(){
        System.out.println("Item: " + this.Name);
        System.out.println("URL: " + this.URL);
        System.out.println("Current Price: " + this.currentPrice);
        System.out.println("Change: " + this.setChange() + "%");
        System.out.println("Added: 02/04/2018 (" + originalPrice + ")");
    }

    public void setURL(String newURL){
        this.URL= newURL;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getOriginalPrice(){
        return originalPrice;
    }

    static double getRandomNumber(double min, double max){ //function from CS2
        return (Math.random()*((max-min)+1))+min;
    }
}



