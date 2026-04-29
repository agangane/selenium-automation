package com.forteholdings.turncloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.forteholdings.turncloud.base.TestComponents.BasePage;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.Demographics;
import com.forteholdings.turncloud.pageobject.PatientLookupGrid;

public class PatientGridLookupTest extends BasePage {

	@Test
	public void lookupOrCreatePatient() throws IOException, InterruptedException {

		// 🔹 LOGIN IS ALREADY DONE IN BasePage
		// 🔹 Do NOT add login here

		// ---------- TEST DATA ----------
		List<HashMap<String, Object>> patientGrid = DataReader.getjsondatamap("patientgrid.json");

		String accountNo = patientGrid.get(0).get("Account").toString();

		PatientLookupGrid lookupGrid = new PatientLookupGrid(driver);

		// App is slow → allow grid to load
		Thread.sleep(4000);

		// ---------- DECISION ----------
		if (lookupGrid.isPatientPresent(accountNo)) {

			System.out.println("✅ Existing patient found → " + accountNo);
			lookupGrid.selectPatientSafely(accountNo);

		} else {

			System.out.println("➕ Patient not found, creating new patient");

			home.addPatientButton();

			Demographics demographics = new Demographics(driver);
			List<HashMap<String, Object>> newPatientData = DataReader.getjsondatamap("addPatient.json");

			demographics.addPatient(newPatientData);
		}

		// ---------- VERIFY ----------

		lookupGrid.clickDemographicsTab();
		System.out.println("🎯 Patient ready for next test steps");
	}
}
