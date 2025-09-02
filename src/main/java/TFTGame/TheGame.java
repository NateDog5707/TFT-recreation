package main.java.TFTGame;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class TheGame {

    private static final int windowSizeWidth = 1280, windowSizeHeight = 800;
    private static final int numOneCosts = 22;
    private static final int numTwoCosts = 20;
    private static final int numThreeCosts = 17;
    private static final int numFourCosts = 10;
    private static final int numFiveCosts = 9;

    private static ArrayList<Unit> listOneCosts, listTwoCosts, listThreeCosts, listFourCosts, listFiveCosts;
    private static ArrayList<Player> listPlayers;
    public Player mainPlayer;
    public PlayerBench playerBench;
    private static int playerCount = 0;
    private Battlefield battlefield;
    public JFrame gameFrame /*= new JFrame("The GAME!!!")*/;
    public JPanel TheGamePanel;
    private JLabel msgWelcome;
    private JButton action1Button;
    private JButton quitButton;
    private JTextArea countTextArea;
    private JPanel shopPanel;
    private JTextArea playerListTextArea;
    private JPanel fieldPH;
    private JTextArea playerExpTA;
    private JPanel playerLevelImage;
    private JButton fightButton;
    private static final LineBorder shopBorder = new LineBorder (new Color(229, 166, 39), 6);
    private static final LineBorder benchBorder = new LineBorder( new Color(53, 235, 174), 6);
    private JInternalFrame intrFrameBench;
    private static final int benchWidth = 900;
    private static final int benchHeight = 450;

    private static int bal = 0;

    private Shop theShop;
    
    int bfSetupFlag = 1;
    
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
        playerBench = mainPlayer.getBench();

        battlefield = new Battlefield(this);

        //initialize the players
        TheGame.addPlayer(mainPlayer);
        displayPlayerList();
        bal = 1000;

        //internal frame field and bench
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.ipadx = benchWidth;
        c.ipady = benchHeight;
        c.gridy = 0;
        //c.anchor = GridBagConstraints.SOUTH;
        intrFrameBench = new JInternalFrame("help");
        ((BasicInternalFrameUI)intrFrameBench.getUI()).setNorthPane(null);
        intrFrameBench.setTitle("Internal frame");
        intrFrameBench.setVisible(true);
        intrFrameBench.setClosable(false);
        intrFrameBench.setResizable(false);
        intrFrameBench.setBorder(benchBorder);
        intrFrameBench.setSize( benchWidth, benchHeight);
        intrFrameBench.setLayout(null);

        intrFrameBench.pack();
        fieldPH.add(intrFrameBench, c);

        action1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bal++;
                System.out.println(bal);
                System.out.println(this.getClass());
                updateTextArea();
                // countTextArea.setText(bal + "");
                //Window owner = getOwner();
                //System.out.println("fieldPH size: " + fieldPH.getSize());
                //System.out.println("intrFrameBench size: " + intrFrameBench.getContentPane().getSize());
                System.out.println("Shop Height: " + shopPanel.getHeight());

            }
        });

        /*Timer timer = new Timer();
        TimerTask endfight = new TimerTask(){
            @Override
            public void run(){
                System.out.println("Ending fight");
                battlefield.bfFrame.setVisible(false);

            }
        };*/
        //int fightTime = 30000; // 30 seconds
        int fightTime = 2000; //testing
        fightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Create a new window pop up that opens a wide gameboard that starts a real-time fight between units!
                //create a new java file and give it UI right??
                int bfFrameWidth = getBenchWidth();
                int bfFrameHeight = (int) (getBenchHeight() * 1.4);
                battlefield.bfFrame.setSize(bfFrameWidth, bfFrameHeight);
                battlefield.bfFrame.setVisible(true);
                if (bfSetupFlag == 1) {
                    battlefield.setupField(bfFrameWidth,bfFrameHeight);
                    bfSetupFlag = 0;
                }

                Timer timer = new Timer();
                TimerTask endfight = new TimerTask(){
                    @Override
                    public void run(){
                        System.out.println("Ending fight");
                        battlefield.bfFrame.setVisible(false);

                    }
                };




                timer.schedule(endfight, fightTime);
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

        theShop = new Shop( this);
        //shopPanel = new Shop(this).getPanel();
        shopPanel = theShop.getPanel();
        //shopPanel.set

        //create a border!
        shopPanel.setBorder(shopBorder);
        System.out.println("TheGame-CreateUIComponents shopPanelSize: " + shopPanel.getSize());

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
            listOneCosts.add(new Unit("tempUnit 1c", i, 1, 100 + i, 0, 10, 10));
            if (i %2 ==0 ) {
                listOneCosts.get(i).setImageFile(Constants.CHAMPIONS_PREFIX + "kirby.jpg");
                listOneCosts.get(i).setName("Kirby");
            }
            else{
                listOneCosts.get(i).setImageFile(Constants.CHAMPIONS_PREFIX + "king_dedede.png");
                listOneCosts.get(i).setName("King Dedede");
            }
        }
        for (int i2 = 0; i2 <numTwoCosts; i2++){
            listTwoCosts.add(new Unit("tempUnit 2c", i2, 2, 200 +i2, 0, 20, 20));
        }
        for (int i3 = 0 ;i3< numThreeCosts; i3++){
            listThreeCosts.add(new Unit("tempUnit 3c", i3, 3, 300 +i3,0, 30, 30));
        }
        for (int i4 = 0; i4 < numFourCosts; i4++){
            listFourCosts.add(new Unit("tempUnit 4c", i4, 4, 400 +i4,0, 40, 40));
        }
        for (int i5 =0; i5 < numFiveCosts; i5++){
            listFiveCosts.add(new Unit("tempUnit 5c", i5, 5, 500 +i5, 0,50, 50));
        }
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

    public Shop getTheShop(){return theShop;}
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
    public int getBenchWidth(){
        //System.out.println("[TheGame] Width: " + intrFrameBench.getWidth());
        return intrFrameBench.getWidth();
    }
    public int getBenchHeight(){
        //System.out.println("[TheGame] Height: " + intrFrameBench.getHeight());
        return intrFrameBench.getHeight();
    }

    public LineBorder getShopBorder(){return shopBorder;}
    public LineBorder getBenchBorder(){return benchBorder;}

    public Dimension getBenchDimensions(){
        return new Dimension(benchWidth,benchHeight);
    }


}
