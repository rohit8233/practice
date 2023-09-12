package com.MedRev.feature;
import java.util.List;

import javax.mail.Folder;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.openqa.selenium.support.ui.WebDriverWait;


import com.MedRev.asserts.DashboardAsserts;
import com.MedRev.utils.GmailUtility;

import com.packages.common.Config;
import com.packages.common.TimeUtils;
import com.packages.common.WebDriverUtility;
import com.packages.common.WebElementUtility;

import junit.framework.Assert;

/**
 * @author Amarendra
 */
public class SurveyResults {
	private WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//h5[text()='Survey Results']")
	private WebElement tabH_SurveyResults;
	@FindBy(xpath = "(//button[@class='dropdown-toggle reviews-filter responded-filter'])[1]")
	private static WebElement drp_AllRespondentType;
	@FindBy(xpath = "(//button[@class='dropdown-toggle reviews-filter responded-filter'])[2]")
	private WebElement drp_RespondedNonResponded;
	@FindBy(xpath = "//button[@class='dropdown-toggle reviews-filter opinion-filter']")
	private WebElement drp_AllReviewRating;
	@FindBy(xpath = "//a[@class='dropdown-item waves-light ng-star-inserted']")
	private static List<WebElement>list_drpAllRespondentType;
	@FindBy(xpath = "(//div[@class='dropdown-menu show'])//a")
	private List<WebElement> list_drpRespondedNonResponded;
	@FindBy(xpath = "(//div[@class='dropdown-menu show'])//a")
	private List<WebElement> list_drpAllReviewRating;
	@FindBy(xpath = "//div[@class='review-item-box ng-star-inserted']")
	private static List<WebElement> Count_SurveyAllRespondent;
	@FindBy(xpath = "//button[@class='dropdown-toggle reviews-filter']")
	private WebElement btn_Timeframe;
	@FindBy(xpath = "(//ngb-datepicker-month)[1]//div/span")
	private List<WebElement> crt_DateSelected;
	@FindBy(xpath = "(//ngb-datepicker-month)[2]//div/span")
	private List<WebElement> final_DateSelected;
	@FindBy(xpath = "//button[@class='btn btn-primary waves-light']")
	private static WebElement btn_SaveDate;
	@FindBy(xpath = "//div[@class='search form-group form-primary']//input")
	private static List<WebElement> tab_NameEmailPhone;
	@FindBy(xpath = "//button[@class='search text-c-black']")
	private static List<WebElement> btn_Search_Clear;
	@FindBy(xpath = "//div[@class='search form-group form-primary']//input[1]")
	private static WebElement tab_ReapondentName;
	@FindBy(xpath = "//div[@class='search form-group form-primary']//input[2]")
	private static WebElement tab_Email;
	@FindBy(xpath = "//div[@class='search form-group form-primary']//input[3]")
	private static WebElement tab_Phone;
	@FindBy(xpath = "(//button[@class='search text-c-black'])[1]")
	private static WebElement btn_Search;
	@FindBy(xpath = "(//button[@class='search text-c-black'])[2]")
	private static WebElement btn_Clear;
	@FindBy(xpath = "//div[@class='review-item-box ng-star-inserted']")
	private List<WebElement> Count_SurveyOfNameEmailPhone;
	@FindBy(css = "div#pcoded simple-notification > div")
	private WebElement lbl_FailedNotificationSurveyResult;
	@FindBy(xpath = "//button[@class='dropdown-toggle btn btn-outline-primary ng-tns-c93-0']")
	private WebElement btn_Locations;
	@FindBy(css = "div#pcoded li:nth-child(2) > div > h5:nth-child(2)")
	private WebElement btn_locUser;
	@FindBy(xpath = "//div[@class='navbar-container container-fluid ng-tns-c93-0']//ul[2]/li[3]/a/span")
	private WebElement btn_UserCompany;
	@FindBy(xpath = "//ul/div/li[contains(@class,'ng-tns-c93-0')][1]/div/h5[2]")
	private WebElement txt_CurrantCompany;
	@FindBy(css = "div#pcoded li:nth-child(1) > div > i")
	private WebElement btn_CompanyChange;
	@FindBy(css = "div#team-selection-menu li:nth-child(3) > div > h5")
	private WebElement txt_NewCompany2;

	@FindBy(xpath = "div#pcoded li.d-inline-block.location-change.show.dropdown > div > button")
	private List<WebElement> list_Locations;
	@FindBy(xpath = "(//button[@class='dropdown-item ng-tns-c93-0 ng-star-inserted'])[1]")
	private static WebElement def_Location;

