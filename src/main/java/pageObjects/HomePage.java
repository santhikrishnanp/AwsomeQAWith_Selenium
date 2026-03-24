package pageObjects;

import core.CoreUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CoreUtils {

    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        super(driver);
        //initialization
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }
    int number =3;

    @FindBy (xpath = "//a[@class='dropdown-toggle']//span[text()='My Account']")
    WebElement myaccount;

    @FindBy (linkText = "Register")
    WebElement register;

    @FindBy (linkText = "Login")
    WebElement login;

    @FindBy (xpath = "//a[contains(text(),'Desktops')]")
    WebElement desktop;

    @FindBy (xpath = "//a[contains(text(),'Show All Desktops')]")
    WebElement showAll;




    public void goTo()
    {
        driver.get("https://awesomeqa.com/ui/");
    }

    public RegisterUsers navigateToRegistrationPage(){
        myaccount.click();
        register.click();
        RegisterUsers registerUsers = new RegisterUsers(driver);
        return registerUsers;

    }

    public LoginUser navigateToLoginPage(){
        myaccount.click();
        login.click();
        LoginUser login = new LoginUser(driver);
        return login;

    }

    public Desktops navigateToDesktopsPage(){
        moveToElement(desktop);
        showAll.click();
        Desktops desktop = new Desktops(driver);
        return desktop;

    }



}
