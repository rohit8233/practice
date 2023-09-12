package com.MedRev.suits.test;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MedRev.feature.Dashboard;
import com.MedRev.utils.SuitesHelper;
import com.packages.common.Config;
import com.packages.common.DDT;

public class SendSurveyPageTest {
	private WebDriver driver;
	private Dashboard dashboard;

	@DataProvider(name = "SendSurveyPage")
	public Object[][] data() {
		return DDT.DDTReader("src/main/resources/DDT/SendSurveyPage.csv");
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

	@Test(dataProvider = "SendSurveyPage")
	public void SendSurveyPage(String tcId, String tsId, String key, String featureId, String email, String name,
			String respondentType, String surveyType, String mobileNumber, String provider, String department,String verificationText) throws InterruptedException {
		switch (key) {
           
		case "SEND_SURVEY_VALID_FUNCTIONALITY_795":
            Reporter.log("verify batch button functionality when we click on batch button");
            dashboard.sendSurvey(email, name, respondentType, surveyType, mobileNumber, provider, department, verificationText);
            break;
		   //New code for survey page
        case "SEND_SURVEY_UPLOAD_BATCH_FUNCTIONALITY_797":
            Reporter.log("verify batch button functionality when we click on batch button");
            dashboard.verifyBatchButtonFunctionality(verificationText);
            break;
            
        case "SEND_SURVEY_EMPTY_FIELDS_FUNCTIONALITY_798":
            Reporter.log("verify send survey functionality when we keep fields empty");
            dashboard.verifySendSurveyFunctionalityEmptyFields(verificationText);
            break;
            
            //799 -surveyType functionality is not working
        case "DASHBOARD_SENDSURVEY_FUNCTIONALITY_EMPTYSURVEYFORM_799":
            Reporter.log("Verify the Send Survey Functionality when Survey form is an Empty form (Blank)");
            dashboard.VerifySendSurveyFunctionalityEmptySurveyForm(verificationText);
            break;
           
        case "SEND_SURVEY_INVALID_FIELDS_FUNCTIONALITY_800":
            Reporter.log("verify send survey functionality when we enter invalid values");
            dashboard.verifySendSurveyFunctionalityInvalidValues( email,  name,  respondentType,  surveyType,  mobileNumber,
    				 provider,  department,  verificationText);
            break;
            
        case "SEND_SURVEY_INVALID_PHONE_FUNCTIONALITY_801":
            Reporter.log("verify send survey functionality when we enter invalid phone");
   //         dashboard.verifySendSurveyFunctionalityInvalidPhone(verificationText);
            break;
            
        case "SEND_SURVEY_BLANK_SURVEYTYPE_FUNCTIONALITY_802":
            Reporter.log("verify send survey functionality when we enter empty survey");
            dashboard.verifySendSurveyFunctionalityInvalidValues( email,  name,  respondentType,  surveyType,  mobileNumber,
   				 provider,  department,  verificationText);
            break;
            //803-can't find xpath
        case "DASHBOARD_SUGGESTIONENGINE_NAMETEXTBOX_SENDSURVEYPAGE_803":
            Reporter.log("Verify suggestion engine for Name Text Box  on Send Survey Page");
            dashboard.VerifySuggetionEngineNameTextBox(verificationText);
            break;
           
        case "SEND_SURVEY_INVALID_EMAIL_FUNCTIONALITY_804":
            Reporter.log("verify send survey functionality when we enter invalid email");
            dashboard.verifySendSurveyFunctionalityInvalidValues( email,  name,  respondentType,  surveyType,  mobileNumber,
      				 provider,  department,  verificationText);
            break;
            
        case "SEND_SURVEY_SINGLE_ANSWER_FUNCTIONALITY_805":
            Reporter.log("verify send survey functionality when we have single answer");
            dashboard.verifySendSurveyFunctionalityInvalidValues( email,  name,  respondentType,  surveyType,  mobileNumber,
   				 provider,  department,  verificationText);
            break;
           
        case "SEND_SURVEY_TEXT_REPLIES_FUNCTIONALITY_931":
            Reporter.log("verify send survey functionality of text replies page");
          //  dashboard.verifySendSurveyFunctionalityTextReplies(verificationText);
            break;
      
        }
			
		}
	

	}


