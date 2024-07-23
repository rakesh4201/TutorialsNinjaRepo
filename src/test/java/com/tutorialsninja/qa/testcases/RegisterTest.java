package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	RegisterPage registerPage;
	AccountSuccessPage accountSuccess;
	
	
	public WebDriver driver;
    
	
	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		driver =initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		registerPage=homePage.navigateToRegisterPage();

	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisteringAndAccountWithMandatoryFields() {
		
		accountSuccess=registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateTimeStamp(), dataProp.getProperty("telephone"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		String actualSuccessHeading = accountSuccess.retriveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading") , "Account succespage not dispalyed");
		driver.quit();
		}
	
	@Test(priority=2)
	public void verifyRegisteringAndAccountWithAllFields() {
		
		accountSuccess=registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateTimeStamp(), dataProp.getProperty("telephone"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		String actualSuccessHeading = accountSuccess.retriveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account succespage not dispalyed");
		
	}
	
	@Test(priority =3)
	public void verifyRegisteringAccountWithExisitingEmailAddress() {
		
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephone"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")), "warning message regarding duplicate email is not displyed");
		}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		
		 registerPage.clickOnContinueButton();
		

		Assert.assertTrue(registerPage.retrievePrivacyPolicywarning().contains(dataProp.getProperty("privacyPolicyWarning")));
		
		Assert.assertEquals(registerPage.retrieveFirstNameWarning(), dataProp.getProperty("firstNameWarning"));
		
		Assert.assertEquals(registerPage.retrieveLastNameWarning(), dataProp.getProperty("lastNameWarning"));
		
		Assert.assertEquals(registerPage.retrieveEmailWarning(), dataProp.getProperty("emailWarning"));
		
		Assert.assertEquals( registerPage.retrieveTelephoneWarning(), dataProp.getProperty("telephoneWarning"));
	
		Assert.assertEquals(registerPage.retrievePasswordWarning(), dataProp.getProperty("passwordWarning"));
		
	}
	

}
