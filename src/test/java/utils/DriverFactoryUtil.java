package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactoryUtil {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void initDriver(String browser) {
		Boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
		ChromeOptions optChrome = new ChromeOptions();
		
		switch(browser) {
		case "chrome":
			if(headless) {
				optChrome.addArguments("--headless");
			}
			driver.set(new ChromeDriver(optChrome));
			break;
		case "firefox":
			FirefoxOptions optFirefox = new FirefoxOptions();
			if(headless) {
				optFirefox.addArguments("--headless");
			}
			driver.set(new FirefoxDriver(optFirefox));
			break;
		case "edge":
			EdgeOptions optEdge = new EdgeOptions();
			if(headless) {
				optEdge.addArguments("--headless");
			}
			driver.set(new EdgeDriver(optEdge));
		default:
			if(headless) {
				optChrome.addArguments("--headless");
			}
			driver.set(new ChromeDriver(optChrome));
			break;
		}
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void quitDriver() {
		if(driver.get()!=null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
