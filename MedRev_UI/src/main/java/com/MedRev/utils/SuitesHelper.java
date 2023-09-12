package com.MedRev.utils;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.MedRev.feature.Dashboard;
import com.MedRev.feature.Login;
import com.packages.common.Config;;

/**
 * 
 * @author Amarendra
 *
 */
public class SuitesHelper {

	/**
	 * Closes all browser windows
	 * 
	 * @param driver
	 */
	public static void closeAllBrowserWindows(WebDriver driver) {
		try{
			Reporter.log("Closing All Browser Instance");
			driver.quit();
		}catch(Exception e){
			
		}
		
	}

	/**
	 * Logins into the application
	 * 
	 * @param driver
	 * @return dashboard object
	 */
	public static Dashboard doLogin(WebDriver driver) {
		Login login = new Login(driver);
		Dashboard dashboard = login.login(Config.getMedrevUserId(),
				Config.getMedrevPassword(),"", true);
		return dashboard;
	}

}
