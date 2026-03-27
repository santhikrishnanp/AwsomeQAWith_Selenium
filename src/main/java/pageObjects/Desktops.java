package pageObjects;

import core.CoreUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Desktops extends CoreUtils{

    WebDriver driver;

    public Desktops(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    private By addToCart = By.xpath("//a[contains(text(),'iPhone')]/ancestor::div/following-sibling::div[@class='button-group']//span[contains(text(),'Add to Cart')]");
    private By goToCart = By.xpath("//a[@title='Shopping Cart']");

    public Checkout addToCarts(){

        safeClick(addToCart);
        safeClick(goToCart);
//        click(addToCart);
//        waitForElementToBeClickable(goToCart);
//        click(goToCart);
        Checkout checkout = new Checkout(driver);
        return checkout;
    }





//    xpath =
}
