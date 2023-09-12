package com.MedRev.suits.test;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MedRev.feature.Analysis;
import com.MedRev.feature.Dashboard;
import com.MedRev.utils.SuitesHelper;
import com.packages.common.Config;
import com.packages.common.DDT;

public class AnalysisViewTest {
	private WebDriver driver;
	private Dashboard dashboard;
	private Analysis analysis;

	@DataProvider(name = "analysis")
	public Object[][] data() {
		return DDT.DDTReader("src/main/resources/DDT/Analysis.csv");
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

	@Test(dataProvider = "analysis")
	public void analysis(String tcId, String tsId, String key, String featureId, String email, String name,
			String respondentType, String surveyType, String mobileNumber, String provider, String department,String verificationText1,String verificationText2, String verificationText3) throws InterruptedException {
		analysis = dashboard.gotoTabAnalysis(driver);
		switch (key) {
		case "ANALYSIS_FUNCTIONALITY_904":
			Reporter.log("Verify the Analysis Functionality ");
			analysis.verifyAnalysisFunctionality(verificationText1, verificationText2);
			break;
		case "ANALYSIS_SELECT_TIMEFRAME_FUNCTIONALITY_905":
			Reporter.log("Verify the Select time frame functionality ");
			analysis.verifyAnalysisTimeframeFunctionality(verificationText1, dashboard);
			break;
		case "ANALYSIS_DROPDOWN_ALLRESPONDENT_SELECTED_906":
			Reporter.log("Verify the Respondent Type Dropdown when  All Respondent is selected");
			analysis.verifyRespondentTypeDrpAllRespondentSelected(verificationText1, verificationText2);
			break;
		case "ANALYSIS_DROPDOWN_OTHER_SELECTED_907":
			Reporter.log("Verify the Respondent Type Dropdown when other type is selected");
			analysis.verifyRespondentTypeDrpOtherSelected(verificationText1, verificationText2);
			break;
		case "ANALYSIS_DROPDOWN_CLIENT_SELECTED_908":
			Reporter.log("Verify the Respondent Type Dropdown when Client is selected from Filter By Survey Section");
			analysis.verifyRespondentTypeDrpClientSelected(verificationText1);
			break;
		case "ANALYSIS_SURVEY_TYPE_DROPDOWN_GENERALSTATISTICS_SELECTED_909":
			Reporter.log("Verify the Survey Type   Dropdown when  General Statistic  is selected under Filter By Survey Section");
			analysis.verifySurveyTypeDrpGeneralStatisticSelected(verificationText1, verificationText2);
			break;
		case "ANALYSIS_SURVEY_TYPE_DROPDOWN_OTHER_GENERALSTATISTICS_SELECTED_910":
			Reporter.log("Verify the Survey Type Dropdown when option other than General Statistic  is selected");
			analysis.verifySurveyTypeDrpOtherThanGeneralStatisticSelected(verificationText1, verificationText2);
			break;
		case "ANALYSIS_DATA_DISPLAY_SELECTED_RESPONDENT/SURVEY_TYPE_911":
			Reporter.log("Verify that data is correctly displayed for selected Respondent and Survey Type");
			analysis.verifyDataDisplaySelectedRespondentSurveyType(verificationText1, verificationText2);
			break;
		case "ANALYSIS_BUSINESS_STATISTIC_SECTION_912":
			Reporter.log("Verify the Your Business Statistic Section");
			analysis.verifyYourBusinessStatisticSection(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, dashboard);
			break;
		case "ANALYSIS_GOOD/BAD_SECTION_BUSINESS_STATISTIC_SECTION_913":
			Reporter.log("Verify the Good/bad column on Your Business Statistic Section  with respective specific survey");
			analysis.verifyGoodBadColumnBusinessStatisticSection(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, verificationText3,  dashboard);
			break;
		case "ANALYSIS_SECTION_SURVEY_COMMENT_NEGATIVE_914":
			Reporter.log("Verify section Survey Comment  most used negative word");
			analysis.verifySurveyCommentNegativeword(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, verificationText3, dashboard);
			break;
		case "ANALYSIS_GOOD/BAD_SECTION_BUSINESS_STATISTIC_SECTION_WRT_GENERAL_STATISTICS_915":
			Reporter.log("Verify the Good/bad column on Your Business Statistic Section  with respective General statistic");
			analysis.verifyGoodBadColumnBusinessStatisticSectionWrtGeneralStatistics(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2,   dashboard);
			break;
		case "ANALYSIS_SECTION_SURVEY_COMMENT_POSITIVE_916":
			Reporter.log("Verify section Survey Comment  most used positive word");
			analysis.verifySurveyCommentPositiveword(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, verificationText3,  dashboard);
			break;
		case "ANALYSIS_SECTION_SURVEY_COMMENT_MOST_USED_WORD_917":
			Reporter.log("Verify section Survey Comment  most used word");
			analysis.verifySurveyCommentMostUsedWord(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, verificationText3,  dashboard);
			break;
		case "ANALYSIS_COMMENTS_SENTIMENTS_RATING_SECTION_921":
			Reporter.log("Verify the Survey Comments Sentiment  Rating Section");			
			analysis.verifySurveyCommentsSentimentsRatingSection(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, dashboard);
			break;
		case "ANALYSIS_FACEBOOK_RATING_SECTION_922":
			Reporter.log("Verify the Facebook  Rating Section");
			analysis.verifyFacebookRatingSection(verificationText1);
			break;
		case "ANALYSIS_SURVEY_SENT_BUSINESS_STATISTICS_SECTION_WRT_GENERALSTATISTICS_923":
			Reporter.log("Verify the Survey Sent  column on Your Bussiness Statistic Section  with respective General statistic");
			analysis.verifySurveySentBusinessStatisticsSectionWrtGeneralStatistics(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, dashboard);
			break;
		case "ANALYSIS_EMAIL_SENT_BUSNINESS_STSTISTICS_SECTION_WRT_GENERALSTATISTICS_924":
			Reporter.log("Verify the Total Email Sent  column on Your Business Statistic Section  with respective General statistic");
			analysis.verifyEmailSentBusinessStatisticsSectionwrtGeneralStatistics(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, dashboard);
			break;
		case "ANALYSIS_TOTALTXT_SENT_BUSNINESS_STSTISTICS_SECTION_925":
			Reporter.log("Verify the Total Text Sent  column on Your Business Statistic Section");
			analysis.verifyTotalTextSentBusinessStatisticsSection(verificationText1);
			break;
		case "ANALYSIS_TOTALTIME_FRAME_RATING_COLUMN_BUSNINESS_STSTISTICS_SECTION_926":
			Reporter.log("Verify the Total Time Frame Rating column on Your Bussiness Statistic Section");
			analysis.verifyTotalTimeframeRatingBusinessStatisticsSection(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, dashboard);
			break;
		case "ANALYSIS_ALLTIME_RATING_COLUMN_BUSNINESS_STSTISTICS_SECTION_927":
			Reporter.log("Verify the Total Time Frame Rating column on Your Bussiness Statistic Section");
			analysis.verifyAllTimeRatingBusinessStatisticsSection(verificationText1,verificationText2);
			break;
		case "ANALYSIS_SURVEY_QUESTION_RATING_928":
			Reporter.log("Verify the Survey Question Rating ");
			analysis.verifySurveyQuestionRating(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, dashboard);
			break;
		case "ANALYSIS_GOOGLE_RATING_929":
			Reporter.log("Verify the Google  Rating Section ");
			analysis.verifyGoogleRating(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText1, verificationText2, dashboard);
			break;
		}
	

	}

}
