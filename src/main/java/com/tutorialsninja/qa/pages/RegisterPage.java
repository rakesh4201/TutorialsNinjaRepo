package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telePhoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public String retrievePasswordWarning() {
		String passwordWarningText=passwordWarning.getText();
		return passwordWarningText;
		
	}
	public String retrieveTelephoneWarning() {
		String telePhoneWarningText=telePhoneWarning.getText();
		return telePhoneWarningText;
	}
	public String retrieveEmailWarning() {
		String emailWarningText=emailWarning.getText();
		return emailWarningText;
	}
	public String retrieveLastNameWarning() {
	String lastNameWarningText=	lastNameWarning.getText();
		return lastNameWarningText;
	}
	public String retrieveFirstNameWarning() {
		String firstNameWarningText=firstNameWarning.getText();
		return firstNameWarningText;
	}
	public String retrievePrivacyPolicywarning() {
		String privacypolicyWarningText= privacyPolicyWarning.getText();
		return privacypolicyWarningText;
	}
   public String retrieveDuplicateEmailAddressWarning() {
	  String duplicateEmailWarningText= duplicateEmailAddressWarning.getText();
	  return duplicateEmailWarningText;
   }
  
   public AccountSuccessPage registerWithMandatoryFields(String firstName, String lastName, String emailText, String telephoneText, String password, String Password ) {
	   firstNameField.sendKeys(firstName);
	   lastNameField.sendKeys(lastName);
	   emailField.sendKeys(emailText);
	   telephoneField.sendKeys(telephoneText);
	   passwordField.sendKeys(password);
	   confirmPasswordField.sendKeys(Password);
	   privacyPolicyField.click();
	   continueButton.click();
	   return new AccountSuccessPage(driver);
	      
   }
   public AccountSuccessPage registerWithAllFields(String firstName, String lastName,String emailText, String telephoneText,String password, String confirmPassword ) {
	   firstNameField.sendKeys(firstName);
	   lastNameField.sendKeys(lastName);
	   emailField.sendKeys(emailText);
	   telephoneField.sendKeys(telephoneText);
	   passwordField.sendKeys(password);
	   confirmPasswordField.sendKeys(confirmPassword);
	   yesNewsLetterOption.click();
	   privacyPolicyField.click();
	   continueButton.click();
	   return new AccountSuccessPage(driver);
	      
   }
public void clickOnContinueButton() {
	
	continueButton.click();
}

}
