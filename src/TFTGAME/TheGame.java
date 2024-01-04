package TFTGAME;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TheGame {

    ArrayList<Unit> listOneCosts, listTwoCosts, listThreeCosts, listFourCosts, listFiveCosts;
    ArrayList<Player> listPlayers;
    int playerCount = 0;

    public JFrame gameFrame;
    public JPanel TheGamePanel;
    private JLabel TestMsg;
    private JButton action1Button;
    private JButton quitButton;
    private JTextArea countTextArea;
    private JPanel shopPanel;
    private JTextArea playerListTextArea;

    static int i = 0;


    public TheGame(){
        gameFrame = new JFrame("The Game Menu");
        gameFrame.setSize(800,600);
        gameFrame.setLocation(300,200);
        gameFrame.setContentPane(TheGamePanel);


        //initialize areas
        countTextArea.setText(i+"");


        listPlayers = new ArrayList<Player>();
        listOneCosts = new ArrayList<Unit>();




        action1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i++;
                System.out.println(i);
                System.out.println(this.getClass());
                countTextArea.setText(i + "");
                //Window owner = getOwner();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //TheGamePanel.setVisible(false);
                System.out.println("Attempting to dispose this game frame");
                gameFrame.dispose();
            }
        });



    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        shopPanel = new Shop().getPanel();

    }

    //gui functions


    //logistic functions
    public void addPlayer(Player player){
        listPlayers.add(player);
        playerCount++;
        player.setPlayerNum(playerCount);
    }



}
