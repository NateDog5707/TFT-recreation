package TFTGAME;

import javax.swing.*;

public class Battlefield {

    private TheGame theGame;
    private PlayerBench benchData;
    public JFrame bfFrame;


    public Battlefield(TheGame theGame){
        benchData = theGame.mainPlayer.getBench();

        bfFrame = new JFrame("Battle");
        // can't put the set size in here b/c when referencing the original dimensions of the field window, not available upon constructing this object.
        //make another internal frame and add a timer





    }
}
