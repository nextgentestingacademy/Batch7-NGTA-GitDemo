package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.SearchPage;
import utils.ExcelUtil;
import utils.LogUtil;

public class DistrictE2ETest extends BaseClass {

	// declaring the page objects
	public static HomePage home;
	public static SearchPage search;

	@BeforeMethod(alwaysRun = true)
	public void pageInitialization() {
		// initializing the page objects
		home = new HomePage(driver);
		search = new SearchPage(driver);
	}

//	@Test(dataProvider = "searchMovie", dataProviderClass = utils.DataProviderUtil.class)
	@Test
//	public static void searchMovie(String searchText, String result) throws InterruptedException {
		public static void searchMovie() throws InterruptedException {
		LogUtil.info("Starting the searchMovie test case");
		Map<String, String> testData = ExcelUtil.getTestData("searchMovie");
		String searchText = testData.get("movieName");
		String expectedSearchResultText = testData.get("searchResult");
		
		home.clickSearch();
		search.clickMovies();
		search.enterMovie(searchText);
		String actualSearchResultText = search.fetchSearchResult();

		Assert.assertTrue(actualSearchResultText.trim().contains(expectedSearchResultText));
		search.clickSearchResult();
		ExcelUtil.updateResult("searchMovie", "Pass");
		LogUtil.info("Completed the searchMovie test case");
	}
}