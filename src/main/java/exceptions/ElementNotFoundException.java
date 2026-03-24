package exceptions;

public class ElementNotFoundException extends SeleniumFrameworkException{


    public ElementNotFoundException(String message) {
        super("Element not found: " + message);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super("Element not found: " + message,cause);
    }
}
