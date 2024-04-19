package app.generalstore.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CartPage {
	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver){
		this.driver = driver;
	}

	public void productTotalCheck() {
		// TODO Auto-generated method stub
		// cart page total check
		List<WebElement> productsInCart = driver
				.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
		int count = productsInCart.size();
		Double sum = 0.0;
		for (int i = 0; i < count; i++) {
			String price = productsInCart.get(i).getAttribute("text");
			String value = price.substring(1);
			Double dValue = Double.parseDouble(value);
			sum = sum + dValue;
		}
		System.out.println(sum);

		// total amount
		String totalSum = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"))
				.getAttribute("text");
		String dTotal = totalSum.substring(1);
		Double total = Double.parseDouble(dTotal);
		System.out.println(total);
		Assert.assertEquals(total, sum);
	}
	
	public void checkBox() {
		//checkbox
				driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
	}
	
	public void verifyTermsCondition() throws InterruptedException {
		//terms and conditions
				WebElement termsCondtions = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
				((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
					    "elementId", ((RemoteWebElement) termsCondtions).getId(),"duration", "2000"
					));
				Thread.sleep(2000);
				String content  = driver.findElement(By.id("android:id/message")).getAttribute("text");
				System.out.println(content);
				//close
				driver.findElement(AppiumBy.className("android.widget.Button")).click();
	}
	
	public void proceedToWebSite() {
		//visit website to complete purchase
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
	}

}
