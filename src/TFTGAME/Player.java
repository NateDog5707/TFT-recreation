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

    public Player(String username){
        this.username = username;
        playerStartGame();
    }
    public void setPlayerNum(int num){
        this.playerNum = num;
    }

    public void playerStartGame(){
        this.balance = 0;
        this.level = 1;
        this.exp = 0;
        this.streak = 0;
        this.hp = 100;
    }

    public String toString(){
        return "Player: " + this.username + " , HP: " + this.hp + "\n";
    }


    public int getLevel(){
        return level;
    }

}
