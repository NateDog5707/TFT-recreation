package TFTGAME;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Battlefield {

    private TheGame theGame;
    private PlayerBench benchData;
    public JFrame bfFrame;
    public JPanel fieldPanel;


    private final double heximgRatio = (double) 191/165;
    private Dimension hexagonDim = new Dimension(120, (int) ((120.0 * heximgRatio))); // this height is giving 0
    private Dimension boardRowOffset = new Dimension((int) hexagonDim.getWidth()/2, (int) hexagonDim.getHeight());
    private Dimension frameDimension;

    private int bfBorderSpace = 10; //width and height, deduct from the main battlefield dimensions

    public Battlefield(TheGame theGame){
        benchData = theGame.mainPlayer.getBench();
        this.theGame = theGame;
        bfFrame = new JFrame("Battle");
        //bfFrame.setLayout(null);
        // can't put the set size in here b/c when referencing the original dimensions of the field window, not available upon constructing this object.
        //make another internal frame and add a timer


    }

    public void setupField(int bfFrameWidth, int bfFrameHeight){
        frameDimension = new Dimension(bfFrameWidth,bfFrameHeight);
        //theGame.getBenchDimensions();
        fieldPanel = createArena();
        createHexes(fieldPanel);
    }
    public JPanel createArena(){
        JPanel newField = new JPanel();
        newField.setVisible(true);
        newField.setSize((int) frameDimension.getWidth()-500,(int) frameDimension.getHeight()-500);
        newField.setSize((int) frameDimension.getWidth()-100,(int) frameDimension.getHeight()-100);
        newField.setBackground(new Color(255,255,255));
        newField.setBorder(new LineBorder(new Color(0,0,0), 2));
        newField.setLayout(null);
        bfFrame.add(newField);
        return newField;
    }
    public void createHexes(JPanel basePanel){
        JLabel hex = new JLabel();

        ImageIcon hexImage = new ImageIcon("./resources/images/UI/hexagon-tile-vert.png");
        Image tempImage = hexImage.getImage().getScaledInstance((int)hexagonDim.getWidth(),(int)hexagonDim.getHeight(), Image.SCALE_DEFAULT);
        hexImage = new ImageIcon(tempImage);

        //idea, might need to use JLayeredPane to stack the hexes right next to each other to make it look nice!
        for (int i = 0; i < benchData.fieldRows; i++){
            for (int j = 0; j < benchData.fieldColumns; j++){
                hex = new JLabel();
                hex.setIcon(hexImage);
                hex.setVisible(true);
                hex.setSize((int)hexagonDim.width,(int) hexagonDim.height);
                if (i % 2 == 0) {
                    hex.setLocation((int)((j) * (hexagonDim.getWidth())), (int) ((i/2) * (hexagonDim.getHeight())));

                }else {
                    hex.setLocation((int)((j-1) * (hexagonDim.getWidth()) + hexagonDim.getWidth()/2), (int) ((i/2) * (hexagonDim.getHeight()) + hexagonDim.getHeight()/2));
                }

                //hex.setText("Hex");
                basePanel.add(hex);

            }
        }

    }
    public void showBattleField(){

    }
}
