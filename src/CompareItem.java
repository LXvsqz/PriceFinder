public abstract class CompareItem extends Item implements Comparable<Item>{

    @Override
    public int compareTo(Item o){


        if(this.getCurrentPrice() < o.getCurrentPrice())
            return 1;

        return 0;
    }
}
