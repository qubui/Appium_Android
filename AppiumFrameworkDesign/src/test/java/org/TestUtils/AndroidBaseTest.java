package org.TestUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.pageObjects.android.FormPage;
import org.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class AndroidBaseTest extends AppiumUtils{
	
	
	public AndroidDriver driver;
	public RemoteWebDriver remotedriver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	public WebDriverWait wait;
	@BeforeClass(alwaysRun=true)
	public void ConfigureAppium() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//resources//data.properties");
		prop.load(fis);
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		String platform = prop.getProperty("platform");
		
		
		String appPath = System.getProperty("user.dir")+"//src//test//java//org//resources//General-Store.apk";
		String chromeDriverPath = System.getProperty("user.dir")+"//src//test//java//org//resources//chromedriver.exe";
		System.out.println(ipAddress);
		System.out.println(platform);
		
		String port = prop.getProperty("port");
		File app = new File(appPath);	
		service = startAppiumServer(ipAddress,Integer.parseInt(port));
		UiAutomator2Options options = new UiAutomator2Options();
		
		if (platform.contains("Emulator")) {
			System.out.println("Run with Emulator");
			options.setDeviceName(prop.getProperty("platform")); //emulator
			//options.setDeviceName("Android Device");// real device		
			options.setChromedriverExecutable(chromeDriverPath);	
			options.setApp(appPath);	
			
			driver = new AndroidDriver(service.getUrl(), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			formPage= new FormPage(driver);
		} else if (platform.contains("RealDevice")) {
			System.out.println("Run with Android Real Device");
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//device name
			capabilities.setCapability("appium:deviceName", "V2025");
			capabilities.setCapability("appium:os_version", "Funtouch OS 12 Global");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("appium:orientation", "portrait");
			capabilities.setCapability("appium:appPackage", "com.androidsample.generalstore");
			capabilities.setCapability("appium:appActivity", "com.androidsample.generalstore.MainActivity");
			// Initialize the driver object with the URL to Appium Server passing the capabilities. Server Url may vary
			capabilities.setCapability("appium:app", appPath);
			driver = new AndroidDriver(new URL("http://127.0.1.1:4723/wd/hub"), capabilities);
			//remotedriver = new RemoteWebDriver(new URL("http://127.0.1.1:4723/wd/hub"), capabilities);
			formPage= new FormPage(driver);
			
		} else if (platform.contains("BrowserStack")) {
			System.out.println("Run with BrowserStack");
			String deviceName = prop.getProperty("deviceName");
			String platformName = prop.getProperty("platformName");
			String os_version = prop.getProperty("os_version");
			String appApi = prop.getProperty("appApi");
			String userName= "quybui_SPusXl";
			String accessKey= "CkvNqqeB9eng8TZ2gVK7";
			URL remoteUrl = new URL("http://" + userName + ":" + accessKey + "@" + "hub.browserstack.com/wd/hub");
			DesiredCapabilities caps = new DesiredCapabilities(); 
		       // Set your access credentials
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            browserstackOptions.put("userName",userName );
            browserstackOptions.put("accessKey", accessKey);
            browserstackOptions.put("buildName", "Demo_Appium_For_Android");          
		    caps.setCapability("appium:app", appApi);
		    caps.setCapability("appium:deviceName", deviceName);
		    caps.setCapability("platformName", platformName);
		    caps.setCapability("appium:os_version", os_version);
		    caps.setCapability("bstack:options", browserstackOptions);
		    driver = new AndroidDriver(remoteUrl, caps);
		    formPage= new FormPage(driver);
		}	

	}
	
	


	
	@AfterMethod
	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
        service.stop();
		}
	
}
