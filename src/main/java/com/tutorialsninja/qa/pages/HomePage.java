package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement searchBoxfield;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//actions Methods
	public void enterProductIntoSearchBox(String productText) {
		searchBoxfield.sendKeys(productText);
	}
		
		public SearchPage clickOnSearchButton() {
			searchButton.click();
		return new SearchPage(driver);
			
	}
	public SearchPage searchForAProduct(String productText) {
		
		searchBoxfield.sendKeys(productText);
		searchButton.click();
		return new SearchPage(driver);
	}
		
	public LoginPage navigateToLoginButton() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	public RegisterPage navigateToRegisterPage() {
		
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
		
	}

	
}
