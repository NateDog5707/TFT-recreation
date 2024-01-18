package TFTGAME;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




public class TheGame {
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

    static int i = 0;


    public TheGame(){
        //createUIComponents is called
        gameFrame = new JFrame("The Game Menu");
        gameFrame.setSize(1600,900);
        gameFrame.setLocation(0,0);
        gameFrame.setContentPane(TheGamePanel);

        TestMsg.setText("Welcome to the game, " + MainMenu.getUsername() + "!");



        //initialize gui areas
        countTextArea.setText(i+"");


        //initialize vars
        listPlayers = new ArrayList<Player>();

        /* //moved to initialize shop function
        listOneCosts = new ArrayList<Unit>();
        listTwoCosts = new ArrayList<Unit>();
        listThreeCosts = new ArrayList<Unit>();
        listFourCosts = new ArrayList<Unit>();
        listFiveCosts = new ArrayList<Unit>();
        */


        mainPlayer = new Player(MainMenu.getUsername());

        //initialize the players
        TheGame.addPlayer(mainPlayer);
        displayPlayerList();





        for( Unit currUnit: listOneCosts){
            System.out.println(currUnit);
        }





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
    public void displayPlayerList(){
        String playerListText = "";
        for (Player player : listPlayers){
            playerListText += player.toString();
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
            listTwoCosts.add(new Unit("tempUnit 2c", 2, 200 +i, 20, 20));
        }
        for (int i3 = 0 ;i3< numThreeCosts; i3++){
            listThreeCosts.add(new Unit("tempUnit 3c", 3, 300 +i, 30, 30));
        }
        for (int i4 = 0; i4 < numFourCosts; i4++){
            listFourCosts.add(new Unit("tempUnit 4c", 4, 400 +i, 40, 40));
        }
        for (int i5 =0; i5 < numFiveCosts; i5++){
            listFiveCosts.add(new Unit("tempUnit 5c", 5, 500 +i, 50, 50));
        }
    }
}
