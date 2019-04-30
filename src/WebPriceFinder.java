import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class WebPriceFinder extends PriceFinder {
    //public WebPriceFinder{
       // int finta = 0;


//}
/**
    @Override
    public  double getCurrentPrice(String web){
        HttpURLConnection con = null;
        try {
            URL url = new URL(web);
            con = (HttpURLConnection) url.openConnection();
            // con.setRequestProperty("User-Agent", "...");
            String encoding = con.getContentEncoding();
            if (encoding == null) { encoding = "utf-8"; }
            InputStreamReader reader = null;
            if ("gzip".equals(encoding)) { // gzipped document?
                reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
            } else {
                reader = new InputStreamReader(con.getInputStream(), encoding);
            }
            BufferedReader in = new BufferedReader(reader);
            String line;
            while ((line = in.readLine()) != null) {
                line
            }
        } catch (IOException e) { e.printStackTrace();
        } finally {
            if (con != null) {  con.disconnect(); }
        }
        return 3;
    }
    @Override
    public double getOriginalPrice(String url){
        return 3;
    }
**/

}
