package TFTGAME;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TheGame {

    private static final int windowSizeWidth = 1280, windowSizeHeight = 700;
    private static final int numOneCosts = 22;
    private static final int numTwoCosts = 20;
    private static final int numThreeCosts = 17;
    private static final int numFourCosts = 10;
    private static final int numFiveCosts = 9;

    private static ArrayList<Unit> listOneCosts, listTwoCosts, listThreeCosts, listFourCosts, listFiveCosts;
    static ArrayList<Player> listPlayers;
    public Player mainPlayer;
    static int playerCount = 0;

    public JFrame gameFrame /*= new JFrame("The GAME!!!")*/;
    public JPanel TheGamePanel;
    private JLabel msgWelcome;
    private JButton action1Button;
    private JButton quitButton;
    private JTextArea countTextArea;
    private JPanel shopPanel;
    private JTextArea playerListTextArea;
    private JPanel fieldPH;
    private static LineBorder shopBorder;
    private static LineBorder benchBorder;
    JInternalFrame intrFrameBench;

    static int bal = 0;

    private Shop theShop;

    /*public TheGame(){
        this((Player) null);
    }*/
    public TheGame(){
        //createUIComponents is called
        gameFrame = new JFrame("The GAME!!!");
        gameFrame.setSize(windowSizeWidth,windowSizeHeight);
        gameFrame.setLocationRelativeTo(null);  //centers the frame
        gameFrame.setContentPane(TheGamePanel);
        //gameFrame.setUndecorated(true);  //uncomment this!
        gameFrame.getRootPane().setBorder(shopBorder); //temp border, will delete
        //gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        msgWelcome.setText("Welcome to the game, " + MainMenu.getUsername() + "!");

        //initialize gui areas
        countTextArea.setText(bal+"");

        //initialize vars
        listPlayers = new ArrayList<Player>();

        //mainPlayer = new Player(MainMenu.getUsername());
        mainPlayer = MainMenu.getPlayer();

        //initialize the players
        TheGame.addPlayer(mainPlayer);
        displayPlayerList();
        bal = 1000;

        //internal frame
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.ipady = 150;
        c.ipadx = 1000;
        c.anchor = GridBagConstraints.WEST;
        intrFrameBench = new JInternalFrame("help");
        ((BasicInternalFrameUI)intrFrameBench.getUI()).setNorthPane(null);
        intrFrameBench.setTitle("Internal frame");
        intrFrameBench.setVisible(true);
        intrFrameBench.setClosable(false);
        intrFrameBench.setResizable(false);
        intrFrameBench.setBorder(benchBorder);
        intrFrameBench.setSize( c.ipadx, c.ipady);
        intrFrameBench.setSize( 1000, 120);

        //intrFrameBench.setContentPane(mainPlayer.getBench().getPanelPlayerBench()); //uncomment this
        intrFrameBench.pack();
        fieldPH.add(intrFrameBench, c);
        //end internalFrame for bench
        System.out.println("fieldPH size: " + fieldPH.getSize());
        System.out.println("intrFrameBench size: " + intrFrameBench.getSize());

        action1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bal++;
                System.out.println(bal);
                System.out.println(this.getClass());
                updateTextArea();
                // countTextArea.setText(bal + "");
                //Window owner = getOwner();
                System.out.println("fieldPH size: " + fieldPH.getSize());
                System.out.println("intrFrameBench size: " + intrFrameBench.getContentPane().getSize());
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset the game

                //TheGamePanel.setVisible(false);
                System.out.println("Attempting to dispose this game frame");
                resetGame();
                theShop = null;
                shopPanel = null;
                gameFrame.dispose();
                //how to remove this game object?
            }
        });



    } //end constructor


    private void createUIComponents() {
        // TODO: place custom component creation code here
        shopBorder = new LineBorder (new Color(229, 166, 39), 6);
        benchBorder = new LineBorder( new Color(53, 235, 174), 6);

        theShop = new Shop( this);
        //shopPanel = new Shop(this).getPanel();
        shopPanel = theShop.getPanel();
        //shopPanel.set

        //create a border!
        shopPanel.setBorder(shopBorder);

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

    public Player getMainPlayer(){
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

    /**
     * change this to fill the unit pool with different units
     */
    public static void initializeUnits(){
        //initialize the units
        for (int i = 0; i < numOneCosts; i++){
            listOneCosts.add(new Unit("tempUnit 1c", i, 1, 100 + i, 10, 10));
            if (i %2 ==0 ) {
                listOneCosts.get(i).setImageFile("resources/images/champions/kirby.jpg");
                listOneCosts.get(i).setName("Kirby");
            }
            else{
                listOneCosts.get(i).setImageFile("resources/images/champions/king_dedede.png");
                listOneCosts.get(i).setName("King Dedede");
            }
        }
        for (int i2 = 0; i2 <numTwoCosts; i2++){
            listTwoCosts.add(new Unit("tempUnit 2c", i2, 2, 200 +i2, 20, 20));
        }
        for (int i3 = 0 ;i3< numThreeCosts; i3++){
            listThreeCosts.add(new Unit("tempUnit 3c", i3, 3, 300 +i3, 30, 30));
        }
        for (int i4 = 0; i4 < numFourCosts; i4++){
            listFourCosts.add(new Unit("tempUnit 4c", i4, 4, 400 +i4, 40, 40));
        }
        for (int i5 =0; i5 < numFiveCosts; i5++){
            listFiveCosts.add(new Unit("tempUnit 5c", i5, 5, 500 +i5, 50, 50));
        }
    }


    public static int getbal(){
        return bal;
    }

    public static void setbal(int newbal){
        bal = newbal;
    }
    public JInternalFrame getBenchFrame(){ return intrFrameBench; }

    public void updateTextArea(){
        countTextArea.setText(bal + "");
    }

    public void resetGame(){
        listPlayers.clear();
        theShop.clearShopArray();
        listOneCosts.clear();
        listTwoCosts.clear();
        listThreeCosts.clear();
        listFourCosts.clear();
        listFiveCosts.clear();
    }

}
