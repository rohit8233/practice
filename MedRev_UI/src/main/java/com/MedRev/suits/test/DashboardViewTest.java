package com.MedRev.suits.test;

import javax.mail.Folder;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MedRev.feature.Dashboard;
import com.MedRev.utils.GmailUtility;
import com.MedRev.utils.SuitesHelper;
import com.packages.common.Config;
import com.packages.common.Constants;
import com.packages.common.DDT;

public class DashboardViewTest {
	private WebDriver driver;
	private Dashboard dashboard;

	@DataProvider(name = "dashboard")
	public Object[][] data() {
		return DDT.DDTReader("src/main/resources/DDT/Dashboard.csv");
	}

	@BeforeMethod
	@Parameters("browser")
	public void setup(@Optional String browser) {
		driver = Config.getDriverMedRev(browser);
		dashboard = SuitesHelper.doLogin(driver);
	}

	@AfterMethod
	public void tearDown() {
		SuitesHelper.closeAllBrowserWindows(driver);
	}

	@Test(dataProvider = "dashboard")
	public void dashboard(String tcId, String tsId, String key, String featureId, String email, String name,
			String respondentType, String surveyType, String mobileNumber, String provider, String department,
			String verificationText) throws InterruptedException {
		dashboard.selectDefaultCompany();
		switch (key) {
		case "DASHBOARD_SEND_SURVEY":
			Reporter.log("Show MedRev Dashboard Item Information");
			Reporter.log("Verify Send Survey Functionality");

			dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
					verificationText);
			try {
				String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
						Folder.READ_WRITE);
				dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, Constants.SURVEY_COMMENTS);

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "DASHBOARD_TIMEFRAME":
			Reporter.log("Verify select Timeframe functionality");
			dashboard.verifyTimeframeFunctionality();
			break;

		case "DASHBOARD_RESPONDENT":
			Reporter.log("Verify Respondent Type Options");
			dashboard.verifyRepondentTypeOptions(verificationText);
			break;

		case "DASHBOARD_LOCATION":
			Reporter.log("Verify Dashboard Location Functionality");
			dashboard.verifyLocationTypeOptions(verificationText);
			break;

		case "DASHBOARD_USER_PROFILE_DROPDOWN":
			Reporter.log("Verify user profile dropdown on dashboard");
			dashboard.verifyUserProfileDropdown(verificationText);
			break;

		case "DASHBOARD_OVERVIEW_APPEARANCE":
			Reporter.log("Verify overview appearance on dashboard");
			dashboard.verifyOverviewAppearance(verificationText);
			break;

		case "DASHBOARD_NET_PROMOTER_FUCTIONALITY":
			Reporter.log("Verify Net Promoter Score functionality under overview");
			dashboard.verifNetPromoterFunctionality(email, name, respondentType, surveyType, mobileNumber, provider,
					department, verificationText, dashboard);
			break;

		case "DASHBOARD_SURVEY_FILLED_COUNT":
			Reporter.log("Verify Surveys Filled functionality under overview section on dashboard");
			dashboard.verifServeyFilledCount(email, name, respondentType, surveyType, mobileNumber, provider,
					department, verificationText, dashboard);
			break;

		case "DASHBOARD_SURVEY_SCORE_COUNT":
			Reporter.log("Verify Survey Score functionality under overview section on dashboard");

			dashboard.verifySurveyScoreFunctionality(email, name, respondentType, surveyType, mobileNumber, provider,
					department, verificationText, dashboard);
			break;

		case "DASHBOARD_QUESTION_LOCATION_CHANGE":
			Reporter.log("Verify Questions functionality when any location is selected whole dashboard should reset");
			dashboard.verifyQuestionFunctionality_Location(verificationText);
			break;

		case "DASHBOARD_QUESTION_AllRESPONDENT_SELECTED":
			Reporter.log(
					"Verify Questions functionality when all respondent type is selected for that particular location.");
			dashboard.verifyQuestionFunctionality_AllRespondentSelected(verificationText);
			break;

