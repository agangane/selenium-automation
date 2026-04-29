package com.forteholdings.turncloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.forteholdings.turncloud.base.TestComponents.BasePage;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.Demographics;
import com.forteholdings.turncloud.pageobject.HomePage;
import com.forteholdings.turncloud.pageobject.MultipleDemographics;
import com.forteholdings.turncloud.pageobject.PatientLookupGrid;

public class DemographicTest extends BasePage {

	private HomePage home;

	// ================== SETUP ==================
	// Login is already handled in BasePage

	@BeforeMethod
	public void setup() {
		home = new HomePage(driver);
	}

	// ================== TEST CASES ==================

	/**
	 * Update demographics for existing patient
	 */
	@Test
	public void updatePatientDetails() throws IOException, InterruptedException {

		selectPatientFromGrid("patientgrid.json");
		updatePatient("updatepatient.json");
	}

	/**
	 * Create a new patient
	 */
	// @Test
	public void saveNewPatient() throws IOException, InterruptedException, TimeoutException {

		createNewPatient("addPatient.json");
	}

	/**
	 * Create multiple patients
	 */
	// @Test
	public void addMultiplePatients() throws IOException, InterruptedException {

		home.addPatientButton();
		home.addPatientButton();

		List<HashMap<String, Object>> multipleDataList = DataReader.getjsondatamap("multiplePatient.json");

		MultipleDemographics demographic = new MultipleDemographics(driver);

		demographic.addPatient(multipleDataList);
	}

	// ================== HELPER METHODS ==================

	private void createNewPatient(String dataFile) throws IOException, InterruptedException, TimeoutException {

		home.addPatientButton();

		List<HashMap<String, Object>> demoDataList = DataReader.getjsondatamap(dataFile);

		Demographics demographics = new Demographics(driver);

		demographics.isDemoPageDisplayed();
		demographics.addPatient(demoDataList);
	}

	private void selectPatientFromGrid(String dataFile) throws IOException, InterruptedException {

		List<HashMap<String, Object>> patientGrid = DataReader.getjsondatamap(dataFile);

		String accountNo = patientGrid.get(0).get("Account").toString();

		PatientLookupGrid lookupGrid = new PatientLookupGrid(driver);

		lookupGrid.selectPatient(accountNo);
		lookupGrid.waitForPatientTabVisibility();
		lookupGrid.clickDemographicsTab();
		lookupGrid.waitForDemographicsPage();
	}

	private void updatePatient(String dataFile) throws IOException, InterruptedException {

		List<HashMap<String, Object>> updatePatientData = DataReader.getjsondatamap(dataFile);

		Demographics demographics = new Demographics(driver);

		demographics.addPatient(updatePatientData);
	}
}
