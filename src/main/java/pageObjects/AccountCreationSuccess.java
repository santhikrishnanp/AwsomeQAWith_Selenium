package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreationSuccess {
    WebDriver driver;
    public AccountCreationSuccess(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//h1[text()='Your Account Has Been Created!']")
    WebElement successmessage;


    public String getSuccessMessage(){
        String message = successmessage.getText();
        return message;
    }
}
