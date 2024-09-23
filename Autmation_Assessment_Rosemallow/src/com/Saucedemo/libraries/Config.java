package com.Saucedemo.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Config {

	public static WebDriver driver = null;
	public static ProjectSpecific ps = null;
	public static Logger APP_LOGS = null;
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static HashMap hm = new HashMap();

	@BeforeSuite
	public void preCondition() throws IOException {
		APP_LOGS = Logger.getLogger("devpinoyLogger");
		APP_LOGS.debug("Loading Property files");
		CONFIG = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
				+ "//src//com//Saucedemo//config//config.properties");
		CONFIG.load(ip);

		OR = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")
				+ "//src//com//Saucedemo//config//OR.properties");
		OR.load(ip);
		APP_LOGS.debug("Loaded Property files successfully");

		String browserType = CONFIG.getProperty("browserType");

		if (browserType.equalsIgnoreCase("Mozilla")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			APP_LOGS.debug("Launching Browser");
			Reporter.log("Opening Firefox", true);
		}else if(browserType.equalsIgnoreCase("Chrome")){
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			APP_LOGS.debug("Launching Browser");
			Reporter.log("Opening Chrome", true);
			
		}

		Generic.deleteRecursive(new File("./Screenshot/"));
		ps = new ProjectSpecific(driver, APP_LOGS, OR, CONFIG, hm);
	}

	@AfterSuite
	public void afterSuiteMethod() throws IOException {

		driver.quit();
		APP_LOGS.debug("Quits the session");

		Generic.writeRes("./ModuleController/ModuleController.xlsx", hm);

	}

}
