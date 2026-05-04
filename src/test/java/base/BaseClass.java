package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;
import utils.DriverFactoryUtil;
import utils.LogUtil;

public class BaseClass {
	protected static WebDriver driver;
	//this comment is added directly from the github central repository
	@BeforeMethod(alwaysRun = true)
	public static void beforeMethod() {
		LogUtil.info("Browser configured and launched");
		
		String browser = ConfigReader.get("browser");
		String url = ConfigReader.get("url").trim();
		int timeout = Integer.parseInt(ConfigReader.get("timeout").trim());
		
		
		switch(browser.trim().toLowerCase()) {
		case "chromium":
			DriverFactoryUtil.initDriver("chromium");
			driver = DriverFactoryUtil.getDriver();
			break;
		case "firefox":
			DriverFactoryUtil.initDriver("firefox");
			driver = DriverFactoryUtil.getDriver();
			break;
		case "edge":
			DriverFactoryUtil.initDriver("edge");
			driver = DriverFactoryUtil.getDriver();
		default:
			DriverFactoryUtil.initDriver("chrome");
			driver = DriverFactoryUtil.getDriver();
		}
		
		// maximise the browser window
		driver.manage().window().maximize();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout)); // waits for 15 seconds, before throwing
	}

	public static void enterText(WebElement elm, String text) {
		if(elm.isDisplayed()) {
			if(elm.isEnabled()) {
				elm.sendKeys(text);
				LogUtil.info("Entered the text " + text + " in the element " + elm);
			}else {
				System.out.println("Element is not enabled");
				LogUtil.error("Element is not enabled " + elm);
			}
		}else {
			System.out.println("Element is not visible");
			LogUtil.error("Element is not visible " + elm);
		}
	}
	
	public static void clickElement(WebElement elm) {
		if(elm.isDisplayed()) {
			if(elm.isEnabled()) {
				elm.click();
			}else {
				System.out.println("Element is not enabled");
				LogUtil.error("Element is not enabled " + elm);
			}
		}else {
			System.out.println("Element is not visible");
			LogUtil.error("Element is not visible " + elm);
		}
	}
	
	public static String fetchText(WebElement elm) {
		if(elm.isDisplayed()) {
			LogUtil.info("Returning the text from the element" + elm);
			return elm.getText();
		}else {
			System.out.println("Element is not visible");
			LogUtil.error("Element is not visible " + elm);
			return "";
		}
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(){
		DriverFactoryUtil.quitDriver();
		LogUtil.info("Closing the browsers");
	}


}
