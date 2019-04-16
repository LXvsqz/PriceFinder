//package pricewatcher.base;


//Luis Ochoa 80508534
//Alex Vasquez 80579070

import java.util.LinkedList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.security.auth.Refreshable;
import javax.swing.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

/**
* A dialog for tracking the price of an item.
*
* @author Yoonsik Cheon
*/
@SuppressWarnings("serial")
public class Main extends JFrame{

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(400, 300);
      
    /** Special panel to display the watched item. */
    private ItemView itemView;

    private LinkedList<Item> itemHolder= new LinkedList<Item>();



    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

    /** Create a new dialog. */
    public Main() {
        super("Price Watcher");
        setSize(DEFAULT_SIZE);
        Item item1= new Item("Samsung", "url.url.url");
        Item item2= new Item("Samsung2", "url.url.url");
        Item item3= new Item("Samsung3", "url.url.url");

        itemHolder.add(item1);
        itemHolder.add(item2);
        itemHolder.add(item3);


        configureUI();
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        showMessage("Welcome!");



        //this(DEFAULT_SIZE);


    }

    /** Create a new dialog of the given screen dimension. */


  //TODO: refreshButtonCLicked
    //TODO: viewPageClicked
    /** Callback to be invoked when the refresh button is clicked. 
     * Find the current price of the watched item and display it 
     * along with a percentage price change. */
    private void refreshButtonClicked(ActionEvent event){

        //itemView.updatePrice();
        repaint();

    	showMessage("Refresh clicked!");
    }

    private void refreshButtonClicked(ActionEvent event, ItemView iV){

        iV.updatePrice(iV.getItem());
        repaint();

        showMessage("Refresh clicked!");
    }


    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. */
    private void viewPageClicked() {    	

        //ItemView.openURL(itemView.getURL());
    	showMessage("View clicked!");
    }
    private void viewPageClicked(ItemView iV) {

        ItemView.openURL(iV.getItem().getURL());
        showMessage("View clicked!");
    }
        
    /** Configure UI. */
    private void configureUI() {
        setLayout(new BorderLayout());
        JPanel control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16)); 
        add(control, BorderLayout.NORTH);
        JPanel board = new JPanel();
        board.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createEmptyBorder(10,16,0,16),
        		BorderFactory.createLineBorder(Color.GRAY)));
        board.setLayout(new GridLayout(itemHolder.size(),1));

        for (int i = 0; i <itemHolder.size(); i++) { //account for scroll

            itemView = new ItemView(itemHolder.get(i));

            itemView.setClickListener(this::viewPageClicked);
            board.add(itemView);
        }
        add(board, BorderLayout.CENTER);
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
        //JFrame menu = new JFrame("Menu");
        //menu.setVisible(true);
        //menu.setSize(600,400);
        //menu.add(control);
        //menu.add(board);
        //JMenuBar menu = new JMenuBar();
        //menu.setBounds(0,0,441,21);
       // control.add(menu,BorderLayout.NORTH);
        //Create the menu bar.
        //JMenuBar menuBar = new JMenuBar();

//Build the first menu.
        //JMenu menu = new JMenu("A Menu");
        //JMenuItem test = new JMenuItem("Test");
        //JLabel l = new JLabel("no task");
        //test.addActionListener(this::actionPerformed);
       // menu.getAccessibleContext().setAccessibleDescription(
               // "The only menu in this program that has menu items");
        //menu.add(test);
        //menuBar.add(menu);
        //control.add(menuBar, BorderLayout.NORTH);

    }
      
    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	//JButton refreshButton = new JButton("Refresh");
    	//refreshButton.setFocusPainted(false);
        //refreshButton.addActionListener(this::refreshButtonClicked);
       // panel.add(refreshButton);
        JMenuBar menubar = new JMenuBar();
        JMenu menu0 = new JMenu("App");
        JMenu menu1 = new JMenu("Item");
        JMenu menu2 = new JMenu("Sort");
        JMenu menu3 = new JMenu("Selected");

        JMenuItem item = new JMenuItem("About");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("About");

                frame.setLocationRelativeTo(panel);

                JLabel textLabel = new JLabel("Written by Luis Ochoa :)",SwingConstants.CENTER);
                textLabel.setPreferredSize(new Dimension(300, 100));
                frame.getContentPane().add(textLabel, BorderLayout.CENTER);
                //Display the window.

                frame.pack();
                frame.setVisible(true);
            }
        });
        JMenuItem item_ = new JMenuItem("Exit");
        item_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem item0 = new JMenuItem("Check Prices");
        item0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshButtonClicked(e);
            }
        });
        JMenuItem item1 = new JMenuItem("Add Item");
        JMenuItem item2 = new JMenuItem("Search");
        JMenuItem item3 = new JMenuItem("Select First");
        JMenuItem item4 = new JMenuItem("Select Last");
        JMenuItem item5 = new JMenuItem("Price");
        JMenuItem item6 = new JMenuItem("View");
        JMenuItem item7 = new JMenuItem("Item");
        JMenuItem item8 = new JMenuItem("Review");

        JCheckBox checkBox0 = new JCheckBox("Added Oldest");
        JCheckBox checkBox1 = new JCheckBox("Added Newest");
        JCheckBox checkBox2 = new JCheckBox("Name Ascending");
        JCheckBox checkBox3 = new JCheckBox("Name Descending");
        JCheckBox checkBox4 = new JCheckBox("Price Change (%)");
        JCheckBox checkBox5 = new JCheckBox("Price Low ($)");
        JCheckBox checkBox6 = new JCheckBox("Price High($)");

        menu0.add(item);
        menu0.add(item_);

        menu1.add(item0);
        menu1.add(item1);
        menu1.addSeparator();
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);
        menu1.addSeparator();
        menu1.add(menu3);
        menu3.add(item5);
        menu3.add(item6);
        menu3.add(item7);
        menu3.add(item8);

        menu2.add(checkBox0);
        menu2.add(checkBox1);
        menu2.addSeparator();
        menu2.add(checkBox2);
        menu2.add(checkBox3);
        menu2.addSeparator();
        menu2.add(checkBox4);
        menu2.add(checkBox5);
        menu2.add(checkBox6);

        menubar.add(menu0);
        menubar.add(menu1);
        menubar.add(menu2);
        panel.add(menubar,BorderLayout.EAST);
        return panel;
    }

    /** Show briefly the given string in the message bar. */
    private void showMessage(String msg) {
        msgBar.setText(msg);
        new Thread(() -> {
        	try {
				Thread.sleep(3 * 1000); // 3 seconds
			} catch (InterruptedException e) {
			}
        	if (msg.equals(msgBar.getText())) {
        		SwingUtilities.invokeLater(() -> msgBar.setText(" "));
        	}
        }).start();
    }
    private void actionPerformed(ActionEvent e){
        //String s = e.getActionCommand();
        //l.setText(s+"selected");
    }

    public static void main(String[] args) {

        new Main();

    }

}
