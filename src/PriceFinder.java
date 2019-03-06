public class PriceFinder{
    private Item item;

    public PriceFinder(Item item) {
        this.item = item;
    }

    public double getCurrentPrice(){
        return this.item.getCurrentPrice();
    }

    public double getOriginalPrice(){
        return this.item.getOriginalPrice();
    }
}
