package tests;

import datautils.JsonReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AccountCreationSuccess;
import pageObjects.HomePage;
import pageObjects.LoginUser;
import pageObjects.RegisterUsers;
import testhelpers.base.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class registerNew extends BaseTest {
    //a[@class="dropdown-toggle"]//span[text()='My Account']
    @Test(dataProvider = "getData")
    public void testRegisterNewUser(HashMap<String,String> input) throws InterruptedException {
        RegisterUsers registerUsers = homePage.navigateToRegistrationPage();
        registerUsers.enterRegistrationDetails(input.get("name"),input.get("lname"),input.get("email"),input.get("phone"),input.get("Password"));
        AccountCreationSuccess accountCreationSuccess = new AccountCreationSuccess(driver);
        accountCreationSuccess.getSuccessMessage();

    }

    @Test(dataProvider = "getData")
    public void testLoginExistingUser(HashMap<String,String> input){
        LoginUser loginUser = homePage.navigateToLoginPage();
        loginUser.loginExistingUser(input.get("email"),input.get("Password"));


    }

    @DataProvider
    public Object[][] getData() throws IOException
    {


        List<HashMap<String,String>> data = JsonReader.getJsonDataToMap(System.getProperty("user.dir")+"/src/main/java/resources/testdata.json");
        return new Object[][]  {{data.get(0)}  };

    }
}
