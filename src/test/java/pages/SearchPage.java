package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utils.LogUtil;

public class SearchPage extends BaseClass{
	WebDriver driver;
	
//	By lnkMovies = By.xpath("//span[normalize-space()='Movies']");
//	By edtSearch = By.xpath("//input[@class='dds-rounded-lg dds-outline-none dds-transition-all dds-duration-200 disabled:dds-opacity-50 disabled:dds-cursor-not-allowed placeholder:dds-text-quaternary dds-h-10 dds-text-lg dds-px-3 dds-border dds-border-[1px] dds-border-solid dds-border-moderate dds-bg-transparent focus:dds-border-moderate dds-w-full dds-pl-3']");
//	By lnkSearchResult = By.xpath("//h5[@class='dds-tracking-tight dds-text-lg dds-font-semibold dds-overflow-hidden dds-whitespace-normal dds-line-clamp-2 dds-text-primary dds-leading-normal dds-my-0']");
	
	@FindBy(xpath = "//span[normalize-space()='Movies']")
	WebElement lnkMovies;
	
	@FindBy(xpath = "//input[@class='dds-rounded-lg dds-outline-none dds-transition-all dds-duration-200 disabled:dds-opacity-50 disabled:dds-cursor-not-allowed placeholder:dds-text-quaternary dds-h-10 dds-text-lg dds-px-3 dds-border dds-border-[1px] dds-border-solid dds-border-moderate dds-bg-transparent focus:dds-border-moderate dds-w-full dds-pl-3']")
	WebElement edtSearch;
	
	@FindBy(xpath = "//h5[@class='dds-tracking-tight dds-text-lg dds-font-semibold dds-overflow-hidden dds-whitespace-normal dds-line-clamp-2 dds-text-primary dds-leading-normal dds-my-0']")
	WebElement lnkSearchResult;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickMovies() {
		lnkMovies.click();
		LogUtil.info("Clicked on the Movies link");
	}
	
	public void enterMovie(String movie) throws InterruptedException {
		enterText(edtSearch,movie);
		Thread.sleep(3000);
		LogUtil.info("Entered the movie name " + movie + " in the search box");
	}
	
	public String fetchSearchResult() {
		LogUtil.info("Fetching the search result text");
		return fetchText(lnkSearchResult);
	}
	
	public void clickSearchResult() {
		clickElement(lnkSearchResult);
		LogUtil.info("Clicked on the search result link");
	}
}
