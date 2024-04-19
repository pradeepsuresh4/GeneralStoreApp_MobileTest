package app.generalstore.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {
	public AndroidDriver driver;
	public UiAutomator2Options options;
	public AppiumDriverLocalService server;
	

	
	@BeforeClass
	public void andriodDriverSetup() throws MalformedURLException, URISyntaxException {
		// start server
		server = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\prade\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4173).build();
		server.start();
		
		//setup capabilities
		options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName("Pixel");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.androidsample.generalstore");
		options.setAppActivity("com.androidsample.generalstore.MainActivity");
		options.setChromedriverExecutable("C:\\Users\\prade\\eclipse-GitHub Projects\\GeneralStoreApk_MobileTest\\Driver\\chromedriver.exe");
		
		//setup URL for driver
		URL url = new URI("http://127.0.0.1:4173").toURL();
		driver = new AndroidDriver(url, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@AfterClass
	public void serverStop() {
		driver.quit();
		server.stop();
		
	}

	public void installApk(String path) throws MalformedURLException, URISyntaxException {
		// TODO Auto-generated method stub
		options.setApp(path);

	}

	public void openApp(String pack) throws MalformedURLException, URISyntaxException {
		// TODO Auto-generated method stub
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", pack));
	}



}
