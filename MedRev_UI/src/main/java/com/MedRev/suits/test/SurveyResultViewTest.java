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
import com.MedRev.feature.SurveyResults;
import com.MedRev.utils.GmailUtility;
import com.MedRev.utils.SuitesHelper;
import com.packages.common.Config;
import com.packages.common.Constants;
import com.packages.common.DDT;
public class SurveyResultViewTest {
	private WebDriver driver;
	public Dashboard dashboard;
	private SurveyResults surveyresults;
	
	@DataProvider(name = "surveyResult")
	public Object[][] data() {
		return DDT.DDTReader("src/main/resources/DDT/SurveyResults.csv");
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

	@Test(dataProvider = "surveyResult")
	public void dastboard(String tcId, String tsId, String key, String featureId, String email, String name,
			String respondentType, String surveyType, String mobileNumber, String provider, String department,String verificationText,String verificationText1,String verificationText2,String verificationText3 ) throws InterruptedException {
		
	
		surveyresults = dashboard.gotoTabSurveyResult(driver);

		switch (key) {
		
		case "SURVEYRESULT_USER_NAVIGATE_SURVEYRESULT_SECTION_823":
			Reporter.log("Verify that user can navigate to Survey Results Section");
			surveyresults.verifyUserNavigateToSurveyResult(verificationText);
			break;
			
		case "SURVEYRESULT_FUNCTIONALITY_RESPONDENTTYPE_DRP_SURVEYSECTION_824":
			Reporter.log("verify the functionality of Respondent type dropdown at Survey Results Screen");
			surveyresults.verifyRespondentTypeDrpSurveyScreen(verificationText);
			break;
			
		case "SURVEYRESULT_AllRESPONDENTDATA_APPEARS_SURVEYRESULT_825":
			Reporter.log("Verify the All Respondent type data appearance at Survey Results Screen");
			surveyresults.verifyAllRespondentTypeDataAppears(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText,dashboard);
			break;

		case "SURVEYRESULT_OTHERRESPONDENTDATA_APPEARS_SURVEYRESULT_826":
			Reporter.log("Verify the Other Respondent type data appearance at Survey Results Screen");
			surveyresults.verifyOtherRespondentTypeDataAppears(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText,dashboard);
			break;
			
		case "SURVEYRESULT_SURVEYAPPEARANCE_RESPONDENT/NONRESPONDENT_DRP_827":
			Reporter.log("Verify the survey's appearance of Responded & Not Responded dropdown at Survey Results Screen");
			surveyresults.verifySurveyAppearanceRespondentNonRespondentDrp(verificationText);
			break;
			
		case "SURVEYRESULT_ALLREVIEW_RATING_DROPDOWN_828":
			Reporter.log("Verify the functionality of All Review Ratings dropdown at Survey Results Screen");
			surveyresults.verifyAllReviewRatingDrp(verificationText);
			break;
			
		case "SURVEYRESULT_NEGATIVE_REVIEW_829":
			Reporter.log("Verify the Negative Reviews Ratings data appearance at Survey Results Screen");
			dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
					verificationText);
			try {
        	  String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
              Folder.READ_WRITE);
        	  dashboard.fillSurvey(url,
              Constants.Rating.ONESTAR, "Two Start Rating Comment");

			} catch (Exception e) {
        	  e.printStackTrace();
			}
			surveyresults = dashboard.gotoTabSurveyResult(driver);
			surveyresults.verifyNegativeReviewRating( verificationText,verificationText1);
			break;
			
		case "SURVEYRESULT_POSITIVE_REVIEW_830":
			Reporter.log("Verify the Positive Reviews Ratings data appearance at Survey Results Screen");
			dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
					verificationText);
			try {
              String url =  GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "", Folder.READ_WRITE);
              dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Five Start Rating Comment");
			}
			catch (Exception e) {
              e.printStackTrace();
			}
			surveyresults = dashboard.gotoTabSurveyResult(driver);
			surveyresults.verifyPositiveReviewRating( verificationText,verificationText1);
			break;
			
		case "SURVEYRESULT_TIMEFRAME_831":
			Reporter.log("Verify the functionality of Select Timeframe dropdown at Survey Results Screen");
			surveyresults.verifyTimeframeDrp(verificationText);
			break;
			
		case "SURVEYRESULT_SEARCHRESULT_APPEARANCE_832":
			Reporter.log("Verify the appearance of Search Respondents at Survey Results Screen");
			surveyresults.verifySearchReasultAppearance(verificationText);
			break;
			
		case "SURVEYRESULT_SEARCHBUTTON_APPEARANCE_833":
			Reporter.log("Verify the functionality of Search Button at Survey Results Screen");
			surveyresults.verifySearchButtonAppearance(verificationText);
			break;
			
		case "SURVEYRESULT_CLEARSEARCHBUTTON_APPEARANCE_834":
			Reporter.log("Verify the functionality of Clear Search Button at Survey Results Screen");
			surveyresults.verifyClearSearchButtonAppearance(verificationText);
			break;
			
		case "SURVEYRESULT_CHANGElOCATION_FUNCTIONALITY_835":
			Reporter.log("verify the functionality of Change Location at Survey Results Screen");
			surveyresults.verifyChangeLocationFunctionality(verificationText);
			break;
			
        case "SURVEY_RESULT_CURRENT_COMPANY_836":
            Reporter.log("verify survey result screen when company is selected");
            surveyresults.verifySurveyresultCompanySelected(verificationText,verificationText1,verificationText2);
            break;
            
        case "SURVEY_RESULT_SCREEN_APPEARANCE_846":
            Reporter.log("verify survey result screen when company is selected");
            surveyresults.verifySurveyresultAppearance(verificationText1,dashboard,verificationText2,verificationText3);
            break;
            
        case "SURVEY_RESULT_SCREEN_RATING_BOX_ABOVE_FOUR":
            Reporter.log("verify rating between 4 to 4+ in rating box");
           dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber,
                    provider, department, verificationText);
            try {
                String url =  GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "", Folder.READ_WRITE);
                dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Five Start Rating Comment");
           }
            catch (Exception e) {
                e.printStackTrace();
            }
           surveyresults = dashboard.gotoTabSurveyResult(driver);
           surveyresults.verifyRatingBoxAboveFour(name, verificationText,verificationText1, verificationText2);
            break;
            
        case "SURVEY_RESULT_SCREEN_RATING_BOX_TWO_TO_FOUR":
            Reporter.log("verify rating between 2.1 to 3.9 in rating box");
            dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
                    verificationText);
            try {
                String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
                        Folder.READ_WRITE);
                dashboard.fillSurvey(url,
                        Constants.Rating.THREESTAR, "Three Start Rating Comment");

           } catch (Exception e) {
                e.printStackTrace();
            }
            surveyresults = dashboard.gotoTabSurveyResult(driver);
            surveyresults.verifyRatingBoxAverage( name, verificationText,verificationText1, verificationText2);
            break;
            
        case "SURVEY_RESULT_SCREEN_RATING_BOX_ZERO_TO_TWO":
            Reporter.log("verify rating between 0 to 2 in rating box");
            dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
                    verificationText);
            try {
                String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
                        Folder.READ_WRITE);
                dashboard.fillSurvey(url,
                        Constants.Rating.ONESTAR, "Two Start Rating Comment");

           } catch (Exception e) {
                e.printStackTrace();
            }
            surveyresults = dashboard.gotoTabSurveyResult(driver);
            surveyresults.verifyRatingBoxBelow(name, verificationText,verificationText1, verificationText2);
            break;
            
        case "SURVEY_RESULT_SCREEN_RESPONDED_DROPDOWN_850":
            Reporter.log("verify responded dropdown on survey result screen");
            dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText); 
            try {
          	  String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
                Folder.READ_WRITE);
          	  	dashboard.fillSurvey(url,
                Constants.Rating.ONESTAR, "Two Start Rating Comment");

  			} catch (Exception e) {
          	  e.printStackTrace();
  			}
  			surveyresults = dashboard.gotoTabSurveyResult(driver);
 
            surveyresults.verifyRespondedDropdownFunctionality(name,verificationText,verificationText1,verificationText2,verificationText3);
            break;
           
        case "SURVEY_RESULT_SCREEN_NOT_RESPONDENT_DROPDOWN_851":
            Reporter.log("verify not responded dropdown on survey result screen");
            
            surveyresults.verifyNotRespondedDropdownFunctionality(verificationText1, verificationText1);
            break;
            
        case "SURVEY_RESULT_SCREEN_RESPOND_MAIL_852":
            Reporter.log("verify respond by mail functionality on survey result screen");
            dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department,
                    verificationText);
          try {
              String url = GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "",
                      Folder.READ_WRITE);
              dashboard.fillSurvey(url,
                      Constants.Rating.ONESTAR, "Two Start Rating Comment");

         } catch (Exception e) {
              e.printStackTrace();
          }
          surveyresults = dashboard.gotoTabSurveyResult(driver);
           
           surveyresults.verifyResopondbymailFunctionality(name,email, verificationText,verificationText1, verificationText2,verificationText3);
            break;
            
        case "SURVEY_RESULT_SCREEN_THANK_MAIL_853":
            Reporter.log("verify thank by mail functionality on survey result screen");
            dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber,
            		provider, department, verificationText);
            try {
        	  	String url =  GmailUtility.gmailUtils(Config.getGMAILUser(), Config.getGMAILPassword(), "", Folder.READ_WRITE);
        	  	dashboard.fillSurvey(url, Constants.Rating.FIVESTAR, "Five Start Rating Comment");
            	}
            catch (Exception e) {
        	  e.printStackTrace();
            }
            surveyresults = dashboard.gotoTabSurveyResult(driver);
            surveyresults.verifyThankMailFunctionality(name,email, verificationText,verificationText1, verificationText2,verificationText3);
            break;
            
        case "SURVEY_RESULT_SCREEN_CLEAR_SEARCH_BUTTON_1110":
            Reporter.log("verify clear search button functionality on survey result screen");
            surveyresults.verifyClearSearchButtonFunctionality(verificationText1, verificationText2,verificationText3);
            break;
}
}
}