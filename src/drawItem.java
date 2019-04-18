import javax.swing.*;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;


public class drawItem extends ItemView implements ListCellRenderer<Item>{


    public drawItem(){
        setOpaque(true);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item value, int index, boolean isSelected, boolean cellHasFocus) {



        setItem(value);
        repaint();

        return this;

    }
}
