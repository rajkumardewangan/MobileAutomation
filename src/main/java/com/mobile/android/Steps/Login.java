package com.mobile.android.Steps;

import org.openqa.selenium.support.PageFactory;
import com.utility.BasePageObject;
import com.utility.Log;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Login extends BasePageObject {

	private MobileDriver<MobileElement> driver;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private MobileElement alreadyAmember;

	@AndroidFindBy(className = "android.widget.EditText")
	private MobileElement emailID;

	@AndroidFindBy(className = "android.widget.Button")
	private MobileElement ContinueButton;

	@AndroidFindBy(className = "android.widget.EditText")
	private MobileElement enterpassword;

	@AndroidFindBy(className = "android.widget.Button")
	private MobileElement signInButton;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Home\"]")
	private MobileElement appdashboard;
	
	public Login(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * This function click on the Already a member button on app
	 */
	public void clickAlreadyMemberButton()  {
		click_On_Element(alreadyAmember, driver);
		Log.info("User click on Already a member button");
	}

	/**
	 * This function Enter the user name on the text box
	 * @param name
	 */
	public void typeEmailID(String name) {
		enter_Value(emailID, name);
		Log.info("User enter the user name :" + name);
	}

	/**
	 * This function click on continue button
	 */
	public void clickContinueButton() {
		click_On_Element(ContinueButton, driver);
		Log.info("User click on continue button");
	}

	/**
	 * This function type the password 
	 * @param password
	 */
	public void typePassword(String password) {
		enter_Value(enterpassword, password);
		Log.info("User enter the password");
	}

	/**
	 * This function click on the sign on Button
	 */
	public void clickSignInButton()  {
		click_On_Element(signInButton, driver);
		Log.info("User click on Sign In Button");
	}

	/**
	 * This function verify the user navigate to the Dashboard
	 */
	public void verifyDashboard()  {
		String dashboard = get_attribute_value(appdashboard, driver,"content-desc");
		assertValue("Home", dashboard);
		Log.info("User Login successfully into the amazon App");
	}

}
