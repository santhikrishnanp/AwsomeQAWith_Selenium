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
    private By goToCart = By.linkText("Cart");

    public Checkout addToCarts(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
        click(addToCart);
        click(goToCart);
        Checkout checkout = new Checkout(driver);
        return checkout;
    }





//    xpath =
}
