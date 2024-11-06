package TFTGAME;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the main menu poggers
 */
public class MainMenu {

    //private JFrame frame;
    private JButton startButton;
    private JPanel MainMenuPanel;
    private JButton EXITButton;
    private JTextField usernameField;
    private JLabel EYUtext;
    private static String username;
    private static TheGame theTFTGame;
    private static Player player;

    public MainMenu(){

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Opening the game menu");

                //TheGame.addPlayer(new Player(usernameField.getName()));

                username = usernameField.getText();
                if (username.isEmpty()){
                    username = "John Doe";
                }
                player = new Player(username);

                theTFTGame = new TheGame();
                theTFTGame.gameFrame.setVisible(true);

                //here to call Player Bench init cuz sizes of modules don't get established until after constructor
                //debug size
                System.out.println("[MainMenu] Bench dimensions: " + theTFTGame.getBenchFrame().getSize());

                player.getBench().initAnchorPoints(theTFTGame.getBenchFrame(), theTFTGame);
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MainMenuPanel.dispose();
                //dispose();
                System.out.println("Trying to exit!");
                System.exit(0);
            }
        });
    }



    public static void main(String[] args){

        JFrame frame = new JFrame("Main Menu");

        frame.setContentPane(new MainMenu().MainMenuPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 200);
        frame.setSize(frame.getPreferredSize());
        frame.setLocation(100,100);
    }

    public static String getUsername(){return username;}
    public static Player getPlayer(){return player;}

    public static TheGame getTheTFTGame(){return theTFTGame;}


}
