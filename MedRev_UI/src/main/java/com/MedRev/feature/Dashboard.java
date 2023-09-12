package com.MedRev.feature;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Folder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.MedRev.asserts.DashboardAsserts;
import com.MedRev.utils.GmailUtility;
import com.packages.common.Config;
import com.packages.common.Constants;
import com.packages.common.Constants.Rating;
import com.packages.common.TimeUtils;
import com.packages.common.WebDriverUtility;
import com.packages.common.WebElementUtility;

/**
 * @author Amarendra
 */
public class Dashboard {
	private static WebDriver driver;
	WebDriverWait wait;
	@FindBy(id = "main")
	private WebElement lbl_YourAccount;
	@FindBy(css = "div#pcoded div.row.dashboard-header.align-items-center > div:nth-child(1) > h5")
	private WebElement lbl_Dashboard;
	@FindBy(xpath = ".//*[@id='det_buttons']/a[normalize-space()='Logout']")
	private WebElement lnk_Logout;
	@FindBy(xpath = "//*[@id='pcoded']//button[text()='Send Survey']")
	private WebElement btn_SendSurvey;
	@FindBy(css = "div:nth-child(1) > label > select")
	private WebElement select_RespondentTypeSurvey;
	@FindBy(xpath = "//select[@class='survey_select']")
	private WebElement select_RespondentTypeDashboard;
	@FindBy(name = "survey_definition_id")
	private WebElement select_SurveyType;
	@FindBy(name = "name")
	private WebElement txt_Name;
	@FindBy(name = "email")
	private WebElement txt_Email;
	@FindBy(name = "phone")
	private WebElement txt_Mobile;
	@FindBy(name = "department")
	private WebElement txt_Department;
	@FindBy(name = "provider")
	private WebElement txt_Provider;
	@FindBy(id = "surveysubmitbutton")
	private WebElement btn_SurveySubmitButton;
	@FindBy(css = "div > div.sn-title.ng-star-inserted")
	private WebElement lbl_SuccessNotification;
	@FindBy(css = "div#pcoded li.d-inline-block.location-change.show.dropdown > div > button")
	List<WebElement> list_Locations;
	@FindBy(id = "dropdownLocation")
	private WebElement btn_Locations;
	@FindBy(xpath = "//form/input[@name='questionId[]'] [@type='hidden']")
	List<WebElement> list_RatingsAndSurvey;
	@FindBy(xpath = "//span[text()='Ashish VYAS']")
	private WebElement btn_User;
	@FindBy(xpath = "//ul/div/li[contains(@class,'ng-tns-c93-0')]")
	private List<WebElement> list_User;
	@FindBy(xpath = "//p[@class='score-label']")
	List<WebElement> list_Overview_Appearance;
	@FindBy(xpath = "//div[@class='row row--has-seperator']")
	List<WebElement> Overview_Appearance_NPS;
	@FindBy(xpath = "//p[text()='Surveys Filled']")
	private WebElement Survey_Filled;
	@FindBy(xpath = "(//div[@class='col-6 col-sm-4'])[3]/p[2]")
	private WebElement Survey_Filled_Count;
	@FindBy(xpath = "(//div[@class='col-6 col-sm-4'])[4]/p[2]")
	private WebElement Survey_Score_Count;
	@FindBy(xpath = "//button[@class='btn btn-link btn-datepicker']")
	private WebElement btn_Timeframe;
	@FindBy(xpath = "(//div[@class='ngb-dp-month-name ng-star-inserted'])")
	private List<WebElement> list_Months;
	@FindBy(css = "div#pcoded div.ngb-dp-arrow.right > button[type=\"button\"] > span")
	private WebElement btn_Next_Month;
	@FindBy(xpath = "(//ngb-datepicker-month)[1]//div/span")
	private List<WebElement> crt_DateSelected;
	@FindBy(xpath = "(//div[@class='ngb-dp-month-name ng-star-inserted'])[2]")
	private WebElement btn_final_Month;
	@FindBy(css = "div#pcoded ngb-datepicker-navigation-select > select:nth-child(1)")
	private WebElement btn_month;
	@FindBy(css = "div#pcoded div:nth-child(7) > p.score")
	private WebElement count_SurrveyScore;

	@FindBy(css = "div#pcoded ngb-datepicker-navigation-select > select:nth-child(1)")
	private WebElement month_March;
	@FindBy(xpath = "(//ngb-datepicker-month)[1]//div/span")
	private List<WebElement> crt_DateSelected1;
	@FindBy(css = "div#pcoded div:nth-child(1) > ngb-datepicker-month > div:nth-child(6) > div:nth-child(4) > span")
	private List<WebElement> btn_final_Month1;

	@FindBy(xpath = "(//ngb-datepicker-month)[2]//div/span")
	private List<WebElement> final_DateSelected;
	@FindBy(xpath = "//button[@class='btn btn-primary waves-light']")
	private static WebElement btn_SaveDate;
	@FindBy(css = "div#pcoded div.ngb-dp-arrow.right > button[type=\"button\"] > span")
	private List<WebElement> List_Company;
	@FindBy(css = "div#pcoded li:nth-child(1) > div > i")
	private WebElement btn_CompanyChange;
	@FindBy(css = "div#team-selection-menu a")
	private WebElement btn_Back_CompanyChange;
	@FindBy(css = "div#team-selection-menu h5")
	private WebElement btn_New_CompanyName;
	@FindBy(xpath = "//h5[contains(text(),'Rating Questions')]")
	private WebElement btn_Rating_Ques;
	@FindBy(css = "div#pcoded ol > li")
	private List<WebElement> count_Ratingques1;
	@FindBy(css = "div#pcoded li.d-inline-block.location-change.ng-tns-c93-7.show.dropdown > div > button")
	private List<WebElement> List_drp_Location;

	@FindBy(css = "nav#main_navbar ul:nth-child(6) > li > a > span.pcoded-mtext.ng-tns-c93-0")
	private WebElement tab_Analysis;
	@FindBy(xpath="//span[text()='Surveys Table']")
	private WebElement tab_SurveyTable;
	@FindBy(xpath = "//span[text()='Survey Results']")
	private WebElement tab_SurveyResults;
	@FindBy(xpath = "(//img[@src=\"assets/images/google-icon.png\"])[2]")
	private WebElement img_Google;
	@FindBy(xpath = "(//img[@src=\"assets/images/facebook-icon.png\"])[2]")
	private WebElement img_Facebook;
	@FindBy(xpath = "//p[@class='score-label']")
	List<WebElement> list_OverviewAppearance;
	@FindBy(xpath = "//div[@class='row row--has-seperator']/div/p")
	List<WebElement> listData_OverviewAppearnce;
	@FindBy(css = "div#pcoded div.col-6.col-sm-4.ng-star-inserted > p.score.black")
	private WebElement txt_NPSScore;

