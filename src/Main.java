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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JLabel;

import java.net.URI;

import java.io.FileWriter;
import java.util.*;

import org.json.*;






/**
 * A dialog for tracking the price of an item.
 * @author Alex Vasquez 80579070
 * @author Luis Ochoa 80508534
 * @author Jacob Padilla 80617758
 * @version HW3
 *
 *
 */
@SuppressWarnings("serial")
public class Main extends JFrame{





    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(400, 600);

    /** Special panel to display the watched item. */
    private ItemView itemView;
    DefaultListModel itemList = new DefaultListModel();
    JList itemHolder;


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

    /**
     * Refreshes item price that is currently selected; if none are selected, does nothing
     * @see  new item price
     *
     *
    */

    private void itemRefresh(){
        if(!itemHolder.isSelectionEmpty()) {
            int index = itemHolder.getSelectedIndex();
            Item item = (Item)itemList.getElementAt(index);
            item.checkCurrentPrice(item.getURL());
            repaint();
        }
    }

    /**
     * Refreshes prices of all items
     * @see refreshAll items refreshed
     */
    private void refreshAll(){
        Item temp;
        for(int i = 0; i < itemList.getSize();i++){
            temp = (Item)itemList.getElementAt(i);
            temp.checkCurrentPrice(temp.getURL());
        }
        write();
        read();
        repaint();
    }

    /**
     * Opens web browser with item website of selected item; if none does nothing
     */
    private void openWebsite(){
        if(!itemHolder.isSelectionEmpty()){
            int index = itemHolder.getSelectedIndex();
            Item item = (Item)itemList.getElementAt(index);
            itemView.openURL(item.getURL());

        }
    }

    /**
     * Selects the last item in the list
     */
    private void selectLast(){
        itemHolder.setSelectedIndex(itemHolder.getLastVisibleIndex());

    }

    /**
     * Selects the first item in the list
     */
    private void selectFirst(){
        itemHolder.setSelectedIndex(itemHolder.getFirstVisibleIndex());
    }


//****************************************************************//

    /**
     * Adds item to List
     * By prompting user to input a short description of item as well as a valid URL to check price from
     */
    private void addItem(){

        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        Object [] message = {
                "Enter Name : ",field1,
                "Enter URL : ", field2,
        };
        int option = JOptionPane.showConfirmDialog(this,message,"Enter all your values",JOptionPane.OK_CANCEL_OPTION);
        if(option==JOptionPane.OK_OPTION){
            String name = field1.getText();
            String url = field2.getText();
            //URL website = null;
           // url = urlChecker(url);
            url = validHost(url);

            Item newItem= new Item(name,url);
            itemList.addElement(newItem);
        }
      write();
        read();
    }


    /**
     *
     * @param url
     * @return url checks whether a url is malformed
     */
    private  String urlChecker(String url){

        URL website = null;
        while(website == null){
            try {
                website = new URL(url);
            }catch(MalformedURLException e){
                url = JOptionPane.showInputDialog(this,"URL is malformed, Try Again\nPast input : "+ url );
            }}

        return url;
    }

    /**
     *
     * @param url
     * @return url
     * Checks if the url is part of the domains supported by the the application
     */
    public String validHost(String url){
        url = urlChecker(url);
        String host = getHostName(url);

        HashSet<String> domains = new HashSet<>();
        domains.add("walmart.com");
        domains.add("elpaso.craigslist.org");
        domains.add("frys.com");
        while(!domains.contains(host)){

            url = JOptionPane.showInputDialog(this,"URL is not part of supported domains, Try Again\nPast input : "+ url );
            url = urlChecker(url);
            host = getHostName(url);
        }
        return url;
    }

    /**
     *
     * @param url
     * @return hostname
     * Used to strip url to find the domain
     */
    public  static String getHostName(String url) {
        URI uri;

        try {
            uri = new URI(url);
        }catch(URISyntaxException e){
            return null;
        }
        String hostname = uri.getHost();
        // to provide faultproof result, check if not null then return only hostname, without www.
        if (hostname != null) {
            return hostname.startsWith("www.") ? hostname.substring(4) : hostname;
        }

        return hostname;
    }

    /**
     *
     * @return data
     * used to get an array iteration of the JList in order to sort it
     */
    public Item[] toArray(){
        ListModel listmodel = itemHolder.getModel();
        int n = listmodel.getSize();
        Item [] data = new Item[n];
        for(int i = 0; i < n ; i++){
            data[i] = (Item)listmodel.getElementAt(i);
        }
        return data;
    }

