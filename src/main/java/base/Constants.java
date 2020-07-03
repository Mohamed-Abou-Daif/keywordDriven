package base;

import utilites.LoadProperties;

public class Constants {

	// configrations
	public static String broswerName = LoadProperties.config.getProperty("browser");
	public static String url = LoadProperties.config.getProperty("url");
	
	
	// Paths
	public static String chromePath = LoadProperties.config.getProperty("chromeDriverPath");
	public static String firefoxPath = LoadProperties.config.getProperty("firefoxDriverPath");
	public static String iePath = LoadProperties.config.getProperty("ieDriverPath");
	public static String reportPath = LoadProperties.config.getProperty("reportPath");
	public static String scenarionSheetPath = System.getProperty("user.dir")+LoadProperties.config.getProperty("scenarioSheetPath");
}
