package at.aau.se2.exceptions;

public class CardNotFoundException extends Exception{
    private String msg;
    public CardNotFoundException(String s) {
        this.msg = s;
    }

    @Override
    public String getMessage(){
        return this.msg;
    }
}