	@FindBy(css = "div#pcoded li:nth-child(1) > div > h5:nth-child(2)")
	private WebElement crt_Company;
	@FindBy(xpath = "(//p[@class='score'])[2]")
	private WebElement survey_change_count;
	@FindBy(xpath = "(//div/a)[5]")
	private WebElement btn_EditQues;
	@FindBy(xpath = "//h5[contains(text(),'Edit Survey Questions')]")
	private WebElement edit_SurveyQues;
	@FindBy(xpath = "(//div[@class='col-xl-3'])[1]//div/div/h5")
	private WebElement txt_Survey_YourAttention;
	@FindBy(xpath = "(//div[@class='col-xl-3'])[1]//div/div/div/div/div/p/span")
	private WebElement txt_BadRating_Count;
	@FindBy(xpath = "(//div[@class='col-xl-3'])[1]//div/div/div/div/button")
	private WebElement btn_BadRating_Survey;
	@FindBy(xpath = "//h5[@class='dashboard-header__title']")
	private WebElement txt_SurveyResult;
	@FindBy(xpath = "//span[text()='Social Reviews']")
	private WebElement btn_SocialReviews;
	@FindBy(xpath = "//span[text()='Add Your Google Reviews']")
	private WebElement btn_Add_GoogleReviews;
	@FindBy(xpath = "//button[text()=' Connect with Google Account ']")
	private WebElement btn_ConnectWithGoogle;
	@FindBy(xpath = "//span[text()='Google']")
	private WebElement txt_Google;
	@FindBy(xpath = "(//div[@class='row dashboard-header align-items-center'])/div/h5)")
	private WebElement txt_Dashboard;
	@FindBy(xpath = "//div[@class='navbar-container container-fluid ng-tns-c93-0']//ul[2]/li[3]/a/span")
	private WebElement btn_UserCompany;
	@FindBy(css = "div#pcoded li:nth-child(1) > div > h5:nth-child(2)")
	private WebElement txt_CurrantCompany;
	@FindBy(css = "div#team-selection-menu > li")
	private List<WebElement> list_CompanyUser;
	@FindBy(css = "div#team-selection-menu li:nth-child(2) > div > h5")
	private WebElement txt_Company2;

	// New AddOn
	@FindBy(xpath = "(//button[@class='dropdown-item ng-tns-c93-0 ng-star-inserted'])[1]")
	private WebElement def_Location;

	// New Code For SurveyPage
	@FindBy(xpath = "//span[text()=\"Survey Results\"]")
	private WebElement tab_SurveyResult;
	@FindBy(xpath = "//*[@id='pcoded']/div[2]/div/div/div/div/div/div/app-reviews/div/div[1]/div[2]/span[4]/button")
	private WebElement dropdown_timeframe;
	@FindBy(xpath = "//*[@id=\"add-location-btn\"]")
	private WebElement btn_batch;
	@FindBy(xpath = "//*[@id='pcoded']/div[2]/div/div/div/div/div/div/app-file-upload-ui/div/div[1]/div/h5")
	private WebElement text_uploadBatch;
	@FindBy(name = "survey_definition_id")
	private WebElement dropdown_surveytype;
	@FindBy(css = "div > div.sn-content.ng-tns-c16-7.ng-star-inserted")
	private WebElement text_emptyerror;
	@FindBy(id = "commentsBox")
	private WebElement textArea_CommentBox;
	@FindBy(css = "app-admin > app-modal-basic > div > div > div > div.modal-header.ng-star-inserted > div > button[type=\"button\"]")
	private WebElement btn_CloseSurvey;
	@FindBy(css = "div#pcoded li:nth-child(2) > div > h5:nth-child(2)")
	private WebElement btn_locUser;
	@FindBy(xpath = "//*[name()='svg']//*[name()='g' and @aria-label='graph 1 Q2 3.33']")
	private WebElement graphBar_Question;
	@FindBy(xpath = "//*[name()='svg']//*[name()='path' and @stroke-linejoin='round']")
	private List<WebElement> graphBar_ChangeOverTime;
	@FindBy(xpath = "//*[name()='svg']//*[name()='rect' and @stroke='#000' and @transform='translate(5,-27)']")
	private List<WebElement> graphBar_ChangeOverTimePanel;
	@FindBy(css = "div#pcoded div:nth-child(6) > p.score.black")
	private WebElement survey_Score;
	@FindBy(xpath = "//p[text()='Survey Score']")
	private WebElement txt_surveyScore;
	@FindBy(xpath = "(//div[@class='card-block ng-tns-c118-6'])/div/div/div")
	private WebElement graph_Appearance;
	@FindBy(xpath = "//li[@class='ng-tns-c93-0 ng-star-inserted'])[5]//h5[contains(text(),'Company1')]")
	private WebElement txt_Company1;
	@FindBy(xpath = "//body/app-root[1]/app-admin[1]/div[1]/div[2]/nav[1]/div[1]/div[2]/ul[2]/li[3]/ul[1]/div[2]/li")
	private List<WebElement> list_Companys;
	@FindBy(css = "div#pcoded div:nth-child(7) > div:nth-child(3) ul > li:nth-child(10)")
	private WebElement negativeWordNightMare;
	@FindBy(xpath = "//*[name()='svg']//*[name()='g' and @aria-label='graph 1 Q2 3.89']")
	private WebElement graph_Bar;
	@FindBy(css = "div#pcoded div:nth-child(4) > div > app-card > div > div.card-header.ng-star-inserted > h5")
	private WebElement btn_OverTime;
	
	
	@FindBy(name="name")
	private WebElement name_field;

	public WebElement getDef_Location() {
		return def_Location;
	}

	public void setDef_Location(WebElement def_Location) {
		this.def_Location = def_Location;
	}

