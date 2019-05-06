import java.time.LocalDate;
import org.json.JSONObject;

//import org.json.*;
//import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;


public class Item{
    private String Name;
    private double currentPrice;
    private String description;
    private  double originalPrice;
    private String URL;
    private String dateAdded;
    private double percentChange;
    //private PriceFinder priceFinder = new PriceFinder();

    /**
     * Default constructor
     * */

    public Item(){

    }

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
    /**
     * @return current price of the item
     * */
    public double getCurrentPrice() {
        return currentPrice;
    }
    /**
     * @return original price
     * */
    public double getOriginalPrice() {
        return originalPrice;
    }
    /**
     * @param url
     * */
    public void checkCurrentPrice(String url) {
        currentPrice = getPrice(url);
        percentChange = Math.floor(((originalPrice - currentPrice) / originalPrice) * 10000) / 100;

    }

    public void setName(String name) {
        Name = name;
    }
    /**
     * @param currentPrice
     * */
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
    /**
     * @param description
     * */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @param URL
     * */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * Creates new instance of finding price
     * for the 3 different stores
     * @param url user input
     * @return price from website
     */
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
    
    /**
     * Takes the item and "maps" in JSON format
     */
    public JSONObject toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", this.description);
        map.put("currentPrice", currentPrice);
        map.put("OriginalPrice", this.originalPrice);
        map.put("percentChange", this.percentChange);
        map.put("URL", this.URL);
        map.put("dateAdded", this.dateAdded);
        return new JSONObject(map);
    }

    /**
     * @param obj this is used to set the values,
     * gathers Item attributes from JSON txt
     * and assigns to the Item
     */
    public  Item fromJson(JSONObject obj){
        this.description = obj.getString("name");
        this.currentPrice = (double) obj.getDouble("currentPrice");
        this.originalPrice= (double) obj.getDouble("OriginalPrice");
        this.percentChange= (double) obj.getDouble("percentChange");
        this.URL= obj.getString("URL");
        this.dateAdded= obj.getString("dateAdded");


        Item item= new Item(currentPrice,description,originalPrice,URL,dateAdded,percentChange);

        return item;
    }
}


