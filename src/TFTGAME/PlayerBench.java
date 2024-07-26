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
            test.setText("Not Good");
        }
    }

    public JPanel getPanelPlayerBench(){
        return panelPlayerBench;
    }

    //ok so starting here i need to figure out how to make draggable stuff. gonna watch youtube


}
