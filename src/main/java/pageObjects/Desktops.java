package pageObjects;

import core.CoreUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class Desktops extends CoreUtils{

    WebDriver driver;
    WebDriverWait wait;

    public Desktops(WebDriver driver){
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    private By productName = By.xpath("//div[@class='product-thumb']//div[a]");
    private By productCard = By.xpath("//h4 ");
    private By price = By.xpath("//p[@class='price']");
    private By addToCart = By.xpath("//button[contains(.,'Add to Cart')]");
    private By goToCart = By.xpath("//a[@title='Shopping Cart']");


    public String getPrice(String targetProduct){
        String mainprice ="0";
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCard));
        for(int i=0;i<products.size();i++){
            String name = products.get(i).getText();
            if (name.equalsIgnoreCase(targetProduct)){

                String iprice = driver.findElements(price).get(i).getText().trim();
                mainprice = iprice.split("\n")[0].trim();
                System.out.println("price is"+mainprice);
                break;
            }
        }
        return mainprice;
    }
    public Checkout addToCarts(String targetProduct){
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCard));
        for(int i=0;i<products.size();i++){
            String name = products.get(i).getText();
            if (name.equalsIgnoreCase(targetProduct)){

                String iprice = driver.findElements(price).get(i).getText().trim();
                String mainprice = iprice.split("\n")[0].trim();
                System.out.println("price is"+mainprice);

                driver.findElements(addToCart).get(i).click();
                break;
            }
        }
        safeClick(goToCart);
        Checkout checkout = new Checkout(driver);
        return checkout;
    }

}
