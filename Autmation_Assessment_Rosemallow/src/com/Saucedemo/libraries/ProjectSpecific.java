package com.Saucedemo.libraries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class ProjectSpecific {

	public static WebDriver driver = null;
	public static Logger APP_LOGS = null;
	public static Properties OR = null;
	public static Properties CONFIG = null;
	public static HashMap hm = null;

	ProjectSpecific(WebDriver driver, Logger APP_LOGS, Properties OR,
			Properties CONFIG, HashMap hm) {
		this.driver = driver;
		this.APP_LOGS = APP_LOGS;
		this.OR = OR;
		this.CONFIG = CONFIG;
		this.hm = hm;
	}


	public void login(String un,String pwd) //login code
	{
		
		driver.findElement(By.id(OR.getProperty("Username"))).sendKeys(un);
		APP_LOGS.debug("Entering the Username");
		driver.findElement(By.id(OR.getProperty("Password"))).sendKeys(pwd);
		APP_LOGS.debug("Entering the Password");
		driver.findElement(By.id(OR.getProperty("Login"))).click();
		APP_LOGS.debug("Click the Login button");
	}
	
	public void logout()
	{
		APP_LOGS.debug("Click the Menu ");
		driver.findElement(By.id(OR.getProperty("Menu"))).click();
		APP_LOGS.debug("Click the Logout Link");
		driver.findElement(By.id(OR.getProperty("Logout"))).click();
	}
	
    // to Verify page Title	
	public void verifyTitle(String ev)
	{
		String av=driver.getTitle();
		
		System.out.println(av);
		if(av.equals(ev))
		{
			Reporter.log("Verify Title: Pass:Title  is " + av, true);
		}
		else
		{
			String msg="Verify Title : Fail:\n Title is " + av +"\n Expected title" + ev;
			Reporter.log(msg,true);
		}
	}
	
	
	
	public void verifySuccessMsg(String ev)
	{
		String av=driver.findElement(By.xpath(OR.getProperty("completeorder"))).getText();
		if(av.equals(ev))
		{
			Reporter.log("Verify  Message:Pass:Success message is " + av, true);
		}
		else
		{
			String msg="Verify  Message:Fail:\n Actual Message is " + av +"\n Expected message" + ev;
			Reporter.log(msg,true);
		}
	}
	
	

	
	public void products(){
		APP_LOGS.debug("Click the Saucelab Back Pack");
		driver.findElement(By.xpath(OR.getProperty("Saucelabbaclpack"))).click();
	}
	
	
	public void checkOut(String fn,String ln,String zip){
		APP_LOGS.debug("Add to cart the item");
		driver.findElement(By.id(OR.getProperty("Addtocart"))).click();
		APP_LOGS.debug("Click the shoppingcartlink");
		driver.findElement(By.xpath(OR.getProperty("Shoppingcart"))).click();
		APP_LOGS.debug("Click the checkout");
		driver.findElement(By.id(OR.getProperty("Checkout"))).click();
		APP_LOGS.debug("Entering the firstname");
		driver.findElement(By.id(OR.getProperty("Firstname"))).sendKeys(fn);
		APP_LOGS.debug("Entering the lastname");
		driver.findElement(By.id(OR.getProperty("Lastname"))).sendKeys(ln);
		APP_LOGS.debug("Entering the Postalcode");
		driver.findElement(By.id(OR.getProperty("Postalcode"))).sendKeys(zip);
		APP_LOGS.debug("Click the continue button");
		driver.findElement(By.id(OR.getProperty("Continue"))).click();
		APP_LOGS.debug("Click the finish button");
		driver.findElement(By.id(OR.getProperty("Finish"))).click();
		
	}

	
	// Common to all the modules
	// to naigate to test-url
	public void navigateTestURL() throws InterruptedException {
		String url = CONFIG.getProperty("testSiteName");
		driver.get(url);
		APP_LOGS.debug("Opening the URL:" + url);
		String waitTime = CONFIG.getProperty("default_implicitWait");
		driver.manage().timeouts()
				.implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);
		
	}


}