    /**
     * sorts based on percent change
     */
    public  void sortPercentChange(){

        Item [] data = toArray();

        Arrays.sort(data, Comparator.comparing(Item::getChange));

        itemHolder.setListData(data);

    }
    /**
     * sorts based on price (ascending)
     */
    public  void sortPriceLow(){

        Item [] data = toArray();

        Arrays.sort(data, Comparator.comparing(Item::getCurrentPrice));

        itemHolder.setListData(data);

    }
    /**
     * sorts based on price(descending)
     */
    public  void sortPriceHigh(){

        Item [] data = toArray();

        Arrays.sort(data, Comparator.comparing(Item::getCurrentPrice));
        reverseArray(data);
        itemHolder.setListData(data);
    }
    /**
     * sorts based on date added(oldest)
     */
    public  void sortOldest(){

        Item [] data = toArray();

        Arrays.sort(data, Comparator.comparing(Item::getDateAdded));

        itemHolder.setListData(data);

    }
    /**
     * sorts based on date added(newest)
     */
    public  void sortNewest(){

        Item [] data = toArray();

        Arrays.sort(data, Comparator.comparing(Item::getDateAdded));
        reverseArray(data);
        itemHolder.setListData(data);
    }
    /**
     * sorts based on name (ascending)
     */
    public  void sortName(){

        Item [] data = toArray();
        Arrays.sort(data, Comparator.comparing(Item::getName));
        itemHolder.setListData(data);
    }
    /**
     * sorts based on name (descending)
     */
    public void sortNameBackwards(){
        Item [] data = toArray();
        Arrays.sort(data, Comparator.comparing(Item::getName));

        reverseArray(data);

        itemHolder.setListData(data);
    }
    /**
     * helper method used to reverse an array
     * used to reverse already sorted arrays
     */
    public void reverseArray(Item [] data){
        Item t;
        int n = data.length;
        for (int i = 0; i < n / 2; i++) {// reverses array
            t = data[i];
            data[i] = data[n - i - 1];
            data[n - i - 1] = t;
        }
    }
    /**
     * removes selected item if there is a selected item
     */
    private void removeItem(){

        if(!itemHolder.isSelectionEmpty()){
            int index = itemHolder.getSelectedIndex();
            itemList.removeElementAt(index);
        }
        write();
        read();

    }

