public class PriceFinder{

    /**
     * @see default constructor
     * */
    public PriceFinder() {

    }

    /**
     * @param url user input
     * @return double of the current price
     * */
    public  double getCurrentPrice(String url)
    {

        return Math.floor(getRandomNumber(100, 500)*100)/100; //truncating double to two decimal
    }

    public double getOriginalPrice(String url){
        return Math.floor(getRandomNumber(100, 500)*100)/100;

    }
    /**
     * @param max highest value
     * @param min smallest value
     * @return random number
     * */
    static double getRandomNumber(double min, double max){
        return (Math.random() * ((max - min) + 1)) + min;
    }


}
