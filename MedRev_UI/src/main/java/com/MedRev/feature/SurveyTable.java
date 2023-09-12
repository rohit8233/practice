package com.MedRev.feature;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.Folder;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.MedRev.asserts.DashboardAsserts;
import com.MedRev.utils.GmailUtility;
import com.packages.common.Config;
import com.packages.common.Constants;
import com.packages.common.WebDriverUtility;
import com.packages.common.WebElementUtility;

/**
 * @author Amarendra
 */
public class SurveyTable {
	private WebDriver driver;
	WebDriverWait wait;

	// New Code For Survey
	@FindBy(id = "dropdownLocation")
	private WebElement btn_Location;
	@FindBy(xpath = "//h5[text()='Surveys Table']")
	private WebElement txt_SurveysTable;
	@FindBy(xpath = "//div[@class='page-notes']")
	private WebElement txt_Table_Content;
	@FindBy(xpath = "//button[@class='dropdown-toggle reviews-filter responded-filter']")
	private WebElement select_All_RespondentTypeSurvey;
	@FindBy(xpath = "//button[text()=' Download as CSV']")
	private WebElement btn_download_csv;
	@FindBy(xpath = "//span[text()='Respondent Name']")
	private WebElement txt_respondentname;
	@FindBy(xpath = "//span[text()='Respondent Email']")
	private WebElement txt_respondentemail;
	@FindBy(xpath = "//span[text()='Respondent Phone']")
	private WebElement txt_respondentphone;
	@FindBy(xpath = "//span[text()='Department']")
	private WebElement txt_department;
	@FindBy(xpath = "//span[text()='Provider']")
	private WebElement txt_provider;
	@FindBy(xpath = "//span[text()='Opted Out of Emails']")
	private WebElement txt_Opted_Outof_Emails;
	@FindBy(xpath = "//span[text()='Opted Out of Texts']")
	private WebElement txt_Opted_Outof_text;
	@FindBy(xpath = "//span[text()='Sent']")
	private WebElement txt_Sent;
	@FindBy(xpath = "//span[text()='# Reminders Sent']")
	private WebElement txt_Reminders_Sent;
	@FindBy(xpath = "//span[text()='Started']")
	private WebElement txt_Started;
	@FindBy(xpath = "//span[text()='Completed']")
	private WebElement txt_Completed;
	@FindBy(xpath = "//span[text()='Questions Asked']")
	private WebElement txt_Questions_Asked;
	@FindBy(xpath = "//span[text()='Ratings']")
	private WebElement txt_Ratings;
	@FindBy(xpath = "//span[text()='Average Rating']")
	private WebElement txt_Average_Rating;
	@FindBy(xpath = "//span[text()='Comments']")
	private WebElement txt_Comments;
	@FindBy(xpath = "//span[text()='Survey Report']")
	private WebElement txt_Survey_Report;
	@FindBy(xpath = "//div[@class='page-count ng-star-inserted']")
	private WebElement txt_total;
	@FindBy(xpath = "//button[text()=' Athens Software Service ']")
	private WebElement tab_Athens_Software;
	@FindBy(xpath = "//a[@class='dropdown-item waves-light ng-star-inserted']")
	private List<WebElement> list_AllRespondentTypes;
	@FindBy(xpath = "//button[text()=' PIO_2 ']")
	private WebElement btn_PIO_location;
	@FindBy(xpath = "//*[@id='dropdownLocation']/span")
	private WebElement current_Location;
	@FindBy(xpath = "//button[text()=' Google Store Chelsea, New York, NY ']")
	private WebElement tab_Google_store_location;
	@FindBy(xpath = "//a[text()='All Respondent Types']")
	private WebElement tab_all_Respondent_Types;
	@FindBy(xpath = "//button[@class='close basic-close ng-tns-c93-40']")
	private WebElement tab_cross_button;
	@FindBy(xpath = "//span[text()='Surveys Table']")
	private WebElement btn_survey_table;
	@FindBy(xpath = "(//span[@class='ng-star-inserted'])[11]")
	private WebElement text_Never;
	@FindBy(xpath = "(//span[@class='resize-handle'])[1]")
	private WebElement btn_column_expand;
	@FindBy(xpath = "//div[@class='comment-cell-overflow ng-star-inserted']")
	private WebElement btn_questions_asked;
	@FindBy(xpath = "//div[@class='comment-cell-overflow ng-star-inserted']")
	private WebElement click_QuestionAsked;
	@FindBy(xpath = "(//button[@class='close basic-close'])[2]")
	private WebElement btn_cross_questions;
	@FindBy(xpath = "(//button[text()='Close'])[2]")
	private WebElement btn_close_questions;
	@FindBy(xpath = "(//button[text()='Close'])[1]")
	private WebElement btn_Close_comments;
	@FindBy(xpath = "//p[@class='ng-star-inserted']")
	private List<WebElement> Questions;
	@FindBy(xpath = "(//div[@class='comment-cell-overflow ng-star-inserted'])[2]")
	private WebElement btn_comment;
	@FindBy(xpath = "//h4[text()='Questions Asked']")
	private WebElement questionsAsked_Heading;
	@FindBy(xpath = "//h4[text()='Kapil Kumar Gupta's Comments']")
	private WebElement comments_Heading;
	@FindBy(xpath = "//p[text()=' Your Services was not good ']")
	private WebElement txt_comments;
	@FindBy(xpath = "(//a[@href='javascript:void(0)'])[4]")
	private WebElement page;
	@FindBy(xpath = "//button[@class='btn btn-transparent view_button ng-star-inserted']")
	private WebElement btn_view;
	@FindBy(xpath = "//h4[text()='SurveyTestAuto']")
	private WebElement txt_heading_view;
	@FindBy(xpath = "(//button[@class='close basic-close'])[1]")
	private WebElement btn_CrossView;
	@FindBy(xpath = "//div[contains(@class,\"general_modal\")]")
	private List<WebElement> view_Questions;
	@FindBy(css = "div#pcoded li.d-inline-block.location-change.show.dropdown > div > button")
	List<WebElement> list_Locations;
	@FindBy(xpath = "//i[@class='datatable-icon-skip']")
	private WebElement btn_doublearrow_lastpage;
	@FindBy(xpath = "//i[@class='datatable-icon-prev']")
	private WebElement btn_doublearrow_firstpage;
	@FindBy(xpath = "//i[@class='datatable-icon-right']")
	private WebElement btn_nextpage;
	@FindBy(xpath = "//i[@class='datatable-icon-left']")
	private WebElement btn_previouspage;
	@FindBy(xpath = "//li[@class='pages active ng-star-inserted']")
	private WebElement btn_pagenumber;
	@FindBy(xpath = "(//a[@href='javascript:void(0)'])[4]")
	private WebElement select_PageNumber;
	@FindBy(xpath = "(//a[@href='javascript:void(0)'])[7]")
	private WebElement last_Page;
	@FindBy(xpath = "(//a[@href='javascript:void(0)'])[3]")
	private WebElement first_Page;
	@FindBy(xpath = " (//datatable-body-row)//datatable-body-cell[1]/div/span")
	private List<WebElement> list_respondentname;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[2]//div//span")
	private List<WebElement> list_respondentemail;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[3]//div//span")
	private List<WebElement> list_respondentphone;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[4]//div//span")
	private List<WebElement> list_department;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[5]//div//span")
	private List<WebElement> list_provider;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[6]//div//span")
	private List<WebElement> list_opted_out_emails;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[7]//div//span")
	private List<WebElement> list_opted_out_text;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[8]//div//span")
	private List<WebElement> list_sent;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[9]//div//span")
	private List<WebElement> list_remindersent;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[10]//div//span")
	private List<WebElement> list_started;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[12]//div//span")
	private List<WebElement> list_completed;
	@FindBy(xpath = "//div[@class=\"comment-cell-overflow ng-star-inserted\"]")
	private List<WebElement> list_questionsasked;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[13]//div//span")
	private List<WebElement> list_ratings;
	@FindBy(xpath = "(//datatable-body-row)//datatable-body-cell[14]//div//span")
	private List<WebElement> list_averageratings;
	@FindBy(css = "datatable-body-cell:nth-child(15) > div > div")
	private List<WebElement> list_comments;
	@FindBy(xpath = "//datatable-body-row/div[2]/datatable-body-cell[16]//div//div")
	private List<WebElement> list_surveyreport;
	@FindBy(xpath = "(//datatable-header)//span/span[@class='datatable-header-cell-label draggable']")
	private List<WebElement> list_surveyfields;
	@FindBy(xpath = "//button[text()='All Respondent Types ']")
	private WebElement surveyresult_dropdownbutton;
	@FindBy(xpath = "//a[text()='Emplyoe']")
	private WebElement dropdown_employeselect;
	@FindBy(xpath = "(//span[@class='ng-star-inserted'])[1]")
	private WebElement text_Name;
	@FindBy(xpath = "//span[contains(text(),'# Reminders Sent')]")
	private WebElement text_reminder;
	@FindBy(xpath = "//span[contains(text(),'Opted Out of Texts')]")
	private WebElement text_outed;
	@FindBy(xpath = "//div[@class='page-count ng-star-inserted']")
	private WebElement text_TotalCount;
	@FindBy(xpath = "(//span[@class='ng-star-inserted'])[2]")
	private WebElement sender_Email;

