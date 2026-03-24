package exceptions;

public class TimeoutException extends SeleniumFrameworkException{
    public TimeoutException(String message) {
        super("Timeout occurred: " + message);
    }

    public TimeoutException(String message, Throwable cause) {
        super("Timeout occurred: " + message, cause);
    }
}
