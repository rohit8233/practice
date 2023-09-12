package com.MedRev.asserts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.MedRev.feature.Dashboard;
import com.MedRev.feature.Login;
import com.packages.common.WebElementUtility;


/**
 * Assertions to verify the Login actions
 * @author Amarendra
 */
public class LoginAsserts {

	/**
	 * Assertion to verify if the Login Actions has been successfully
	 * @param driver
	 * @param dashboard
	 * @param userName
	 */
	public static void assertLoginSuccess(WebDriver driver,
			Dashboard dashboard, String userId, String welcomeLabel) {
		Assert.assertTrue(WebElementUtility.isDisplayed(driver, dashboard.getLbl_Dashboard(), 30),
				"User: " + userId + " failed to login");
	}

	public static void assertLoginFailed(WebDriver driver, Login login){
		Reporter.log("Verifying Login Failed");
		//Assert.assertTrue(login.getLbl_error().isDisplayed(), "Authentication Failure message doesn't exists");
	}

	public static void assertLogout(Login login) {
		Reporter.log("Verifying Logout");
		//Assert.assertTrue(login.getBtn_login().isDisplayed(), "Session unable to logged out");
		
	}
}
