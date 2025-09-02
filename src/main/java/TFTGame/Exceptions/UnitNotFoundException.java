package TFTGame.Exceptions;

public class UnitNotFoundException extends Exception{
    public UnitNotFoundException(){
        super();
    }
    public UnitNotFoundException(String msg){
        super(msg);
    }
}