package com.MedRev.feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.MedRev.asserts.LoginAsserts;
import com.packages.common.WebDriverUtility;



/***
 * 
 * @author AmarendraSrivastava
 *
 */

public class Login {
	private WebDriver driver;
	private WebDriverWait wait;
	@FindBy(name="email")
	private WebElement txt_Email;
	@FindBy(name="password")
	private WebElement txt_Password;
	@FindBy(css="div > button[type='submit']")
	private WebElement btn_Login;
	
	@FindBy(id= "user_info")
	private WebElement link_SingnIn;

	public Login(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = WebDriverUtility.getWaitFor1Mins(driver);
	}


	/**
	 * Login into MedRev portal and verify if login is successful/failure
	 * @param user user's login id
	 * @param password password
	 * @param assertSuccess if true, verifies successful login; verifies failed login otherwise
	 * @return
	 */
	public Dashboard login(String email, String password, String welcomeLabel,
			boolean assertSuccess) {
		Reporter.log("Login to Portal");
		wait.until(ExpectedConditions.elementToBeClickable(txt_Email));
		Reporter.log("Entering User Name - " + email);
		txt_Email.clear();
		txt_Email.sendKeys(email);
		Reporter.log("Entering Password");
		wait.until(ExpectedConditions.elementToBeClickable(txt_Password));
		txt_Password.sendKeys(password);
		Reporter.log("Clicking on Login Button");
		wait.until(ExpectedConditions.elementToBeClickable(btn_Login));
		btn_Login.click();
		// To do Click on Yes Dialog Box
		Dashboard  dashboard = new Dashboard(driver);
		
		Reporter.log("Validating Success Login");
		if (assertSuccess) {
			LoginAsserts.assertLoginSuccess(driver, dashboard, email, welcomeLabel);
			
			
		} else {
			LoginAsserts.assertLoginFailed(driver, this);
		}
		return dashboard;
		 
	}
	
}
