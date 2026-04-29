package com.forteholdings.turncloud.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.forteholdings.turncloud.base.TestComponents.BasePage;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.Cases;
import com.forteholdings.turncloud.pageobject.Demographics;
import com.forteholdings.turncloud.pageobject.HomePage;
import com.forteholdings.turncloud.pageobject.Insurance;
import com.forteholdings.turncloud.pageobject.PatientLookupGrid;

public class CasesTest extends BasePage {

	private HomePage home;
	private Cases cases;
	Insurance insurance;

	// ================== SETUP METHODS ==================

	@BeforeClass
	public void setUpClass() throws IOException, InterruptedException {
		home = new HomePage(driver);
		cases = new Cases(driver);
		insurance = new Insurance(driver);
	}

	@BeforeMethod
	public void setUpTest() throws IOException, InterruptedException {
		// addNewPatient("addPatient.json");
		selectPatientFromGrid("patientgrid.json");
		home.addCasesTab();

		// home.addInsuranceTab();
		// addInsuranceDetails("addInsurance.json");

	}

	// ================== TEST METHODS ==================

	@Test

	public void testClickOnCasesTabWithexistingpatient() throws IOException, InterruptedException {

		performCaseSelectionFlows("exisitngpatientcase.json", "casesgrid.json");

	}

//	@Test

	public void testClickOnCaseTabWithNewPatient() throws IOException, InterruptedException {

		performCaseSelectionFlows("cases.json", "casesgrid.json");

	}

//	@Test

	public void testclickonCasesTabandInsurance() throws IOException, InterruptedException {

		performCaseSelectionFlows("cases.json", "casesgrid.json");

	}

//	@Test
	public void saveMultipleInsurance() throws IOException, InterruptedException, TimeoutException {

		// addCaseDetails("multicases.json", "casesgrid.json");

		// ********chatgpt************

		// addCaseDetailsSafely("multicases.json", "casesgrid.json");

		addMultipleCasesWithSleep("multicases.json", "casesgrid.json");

	}

	// ================== HELPER METHODS ==================

	// ********************Add New patient**********************

	private void addNewPatient(String dataFile) throws IOException, InterruptedException {
		home.addPatientButton();
		List<HashMap<String, Object>> demoDataList = DataReader.getjsondatamap(dataFile);
		Demographics demographics = new Demographics(driver);
		demographics.addPatient(demoDataList);
	}

	// **********************Open Exiting Patient*************************

	private void selectPatientFromGrid(String datafile) throws IOException, InterruptedException {

		List<HashMap<String, Object>> patientGrid = DataReader.getjsondatamap(datafile);
		String accountNo = patientGrid.get(0).get("Account").toString();
		PatientLookupGrid lookupGrid = new PatientLookupGrid(driver);
		lookupGrid.selectPatient(accountNo);
		lookupGrid.waitForPatientTabVisibility();
		lookupGrid.waitForDemographicsPage();

	}

	private void performCaseSelectionFlows(String dropdownDataFile, String gridDataFile)
			throws IOException, InterruptedException {
		// Fill dropdowns
		List<HashMap<String, Object>> dropdownData = DataReader.getjsondatamap(dropdownDataFile);

		cases.selectExistingCases(dropdownData.get(0).get("Doctor").toString());

		// Select case(s) from grid
		List<HashMap<String, Object>> gridData = DataReader.getjsondatamap(gridDataFile);

		cases.selectFromDropdown(gridData);

		// cases.selectCaseSafelyByDoctor(dropdownData.get(0).get("Doctor").toString());

	}

	private void addInsuranceDetails(String dataFile) throws IOException, InterruptedException {

		List<HashMap<String, Object>> insuranceList = DataReader.getjsondatamap(dataFile);

		insurance.addInsuranceDetails(insuranceList);
	}

	private void addCaseDetails(String dataFile, String gridDataFile) throws IOException, InterruptedException {
		List<HashMap<String, Object>> caseList = DataReader.getjsondatamap(dataFile);
		Cases cases = new Cases(driver);
		for (int i = 0; i < caseList.size(); i++) {
			if (i == 0) {
				home.addCasesTab();
			} else {
				home.clickAddCasesButtonAndRow();

			}
			List<HashMap<String, Object>> list = new ArrayList<>();
			list.add(caseList.get(i));
			// insurance.addInsuranceDetails(list);
			cases.selectFromDropdown(list);

			// Select case(s) from grid
			List<HashMap<String, Object>> gridData = DataReader.getjsondatamap(gridDataFile);
			cases.selectFromDropdown(gridData);
		}
	}

	// **************************chatgpt code*****************************

	private void addCaseDetailsSafely(String multiCaseFile, String gridDataFile)
			throws IOException, InterruptedException {

		// 🔹 Data ONLY for looping (multicases.json)
		List<HashMap<String, Object>> multiCases = DataReader.getjsondatamap(multiCaseFile);

		// 🔹 Data ONLY for filling form (casesgrid.json)
		List<HashMap<String, Object>> caseFormData = DataReader.getjsondatamap(gridDataFile);

		for (int i = 0; i < multiCases.size(); i++) {

			if (i == 0) {
				home.addCasesTab();
			} else {
				home.clickAddCasesButtonAndRow();
			}

			// 🔹 Select correct case row using doctor name
			String doctorName = multiCases.get(i).get("Doctor").toString();

			cases.selectCaseSafelyByDoctor(doctorName);

			cases.waitForCaseFormReady();

			// 🔹 Fill case form using FULL data
			cases.selectFromDropdown(caseFormData);

			// 🔹 Save explicitly
			cases.clickSaveButton();
		}

	}

	private void addMultipleCasesWithSleep(String multiCaseFile, String gridDataFile)
			throws IOException, InterruptedException {

		List<HashMap<String, Object>> multiCases = DataReader.getjsondatamap(multiCaseFile);

		List<HashMap<String, Object>> caseFormData = DataReader.getjsondatamap(gridDataFile);

		for (int i = 0; i < multiCases.size(); i++) {

			if (i == 0) {
				// default case already exists
				home.addCasesTab();
				Thread.sleep(4000);
			} else {
				// ADD NEW CASE
				home.clickAddNewCase();
				Thread.sleep(4000);
			}

			// 🔑 THIS WAS MISSING

			cases.waitForCaseFormReady();

			// now safe to fill dropdowns
			cases.selectFromDropdown(caseFormData);

			cases.clickSaveButton();
			Thread.sleep(5000); // save + grid refresh
		}
	}

}
