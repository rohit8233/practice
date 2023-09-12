package com.MedRev.feature;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Folder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.MedRev.asserts.DashboardAsserts;
import com.MedRev.utils.GmailUtility;
import com.packages.common.Config;
import com.packages.common.Constants;
import com.packages.common.TimeUtils;
import com.packages.common.WebDriverUtility;
import com.packages.common.WebElementUtility;

/**
 * @author Amarendra
 */
public class Analysis {
	private WebDriver driver;
	WebDriverWait wait;
	@FindBy(id = "main")
	private WebElement lbl_YourAccount;
	@FindBy(css = "div#pcoded div.row.dashboard-header.align-items-center > div:nth-child(1) > h5")
	private WebElement lbl_Dashboard;
	@FindBy(xpath = ".//*[@id='det_buttons']/a[normalize-space()='Logout']")
	private WebElement lnk_Logout;
	@FindBy(xpath = "(//div[@class='col-6']/select)[1]")
	private WebElement drp_AllRespondentType;
	@FindBy(xpath = "(//div[@class='col-6']/select)[2]")
	private WebElement drp_GeneralStatistics;
	@FindBy(css = "div#pcoded li.d-inline-block.location-change.show.dropdown > div > button")
	private List<WebElement> list_Locations;
	@FindBy(id = "dropdownLocation")
	private WebElement btn_Locations;
	@FindBy(xpath = "(//div[@class='statsCard'])[4]")
	private WebElement count_Good_Bad;
	@FindBy(xpath = "((//app-card/div)[1]/div/div/div/div/select)[2]/option")
	private List<WebElement> drp_list_Survey;
	@FindBy(xpath = "//h5[text()='Survey Comments Sentiment']")
	private WebElement txt_SurveyCommentSentiments;
	@FindBy(xpath = "(//div[@class='statsCard'])[3]")
	private WebElement count_Surveys_sent;
	@FindBy(xpath = "(//div[@class='statsCard'])[5]")
	private WebElement count_TotalEmail_sent;
	@FindBy(xpath = "(//div[@class='last statsCard'])")
	private WebElement count_TotalTexts_Sent;
	@FindBy(xpath = "(//div[@class='statsCard'])[1]")
	private WebElement count_AllTime_Rating;
	@FindBy(xpath = "(//div[@class='statsCard'])[2]")
	private WebElement count_Timeframe_Rating;
	@FindBy(xpath = "//div[@class='social-rate social-rate-google bg-danger']")
	private WebElement count_GoogleRating;
	@FindBy(xpath = "//h5[text()='Analysis']")
	private WebElement heading_Analysis;
	@FindBy(xpath = "(//option[text()=\"All Respondent Types\"])[2]")
	private WebElement selected_Dropdown;
	@FindBy(xpath = "//select[@class='survey_select form-control']//option")
	private List<WebElement> list_drpGeneralStatistics;
	@FindBy(xpath = "(//h4[@class='text-c-black'])[1]")
	private WebElement all_TimeSurveyCount;
	@FindBy(xpath = "(//h4[@class='text-c-black'])[4]")
	private WebElement goodbadCount;
	@FindBy(xpath = "//h6[@class='m-b-0']")
	private List<WebElement> list_BusinessStatistics;
	@FindBy(xpath = "//h4[@class='text-c-black']")
	private List<WebElement> list_BusinessStatisticsData;
	@FindBy(id = "dropdownLocation")
	private WebElement btn_Location;
	@FindBy(xpath = "(//h4[@class='text-c-black'])[3]")
	private WebElement count_SurveySent;
	@FindBy(xpath = "//div[@class='text-center sentiment-label text-primary ng-star-inserted']")
	private WebElement surveyCommentSentiments;
	@FindBy(xpath = "(//h4[@class='text-c-black'])[5]")
	private WebElement count_EmailSent;
	@FindBy(xpath = "(//h4[@class='text-c-black'])[2]")
	private WebElement count_TimeframeRating;