		case "DASHBOARD_QUESTION_EMPLOYEE_SELECTED":
			Reporter.log("Verify Questions functionality when employee is selected for that particular location.");
			dashboard.verifyQuestionFunctionality_EmployeeSelected(verificationText);
			break;
		// Duplicate
		case "DASHBOARD_QUESTION_CLIENT_SELECTED":
			Reporter.log("Verify Questions functionality when client is selected for that particular location.");
			dashboard.verifyQuestionFunctionality_ClientSelected(verificationText);
			break;

		case "DASHBOARD_QUESTION_FUCTIONALITY_TIMEFRAME_WITH_RESPONDENT_TYPE":
			Reporter.log(
					"Verify Questions functionality when a specific timeframe is selected in response with respondent type.");
			dashboard.VerifyQuestionFunctionality_TimeframeSelected_WrtRespondent_Type(verificationText);
			break;

		case "DASHBOARD_QUESTION_COMPANY_CHANGED":
			Reporter.log("Verify Questions functionality when current company is changed.");
			dashboard.verifyQuestionFunctionality_CompanyChanged(verificationText);
			break;

		case "DASHBOARD_QUESTION_APPEARNACE_DASHBOARD":
			Reporter.log("Verify Questions appearance on dashboard ");
			dashboard.verifyQuestionFunctionality_DashboardAppearance(verificationText);
			break;

		case "DASHBOARD_SURVEY_CHANGE":
			Reporter.log("Verify Survey Change functionality under overview section on dashboard");
			dashboard.verifyQuestionFunctionality_SurveyChange(verificationText);
			break;

		case "DASHBOARD_SELECTED_ALLRESPONDENT_DEFAULTLOCATION":
			Reporter.log(
					"Verify overview functionality when selected Respondent Type is All Respondent and selected location is default location");
			dashboard.verifyOverviewFunctionality_allRespondent_defLocation(verificationText);
			break;

		case "DASHBOARD_SELECTED_ALLRESPONDENT_CHANGED_LOCATION":
			Reporter.log(
					"Verify overview functionality when selected respondent type is all respondent type and location is changed");
			dashboard.verifyOverviewFunctionality_allRespondent_ChangedLocation(verificationText);
			break;

		case "DASHBOARD_SELECTED_OTHER_RESPONDENT_TYPE_DEFAULTLOCATION":
			Reporter.log(
					"Verify overview functionality when any other respondent type is selected and location is default location");
			dashboard.verifyOverviewFunctionality_otherRespondent_defLocation(verificationText);
			break;

		case "DASHBOARD_SELECTED_All_RESPONDENT":
			Reporter.log("Verify respondent functionality when All respondent is selected");
			dashboard.verifyRespondentFunctionality_AllRespondentType_Selected(verificationText);
			break;

		case "DASHBOARD_SELECTED_OTHER_RESPONDENT":
			Reporter.log("Verify respondent type functionality, When other respondent is selected");
			dashboard.verifyRespondentFunctionality_OtherRespondentType_Selected(verificationText);
			break;

		case "DASHBOARD_SURVEY_QUESTIONS_UNDER_QUESTION_SECTION":
			Reporter.log("Verify Survey's With the questions are displayed under Questions section.");
			dashboard.verifySurveyQuestion_UnderQuestionSection(verificationText);
			break;

		case "DASHBOARD_SELECTED_OTHER_RESPONDENT_CHANGED_LOCATION":
			Reporter.log("Verify overview functionality when any other Respondent Type is selected and location is changed");
			dashboard.verifyRespondentFunctionality_OtherRespondentType_ChangedLocation(verificationText);
			break;

		case "DASHBOARD_GEAPH_DISPLAY_UNDER_QUESTION_SECTION":
			Reporter.log("Verify Under Questions Section for each question the graph is displayed.");
			dashboard.verifyGraphDisplayQuestion_UnderQuestionSection(verificationText);
			break;

		case "DASHBOARD_HOVER_GEAPH_RATING_DISPLAY_UNDER_QUESTION_SECTION":
			Reporter.log("Verify under Questions Section when we hover over the graph the rating is displayed.");
			dashboard.verifyHoverGraph_RatingDisplayQuestion_UnderQuestionSection(verificationText);
			break;

