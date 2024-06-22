package TFTGAME;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




public class TheGame {

    private static final int windowSizeWidth = 1200, windowSizeHeight = 700;
    private static final int numOneCosts = 22;
    private static final int numTwoCosts = 20;
    private static final int numThreeCosts = 17;
    private static final int numFourCosts = 10;
    private static final int numFiveCosts = 9;

    private static ArrayList<Unit> listOneCosts, listTwoCosts, listThreeCosts, listFourCosts, listFiveCosts;
    static ArrayList<Player> listPlayers;
    static Player mainPlayer;
    static int playerCount = 0;

    public JFrame gameFrame;
    public JPanel TheGamePanel;
    private JLabel TestMsg;
    private JButton action1Button;
    private JButton quitButton;
    private JTextArea countTextArea;
    private JPanel shopPanel;
    private JTextArea playerListTextArea;

    static int bal = 0;


    public TheGame(){
        //createUIComponents is called
        gameFrame = new JFrame("The GAME!!!");
        gameFrame.setSize(windowSizeWidth,windowSizeHeight);
        gameFrame.setLocation(0,0);
        gameFrame.setContentPane(TheGamePanel);

        TestMsg.setText("Welcome to the game, " + MainMenu.getUsername() + "!");

        //initialize gui areas
        countTextArea.setText(bal+"");

        //initialize vars
        listPlayers = new ArrayList<Player>();

        mainPlayer = new Player(MainMenu.getUsername());

        //initialize the players
        TheGame.addPlayer(mainPlayer);
        displayPlayerList();


        //debug
        for( Unit currUnit: listOneCosts){
            System.out.println(currUnit);
        }





        action1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bal++;
                System.out.println(bal);
                System.out.println(this.getClass());
                updateTextArea();
                // countTextArea.setText(bal + "");
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



    } //end constructor


    private void createUIComponents() {
        // TODO: place custom component creation code here
        shopPanel = new Shop(this).getPanel();

    }

    //gui functions
    public void displayPlayerList(){
        String playerListText = "";
        for (Player player : listPlayers){
            playerListText += player.toString() + "\n";
        }
        playerListTextArea.setText(playerListText);
    }


    //logistic functions
    static public void addPlayer(Player player){
        listPlayers.add(player);
        playerCount++;
        player.setPlayerNum(playerCount);
    }

    public static Player getMainPlayer(){
        return mainPlayer;
    }

    public static ArrayList<Unit> getListOneCosts(){
        return listOneCosts;
    }
    public static ArrayList<Unit> getListTwoCosts(){
        return listTwoCosts;
    }
    public static ArrayList<Unit> getListThreeCosts(){
        return listThreeCosts;
    }
    public static ArrayList<Unit> getListFourCosts(){
        return listFourCosts;
    }
    public static ArrayList<Unit> getListFiveCosts(){
        return listFiveCosts;
    }


    public static void initializeShop(){
        listOneCosts = new ArrayList<Unit>();
        listTwoCosts = new ArrayList<Unit>();
        listThreeCosts = new ArrayList<Unit>();
        listFourCosts = new ArrayList<Unit>();
        listFiveCosts = new ArrayList<Unit>();
        TheGame.initializeUnits();
    }

    public static void initializeUnits(){
        //initialize the units
        for (int i = 0; i < numOneCosts; i++){
            listOneCosts.add(new Unit("tempUnit 1c", 1, 100 + i, 10, 10));
            if (i %2 ==0 ) {
                listOneCosts.get(i).setImageFile("resources/images/champions/kirby.jpg");
            }
            else{
                listOneCosts.get(i).setImageFile("resources/images/champions/king_dedede.png");
            }
        }
        for (int i2 = 0; i2 <numTwoCosts; i2++){
            listTwoCosts.add(new Unit("tempUnit 2c", 2, 200 +i2, 20, 20));
        }
        for (int i3 = 0 ;i3< numThreeCosts; i3++){
            listThreeCosts.add(new Unit("tempUnit 3c", 3, 300 +i3, 30, 30));
        }
        for (int i4 = 0; i4 < numFourCosts; i4++){
            listFourCosts.add(new Unit("tempUnit 4c", 4, 400 +i4, 40, 40));
        }
        for (int i5 =0; i5 < numFiveCosts; i5++){
            listFiveCosts.add(new Unit("tempUnit 5c", 5, 500 +i5, 50, 50));
        }
    }


    public static int getbal(){
        return bal;
    }

    public static void setbal(int newbal){
        bal = newbal;
    }


    public void updateTextArea(){
        countTextArea.setText(bal + "");
    }

}
