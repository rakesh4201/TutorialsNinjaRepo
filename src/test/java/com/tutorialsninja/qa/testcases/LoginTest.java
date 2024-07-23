package com.tutorialsninja.qa.testcases;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;




public class LoginTest extends Base {
	
	public WebDriver driver;
	LoginPage loginPage;
	@BeforeMethod
	public void setup() {
		
		loadPropertiesFile();
		driver =initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage hp = new HomePage(driver);
		loginPage= hp.navigateToLoginButton(); 
		}
		
	@AfterMethod
	public void tearDown() {
		driver.quit();
		}
	
	@Test(priority=1, dataProvider= "validCredentialSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
	   
		AccountPage accountPage=loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());
	}
	
	@DataProvider(name="validCredentialSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
		
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithInValidCredentials() {
		
		loginPage.login(Utilities.generateTimeStamp(),dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.emailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")));
		
		
		
	}
	@Test(priority =3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.login(Utilities.generateTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.emailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected warning message is not dispalyed");
		
		
	}
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.emailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected warning message is not dispalyed");
		
		}
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials()  {
		
		loginPage.login("", "");
		Assert.assertTrue(loginPage.emailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected warning message is not dispalyed");
		}
	


}
