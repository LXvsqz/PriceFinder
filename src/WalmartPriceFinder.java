import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WalmartPriceFinder extends PriceFinder{


    @Override
    public double getCurrentPrice(String walmart_url){
        String p = "";
        try {
            Document document = Jsoup.connect(walmart_url).get();
            String title = document.title();
            //System.out.println(title);

            Elements price = document.select(".price-group:contains($)");
            p =  price.get(0).text();



        }
        catch (IOException e){ e.printStackTrace();}
        p = p.replaceAll(",","");
        p=p.substring(1);
        Double price = Double.parseDouble(p);
        return price;
    }
}
