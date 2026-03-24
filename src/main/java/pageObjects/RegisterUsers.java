package pageObjects;

import core.CoreUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterUsers extends CoreUtils {
    WebDriver driver;

    public RegisterUsers(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='firstname']")
    WebElement username;
    @FindBy(id = "input-lastname")
    WebElement lastname;
    @FindBy(id = "input-email")
    WebElement mail;
    @FindBy(name = "telephone")
    WebElement telephone;
    @FindBy(name = "password")
    WebElement password;
    @FindBy(name = "confirm")
    WebElement confirm;
    @FindBy(xpath = "//label[normalize-space()='No']")
    WebElement subscribe;
    @FindBy(css = "input[type='checkbox']")
    WebElement terms;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement submit;


    public LoginUser enterRegistrationDetails(String firstname,String lname,String email,String phone,String pass) throws InterruptedException {
        username.sendKeys(firstname);
        lastname.sendKeys(lname);
        mail.sendKeys(email);
        telephone.sendKeys(phone);
        password.sendKeys(pass);
        confirm.sendKeys(pass);
        subscribe.click();
        if(!terms.isSelected()){
            terms.click();
        }
        submit.click();
        Thread.sleep(3000);

       LoginUser login = new LoginUser(driver);
        return login;

    }

}
