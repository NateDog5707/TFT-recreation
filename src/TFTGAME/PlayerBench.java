package TFTGAME;

import javax.swing.*;

public class PlayerBench {

    Player player;
    Unit[] bench = null;
    private JPanel panelPlayerBench;
    private JLabel test;

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

    public JPanel getPanelPlayerBench(){
        return panelPlayerBench;
    }

    //ok so starting here i need to figure out how to make draggable stuff. gonna watch youtube
    //update/ add or delete image icons
    public void updateIcons(){
        //go through the 9 bench slots for the player
        //debug
        for (int i =0; i < 9; i ++){
            if (bench[i] != null){
                System.out.println(i + " exists");
            }
            else{
                System.out.println(i + " doesn't exist");
            }
        }
    }

}
