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
    private final LineBorder unitBorder = new LineBorder(new Color(0,0,0),borderWidth);
    private final LineBorder hoveredBorder = new LineBorder(new Color(50,100,255), borderWidth);
    private JLabel addingLabel;
    private JLabel[] tempSlotholder = new JLabel[9];
    private int debugIndex = 0;

    // for mouse dragging
    private Point startPoint;
    private Point location;
    private final int fieldRows = 4;
    private final int fieldColumns = 7;
    private Point[][] anchorPointsField = new Point[fieldRows][fieldColumns]; //for gui
    private JLabel[][] unitField = new JLabel[fieldRows][fieldColumns]; // so the game knows
    private Point[] anchorPointsBench = new Point[9];


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

    //TODO: develop anchor points for field so units are in a respective slot.
    public void initSlotsIcons (JInternalFrame intFrame, TheGame game){
        internalFrame = intFrame;
        //intFrame.setLayout(null); // called in TheGame on frame setup
        for (int i = 0; i < benchSize ; i++) {
            addingLabel = new JLabel();
            tempSlotholder[i] = addingLabel;
            addingLabel.setSize(90, 90);
            //TODO. solidfy location setting.
            System.out.println("Help");
            addingLabel.setLocation(i * (game.getBenchWidth()/9 -1) +5, game.getBenchHeight() - (benchIconHeight + 20));
            //addingLabel.setLocation(i * 100 + 5, game.getBenchHeight() - (benchIconHeight + 20));
            ImageIcon unitImageIcon = new ImageIcon("resources/images/champions/king_dedede.png");
            Image unitImage = unitImageIcon.getImage().getScaledInstance(benchIconWidth, benchIconHeight, Image.SCALE_DEFAULT);
            unitImageIcon = new ImageIcon(unitImage);
            addingLabel.setIcon(unitImageIcon);
            addingLabel.setBorder(unitBorder);

            unitAddListenerOnBench(addingLabel);
            unitAddListenerDraggable(addingLabel);
            intFrame.add(addingLabel);
            System.out.println("playerbench creation: " + intFrame.getComponentCount());
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


    //TODO update this for new no-layout bench
    public void updateBenchIcons(JInternalFrame intFrame, TheGame game, int index){

        addingLabel = new JLabel();
        ImageIcon unitImageIcon = new ImageIcon(player.getUnitsOnBench()[index].getImageFile());
        Image unitImage = unitImageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        unitImageIcon = new ImageIcon(unitImage);
        addingLabel.setIcon(unitImageIcon);
        addingLabel.setBorder(unitBorder);

        if (tempSlotholder[index] != null){
            intFrame.remove(tempSlotholder[index]);
        }
        // i need to set location
        System.out.println("Help");
        addingLabel.setLocation(index * (game.getBenchWidth()/9 -1) +5, game.getBenchHeight() - (benchIconHeight + 20));
        //addingLabel.setLocation(index * 100 + 5, game.getBenchHeight() - (benchIconHeight + 20));
        addingLabel.setSize(90,90);


        tempSlotholder[index] = addingLabel;
        unitAddListenerOnBench(addingLabel);
        unitAddListenerDraggable(addingLabel);
        intFrame.add(addingLabel);
        intFrame.updateUI();
        ((BasicInternalFrameUI)intFrame.getUI()).setNorthPane(null);

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
