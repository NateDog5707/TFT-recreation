package TFTGAME;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.lang.Math;

import java.awt.event.*;

public class PlayerBench {
    private final int benchIconWidth = 90;
    private final int benchIconHeight = 90;
    private final int benchSize = 9;
    private Player player;
    private Unit[] bench = null;
    private Unit[][] field = null;
    private JPanel panelPlayerBench;
    private JLabel test;
    private JInternalFrame internalFrame;
    private GridBagConstraints c = new GridBagConstraints();
    private final int borderWidth = 4;
    private final LineBorder unitBorder = new LineBorder(new Color(0,0,0),borderWidth);
    private final LineBorder hoveredBorder = new LineBorder(new Color(50,100,255), borderWidth);
    private JLabel addingLabel;
    private int debugIndex = 0;

    // for mouse dragging
    private Point startPoint;
    private Point location;
    private final int fieldRows = 4;
    private final int fieldColumns = 7;
    private final int heightOfBench = 125;
    private int fieldFrameHeight;
    private final int anchorPointThreshW = 68;
    private final int anchorPointThreshH = 50;
    private Point[][] anchorPointsField = new Point[fieldRows][fieldColumns]; //for gui
    private JLabel[][] unitField = new JLabel[fieldRows][fieldColumns]; // so the game knows
    private Point[] anchorPointsBench = new Point[9];


    public PlayerBench(){
        this((Player) null);
    }

    public PlayerBench(Player player){
        if (player != null) {
            this.player = player;
            this.bench = player.getUnitsOnBench();
        }
    }

    //TODO: develop anchor points for field so units are in a respective slot.
    public void initSlotsIcons (JInternalFrame intFrame, TheGame game){
        fieldFrameHeight = game.getBenchHeight();
        internalFrame = intFrame;
        //intFrame.setLayout(null); // called in TheGame on frame setup

        //create a line that divides field and bench
        JLabel divider = new JLabel();
        divider.setBorder(unitBorder);
        divider.setSize(game.getBenchWidth(),6);
        divider.setLocation(0,game.getBenchHeight()-heightOfBench);
        divider.setVisible(true);
        internalFrame.add(divider);


//        for (int i = 0; i < benchSize ; i++) {
//            addingLabel = new JLabel();
//            addingLabel.setSize(90, 90);
//            //TODO. solidfy location setting.
//            addingLabel.setLocation(i * (game.getBenchWidth()/9 -1) +5, game.getBenchHeight() - (benchIconHeight + 20));
//            //addingLabel.setLocation(i * 100 + 5, game.getBenchHeight() - (benchIconHeight + 20));
//            ImageIcon unitImageIcon = new ImageIcon("resources/images/champions/king_dedede.png");
//            Image unitImage = unitImageIcon.getImage().getScaledInstance(benchIconWidth, benchIconHeight, Image.SCALE_DEFAULT);
//            unitImageIcon = new ImageIcon(unitImage);
//            addingLabel.setIcon(unitImageIcon);
//            addingLabel.setBorder(unitBorder);
//
//            //unitAddListenerOnBench(addingLabel);
//            unitAddListenerDraggable(addingLabel);
//            intFrame.add(addingLabel);
//            System.out.println("playerbench creation: " + intFrame.getComponentCount());
//        }


    }

