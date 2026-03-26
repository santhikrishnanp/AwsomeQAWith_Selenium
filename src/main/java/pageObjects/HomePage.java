package pageObjects;

import core.CoreUtils;
import org.openqa.selenium.By;
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
    private By myaccount = By.xpath("//a[@class='dropdown-toggle']//span[text()='My Account']");
//    @FindBy (xpath = "//a[@class='dropdown-toggle']//span[text()='My Account']")
//    WebElement myaccount;
    private By register = By.linkText("Register");
//    @FindBy (linkText = "Register")
//    WebElement register;

    private By login = By.linkText("Login");

    private By desktop = By.xpath("//a[contains(text(),'Desktops')]");
    private By showAll = By.xpath("//a[contains(text(),'Show All Desktops')]");


    public void goTo()
    {
        driver.get("https://awesomeqa.com/ui/");
    }

    public RegisterUsers navigateToRegistrationPage(){
        click(myaccount);
//        myaccount.click();
        click(register);
//        register.click();
        RegisterUsers registerUsers = new RegisterUsers(driver);
        return registerUsers;

    }

    public LoginUser navigateToLoginPage(){
        click( myaccount);
//        myaccount.click();
        click(login);
//        login.click();
        LoginUser login = new LoginUser(driver);
        return login;

    }

    public Desktops navigateToDesktopsPage(){
        moveToElement(desktop);
        click(showAll);
//        showAll.click();
        Desktops desktop = new Desktops(driver);
        return desktop;

    }

}
