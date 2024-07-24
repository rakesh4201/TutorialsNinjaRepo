package com.tutorialsninja.qa.testcases;




import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

//updated comment 

public class SearchTest extends Base{
	
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
	    homePage = new HomePage(driver);
		
		}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
	   
		searchPage=homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		 Assert.assertTrue(searchPage.displayStatusOfHPValidProduct());
		
		}
	@Test(priority=2)
	public void verifySearchWithInValidProduct() {
		searchPage=homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), dataProp.getProperty("noProductText"));
		
		}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct() {
		searchPage =homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), dataProp.getProperty("noProductText"));
		
		
	}
	}


