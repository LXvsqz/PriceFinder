import java.io.IOException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main_manager extends Main{
    public static void main(String[] args) {
        new Main();
        String walmart_url = "https://walmart.com/ip/Straight-Talk-Apple-iPhone-6s-Prepaid-Smartphone-with-32GB-Space-Gray/863424218";
        String wprice = Walmart(walmart_url);
        System.out.println(wprice);
    }
    private static String Walmart(String walmart_url){
        String p = "";
        try {
            Document document = Jsoup.connect(walmart_url).get();
            String title = document.title();
            System.out.println(title);

            Elements price = document.select(".price-group:contains($)");
            p =  price.get(0).text();


        }
        catch (IOException e){ e.printStackTrace();}
        return p;
    }
}
