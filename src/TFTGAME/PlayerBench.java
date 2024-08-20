package TFTGAME;

import javax.swing.*;
import java.awt.*;
<<<<<<< Updated upstream
=======
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
>>>>>>> Stashed changes

public class PlayerBench {

    private final int benchSize = 9;
    private Player player;
    private Unit[] bench = null;
    private JPanel panelPlayerBench;
    private JLabel test;
    private GridBagConstraints c = new GridBagConstraints();

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

<<<<<<< Updated upstream
    /**
     * called from main menu.
     */
    public void initSlotIcons(){
        JLabel tempPlaceholder;
        panelPlayerBench.removeAll();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        panelPlayerBench.updateUI();
        c.gridy = 0;
        //c.ipadx = (((int) (panelPlayerBench.getSize().getWidth())) /9)-25; //this can be changed?
        System.out.println(c.ipadx);
        c.insets = new Insets(0,2,0,2);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        for (int i = 0;i < benchSize; i++){
            c.gridx = i;
            tempPlaceholder = new JLabel();
            //tempPlaceholder.setIcon(new ImageIcon("resources/images/champions/kirby.jpg"));
            tempPlaceholder.setHorizontalAlignment(SwingConstants.LEFT);
            panelPlayerBench.add(tempPlaceholder, c);
        }

        panelPlayerBench.updateUI();
=======
    public void initSlotsIcons (JInternalFrame intFrame){
        internalFrame = intFrame;
        //want to get the JFrame to add to directly
        //intFrame.setContentPane(new JPanel());
        intFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 15));

        for (int i = 0; i < 9; i++) {
            //c.gridx = i;
            tempPlaceholder = new JLabel();
            tempSlotholder[i] = tempPlaceholder;
            tempPlaceholder.setSize(i, i);
            //tempPlaceholder.setIcon(new ImageIcon("resources/images/champions/kirby.jpg"));
            ImageIcon unitImageIcon = new ImageIcon("resources/images/champions/king_dedede.png");
            Image unitImage = unitImageIcon.getImage().getScaledInstance(benchIconWidth, benchIconHeight, Image.SCALE_DEFAULT);
            unitImageIcon = new ImageIcon(unitImage);
            tempPlaceholder.setIcon(unitImageIcon);
            tempPlaceholder.setBorder(unitBorder);

            unitAddListenerOnBench(tempPlaceholder);

            intFrame.add(tempPlaceholder);
        }

    }

    //debug
    public void printTempSlots(){
        for (int i =0; i < 9; i++){
            System.out.println(tempSlotholder[i]);
        }
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
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

=======


    //could use
    public void updateBenchIcons(int index){
        JLabel addingLabel = new JLabel();
        if (bench[index] != null){
            ImageIcon unitImageIcon = new ImageIcon(player.getUnitsOnBench()[index].getImageFile());
            Image unitImage = unitImageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
            unitImageIcon = new ImageIcon(unitImage);
            addingLabel.setIcon(unitImageIcon);
            addingLabel.setBorder(unitBorder);
        }
        else{
            addingLabel.setIcon(null);
        }


        internalFrame.remove(tempSlotholder[index]);
        tempSlotholder[index] =addingLabel;
        unitAddListenerOnBench(addingLabel);
        internalFrame.add(addingLabel, index);
        internalFrame.updateUI();
        ((BasicInternalFrameUI)internalFrame.getUI()).setNorthPane(null);
        //debug
        //System.out.println("bench index adding image to: " + index);
    }

    public JLabel unitAddListenerOnBench(JLabel theLabel){
        theLabel.addMouseListener(new MouseListener(){
            int hovered = 0;
            int index = debugIndex;
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)){
                    System.out.println("Left mouse " + index);
                }
                else if (SwingUtilities.isRightMouseButton(e)){
                    System.out.println("Right mouse " + index);
                }
                System.out.println(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                if (hovered == 0){
                    hovered = 1;
                    ((JLabel)e.getComponent()).setBorder(hoveredBorder);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (hovered == 1){
                    hovered = 0;
                    ((JLabel)e.getComponent()).setBorder(unitBorder);
                }
            }
        });
        debugIndex += 1;
        return theLabel;
    }

>>>>>>> Stashed changes
}
