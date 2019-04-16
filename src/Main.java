
//Luis Ochoa 80508534
//Alex Vasquez 80579070

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
import java.io.*;
import java.net.URL;
import java.util.LinkedList;

/**
 * A dialog for tracking the price of an item.
 *
 * @author Yoonsik Cheon
 */
@SuppressWarnings("serial")
public class Main extends JFrame{

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(400, 400);

    /** Special panel to display the watched item. */
    private ItemView itemView;
    DefaultListModel itemList = new DefaultListModel();
    JList itemHolder;

    Item item1= new Item("Samsung Galaxy", "https://www.samsung.com/us/mobile/galaxy-s10/?cid=sem-mktg-pfs-mob-22019-22509&gclid=Cj0KCQjw19DlBRCSARIsAOnfRejgqcLTCgyV41Wg4_f-UNYVifG_0yix7br2SFXYgpDwoAznwMEnnIEaAuByEALw_wcB&gclsrc=aw.ds");
    Item item2= new Item("Iphone X", "https://www.bestbuy.com/site/iphone/iphone-x/pcmcat1505326434742.c?id=pcmcat1505326434742");
    Item item3= new Item("Google Pixel", "https://store.google.com/us/product/pixel_3?hl=en-US");

    JScrollPane scroller= new JScrollPane();


    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

    /** Create a new dialog. */


    /** Create a new dialog of the given screen dimension. */
    public Main() {
        super("Price Watcher");
        setSize(DEFAULT_SIZE);

        configureUI();
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        showMessage("Welcome!");

        //this(DEFAULT_SIZE);
    }
    //TODO: refreshButtonCLicked
    //TODO: viewPageClicked
    /** Callback to be invoked when the refresh button is clicked.
     * Find the current price of the watched item and display it
     * along with a percentage price change. */



    //******************* NEEDS FIXING ***************************//

    private void refreshButtonClicked(ActionEvent event){

        //itemView.updatePrice();
        repaint();

        showMessage("Refresh clicked!");                                //need to fix with current code
    }


    /*

    private void refreshButtonClicked(ActionEvent event, ItemView iV){
        for(Item view :itemHolder) {
            iV.updatePrice(view);

        }
        repaint();
        showMessage("Refresh clicked!");
    }
    */
//****************************************************************//

    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. */


    //********************* NEEDS FIXING *************************//
    /*
    private void viewPageClicked() {

        //ItemView.openURL(itemView.getURL());
        showMessage("View clicked!");
    }
    private void viewPageClicked(ItemView iV) {                 //Not sure if we need this?

        ItemView.openURL(iV.getItem().getURL());
        showMessage("View clicked!");
    }
    */
    //***********************************************************//


    private void addItem(String name, String url){
        Item newItem= new Item(name,url);
        itemList.addElement(newItem);
    }


