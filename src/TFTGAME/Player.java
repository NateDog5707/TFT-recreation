package TFTGAME;

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
    }


    public void addUnitBench(Unit added){

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

}