		case "DASHBOARD_TIMEFRAME_FUCTIONALITY_WRT_ALLRESPONDENT_TYPE":
			Reporter.log("Verify  Timeframe with respect to Respondent type (All respondent type) at Dashboard");
			dashboard.VerifyTimeframeFunctionality_WrtAllRespondent_Type(verificationText);
			break;

		case "DASHBOARD_CHANGE_OVERTIME_APPEARANCE":
			Reporter.log("Verify Change Over Time appearance on dashboard\r\n");
			dashboard.VerifyChangeOverTimeAppearance(verificationText);
			break;

		case "DASHBOARD_TIMEFRAME_FUCTIONALITY_WRT_OTHER_RESPONDENT_TYPE":
			Reporter.log("Verify Timeframe with respect to Respondent type (Other) at Dashboard\r\n");
			dashboard.VerifyTimeframeFunctionality_WrtOtherRespondent_Type(verificationText);
			break;
		

		case "DASHBOARD_EDIT_QUESTION_BUTTON_QUESTION_SECTION":
			Reporter.log("verifyEditQuestionButton_QuestionSection");
			dashboard.verifyEditQuestionButton_QuestionSection(verificationText);
			break;

		case "DASHBOARD_SURVEY_YOUR_ATTENTION_SECTION_APPEARANCE":
			Reporter.log("Verify \"Surveys that require your attention\" section appearance");
			dashboard.verifySurvey_Attention_SectionAppearance(verificationText);
			break;

		case "DASHBOARD_BAD_RATING_SURVEYBTN_WRT_SURVEY_YOUR_ATTENTION":
			Reporter.log("Verify \\\"Check bad ratings survey\\\" button With respect to \\\"Surveys that require your attention\\\" section at dashboard");
			dashboard.verifyBadRatingSurvey_wrtSurvey_Attention(verificationText);
			break;

		case "DASHBOARD_OVERVIEW_FUNCTIONALITY_COMPANY_CHANGED":
			Reporter.log("Verify overview functionality when current company changed");
			dashboard.verifyOverviewFunctinality_Companay_Changed(verificationText);
			break;
			//
		case "DASHBOARD_CHANGE_OVERTIME_FUNCTIONALITY":
			Reporter.log("Verify Change Over Time functionality on dashboard");
			dashboard.verifyChangeOverTimeFunctionality(email, name, respondentType, surveyType, mobileNumber, provider,
					department, verificationText, dashboard);
			break;
		case "DASHBOARD_OVERTIME_FUNCTIONALITY_LOCATION_CHANGED":
			Reporter.log("Verify Change Over Time functionality when location is changed");
			dashboard.verifyOverTimeFunctinality_Location_Changed(verificationText);
			break;

		case "DASHBOARD_OVERTIME_FUNCTIONALITY_CURRENT_COMPANY_CHANGED":
			Reporter.log("Function to Change Over Time functionality when current company is changed");
			dashboard.verifyOverTimeFunctinality_CrtCompany_Changed(verificationText);
			break;

		case "DASHBOARD_LEFT_RIGHTDRAG_ICONS_FUNCTIONALITY_CHANGE_OVERGRAPH":
			Reporter.log("Verify Left and Right Drag icons functionality on Change Over Graph");
			dashboard.verifyLeftRightDragIcons_Functinality_Change_OverGraph(verificationText);
			break;

		case "DASHBOARD_TOTAL_RATINGS_FUNCTIONALITY_FACEBOOK_PLATFORMREVIEW_SECTION":
			Reporter.log("Verify Total ratings functionality of facebook in Platform Review Score section");
			dashboard.verifyTotalRating_Functinality_Facebook_PlatformReviewScoreSection(verificationText);
			break;

		case "DASHBOARD_PLATFORM_REVIEW_SCORE_APPEARANCE":
			Reporter.log("Verify Platform review score appearance on dashboard");
			dashboard.VerifyPlatformReviewScoreAppearance(verificationText);
			break;

		}

	}
}