	@FindBy(xpath="//a[text()=\"Emplyoe\"]")
	private WebElement dropdown_employeselect;

	@FindBy(xpath="(//div[@class='review-item'])[1]//div[@class='review-rating-note good']/h4")
	private WebElement ratingsForSurvey;
	@FindBy(xpath="(//div[@class='review-rating-note good'])[1]//h4")
	private WebElement verygood_rating;
	@FindBy(xpath="(//div[@class='review-rating-note mediocre'])[1]//h4")
	private WebElement average_rating;
	@FindBy(xpath="(//div[@class='review-rating-note bad'])[1]//h4")
	private WebElement low_rating;
	@FindBy(xpath="(//div[contains(text(),'Q')])[1]")
	private List<WebElement> cardblock_questions;
	@FindBy(xpath = "(//div[@class='ng-star-inserted'])[1]/textarea")
	private WebElement cardblock_dragbutton;
	@FindBy(xpath="(//div[@class='col-xl-6 col-md-12 answers_container'])[1]/div[1]")
	private WebElement btn_showmore;
	@FindBy(xpath = "(//div[@class='app-modal-header remove-modal'])[1]/button")
	private WebElement btn_showmoreClose;
	@FindBy(xpath="(//div[@class='app-modal-header remove-modal'])[1]/h4")
	private WebElement cardblock_heading;

	@FindBy(xpath = "(//div[@class='review-person'])[1]/div/div/h4")
	private WebElement surveyRespondent_name;
	@FindBy(xpath = "(//div[@class='flex ng-star-inserted'])[1]/p")
	private WebElement respondent_email;
	@FindBy(xpath = "(//div[@class='flex ng-star-inserted'])[3]/p")
	private WebElement doctor_name;
	@FindBy(xpath = "(//div[@class='flex ng-star-inserted'])[2]/p")
	private WebElement depart_name;
	@FindBy(xpath="(//div[@class='ng-star-inserted'])[1]/textarea[1]")
	private WebElement respondent_comments;

	@FindBy(xpath="//div[@class='general_modal ng-star-inserted'][1]")
	private WebElement survey_questions;

	@FindBy(xpath="//*[@id=\"pcoded\"]/div[2]/div/div/div/div/div/div/app-reviews/app-modal-basic[2]/div/div/div/div[1]/div/button/span")
	private WebElement btn_cross;

	@FindBy(css = "input[name='emailResponseSubject']")
	private WebElement heading_emailsubject;
	@FindBy(xpath = "//label[text()='Email Body ']")
	private WebElement heading_emailbody;
	@FindBy(name="emailResponseSubject")
	private WebElement text_surveyresponse;
	@FindBy(name="emailResponseText")
	private WebElement text_emailbody;

	@FindBy(xpath="//a[contains(text(),'Cancel')]")
	private WebElement btn_cancel;
	@FindBy(xpath="(//div[@class='review-timeline'])[1]/ul/li[4]/span[2]")
	private WebElement text_Responsetimeline;
	@FindBy(xpath = "(//div[@class='review-timeline'])[1]/ul/li[1]/span[2]")
	private WebElement text_reviewtimelineText;
	@FindBy(xpath="(//div[@class='review-timeline'])[1]/ul/li[1]/span[1]")
	private WebElement date_reviewtimelineDate;
	@FindBy(xpath = "(//div[@class='review-timeline'])[1]/ul/li/span[1]")
	private List<WebElement> list_dateTimeline;
	@FindBy(xpath = "(//div[@class='review-timeline'])[1]/ul/li/span[2]")
	private List<WebElement> list_TextTimeline;
	@FindBy(xpath="(//div[@class='ng-tns-c93-0 dropdown-menu show'])/button[2]")
	private WebElement tab_Google_store_location;
	@FindBy(xpath="(//div[@class='ng-tns-c93-0 dropdown-menu show'])/button[1]")
	private WebElement tab_Athens_Software;

	@FindBy(xpath="(//div[@class='review-person'])[1]//h4")
	private WebElement text_User1;
	@FindBy(xpath="(//div[@class='review-person'])[1]//h4")
	private WebElement text_User2;  
	@FindBy(xpath="(//div[@class='ng-tns-c93-0 dropdown-menu show'])/button[3]")
	private WebElement location_topNotch;

