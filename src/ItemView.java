

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.StyledEditorKit;
//TODO: Change color on individual lines. String of price (DONE)
//TODO: Add button and get refresh working
    //TODO: Refresh should only change current price
//TODO: isPageClicked
/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {
    private Item item = new Item("Iphone X","https://apple.com/iphone");
	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}
	public void updatePrice(){
	    item.checkCurrentPrice(item.getURL());
    }
    public String getURL(){
	    return item.getURL();
    }
	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "/image/";
        
	/** View-page clicking listener. */
    private ClickListener listener;
    
    /** Create a new instance. */
    public ItemView() {
    	setPreferredSize(new Dimension(100, 160));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
            		listener.clicked();
            	}
            }
        });
    }
        
    /** Set the view-page click listener. */
    public void setClickListener(ClickListener listener) {
    	this.listener = listener;
    }
    
    /** Overridden here to display the details of the item. */
    @Override
	public void paintComponent(Graphics g) {

        super.paintComponent(g);

        int x = 20, y = 30;

        g.setColor(Color.MAGENTA);
        g.drawString("{Site}", x,y);
        g.setColor(Color.BLACK);
        y+=30;
        g.drawString("Name: \t" + item.getName(),x,y);
        y+=22;
        g.drawString("URL: \t" + item.getURL(),x,y);
        y+=22;


        g.drawString("Price: \t",x,y);
        g.setColor(Color.BLUE);
        x += 40;
        g.drawString("$"+ item.getCurrentPrice(),x,y);
        x -=40;
        y+=22;
        g.setColor(Color.BLACK);
        String change= Double.toString(item.getChange());
        NoApplet app = new NoApplet();
        if(item.getChange() < 0) {
            g.drawString("Change: \t", x, y);
            g.setColor(Color.red);
            x += 50;
            g.drawString("\t" + item.getChange() + "%", x, y);
            x -= 50;
            app.play("http://www.wavsource.com/snds_2018-06-03_5106726768923853/sfx/boo.wav");
        }
        else{
            g.drawString("Change: \t",x,y);
            g.setColor(Color.green);
            x+=50;
            g.drawString("\t" + change + "%",x,y);
            x-=50;

            app.play("http://www.wavsource.com/snds_2018-06-03_5106726768923853/sfx/boing_x.wav");
        }
        g.setColor(Color.BLACK);

        y+=22;
        g.drawString("Added: \t" +item.getDateAdded() + "\t($" + item.getOriginalPrice() + ")",x,y );
        g.drawImage(getImage("IphoneX.png"),10,10,null);
    }
    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
    	//-- WRITE YOUR CODE HERE
        if(x == 20 && y==20)
            return true;

    	return new Rectangle(20, 20, 30, 20).contains(x,  y);
    }
    /** Return the image stored in the given file. */
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

}
