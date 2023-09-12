package com.MedRev.suits.test;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MedRev.feature.Login;
import com.MedRev.utils.SuitesHelper;
import com.packages.common.Config;
import com.packages.common.DDT;
import com.packages.common.WebDriverUtility;


public class LoginMedRevTest {
	private WebDriver driver;
	private Login login;

	/**
	 * Method To Test Login.
	 * @param tcId
	 * @param tsId
	 * @param key
	 * @param tcName
	 * @param userEmail
	 * @param pass
	 * @param success
	 */
	@Test(dataProvider = "login")
	public void login(String tcId, String tsId, String key, String tcName, String userEmail, String pass,
			String success, String welcomeLable) {
		login = new Login(driver);
		switch (key) {

		case "LOGIN_MedRev_IOT_DASHBOARD":
			login.login(userEmail, pass,welcomeLable, true);
			driver.findElement(By.xpath(""));
			break;
		}
	}

	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(@Optional String browser) {
		try {
			driver = Config.getDriverMedRev("chrome");
			WebDriverUtility.wait(5);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod() {
		SuitesHelper.closeAllBrowserWindows(driver);
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider(name = "login")
	public Object[][] data() {
		return DDT.DDTReader("DDT/Login.csv");
	}


}
