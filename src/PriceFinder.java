public class PriceFinder{


    public PriceFinder() {

    }

    public static double getCurrentPrice(String url)
    {

        return Math.floor(getRandomNumber(100, 500)*100)/100;
    }

    public static double getOriginalPrice(String url){
        return Math.floor(getRandomNumber(100, 500)*100)/100;

    }
    static double getRandomNumber(double min, double max){
        return (Math.random() * ((max - min) + 1)) + min;
    }
}