    private void AddButtonClicked(ActionEvent event){

        itemView.establish();
        repaint();

        showMessage("Added item");
    }
    /** Configure UI. */
    private void configureUI() {
        //Luis
        setLayout(new BorderLayout());
        JMenuBar control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16));
        setJMenuBar(control);

        //Toolbar

        JPanel toolbar = tools();
        toolbar.setBorder(BorderFactory.createEmptyBorder(10,0,0,16));
        add(toolbar,BorderLayout.NORTH);

        //Board (ALEX)
        Item item1= new Item("Samsung Galaxy", "https://www.samsung.com/us/mobile/galaxy-s10/?cid=sem-mktg-pfs-mob-22019-22509&gclid=Cj0KCQjw19DlBRCSARIsAOnfRejgqcLTCgyV41Wg4_f-UNYVifG_0yix7br2SFXYgpDwoAznwMEnnIEaAuByEALw_wcB&gclsrc=aw.ds");
        Item item2= new Item("Iphone X", "https://www.bestbuy.com/site/iphone/iphone-x/pcmcat1505326434742.c?id=pcmcat1505326434742");
        Item item3= new Item("Iphone X", "https://www.bestbuy.com/site/iphone/iphone-x/pcmcat1505326434742.c?id=pcmcat1505326434742");


        Item[]displayItem= {item1,item2,item3};



        addItem("iphoneColor", "iphone.com");

        /*
        for (int i = 0; i < 1 ; i++){
            itemList.addElement(displayItem);
        }
*/

        itemHolder= new JList(itemList);

        itemHolder.setVisibleRowCount(3);

        scroller= new JScrollPane(itemHolder,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        itemHolder.setFixedCellHeight(100);

        itemHolder.setFixedCellWidth(350);


        JPanel board = new JPanel();

        board.add(scroller);

        this.add(scroller);
        this.setVisible(true);

    }

    /** Create a control panel consisting of a refresh button. */
    private JMenuBar makeControlPanel() {
        //JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        //JButton refreshButton = new JButton("Refresh");
        //refreshButton.setFocusPainted(false);
        //refreshButton.addActionListener(this::refreshButtonClicked);
        // panel.add(refreshButton);
        JMenuBar menubar = new JMenuBar();
        JMenu menu0 = new JMenu("App");
        JMenu menu1 = new JMenu("Item");
        JMenu menu2 = new JMenu("Sort");
        JMenu menu3 = new JMenu("Selected");

        JMenuItem item = new JMenuItem("About",createImageIcon("envelope.png"));
        item.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("About");

                frame.setLocationRelativeTo(menubar);

                JLabel textLabel = new JLabel("Written by Luis Ochoa :)",SwingConstants.CENTER);
                textLabel.setPreferredSize(new Dimension(300, 100));
                frame.getContentPane().add(textLabel, BorderLayout.CENTER);
                //Display the window.

                frame.pack();
                frame.setVisible(true);
            }
        });
        JMenuItem item_ = new JMenuItem("Exit",createImageIcon("cancel.png"));
        item_.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        item_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem item0 = new JMenuItem("Check Prices",createImageIcon("reload.png"));
        item0.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        item0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // refreshButtonClicked(e,itemView);
            }
        });
        JMenuItem item1 = new JMenuItem("Add Item",createImageIcon("plus.png"));

        item1.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item2 = new JMenuItem("Search",createImageIcon("magnifying-glass.png"));
        item2.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item3 = new JMenuItem("Select First",createImageIcon("next.png"));
        item3.setAccelerator(KeyStroke.getKeyStroke('T', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item4 = new JMenuItem("Select Last",createImageIcon("previous.png"));
        item4.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item5 = new JMenuItem("Price",createImageIcon("list.png"));
        item5.setAccelerator(KeyStroke.getKeyStroke('U', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item6 = new JMenuItem("View",createImageIcon("eye.png"));
        item6.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item7 = new JMenuItem("Item",createImageIcon("edit.png"));
        item7.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item8 = new JMenuItem("Review",createImageIcon("shopping-cart.png"));
        item8.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item9 = new JMenuItem("Copy Name");
        JMenuItem item10 = new JMenuItem("Copy URL");
        JMenuItem item11 = new JMenuItem("Copy Item");
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
        menu3.addSeparator();
        menu3.add(item9);
        menu3.add(item10);
        menu3.add(item11);

        ButtonGroup group0 = new ButtonGroup();
        group0.add(checkBox0);
        group0.add(checkBox1);


        group0.add(checkBox2);
        group0.add(checkBox3);


        group0.add(checkBox4);
        group0.add(checkBox5);
        group0.add(checkBox6);

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
        //panel.add(menubar,BorderLayout.CENTER);
        //JToolBar jtoolbar = new JToolBar();
        //JButton button = new JButton(createImageIcon("envelope.png"));
        // button.setToolTipText("Testing");
        //button.setFocusPainted(false);
        //jtoolbar.add(button);
        //JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        // panel2.add(jtoolbar);
        //panel.add(panel2);
        return menubar;
    }
    private JPanel tools() {
        JPanel control = new JPanel(new FlowLayout(FlowLayout.LEADING));

        JToolBar toolBar = new JToolBar("Pricewatch");
        JButton b1 = new JButton("Check");
        b1.addActionListener(this::refreshButtonClicked);
        b1.setToolTipText("Check the price");
        b1.setFocusPainted(false);
        toolBar.add(b1);

        JButton b2 = new JButton("Add");
        b2.addActionListener(this::AddButtonClicked);
        b2.setToolTipText("Add to the pricefinder");
        b2.setFocusPainted(false);
        toolBar.add(b2);

        control.add(toolBar, BorderLayout.NORTH);

        return control;

    }
    /** Show briefly the given string in the message bar. */
    private void showMessage(String msg) {
        msgBar.setText(msg);
        new Thread(() -> {
            try {
                Thread.sleep(3 * 1000); // 3 seconds
            } catch (InterruptedException e) {
                System.out.print("");
            }
            if (msg.equals(msgBar.getText())) {
                SwingUtilities.invokeLater(() -> msgBar.setText(" "));
            }
        }).start();
    }
    private ImageIcon createImageIcon(String filename) {
        URL imageUrl = getClass().getResource( filename);

        if (imageUrl != null) {
            return  new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        }
        return null;
    }




    public static void main(String[] args) {
        new Main();

    }

}