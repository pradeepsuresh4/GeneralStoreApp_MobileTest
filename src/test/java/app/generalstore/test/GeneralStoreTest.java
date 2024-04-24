package app.generalstore.test;

import org.testng.annotations.Test;

import app.generalstore.pageobject.CartPage;
import app.generalstore.pageobject.LoginPage;
import app.generalstore.pageobject.ProductPage;
import app.generalstore.utils.BaseClass;

public class GeneralStoreTest extends BaseClass {

	@Test
	public void productPurchase() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.selectCountryDropDown();
		loginPage.selectCountry("Andorra");
		loginPage.enterUsername("Pradeep Suresh");
		loginPage.selectGender("Male");
		loginPage.loginApp();
	}

	@Test(dependsOnMethods = "productPurchase")
	public void searchProduct() throws InterruptedException {
		ProductPage productpage = new ProductPage(driver);
		productpage.scrollToProduct("Nike SFB Jungle");
		productpage.openCart();
	}

	@Test(dependsOnMethods = "searchProduct")
	public void checkOut() throws InterruptedException {
		CartPage cartpage = new CartPage(driver);
		cartpage.productTotalCheck();
		cartpage.checkBox();
		cartpage.verifyTermsCondition();
		cartpage.proceedToWebSite();

	}

}
