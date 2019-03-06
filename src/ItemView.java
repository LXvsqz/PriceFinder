//package pricewatcher.base;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
//TODO: Change color on individual lines. String of price
//TODO: Add button and get refresh working
    //TODO: Refresh should only change current price
//TODO: isPageClicked
/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {
    
	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
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
        Item iphone= new Item("Iphone x", 350.00,"Newest Iphone on the market", "https://www.apple.com/iphone/", "03/05/2019");
        //Dimension dim = getSize();
        
        //--
        //-- WRITE YOUR CODE HERE!
        //--
        int x = 20, y = 30;
        //g.drawString("Name" +iphone.getName(),x,y);
        //g.drawString("");
        //g.drawImage(getImage("IphoneX.png"), x,y);
        //g.drawImage(x,y, getImage("IphoneX.png"));
        g.drawImage(getImage("IphoneX.png"),x,y,20,20,this);
        g.drawString("[View]", x, y);
        y += 22;
        g.drawString("Name: \t" + iphone.getName(),x,y);
        y+=22;
        g.drawString("URL: \t" + iphone.getURL(),x,y);
        y+=22;
        String origPrice= Double.toString(iphone.getOriginalPrice());
        String currPrice= Double.toString(iphone.getCurrentPrice());

        g.drawString("Price: \t",x,y);
        g.setColor(Color.BLUE);
        x += 40;
        g.drawString("$"+ currPrice,x,y);
        x -=40;
        y+=22;
        g.setColor(Color.BLACK);
        String change= Double.toString(iphone.getChange());
        if(iphone.getChange() < 0) {
            g.drawString("Change: \t", x, y);
            g.setColor(Color.red);
            x += 50;
            g.drawString("\t" + change + "%", x, y);
            x -= 50;
        }
        else{
            g.drawString("Change: \t",x,y);
            g.setColor(Color.green);
            x+=50;
            g.drawString("\t" + change + "%",x,y);
            x-=50;
        }
        g.setColor(Color.BLACK);

        y+=22;
        g.drawString("Added: \t" +iphone.getDateAdded() + "\t($" + origPrice + ")",x,y );

    }
    
    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
    	//--
    	//-- WRITE YOUR CODE HERE
    	//--
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
    public void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//test change