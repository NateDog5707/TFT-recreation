package TFTGAME;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Shop {
    //global var
    public static final int shopIconWidth = 150;
    public static final int shopIconHeight = 150;
    private JLabel shopLabel;
    private JButton buyButton1;
    private JButton buyButton2;
    private JButton buyButton3;
    private JButton buyButton4;
    private JButton buyButton5;
    private JLabel shopImage1;
    private JLabel shopImage2;
    private JLabel shopImage3;
    private JLabel shopImage4;
    private JLabel shopImage5;
    private JPanel panel;
    private JLabel costShop1Label;
    private JButton rerollButton;

    private BufferedImage myPicture;
    private static Unit shopArray[] = new Unit[5];



    public Shop(){
        //create uicomponents
        TheGame.initializeShop();

        /*
        try {
            myPicture = ImageIO.read(new File("resources/images/champions/king_dedede.jpg"));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("oopsie, file read error");
        }
        */

            //initialize first shop
            for (int i = 0; i < 5 ;i++){
                shopArray[i] = rerollShopUnit(1, i);
            }




        rerollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rerollin'!");
                rerollShop();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        /*
        shopImage1 = new JLabel(new ImageIcon());
        shopImage2 = new JLabel(new ImageIcon("resources/images/champions/kirby.jpg"));
        shopImage3 = new JLabel(new ImageIcon("resources/images/champions/kirby.jpg"));
        shopImage4 = new JLabel(new ImageIcon("resources/images/champions/kirby.jpg"));
        shopImage5 = new JLabel(new ImageIcon("resources/images/champions/kirby.jpg"));
        */
    }

    public JPanel getPanel(){
        return panel;
    }



    /**
     * Call to reroll a new shop as intended. This will find 5 new units from the pool and display them and have an opportuntiy for the player to buy
     * adds new batch of units to shopArray
     */
    public void rerollShop(){
        ImageIcon imageicons[] = new ImageIcon[5];
        Image images[] = new Image[5];

        try{
            for (int i2 = 0; i2 < 5; i2++){
                //reroll the costs
                shopArray[i2] = rerollShopUnit(rerollShopCosts(TheGame.getMainPlayer().getLevel()), i2);
                //gui stuff
                imageicons[i2] = new ImageIcon(shopArray[i2].getImageFile());
                images[i2] = imageicons[i2].getImage().getScaledInstance(shopIconWidth, shopIconHeight, Image.SCALE_DEFAULT);
                imageicons[i2] = new ImageIcon(images[i2]);
            }


            //gui: show new batch of units
            //shopImage1 = new JLabel(new ImageIcon(myPicture));
            //shopImage2.setIcon(new ImageIcon("resources/images/champions/king_dedede.png"));

            shopImage1.setIcon(imageicons[0]);
            shopImage2.setIcon(imageicons[1]);
            shopImage3.setIcon(imageicons[2]);
            shopImage4.setIcon(imageicons[3]);
            shopImage5.setIcon(imageicons[4]);



            /*
            shopImage1.setIcon(new ImageIcon(shopArray[0].getImageFile()));
            shopImage2.setIcon(new ImageIcon(shopArray[1].getImageFile()));
            shopImage3.setIcon(new ImageIcon(shopArray[2].getImageFile()));
            shopImage4.setIcon(new ImageIcon(shopArray[3].getImageFile()));
            shopImage5.setIcon(new ImageIcon(shopArray[4].getImageFile()));
            */


        }
        catch (Exception e){
            System.out.println("Rerolling shop error");
            e.printStackTrace();
        }


    }

    /**
     * randomizes  a shop cell's cost of a unit.
     * @param level the player's level to use the correct odds in rolling shop unit costs
     * @return range 1 - 5
     */
    public int rerollShopCosts(int level){
        Random rand = new Random();
        int randResult = Math.abs(rand.nextInt()) % 100;
        System.out.println("Reroll 1 - 100: " +randResult);
        switch(level){
            case 1:
            case 2:
                return 1;
            case 3:
                if (randResult < 75){ return 1;} else {return 2;}
            case 4:
                if (randResult < 55){ return 1;} else if(randResult < 85){return 2;} else {return 3;}
            case 5:
                if (randResult < 45){ return 1;} else if(randResult <78){return 2;} else if(randResult <98){return 3;} else {return 4;}
            case 6:
                if (randResult < 30){ return 1;} else if(randResult <70){return 2;} else if(randResult <95){return 3;} else {return 4;}
            case 7:
                if (randResult < 19){ return 1;} else if(randResult <54){return 2;} else if(randResult <89){return 3;} else if(randResult <99){return 4;} else {return 5;}
            case 8:
                if (randResult < 18){ return 1;} else if(randResult <43){return 2;} else if(randResult <79){return 3;} else if(randResult <97){return 4;} else {return 5;}
            case 9:
                if (randResult < 10){ return 1;} else if(randResult <30){return 2;} else if(randResult <55){return 3;} else if(randResult <90){return 4;} else {return 5;}
            default:
                return 1;
        }
    }

    /**
     * finds a new unit from the X-cost pool to display in shop
     * clears shopArray
     * @param cost the cost of the unit to find.
     * @return the unit to display in new shop
     */
    public Unit rerollShopUnit(int cost,int index){
        Random rand = new Random();
        Unit theUnit;
        int randResult;
        int listLength = 1;


        if (shopArray[index] != null){
            theUnit = shopArray[index];
            shopArray[index] = null;
            //find the unit's cost and add them back into their respective pool
            switch (theUnit.getCost()){
                case 1: TheGame.getListOneCosts().add(theUnit); break;
                case 2: TheGame.getListTwoCosts().add(theUnit); break;
                case 3: TheGame.getListThreeCosts().add(theUnit); break;
                case 4: TheGame.getListFourCosts().add(theUnit); break;
                case 5: TheGame.getListFiveCosts().add(theUnit); break;
                default: System.out.println("ERROR in adding unit back to pool"); break;
            }
            // reset it for insurance
            theUnit = null;
        }
        else /*there is an empty space b/c the unit was bought*/{
            //debug
            System.out.println("No unit to add back to pool");
        }

        //i want to grab a new unit from the pool
        System.out.println("I want to add a unit to shop");
        switch(cost){
            case 1:
                listLength = TheGame.getListOneCosts().size();
                randResult = Math.abs(rand.nextInt()) % listLength;
                theUnit = TheGame.getListOneCosts().remove(randResult);
                break;
            case 2:
                listLength = TheGame.getListTwoCosts().size();
                randResult = Math.abs(rand.nextInt()) % listLength;
                theUnit = TheGame.getListTwoCosts().remove(randResult);
                break;
            case 3:
                listLength = TheGame.getListThreeCosts().size();
                randResult = Math.abs(rand.nextInt()) % listLength;
                theUnit = TheGame.getListThreeCosts().remove(randResult);
                break;
            case 4:
                listLength = TheGame.getListFourCosts().size();
                randResult = Math.abs(rand.nextInt()) % listLength;
                theUnit = TheGame.getListFourCosts().remove(randResult);
                break;
            case 5:
                listLength = TheGame.getListFiveCosts().size();
                randResult = Math.abs(rand.nextInt()) % listLength;
                theUnit = TheGame.getListFiveCosts().remove(randResult);
                break;
            default:
                System.out.println("Error in grabbing a unit from pool");
                theUnit = new Unit("Error");
                break;
        }
        return theUnit;


    }


}
