package exceptions;

public class SeleniumFrameworkException extends RuntimeException{
    public SeleniumFrameworkException(String message) {
        super(message);
    }

    public SeleniumFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
