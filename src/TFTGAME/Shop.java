package TFTGAME;

import TFTGAME.Exceptions.NotEnoughBalException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

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
    /**
     * the shop's array. holds 5 units.
     */
    private static Unit[] shopArray = new Unit[5];

    private TheGame theTFTGame;

    public Shop(){
        this((TheGame) null);

    }

    public Shop (TheGame gamescreen){
        theTFTGame = gamescreen;
        //create uicomponents
        TheGame.initializeShop();

        ImageIcon[] imageicons = new ImageIcon[5];
        Image[] images = new Image[5];
        //initialize first shop
        for (int i = 0; i < 5 ;i++){
            shopArray[i] = rerollShopUnit(1, i);


            imageicons[i] = new ImageIcon(shopArray[i].getImageFile());
            images[i] = imageicons[i].getImage().getScaledInstance(shopIconWidth, shopIconHeight, Image.SCALE_DEFAULT);
            imageicons[i] = new ImageIcon(images[i]);
            //System.out.println("E");
        }
        shopImage1.setIcon(imageicons[0]);
        shopImage2.setIcon(imageicons[1]);
        shopImage3.setIcon(imageicons[2]);
        shopImage4.setIcon(imageicons[3]);
        shopImage5.setIcon(imageicons[4]);
        //end of initalization


        rerollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rerollin'!");
                rerollShop();
                System.out.println("size of 1c pool: " + TheGame.getListOneCosts().size());
            }
        });
        shopImage1.addComponentListener(new ComponentAdapter() {
        });
        shopImage1.addComponentListener(new ComponentAdapter() {
        });
        buyButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shopArray[0] == null){
                    System.out.println("hey nothing in this shop");
                }
                else{
                    System.out.println(shopArray[0]);
                    System.out.println("buying");

                    //get rid of unit in shopArray[0], add it to player's bench
                    // need access to player's bench.
                    //also, get rid of unit in unit pool.


                }
            }
        });
        buyButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }




    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    public JPanel getPanel(){
        return panel;
    }



    /**
     * Call to reroll a new shop as intended. This will find 5 new units from the pool and display them and have an opportuntiy for the player to buy
     * adds new batch of units to shopArray
     */
    public void rerollShop(){
        ImageIcon[] imageicons = new ImageIcon[5];
        Image[] images = new Image[5];

        try{
            //A check if player can reroll
            if (TheGame.getbal()<= 1){
                throw new NotEnoughBalException();
            }
            else{
                //enough balance, remove 2 gold and proceed to reroll
                TheGame.setbal(TheGame.getbal() -2 );
                theTFTGame.updateTextArea();
            }
            //A done checking!

            //B start the reroll process   =========---------
            for (int i2 = 0; i2 < 5; i2++){
                //before reroll debug
                for (int i3 = 0; i3 < TheGame.getListOneCosts().size(); i3++){
                    System.out.println("iteration " + i3 + ": " + TheGame.getListOneCosts().get(i3));
                }
                //reroll the costs
                shopArray[i2] = rerollShopUnit(rerollShopCosts(TheGame.getMainPlayer().getLevel()), i2); // may have to change this to support MULTIPLE PLAYERS

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



        }
        catch (NotEnoughBalException e){
            System.out.println("Not enough bal to reroll!");
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
    public Unit rerollShopUnit(int cost,int shopArrayIndex){
        Random rand = new Random();
        Unit theUnit;
        int randResult;
        int listLength = 1;

        //want to put unit back into pool if it was in the shop
        if (shopArray[shopArrayIndex] != null){
            theUnit = shopArray[shopArrayIndex];
            shopArray[shopArrayIndex] = null;
            //find the unit's cost and add them back into their respective pool
            //REPLACE this section with binary search adding, for O(lgn) time
            addUnitBackToPool(theUnit, theUnit.getCost());
            /*{
                switch (theUnit.getCost()){
                    case 1: TheGame.getListOneCosts().add(theUnit); break;
                    case 2: TheGame.getListTwoCosts().add(theUnit); break;
                    case 3: TheGame.getListThreeCosts().add(theUnit); break;
                    case 4: TheGame.getListFourCosts().add(theUnit); break;
                    case 5: TheGame.getListFiveCosts().add(theUnit); break;
                    default: System.out.println("ERROR in adding unit back to pool"); break;
                }
            }*/

            // reset it for insurance
            theUnit = null;


        }
        else /*there is an empty space b/c the unit was bought*/{
            //debug
            //System.out.println("No unit to add back to pool");
        }

        //i want to grab a new unit from the pool
        //System.out.println("I want to add a unit to shop");
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


    public void addUnitBackToPool(Unit unitToAddBack, int cost){
        int l, h;
        l = 0;
        switch(cost){
            case 1:
                h = TheGame.getListOneCosts().size()-1;
                //binary search in 1costpool
                TheGame.getListOneCosts().add( bSearchUnitPool(TheGame.getListOneCosts(), unitToAddBack.getID(), l, h), unitToAddBack);break;
            case 2:
                h = TheGame.getListTwoCosts().size()-1;
                TheGame.getListTwoCosts().add( bSearchUnitPool(TheGame.getListTwoCosts(), unitToAddBack.getID(), l, h), unitToAddBack);break;
            case 3:
                h = TheGame.getListThreeCosts().size()-1;
                TheGame.getListThreeCosts().add(bSearchUnitPool(TheGame.getListThreeCosts(), unitToAddBack.getID(), l, h),unitToAddBack);break;
            case 4:
                h = TheGame.getListFourCosts().size()-1;
                TheGame.getListFourCosts().add(bSearchUnitPool(TheGame.getListFourCosts(), unitToAddBack.getID(), l, h),unitToAddBack);break;
            case 5:
                h = TheGame.getListFiveCosts().size()-1;
                TheGame.getListFiveCosts().add(bSearchUnitPool(TheGame.getListFiveCosts(), unitToAddBack.getID(), l, h),unitToAddBack);break;

            default:
                System.out.println("error adding unit back to pool");
        }

    }


    public int bSearchUnitPool(ArrayList<Unit> unitPool, int unitID, int l, int h){
        //implement binary search
        int max = h;
        while (l <= h) {

            int q = (l + h) / 2;

            if (q == h) {
                if (unitID < unitPool.get(0).getID()){
                    return 0;
                }
                return q+1;
            } else if (unitID >= unitPool.get(q).getID() && unitID < unitPool.get(q + 1).getID()) {
                if (max == q){
                    return q;
                }
                else{
                    return q+1;
                }
            } else if (unitID < unitPool.get(q).getID()) {
                h = q -1;
            } else {
                l = q+1;
            }
        }

        return -1;
    }
}
