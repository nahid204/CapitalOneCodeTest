package util;


public class InvalidDateException extends Exception {
    public InvalidDateException()
    {
        super("From date can not be greater than to date");
    }

}
