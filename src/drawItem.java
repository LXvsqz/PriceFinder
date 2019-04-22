import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

        MouseListener click;

        setItem(value);
        repaint();

        if (isSelected) {

            setBackground(list.getSelectionBackground().LIGHT_GRAY);
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;

    }
}
