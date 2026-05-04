package utils;
import org.apache.logging.log4j.*;

public class LogUtil {
	
	private static final Logger log = LogManager.getLogger(LogUtil.class);
	
	public static void info(String message) {
		log.info(message);
	}
	
	public static void error(String message) {
		log.error(message);
	}

	public static void warning(String message) {
		log.warn(message);
	}
}
