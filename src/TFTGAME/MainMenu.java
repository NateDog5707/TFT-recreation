package TFTGAME;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    //private JFrame frame;
    private JButton Button1;
    private JLabel YeahLabel;
    private JPanel MainMenuPanel;
    private JButton EXITButton;
    private JTextField usernameField;

    private static String username;

    private TheGame theTFTGame;

    public MainMenu(){
        //mainMenuFrame = new JFrame("")

        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Opening the game menu");

                //TheGame.addPlayer(new Player(usernameField.getName()));

                username = usernameField.getText();
                theTFTGame = new TheGame();
                theTFTGame.gameFrame.setVisible(true);



                System.out.println("Opened the game menu");
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
        frame.setLocation(100,100);
    }

    public static String getUsername(){
        return username;
    }
}
