package com.mobile.android.Test;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mobile.android.Steps.Checkout;
import com.mobile.android.Steps.Login;
import com.mobile.android.Steps.SearchItem;
import com.utility.BaseTestCases;
import com.utility.Driverfactory;
import com.utility.Log;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class BuyProduct extends BaseTestCases {

	private MobileDriver<MobileElement> driver;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		Driverfactory driverfactory = new Driverfactory();
		driver = driverfactory.setMobileDriver();
		Log.info("Test execution starts");
	}

	@Test
	public void calculator() throws Exception {
		Login loginPage = new Login(driver);
		SearchItem searchitem = new SearchItem(driver);
		Checkout checkout = new Checkout(driver);
		
	    login(loginPage);
	    searchItemOnApp(searchitem);
	    checkoutItem(checkout);
	}

	public void checkoutItem(Checkout checkout) throws InterruptedException {
		checkout.verifyItemDescription();
	    checkout.verifyButtonDetails();
	    checkout.clickOnCart_Icon();
	    checkout.verifyItemOnCart();
	    checkout.removeitem();
	}

	public void searchItemOnApp(SearchItem searchitem) throws InterruptedException {
		searchitem.searchItem(prop.getProperty("SEARCHITEM"));
	    searchitem.verticalScroll(2,driver);
	    searchitem.selectItem();
	}

	public void login(Login loginPage) {
		loginPage.clickAlreadyMemberButton();
	    loginPage.typeEmailID(prop.getProperty("USERNAME"));
	    loginPage.clickContinueButton();
	    loginPage.typePassword(prop.getProperty("PASSWORD"));
	    loginPage.clickSignInButton();
	    loginPage.verifyDashboard();
	}

	@AfterClass
	public void teardown() {
		driver.quit();
		Log.endTestCase();
	}

}
