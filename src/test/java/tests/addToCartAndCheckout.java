package tests;

import com.sun.net.httpserver.Authenticator;
import datautils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;
import testhelpers.base.BaseTest;
import testhelpers.listeners.RetryListener;



public class  addToCartAndCheckout extends BaseTest {

    @Test(groups = {"Regression"},retryAnalyzer = RetryListener.class)
    public void testBuyIPhone() throws InterruptedException {
        Desktops allDesktops = homePage.navigateToDesktopsPage();

        allDesktops.addToCarts("iPhone");
        Checkout checkout = new Checkout(driver);
        Assert.assertEquals(checkout.confirmCheckoutSize(),1);

    }


}
