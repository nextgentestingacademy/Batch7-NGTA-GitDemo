package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

	@DataProvider(name="searchMovie")
	public static Object[][] searchMovieData(){
		return new Object[][] {
			{"Dhurandhar","Dhurandhar123"}
		};
	}
}
