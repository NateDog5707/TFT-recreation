package TFTGAME;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;

import java.awt.event.*;

public class PlayerBench {
    private final int benchIconWidth = 90;
    private final int benchIconHeight = 90;

    private final int benchSize = 9;
    private Player player;
    private Unit[] bench = null;
    private JPanel panelPlayerBench;
    private JLabel test;
    private JInternalFrame internalFrame;
    private GridBagConstraints c = new GridBagConstraints();
    private final int borderWidth = 4;
    private LineBorder unitBorder = new LineBorder(new Color(0,0,0),borderWidth);
    private LineBorder hoveredBorder = new LineBorder(new Color(50,100,255), borderWidth);
    private JLabel tempPlaceholder;
    private JLabel[] tempSlotholder = new JLabel[9];
    private int debugIndex = 0;

    // for mouse dragging
    private Point startPoint;
    private Point location;


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


    public void initSlotsIcons (JInternalFrame intFrame){
        internalFrame = intFrame;
        //want to get the JFrame to add to directly
        //intFrame.setContentPane(new JPanel());
        intFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 12));

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
            unitAddListenerDraggable(tempPlaceholder);
            intFrame.add(tempPlaceholder);
        }

    }



    //debug
    public void printTempSlots(){
        for (int i =0; i < 9; i++){
            System.out.println(tempSlotholder[i]);
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
        //unitAddListenerDraggable(addingLabel);
        internalFrame.add(addingLabel, index);
        internalFrame.updateUI();
        ((BasicInternalFrameUI)internalFrame.getUI()).setNorthPane(null);
        //debug
        //System.out.println("bench index adding image to: " + index);
    }

    public JLabel unitAddListenerOnBench(JLabel theLabel){
        theLabel.addMouseListener(new MouseListener(){
            int hovered = 0;
            final int index = debugIndex;
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


    public JLabel unitAddListenerDraggable(JLabel theLabel){
        theLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = SwingUtilities.convertPoint(theLabel, e.getPoint(), theLabel.getParent());
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        theLabel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                location = SwingUtilities.convertPoint(theLabel, e.getPoint(), theLabel.getParent());
                if (theLabel.getParent().getBounds().contains(location)) {
                    Point endLoc = theLabel.getLocation();
                    endLoc.translate(location.x - startPoint.x, location.y - startPoint.y);
                    endLoc.x = Math.max(endLoc.x, 0);
                    endLoc.y = Math.max(endLoc.y, 0);
                    endLoc.x = Math.min(endLoc.x, theLabel.getParent().getWidth()- theLabel.getWidth());
                    endLoc.y = Math.min(endLoc.y, theLabel.getParent().getHeight()- theLabel.getHeight());
                    theLabel.setLocation(endLoc);
                    startPoint = location;

                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        return theLabel;


    }

}