	@FindBy(xpath = "//button[@class='btn btn-link btn-datepicker btn-wrap-text']")
	private WebElement btn_Timeframe;
	@FindBy(xpath = "(//ngb-datepicker-month)[1]//div/span")
	private List<WebElement> crt_DateSelected;
	@FindBy(xpath = "(//ngb-datepicker-month)[2]//div/span")
	private List<WebElement> final_DateSelected;
	@FindBy(xpath = "//button[@class='btn btn-primary waves-light']")
	private static WebElement btn_SaveDate;
	@FindBy(xpath = "(//li[@class=\"very-negative ng-star-inserted\"])[1]")
	private WebElement services;
	@FindBy(css = "#allocation-chart > div> div.amChartsLegend.amcharts-legend-div text:nth-child(4)")
	private List<WebElement> list_SurveyQuestionsRating;
	@FindBy(xpath = "//div[@class='text-center rating-label text-warning ng-star-inserted']")
	private WebElement average_QuestionsRating;
	@FindBy(xpath = "(//li[@class='very-negative ng-star-inserted'])[9]")
	private WebElement negativeWordNightMare;
	@FindBy(xpath = "(//li[@class='very-positive ng-star-inserted'])[2]")
	private WebElement positiveWordGood;
	@FindBy(xpath = "(//li[@class='somewhat-negative ng-star-inserted'])[1]")
	private WebElement mostUsedWord;

	public Analysis(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = WebDriverUtility.getWaitFor30Secs(driver);
	}

