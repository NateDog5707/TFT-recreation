package TFTGAME;

import javax.swing.*;

public class UnitLabel extends JLabel {

    JLabel theLabel;
    JInternalFrame intFrame;
    Unit theUnit;
    int onBench = 0; // 0 = bench, 1 = field
    int coordX;
    int coordY;


    public UnitLabel(){
        coordX = 0;
        coordY = 0;
    }
    public UnitLabel(JInternalFrame intFrame) {
        this.intFrame = intFrame;
    }

    public UnitLabel(JLabel theLabel, JInternalFrame intFrame, int x, int y){
        this.theLabel = theLabel;
        this.intFrame = intFrame;
        this.coordX = x;
        this.coordY = y;
        onBench = 0;
    }

    public UnitLabel(JInternalFrame intFrame, Unit theUnit, int x, int y){
        this.intFrame = intFrame;
        this.theUnit = theUnit;
        this.coordX = x;
        this.coordY = y;
        onBench = 0;
    }

    public void setCoords(int x, int y) {
        this.coordX = x;
        this.coordY = y;
    }

    public void setOnBench(int val){
        onBench = val;
    }
    public void setBenchAndCoords(int val, int x, int y){
        System.out.println("x and y: " + x + ", " + y);
        setOnBench(val);
        setCoords(x,y);
    }

    public Unit getTheUnit(){return theUnit;}
}
