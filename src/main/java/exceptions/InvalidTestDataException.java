package exceptions;

public class InvalidTestDataException extends SeleniumFrameworkException{
    public InvalidTestDataException(String message) {
        super("Invalid test data: " + message);
    }


}
