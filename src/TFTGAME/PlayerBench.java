package TFTGAME;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PlayerBench {

    private final int benchSize = 9;
    private Player player;
    private Unit[] bench = null;
    private JPanel panelPlayerBench;
    private JLabel test;
    private GridBagConstraints c = new GridBagConstraints();
    //private LineBorder unitBorder = new Border( )

    public PlayerBench(){
        this((Player) null);
    }

    public PlayerBench(Player player){
        if (player != null){
            this.player = player;
            this.bench = player.getUnitsOnBench();
            test.setText("Bench");
            //System.out.println("PLAYER BENCH DEBUG");
        }
        else{
            //debug
            test.setText("Not Good");
        }
    }

    /**
     * called from main menu.
     */
    public void initSlotIcons(){
        JPanel insidePanel = new JPanel(new BorderLayout());

        /*GridBagLayout layout = new GridBagLayout();
        panelPlayerBench.setLayout(layout);*/
        panelPlayerBench.removeAll();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        panelPlayerBench.updateUI();
        c.gridy = 0;
        //c.ipadx = 5;
        //c.ipadx = (((int) (panelPlayerBench.getSize().getWidth())) /9)-25; //this can be changed?
        c.insets = new Insets(0,2,0,2);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.anchor = GridBagConstraints.WEST;

        JLabel tempPlaceholder;
        //testing stuff
        /*c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 9;
        tempPlaceholder = new JLabel();
        tempPlaceholder.setIcon(new ImageIcon("resources/images/champions/kirby.jpg"));
        panelPlayerBench.add(tempPlaceholder, c);
        */
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 0.111;
        for (int i = 0; i < 9; i++){
            c.gridx = i;
            tempPlaceholder = new JLabel();
            //tempPlaceholder.setIcon(new ImageIcon("resources/images/champions/kirby.jpg"));
            if (i < 5){
                tempPlaceholder.setIcon(new ImageIcon("resources/images/champions/kirby.jpg"));
            }else{
                tempPlaceholder.setIcon(new ImageIcon("resources/images/champions/transparent.jpg"));
                //tempPlaceholder.setBorder();
            }
            tempPlaceholder.setHorizontalAlignment(SwingConstants.LEFT);
            panelPlayerBench.add(tempPlaceholder, c);
            //panelPlayerBench.add(tempPlaceholder, BorderLayout.WEST);
        }

        panelPlayerBench.updateUI();
    }

    public void initSlotsTemp (JInternalFrame intFrame){
        //want to get the JFrame to add to directly
        JLabel tempPlaceholder;
        intFrame.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 15));

        for (int i = 0; i < 9; i++) {
            //c.gridx = i;
            tempPlaceholder = new JLabel();
            //tempPlaceholder.setIcon(new ImageIcon("resources/images/champions/kirby.jpg"));
            ImageIcon unitImageIcon = new ImageIcon("resources/images/champions/king_dedede.png");
            Image unitImage = unitImageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
            unitImageIcon = new ImageIcon(unitImage);
            tempPlaceholder.setIcon(unitImageIcon);

            intFrame.add(tempPlaceholder);
        }
    }

    public JPanel getPanelPlayerBench(){
        return panelPlayerBench;
    }

    public void clearBench(){
        for (int i =0 ;i < benchSize; i++){
            if (bench[i] != null){
                bench[i] = null;
            }
        }
    }

    //ok so starting here i need to figure out how to make draggable stuff. gonna watch youtube
    //update/ add or delete image icons
    public void updateBenchIcons(){
        //go through the 9 bench slots for the player
        //debug
        /*for (int i =0; i < benchSize; i ++){
            if (bench[i] != null){
                System.out.println(i + " exists");
            }
            else{
                System.out.println(i + " doesn't exist");
            }
        }*/
        //preliminary test
        if (bench[0] != null){
            c.gridx = 0;
            c.gridy = 0;
            panelPlayerBench.remove(c.gridx);
            panelPlayerBench.add(new JLabel("lll"), c, c.gridx);
            panelPlayerBench.updateUI();
            c.gridx = 8;
            panelPlayerBench.remove(c.gridx);
            panelPlayerBench.add(new JLabel("no"), c, c.gridx);

            /// getComponentAt() could also help
        }
        //for loop to update the bench displays. but do i need to check all slots if they are changed or just one?

        panelPlayerBench.updateUI();
    }


    //TODO can't use panel, must use the JInternalFrame
    public void updateBenchIcons(int index){
        c.gridx = index;
        JLabel addingLabel = new JLabel();
        ImageIcon unitImageIcon = new ImageIcon(player.getUnitsOnBench()[index].getImageFile());
        Image unitImage = unitImageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        unitImageIcon = new ImageIcon(unitImage);
        addingLabel.setIcon(unitImageIcon);
        panelPlayerBench.add(addingLabel, c, index);
        panelPlayerBench.remove(index+1);
        panelPlayerBench.updateUI();
        //debug
        System.out.println("bench index adding image to: " + index);
    }

}
