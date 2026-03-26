package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;
import java.util.List;

public class Checkout {

    WebDriver driver;

    public Checkout(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    By tables = By.xpath(" //table[@class ='table table-bordered']/tbody/tr");

    public int confirmCheckoutSize(){

       List<WebElement> rows = driver.findElements(tables);
       int rowsSize = rows.size();
       return rowsSize;
    }

}