    /** Configure UI.
     * Manages the display of the application
     *
     * */
    private void configureUI(){
        //Luis
        setLayout(new BorderLayout());
        JMenuBar control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16));
        setJMenuBar(control);

        //Toolbar Jacob
        JPanel toolbar = tools();
        toolbar.setBorder(BorderFactory.createEmptyBorder(10,0,0,16));
        add(toolbar,BorderLayout.NORTH);

        JPanel lower = View_all_display();
        lower.setBorder(BorderFactory.createEmptyBorder(20,16,20,16));
        add(lower);



        //Board (ALEX)
        read();
        itemHolder = new JList(itemList);
        itemHolder.setCellRenderer(new drawItem());
        itemHolder.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        itemHolder.setVisibleRowCount(3);

        scroller= new JScrollPane(itemHolder,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        itemHolder.setFixedCellHeight(160);

        itemHolder.setFixedCellWidth(350);

        itemHolder.addMouseListener( new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {

                if ( SwingUtilities.isRightMouseButton(e) )
                {
                    JPopupMenu popo = MenuPop();
                    popo.show(itemHolder,e.getX(),e.getY());


                }
            }
        });
        JPanel board = new JPanel();
        board.add(scroller);


        //TODO: Add frame around the board. (user experience)

        this.add(scroller);
        this.setVisible(true);

        JPanel bottom = new JPanel();
        bottom.setBorder(BorderFactory.createEmptyBorder(10,0,0,16));
        add(bottom,BorderLayout.SOUTH);
    }
    private JPanel View_all_display(){
        JPanel control = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("View all: ");
        control.add(label);
        return control;
    }

    /**
     * Opens a new window that displays further information regarding PriceFinder
     */
    private void about(){

        JFrame frame = new JFrame("About");

        frame.setLocationRelativeTo(this);

        JLabel textLabel = new JLabel("Price Finder Application :)",SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        //Display the window.

        frame.pack();
        frame.setVisible(true);
    }
    /** Create a control panel consisting a JMenubar with multiple functions
     * @return  JMenuBar with menu functionality
     * */

    private JMenuBar makeControlPanel() {

        JMenuBar menubar = new JMenuBar();
        JMenu menu0 = new JMenu("App");
        JMenu menu1 = new JMenu("Item");
        JMenu menu2 = new JMenu("Sort");
        JMenu menu3 = new JMenu("Selected");

        JMenuItem item = new JMenuItem("About",createImageIcon("/images/envelope.png"));
        item.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
        });

        JMenuItem item_ = new JMenuItem("Exit",createImageIcon("/images/cancel.png"));
        item_.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        item_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem item0 = new JMenuItem("Check Prices",createImageIcon("/images/reload.png"));
        item0.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        item0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshAll();
            }
        });

        JMenuItem item1 = new JMenuItem("Add Item",createImageIcon("/images/plus.png"));
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        item1.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));


        JMenuItem item2 = new JMenuItem("Search",createImageIcon("/images/magnifying-glass.png"));
        item2.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item3 = new JMenuItem("Select First",createImageIcon("/images/next.png"));
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFirst();
            }
        });

        item3.setAccelerator(KeyStroke.getKeyStroke('T', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item4 = new JMenuItem("Select Last",createImageIcon("/images/previous.png"));
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectLast();
            }
        });

        item4.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item5 = new JMenuItem("Price",createImageIcon("/images/list.png"));
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemRefresh();
            }
        });

        item5.setAccelerator(KeyStroke.getKeyStroke('U', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item6 = new JMenuItem("View",createImageIcon("/images/eye.png"));
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebsite();
            }
        });

        item6.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        JMenuItem item7 = new JMenuItem("Edit",createImageIcon("/images/edit.png"));
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    itemEditor();
            }
        });
        item7.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));

        JMenuItem item8 = new JMenuItem("Review",createImageIcon("/images/shopping-cart.png"));
        item8.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));

        JMenuItem item9 = new JMenuItem("Copy Name");

        JMenuItem item10 = new JMenuItem("Copy URL");

        JMenuItem item11 = new JMenuItem("Copy Item");

        JCheckBox checkBox0 = new JCheckBox("Added Oldest");
        checkBox0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortOldest();
            }
        });
        JCheckBox checkBox1 = new JCheckBox("Added Newest");
        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortNewest();
            }
        });
        JCheckBox checkBox2 = new JCheckBox("Name Ascending");
        checkBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortName();
            }
        });
        JCheckBox checkBox3 = new JCheckBox("Name Descending");
        checkBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortNameBackwards();
            }
        });
        JCheckBox checkBox4 = new JCheckBox("Price Change (%)");
        checkBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortPercentChange();
            }
        });
        JCheckBox checkBox5 = new JCheckBox("Price Low ($)");
        checkBox5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortPriceLow();
            }
        });
        JCheckBox checkBox6 = new JCheckBox("Price High($)");
        checkBox6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortPriceHigh();
            }
        });

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

        return menubar;
    }

    /////////////START OF FILE WRITING READING//////////


    /**
     * checks if there is a data file existing if not creates one
     */
    public  void checkExist(){
        if(!new File("data.txt").exists()){
            try{
                FileWriter file= new FileWriter("data.txt");
                file.close();
                }catch(IOException ex){
                System.out.println("");
            }

        }

    }

    /**
     * writes on a file the item currently being watched
     */
    public  void write(){
             checkExist();

            JSONArray finta = new JSONArray();
            for(int i = 0 ; i < itemList.getSize();i++){
                JSONObject temp = ((Item)(itemList.getElementAt(i))).toJson();
                finta.put(temp);
            }
            try {

                FileWriter write = new FileWriter("data.txt");
                PrintWriter pw = new PrintWriter(write,true);
                pw.write(finta.toString());
                pw.close();
            }catch(IOException e){
                e.printStackTrace();

            }
    }

    /**
     * reads from file if there is items currently being watched
     */
    public void read()  {

        checkExist();
        try {
            JSONTokener obj;
            try {
                 obj = new JSONTokener(new FileReader("data.txt"));
                JSONArray tem = new JSONArray(obj);
                itemList.clear();
                for(int i = 0; i < tem.length();i++){
                    Item temp = new Item();
                    temp.fromJson((JSONObject)tem.get(i));

                    itemList.addElement(temp);
                }
            }catch (JSONException e){

            }
        }catch (FileNotFoundException e){

        }

    }

    ///////////////////////////////////////


    /**
     * Creates a toolbar with many functions
     * @return JPanel with toolbar
     */
    private JPanel tools() {
        JPanel control = new JPanel(new FlowLayout(FlowLayout.LEADING));

        JToolBar toolBar = new JToolBar("Price watch");

        //Buttons
        JButton b1 = new JButton(createImageIcon("/images/blueCheck.png"));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemRefresh();
            }
        });
        b1.setToolTipText("Check the price");
        b1.setFocusPainted(false);
        toolBar.add(b1);

        JButton b2 = new JButton(createImageIcon("/images/addBlue.png"));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        b2.setToolTipText("Add to the price finder");
        b2.setFocusPainted(false);
        toolBar.add(b2);

        JButton b3 = new JButton(createImageIcon("/images/search.png"));
        b3.setToolTipText("Search");
        b3.setFocusPainted(false);
        toolBar.add(b3);

        JButton b4 = new JButton(createImageIcon("/images/blueLast.png"));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFirst();
            }
        });
        b4.setToolTipText("First item");
        b4.setFocusPainted(false);
        toolBar.add(b4);

        JButton b5 = new JButton(createImageIcon("/images/blueFirst.png"));
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectLast();
            }
        });
        b5.setToolTipText("Last item");
        b5.setFocusPainted(false);
        toolBar.add(b5);

        toolBar.addSeparator();

        JButton menu_1= new JButton(createImageIcon("/images/CheckGreen.png"));
        menu_1.setToolTipText("Item Price of Selected Item");
        menu_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemRefresh();
            }
        });
        menu_1.setFocusPainted(false);
        toolBar.add(menu_1);

        JButton menu_2= new JButton(createImageIcon("/images/launch.png"));
        menu_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebsite();
            }
        });
        menu_2.setToolTipText("Launch Item Web-page");
        menu_2.setFocusPainted(false);
        toolBar.add(menu_2);

        JButton menu_3= new JButton(createImageIcon("/images/EditGreen.png"));
        menu_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemEditor();
            }
        });
        menu_3.setToolTipText("Edit Selected Item");
        menu_3.setFocusPainted(false);
        toolBar.add(menu_3);

        JButton menu_4= new JButton(createImageIcon("/images/minus.png"));
        menu_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }
        });
        menu_4.setToolTipText("Delete Selected Item");
        menu_4.setFocusPainted(false);
        toolBar.add(menu_4);

        toolBar.addSeparator();

        JButton info = new JButton(createImageIcon("/images/Questionblue.png"));
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
        });
        info.setToolTipText("App Information");
        info.setFocusPainted(false);
        toolBar.add(info);


        control.add(toolBar, BorderLayout.NORTH);

        return control;

    }

    /**
     * Allows user to edit currently selected item by prompting user to input new/updated item information
     */
    private void itemEditor(){
        if(!itemHolder.isSelectionEmpty()) {
            int index = itemHolder.getSelectedIndex();
            Item item = (Item) itemList.getElementAt(index);
            String originalURL= item.getURL();
            String originalName= item.getName();
            JTextField field1 = new JTextField();
            JTextField field2 = new JTextField();
            Object[] message = {
                    "Enter new Name : ", field1,
                    "Enter new URL : ", field2,
            };
            int option = JOptionPane.showConfirmDialog(this, message, "Edit Item", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String name = field1.getText();
                String url = field2.getText();
                if(url.isEmpty())
                    url= originalURL;
                if(originalName.isEmpty())
                    name= originalName;
                item.setURL(url);
                item.setDescription(name);
                write();
            }
        }
    }

    /**
     * Creates a JPopupMenu that gives user options when right clicking item
     * @return JPopupMenu
     */
    private JPopupMenu MenuPop(){
        JPopupMenu mp = new JPopupMenu();
        JMenuItem mp1 = new JMenuItem(createImageIcon("/images/bluecheck.jpg"));
        mp1.setToolTipText("Check price");
        mp1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemRefresh();
            }
        });
        JMenuItem mp2 = new JMenuItem(createImageIcon("/images/Web.png"));
        mp2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebsite();
            }
        });
        mp2.setToolTipText("Open Website");
        JMenuItem mp3 = new JMenuItem(createImageIcon("/images/35-512.png"));
        mp3.setToolTipText("edit Item");
        mp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemEditor();
            }
        });

        JMenuItem mp4 = new JMenuItem(createImageIcon("/images/remove.jpg"));
        mp4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }
        });
        mp4.setToolTipText("remove item");

        mp.add(mp1);
        mp.add(mp2);
        mp.add(mp3);
        mp.add(mp4);

        return mp;
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

    /**
     * Creates an icon that fits desired size specs from an image file
     * @param filename path of file
     * @return ImageIcon
     */
    private ImageIcon createImageIcon(String filename) {
        URL imageUrl = getClass().getResource( filename);

        if (imageUrl != null) {
            return  new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        new Main();


    }

}