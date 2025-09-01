package main.java.TFTGame.Exceptions;

public class NotEnoughSpaceException extends Exception{
    public NotEnoughSpaceException(){
        super();
    }
    public NotEnoughSpaceException(String msg){
        super(msg);
    }
}