	@FindBy (xpath = "//div[@class='search form-group form-primary']")
	private WebElement txt_SearchRespondent;
	@FindBy(xpath = "//a[@class='dropdown-item waves-light ng-star-inserted'][1]")
	private WebElement txt_AllRespondent;
	@FindBy(xpath = "//a[@class='dropdown-item waves-light ng-star-inserted'][2]")
	private WebElement txt_Client;
	@FindBy(xpath = "(//div[@class='review-item'])[1]/div[1]/div[1]/div[1]/div/div/div[1]")
	private WebElement div_Rating;
	@FindBy(xpath = "(//div[@class='review-item'])[1]/div[1]/div[1]/div[1]/div/div/div/div/div/h4")
	private WebElement div_RatingCompany1;
	@FindBy(css = "div#pcoded input[name='name']")
	private WebElement txt_SearchName;
	@FindBy(css = "div#pcoded button[type='button']:nth-child(4)")
	private WebElement btn_SearchSurvey;

	@FindBy(xpath = "(//div[@class='review-person'])[2]/div/div[2]/i")
	private WebElement btn_EnvolopePointer;
	@FindBy(xpath = "(//div[@class='app-modal-header respond-modal'])/h4")
	private WebElement btn_ThanksByEmail;
	@FindBy(css = "div#pcoded a.btn.btn-primary.waves-light.m-r-20")
	private WebElement btn_SendResponse;
	@FindBy(css = "div#pcoded div.sn-title.ng-star-inserted")
	private WebElement txt_Success;
	@FindBy(xpath = "(//span[@class='dropdown'])[2]/div/a[3]")
	private WebElement txt_NotResponded;
	@FindBy(css= "input[name=\"emailResponseSubject\"]")
	private WebElement txt_EmailSubSurveyResponse;
	@FindBy(xpath = "(//a[@class='dropdown-item waves-light ng-star-inserted'])[1]")
	private WebElement txt_AllRespondentTypes;
	@FindBy(xpath = "(//span[@class='dropdown'])[3]/div/a[3]")
	private WebElement txt_PositiveReviews;
	@FindBy(css = "div#pcoded app-reviews > div > div.ng-star-inserted > div")
	private WebElement SpinerLoader;
	@FindBy(css = "//div[@class='navbar-container container-fluid ng-tns-c93-0']/ul[2]/li[2]/div/button[1]")
	private WebElement Location_NorthBrookeborough;
	@FindBy(xpath = "")
	private WebElement btn_OldCompany;
	@FindBy(xpath = "(//div[@id='team-selection-menu'])/li")
	List<WebElement> list_Company;




	/** function to print text for webelements
	 *
	 */


	public SurveyResults(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = WebDriverUtility.getWaitFor30Secs(driver);
	}

	/**
	 * Function to Verify that user can navigate to Survey Results Section
	 * @param verificationText
	 */
	public void verifyUserNavigateToSurveyResult(String verificationText) {
		WebDriverUtility.wait(2);
		DashboardAsserts.assertTextEquals(tabH_SurveyResults.getText(), verificationText);
	}

