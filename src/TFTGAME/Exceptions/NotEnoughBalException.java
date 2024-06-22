package TFTGAME.Exceptions;

public class NotEnoughBalException extends Exception{
    public NotEnoughBalException(){
        super();
    }
    public NotEnoughBalException(String msg){
        super(msg);
    }
}
