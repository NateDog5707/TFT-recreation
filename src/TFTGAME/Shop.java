package TFTGAME;

import javax.swing.*;

public class Shop {
    private JLabel shopLabel;
    private JButton buyButton1;
    private JButton buyButton2;
    private JButton buyButton3;
    private JButton buyButton4;
    private JButton buyButton5;
    private JLabel shopImage1;
    private JLabel shopImage2;
    private JLabel shopImage3;
    private JLabel shopImage4;
    private JLabel shopImage5;
    private JPanel panel;

    public Shop(){




    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        shopImage1 = new JLabel(new ImageIcon("resources/images/champions/kirby.jpg"));
        shopImage2 = new JLabel(new ImageIcon("resources/images/champions/kirby.jpg"));
        shopImage3 = new JLabel(new ImageIcon("resources/images/champions/kirby.jpg"));
        shopImage4 = new JLabel(new ImageIcon("resources/images/champions/kirby.jpg"));
        shopImage5 = new JLabel(new ImageIcon("resources/images/champions/kirby.jpg"));
    }

    public JPanel getPanel(){
        return panel;
    }

}
