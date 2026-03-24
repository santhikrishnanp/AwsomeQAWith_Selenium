package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginUser {
    WebDriver driver;
    public LoginUser(WebDriver driver){
    this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "input-email")
    WebElement email;

    @FindBy (id = "input-password")
    WebElement password;
    @FindBy (xpath = "//input[@value ='Login']")
    WebElement login;

    @FindBy (xpath = "//h1[text()='Your Account Has Been Created!']")
    WebElement successmessage;


    public String getSuccessMessage() {
        String message = successmessage.getText();
        return message;
    }


    public AccountPage loginExistingUser(String mail,String pass){
    email.sendKeys(mail);
    password.sendKeys(pass);
    login.click();
    AccountPage accountPage = new AccountPage(driver);
    return accountPage;
    }


}
