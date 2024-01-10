package TFTGAME;

public class Unit {

    private String name;
    private int cost;
    private int HP, MANA, ATK;
    private int maxHP, maxMANA;


    public Unit(String name, int cost, int HP, int mana, int atk){
        this.name = name;
        this.cost = cost;
        this.HP = HP;
        this.maxHP = HP;
        this.MANA = mana;
        this.maxMANA = mana;
        this.ATK = atk;
    }

    public Unit(String name){
        this.name = name;
        this.cost = 1;
        this.HP = 0;
        this.maxHP = 0;
        this.MANA = 0;
        this.maxMANA = 0;
        this.ATK = 0;
    }


    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
    public int getCost(){
        return this.cost;
    }
    public void setHP(int hp){
        this.HP = hp;
    }
    public int getHP(){
        return this.HP;
    }
    public void setMaxHP(int maxHP){
        this.maxHP = maxHP;
    }
    public int getMaxHP(){
        return this.maxHP;
    }
    public void setMana(int mana){
        this.MANA = mana;
    }
    public int getMana(){
        return this.MANA;
    }
    public void setMaxMana(int maxMANA){
        this.maxMANA = maxMANA;
    }
    public int getMaxMana(){
        return this.maxMANA;
    }

    public int getATK(){
        return this.ATK;
    }

    public void setATK(int ATK){
        this.ATK = ATK;
    }


    public String toString(){
        return this.name + " " + this.getHP() + " ";
    }
}
