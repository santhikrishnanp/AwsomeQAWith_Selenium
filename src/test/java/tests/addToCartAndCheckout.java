package tests;

import datautils.JsonReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;
import testhelpers.base.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class addToCartAndCheckout extends BaseTest {
    //a[@class="dropdown-toggle"]//span[text()='My Account']
    @Test(groups = {"Regression"})
    public void testBuyIPhone() throws InterruptedException {
        Desktops allDesktops = homePage.navigateToDesktopsPage();
        allDesktops.addToCart();
        Checkout checkout = new Checkout(driver);
        System.out.println(checkout.confirmCheckoutSize());
    }

//    @Test(dataProvider = "getData")
//    public void testLoginExistingUser(HashMap<String,String> input){
//        LoginUser loginUser = homePage.navigateToLoginPage();
//        loginUser.loginExistingUser(input.get("email"),input.get("Password"));
//
//
//    }

//    @DataProvider
//    public Object[][] getData() throws IOException
//    {
//
//
//        List<HashMap<String,String>> data = JsonReader.getJsonDataToMap(System.getProperty("user.dir")+"/src/main/java/resources/testdata.json");
//        return new Object[][]  {{data.get(0)}  };
//
//    }
}
