package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Desktops {

    WebDriver driver;

    public Desktops(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//a[contains(text(),'iPhone')]/ancestor::div/following-sibling::div[@class='button-group']//span[contains(text(),'Add to Cart')]")
    WebElement addToCart;

    @FindBy(partialLinkText = "Cart" )
    WebElement goToCart;

    public Checkout addToCart(){
        addToCart.click();
        goToCart.click();
        Checkout checkout = new Checkout(driver);
        return checkout;
    }





//    xpath =
}
