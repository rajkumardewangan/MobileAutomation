package com.mobile.android.Steps;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;

import java.util.List;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.utility.BasePageObject;
import com.utility.Log;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author Rajkumar.Dewangan
 *
 */
public class Checkout extends BasePageObject {
	private MobileDriver<MobileElement> driver;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Cart\"]")
	private MobileElement clickoncart;

	@AndroidFindBy(className = "android.widget.Image")
	private List<MobileElement> getitemdescriptioOnCart;  
	
	@AndroidFindBy(className = "android.widget.Image")
	private MobileElement getitemdescription;

	@AndroidFindBy(className = "android.view.View")
	private List<MobileElement> itemDescription;
	
	@AndroidFindBy(className = "android.widget.Button") 
	private List<MobileElement> itemDetailsButton;
		
	@AndroidFindBy(className = "android.webkit.WebView")
	private MobileElement emptycart;

	public Checkout(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * This function click on the cart icon
	 * @throws InterruptedException
	 */
	public void clickOnCart_Icon() throws InterruptedException {
		waitForElement();
		click_On_Element(clickoncart, driver);
		Log.info("User click on the cart icon");
	}

	/**
	 * This function the verify the item on the cart
	 */
	public void verifyItemOnCart() {
		waitForElement();
		waitForElement();
		String desc = null;
		@SuppressWarnings("unused")
		int count = 0;
		for (int i = 0; i < getitemdescriptioOnCart.size(); i++) {
			desc = getitemdescription.getText();
			if (desc.equalsIgnoreCase(hm.get("TVDescription"))) {
				count++;
				break;
			}
		}
		validateTVDetails(hm.get("TVDescription"), prop.getProperty("SCREENSIZE"), "Expected screen size is 65 inch", "Expected screen size is not 65 inch");
		validateTVDetails(hm.get("TVDescription"), prop.getProperty("RESOLUTION"), "Expected screen resolution is 4K", "Expected screen resolution is not 4K");
		validateTVDetails(hm.get("TVDescription"), prop.getProperty("TVTYPE"), "Expected Tv is Smart TV", "Expected TV is not Smart TV");
		validateTVDetails(hm.get("TVDescription"), desc, hm.get("TVDescription"), desc);
		//verifyText(desc, count,"Verify the text on checkout successfully");
	}

	/**
	 * This function verify the Descripttion of the item added to the cart
	 * @param desc
	 * @param count
	 * @param message
	 */
	private void verifyText(String desc, int count, String message) {
		if (count == 1) {
			Assert.assertEquals(count, 1, desc);
			Log.info("Expected Description :" + hm.get("TVDescription") + ", Actual Description :" + desc);
		} else {
			Log.error("Expected Description :" + hm.get("TVDescription") + ", Actual Description :" + desc);
			Assert.assertEquals(count, 1, desc);
		}
		Log.info(message);
	}

	@SuppressWarnings("rawtypes")
	public void clickOnProceed_Button() {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(500, 850)).perform();
		Log.info("User click on the Proceed to Checkout button");
	}

	/**
	 * This function perform the scroll functionality
	 * @param element
	 * @param driver
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	public void scrollUpToElement(MobileElement element, MobileDriver<MobileElement> driver)
			throws InterruptedException {
		Dimension size = driver.manage().window().getSize();
		int start_Y = (int) (size.getHeight() * 0.80);
		int end_Y = (int) (size.getHeight() * 0.20);
		int start_end_X = (int) (size.getWidth() * 0.50);
		while (!isElementDisplayed((IOSElement) element)) {
			@SuppressWarnings("unused")
			TouchAction swipe = new TouchAction(driver).press(PointOption.point(start_end_X, end_Y))
					.waitAction(waitOptions(ofSeconds(2))).moveTo(PointOption.point(start_end_X, start_Y)).release()
					.perform();
		}
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void verifyButtonDetails() {
		waitForElement();
		@SuppressWarnings("unused")
		TouchAction swipe = new TouchAction(driver).press(PointOption.point(250, 1750))
				.moveTo(PointOption.point(250, 400)).release()
				.perform();
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(new PointOption().withCoordinates(250, 1300)).perform();
		Log.info("Added item to cart");
	}
	
	
	/**
	 * This functoin verify the description
	 */
	public void verifyItemDescription() {
		waitForElement();
		waitForElement();
		String desc = null;
		int count = 0;
		for (int i = 0; i < itemDescription.size(); i++) {
			desc = itemDescription.get(i).getText();
			if (desc.equalsIgnoreCase(hm.get("TVDescription"))) {
				count++;
				break;
			}
		}
		verifyText(desc, count,"Verify the selected item description on Item Description screen");
	}

	/**
	 * This function remove item from cart
	 */
	public void removeitem() {
		waitForElement();
		for (int i = 0; i < itemDetailsButton.size(); i++) {
			itemDescription.get(1).click();
		}
		Log.info("Item removed from the cart");
	}
	
	
	public void checkEmptyCart() {
		waitForElement();
		//String value = driver.getPageSource();
		//System.out.println(value);
		Log.info("Item removed from the cart");
	}
	
}
