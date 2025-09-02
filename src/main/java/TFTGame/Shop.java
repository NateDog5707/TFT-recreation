package TFTGame;

import TFTGame.Exceptions.NotEnoughBalException;
import TFTGame.Exceptions.NotEnoughSpaceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

public class Shop {
    //global var
    public static final int shopIconWidth = 90;
    public static final int shopIconHeight = 90;
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
    private JLabel shop1Label;
    private JButton rerollButton;
    private JLabel shop2Label;
    private JLabel shop3Label;
    private JLabel shop4Label;
    private JLabel shop5Label;
    private JLabel shop1LabelCost;
    private JLabel shop2LabelCost;
    private JLabel shop3LabelCost;
    private JLabel shop4LabelCost;
    private JLabel shop5LabelCost;
    private JButton buyEXPButton;
    private int shopPanelHeight;

    private BufferedImage myPicture;
    /**
     * the shop's array. holds 5 units.
     */
    private static Unit[] shopArray = new Unit[5];

    private TheGame theGame;

    ImageIcon[] imageIcons = new ImageIcon[5];
    Image[] images = new Image[5];

    private int shopHeightSetup = 1;


    public Shop (TheGame gamescreen){
        theGame = gamescreen;
        //create uicomponents
        TheGame.initializeShop();
        //initialize first shop
        for (int i = 0; i < 5 ;i++){
            shopArray[i] = rerollShopUnit(1, i);

            imageIcons[i] = new ImageIcon(shopArray[i].getImageFile());
            images[i] = imageIcons[i].getImage().getScaledInstance(shopIconWidth, shopIconHeight, Image.SCALE_DEFAULT);
            imageIcons[i] = new ImageIcon(images[i]);
            //along with updating image, update text
            updateShopLabel(i);
        }
        shopImage1.setIcon(imageIcons[0]);
        shopImage2.setIcon(imageIcons[1]);
        shopImage3.setIcon(imageIcons[2]);
        shopImage4.setIcon(imageIcons[3]);
        shopImage5.setIcon(imageIcons[4]);
        shopPanelHeight = panel.getHeight();
        System.out.println("initial shop height: " + shopPanelHeight);
        //end of initalization


        rerollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rerollin'!");
                rerollShop();
                System.out.println("size of 1c pool: " + TheGame.getListOneCosts().size());
            }
        });

        buyButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //theGame.getTheShop().freezeShopPanelSize();
                /*panel.setPreferredSize(panel.getSize());
                System.out.println(panel.getBackground());*/
                if (shopArray[0] == null){
                    System.out.println("hey nothing in this shop");
                }
                else{
                    System.out.println(shopArray[0]);
                    int benchIndexAdded = buyUnit(theGame.mainPlayer, 0);
                    //buyUnit can return -1 when errors of not enough bal or not enough bench space
                    if (benchIndexAdded == -1){
                        System.out.println("Error when buying");
                        return;
                    }

                    //gui
                    {
                        clearShopDisplay(0);
                        theGame.mainPlayer.getBench().updateBenchIcons(theGame.getBenchFrame(), theGame, benchIndexAdded);
                        System.out.println("outside call:" +panel.getHeight());
                    }
                }

            }
        });
        buyButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shopArray[1] == null){
                    System.out.println("hey nothing in this shop");
                } else{
                    System.out.println(shopArray[1]);
                    int benchIndexAdded = buyUnit(theGame.mainPlayer, 1);
                    if (benchIndexAdded == -1){
                        System.out.println("Error when buying");
                        return;
                    }
                    clearShopDisplay(1);
                    theGame.mainPlayer.getBench().updateBenchIcons(theGame.getBenchFrame(), theGame, benchIndexAdded);
                }
            }
        });
        buyButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shopArray[2] == null){
                    System.out.println("hey nothing in this shop");
                } else{
                    System.out.println(shopArray[2]);
                    int benchIndexAdded = buyUnit(theGame.mainPlayer, 2);
                    if (benchIndexAdded == -1){
                        System.out.println("Error when buying");
                        return;
                    }
                    clearShopDisplay(2);
                    theGame.mainPlayer.getBench().updateBenchIcons(theGame.getBenchFrame(), theGame, benchIndexAdded);
                }
            }
        });
        buyButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shopArray[3] == null){
                    System.out.println("hey nothing in this shop");
                } else{
                    System.out.println(shopArray[3]);
                    int benchIndexAdded = buyUnit(theGame.mainPlayer, 3);
                    if (benchIndexAdded == -1){
                        System.out.println("Error when buying");
                        return;
                    }
                    clearShopDisplay(3);
                    theGame.mainPlayer.getBench().updateBenchIcons(theGame.getBenchFrame(), theGame, benchIndexAdded);
                }
            }
        });
        buyButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shopArray[4] == null){
                    System.out.println("hey nothing in this shop");
                } else{
                    System.out.println(shopArray[4]);
                    int benchIndexAdded = buyUnit(theGame.mainPlayer, 4);
                    if (benchIndexAdded == -1){
                        System.out.println("Error when buying");
                        return;
                    }
                    clearShopDisplay(4);
                    theGame.mainPlayer.getBench().updateBenchIcons(theGame.getBenchFrame(), theGame, benchIndexAdded);
                }
            }
        });
    }




    private void createUIComponents() {

    }

    public JPanel getPanel(){
        return panel;
    }

    public void clearShopArray(){
        for (int i = 0; i < 5; i++){
            if (shopArray[i] != null){
                addUnitBackToPool(shopArray[i], shopArray[i].getCost());
                shopArray[i] = null;
            }
        }
    }


    /**
     * Call to reroll a new shop as intended. This will find 5 new units from the pool and display them and have an opportuntiy for the player to buy
     * adds new batch of units to shopArray
     */
    public void rerollShop(){
        /*ImageIcon[] imageIcons = new ImageIcon[5];
        Image[] images = new Image[5];*/

        try{
            //A check if player can reroll
            if (TheGame.getbal()<= 1){
                throw new NotEnoughBalException();
            }
            else{
                //enough balance, remove 2 gold and proceed to reroll
                TheGame.setbal(TheGame.getbal() -2 );
                theGame.updateTextArea();
            }
            //A done checking!

            //B start the reroll process   =========---------
            for (int i2 = 0; i2 < 5; i2++) {
                //before reroll debug
                /*for (int i3 = 0; i3 < TheGame.getListOneCosts().size(); i3++){
                    System.out.println("iteration " + i3 + ": " + TheGame.getListOneCosts().get(i3));
                }*/
                //reroll the costs
                shopArray[i2] = rerollShopUnit(rerollShopCosts(theGame.getMainPlayer().getLevel()), i2); // may have to change this to support MULTIPLE PLAYERS

                //gui stuff
                imageIcons[i2] = new ImageIcon(shopArray[i2].getImageFile());
                images[i2] = imageIcons[i2].getImage().getScaledInstance(shopIconWidth, shopIconHeight, Image.SCALE_DEFAULT);
                imageIcons[i2] = new ImageIcon(images[i2]);

                updateShopLabel(i2);
            }

            shopImage1.setIcon(imageIcons[0]);
            shopImage2.setIcon(imageIcons[1]);
            shopImage3.setIcon(imageIcons[2]);
            shopImage4.setIcon(imageIcons[3]);
            shopImage5.setIcon(imageIcons[4]);



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

            addUnitBackToPool(theUnit, theUnit.getCost());

        }
        else /*there is an empty space b/c the unit was bought*/{
            //debug
            //System.out.println("No unit to add back to pool");
        }

        //i want to grab a new unit from the pool

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

            //if the last number
            if (q >= h) {
                if (unitID < unitPool.get(0).getID()){
                    return 0;
                }
                return q+1;
            }
            // if unitID is in between the midpoint number "q" and "q+1"
            else if (unitID >= unitPool.get(q).getID() && unitID < unitPool.get(q + 1).getID()) {
                if (max == q){
                    return q;
                }
                else{
                    return q+1;
                }
            }
            //didnt find a match yet, cut the search in half.
            else if (unitID < unitPool.get(q).getID()) {
                h = q ;
            } else {
                l = q+1;
            }
        }

        //this should never happen
        return -1;
    }


    public int buyUnit (Player player, int index){
        Unit boughtUnit = shopArray[index];
        //check unit's price and see if player has enough money
        System.out.println("buying a unit!");
        try {

            if (boughtUnit.getCost() < player.getBalance()){
                //cant buy, return flag
                throw new NotEnoughBalException();
            }
            else if (player.isBenchFull()){
                //can't buy, return flag
                throw new NotEnoughSpaceException();
            }
            else{
                player.setBalance(player.getBalance() - boughtUnit.getCost()); //new bal = old bal - cost!
                shopArray[index] = null;
                //add unit to player's bench
                return player.addUnitBench(boughtUnit);
            }

        }catch (NotEnoughBalException e){
            System.out.println("Not enough balance to buy unit!");
            return -1;
        }catch (NotEnoughSpaceException e){
            System.out.println("Not enough space on bench to buy unit!");
            return -1;
        }

    }

    //called when player wants to sell unit
    //called with middle clicking over a unitLabel
    public void sellUnit(Player player, Unit unitSold, int benchField, int x, int y){
        //remove it from game too.
        if (benchField == 0) {
            player.removeUnitBench(unitSold, x);
        }
        else{
            player.removeUnitField(unitSold,x,y);
        }
        player.setBalance(player.getBalance() + unitSold.getCost());
        addUnitBackToPool(unitSold, unitSold.getCost());
        return;
    }


    /**
     * want to remove unit's displayed image from shop slot if bought
     * @param index which index of the array to remove, handles 0-4
     */
    public void clearShopDisplay(int index){
        if (shopHeightSetup == 1){
            shopPanelHeight = panel.getHeight();
            shopHeightSetup = 0;
            panel.setBorder(null);
            panel.updateUI();
            shopPanelHeight = panel.getHeight();
            //panel.setMinimumSize(new Dimension(panel.getWidth(), shopPanelHeight));
            panel.setPreferredSize(new Dimension(panel.getWidth(), shopPanelHeight));
            panel.setBorder(theGame.getShopBorder());
            panel.updateUI();
        }
        /*//panel.setBorder(null);
        panel.setPreferredSize(new Dimension(panel.getWidth(), shopPanelHeight));
        //panel.setBorder(theGame.getShopBorder());*/
        System.out.println("inside after: " + panel.getHeight());


        switch(index){
            case 0:
                buyButton1.setEnabled(false);
                //shopImage1.setIcon(null);
                shop1Label.setText("");
                shop1LabelCost.setText("");
                BufferedImage bImage = new BufferedImage(shopIconWidth, shopIconHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bImage.createGraphics();
                g2d.setColor(panel.getBackground());
                g2d.setColor(new Color(238,238,238));
                g2d.fillRect(0, 0, shopIconWidth, shopIconHeight);
                shopImage1.setIcon(new ImageIcon(bImage));
                break;
            case 1:
                buyButton2.setEnabled(false);
                shopImage2.setIcon(null);
                shop2Label.setText("");
                shop2LabelCost.setText("");
                break;
            case 2:
                buyButton3.setEnabled(false);
                shopImage3.setIcon(null);
                shop3Label.setText("");
                shop3LabelCost.setText("");
                break;
            case 3:
                buyButton4.setEnabled(false);
                shopImage4.setIcon(null);
                shop4Label.setText("");
                shop4LabelCost.setText("");
                break;
            case 4:
                buyButton5.setEnabled(false);
                shopImage5.setIcon(null);
                shop5Label.setText("");
                shop5LabelCost.setText("");
                break;

        }
    }

    /**
     * want to update unit's shop info during reroll when a new unit takes the shopArray space.
     * @param index
     */
    public void updateShopLabel(int index){
        if (shopArray[index] != null) {
            switch (index) {
                case 0:
                    buyButton1.setEnabled(true);
                    shop1Label.setText("" + shopArray[index].getName());
                    shop1LabelCost.setText("" + shopArray[index].getCost() + "g");
                    break;
                //shop1Label.setText()
                case 1:
                    buyButton2.setEnabled(true);
                    shop2Label.setText("" + shopArray[index].getName());
                    shop2LabelCost.setText("" + shopArray[index].getCost() + "g");
                    break;
                case 2:
                    buyButton3.setEnabled(true);
                    shop3Label.setText("" + shopArray[index].getName());
                    shop3LabelCost.setText("" + shopArray[index].getCost() + "g");
                    break;
                case 3:
                    buyButton4.setEnabled(true);
                    shop4Label.setText("" + shopArray[index].getName());
                    shop4LabelCost.setText("" + shopArray[index].getCost() + "g");
                    break;
                case 4:
                    buyButton5.setEnabled(true);
                    shop5Label.setText("" + shopArray[index].getName());
                    shop5LabelCost.setText("" + shopArray[index].getCost() + "g");
                    break;

            }
        }

    }

}

