package core;

import exceptions.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class CoreUtils {
    WebDriver driver ;
    private static final int TIMEOUT = 10;
    public CoreUtils(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void waitForElementToAppear(By findBy) {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible: " + findBy, e);
        }



    }

    public void waitForWebElementToAppear(WebElement findBy) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }

    public void waitForElementToBeClickable(By findBy) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(findBy));
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new TimeoutException("Element not clickable: " + findBy, e);
        }
    }

    public void click(By locator) {
        try {
            waitForElementToBeClickable(locator);
            driver.findElement(locator).click();
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(locator.toString(), e);
        }
    }

    public void sendKeys(By locator, String text) {
        try {
            waitForElementToAppear(locator);
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(locator.toString(), e);
        }
    }

    public String getText(By locator) {
        try {
            waitForElementToAppear(locator);
            return driver.findElement(locator).getText();
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(locator.toString(), e);
        }
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void moveToElement(WebElement findBy){
        try{
            Actions action = new Actions(driver);
            action.moveToElement(findBy).build().perform();

        }catch (NoSuchElementException e){
            throw new ElementNotFoundException("Element not found: " +findBy,e);
        }

    }

}
