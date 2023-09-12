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
import com.MedRev.feature.SurveyTable;
import com.MedRev.utils.SuitesHelper;
import com.packages.common.Config;
import com.packages.common.DDT;
public class SurveyTableViewTest {
	private WebDriver driver;
	public Dashboard dashboard;
	private SurveyTable surveyTable;

	@DataProvider(name = "survey")
	public Object[][] data() {
		return DDT.DDTReader("src/main/resources/DDT/SurveyTable.csv");
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

	@Test(dataProvider = "survey")
	public void dastboard(String tcId, String tsId, String key, String featureId, String email, String name, String respondentType,String surveyType, String mobileNumber,
			String provider, String department, String verificationText1, String verificationText2, String verificationText3) throws Throwable {
		
	
		surveyTable = dashboard.gotoTabSurvey(driver);

		switch (key) {
		
        case "SURVEY_TABLE_PAGE_APPEARANCE_876":
            Reporter.log("verify surveys table section in survey table screen");
            surveyTable.verifySurveyTableSection(verificationText1);
            break;
        case "SURVEY_TABLE_SECTION_APPEARANCE_878":
            Reporter.log("verify surveys table contents in survey table section");
            surveyTable.verifySurveyTableContents(verificationText1, verificationText2);
            break; 
        case "SURVEY_TABLE_CHANGED_LOCATION_879":
            Reporter.log("verify surveys table when location is changed");
            surveyTable.verifySurveyTableLocationChanged(verificationText1);
            break;
        case "SURVEY_TABLE_SCREEN_CHANGE_USER_880":
            Reporter.log("verify surveys table when user is changed");
            surveyTable.verifySurveyTableUserChanged();
            break; 
        case "SURVEY_TABLE_SCREEN_RESPONDENT_TYPE_881":
            Reporter.log("verify dropdown in surveys table screen");
            surveyTable.verifySurveyTableDropdown(verificationText1);
            break;
        case "SURVEY_TABLE_SCREEN_ALL_RESPONDENT_TYPES_DATA_882":
            Reporter.log("Verify data appearance when all respondent type dropdown is selected");
            surveyTable.verifySurveyTableAllRespondenttypeData( email, name, respondentType, surveyType, mobileNumber, provider, department,
                     verificationText1, verificationText2, dashboard);
            break;
        case "SURVEY_TABLE_SCREEN_OTHER_RESPONDENT_TYPE_DATA_884":
            Reporter.log("Verify data appearance when other respondent type dropdown is selected");
            surveyTable.verifySurveyTableotherRespondenttypeData( email, name, respondentType, surveyType, mobileNumber, provider, department,
                    verificationText1, verificationText2, dashboard);
            break;
        case "SURVEY_TABLE_SCREEN_DOWNLOAD_BUTTON_885":
            Reporter.log("verify download button in survey table screen");
            surveyTable.verifySurveyTableDownloadButton(verificationText1, verificationText2);
            break;
        case "SURVEY_TABLE_SCREEN_PAGER_FUNCTIONALITY_887":
            Reporter.log("verify pager functionality in survey table screen");
            surveyTable.verifySurveyTablePagerFunctionality(verificationText1);
            break;
        case "SURVEY_TABLE_SCREEN_SORTING_FUNCTIONALITY_888":
            Reporter.log("verify survey functionality in survey table screen");
            surveyTable.verifySurveyTableSortingFunctionality(verificationText1);
            break;  
        case "SURVEY_TABLE_FUNCTIONALITY_889":
            Reporter.log("verify survey table functionality when location is selected");
            surveyTable.verifySurveyTableFunctionality(verificationText1,verificationText2 );
            break;
        case "SURVEY_TABLE_ATTEMPTED_APPEARANCE_890":
            Reporter.log("verify attempted surveys appearance at survey table"); 
            surveyTable.verifySurveyTableAttemptedAppearance(email, name, respondentType, surveyType, mobileNumber, provider, department,
                    verificationText1, verificationText2, dashboard);
            break;
        case "SURVEY_TABLE_NOT_ATTEMPTED_APPEARANCE_891":
            Reporter.log("verify not attempted surveys appearance at survey table");
            
            surveyTable.verifySurveyTableNotAttemptedAppearance(email, name, respondentType, surveyType, mobileNumber, provider, department,
                    verificationText1, verificationText2, dashboard);
            break;
        case "SURVEY_TABLE__DRAGGABLE_FUNCTIONALITY_892":
            Reporter.log("verify draggable functionality in survey table screen");
            surveyTable.verifyDraggableFunctionality(verificationText1);
            break;                      
        case "SURVEY_TABLE__QUESTIONS_ASKED_FUNCTIONALITY_893":
            Reporter.log("verify questions asked functionality in survey table");
            surveyTable.verifyQuestionsAskedFunctionality(verificationText1);
            break;
        case "SURVEY_TABLE__COMMENTS_FUNCTIONALITY_894":
            Reporter.log("verify comments functionality in survey table");
            surveyTable.verifyCommentsFunctionality(verificationText1);
            break;
        case "SURVEY_TABLE__VIEW_FUNCTIONALITY_895":
            Reporter.log("verify view functionality under survey report field");
            surveyTable.verifyViewFunctionality(verificationText1, verificationText2);
            break;
            
                
        }
			
				
		}
	
		
	}



