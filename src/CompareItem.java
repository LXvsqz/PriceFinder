public abstract class CompareItem extends Item implements Comparable<Item>{
    /**
     * @param o
     * @return a binary value
     * */
    @Override
    public int compareTo(Item o){


        if(this.getCurrentPrice() < o.getCurrentPrice())
            return 1;

        return 0;
    }
}
