package TFTGAME;

import TFTGAME.Exceptions.UnitNotFoundException;

public class Player {

    //logistics
    private String username;
    private int playerNum; //1-8

    //gameplay related
    private int balance;
    private int level;
    private int exp;
    private int streak;
    private int hp;

    private Unit[] unitsOnBench = new Unit[9];
    private Unit[] unitsActive = new Unit[10];
    private PlayerBench benchObj;




    public Player(){
        this.username = "John Doe";
        playerStartGame();
    }
    public Player(String username){
        this.username = username;
        playerStartGame();
    }

    public void playerStartGame(){
        this.balance = 0;
        this.level = 1;
        this.exp = 0;
        this.streak = 0;
        this.hp = 100;
        createNewBench();

    }

    public void createNewBench(){
        benchObj = new PlayerBench(this);
    }
    public int addUnitBench(Unit added){
        //find first open spot in bench, then acquire index and add unit
        //linear search works
        for (int i = 0; i < 9; i++){
            if (unitsOnBench[i] == null) {
                unitsOnBench[i] = added;
                return i;
            }
        }
        return -1;
    }

    public Unit removeUnitBench(Unit removed){
        try {
            for (int i = 0; i < 9; i++){
                if (unitsOnBench[i] == removed){
                    unitsOnBench[i] = null;
                    return removed;
                }
            }
            throw new UnitNotFoundException();
        }catch (UnitNotFoundException e){
            System.out.println("Unit does not exist on bench!");
        }
        return null;
    }

    public Unit removeUnitBench(int index){
        try{
            Unit grabbed;
            if (unitsOnBench[index] != null){
                grabbed = unitsOnBench[index];
                unitsOnBench[index] = null;
                return grabbed;
            }else{
                throw new UnitNotFoundException();
            }

        }catch (UnitNotFoundException e){
            System.out.println("Unit does not exist on bench!");
            return null;
        }
    }
    public boolean isBenchFull(){
        for (int i = 0; i < 9; i++){
            if (unitsOnBench[i] == null){
                return false;
            }
        }
        return true;
    }



    public PlayerBench getBench(){
        return benchObj;
    }

    public String toString(){
        return "Player: " + this.username + " , HP: " + this.hp + "\n";
    }

    public void setPlayerNum(int num){
        this.playerNum = num;
    }
    public int getPlayerNum(){return this.playerNum;}
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){ this.level = level;}
    public void addLevel(){ this.level += 1;}

    public int getBalance() {return this.balance;}
    public void setBalance(int bal) {this.balance = bal;}

    public int getHp() {return this.hp;}
    public void setHp(int hp) {this.hp = hp;}
    public int getExp() {return this.exp;}
    public void setExp(int exp) {this.exp = exp;}
    public int getStreak() {return this.exp;}
    public void breakStreak() {this.streak = 0;}
    public void addStreak() {this.streak++;}
    public void setStreak (int streak) {this.streak = streak;}

    public Unit[] getUnitsOnBench(){return unitsOnBench;}
    public Unit[] getUnitsActive(){return unitsActive;}


}
