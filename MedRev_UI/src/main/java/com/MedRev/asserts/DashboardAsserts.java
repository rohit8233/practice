package com.MedRev.asserts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.MedRev.feature.Dashboard;
import com.packages.common.WebElementUtility;


/**
 * Assertions to verify the Login actions
 * @author Amarendra
 */

public class DashboardAsserts {
	/*
	 * Initializing a logger instance using the root logger
	 */
	//private static Logger logger = Logger.getRootLogger();

	/**
	 * Assertion to verify if the Login Actions has been successfully
	 * @param driver
	 * @param dashboard
	 * @param userName
	 */
	public static void assertLoginSuccess(WebDriver driver,
			Dashboard dashboard, String userName) {
		Reporter.log("Verifying Login Successfully");
		Assert.assertTrue(dashboard.getLbl_Dashboard().isDisplayed(), userName +": Login Successfully");
		Assert.assertEquals(dashboard.getLbl_Dashboard().getText(), "Welcome: " + userName, userName +": Login Successfully");
	}

	public static void assertAllTabExists(Dashboard dashboard) {
		Reporter.log("Verifying Tab Admin,Continuity,Dashboard,Patches.QuickAccess,Reporter,RMM,Scripts,Ticket displaying");

	}


	public static void assertElementExists(WebElement element, String elementText) {
		Assert.assertTrue(element.isDisplayed(), elementText +"Dose Not Exist On Page");
		Assert.assertTrue(element.getText().contains(elementText), elementText +"Dose Not Exist On Page");

	}

	public static void assertElementNotExists(WebElement element, String elementText) {
		Assert.assertFalse(element.getText().equals(elementText), elementText +"Dose Not Exist On Page");
		Assert.assertFalse(element.getText().contains(elementText), elementText +"Dose Not Exist On Page");

	}

	public static void assertElementExists(WebElement element) {
		Assert.assertTrue(element.isDisplayed(),"Element Dose Not Exist On Page");

	}

	public static void assertElementNotExists(WebElement element, WebDriver driver) {
		Assert.assertFalse(WebElementUtility.isDisplayed(driver, element) ,"Element Dose Not Exist On Page");

	}

	public static void assertTextEquals(String string, String expectedText) {
		Assert.assertEquals(string, expectedText, expectedText +"Expected text is not Matched with Object");

	}

	public static void assertCheckboxisChecked(WebElement element) {
		Assert.assertTrue(element.isSelected(), "Checkbox for is Not Selected");

	}

	public static void assertElementContainsText(String attribute, String subject) {
		Assert.assertTrue(attribute.contains(subject), "Text Not Equals");

	}

	public static void assertElementNotContainsText(String WholeText, String verificationText) {
		Assert.assertFalse(WholeText.contains(verificationText), "Text Equals");

	}

	public static void assertTrueCondition(boolean verifyDataSorted) {
		Assert.assertTrue(verifyDataSorted, "Data iS not Sorted");

	}

	public static void assertListEquals(List<String> listSubsSuper, List<String> listDefaultSuper) {
		Assert.assertEquals(listSubsSuper, listDefaultSuper, "List Item Mismatched");

	}
	
	public static void assertListNotEquals(List<String> listSubsSuper, List<String> listDefaultSuper) {
		Assert.assertNotEquals(listSubsSuper, listDefaultSuper, "List Item Mismatched");

	}

}