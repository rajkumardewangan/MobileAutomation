package com.utility;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class BasePageObject extends ReadProperty {

	protected static HashMap<String, String> hm = new HashMap<String, String>();

	public final int explicitWaitDefault = 10;

	/**
	 * To assert value
	 * @param expectedvalue
	 * @param actualValue
	 */
	public void assertValue(String expectedvalue, String actualValue) {
		if (expectedvalue.equalsIgnoreCase(actualValue)) {
			Log.info("Expected value :" + expectedvalue + " , Actual Value : " + actualValue);
		} else {
			Log.error("Expected value :" + expectedvalue + " , Actual Value : " + actualValue);
			Assert.assertEquals(expectedvalue, actualValue,
					"Expected value :" + expectedvalue + " , Actual Value : " + actualValue);
		}
	}

	/**
	 * To wait for button to be clickable
	 *
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(final WebElement element, final WebDriver driver) {
		waitForElement();
		new WebDriverWait(driver, this.explicitWaitDefault).until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * To wait for given element (By) to be present
	 *
	 * @param driver
	 * @param locator
	 */
	public void waitForElementToBePresent(final WebElement element, final WebDriver driver) {
		new WebDriverWait(driver, this.explicitWaitDefault)
				.until(ExpectedConditions.invisibilityOfAllElements(element));
	}

	/**
	 * This method will click at particular point+
	 *
	 * @param x
	 * @param y
	 * @param driver
	 */
	@SuppressWarnings("rawtypes")
	public static void clickOnPoint(int x, int y, final PerformsTouchActions driver) {
		new TouchAction(driver).press(PointOption.point(x, y)).release().perform();
	}

	/**
	 * Click on passed element 
	 * @param mobileElement
	 * @param driver
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public RemoteWebElement click_On_Element(MobileElement mobileElement, MobileDriver driver) {
		RemoteWebElement element = null;
		try {
			waitForElementToBeClickable(mobileElement, driver);
			mobileElement.click();
		} catch (Exception e) {
			Log.error("Unable to locate the element -" + mobileElement + " : Exception Message" + e);
			Assert.assertEquals(false, true,
					"Unable to locate the element -" + mobileElement + " : Exception Message" + e);
		}
		return element;
	}

	/**
	 * This fucntion return the vale for the passed web element
	 * @param mobileElement
	 * @param driver
	 * @return
	 */
	public String get_text(MobileElement mobileElement, MobileDriver<MobileElement> driver) {
		String value = null;
		try {
			waitForElementToBeClickable(mobileElement, driver);
			value = mobileElement.getText();
		} catch (Exception e) {
			Log.error("Unable to locate the element -" + mobileElement + " : Exception Message" + e);
			Assert.assertEquals(false, true,
					"Unable to locate the element -" + mobileElement + " : Exception Message" + e);
		}
		return value;
	}

	/**
	 * This fucntion return the attributr value for passed webelement
	 * @param mobileElement
	 * @param driver
	 * @param attribute
	 * @return
	 */
	public String get_attribute_value(MobileElement mobileElement, MobileDriver<MobileElement> driver,
			String attribute) {
		String value = null;
		try {
			waitForElementToBeClickable(mobileElement, driver);
			value = mobileElement.getAttribute(attribute);
		} catch (Exception e) {
			Log.error("Unable to locate the element -" + mobileElement + " : Exception Message" + e);
			Assert.assertEquals(false, true,
					"Unable to locate the element -" + mobileElement + " : Exception Message" + e);
		}
		return value;
	}

	/**
	 * This function enter the value to textbox
	 * @param mobileElement
	 * @param texttoenter
	 * @return
	 */
	public RemoteWebElement enter_Value(MobileElement mobileElement, String texttoenter) {
		RemoteWebElement element = null;
		try {
			waitForElement();
			mobileElement.sendKeys(texttoenter);
		} catch (Exception e) {
			Log.error("Unable to locate the element -" + mobileElement + " : Exception Message" + e);
			Assert.assertEquals(false, true,
					"Unable to locate the element -" + mobileElement + " : Exception Message" + e);
		}
		return element;
	}

	/**
	 * this function clear the text box
	 * @param mobileElement
	 * @return
	 */
	public RemoteWebElement clear_textbox(MobileElement mobileElement) {
		RemoteWebElement element = null;
		try {
			waitForElement();
			mobileElement.clear();
		} catch (Exception e) {
			Log.error("Unable to locate the element -" + mobileElement + " : Exception Message" + e);
			Assert.assertEquals(false, true,
					"Unable to locate the element -" + mobileElement + " : Exception Message" + e);
		}
		return element;
	}

	/**
	 * This method is for static wait
	 *
	 * @param millis
	 */
	public void staticWait(final long millis) {
		try {
			TimeUnit.MILLISECONDS.sleep(millis);
		} catch (final InterruptedException e) {
		}
	}

	/**
	 * This method is for element to be present
	 *
	 * @param millis
	 */
	public void waitForElement() {
		try {
			TimeUnit.MILLISECONDS.sleep(4000);
		} catch (final InterruptedException e) {
		}
	}

	/**
	 * This function perform the vertical scroll on mobile screen
	 * @param l
	 * @param driver
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	public void verticalScroll(int l, MobileDriver driver) throws InterruptedException {
		waitForElement();
		int start_end_X = 0;
		int end_Y = 0;
		;
		for (int i = 0; i <= l; i++) {
			Dimension size = driver.manage().window().getSize();
			int start_Y = (int) (size.getHeight() * 0.75);
			end_Y = (int) (size.getHeight() * 0.10);
			start_end_X = (int) (size.getWidth() * 0.50);
			@SuppressWarnings("unused")
			TouchAction swipe = new TouchAction(driver).press(PointOption.point(start_end_X, start_Y))
					.waitAction(waitOptions(ofSeconds(2))).moveTo(PointOption.point(start_end_X, end_Y)).release()
					.perform();
		}
		Log.info("Scrolling screen vertically");
	}

	/**
	 * This function validate the wen element presence
	 * @param element
	 * @return
	 * @throws InterruptedException
	 */
	public boolean isElementDisplayed(MobileElement element) throws InterruptedException {
		waitForElement();
		boolean isPresent = false;
		try {
			element.isDisplayed();
			isPresent = true;
		} catch (Exception e) {
			isPresent = false;
		}
		return isPresent && element.isDisplayed();
	}
	
	public void validateTVDetails(String tvdescription, String expectedvalue, String passmessage, String failmessage) {
		if(tvdescription.contains(expectedvalue)) {
			Log.info(passmessage);
		} else {
			Log.info(failmessage);
			Assert.assertEquals(true, false, failmessage);
		}
	}
}
