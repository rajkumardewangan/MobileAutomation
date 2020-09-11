package com.utility;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Driverfactory extends ReadProperty{
	
	
	private MobileDriver<MobileElement> driver;
	
		public MobileDriver<MobileElement> setMobileDriver() {
			BasicConfigurator.configure();
			Log.startTestCase();
			String platfrom = prop.getProperty("Platform");
			String mobiledriverurl = prop.getProperty("MOBILEDRIVERURL");
			if(platfrom.equalsIgnoreCase("Android")) {
				setAndroidDriver(mobiledriverurl);
			} else if(platfrom.equalsIgnoreCase("IOS")) {
				setIOSDriver(mobiledriverurl);
			}
			return driver;
		}

		private void setIOSDriver(String mobiledriverurl) {
			try {
				Log.info("Starting IOS driver");
				driver = new IOSDriver<MobileElement>(new URL(mobiledriverurl), getIosCapabilities());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		private void setAndroidDriver(String mobiledriverurl) {
			try {
				Log.info("Starting Android driver");
				driver = new AndroidDriver<MobileElement>(new URL(mobiledriverurl), getAndroidCapabilities());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		private Capabilities getIosCapabilities() {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("VERSION", prop.getProperty("IOS_VERSION"));
			capabilities.setCapability("deviceName", prop.getProperty("IOS_deviceName"));
			capabilities.setCapability("platformName", prop.getProperty("IOS_platformName"));
			capabilities.setCapability("appPackage", prop.getProperty("IOS_appPackage"));
			capabilities.setCapability("appActivity", prop.getProperty("IOS_appActivity")); 
			capabilities.setCapability("automationName", prop.getProperty("IOS_automationName")); 
			capabilities.setCapability("app",  System.getProperty("user.dir") + "/Resource/Amazon_shopping.apk"); 
			capabilities.setCapability("unicodeKeyboard", "true"); 
			return capabilities;
		}

		private Capabilities getAndroidCapabilities() {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("VERSION", prop.getProperty("ANDROID_VERSION"));
			capabilities.setCapability("deviceName", prop.getProperty("ANDROID_deviceName"));
			capabilities.setCapability("platformName", prop.getProperty("ANDROID_platformName"));
			capabilities.setCapability("appPackage", prop.getProperty("ANDROID_appPackage"));
			capabilities.setCapability("appActivity", prop.getProperty("ANDROID_appActivity")); 
			capabilities.setCapability("automationName", prop.getProperty("ANDROID_automationName")); 
			capabilities.setCapability("app",  System.getProperty("user.dir") + "/Resource/Amazon_shopping.apk"); 
			capabilities.setCapability("unicodeKeyboard", "true"); 
			return capabilities;
		}

		public void closeDriver() {
			driver.quit();
		}
}