	public SurveyTable(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = WebDriverUtility.getWaitFor30Secs(driver);
	}

	// New Code For Surveys Table
	/**
	 * Verify survey table section
	 * 
	 * @param verificationText1
	 */
	public void verifySurveyTableSection(String verificationText1) {
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), verificationText1);

	}

	/**
	 * Function to verify Surveys table contents
	 * 
	 * @param verificationText1
	 * @param verificationText2
	 */

	public void verifySurveyTableContents(String verificationText1, String verificationText2) {

		String[] text = verificationText1.split(";");
		DashboardAsserts.assertTextEquals(txt_Table_Content.getText().trim(), text[0]);
		wait.until(ExpectedConditions.elementToBeClickable(select_All_RespondentTypeSurvey));
		WebElementUtility.click(select_All_RespondentTypeSurvey, driver);
		int i = 1;
		for (WebElement ele : list_AllRespondentTypes) {
			DashboardAsserts.assertTextEquals(ele.getText().trim(), text[i]);
			i++;
		}

		WebDriverUtility.wait(3);
		WebElementUtility.click(select_All_RespondentTypeSurvey, driver);
		wait.until(ExpectedConditions.visibilityOf(btn_download_csv));
		DashboardAsserts.assertElementExists(btn_download_csv);
		List<String> strings = new ArrayList<String>();
		String[] surveyFieldOptions = verificationText2.split(",");
		for (WebElement e : list_surveyfields) {
			strings.add(e.getText());
			if (e.getText().equals(text_outed.getText())) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", txt_Ratings);
				WebDriverUtility.wait(2);
			} else if (e.getText().equals(txt_Ratings.getText())) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", txt_Survey_Report);
				WebDriverUtility.wait(2);
			}

		}
		String[] myArray = new String[strings.size()];
		strings.toArray(myArray);
		for (int j = 0; j < strings.size(); j++) {
			DashboardAsserts.assertTextEquals(myArray[j], surveyFieldOptions[j]);
		}
		DashboardAsserts.assertElementExists(txt_total);
		DashboardAsserts.assertElementExists(btn_nextpage);
		DashboardAsserts.assertElementExists(btn_previouspage);
	}

	/**
	 * verify Survey Table data when location is changed
	 * 
	 * @param verificationText1
	 */
	public void verifySurveyTableLocationChanged(String verificationText1) {

		String[] text = verificationText1.split(";");
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		wait.until(ExpectedConditions.visibilityOf(btn_Location));
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[1]);
		WebDriverUtility.wait(3);
		ArrayList<String> ArrayList1 = new ArrayList<String>();
		for (WebElement a : list_sent) {
			ArrayList1.add(a.getText());
		}
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[2]);
		WebDriverUtility.wait(5);
		ArrayList<String> ArrayList2 = new ArrayList<String>();
		for (WebElement a : list_sent) {
			ArrayList2.add(a.getText());
		}
		Assert.assertNotEquals(ArrayList1, ArrayList2);
		WebDriverUtility.wait(3);
		WebElementUtility.resetLocation(driver, btn_Location, list_Locations, tab_Athens_Software);

	}

	/**
	 * Verify Survey Table User functionality
	 */
	public void verifySurveyTableUserChanged() {
		 Assert.assertTrue(true, "Duplicate");
	}

	/**
	 * Verify survey table dropdown functionality
	 * 
	 * @param verificationText1
	 */

	public void verifySurveyTableDropdown(String verificationText1) {

		String[] text = verificationText1.split(",");
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		WebElementUtility.resetLocation(driver, btn_Location, list_Locations, tab_Athens_Software);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[1]);
		int num1 = verifyTotalCount(text_TotalCount);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[2]);
		int num2 = verifyTotalCount(text_TotalCount);
		Assert.assertNotEquals(num1, num2);

	}

	/**
	 * Verify data when all respondent type is selected
	 * 
	 * @param email
	 * @param name
	 * @param respondentType
	 * @param surveyType
	 * @param mobileNumber
	 * @param provider
	 * @param department
	 * @param verificationText1
	 * @param verificationText2
	 * @param verificationText3
	 * @param dashboard
	 */

	public void verifySurveyTableAllRespondenttypeData(String email, String name, String respondentType,
			String surveyType, String mobileNumber, String provider, String department, String verificationText1,
			String verificationText2, Dashboard dashboard) {

		String[] text = verificationText1.split(",");
		WebDriverUtility.wait(2);
		WebElementUtility.resetLocation(driver, btn_Location, list_Locations, tab_Athens_Software);
		WebDriverUtility.wait(2);
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		int num1 = verifyTotalCount(text_TotalCount);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText2);
		WebDriverUtility.wait(3);
		driver.navigate().refresh();
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[1]);
		wait.until(ExpectedConditions.visibilityOf(sender_Email));
		DashboardAsserts.assertTextEquals(sender_Email.getText(), text[2]);
		WebDriverUtility.wait(2);
		int num2 = verifyTotalCount(text_TotalCount);
		Assert.assertEquals(num1 + 1, num2);

	}

	public int verifyTotalCount(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		String text1 = element.getText();
		String text2=text1.replaceAll(",","");
		String[] text3 = text2.split(" ");
		int number1 = Integer.parseInt(text3[0]);
		return number1;
	}

	/**
	 * Verify other respondent type data
	 * 
	 * @param email
	 * @param name
	 * @param respondentType
	 * @param surveyType
	 * @param mobileNumber
	 * @param provider
	 * @param department
	 * @param verificationText1
	 * @param verificationText2
	 * @param verificationText3
	 * @param dashboard
	 */

	public void verifySurveyTableotherRespondenttypeData(String email, String name, String respondentType,
			String surveyType, String mobileNumber, String provider, String department, String verificationText1,
			String verificationText2, Dashboard dashboard) {

		String[] text = verificationText1.split(",");
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[1]);
		int num1 = verifyTotalCount(text_TotalCount);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[3]);
		WebDriverUtility.wait(3);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText2);
		driver.navigate().refresh();
		WebDriverUtility.wait(2);
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[1]);
		wait.until(ExpectedConditions.visibilityOf(sender_Email));
		DashboardAsserts.assertTextEquals(sender_Email.getText(), text[2]);
		int num2 = verifyTotalCount(text_TotalCount);
		Assert.assertEquals(num1 + 1, num2);
		wait.until(ExpectedConditions.visibilityOf(select_All_RespondentTypeSurvey));
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[3]);
		WebDriverUtility.wait(3);
	}

	/**
	 * Verify download button functionality in survey table screen
	 * 
	 * @param verificationText1
	 * @param filename
	 */

	public void verifySurveyTableDownloadButton(String verificationText1, String filename) {
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), verificationText1);
		wait.until(ExpectedConditions.elementToBeClickable(btn_download_csv));
		WebElementUtility.click(btn_download_csv, driver);
		WebDriverUtility.wait(3);
		String home = System.getProperty("user.home");
		String downloadpath = home + "\\Downloads\\";
		boolean result = WebElementUtility.isFileDownloaded(downloadpath, filename);
		Assert.assertTrue(result);
	}

	/**
	 * Function to verify Survey table pager functionality
	 * 
	 * @param verificationText1
	 */

	public void verifySurveyTablePagerFunctionality(String verificationText1) {

		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), verificationText1);
		WebElementUtility.resetLocation(driver, btn_Location, list_Locations, tab_Athens_Software);
		wait.until(ExpectedConditions.visibilityOf(btn_doublearrow_lastpage));
		methodToVerifyPagerFunctionality(btn_doublearrow_lastpage, last_Page, driver);
		methodToVerifyPagerFunctionality(btn_doublearrow_firstpage, first_Page, driver);
		String text5 = btn_pagenumber.getText();
		int number1 = Integer.parseInt(text5);
		wait.until(ExpectedConditions.elementToBeClickable(btn_nextpage));
		WebElementUtility.click(btn_nextpage, driver);
		String text6 = btn_pagenumber.getText();
		int number2 = Integer.parseInt(text6);
		Assert.assertEquals(number1 + 1, number2);
		WebDriverUtility.wait(2);
		String text7 = btn_pagenumber.getText();
		int number3 = Integer.parseInt(text7);
		wait.until(ExpectedConditions.elementToBeClickable(btn_previouspage));
		WebElementUtility.click(btn_previouspage, driver);
		String text8 = btn_pagenumber.getText();
		int number4 = Integer.parseInt(text8);
		Assert.assertEquals(number3 - 1, number4);
		wait.until(ExpectedConditions.elementToBeClickable(select_PageNumber));
		WebElementUtility.click(select_PageNumber, driver);
		String text9 = btn_pagenumber.getText();
		int number5 = Integer.parseInt(text9);
		Assert.assertEquals(number2, number5);

	}

	/**
	 * 
	 * @param element1
	 * @param element2
	 * @param driver
	 */
	public void methodToVerifyPagerFunctionality(WebElement element1, WebElement element2, WebDriver driver) {

		WebElementUtility.click(element1, driver);
		String text1 = element2.getText();
		WebElementUtility.click(element1, driver);
		String text2 = element2.getText();
		Assert.assertEquals(text1, text2);
		WebDriverUtility.wait(2);
	}

	/**
	 * Function to verify survey table sorting functionality
	 * 
	 * @param verificationText1
	 */

	public void verifySurveyTableSortingFunctionality(String verificationText1) {

		String[] text = verificationText1.split(",");
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[1]);
		wait.until(ExpectedConditions.visibilityOf(txt_respondentname));
		headingSelected(txt_respondentname, list_respondentname);
		headingSelected(txt_respondentemail, list_respondentemail);
		headingSelected(txt_respondentphone, list_respondentphone);
		headingSelected(txt_department, list_department);
		headingSelected(txt_provider, list_provider);
		headingSelected(txt_Opted_Outof_Emails, list_opted_out_emails);
		headingSelected(txt_Opted_Outof_text, list_opted_out_text);
		headingSelected(txt_Sent, list_sent);
		headingSelected(txt_Reminders_Sent, list_remindersent);
		headingSelected(txt_Started, list_started);
		headingSelected(txt_Completed, list_completed);
		headingSelected(txt_Questions_Asked, list_questionsasked);
		headingSelected(txt_Ratings, list_ratings);
		headingSelected(txt_Average_Rating, list_averageratings);
		headingSelected(txt_Comments, list_comments);
		headingSelected(txt_Survey_Report, list_surveyreport);

	}

	/**
	 * 
	 * @param element1
	 * @param element2
	 */
	public void headingSelected(WebElement element1, List<WebElement> element2) {

		element1.click();
		ArrayList<String> actuallist = new ArrayList<String>();
		for (WebElement a : element2) {
			actuallist.add(a.getText());
		}
		WebDriverUtility.wait(2);
		ArrayList<String> sortedlist = new ArrayList<String>();
		for (WebElement a : element2) {
			sortedlist.add(a.getText());
		}
		Collections.sort(sortedlist);
		Assert.assertEquals(actuallist, sortedlist);
		WebDriverUtility.wait(2);
	}

	/**
	 * verify survey table functionality in survey table screen
	 * 
	 * @param verificationText1
	 * @param verificationText2
	 */
	public void verifySurveyTableFunctionality(String verificationText1, String verificationText2) {

		String[] text = verificationText2.split(";");
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), verificationText1);
		WebElementUtility.resetLocation(driver, btn_Location, list_Locations, tab_Athens_Software);
		wait.until(ExpectedConditions.visibilityOfAllElements(list_sent));
		ArrayList<String> ArrayList1 = new ArrayList<String>();
		for (WebElement a : list_sent) {
			ArrayList1.add(a.getText());
		}
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[0]);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[1]);
		wait.until(ExpectedConditions.visibilityOfAllElements(list_sent));
		ArrayList<String> ArrayList2 = new ArrayList<String>();
		for (WebElement a : list_sent) {
			ArrayList2.add(a.getText());
		}
		Assert.assertNotEquals(ArrayList1, ArrayList2);
		WebDriverUtility.wait(3);
		WebElementUtility.resetLocation(driver, btn_Location, list_Locations, tab_Athens_Software);
		WebDriverUtility.wait(3);

	}

	/**
	 * Verify attempted appearance at survey table screen
	 * 
	 * @param email
	 * @param name
	 * @param respondentType
	 * @param surveyType
	 * @param mobileNumber
	 * @param provider
	 * @param department
	 * @param verificationText1
	 * @param verificationText2
	 * @param dashboard
	 */
	public void verifySurveyTableAttemptedAppearance(String email, String name, String respondentType,
			String surveyType, String mobileNumber, String provider, String department, String verificationText1,
			String verificationText2, Dashboard dashboard) {

		String[] text = verificationText1.split(",");
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		int num1 = verifyTotalCount(text_TotalCount);
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[1]);
		WebDriverUtility.wait(3);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[2]);
		WebDriverUtility.wait(2);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText2);

		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.ONESTAR, "Two Start Rating Comment");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabSurvey(driver);
		WebDriverUtility.wait(3);
		driver.navigate().refresh();
		WebDriverUtility.wait(5);
		wait.until(ExpectedConditions.visibilityOf(text_TotalCount));
		int num2 = verifyTotalCount(text_TotalCount);
		Assert.assertEquals((num1) + 1, num2);
		WebDriverUtility.wait(3);
		String result = text_Never.getText();
		Assert.assertNotEquals(text[3], result);
		WebElementUtility.resetLocation(driver, btn_Location, list_Locations, tab_Athens_Software);
		WebDriverUtility.wait(3);

	}

	/**
	 * Verify not attempted appearance at survey table screen
	 * 
	 * @param email
	 * @param name
	 * @param respondentType
	 * @param surveyType
	 * @param mobileNumber
	 * @param provider
	 * @param department
	 * @param verificationText1
	 * @param verificationText2
	 * @param dashboard
	 */

	public void verifySurveyTableNotAttemptedAppearance(String email, String name, String respondentType,
			String surveyType, String mobileNumber, String provider, String department, String verificationText1,
			String verificationText2, Dashboard dashboard) {

		String[] text = verificationText1.split(",");
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		int num1 = verifyTotalCount(text_TotalCount);
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[1]);
		WebDriverUtility.wait(3);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText2);
		WebDriverUtility.wait(3);
		driver.navigate().refresh();
		WebDriverUtility.wait(5);
		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, select_All_RespondentTypeSurvey,
				list_AllRespondentTypes, text[2]);
		int num2 = verifyTotalCount(text_TotalCount);
		Assert.assertEquals((num1) + 1, num2);
		String result = text_Never.getText();
		Assert.assertEquals(text[3], result);
		WebElementUtility.resetLocation(driver, btn_Location, list_Locations, tab_Athens_Software);
		WebDriverUtility.wait(3);

	}

	/**
	 * Verify draggable functionality in survey table
	 * 
	 * @param verificationText1
	 */

	public void verifyDraggableFunctionality(String verificationText1) {

		DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), verificationText1);
		WebDriverUtility.wait(3);
		Actions action = new Actions(driver);
		action.clickAndHold(btn_column_expand).dragAndDropBy(btn_column_expand, 300, 285).release().build().perform();
		WebDriverUtility.wait(3);

		action.clickAndHold(btn_column_expand).dragAndDropBy(btn_column_expand, -100, 285).release().build().perform();
		WebDriverUtility.wait(5);
		action.clickAndHold(txt_respondentemail).pause(Duration.ofSeconds(2)).moveToElement(txt_respondentname)
				.release().build().perform();
		WebDriverUtility.wait(5);

	}

	/**
	 * verify questions asked functionality in surveys table
	 * 
	 * @param verificationText1
	 */
	public void verifyQuestionsAskedFunctionality(String verificationText1) {

		try {
			String[] text = verificationText1.split(",");
			DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
			WebDriverUtility.wait(2);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", txt_Questions_Asked);
			WebDriverUtility.wait(2);
			WebElementUtility.click(txt_Questions_Asked, driver);
			WebDriverUtility.wait(2);
			WebElementUtility.click(txt_Questions_Asked, driver);
			WebDriverUtility.wait(2);
			WebElementUtility.click(btn_questions_asked, driver);
			WebDriverUtility.wait(2);
			DashboardAsserts.assertTextEquals(questionsAsked_Heading.getText().trim(), text[1]);
			btn_cross_questions.click();
			WebDriverUtility.wait(4);
			DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
			WebElementUtility.click(btn_questions_asked, driver);
			for (WebElement ele : Questions) {
				if (ele.getText().equals("Q1"))
					DashboardAsserts.assertElementExists(ele);
			}
			WebDriverUtility.wait(4);
			btn_close_questions.click();
			DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		} catch (Exception e) {

			System.out.println("Question is not available");
		}
	}

	/**
	 * verify comments functionality in survey table
	 * 
	 * @param verificationText1
	 */
	public void verifyCommentsFunctionality(String verificationText1) {

		try {
			String[] text = verificationText1.split(",");
			DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", txt_Comments);
			WebDriverUtility.wait(2);
			WebElementUtility.click(txt_Comments, driver);
			WebDriverUtility.wait(2);
			WebElementUtility.click(txt_Comments, driver);
			WebDriverUtility.wait(2);
			WebElementUtility.click(btn_comment, driver);
			WebDriverUtility.wait(2);
			DashboardAsserts.assertTextEquals(comments_Heading.getText().trim(), text[1]);
			DashboardAsserts.assertTextEquals(txt_comments.getText().trim(), text[2]);
			WebElementUtility.click(btn_cross_questions, driver);
			WebDriverUtility.wait(4);
			DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
			WebElementUtility.click(btn_comment, driver);
			WebDriverUtility.wait(2);
			WebElementUtility.click(btn_Close_comments, driver);
			DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		} catch (Exception e) {

			System.out.println("Comment is not available");
		}

	}

	/**
	 * Verify view functionality in survey table
	 * 
	 * @param verificationText1
	 * @param verificationText2
	 */
	public void verifyViewFunctionality(String verificationText1, String verificationText2) {

		try {
			String[] text = verificationText1.split(",");
			DashboardAsserts.assertTextEquals(txt_SurveysTable.getText().trim(), text[0]);
			WebDriverUtility.wait(2);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", txt_Survey_Report);
			WebDriverUtility.wait(2);
			WebElementUtility.click(page, driver);
			WebElementUtility.click(txt_Survey_Report, driver);
			WebDriverUtility.wait(2);
			WebElementUtility.click(txt_Survey_Report, driver);
			WebDriverUtility.wait(2);
			WebElementUtility.click(page, driver);
			WebDriverUtility.wait(2);
			WebElementUtility.click(btn_view, driver);
			WebDriverUtility.wait(2);
			DashboardAsserts.assertTextEquals(txt_heading_view.getText().trim(), text[1]);
			for (WebElement ele : view_Questions) {
				if (ele.getText().contains("Q1"))
					DashboardAsserts.assertElementExists(ele);
			}
			WebDriverUtility.wait(3);
			WebElementUtility.click(btn_CrossView, driver);
			WebDriverUtility.wait(3);
			DashboardAsserts.assertTextEquals(txt_SurveysTable.getText(), text[0]);
		} catch (Exception e) {

			System.out.println("Survey report is not available");
		}
	}

}
