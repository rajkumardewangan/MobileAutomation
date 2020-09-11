package com.mobile.android.Steps;

import java.util.List;
import org.openqa.selenium.support.PageFactory;
import com.utility.BasePageObject;
import com.utility.Log;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchItem extends BasePageObject {

	private MobileDriver<MobileElement> driver;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	private MobileElement searchItem;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/list_product_linear_layout")
	private List<MobileElement> getItemList;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/item_title")
	private MobileElement getItemDescription;

	public SearchItem(MobileDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * This function search the passed item on app
	 * @param itemname
	 * @throws InterruptedException
	 */
	public void searchItem(String itemname) throws InterruptedException {
		try {
			waitForElement();
			clear_textbox(searchItem);
			click_On_Element(searchItem, driver);
			enter_Value(searchItem, itemname);
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			Log.info("User search for the item :" + itemname);
		} catch (Exception e) {
			Log.error("Exception :" + e);		
		}
	}

	/**
	 * This function select the item from the search result
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unused")
	public void selectItem() throws InterruptedException {
		try {
			waitForElement();
			for (int i = 0; i < getItemList.size(); i++) {
				String itemDescription = getItemDescription.getText();
				Log.info("Item Selected :" + itemDescription);
				
				hm.put("TVDescription", itemDescription);
				click_On_Element(getItemDescription, driver);
				break;
			}
		} catch (Exception e) {
			Log.error("Exception :" + e);	
		}
	}

}
