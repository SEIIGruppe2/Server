package at.aau.se2.exceptions;

/**
 * Signals that a card was not found.
 * This exception is thrown to indicate that a card could not be found in the game.
 */
public class CardNotFoundException extends Exception{
    /** The detail message for the exception. */
    private final String msg;

    /**
     * Constructs a CardNotFoundException with the specified detail message.
     *
     * @param s the detail message
     */
    public CardNotFoundException(String s) {
        this.msg = s;
    }

    /**
     * Retrieves the detail message of this exception.
     *
     * @return the detail message
     */
    @Override
    public String getMessage(){
        return this.msg;
    }
}
