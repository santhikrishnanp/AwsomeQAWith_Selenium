package tests;

import datautils.JsonReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginUser;
import pageObjects.RegisterUsers;
import testhelpers.base.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class registerNew extends BaseTest {
    //a[@class="dropdown-toggle"]//span[text()='My Account']
    @Test(dataProvider = "getData" ,groups = {"NewRegistration"})
    public void testRegisterNewUser(HashMap<String,String> input) throws InterruptedException {
        RegisterUsers registerUsers = homePage.navigateToRegistrationPage();
        int random = (int)(Math.random()*1000);
        String dynamicEmail = input.get("email").split("@")[0]+random+"@gmail.com";
        registerUsers.enterRegistrationDetails(input.get("name"),input.get("lname"),dynamicEmail,input.get("phone"),input.get("Password"));
        LoginUser registrationSuccess = new LoginUser(driver);
        registrationSuccess.getSuccessMessage();
//        AccountCreationSuccess accountCreationSuccess = new AccountCreationSuccess(driver);
//        accountCreationSuccess.getSuccessMessage();

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