	/**
	 * Function to verify the functionality of "Respondent type" dropdown at Survey Results Screen
	 * @param verificationText
	 */
	public void verifyRespondentTypeDrpSurveyScreen(String verificationText) {
		String[] varText=verificationText.split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, varText[0]);
		WebDriverUtility.wait(2);
		Assert.assertTrue(varText[0], true);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyAllRespondent);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, varText[1]);
		Assert.assertTrue(varText[1], true);
		WebDriverUtility.wait(2);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyAllRespondent);
	}

	/**
	 * Function to Verify the "All Respondent type" data appearance at "Survey Results" Screen
	 * @param verificationText
	 * @param email 
	 * @param respondentType 
	 * @param surveyType 
	 * @param mobileNumber 
	 * @param provider 
	 * @param department 
	 * @param dashboard
	 */
	public void verifyAllRespondentTypeDataAppears(String email,String name, String respondentType, String surveyType, String mobileNumber, String provider, String department,String verificationText, Dashboard dashboard) {
		String[]varText=verificationText.split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, varText[1]);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department, varText[0]);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, varText[1]);
		WebDriverUtility.wait(2);
		Assert.assertNotSame(surveyRespondent_name.getText(), varText[2]); 



	}
	/**
	 * Function to Verify the "Other Respondent type" data appearance at "Survey Results" Screen
	 * @param verificationText
	 * @param dashboard 
	 */
	public void verifyOtherRespondentTypeDataAppears(String email,String name, String respondentType, String surveyType, String mobileNumber, String provider, String department,String verificationText, Dashboard dashboard) {
		String[]varText=verificationText.split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, varText[1]);
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department, varText[0]);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, varText[1]);
		WebDriverUtility.wait(2);	
		Assert.assertNotSame(surveyRespondent_name.getText(), varText[2]);   

	}
	/**
	 * Function to Verify the survey's appearance of "Responded & Not Responded" dropdown at Survey Results Screen
	 * @param verificationText
	 */
	public void verifySurveyAppearanceRespondentNonRespondentDrp(String verificationText) {
		String[] varText=verificationText.split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_RespondedNonResponded, list_drpRespondedNonResponded, varText[0]);
		wait.until(ExpectedConditions.visibilityOf(surveyRespondent_name));
		DashboardAsserts.assertTextEquals(drp_RespondedNonResponded.getText(), varText[0]);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_RespondedNonResponded, list_drpRespondedNonResponded, varText[1]);
		wait.until(ExpectedConditions.visibilityOf(surveyRespondent_name));
		DashboardAsserts.assertTextEquals(drp_RespondedNonResponded.getText(), varText[1]);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_RespondedNonResponded, list_drpRespondedNonResponded, varText[2]);
		wait.until(ExpectedConditions.visibilityOf(surveyRespondent_name));
		DashboardAsserts.assertTextEquals(drp_RespondedNonResponded.getText(), varText[2]);
	}
	/**
	 * Function to Verify the functionality of "All Review Ratings" dropdown at Survey Results Screen
	 * @param verificationText
	 */
	public void verifyAllReviewRatingDrp(String verificationText) {
		String[] varText=verificationText.split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllReviewRating, list_drpAllReviewRating, varText[0]);
		wait.until(ExpectedConditions.visibilityOf(surveyRespondent_name));
		DashboardAsserts.assertTextEquals(drp_AllReviewRating.getText(), varText[0]);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllReviewRating, list_drpAllReviewRating, varText[1]);
		wait.until(ExpectedConditions.visibilityOf(surveyRespondent_name));
		DashboardAsserts.assertTextEquals(drp_AllReviewRating.getText(), varText[1]);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllReviewRating, list_drpAllReviewRating, varText[2]);
		wait.until(ExpectedConditions.visibilityOf(surveyRespondent_name));
		DashboardAsserts.assertTextEquals(drp_AllReviewRating.getText(), varText[2]);

	}
	/**
	 * Function to Verify the "Negative Reviews Ratings" data appearance at "Survey Results" Screen
	 * @param verificationText
	 * @param dashboard 
	 */
	public void verifyNegativeReviewRating(String verificationText,String verificationText1) {
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllReviewRating, list_drpAllReviewRating, verificationText1);
		WebElementUtility.verifySurveyCount(driver, drp_AllReviewRating, Count_SurveyAllRespondent);
		wait.until(ExpectedConditions.visibilityOf(surveyRespondent_name));
		DashboardAsserts.assertTextEquals(drp_AllReviewRating.getText(), verificationText1);


	}
	/**
	 * Function to Verify the "Positive Reviews Ratings" data appearance at "Survey Results" Screen
	 * @param verificationText
	 */
	public void verifyPositiveReviewRating(String verificationText, String  verificationText1) {
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllReviewRating, list_drpAllReviewRating, verificationText1);
		WebElementUtility.verifySurveyCount(driver, drp_AllReviewRating, Count_SurveyAllRespondent);
		wait.until(ExpectedConditions.visibilityOf(surveyRespondent_name));
		DashboardAsserts.assertTextEquals(drp_AllReviewRating.getText(), verificationText1);

	}
	/**
	 * Function to Verify the functionality of "Select Timeframe" dropdown at Survey Results Screen
	 * @param verificationText
	 */
	public void verifyTimeframeDrp(String verificationText) {
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectDateRangeFromCalaender(btn_Timeframe, crt_DateSelected, final_DateSelected,  driver, TimeUtils.getCurrentDayofDate(), TimeUtils.getNextThirtyDayofDate());	
		btn_SaveDate.click();
		WebDriverUtility.wait(2);
		String validationText = TimeUtils.getDayInMMMddyyyyFormat() +" - " + TimeUtils.getNextThirtyDayInMMMddyyyyFormat();
		DashboardAsserts.assertTextEquals(btn_Timeframe.getText().trim(), validationText);

	}
	/**
	 * Function to Verify the appearance of "Search Respondents" at Survey Results Screen
	 * @param verificationText
	 */
	public void verifySearchReasultAppearance(String verificationText) {
		String [] varText =  verificationText.split(";");
		String[]text_1=varText[0].split(",");
		String[]text_2=varText[1].split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		String m=txt_SearchRespondent.getText().substring(0, 18);
		m.contains(text_1[2]);
		wait.until(ExpectedConditions.visibilityOfAllElements(btn_Search_Clear));
		WebDriverUtility.wait(3);
		int i = 0; 
		for (WebElement webElement : btn_Search_Clear) {
			DashboardAsserts.assertTextEquals(webElement.getText(), text_1[i]);
			i++;
		}	
		int j = 0; 
		for (WebElement webElement : tab_NameEmailPhone) {			
			DashboardAsserts.assertTextEquals(webElement.getAttribute("placeholder"), text_2[j]);
			j++;			
		}	


	}
	/**
	 * Function to Verify the functionality of "Search" Button at Survey Results Screen
	 * @param verificationText
	 */
	public void verifySearchButtonAppearance(String verificationText) {

		String[]varText=verificationText.split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, varText[0]);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyAllRespondent);
		WebDriverUtility.wait(5);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		WebElementUtility.sendText(driver, tab_ReapondentName, varText[1]);
		WebDriverUtility.wait(1);
		WebElementUtility.sendText(driver, tab_Email, varText[2]);
		WebDriverUtility.wait(1);
		//		WebElementUtility.sendText(driver, tab_Phone, varText[3]);
		//		WebDriverUtility.wait(1);
		WebElementUtility.click(btn_Search, driver);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyOfNameEmailPhone);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		btn_Clear.click();
		WebDriverUtility.wait(5);
		WebElementUtility.sendText(driver, tab_ReapondentName, varText[1]);
		WebElementUtility.click(btn_Search, driver);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyOfNameEmailPhone);
		btn_Clear.click();
		WebDriverUtility.wait(5);
		WebElementUtility.sendText(driver, tab_Email, varText[2]);
		WebElementUtility.click(btn_Search, driver);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyOfNameEmailPhone);
		btn_Clear.click();
		WebDriverUtility.wait(5);
		WebElementUtility.sendText(driver, tab_Phone, varText[3]);
		WebElementUtility.click(btn_Search, driver);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyOfNameEmailPhone);
		btn_Clear.click();
		WebDriverUtility.wait(5);
		WebElementUtility.click(btn_Search, driver);
		DashboardAsserts.assertElementExists(lbl_FailedNotificationSurveyResult);	


	}
	/**
	 * Function to Verify the functionality of \"Clear Search\" Button at Survey Results Screen
	 * @param verificationText
	 */
	public void verifyClearSearchButtonAppearance(String verificationText) {
		String [] varText =  verificationText.split(";");
		String[]text_1=varText[0].split(",");
		String[]text_2=varText[1].split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, text_1[0]);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyAllRespondent);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_RespondedNonResponded, list_drpRespondedNonResponded, text_1[1]);
		WebElementUtility.verifySurveyCount(driver, drp_RespondedNonResponded, Count_SurveyAllRespondent);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		WebElementUtility.sendText(driver, tab_ReapondentName, text_1[2]);
		WebDriverUtility.wait(1);
		WebElementUtility.sendText(driver, tab_Email, text_1[3]);
		WebDriverUtility.wait(1);
		WebElementUtility.sendText(driver, tab_Phone, text_1[4]);
		WebDriverUtility.wait(1);
		btn_Clear.click();
		int j = 0; 
		for (WebElement webElement : tab_NameEmailPhone) {	
			DashboardAsserts.assertTextEquals(webElement.getAttribute("placeholder"), text_2[j]);
			j++;			
		}	
	}
	/**
	 * Function to verify the functionality of \"Change Location \"at Survey Results Screen
	 * @param verificationText
	 */
	public void verifyChangeLocationFunctionality(String verificationText) {
		String[]varText=verificationText.split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectLocations(driver, btn_Locations, list_Locations, varText[0]);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyAllRespondent);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		String text1=surveyRespondent_name.getText();
		WebDriverUtility.wait(5);
		WebElementUtility.selectLocations(driver, btn_Locations, list_Locations, varText[1]);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyAllRespondent);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		String text2=surveyRespondent_name.getText();
		WebDriverUtility.wait(5);
		WebElementUtility.resetLocation(driver, btn_Locations, list_Locations, def_Location);
		Assert.assertNotSame(text1, text2);
	}


	public void verifySurveyresultCompanySelected(String verificationText,String verificationText1,String verificationText2) {
		String varText[]=verificationText1.split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.isDisplayed(driver, txt_CurrantCompany); 
		WebDriverUtility.wait(2);
		DashboardAsserts.assertTextEquals(btn_locUser.getText(), varText[0]);
		DashboardAsserts.assertTextEquals(txt_CurrantCompany.getText(), varText[1]);
		WebElementUtility.click(btn_CompanyChange, driver);
		WebDriverUtility.wait(2);
		WebElementUtility.click(txt_NewCompany2, driver);
		WebDriverUtility.wait(5);
		String varText_1[]= verificationText.split(",");
		DashboardAsserts.assertTextEquals(btn_Locations.getText(), varText_1[0]);

		WebDriverUtility.wait(3);
		WebElementUtility.resetLocationforChangedLocation(driver, btn_UserCompany, btn_CompanyChange, verificationText2, list_Company);


	}

	/**
	 * function to verify survey result appearance
	 * @param verificationText1
	 * @param dashboard
	 * @param verificationText2
	 * @param verificationText3
	 */


	public void verifySurveyresultAppearance(String verificationText1, Dashboard dashboard, String verificationText2, String verificationText3) {
		String [] varText=verificationText1.split(",");
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);	          
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, varText[0]);
		WebElementUtility.isDisplayed(driver, txt_AllRespondentTypes);
		WebDriverUtility.wait(2);   
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_RespondedNonResponded, list_drpRespondedNonResponded, varText[1]);
		WebElementUtility.isDisplayed(driver, txt_NotResponded);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllReviewRating, list_drpAllReviewRating, varText[2]);
		WebElementUtility.isDisplayed(driver, txt_PositiveReviews);
		dashboard.verifyTimeframeFunctionalitySurveyresult(btn_Timeframe);

		String [] varText_1=verificationText2.split(",");
		WebElementUtility.sendText(driver, tab_ReapondentName, varText_1[0]);
		WebElementUtility.sendText(driver, tab_Email, varText_1[1]);
		WebElementUtility.sendText(driver, tab_Phone, varText_1[2]);
		WebElementUtility.click(btn_Search, driver);
		WebDriverUtility.wait(2);
		btn_Clear.click();
		WebDriverUtility.wait(3);
		String []text3=verificationText3.split(",");
		DashboardAsserts.assertTextEquals(tab_ReapondentName.getAttribute("placeholder"), text3[0]);
		DashboardAsserts.assertTextEquals(tab_Email.getAttribute("placeholder"), text3[1]);
		DashboardAsserts.assertTextEquals(tab_Phone.getAttribute("placeholder"), text3[2]);
		WebElementUtility.isDisplayed(driver, div_Rating);
		WebElementUtility.isDisplayed(driver, respondent_comments);
		DashboardAsserts.assertElementExists(surveyRespondent_name);
		DashboardAsserts.assertElementExists(respondent_email);
		DashboardAsserts.assertElementExists(doctor_name);
		DashboardAsserts.assertElementExists(depart_name);
		DashboardAsserts.assertElementExists(btn_showmore);
		wait.until(ExpectedConditions.elementToBeClickable(btn_showmore));
		WebElementUtility.click(btn_showmore, driver);
		WebDriverUtility.wait(2);
		WebElementUtility.isDisplayed(driver, cardblock_heading);
		for(WebElement options:cardblock_questions) {
			options.getText();
			WebElementUtility.isDisplayed(driver, options);
		}
		btn_showmoreClose.click();
		WebDriverUtility.wait(2);
		for(WebElement options:list_TextTimeline) {
			options.getText();
			WebElementUtility.isDisplayed(driver, options);
		}

	}
	/**
	 * Function to verify rating above 4
	 */
	public void verifyRatingBoxAboveFour(String name,String verificationText, String rating, String ratingColor) {     
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(txt_SearchName));
		txt_SearchName.sendKeys(name);
		btn_SearchSurvey.click();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(div_Rating));
		DashboardAsserts.assertTextEquals(div_Rating.getText(), rating);
		String color = div_Rating.getCssValue("background-color");
		String green = WebElementUtility.covertRGBtoHEX(color);
		DashboardAsserts.assertTextEquals(green, ratingColor);

	}



	/**
	 * Function to verify rating between 2.1 to 3.9
	 */
	public void verifyRatingBoxAverage(String name,String verificationText, String rating, String ratingColor) {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(txt_SearchName));
		txt_SearchName.sendKeys(name);
		btn_SearchSurvey.click();
		wait.until(ExpectedConditions.elementToBeClickable(div_Rating));
		DashboardAsserts.assertTextEquals(div_Rating.getText(), rating);
		String color = div_Rating.getCssValue("background-color");
		String orange = WebElementUtility.covertRGBtoHEX(color);
		DashboardAsserts.assertTextEquals(orange, ratingColor);

	}

	/**
	 * Function to verify rating below 2
	 */
	public void verifyRatingBoxBelow(String name,String verificationText, String rating, String ratingColor) {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(txt_SearchName));
		txt_SearchName.sendKeys(name);
		btn_SearchSurvey.click();
		wait.until(ExpectedConditions.elementToBeClickable(div_Rating));
		DashboardAsserts.assertTextEquals(div_Rating.getText(), rating);
		String color = div_Rating.getCssValue("background-color");
		String red = WebElementUtility.covertRGBtoHEX(color);
		DashboardAsserts.assertTextEquals(red, ratingColor);
	}
	/**
	 * function to verify respondent dropdown functionality
	 * @param verificationText1
	 */
	public void verifyRespondedDropdownFunctionality(String name,String verificationText,String verificationText1,String verificationText2,String verificationText3) {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(txt_SearchName));
		String varText[]= verificationText1.split(",");
		txt_SearchName.sendKeys(name);
		btn_SearchSurvey.click();
		wait.until(ExpectedConditions.visibilityOf(respondent_email));
		DashboardAsserts.assertTextEquals(respondent_email.getText(), varText[0]);
		btn_EnvolopePointer.click();
		WebElementUtility.isDisplayed(driver,btn_ThanksByEmail );
		WebDriverUtility.wait(2);
		btn_SendResponse.click();
		WebDriverUtility.wait(2);
		wait.until(ExpectedConditions.visibilityOf(txt_Success));
		DashboardAsserts.assertTextEquals(txt_Success.getText(), verificationText3);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_RespondedNonResponded, list_drpRespondedNonResponded, varText[1]);
		WebElementUtility.verifySurveyCount(driver, drp_AllRespondentType, Count_SurveyAllRespondent);

		try {
			String subject= GmailUtility.getSubjectFromGmail(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE,verificationText2);
			DashboardAsserts.assertTextEquals(subject.trim(), verificationText2);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * function to verify not respondent dropdown functionality
	 * @param verificationText1
	 */
	public void verifyNotRespondedDropdownFunctionality(String verificationText1,String verificationText2) {
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_RespondedNonResponded, list_drpRespondedNonResponded, verificationText1);
		WebElementUtility.isDisplayed(driver, txt_NotResponded);
		WebElementUtility.verifySurveyCount(driver, drp_RespondedNonResponded, Count_SurveyAllRespondent);




	}
	public void verifyPointerEnvolopFunctionality(String name,String rating,String email) {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(txt_SearchName));
		txt_SearchName.sendKeys(name);
		btn_SearchSurvey.click();
		wait.until(ExpectedConditions.elementToBeClickable(div_Rating));
		DashboardAsserts.assertTextEquals(div_Rating.getText(), rating);
		DashboardAsserts.assertTextEquals(respondent_email.getText(), email);
		btn_EnvolopePointer.click();
		WebElementUtility.isDisplayed(driver,btn_ThanksByEmail );
		WebElementUtility.isDisplayed(driver,div_Rating );
		WebElementUtility.isDisplayed(driver,surveyRespondent_name );
		WebElementUtility.isDisplayed(driver,depart_name );
		WebElementUtility.isDisplayed(driver,doctor_name );
		WebElementUtility.isDisplayed(driver,respondent_comments );
		WebElementUtility.isDisplayed(driver,btn_showmore );
		btn_showmore.click();
		WebDriverUtility.wait(2);
		WebElementUtility.isDisplayed(driver,survey_questions );
		btn_showmoreClose.click();
		WebElementUtility.isDisplayed(driver,date_reviewtimelineDate );
		WebElementUtility.isDisplayed(driver,text_reviewtimelineText );
		WebElementUtility.isDisplayed(driver,btn_SendResponse );
		WebElementUtility.isDisplayed(driver,btn_cancel );
		WebElementUtility.isDisplayed(driver, heading_emailbody);

	}


	/**
	 * Function to verify respond by mail functionality
	 * @param name
	 * @param verificationText
	 * @param rating
	 * @param verificationText1
	 * @param verificationText2
	 * @param verificationText3
	 */
	public void verifyResopondbymailFunctionality(String name,String email,String varificationText, String verificationText1,String varificationText2,String varificationText3) {
		String[] vartText=varificationText2.split(";");
		verifyPointerEnvolopFunctionality(name, verificationText1,email);
		wait.until(ExpectedConditions.elementToBeClickable(heading_emailsubject));
		heading_emailsubject.click();
		WebElementUtility.isDisplayed(driver, txt_EmailSubSurveyResponse);
		DashboardAsserts.assertTextEquals(txt_EmailSubSurveyResponse.getAttribute("placeholder"), vartText[0]);
		heading_emailbody.click();
		DashboardAsserts.assertTextEquals(text_emailbody.getAttribute("value"), vartText[1]);   
		WebElementUtility.click(btn_cancel, driver);
		WebElementUtility.isDisplayed(driver, surveyRespondent_name);
		WebDriverUtility.wait(5);
		wait.until(ExpectedConditions.elementToBeClickable(btn_EnvolopePointer));
		WebElementUtility.click(btn_EnvolopePointer, driver);
		WebElementUtility.click(btn_SendResponse, driver);
		WebDriverUtility.wait(2);

		DashboardAsserts.assertTextEquals(txt_Success.getText(), varificationText3);
		try {
			String subject= GmailUtility.getSubjectFromGmail(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE,vartText[0]);
			DashboardAsserts.assertTextEquals(subject.trim(), vartText[0]);
			String subject1= GmailUtility.getBodyMessageFromGmail(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE, vartText[1]);
			DashboardAsserts.assertTextEquals(subject, vartText[0]);
			DashboardAsserts.assertTextEquals(subject1, vartText[1]);

		}catch (Exception e) {
			e.printStackTrace();
		}


	}

	/**
	 * Function to verify thank by mail functionality
	 * @param name
	 * @param verificationText
	 * @param rating
	 * @param verificationText1
	 * @param verificationText2
	 * @param verificationText3
	 */

	public void verifyThankMailFunctionality(String name,String email,String varificationText, String rating,String varificationText2,String varificationText3) {
		String[] vartText=varificationText2.split(";");
		verifyPointerEnvolopFunctionality(name, rating,email);
		wait.until(ExpectedConditions.elementToBeClickable(heading_emailsubject));
		heading_emailsubject.click();
		WebElementUtility.isDisplayed(driver, txt_EmailSubSurveyResponse);
		DashboardAsserts.assertTextEquals(txt_EmailSubSurveyResponse.getAttribute("placeholder"), vartText[0]);
		heading_emailbody.click();
		DashboardAsserts.assertTextEquals(text_emailbody.getAttribute("value"), vartText[1]);     
		WebElementUtility.click(btn_SendResponse, driver);
		DashboardAsserts.assertElementExists(txt_Success);
		DashboardAsserts.assertTextEquals(txt_Success.getText(), varificationText3) ; 
		WebDriverUtility.wait(2);

		try {
			String subject= GmailUtility.getSubjectFromGmail(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE,vartText[0]);
			DashboardAsserts.assertTextEquals(subject.trim(), vartText[0]);
			String message = GmailUtility.getBodyMessageFromGmail(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE, vartText[1]);
			DashboardAsserts.assertTextEquals(subject, vartText[0]);
			DashboardAsserts.assertTextEquals(message, vartText[1]);

		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Function to verify clear search button functionality
	 * @param verificationText1
	 * @param verificationText2
	 * @param verificationText3
	 */
	public void verifyClearSearchButtonFunctionality(String verificationText1, String verificationText2, String verificationText3) {
		WebElementUtility.isDisplayed(driver, tabH_SurveyResults);
		String [] varText_1=verificationText1.split(",");
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllRespondentType, list_drpAllRespondentType, varText_1[0]);
		WebElementUtility.isDisplayed(driver, txt_AllRespondentTypes);
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_RespondedNonResponded,list_drpRespondedNonResponded, varText_1[1]);
		WebElementUtility.isDisplayed(driver, txt_NotResponded);
		WebDriverUtility.wait(5);
		WebElementUtility.selectRespondentTypeFunctionalitySurveyResult(driver, drp_AllReviewRating, list_drpAllReviewRating, varText_1[2]);
		WebElementUtility.isDisplayed(driver, txt_PositiveReviews);

		String [] varText_2=verificationText2.split(",");
		WebElementUtility.sendText(driver, tab_ReapondentName, varText_2[0]);
		WebElementUtility.sendText(driver, tab_Email, varText_2[1]);
		WebElementUtility.sendText(driver, tab_Phone, varText_2[2]);
		WebElementUtility.click(btn_Clear, driver);
		WebDriverUtility.wait(3);
		String []text3=verificationText3.split(",");
		DashboardAsserts.assertTextEquals(tab_ReapondentName.getAttribute("placeholder"), text3[0]);
		DashboardAsserts.assertTextEquals(tab_Email.getAttribute("placeholder"), text3[1]);
		DashboardAsserts.assertTextEquals(tab_Phone.getAttribute("placeholder"), text3[2]);


	}


}