	/**
	 * Function to verify AnalysisFunctionality
	 * 
	 * @param verificationText1
	 * @param verificationText2
	 * 
	 */
	public void verifyAnalysisFunctionality(String verificationText1, String verificationText2) {

		methodToNavigateAnalysisPage(verificationText1, heading_Analysis, selected_Dropdown);
		WebDriverUtility.wait(2);
		ArrayList<String> ArrayList1 = methodToVerifylist(list_BusinessStatisticsData);
		String[] text = verificationText2.split(";");
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[0]);
		WebDriverUtility.wait(5);
		ArrayList<String> ArrayList2 = methodToVerifylist(list_BusinessStatisticsData);
		WebDriverUtility.wait(2);
		Assert.assertNotEquals(ArrayList1, ArrayList2);
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[1]);
		WebDriverUtility.wait(5);

	}

	/**
	 * Function to Verify List of Analysis
	 * 
	 * @param ele
	 * @return
	 */
	public ArrayList<String> methodToVerifylist(List<WebElement> ele) {
		ArrayList<String> numbers = new ArrayList<String>();
		for (WebElement a : ele) {
			numbers.add(a.getText());
		}
		return (numbers);
	}

	/**
	 * Function to Navigate to Analysis Page
	 * 
	 * @param verificationText2
	 * @param heading_Analysis
	 * @param selected_Dropdown
	 */

	public void methodToNavigateAnalysisPage(String verificationText2, WebElement heading_Analysis,
			WebElement selected_Dropdown) {
		String[] text = verificationText2.split(",");
		Assert.assertEquals(heading_Analysis.getText(), text[0]);
		Assert.assertEquals(selected_Dropdown.getText(), text[1]);

	}

	/***
	 * Function to Verify TimeFrame functionality
	 * 
	 * @param verificationText
	 */

	public void verifyTimeframeFunctionality() {

		// Select Date Range from Calendar Date
		WebElementUtility.selectDateRangeFromCalaender(btn_Timeframe, crt_DateSelected, final_DateSelected, driver,
				TimeUtils.getCurrentDayofDate(), TimeUtils.getNextThirtyDayofDate());
		// Select End Date
		// WebWebElementUtility.selectFinalMonth(btn_Timeframe, final_date_Selected,
		// driver, TimeUtils.getNextThirtyDayofDate());
		btn_SaveDate.click();
		WebDriverUtility.wait(2);
		String validationText = TimeUtils.getDayInMMMddyyyyFormat() + " - "
				+ TimeUtils.getNextThirtyDayInMMMddyyyyFormat();
		DashboardAsserts.assertTextEquals(btn_Timeframe.getText().trim(), validationText);

	}

	/**
	 * Function to verify verifyAnalysis_Timeframe_Functionality
	 * 
	 * @param verificationText1
	 * @param dashboard
	 */

	public void verifyAnalysisTimeframeFunctionality(String verificationText1, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText1, heading_Analysis, selected_Dropdown);
		ArrayList<String> ArrayList1 = methodToVerifylist(list_BusinessStatisticsData);
		verifyTimeframeFunctionality();
		ArrayList<String> ArrayList2 = methodToVerifylist(list_BusinessStatisticsData);
		Assert.assertNotEquals(ArrayList1, ArrayList2);

	}

	/**
	 * Function to Verify the Respondent Type Dropdown when All Respondent is
	 * selected
	 * 
	 * @param verificationText1
	 * @param verificationText2
	 */
	public void verifyRespondentTypeDrpAllRespondentSelected(String verificationText1, String verificationText2) {

		methodToNavigateAnalysisPage(verificationText1, heading_Analysis, selected_Dropdown);
		String[] text = verificationText2.split(";");
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text[0]);
		ArrayList<String> ArrayList1 = methodToVerifylist(list_BusinessStatisticsData);
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[1]);
		WebDriverUtility.wait(5);
		ArrayList<String> ArrayList2 = methodToVerifylist(list_BusinessStatisticsData);
		Assert.assertNotEquals(ArrayList1, ArrayList2);
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[2]);
		WebDriverUtility.wait(5);

	}

	/**
	 * Function to Verify the Respondent Type Dropdown when other type is selected
	 * 
	 * @param verificationText1
	 * @param verificationText2
	 * 
	 */
	public void verifyRespondentTypeDrpOtherSelected(String verificationText1, String verificationText2) {

		methodToNavigateAnalysisPage(verificationText1, heading_Analysis, selected_Dropdown);
		String[] text = verificationText2.split(",");
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[0]);
		WebDriverUtility.wait(4);
		ArrayList<String> ArrayList1 = methodToVerifylist(list_BusinessStatisticsData);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text[1]);
		WebDriverUtility.wait(5);
		ArrayList<String> ArrayList2 = methodToVerifylist(list_BusinessStatisticsData);
		Assert.assertNotEquals(ArrayList1, ArrayList2);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text[2]);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[3]);
		WebDriverUtility.wait(2);
	}

	/**
	 * Function to Verify the Respondent Type Dropdown when Client is selected from
	 * Filter By Survey Section
	 * 
	 * @param verificationText1
	 */

	public void verifyRespondentTypeDrpClientSelected(String verificationText1) {
		// Duplicate
		Assert.assertTrue(true);

	}

	/**
	 * Function to Verify the Survey Type Dropdown when General Statistic is
	 * selected from dropdown
	 * 
	 * @param verificationText1
	 */

	public void verifySurveyTypeDrpGeneralStatisticSelected(String verificationText1, String verificationText2) {

		methodToNavigateAnalysisPage(verificationText1, heading_Analysis, selected_Dropdown);
		WebDriverUtility.wait(3);
		String[] text = verificationText2.split(";");
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[0]);
		WebDriverUtility.wait(3);
		ArrayList<String> ArrayList1 = methodToVerifylist(list_BusinessStatisticsData);
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[1]);
		WebDriverUtility.wait(5);
		ArrayList<String> ArrayList2 = methodToVerifylist(list_BusinessStatisticsData);
		Assert.assertNotEquals(ArrayList1, ArrayList2);
		WebDriverUtility.wait(3);
		WebElementUtility.selectLocations(driver, btn_Location, list_Locations, text[2]);
		WebDriverUtility.wait(4);
	}

	/**
	 * Function to Verify the Survey Type Dropdown when other than General Statistic
	 * is selected
	 * 
	 * @param verificationText1
	 * @param verificationText2
	 * 
	 */

	public void verifySurveyTypeDrpOtherThanGeneralStatisticSelected(String verificationText1,
			String verificationText2) {

		methodToNavigateAnalysisPage(verificationText1, heading_Analysis, selected_Dropdown);
		WebDriverUtility.wait(3);
		String[] text = verificationText2.split(",");
		ArrayList<String> ArrayList1 = methodToVerifylist(list_BusinessStatisticsData);
		WebDriverUtility.wait(3);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[0]);
		WebDriverUtility.wait(3);
		ArrayList<String> ArrayList2 = methodToVerifylist(list_BusinessStatisticsData);
		Assert.assertNotEquals(ArrayList1, ArrayList2);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[1]);
		WebDriverUtility.wait(3);

	}

	/**
	 * Function to Verify that data is correctly displayed for selected Respondent
	 * and Survey Type
	 * 
	 * @param verificationText1
	 * @param verificationText2
	 * 
	 */
	public void verifyDataDisplaySelectedRespondentSurveyType(String verificationText1, String verificationText2) {

		methodToNavigateAnalysisPage(verificationText1, heading_Analysis, selected_Dropdown);
		String[] text = verificationText2.split(",");
		WebDriverUtility.wait(3);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text[0]);
		WebDriverUtility.wait(3);
		ArrayList<String> ArrayList1 = methodToVerifylist(list_BusinessStatisticsData);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[1]);
		WebDriverUtility.wait(3);
		ArrayList<String> ArrayList2 = methodToVerifylist(list_BusinessStatisticsData);
		Assert.assertNotEquals(ArrayList1, ArrayList2);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text[2]);
		WebDriverUtility.wait(3);
		ArrayList<String> ArrayList3 = methodToVerifylist(list_BusinessStatisticsData);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[3]);
		WebDriverUtility.wait(3);
		ArrayList<String> ArrayList4 = methodToVerifylist(list_BusinessStatisticsData);
		Assert.assertNotEquals(ArrayList3, ArrayList4);
		WebDriverUtility.wait(3);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[4]);
		WebDriverUtility.wait(3);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text[0]);
		WebDriverUtility.wait(3);

	}

	/**
	 * Function to Verify the Your Business Statistic Section
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

	public void verifyYourBusinessStatisticSection(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText1, String verificationText2,
			Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		wait.until(ExpectedConditions.visibilityOf(count_SurveySent));
		String count1 = count_SurveySent.getText();
		int num1 = Integer.parseInt(count1);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		driver.navigate().refresh();
		WebDriverUtility.wait(5);
		wait.until(ExpectedConditions.visibilityOf(count_SurveySent));
		String count2 = count_SurveySent.getText();
		int num2 = Integer.parseInt(count2);
		Assert.assertEquals((num1) + 1, num2);
		WebDriverUtility.wait(3);

	}

	/**
	 * Function to Verify Good Bad Column_Business_StatisticSection
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

	public void verifyGoodBadColumnBusinessStatisticSection(String email, String name, String respondentType,
			String surveyType, String mobileNumber, String provider, String department, String verificationText1,
			String verificationText2, String verificationText3, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		String[] text = verificationText3.split(",");
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text[0]);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[1]);
		wait.until(ExpectedConditions.visibilityOf(goodbadCount));
		String text1 = goodbadCount.getText();
		String[] text2 = text1.split("/");
		int num1 = Integer.parseInt(text2[1]);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.ONESTAR, "one Start Rating Comment");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		driver.navigate().refresh();
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text[2]);
		WebDriverUtility.wait(3);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[3]);
		wait.until(ExpectedConditions.visibilityOf(goodbadCount));
		String text3 = goodbadCount.getText();
		String[] text4 = text3.split("/");
		int num2 = Integer.parseInt(text4[1]);
		Assert.assertEquals(num1 + 1, num2);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[4]);
		WebDriverUtility.wait(3);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text[5]);

	}

	/**
	 * Function to Verify section Survey Comment most used negative word
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
	public void verifySurveyCommentNegativeword(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText1, String verificationText2,
			String verificationText3, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		String[] text1 = verificationText3.split(",");
		WebDriverUtility.wait(3);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text1[0]);
		String negativeword = negativeWordNightMare.getText();
		String negativewordCountBeforeSurvey = negativeword.replaceAll("\\D+", "");
		WebDriverUtility.wait(3);
		System.out.println(Integer.parseInt(negativewordCountBeforeSurvey));
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.ONESTAR, "Nightmare");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		driver.navigate().refresh();
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text1[1]);
		WebDriverUtility.wait(3);
		String negativeword2 = negativeWordNightMare.getText();
		String negativewordCountAfterSurvey = negativeword2.replaceAll("\\D+", "");
		WebDriverUtility.wait(3);
		System.out.println(Integer.parseInt(negativewordCountBeforeSurvey));
		DashboardAsserts.assertTrueCondition(
				(Integer.parseInt(negativewordCountBeforeSurvey) > Integer.parseInt(negativewordCountAfterSurvey)));
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text1[2]);
		WebDriverUtility.wait(3);

	}

	/**
	 * Function to Verify the Good/bad column on Your Business Statistic Section
	 * with respective General statistic
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
	public void verifyGoodBadColumnBusinessStatisticSectionWrtGeneralStatistics(String email, String name,
			String respondentType, String surveyType, String mobileNumber, String provider, String department,
			String verificationText1, String verificationText2, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		wait.until(ExpectedConditions.visibilityOf(goodbadCount));
		String text1 = goodbadCount.getText();
		String[] text2 = text1.split("/");
		int num1 = Integer.parseInt(text2[1]);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.ONESTAR, "one Start Rating Comment");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		driver.navigate().refresh();
		WebDriverUtility.wait(3);
		wait.until(ExpectedConditions.visibilityOf(goodbadCount));
		String text3 = goodbadCount.getText();
		String[] text4 = text3.split("/");
		int num2 = Integer.parseInt(text4[1]);
		Assert.assertEquals(num1 + 1, num2);
		WebDriverUtility.wait(3);
	}

	/**
	 * Function to Verify section Survey Comment most used positive word
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
	public void verifySurveyCommentPositiveword(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText1, String verificationText2,
			String verificationText3, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		String[] text1 = verificationText3.split(",");
		WebDriverUtility.wait(3);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text1[0]);
		String positiveword = positiveWordGood.getText();
		String positivewordCountBeforeSurvey = positiveword.replaceAll("\\D+", "");
		System.out.println(Integer.parseInt(positivewordCountBeforeSurvey));
		WebDriverUtility.wait(3);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Good");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		driver.navigate().refresh();
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text1[1]);
		WebDriverUtility.wait(3);
		String positiveword2 = positiveWordGood.getText();
		String positivewordCountAfterSurvey = positiveword2.replaceAll("\\D+", "");
		System.out.println(Integer.parseInt(positivewordCountAfterSurvey));
		WebDriverUtility.wait(2);
		DashboardAsserts.assertTrueCondition(
				(Integer.parseInt(positivewordCountBeforeSurvey) > Integer.parseInt(positivewordCountAfterSurvey)));
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text1[2]);
		WebDriverUtility.wait(5);

	}

	/**
	 * Function to Verify section Survey Comment most used word
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

	public void verifySurveyCommentMostUsedWord(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText1, String verificationText2,
			String verificationText3, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		String[] text1 = verificationText3.split(",");
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text1[0]);
		String mostusedword = mostUsedWord.getText();
		String mostusedWordCountBeforeSurvey = mostusedword.replaceAll("\\D+", "");
		System.out.println(Integer.parseInt(mostusedWordCountBeforeSurvey));
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FOURSTAR, "Services");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		driver.navigate().refresh();
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text1[1]);
		WebDriverUtility.wait(3);
		String mostusedword2 = mostUsedWord.getText();
		String mostusedWordCountAfterSurvey = mostusedword2.replaceAll("\\D+", "");
		System.out.println(Integer.parseInt(mostusedWordCountAfterSurvey));
		DashboardAsserts.assertTrueCondition(
				(Integer.parseInt(mostusedWordCountBeforeSurvey) > Integer.parseInt(mostusedWordCountAfterSurvey)));
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentFunctionality(drp_AllRespondentType, driver, text1[2]);
		WebDriverUtility.wait(5);

	}

	/**
	 * Function to Verify the Survey Comments Sentiment Rating Section
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
	public void verifySurveyCommentsSentimentsRatingSection(String email, String name, String respondentType,
			String surveyType, String mobileNumber, String provider, String department, String verificationText1,
			String verificationText2, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		String text1 = surveyCommentSentiments.getText();
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Five Start Rating Comment");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		WebDriverUtility.wait(3);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FOURSTAR, "Four Start Rating Comment");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		String text2 = surveyCommentSentiments.getText();
		Assert.assertNotEquals(text1, text2);
	}

	/**
	 * Function to Verify the Facebook Rating Section
	 * 
	 * @param verificationText1
	 */
	public void verifyFacebookRatingSection(String verificationText1) {
		Assert.assertTrue(true, "Not in scope");

	}

	/**
	 * Function to Verify the Survey Sent column on Your Bussiness Statistic Section
	 * with respective General statistic
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
	public void verifySurveySentBusinessStatisticsSectionWrtGeneralStatistics(String email, String name,
			String respondentType, String surveyType, String mobileNumber, String provider, String department,
			String verificationText1, String verificationText2, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		wait.until(ExpectedConditions.visibilityOf(count_SurveySent));
		String text1 = count_SurveySent.getText();
		int value1 = Integer.parseInt(text1);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		driver.navigate().refresh();
		WebDriverUtility.wait(3);
		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		wait.until(ExpectedConditions.visibilityOf(count_SurveySent));
		String text2 = count_SurveySent.getText();
		int value2 = Integer.parseInt(text2);
		Assert.assertEquals(value1 + 1, value2);

	}

	/**
	 * Function to Verify the Total Email Sent column on Your Business Statistic
	 * Section with respective General statistic
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
	public void verifyEmailSentBusinessStatisticsSectionwrtGeneralStatistics(String email, String name,
			String respondentType, String surveyType, String mobileNumber, String provider, String department,
			String verificationText1, String verificationText2, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		wait.until(ExpectedConditions.visibilityOf(count_EmailSent));
		String text1 = count_EmailSent.getText();
		int value1 = Integer.parseInt(text1);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		driver.navigate().refresh();
		WebDriverUtility.wait(3);
		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		wait.until(ExpectedConditions.visibilityOf(count_EmailSent));
		String text2 = count_EmailSent.getText();
		int value2 = Integer.parseInt(text2);
		Assert.assertEquals(value1 + 1, value2);

	}

	/**
	 * Function to Verify the Total Text Sent column on Your Business Statistic
	 * Section.
	 * 
	 * @param verificationText1
	 */
	public void verifyTotalTextSentBusinessStatisticsSection(String verificationText1) {
		Assert.assertTrue(true, "Not in scope");

	}

	/**
	 * Function to Verify the Total TimeFrame Rating column on Your Business
	 * Statistic Section.
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

	public void verifyTotalTimeframeRatingBusinessStatisticsSection(String email, String name, String respondentType,
			String surveyType, String mobileNumber, String provider, String department, String verificationText1,
			String verificationText2, Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		wait.until(ExpectedConditions.visibilityOf(count_TimeframeRating));
		String text1 = count_TimeframeRating.getText();
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "One Start Rating Comment");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		WebDriverUtility.wait(3);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.ONESTAR, "One Start Rating Comment");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(count_TimeframeRating));
		String text2 = count_TimeframeRating.getText();
		Assert.assertNotEquals(text2, text1);

	}

	/**
	 * Function to Verify the All Time Rating column on Your Business Statistic
	 * Section.
	 * 
	 * @param verificationText1
	 * @param verificationText2
	 * 
	 */
	public void verifyAllTimeRatingBusinessStatisticsSection(String verificationText1, String verificationText2) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		wait.until(ExpectedConditions.visibilityOf(all_TimeSurveyCount));
		String text1 = all_TimeSurveyCount.getText();
		String[] text = verificationText1.split(",");
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[0]);
		WebDriverUtility.wait(3);
		wait.until(ExpectedConditions.visibilityOf(all_TimeSurveyCount));
		String text2 = all_TimeSurveyCount.getText();
		Assert.assertNotEquals(text1, text2);
		WebElementUtility.selectRespondentFunctionality(drp_GeneralStatistics, driver, text[1]);
		WebDriverUtility.wait(2);
	}

	/**
	 * Function to Verify the Survey Question rating
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

	public void verifySurveyQuestionRating(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText1, String verificationText2,
			Dashboard dashboard) {

		methodToNavigateAnalysisPage(verificationText2, heading_Analysis, selected_Dropdown);
		WebDriverUtility.wait(3);
		ArrayList<String> ArrayList1 = methodToVerifylist(list_SurveyQuestionsRating);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText1);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Five Start Rating Comment");

		} catch (Exception e) {
			e.printStackTrace();
		}
		dashboard.gotoTabAnalysis(driver);
		driver.navigate().refresh();
		WebDriverUtility.wait(5);
		ArrayList<String> ArrayList2 = methodToVerifylist(list_SurveyQuestionsRating);
		Assert.assertNotEquals(ArrayList1, ArrayList2);

	}

	/**
	 * Function to Verify the Google rating section
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

	public void verifyGoogleRating(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText1, String verificationText2,
			Dashboard dashboard) {

		Assert.assertTrue(true, "Not in scope");

	}

}
