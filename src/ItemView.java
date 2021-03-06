
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.StyledEditorKit;

/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel{

    private Item item;

    //public ItemView(Item newItem){
      //  this.item= newItem;

    //}
    public ItemView(){
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
                    listener.clicked();
                }
            }
        });
    }


    /**
     * Interface to notify a click on the view page icon.
     */
    public interface ClickListener {

        /**
         * Callback to be invoked when the view page icon is clicked.
         */
        void clicked();
    }

    /**
     * @param item in path of Item
     */
    public void updatePrice(Item item) {
        item.checkCurrentPrice(item.getURL());
    }



    public void establish() {
        JTextField txtInput = new JTextField("");
        item.setName(txtInput.getText());
        JTextField txtInputs = new JTextField("");
        item.setURL(txtInputs.getText());
        item.getCurrentPrice();
    }
    /**
     * @param item in path of Item
     */
    public String getURL(Item item) {
        return item.getURL();
    }

    /**
     * Directory for image files: src/image in Eclipse.
     */
    private final static String IMAGE_DIR = "/image/";

    /**
     * View-page clicking listener.
     */
    protected ClickListener listener;

    /**
     * Create a new instance.
     */


    /**
     * Set the view-page click listener.
     */
    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    /**
     * Overridden here to display the details of the item.
     */

    @Override
    public void paintComponent(Graphics g) {

        Image logo = getImage("http://icons.iconarchive.com/icons/wineass/ios7-redesign/256/Safari-icon.png");

        super.paintComponent(g);


        int x = 20, y = 30;


        g.drawImage(logo, x - 5, y - 10, 20, 20, null);

        g.setColor(Color.BLACK);
        y += 30;

        g.drawString("Name: \t", x, y);

        g.setFont(new Font("default", Font.BOLD, 12)); //Bolding item name
        g.drawString(item.getName(), x + 45, y);
        g.setFont(new Font("default", Font.PLAIN, 12)); //setting font back to normal
        y += 22;

        g.drawString("URL: \t" + item.getURL(), x, y); //displayURL
        y += 22;

        g.drawString("Price: \t", x, y);
        g.setColor(Color.BLUE);
        x += 40;

        g.drawString("$" + item.getCurrentPrice(), x, y);
        x -= 40;
        y += 22;

        g.setColor(Color.BLACK);

        String change = Double.toString(item.getChange());
        NoApplet app = new NoApplet(); //music

        if (item.getChange() < 0) {

            g.drawString("Change: \t", x, y);
            g.setColor(Color.red);
            x += 50;
            g.drawString("\t" + item.getChange() + "%", x, y);
            x -= 50;
            //app.play("http://www.wavsource.com/snds_2018-06-03_5106726768923853/sfx/boo.wav");

        } else {

            g.drawString("Change: \t", x, y);
            g.setColor(Color.GREEN);
            x += 50;
            g.drawString("\t" + change + "%", x, y);
            x -= 50;

            //app.play("http://www.wavsource.com/snds_2018-06-03_5106726768923853/sfx/boing_x.wav");
        }

        g.setColor(Color.BLACK);

        y += 22;

        g.drawString("Added: \t" + item.getDateAdded() + "\t($" + item.getOriginalPrice() + ")", x, y);

    }



    /**
     * @return true if the given screen coordinate is inside the viewPage icon.
     */
    private boolean isViewPageClicked(int x, int y) {
        if (x <= 20 && y <= 20) {
            openURL(item.getURL());
            return true;
        }

        return new Rectangle(20, 20, 30, 30).contains(x, y);
    }

    /**
     * @return the image stored in the given file.
     */
    public Image getImage(String file) {
        try {
            URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


}