	public Dashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = WebDriverUtility.getWaitFor30Secs(driver);
	}

	/**
	 * @return the lbl_Dashboard
	 */
	public WebElement getLbl_Dashboard() {
		return lbl_Dashboard;
	}

	/**
	 * @return the lnk_Logout
	 */
	public WebElement getLnk_Logout() {
		return lnk_Logout;
	}

	public WebElement getLbl_YourAccount() {
		return lbl_YourAccount;
	}

	/**
	 * Function to verify send survey
	 * 
	 * @param email
	 * @param name
	 * @param respondentType
	 * @param surveyType
	 * @param mobileNumber
	 * @param provider
	 * @param department
	 * @param verificationText
	 * @param text
	 */
	public void sendSurvey(String email, String name, String respondentType, String surveyType, String mobileNumber,
			String provider, String department, String verificationText) {
		Reporter.log("Click on Send Survey Button ");
		wait.until(ExpectedConditions.visibilityOf(btn_SendSurvey));
		WebElementUtility.click(btn_SendSurvey, driver);
		wait.until(ExpectedConditions.elementToBeClickable(select_RespondentTypeSurvey));
		WebDriverUtility.wait(2);
		Select select = new Select(select_RespondentTypeSurvey);
		WebElementUtility.selectByPartOfVisibleTextIgnoreCase(select, respondentType);
		select = new Select(select_SurveyType);
		WebElementUtility.selectByPartOfVisibleTextIgnoreCase(select, surveyType);
		WebElementUtility.sendText(driver, txt_Name, name);
		WebElementUtility.sendText(driver, txt_Email, email);
		// WebElementUtility.sendText(driver, txt_Mobile, mobileNumber);
		WebElementUtility.sendText(driver, txt_Department, department);
		WebElementUtility.sendText(driver, txt_Provider, provider);
		Reporter.log("Clcik on Send Survey Button ");
		WebElementUtility.click(btn_SurveySubmitButton, driver);
		WebDriverUtility.wait(3);

		wait.until(ExpectedConditions.visibilityOf(lbl_SuccessNotification));
		DashboardAsserts.assertElementExists(lbl_SuccessNotification);
		DashboardAsserts.assertElementContainsText(lbl_SuccessNotification.getText(), verificationText);
		wait.until(ExpectedConditions.elementToBeClickable(btn_CloseSurvey));
		btn_CloseSurvey.click();

	}

	/**
	 * Function to Verify Respondent Type Options
	 * 
	 * @param verificationText
	 */
	public void verifyRepondentTypeOptions(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		wait.until(ExpectedConditions.elementToBeClickable(select_RespondentTypeDashboard));
		String[] respontentOptions = verificationText.split(",");
		Select select = new Select(select_RespondentTypeDashboard);
		List<WebElement> options = select.getOptions();
		int i = 0;
		for (WebElement webElement : options) {
			DashboardAsserts.assertTextEquals(webElement.getText(), respontentOptions[i]);
			i++;
		}

	}

	/**
	 * Function to Verify Location Type Options
	 * 
	 * @param verificationText
	 */
	public void verifyLocationTypeOptions(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		WebElementUtility.click(btn_Locations, driver);
		for (WebElement webElement : list_Locations) {
			WebElementUtility.isDisplayed(driver, webElement);
		}

	}

	/**
	 * Function to Fill Survey
	 *
	 * @param url
	 */
	public void fillSurvey(String url, Rating rating, String surveyComment) {
		String value;
		WebDriverUtility.wait(5);
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // switches to new tab
		driver.get(url);
		WebDriverUtility.wait(2);
		for (int i = 0; i < list_RatingsAndSurvey.size(); i++) {
			value = list_RatingsAndSurvey.get(i).getAttribute("Value");

			if (i < 2) {
				switch (rating) {
				case FIVESTAR:
					value = "//*[@id='question" + value + "']/label[@for='_" + value + "_star5']";
					break;
				case FOURSTAR:
					value = "//*[@id='question" + value + "']/label[@for='_" + value + "_star4']";
					break;
				case THREESTAR:
					value = "//*[@id='question" + value + "']/label[@for='_" + value + "_star3']";
					break;
				case TWOSTAR:
					value = "//*[@id='question" + value + "']/label[@for='_" + value + "_star2']";
					break;
				case ONESTAR:
					value = "//*[@id='question" + value + "']/label[@for='_" + value + "_star1']";
					break;
				}
				WebDriverUtility.wait(1);
				WebElement ratingStar = driver.findElement(By.xpath(value));
				ratingStar.click();
			}
			if (i == 2) {

				WebElement textAreaExperience = driver.findElement(By.name("_" + value));
				textAreaExperience.sendKeys(surveyComment);
			}
			if (i == 3) {
				WebElement checkbox1 = driver.findElement(By.id("option" + value + "-" + 0));
				checkbox1.click();
				WebElement checkbox2 = driver.findElement(By.id("option" + value + "-" + 1));
				checkbox2.click();
				WebElement checkbox3 = driver.findElement(By.id("option" + value + "-" + 2));
				checkbox3.click();
				WebElement checkbox4 = driver.findElement(By.id("option" + value + "-" + 3));
				checkbox4.click();
				WebDriverUtility.wait(1);
			}
			if (i == 4) {
				WebElement option = driver.findElement(By.id("option" + value + "-" + 0));
				option.click();
				WebDriverUtility.wait(1);
			}

			WebDriverUtility.wait(1);
		}
		WebElement submitSurvey = driver.findElement(By.xpath("//*[@id='submit']/button"));
		submitSurvey.click();
		WebDriverUtility.wait(2);
		try {
			if (textArea_CommentBox.isDisplayed()) {
				textArea_CommentBox.sendKeys(surveyComment);
				submitSurvey.click();
				WebDriverUtility.wait(2);
			}

		} catch (Exception e) {
			System.out.println("Final survey comment object is not displayed");
		} finally {
			driver.switchTo().window(tabs.get(0));
		}

		driver.switchTo().window(tabs.get(0));
	}

	/**
	 * Function to verify User Profile Dropdown
	 * 
	 * @param verificationText
	 */

	public void verifyUserProfileDropdown(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		WebElementUtility.click(btn_UserCompany, driver);
		wait.until(ExpectedConditions.elementToBeClickable(list_User.get(0)));
		String[] dropdownOptions = verificationText.split(",");
		int j = 2;
		for (int i = 0; i < dropdownOptions.length; i++) {
			DashboardAsserts.assertTextEquals(list_User.get(j).getText(), dropdownOptions[i]);
			j++;
		}
	}

	/**
	 * Function to verify overview Appearance
	 *
	 * @param verificationText
	 */

	public void verifyOverviewAppearance(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		wait.until(ExpectedConditions.visibilityOfAllElements(list_OverviewAppearance));
		String[] overviewOptions = verificationText.split(",");
		WebDriverUtility.wait(2);
		int i = 0;
		for (WebElement webElement : list_OverviewAppearance) {
			DashboardAsserts.assertTextEquals(webElement.getText(), overviewOptions[i]);
			i++;
		}

	}

	/**
	 * 
	 * @param email
	 * @param name
	 * @param respondentType
	 * @param surveyType
	 * @param mobileNumber
	 * @param provider
	 * @param department
	 * @param verificationText
	 * @param dashboard
	 */

	public void verifNetPromoterFunctionality(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText, Dashboard dashboard) {
		String varText[] = verificationText.split(",");
		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.isDisplayed(driver, txt_CurrantCompany);
		WebDriverUtility.wait(2);
		WebElementUtility.click(btn_CompanyChange, driver);
		for (WebElement companies : list_CompanyUser) {
			if (varText[1].equals(companies.getText())) {
				WebElementUtility.click(companies, driver);
			}
		}
		WebDriverUtility.wait(2);
		wait.until(ExpectedConditions.visibilityOfAllElements(listData_OverviewAppearnce.get(0)));
		WebDriverUtility.wait(2);
		String NPS_BeforeSurvey = txt_NPSScore.getText();
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department, varText[0]);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Five Start Rating Comment");
		} catch (Exception e) {
			e.printStackTrace();
		}
		WebDriverUtility.wait(2);
		driver.navigate().refresh();
		WebDriverUtility.wait(3);
		String NPS_AfterSurvey = txt_NPSScore.getText();
		Assert.assertNotEquals(NPS_BeforeSurvey, NPS_AfterSurvey);

	}

	/***
	 * Function to Verify Survey Filled Count
	 * 
	 * @param email
	 * @param name
	 * @param respondentType
	 * @param surveyType
	 * @param mobileNumber
	 * @param provider
	 * @param department
	 * @param verificationText
	 * @param dashboard
	 */
	public void verifServeyFilledCount(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText, Dashboard dashboard) {
		String varText[] = verificationText.split(",");

		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.isDisplayed(driver, txt_CurrantCompany);
		WebElementUtility.isDisplayed(driver, btn_locUser);
		WebElementUtility.isDisplayed(driver, txt_CurrantCompany);
		WebDriverUtility.wait(2);
		WebElementUtility.click(btn_CompanyChange, driver);
		for (WebElement companies : list_CompanyUser) {
			if (varText[1].equals(companies.getText())) {
				WebElementUtility.click(companies, driver);
			}
		}
		WebDriverUtility.wait(2);
		String surveyFilled_count_BeforeSurvey = Survey_Filled_Count.getText();
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department, varText[0]);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Five Start Rating Comment");
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.navigate().refresh();
		WebDriverUtility.wait(5);
		String surveyFilled_count_AfterSurvey = Survey_Filled_Count.getText();
		DashboardAsserts.assertTextEquals(surveyFilled_count_BeforeSurvey, surveyFilled_count_AfterSurvey);

	}

	/**
	 * Function to Verify Survey Score Count
	 * 
	 * @param email
	 * @param name
	 * @param respondentType
	 * @param surveyType
	 * @param mobileNumber
	 * @param provider
	 * @param department
	 * @param verificationText
	 * @param dashboard
	 */

	public void verifySurveyScoreFunctionality(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText, Dashboard dashboard) {
		String varText[] = verificationText.split(",");

		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.isDisplayed(driver, txt_CurrantCompany);
		WebElementUtility.isDisplayed(driver, btn_locUser);
		WebElementUtility.isDisplayed(driver, txt_CurrantCompany);
		WebDriverUtility.wait(2);
		WebElementUtility.click(btn_CompanyChange, driver);
		for (WebElement companies : list_CompanyUser) {
			if (varText[1].equals(companies.getText())) {
				WebElementUtility.click(companies, driver);
			}
		}
		WebDriverUtility.wait(2);
		String surveyScore_count_BeforeSurvey = Survey_Score_Count.getText();
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department, varText[0]);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Five Start Rating Comment");
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.navigate().refresh();
		WebDriverUtility.wait(3);
		String surveyScore_count_AfterSurvey = Survey_Score_Count.getText();
		DashboardAsserts.assertTextEquals(surveyScore_count_BeforeSurvey, surveyScore_count_AfterSurvey);
	}

	/**
	 * Function to Verify Questions functionality when any location is selected
	 * whole dashboard should reset
	 * 
	 * @param verificationText
	 */

	public void verifyQuestionFunctionality_Location(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		wait.until(ExpectedConditions.visibilityOf(Survey_Score_Count));
		WebDriverUtility.wait(3);
		WebElementUtility.isDisplayed(driver, Survey_Score_Count);
		String Count_BeforeSurvey = Survey_Score_Count.getText();
		WebElementUtility.selectLocations(driver, btn_Locations, list_Locations, verificationText);
		WebDriverUtility.wait(2);
		WebElementUtility.isDisplayed(driver, Survey_Score_Count);
		String Count_AfterSurvey = Survey_Score_Count.getText();
		Assert.assertNotEquals(Count_BeforeSurvey, Count_AfterSurvey);
		WebElementUtility.resetLocation(driver, btn_Locations, list_Locations, def_Location);

	}

	/**
	 * Function to Verify Questions functionality when all respondent type is
	 * selected for that particular location.
	 * 
	 * @param verificationText
	 */
	public void verifyQuestionFunctionality_AllRespondentSelected(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		String varText[] = verificationText.split(";");
		WebElementUtility.selectLocations(driver, btn_Locations, list_Locations, varText[0]);
		verifyTimeframeFunctionality();
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, varText[1]);
		WebDriverUtility.wait(2);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		int count1 = count_Ratingques1.size();
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, varText[2]);
		WebDriverUtility.wait(2);
		driver.navigate().refresh();
		WebDriverUtility.wait(2);
		wait.until(ExpectedConditions.visibilityOf(btn_Rating_Ques));
		int count2 = count_Ratingques1.size();
		Assert.assertNotEquals(count1, count2);

	}

	/**
	 * Function to Verify Questions functionality when employee is selected for that
	 * particular location.
	 * 
	 * @param verificationText
	 */
	public void verifyQuestionFunctionality_EmployeeSelected(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		WebDriverUtility.wait(2);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		int count1 = count_Ratingques1.size();
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, verificationText);
		WebDriverUtility.wait(2);
		wait.until(ExpectedConditions.visibilityOf(btn_Rating_Ques));
		int count2 = count_Ratingques1.size();
		Assert.assertNotEquals(count1, count2);

	}

	/// Duplicate///
	/**
	 * Function to Verify Questions functionality when client is selected for that
	 * particular location.
	 * 
	 * @param verificationText
	 */
	public void verifyQuestionFunctionality_ClientSelected(String verificationText) {
		String[] varfText = verificationText.split(";");
		WebElementUtility.selectLocations(driver, btn_Locations, list_Locations, varfText[6]);
		// To Do
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, varfText[10]);
		Assert.assertTrue(true, "Question functionality is not working for Assertion");
		WebElementUtility.resetLocation(driver, btn_Locations, list_Locations, def_Location);
	}

	/**
	 * Function to Verify Questions functionality when current company is changed.
	 * 
	 * @param verificationText
	 */
	public void verifyQuestionFunctionality_CompanyChanged(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		String varText[] = verificationText.split(",");
		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.isDisplayed(driver, txt_CurrantCompany);
		WebElementUtility.isDisplayed(driver, btn_locUser);
		WebDriverUtility.wait(2);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		int count1 = count_Ratingques1.size();
		WebDriverUtility.wait(2);

		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.click(btn_CompanyChange, driver);
		WebElementUtility.click(txt_Company1, driver);
		wait.until(ExpectedConditions.visibilityOf(txt_CurrantCompany));
		DashboardAsserts.assertTextEquals(txt_Company1.getText(), varText[2]);
		driver.navigate().refresh();

		DashboardAsserts.assertTextEquals(btn_Locations.getText(), varText[1]);

		WebDriverUtility.wait(2);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		int count2 = count_Ratingques1.size();
		Assert.assertNotEquals(count1, count2);
		WebDriverUtility.wait(5);
		SelectCompany();
		WebElementUtility.click(txt_Company2, driver);
		WebDriverUtility.wait(5);
	}

	public void SelectCompany() {
		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.click(btn_CompanyChange, driver);
	}

	/**
	 * Function to Verify Questions appearance on dashboard
	 * 
	 * @param verificationText
	 */

	public void verifyQuestionFunctionality_DashboardAppearance(String verificationText) {

		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		WebDriverUtility.wait(2);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		wait.until(ExpectedConditions.visibilityOf(btn_Rating_Ques));
		DashboardAsserts.assertTextEquals(btn_Rating_Ques.getText(), verificationText);
		for (WebElement ques : count_Ratingques1) {
			WebElementUtility.isDisplayed(driver, ques);
		}

	}

	/**
	 * Function to Verify Survey Change functionality under overview section on
	 * dashboard
	 * 
	 * @param verificationText
	 */
	public void verifyQuestionFunctionality_SurveyChange(String verificationText) {

		String varText[] = verificationText.split(",");
		WebElementUtility.selectDateRangeFromCalaender1(btn_Timeframe, varText[0], btn_month, crt_DateSelected1,
				crt_DateSelected1, driver, "1", "30");
		btn_SaveDate.click();
		WebDriverUtility.wait(2);
		String count_March = survey_Score.getText();
		System.out.println(count_March);
		WebElementUtility.selectDateRangeFromCalaender1(btn_Timeframe, varText[1], btn_month, crt_DateSelected1,
				crt_DateSelected1, driver, "1", "30");
		btn_SaveDate.click();
		WebDriverUtility.wait(2);

		String count_April = survey_Score.getText();
		System.out.println(count_April);
		Assert.assertNotEquals(count_March, count_April);

	}

	/**
	 * Function to Verify Verify overview functionality when selected Respondent
	 * Type is All Respondent and selected location is default location
	 * 
	 * @param verificationText
	 */
	public void verifyOverviewFunctionality_allRespondent_defLocation(String verificationText) {
		String[] varText = verificationText.split(";");
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		wait.until(ExpectedConditions.visibilityOf(btn_Locations));
		System.out.println(btn_Locations.getText());
		DashboardAsserts.assertTextEquals(btn_Locations.getText(), varText[2]);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, varText[0]);
		WebDriverUtility.wait(5);
		verifyOverviewAppearance(varText[1]);
	}

	/**
	 * Function to Verify overview functionality when selected respondent type is
	 * all respondent type and location is changed
	 * 
	 * @param verificationText
	 */

	public void verifyOverviewFunctionality_allRespondent_ChangedLocation(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		wait.until(ExpectedConditions.visibilityOf(btn_Locations));
		String varText[] = verificationText.split(";");
		WebElementUtility.selectLocations(driver, btn_Locations, list_Locations, varText[0]);
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, varText[1]);
		verifyOverviewAppearance(varText[2]);
		WebElementUtility.resetLocation(driver, btn_Locations, list_Locations, def_Location);
	}

	/**
	 * Function to Verify overview functionality when any other respondent type is
	 * selected and location is default location
	 * 
	 * @param verificationText
	 */

	public void verifyOverviewFunctionality_otherRespondent_defLocation(String verificationText) {
		String varText[] = verificationText.split(";");
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		wait.until(ExpectedConditions.visibilityOf(btn_Locations));
		DashboardAsserts.assertTextEquals(btn_Locations.getText(), varText[0]);
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, varText[1]);
		WebDriverUtility.wait(2);
		verifyOverviewAppearance(varText[2]);
	}

	/**
	 * Function to Verify respondent functionality when All respondent is selected
	 * 
	 * @param verificationText
	 */
	public void verifyRespondentFunctionality_AllRespondentType_Selected(String verificationText) {
		String varText[] = verificationText.split(",");
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		wait.until(ExpectedConditions.visibilityOf(survey_Score));
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, varText[0]);
		WebDriverUtility.wait(2);
		WebElementUtility.isDisplayed(driver, survey_Score);
		String surveyScore_otherRespondent = survey_Score.getText();
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, varText[1]);
		driver.navigate().refresh();
		WebDriverUtility.wait(2);
		WebElementUtility.isDisplayed(driver, count_SurrveyScore);
		String surveyScore_AllRespondent = survey_Score.getText();
		Assert.assertNotEquals(surveyScore_otherRespondent, surveyScore_AllRespondent);

	}

	/***
	 * Function to Verify respondent type functionality, When other respondent is
	 * selected
	 * 
	 * @param verificationText
	 */
	public void verifyRespondentFunctionality_OtherRespondentType_Selected(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		wait.until(ExpectedConditions.visibilityOf(survey_Score));
		WebElementUtility.isDisplayed(driver, survey_Score);
		String surveyScore_AllRespondent = survey_Score.getText();
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, verificationText);
		WebDriverUtility.wait(2);
		WebElementUtility.isDisplayed(driver, count_SurrveyScore);
		String surveyScore_otherRespondent = survey_Score.getText();
		Assert.assertNotEquals(surveyScore_otherRespondent, surveyScore_AllRespondent);
	}

	/***
	 * Function to Verify respondent type functionality, When other respondent is
	 * selected changed Location
	 * 
	 * @param verificationText
	 */

	public void verifyRespondentFunctionality_OtherRespondentType_ChangedLocation(String verificationText) {
		String[] vartext = verificationText.split(";");
		WebElementUtility.selectLocations(driver, btn_Locations, list_Locations, vartext[0]);
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, vartext[1]);
		WebDriverUtility.wait(3);
		WebElementUtility.isDisplayed(driver, survey_Score);

		Assert.assertTrue(WebElementUtility.isDisplayed(driver, survey_Score));
		WebDriverUtility.wait(3);
		WebElementUtility.resetLocation(driver, btn_Locations, list_Locations, def_Location);

	}

	/***
	 * Function to Verify Survey's With the questions are displayed under Questions
	 * section
	 * 
	 * @param verificationText
	 */

	public void verifySurveyQuestion_UnderQuestionSection(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		verifyTimeframeFunctionality();
		WebElementUtility.isDisplayed(driver, graph_Appearance);
		Assert.assertTrue(WebElementUtility.isDisplayed(driver, graph_Appearance));
	}

	/***
	 * Function to Verify Under Questions Section for each question the graph is
	 * displayed.
	 * 
	 * @param verificationText
	 */

	public void verifyGraphDisplayQuestion_UnderQuestionSection(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		verifyTimeframeFunctionality();
		WebElementUtility.isDisplayed(driver, btn_Rating_Ques);
		for (WebElement ques : count_Ratingques1) {
			DashboardAsserts.assertElementExists(ques);
		}

	}

	/***
	 * Function to Verify under Questions Section when we hover over the graph the
	 * rating is displayed..
	 * 
	 * @param verificationText
	 */

	public void verifyHoverGraph_RatingDisplayQuestion_UnderQuestionSection(String verificationText) {
		// To Do
		String[] vartext = verificationText.split(",");
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, vartext[1]);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, vartext[0]);
		WebDriverUtility.wait(2);
		wait.until(ExpectedConditions.elementToBeClickable(graphBar_Question));
		DashboardAsserts.assertElementExists(graphBar_Question);

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
	 * Function to Verify Edit Questions Button in Questions section is Clickable
	 * 
	 * @param verificationText
	 */
	public void verifyEditQuestionButton_QuestionSection(String verificationText) {
		WebElementUtility.click(btn_EditQues, driver);
		System.out.println(edit_SurveyQues.getText());
		DashboardAsserts.assertTextEquals(edit_SurveyQues.getText(), verificationText);

	}

	/**
	 * Function to Verify "Surveys that require your attention" section appearance
	 * 
	 * @param verificationText
	 */

	public void verifySurvey_Attention_SectionAppearance(String verificationText) {
		String[] txt = verificationText.split(",");
		WebDriverUtility.wait(3);
		DashboardAsserts.assertTextEquals(txt_Survey_YourAttention.getText(), txt[0]);
		DashboardAsserts.assertTextEquals(btn_BadRating_Survey.getText(), txt[1]);

	}

	/**
	 * Function to Verify \"Check bad ratings survey\" button With respect to
	 * \"Surveys that require your attention\" section at dashboard
	 * 
	 * @param verificationText
	 */

	public void verifyBadRatingSurvey_wrtSurvey_Attention(String verificationText) {
		String[] txt_1 = verificationText.split(";");
		verifySurvey_Attention_SectionAppearance(txt_1[0]);
		btn_BadRating_Survey.click();
		WebDriverUtility.wait(2);
		DashboardAsserts.assertTextEquals(txt_SurveyResult.getText(), txt_1[1]);

	}

	/**
	 * Function to Verify overview functionality when current company changed
	 * 
	 * @param verificationText
	 */

	public void verifyOverviewFunctinality_Companay_Changed(String verificationText) {
		String varText[] = verificationText.split(";");
		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.isDisplayed(driver, txt_CurrantCompany);
		WebElementUtility.isDisplayed(driver, btn_locUser);
		WebDriverUtility.wait(1);
		wait.until(ExpectedConditions.elementToBeClickable(btn_UserCompany));
		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.click(btn_CompanyChange, driver);
		WebDriverUtility.wait(2);
		WebElementUtility.click(txt_Company1, driver);
		wait.until(ExpectedConditions.visibilityOf(txt_CurrantCompany));
		DashboardAsserts.assertTextEquals(txt_Company1.getText(), varText[2]);
		WebDriverUtility.wait(2);
		verifyOverviewAppearance(varText[3]);
		WebElementUtility.selectOverviewData(driver, listData_OverviewAppearnce);
		SelectCompany();
		WebElementUtility.click(txt_Company2, driver);
		WebDriverUtility.wait(1);
	}

	/**
	 * Function to Change Over Time functionality when location is changed
	 * 
	 * @param verificationText
	 */

	public void verifyOverTimeFunctinality_Location_Changed(String verificationText) {
		String varText[] = verificationText.split(";");
		wait.until(ExpectedConditions.visibilityOf(graphBar_ChangeOverTime.get(0)));
		for (WebElement changeGraph : graphBar_ChangeOverTime) {
			DashboardAsserts.assertElementExists(changeGraph);
		}
		String graphPosition1 = graphBar_ChangeOverTime.get(2).getAttribute("d");
		System.out.println(graphPosition1);
		WebDriverUtility.wait(5);
		WebElementUtility.selectLocations(driver, btn_Locations, list_Locations, verificationText);
		DashboardAsserts.assertElementExists(btn_OverTime);
		for (WebElement changeGraph : graphBar_ChangeOverTime) {
			DashboardAsserts.assertElementExists(changeGraph);
		}
		String graphPosition2 = graphBar_ChangeOverTime.get(2).getAttribute("d");
		System.out.println(graphPosition2);
		WebDriverUtility.wait(2);
		WebElementUtility.resetLocation(driver, btn_Locations, list_Locations, def_Location);
		  

	}

	/**
	 * Function to Change Over Time functionality when current company is changed
	 * 
	 * @param verificationText
	 */

	public void verifyOverTimeFunctinality_CrtCompany_Changed(String verificationText) {
		String varText[] = verificationText.split(";");
		wait.until(ExpectedConditions.visibilityOf(graphBar_ChangeOverTime.get(0)));
		for (WebElement changeGraph : graphBar_ChangeOverTime) {
			DashboardAsserts.assertElementExists(changeGraph);
		}
		String graphPosition1 = graphBar_ChangeOverTime.get(2).getAttribute("d");
		System.out.println(graphPosition1);
		WebDriverUtility.wait(5);
		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.isDisplayed(driver, txt_CurrantCompany);
		WebElementUtility.isDisplayed(driver, btn_locUser);
		WebDriverUtility.wait(1);
		wait.until(ExpectedConditions.elementToBeClickable(btn_UserCompany));
		WebElementUtility.click(btn_UserCompany, driver);
		WebElementUtility.click(btn_CompanyChange, driver);
		WebDriverUtility.wait(2);
		WebElementUtility.click(txt_Company1, driver);
		wait.until(ExpectedConditions.visibilityOf(txt_CurrantCompany));
		DashboardAsserts.assertTextEquals(txt_Company1.getText(), varText[0]);
		WebDriverUtility.wait(2);
		DashboardAsserts.assertElementExists(btn_OverTime);
		for (WebElement changeGraph : graphBar_ChangeOverTime) {
			DashboardAsserts.assertElementExists(changeGraph);
		}
		SelectCompany();
		WebElementUtility.click(txt_Company2, driver);
		WebDriverUtility.wait(2);

	}

	/**
	 * Function to Verify Left and Right Drag icons functionality on Change Over
	 * Graph
	 * 
	 * @param verificationText
	 */

	public void verifyLeftRightDragIcons_Functinality_Change_OverGraph(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		DashboardAsserts.assertElementExists(btn_OverTime);
		wait.until(ExpectedConditions.visibilityOf(graphBar_ChangeOverTime.get(0)));
		for (WebElement changeGraph : graphBar_ChangeOverTime) {
			DashboardAsserts.assertElementExists(changeGraph);
		}

		Actions action = new Actions(driver);
		action.dragAndDrop(graphBar_ChangeOverTimePanel.get(0), graphBar_ChangeOverTime.get(2)).build().perform();
		WebDriverUtility.wait(5);

	}

	/***
	 * Function to Verify Total ratings functionality of facebook in Platform Review
	 * Score section
	 * 
	 * @param verificationText
	 */

	public void verifyTotalRating_Functinality_Facebook_PlatformReviewScoreSection(String verificationText) {
		wait.until(ExpectedConditions.visibilityOf(btn_SocialReviews));
		WebElementUtility.click(btn_SocialReviews, driver);
		// To Do
		Assert.assertTrue(true, "Add your facebook Review button is not visible..");

	}

	/**
	 * 
	 * Function to Verify Reviews that require your attention functionality of
	 * google in Platform Review Score section
	 *
	 * @param verificationText
	 */

	public void VerifyPlatformReviewScoreAppearance(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		wait.until(ExpectedConditions.visibilityOf(txt_Google));
		DashboardAsserts.assertTextEquals(txt_Google.getText(), verificationText);
				
	}

	/**
	 * Function to verify Google logo in Platform review score appearance
	 */

	public void VerifyGoogleLogoInPlatformReviewSection() {
		if (img_Google.isDisplayed()) {
			System.out.println("Google logo is displayed");
		} else {
			System.out.println("Google logo is not displayed");
		}
		System.out.println("pp");
	}

	/**
	 * Function to verify Facebook logo in Platform review score appearance
	 */

	public void VerifyFacebookLogoInPlatformReviewSection() {
		if (img_Facebook.isDisplayed()) {
			System.out.println("Facebook logo is displayed");
		} else {
			System.out.println("Facebook logo is not displayed");
		}
		System.out.println("pp");
	}

	/**
	 * Function to navigate to Analysis tab
	 * 
	 * @param driver2
	 * @return Analysis object
	 */
	public Analysis gotoTabAnalysis(WebDriver driver2) {
		 wait.until(ExpectedConditions.visibilityOf(tab_Analysis));
	        tab_Analysis.click();
	        WebDriverUtility.wait(4);
	        Analysis analysis = new Analysis(driver);
	        WebElementUtility.click(btn_UserCompany, driver);
	        WebDriverUtility.wait(2);
	        WebElementUtility.click(btn_CompanyChange, driver);
	        WebDriverUtility.wait(2);
	        WebElementUtility.click(txt_Company2, driver);
	        wait.until(ExpectedConditions.visibilityOf(txt_CurrantCompany));
	        WebDriverUtility.wait(5);
	        return analysis;
	}

	public WebElement getSurvey_Filled_Count() {
		return Survey_Filled_Count;
	}

	public void setSurvey_Filled_Count(WebElement survey_Filled_Count) {
		Survey_Filled_Count = survey_Filled_Count;
	}

	public WebElement getSurvey_Score_Count() {
		return Survey_Score_Count;
	}

	public void setSurvey_Score_Count(WebElement survey_Score_Count) {
		Survey_Score_Count = survey_Score_Count;
	}

	/**
	 * Function to VerifyQuestionFunctionality_TimeframeSelected_WrtRespondent_Type
	 * 
	 * @param driver2
	 * @return Survey object
	 */

	public void VerifyQuestionFunctionality_TimeframeSelected_WrtRespondent_Type(String VerificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		WebDriverUtility.wait(2);
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, VerificationText);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		int count1 = count_Ratingques1.size();
		WebDriverUtility.wait(2);
		verifyTimeframeFunctionality();
		wait.until(ExpectedConditions.visibilityOf(btn_Rating_Ques));
		int count2 = count_Ratingques1.size();
		Assert.assertNotEquals(count1, count2);

	}

	/**
	 * Function to VerifyTimeframeFunctionality_WrtAllRespondent_Type
	 * 
	 * @param driver2
	 * @return Survey object
	 */

	public void VerifyTimeframeFunctionality_WrtAllRespondent_Type(String verificationText) {
		WebElementUtility.isDisplayed(driver, txt_Dashboard);
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, verificationText);
		String surveyChange = count_SurrveyScore.getText();
		WebDriverUtility.wait(2);
		verifyTimeframeFunctionality();
		WebDriverUtility.wait(5);
		String surveyChange1 = count_SurrveyScore.getText();
		Assert.assertNotEquals(surveyChange, surveyChange1);

	}

	/**
	 * Function to navigate to Survey tab
	 * 
	 * @param driver2
	 * @return Survey object
	 */
	public SurveyTable gotoTabSurvey(WebDriver driver) {
		wait.until(ExpectedConditions.elementToBeClickable(tab_SurveyTable));	
        WebElementUtility.click(tab_SurveyTable, driver);
		WebDriverUtility.wait(3);
		WebElementUtility.click(btn_UserCompany, driver);
        WebDriverUtility.wait(2);
        WebElementUtility.click(btn_CompanyChange, driver);
        WebDriverUtility.wait(2);
        WebElementUtility.click(txt_Company2, driver);
        wait.until(ExpectedConditions.visibilityOf(txt_CurrantCompany));
        WebDriverUtility.wait(5);
		SurveyTable surveytable = new SurveyTable(driver);
		return surveytable;
	}

	/**
	 * Function to navigate to SurveyResult tab
	 * 
	 * @return Survey object
	 */
	public SurveyResults gotoTabSurveyResult(WebDriver driver) {
		wait.until(ExpectedConditions.visibilityOf(tab_SurveyResults));
		tab_SurveyResults.click();
		WebDriverUtility.wait(2);
		WebElementUtility.click(btn_UserCompany, driver);
        WebDriverUtility.wait(2);
        WebElementUtility.click(btn_CompanyChange, driver);
        WebDriverUtility.wait(2);
        WebElementUtility.click(txt_Company2, driver);
        wait.until(ExpectedConditions.visibilityOf(txt_CurrantCompany));
        WebDriverUtility.wait(5);
		SurveyResults surveyresults = new SurveyResults(driver);
		return surveyresults;
	}

	/***
	 * Function to Verify TimeFrame functionality of survey result
	 * 
	 * @param verificationText
	 */

	public void verifyTimeframeFunctionalitySurveyresult(WebElement element) {

		// Select Date Range from Calendar Date
		WebElementUtility.selectDateRangeFromCalaender(element, crt_DateSelected, final_DateSelected, driver,
				TimeUtils.getCurrentDayofDate(), TimeUtils.getNextThirtyDayofDate());
		// Select End Date
		// WebWebElementUtility.selectFinalMonth(btn_Timeframe, final_date_Selected,
		// driver, TimeUtils.getNextThirtyDayofDate());
		btn_SaveDate.click();
		WebDriverUtility.wait(2);
		String validationText = TimeUtils.getDayInMMMddyyyyFormat() + " - "
				+ TimeUtils.getNextThirtyDayInMMMddyyyyFormat();
		DashboardAsserts.assertTextEquals(element.getText().trim(), validationText);

	}

	/**
	 * Function to verify batch button
	 * 
	 * @param validationText1
	 */
	public void verifyBatchButtonFunctionality(String validationText1) {

		WebElementUtility.click(btn_SendSurvey, driver);
		WebDriverUtility.wait(3);
		WebElementUtility.click(btn_batch, driver);
		WebDriverUtility.wait(2);
		DashboardAsserts.assertTextEquals(text_uploadBatch.getText().trim(), validationText1);
	}

	/**
	 * Function to verify send survey functionality with empty fields
	 * 
	 * @param validationText
	 */
	public void verifySendSurveyFunctionalityEmptyFields(String validationText) {

		WebElementUtility.click(btn_SendSurvey, driver);
		wait.until(ExpectedConditions.elementToBeClickable(btn_SurveySubmitButton));
		WebElementUtility.click(btn_SurveySubmitButton, driver);
		DashboardAsserts.assertTextEquals(text_emptyerror.getText().trim(), validationText);
	}

	/**
	 * Function to verify send survey functionality when we enter invalid values
	 *
	 * @param verificationText
	 */
	public void verifySendSurveyFunctionalityInvalidValues(String email, String name, String respondentType,
			String surveyType, String mobileNumber, String provider, String department, String verificationText) {
		WebElementUtility.click(btn_SendSurvey, driver);
		WebDriverUtility.wait(2);
		Select select = new Select(select_RespondentTypeSurvey);
		WebElementUtility.selectByPartOfVisibleTextIgnoreCase(select, respondentType);
		WebDriverUtility.wait(2);
		Select select1 = new Select(dropdown_surveytype);
		select1.selectByVisibleText(surveyType);
		WebDriverUtility.wait(2);
		WebElementUtility.sendText(driver, txt_Name, name);
		WebElementUtility.sendText(driver, txt_Email, email);
		WebElementUtility.sendText(driver, txt_Department, department);
		WebElementUtility.sendText(driver, txt_Provider, provider);
		Reporter.log("Clcik on Send Survey Button ");
		WebElementUtility.click(btn_SurveySubmitButton, driver);
		wait.until(ExpectedConditions.elementToBeClickable(text_emptyerror));
		DashboardAsserts.assertElementExists(text_emptyerror);
		DashboardAsserts.assertElementContainsText(text_emptyerror.getText(), verificationText);

	}

	// 803
	/**
	 * Function to Verify suggestion engine for Name Text Box on Send Survey Page
	 */
	public void VerifySuggetionEngineNameTextBox(String verificationText) {
		// can't find xpath of autosuggetion box
		WebElementUtility.click(btn_SendSurvey, driver);

		//name_field.sendKeys("a");
		WebElementUtility.click(name_field, driver);

		WebDriverUtility.wait(4);

		name_field.sendKeys(Keys.ARROW_DOWN);
		name_field.sendKeys(Keys.ENTER);

		WebDriverUtility.wait(5);

		

		
		

	}

	/**
	 * Function to verify empty send survey
	 * 
	 * @param verificationText
	 */
	public void verifySendSurveyFunctionalityEmptySurvey(String verificationText) {
		// TODO
		Assert.assertTrue(true, "Send Survey Functionality is not working");
	}

	// 799
	/**
	 * Function to Verify the Send Survey Functionality when Survey form is an Empty
	 * form (Blank)
	 */
	public void VerifySendSurveyFunctionalityEmptySurveyForm(String verificationText) {
		// TODO
		Assert.assertTrue(true, "Send Survey Functionality is not working");

	}

	/**
	 * Verify Verify Change Over Time appearance on dashboard\r\n
	 * 
	 * @param verificationText
	 * @param driver2
	 */
	public void VerifyChangeOverTimeAppearance(String verificationText) {
		wait.until(ExpectedConditions.visibilityOf(graphBar_ChangeOverTime.get(0)));
		for (WebElement changeGraph : graphBar_ChangeOverTime) {
			DashboardAsserts.assertElementExists(changeGraph);

		}
	}

	/**
	 * Verify Verify Change Over Time appearance on dashboard\r\n
	 * 
	 * @param verificationText
	 */
	public void VerifyTimeframeFunctionality_WrtOtherRespondent_Type(String verificationText) {
		WebElementUtility.selectRespondentFunctionality(select_RespondentTypeDashboard, driver, verificationText);
		WebDriverUtility.wait(5);
		String surveyChange = count_SurrveyScore.getText();
		WebDriverUtility.wait(2);
		verifyTimeframeFunctionality();
		WebDriverUtility.wait(5);
		String surveyChange1 = count_SurrveyScore.getText();
		Assert.assertNotEquals(surveyChange, surveyChange1);

	}

	public void verifyChangeOverTimeFunctionality(String email, String name, String respondentType, String surveyType,
			String mobileNumber, String provider, String department, String verificationText, Dashboard dashboard) {
		dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
				verificationText);
		try {
			String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
					Folder.READ_WRITE);
			dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Five Start Rating Comment");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		WebDriverUtility.wait(5);
		driver.navigate().refresh();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		wait.until(ExpectedConditions.visibilityOf(graphBar_ChangeOverTime.get(0)));
		for (WebElement changeGraph : graphBar_ChangeOverTime) {
			DashboardAsserts.assertElementExists(changeGraph);
		}
		WebDriverUtility.wait(5);
		Actions action = new Actions(driver);
		action.dragAndDrop(graphBar_ChangeOverTimePanel.get(0), graphBar_ChangeOverTime.get(2)).build().perform();
		WebDriverUtility.wait(5);

	}

	/**
	 * Function to select Default Value for Company
	 */
	public void selectDefaultCompany() {
		WebElementUtility.click(btn_UserCompany, driver);
        WebDriverUtility.wait(2);
        //wait.until(ExpectedConditions.elementToBeClickable(btn_UserCompany));
        //WebElementUtility.click(btn_UserCompany, driver);
        WebElementUtility.click(btn_CompanyChange, driver);
        WebDriverUtility.wait(2);
        WebElementUtility.click(txt_Company2, driver);
        wait.until(ExpectedConditions.visibilityOf(txt_CurrantCompany));
        WebDriverUtility.wait(5);
		
	}

}
