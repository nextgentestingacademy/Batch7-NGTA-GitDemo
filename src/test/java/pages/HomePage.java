package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utils.LogUtil;

public class HomePage extends BaseClass{
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//Home page specific By locators
//	By imgSearch = By.xpath("//div[@class='dds-w-7 dds-h-7 dds-flex dds-items-center dds-cursor-pointer']//*[name()='svg']");
	
	@FindBy(xpath = "//div[@class='dds-w-7 dds-h-7 dds-flex dds-items-center dds-cursor-pointer']//*[name()='svg']")
	WebElement imgSearch;

	//Home page specific methods
	public void clickSearch() {
		clickElement(imgSearch);
		LogUtil.info("Clicked on the search icon");
	}
	
}
