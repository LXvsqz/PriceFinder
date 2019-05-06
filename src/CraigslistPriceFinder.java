import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CraigslistPriceFinder extends PriceFinder{

    /**
     * @param url that's given by user
     * @return double that contains the price
     * To connect to the site and scrap the information of the price
     * */
    @Override
    public double getCurrentPrice(String url){
        String p = "";
        try {
            Document document = Jsoup.connect(url).get();

            Elements price = document.select(".price:contains($)");
            p =  price.get(0).text();



        }
        catch (IOException e){ e.printStackTrace();}

        p = p.replaceAll(",","");
        p=p.substring(1);
        Double price = Double.parseDouble(p);
        return price;
    }
}

