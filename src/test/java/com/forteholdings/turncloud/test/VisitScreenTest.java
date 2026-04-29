package com.forteholdings.turncloud.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.forteholdings.turncloud.base.TestComponents.BasePage;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.Cases;
import com.forteholdings.turncloud.pageobject.Demographics;
import com.forteholdings.turncloud.pageobject.HomePage;
import com.forteholdings.turncloud.pageobject.PatientLookupGrid;
import com.forteholdings.turncloud.pageobject.Visitscreen;

public class VisitScreenTest extends BasePage {

	private HomePage home;
	private Cases cases;
	private Visitscreen visit;

	@BeforeClass
	public void setUpClass() throws IOException, InterruptedException {
		home = new HomePage(driver);
		cases = new Cases(driver);
		visit = new Visitscreen(driver);

	}

	@BeforeMethod
	public void setUpTest() throws IOException, InterruptedException {
		// addNewPatient("addPatient.json");
		selectexisitnpatient("patientgrid.json");
		// home.addCasesTab();
		// home.addInsuranceTab();
		// addInsuranceDetails("addInsurance.json");

	}

//	@Test

	public void testClickOnCasesTab() throws IOException, InterruptedException {

		performCaseSelectionFlows("cases.json", "casesgrid.json");
		home.clickAddVisitTab();

	}

	@Test

	public void openExisitngPatientClickVisit() throws IOException, InterruptedException {
		// performCaseSelectionFlows("cases.json", "casesgrid.json");
		home.clickAddVisitTab();
		visit.clickAddVisit();
		selectVisit("addVisit.json");

	}

	// ================== HELPER METHODS ==================

	// *********************Visit*********************

	private void selectVisit(String dataFile) throws IOException, InterruptedException {

		List<HashMap<String, Object>> visitPatient = DataReader.getjsondatamap(dataFile);
		Visitscreen visit = new Visitscreen(driver);
		// visit.selectVisitHeaderdropdowns(visitPatient);
		// visit.enterVitalsRecord(visitPatient);
		// visit.enterPatientHistory(visitPatient);
		visit.addComplaint();
	}

	// ***********************Add New Patient*************************
	private void addNewPatient(String dataFile) throws IOException, InterruptedException {
		home.addPatientButton();
		List<HashMap<String, Object>> demoDataList = DataReader.getjsondatamap(dataFile);
		Demographics demographics = new Demographics(driver);
		demographics.addPatient(demoDataList);
	}

	// *****************Open existing patiehnt************************
	private void selectexisitnpatient(String dataFile) throws IOException, InterruptedException {

		List<HashMap<String, Object>> patientGrid = DataReader.getjsondatamap(dataFile);
		String accountNumber = patientGrid.get(0).get("Account").toString();
		PatientLookupGrid lookupGrid = new PatientLookupGrid(driver);
		lookupGrid.selectPatient(accountNumber);
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
}
