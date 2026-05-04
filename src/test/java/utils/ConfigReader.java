package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	static Properties prop;
	static {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("config.properties");
			prop.load(fis);
		} catch (Exception e) {

		}
	}
	
	public static String get(String key) {
		return prop.getProperty(key);
	}
}
