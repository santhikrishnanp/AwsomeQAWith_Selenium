package pageObjects;

import core.CoreUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginUser extends CoreUtils {
    WebDriver driver;
    public LoginUser(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    private By email = By.id("input-email");
//    @FindBy(id = "input-email")
//    WebElement email;
    private By password = By.id("input-password");

    private By login = By.xpath("//input[@value ='Login']");

    private By successMessage = By.xpath("//h1[text()='Your Account Has Been Created!']");
//    @FindBy (xpath = "//h1[text()='Your Account Has Been Created!']")
//    WebElement successmessage;


    public String getSuccessMessage() {
        String message = getText(successMessage);
//        String message = successmessage.getText();
        return message;
    }


    public AccountPage loginExistingUser(String mail,String pass){
    sendKeys(email,mail);
//        email.sendKeys(mail);
    sendKeys(password,pass);
//    password.sendKeys(pass);
     click( login);
//    login.click();
    AccountPage accountPage = new AccountPage(driver);
    return accountPage;
    }


}
