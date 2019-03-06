public class consoleUI {
    private Item item;

    public consoleUI(Item item) {
        this.item = item;
    }

    public void showWelcome() {
        // TODO Auto-generated method stub
        System.out.println("Welcome to PW!");

    }

    public void showItem() {
        System.out.println("Here're item details...");
        item.displayInfo();
    }

    public void promptUser(){
        System.out.println("0.Exit Program");
        System.out.println("1.Check Price: ");
        System.out.println("2.View Page");
        // TODO Auto-generated method stub
    }
}