    public void initAnchorPoints(JInternalFrame intFrame, TheGame game){
        JLabel anchorPointSee;
        JLabel divider;
        //gonna find dividing slots of 4 rows 7 columns first
        {

            for (int x = 1; x < fieldRows ;x++){
                divider = new JLabel();
                divider.setVisible(true);
                divider.setSize(game.getBenchWidth(), 2);
                divider.setBorder(unitBorder);
                divider.setLocation(0, (x) * (game.getBenchHeight()-heightOfBench)/(fieldRows ));
                System.out.println("Differnce in Height: " + (game.getBenchHeight()-heightOfBench)/(fieldRows ));
                internalFrame.add(divider);
            }
            for (int y = 1; y < fieldColumns ; y++){
                divider = new JLabel();
                divider.setVisible(true);
                divider.setSize(2, game.getBenchHeight()-heightOfBench);
                divider.setBorder(unitBorder);
                divider.setLocation((y) * (game.getBenchWidth())/(fieldColumns),0 );
                System.out.println("Differnce in Width: " + (game.getBenchWidth())/(fieldColumns));
                internalFrame.add(divider);

            }
            //create bench slot dividers
            for (int z = 1; z < benchSize; z++){
                divider = new JLabel();
                divider.setVisible(true);
                divider.setSize(2, heightOfBench);
                divider.setBorder(unitBorder);
                divider.setLocation(z * ((game.getBenchWidth() - 12)/benchSize) -3,game.getBenchHeight()-heightOfBench);
                internalFrame.add(divider);
            }



        }
        // vvv create the points
        for (int x = 0; x < fieldRows; x++){
            for (int y = 0; y < fieldColumns; y++){
                /*anchorPointSee = new JLabel("Yeah", SwingConstants.CENTER);
                unitField[x][y] = anchorPointSee;
                anchorPointSee.setBorder(unitBorder);
                unitAddListenerOnBench(anchorPointSee);*/
                //location
                anchorPointsField[x][y] = new Point(y * game.getBenchWidth()/fieldColumns + ((game.getBenchWidth()/fieldColumns)/2), x * ((game.getBenchHeight() - heightOfBench)/fieldRows) + ((game.getBenchHeight() - heightOfBench)/fieldRows)/ 2); //x and y are reversed
                //anchorPointSee.setSize((game.getBenchWidth())/(fieldColumns) ,(game.getBenchHeight()-heightOfBench)/(fieldRows ));
                /*anchorPointSee.setSize(50,50);
                anchorPointSee.setLocation(anchorPointsField[x][y]);
                intFrame.add(anchorPointSee);*/
            }
        }
        //create the points FOR BENCH
        for (int x = 0; x < benchSize; x++){
            anchorPointsBench[x] = new Point ( x * (game.getBenchWidth()/benchSize -1) - 2 + ((game.getBenchWidth()/benchSize)/2), game.getBenchHeight()-(heightOfBench/2));
            /*anchorPointSee = new JLabel("Yeah");
            anchorPointSee.setBorder(unitBorder);
            anchorPointSee.setSize(50,50);
            anchorPointSee.setLocation(anchorPointsBench[x]);
            intFrame.add(anchorPointSee);*/
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


    //this gets called when a new unit is bought.
    public void updateBenchIcons(JInternalFrame intFrame, TheGame game, int index){

        addingLabel = new JLabel();
        //ImageIcon unitImageIcon = new ImageIcon(player.getUnitsOnBench()[index].getImageFile());
        ImageIcon unitImageIcon = new ImageIcon(bench[index].getImageFile());
        Image unitImage = unitImageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        unitImageIcon = new ImageIcon(unitImage);
        addingLabel.setIcon(unitImageIcon);
        addingLabel.setBorder(unitBorder);

        // i need to set location
        addingLabel.setLocation(index * (game.getBenchWidth()/9 -1) +5, game.getBenchHeight() - (benchIconHeight + 20));
        addingLabel.setSize(90,90);

        //unitAddListenerOnBench(addingLabel);
        unitAddListenerDraggable(addingLabel);
        intFrame.add(addingLabel);
        intFrame.updateUI();
        ((BasicInternalFrameUI)intFrame.getUI()).setNorthPane(null); //just need to call this again b/c it resets

    }

/*    public JLabel unitAddListenerOnBench(JLabel theLabel){
        theLabel.addMouseListener(new MouseListener(){
            int hovered = 0;
            final int index = debugIndex;
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                *//*if (SwingUtilities.isLeftMouseButton(e)){
                    System.out.println("Left mouse " + index);
                }
                else if (SwingUtilities.isRightMouseButton(e)){
                    System.out.println("Right mouse " + index);
                }
                System.out.println(e);*//*
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
    }*/


    public JLabel unitAddListenerDraggable(JLabel theLabel){
        Unit grabbedUnit, swapUnit;
        Point grabbedLoc, swapLoc; //x,y coord in field.

        theLabel.addMouseListener(new MouseListener() {
            int hovered = 0;
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = SwingUtilities.convertPoint(theLabel, e.getPoint(), theLabel.getParent());
                //System.out.println("Pressed loc: " + startPoint);
                //i want to get the x,y coord of the field of the unit.
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //check if near a point.
                //System.out.println("Released loc: " + location);

                //bench points
                if (location.getY() > (fieldFrameHeight - heightOfBench)){
                    //move to bench
                    for (int i =0; i < benchSize; i++){
                        System.out.println(anchorPointsBench[i].getX());
                        if (Math.abs(location.getX() - anchorPointsBench[i].getX()) <= ((anchorPointThreshW) * ((float) 7/9))){
                            //snap to the point
                            theLabel.setLocation( (int) anchorPointsBench[i].getX() - (benchIconWidth/2), (fieldFrameHeight - benchIconHeight)-20);
                        }
                    }

                }
                //field points
                else{
                    for (int i = 0 ; i < fieldRows;i++){
                        // if within the range of a row
                        if (Math.abs(location.getY() - anchorPointsField[i][0].getY()) <= anchorPointThreshH){
                            for (int j = 0; j < fieldColumns; j++){

                                if (Math.abs(location.getX() - anchorPointsField[0][j].getX()) <= anchorPointThreshW ){
                                    //snap to the point i,j
                                    //System.out.println(anchorPointsField[i][j]);
                                    theLabel.setLocation( (int) anchorPointsField[i][j].getX() - (benchIconHeight/2), (int) anchorPointsField[i][j].getY() - (benchIconWidth/2));

                                    //after the label itself moves, we should also show that the game variables know it moves in unitField

                                    printUnitMap();
                                    return;
                                }
                            }
                        }
                    }
                }
                printUnitMap();
            }

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

    //debug to show all units present
    public void printUnitMap(){
        String map = "";
        for (int i = 0; i < fieldColumns; i++){
            for (int j = 0; j < fieldRows; j++){
                if (unitField[j][i] != null){
                    map += "O ";
                }
                else{
                    map += "X ";
                }
            }
            map += "\n";
        }
        //print bench
        map += "----------------\n";
        for (int k =0; k < benchSize;k++){
            if (bench[k] != null){
                map += "O ";
            }
            else{
                map += "X ";
            }
        }
        System.out.println("dawdwad");
        System.out.println(map);
    }

}
