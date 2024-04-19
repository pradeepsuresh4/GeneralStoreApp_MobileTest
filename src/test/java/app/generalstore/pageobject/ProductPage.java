package app.generalstore.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;

import app.generalstore.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ProductPage extends AndroidActions {
	AndroidDriver driver;

	public ProductPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void scrollToProduct(String productName) {
		// TODO Auto-generated method stub
		scrollGesture(productName);
		List<WebElement> items = driver.findElements(AppiumBy.className("android.widget.TextView"));
		int count = items.size();
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			String name = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i)
					.getText();
			if (name.equalsIgnoreCase(productName)) {
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
		// ADD ONE MORE PRODUCT TO CART
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\").instance(0)")).click();
	}

	public CartPage openCart() throws InterruptedException {
		// OPEN CART
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(1000);
		return new CartPage(driver);
	}

}
