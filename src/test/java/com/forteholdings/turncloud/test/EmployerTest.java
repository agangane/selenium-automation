package com.forteholdings.turncloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.forteholdings.turncloud.base.TestComponents.BasePage;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.Employer;
import com.forteholdings.turncloud.pageobject.HomePage;
import com.forteholdings.turncloud.pageobject.PatientLookupGrid;

public class EmployerTest extends BasePage {

	private HomePage home;

	// ================== SETUP ==================
	// Login is already handled in BasePage

	@BeforeMethod
	public void setup() {
		home = new HomePage(driver);
	}

	// ================== TEST CASES ==================
	@Test
	public void employerPageDisplayedCheck() throws IOException, TimeoutException, InterruptedException {
		// addNewPatient("addPatient.json");
		selectPatientFromGrid("patientgrid.json");
		openEmployerAndAddDetails("employer.json");
	}

	// ------------------ Helper Methods ------------------

	// ********************Add New patient**************************
	/*
	 * private void addNewPatient(String dataFile) throws IOException,
	 * InterruptedException, TimeoutException { home.addPatientButton();
	 *
	 * List<HashMap<String, Object>> demoDataList =
	 * DataReader.getjsondatamap(dataFile); Demographics demographic = new
	 * Demographics(driver); demographic.addPatient(demoDataList); }
	 */

	// ******************Open Exisitng
	// Patient***************************************

	private void selectPatientFromGrid(String dataFile) throws IOException, InterruptedException {

		List<HashMap<String, Object>> patientGrid = DataReader.getjsondatamap(dataFile);

		String accountNo = patientGrid.get(0).get("Account").toString();

		PatientLookupGrid lookupGrid = new PatientLookupGrid(driver);

		lookupGrid.selectPatient(accountNo);
		lookupGrid.waitForPatientTabVisibility();
		// lookupGrid.clickDemographicsTab();
		lookupGrid.waitForDemographicsPage();
	}

	private void openEmployerAndAddDetails(String dataFile) throws IOException, InterruptedException, TimeoutException {
		home.addEmployerTab();

		Employer employer = new Employer(driver);
		List<HashMap<String, Object>> employerDataList = DataReader.getjsondatamap(dataFile);

		employer.isEmployerDisplayed();
		employer.verifyEmployerTabText();
		employer.addEmployerDetails(employerDataList);
	}
}
