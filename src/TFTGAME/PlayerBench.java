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
    private JLabel[] unitBench = new JLabel[benchSize];
    private Point[] anchorPointsBench = new Point[9];
    //below is for unit grabbing
    private Unit grabbedUnit, swapUnit;
    private JLabel swapLabel;
    private int[] swapData = new int[6]; // [0, 3] are bench/field flags (0 = bench, 1 = field) , [1,2,4,5] is x,y indeces


    public PlayerBench(){
        this((Player) null);
    }

    public PlayerBench(Player player){
        if (player != null) {
            this.player = player;
            this.bench = player.getUnitsOnBench();
            this.field = new Unit[fieldRows][fieldColumns];
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
        unitBench[index] = addingLabel;

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
        final int[] notDraggedYet = {1};

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
                //check if in bench or field to pick between
                if (startPoint.getY() > (fieldFrameHeight - heightOfBench)){
                    //in bench
                    for (int x = 0 ; x < benchSize;x++){
                        //get the anchor poitns and get a 90x90 square aroudn them (Cuz the size of icons)
                        if ((benchIconWidth/2) >= Math.abs(anchorPointsBench[x].getX() - startPoint.getX())){
                            //System.out.println("[mousePressed]: " + startPoint.getX() + " " + startPoint.getY());
                            grabbedUnit = bench[x];
                            swapData[0] = 0;
                            swapData[1] = x;
                            swapData[2] = 0;
                            break;
                        }
                    }
                }
                else{
                    //in field
                    int foundPointFlag = 0;
                    for (int y = 0; y < fieldRows; y++){
                        if((benchIconHeight/2) >= Math.abs(anchorPointsField[y][0].getY() - startPoint.getY())){
                            for(int x = 0; x < fieldColumns; x++){
                                if ((benchIconWidth/2) >= Math.abs(anchorPointsField[y][x].getX() - startPoint.getX() )){
                                    //System.out.println("[mousePressed]: " + startPoint.getX() + " " + startPoint.getY());
                                    //NULL rn b/c
                                    grabbedUnit = field[y][x];
                                    swapData[0] = 1;
                                    swapData[1] = x;
                                    swapData[2] = y;
                                    foundPointFlag = 1;
                                    break;
                                }
                            }
                            if (foundPointFlag == 1){
                                break;
                            }
                        }
                    }
                }
                System.out.println("begin: " + swapData[0] + " " + swapData[1] + " " + swapData[2]);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //check if near a point.
                //System.out.println("Released loc: " + location);
                int goodSwap = 0;
                if (notDraggedYet[0] == 1){
                    location = startPoint;
                }
                //bench points
                if (location.getY() > (fieldFrameHeight - heightOfBench)){
                    System.out.println("Released Bench");
                    //move to bench
                    for (int i =0; i < benchSize; i++){
                        //if the distance between anchor point and release position is close enough, snap to position
                        if (Math.abs(location.getX() - anchorPointsBench[i].getX()) <= ((anchorPointThreshW) * ((float) 7/9))){
                            //snap to the point
                            theLabel.setLocation( (int) anchorPointsBench[i].getX() - (benchIconWidth/2), (fieldFrameHeight - benchIconHeight)-20);

                            //get the swap data and call swap
                            swapData[3] = 0;
                            swapData[4] = i;
                            swapData[5] = 0;
                            if (bench[i] != null){
                                swapUnit = bench[i];
                                swapLabel = unitBench[i];
                            }
                            else{
                                swapUnit = null;
                            }

                            //call swap
                            goodSwap = 1;
                            if (swapData[0] != swapData[3] || swapData[1] != swapData[4] || swapData[2] != swapData[5]){
                                swapUnits(theLabel);
                            }
                            break;
                        }
                    }
                    if (goodSwap != 1){
                        //somehow not in the range to snap to a point, return back to original spot. try!
                        theLabel.setLocation((int) anchorPointsBench[swapData[1]].getX() - (benchIconHeight/2), (int)anchorPointsBench[swapData[1]].getY() - (benchIconHeight/2));
                        System.out.println("Drag Failed");
                    }

                }
                //field points
                else{
                    System.out.println("Released Field");
                    int foundPointFlag = 0;
                    int dragFailed = 0;
                    for (int i = 0 ; i < fieldRows;i++){
                        // if within the range of a row
                        if (Math.abs(location.getY() - anchorPointsField[i][0].getY()) <= anchorPointThreshH){
                            for (int j = 0; j < fieldColumns; j++){
                                if (Math.abs(location.getX() - anchorPointsField[0][j].getX()) <= anchorPointThreshW ){
                                    //snap to the point i,j
                                    //System.out.println(anchorPointsField[i][j]);
                                    theLabel.setLocation( (int) anchorPointsField[i][j].getX() - (benchIconHeight/2), (int) anchorPointsField[i][j].getY() - (benchIconWidth/2));
                                    //after the label itself moves, we should also show that the game variables know it moves in unitField
                                    swapData[3] = 1;
                                    swapData[4] = j;
                                    swapData[5] = i;
                                    foundPointFlag = 1;
                                    //printUnitMap(); //for debug
                                    //return;
                                    if (field[i][j] != null){
                                        swapUnit = field[i][j];
                                        swapLabel = unitField[i][j];
                                    }else{
                                        swapUnit = null;
                                    }
                                    goodSwap = 1;
                                    if (swapData[0] != swapData[3] || swapData[1] != swapData[4] || swapData[2] != swapData[5]){
                                        swapUnits(theLabel);
                                    }
                                    swapUnit = null;
                                    break;
                                }
                            }
                            if(foundPointFlag == 1 || dragFailed == 1){
                                break;
                            }
                        }
                    }
                    if (goodSwap != 1){
                        if (swapData[0] == 1){
                            theLabel.setLocation((int) anchorPointsField[swapData[2]][swapData[1]].getX() - (benchIconHeight/2), (int)anchorPointsField[swapData[2]][swapData[1]].getY() - (benchIconHeight/2));
                        }
                        else{
                            theLabel.setLocation((int) anchorPointsBench[swapData[1]].getX() - (benchIconHeight/2), (int)anchorPointsBench[swapData[1]].getY() - (benchIconHeight/2));
                        }
                        System.out.println("Drag Failed");
                    }
                }
                System.out.println("end:" + swapData[3] + " " + swapData[4] + " " + swapData[5]);
                //printUnitMap();
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
                notDraggedYet[0] = 0;
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
        for (int i = 0; i < fieldRows; i++){
            for (int j = 0; j < fieldColumns; j++){
                if (field[i][j] != null){
                    if (field[i][j].getName().equals("Kirby")) {
                        map += "K ";
                    }
                    //map += "O ";
                    else if(field[i][j].getName().equals("King Dedede")){
                        map += "D ";
                    }
                    else{
                        map += "O ";
                    }
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
                if (bench[k].getName().equals("Kirby")) {
                    map += "K ";
                }
                //map += "O ";
                else if(bench[k].getName().equals("King Dedede")){
                    map += "D ";
                }
                else{
                    map += "O ";
                }
            }
            else{
                map += "X ";
            }
        }
        System.out.println(map);
    }

    public void swapUnits(JLabel theLabel){
        //swapData, bench, and field
        Unit temp;
        //put the original unit to the new space
        if (swapData[3] == 0) {
            bench[swapData[4]] = grabbedUnit;
            unitBench[swapData[4]] = theLabel;
        }else{
            field[swapData[5]][swapData[4]] = grabbedUnit;
            unitField[swapData[5]][swapData[4]] = theLabel;
        }

        //if there is a swap unit, move it to the original spot
        if (swapUnit != null){
            System.out.println("Swap: pair");
            printSwapData();
            if (swapData[0] == 0) {
                bench[swapData[1]] = swapUnit;
                unitBench[swapData[1]] = swapLabel;
                swapLabel.setLocation((int) anchorPointsBench[swapData[1]].getX() - (benchIconHeight/2), (int)anchorPointsBench[swapData[1]].getY() - (benchIconHeight/2));
            }
            else{
                field[swapData[2]][swapData[1]] = swapUnit;
                unitField[swapData[2]][swapData[1]] = swapLabel;
                swapLabel.setLocation((int) anchorPointsField[swapData[2]][swapData[1]].getX() - (benchIconHeight/2), (int)anchorPointsField[swapData[2]][swapData[1]].getY() - (benchIconHeight/2));
            }
        }
        //else no 2nd unit to swap back, just clear the original space
        else{
            System.out.println("Swap: single");
            printSwapData();
            if (swapData[0] == 0) {
                bench[swapData[1]] = null;
            }
            else{
                field[swapData[2]][swapData[1]] = null;
            }

        }
        printUnitMap();
        swapUnit = null;

//        System.out.println("swapped");
        //do i need to reset swapData maybe
        /*for (int i = 0; i < 6; i++){
            swapData[i] = 0;
        }*/
    }

    public void printSwapData(){
        System.out.println("" + swapData[0] + ", " + swapData[1] +", " + swapData[2] + ", " + swapData[3] +  ", " + swapData[4] + ", " + swapData[5]);
    }
}
