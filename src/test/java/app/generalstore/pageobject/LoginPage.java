package app.generalstore.pageobject;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
	AndroidDriver driver;

	public LoginPage(AndroidDriver driver) {
		this.driver = driver;

	}

	public void selectCountryDropDown() throws InterruptedException {
		// select drop down value
		driver.findElement(AppiumBy.className("android.widget.Spinner")).click();
		Thread.sleep(500);
	}

	public void selectCountry(String name) {
		// select India
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + name + "\"));"));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + name + "']")).click();

	}

	public void enterUsername(String name) {

		// enter user name
		driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(name);
	}

	public void selectGender(String gender) {
		// select gender
		if (gender.contains("Female")) {
			driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		} else {
			driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		}
	}

	public ProductPage loginApp() {
		// lets shop
		driver.findElement(AppiumBy.className("android.widget.Button")).click();
		return new ProductPage(driver);
	}
}
