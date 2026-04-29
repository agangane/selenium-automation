package com.forteholdings.turncloud.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.forteholdings.turncloud.base.TestComponents.BasePage;
import com.forteholdings.turncloud.base.TestComponents.ListenersPage;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.Demographics;
import com.forteholdings.turncloud.pageobject.HomePage;
import com.forteholdings.turncloud.pageobject.Insurance;

@Listeners(ListenersPage.class)
public class InsuranceTest extends BasePage {

	private HomePage home;
	Insurance insurance;

	@BeforeClass
	public void setUpClass() throws IOException, InterruptedException {
		home = new HomePage(driver);
		insurance = new Insurance(driver);
	}

//	@BeforeMethod
	public void setUpTest() throws IOException, InterruptedException, TimeoutException {
		addNewPatient("addPatient.json");
		home.addInsuranceTab();

	}

	@Test
	public void saveNewInsurance() throws InterruptedException, IOException, TimeoutException {

		// test.info("Insurance Test");
		addNewPatient("addPatient.json");
		addInsuranceDetails("addInsurance.json");
		// test.pass("Test case passed");
		// extent.flush();
//		Assert.assertTrue(false, "Test Failed: Products in the cart do NOT match the expected list!");
		// System.out.println("Test Passed: Products in the cart successfully
		// verified!");
	}

	// @Test
	public void saveMultipleInsurance() throws IOException, InterruptedException, TimeoutException {
		addNewPatient("addPatient.json");
		addInsuranceDetails("addInsurance.json");
	}

//	@Test
	public void updateExistingInsurance() throws IOException, InterruptedException, TimeoutException {

		addNewPatient("addPatient.json");
		home.addInsuranceTab();
		addInsuranceDetails("addInsurance.json");
		// Read which record to update
		List<HashMap<String, Object>> searchListMap = DataReader.getjsondatamap("insuranceList.json");
		// Read new details for update
		List<HashMap<String, Object>> updateListMap = DataReader.getjsondatamap("updateInsurance.json");
		// Update details
		Insurance insurancePage = new Insurance(driver);
		insurancePage.updateInsuranceDetails(searchListMap, updateListMap);
	}

	// @Test
	public void deleteExistingInsurance() throws IOException, InterruptedException, TimeoutException {

		addNewPatient("addPatient.json");
		home.addInsuranceTab();
		addInsuranceDetails("addInsurance.json");

		// Read new details for delete
		List<HashMap<String, Object>> searchListMap = DataReader.getjsondatamap("insuranceList.json");
		// Delete record
		Insurance insurancePage = new Insurance(driver);
		insurancePage.deleteInsuranceDetails(searchListMap);

	}
	// ------------------ Helper Methods ------------------

	private void addNewPatient(String dataFile) throws IOException, InterruptedException, TimeoutException {
		home.addPatientButton();
		List<HashMap<String, Object>> demoDataList = DataReader.getjsondatamap(dataFile);
		Demographics demographic = new Demographics(driver);
		demographic.addPatient(demoDataList);
	}

	private void addInsuranceDetails(String dataFile) throws IOException, InterruptedException {
		List<HashMap<String, Object>> insuranceList = DataReader.getjsondatamap(dataFile);
		Insurance insurance = new Insurance(driver);
		for (int i = 0; i < insuranceList.size(); i++) {
			if (i == 0) {
				home.addInsuranceTab();
			} else {
				home.clickAddInsuranceButtonAndRow();
			}
			List<HashMap<String, Object>> list = new ArrayList<>();
			list.add(insuranceList.get(i));
			insurance.addInsuranceDetails(list);

		}
	}
}
