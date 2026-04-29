package com.forteholdings.turncloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.forteholdings.turncloud.base.TestComponents.BasePage;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.Demographics;
import com.forteholdings.turncloud.pageobject.HomePage;
import com.forteholdings.turncloud.pageobject.PatientHistory;

public class PatientHistoryTest extends BasePage {

	List<HashMap<String, Object>> logindata;
	HomePage home;
	PatientHistory history;

	@BeforeClass
	public void classSetup() throws IOException, InterruptedException {
		// ✅ BasePage handles login + popup close
		home = new HomePage(driver); // initialize homepage once
		history = new PatientHistory(driver);
	}

	@BeforeMethod
	public void loginSetup() throws IOException, InterruptedException {
		// ✅ Each test needs a new patient before working with history
		addNewpatient("addPatient.json");

		// Navigate to Patient History tab
		home.addPatientHistoryTab();
		history.isPagedisplayed();

	}
	// ================== TEST METHODS ==================

	// @Test
	public void clickonPatientHistoryTab() {
		// Just verify tab is displayed
		history.isPagedisplayed();
	}

//	@Test
	public void printLabelPatientHistoryTab() {
		// Verify tab content or label
		history.isPagedisplayed();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void addpatienthistory() throws IOException, InterruptedException {
		// Load JSON data
		List<HashMap<String, Object>> allergieslist = DataReader.getjsondatamap("allergies.json");

		// Add allergies

		// Collect allergy names for logging or validation
		List<String> allergies = allergieslist.stream().map(a -> a.get("Allergy").toString())
				.collect(Collectors.toList());

		List<HashMap<String, Object>> surgerieslist = DataReader.getjsondatamap("surgeries.json");

		// Collect surgery names for logging or validation (if present in JSON)

		List<String> surgeries = surgerieslist.stream()// ensure key exists
				.map(a -> a.get("Surgery").toString()).collect(Collectors.toList());

		List<HashMap<String, Object>> medicalhistorylist = DataReader.getjsondatamap("medicalhistory.json");

		// Collect medical history names for logging or validation (if present in Json)

		List<String> medicalhistory = medicalhistorylist.stream().map(a -> a.get("Medical History").toString())
				.collect(Collectors.toList());

		history.addAllergies(allergieslist, surgerieslist, medicalhistorylist);

		// Optional: print for debug
		System.out.println("Allergies: " + allergies);
		System.out.println("Surgeries: " + surgeries);
		System.out.println("MedicalHistory: " + medicalhistory);
	}

//	@Test

	public void updatepatienthistorydetails() throws IOException, InterruptedException {

		List<HashMap<String, Object>> allergieslist = DataReader.getjsondatamap("allergies.json");
		List<String> allergies = allergieslist.stream().map(a -> a.get("Allergy").toString().trim())
				.collect(Collectors.toList());

		// Step 2: Load updated allergies from JSON
		List<HashMap<String, Object>> updateallergieslist = DataReader.getjsondatamap("updateallergies.json");
		List<String> updateAllergies = updateallergieslist.stream().map(a -> a.get("Allergy").toString().trim())
				.collect(Collectors.toList());

		// ✅ Step 3: Add allergies and alsoe update
		history.updatePatientHistory(allergieslist, updateallergieslist);

		// ✅ Step 5: Logging/Validation
		System.out.println("Added Allergies   : " + String.join(", ", allergies));
		System.out.println("Updated Allergies : " + String.join(", ", updateAllergies));

	}

	// ================== HELPER METHODS ==================
	private void addNewpatient(String dataFile) throws IOException, InterruptedException {
		home.addPatientButton();
		List<HashMap<String, Object>> demoDataList = DataReader.getjsondatamap(dataFile);
		Demographics demographic = new Demographics(driver);
		demographic.addPatient(demoDataList);
	}
}
