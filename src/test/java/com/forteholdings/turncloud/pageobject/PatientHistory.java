package com.forteholdings.turncloud.pageobject;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.forteholdings.turncloud.commoncode.CommonCode;

public class PatientHistory extends CommonCode {

	private static final int Hashmap = 0;

	WebDriver driver;

	public PatientHistory(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

//=====================Dropdownlist===================

	@FindBy(xpath = "//div[@class='k-list k-list-md']//ul//li//span")
	private List<WebElement> dropdownOptions;

//=============== Patient History Tab Name=================

	@FindBy(css = "#PatientHistory .k-panelbar-item-text")
	WebElement patienthistorytext;

//================= Allergies ================================

	@FindBy(xpath = "//span[normalize-space()='Add New Allergy']")
	WebElement allergiesbutton;

	@FindBy(css = "#PatientHistoryAllergiesGrid_active_cell")
	WebElement gridallergies;

	@FindBy(css = "#PatientHistoryAllergiesGrid span.k-picker.k-dropdownlist.k-picker-solid")
	WebElement allergiesdropdownbutton;

	@FindBy(xpath = "//ul[contains(@class,'k-list-ul')]//li//span")
	WebElement allergiesdropdownbuttons;

//=====================Surgeries================================
	@FindBy(xpath = "//span[normalize-space()='Add New Surgery']")
	WebElement surgeriesbutton;

	@FindBy(css = "	#PatientHistorySurgeriresGrid span.k-picker.k-dropdownlist.k-picker-solid")
	WebElement surgeriesdropdownbutton;

//====================Medical History============================

	@FindBy(xpath = "//span[normalize-space()='Add New Medical History']")
	WebElement medicalhistorybutton;

	@FindBy(css = "	#PatientHistoryMedicalHistoryGrid span.k-picker.k-dropdownlist.k-picker-solid")
	WebElement medicalhistorydropdownbutton;

//==================Allergies update grid===========================

	@FindBy(xpath = "//tr[contains(@class, 'k-master-row k-grid-edit-row')]//td[2]")
	private List<WebElement> allergiesgrid;

//====================Start Date====================================

	@FindBy(xpath = "//tr[contains(@class, 'k-master-row k-grid-edit-row')]//td[3]")
	WebElement startdate;

	@FindBy(xpath = "//tr[contains(@class, 'k-master-row k-grid-edit-row')]//td[3]//input")
	WebElement startdateinput;

	@FindBy(xpath = "//tr[contains(@class, 'k-master-row k-grid-edit-row')]//td[4]")
	WebElement enddate;

	@FindBy(xpath = "//tr[contains(@class, 'k-master-row k-grid-edit-row')]//td[4]//input")
	WebElement enddateinput;

//============= Page displayed method========================

	// Main entry: Pass JSON list here

	public void updatePatientHistory(List<HashMap<String, Object>> allergieslist,

			List<HashMap<String, Object>> updateallergieslist) throws InterruptedException {
		selectAllergies(allergieslist);
		selectUpdateallergies(updateallergieslist);
		clickSaveButton();

	}

	public void selectUpdateallergies(List<HashMap<String, Object>> updateallergies) throws InterruptedException {

		Thread.sleep(1000);

		// Iterate with index using IntStream
		IntStream.range(0, updateallergies.size()).forEach(i -> {
			HashMap<String, Object> updateallergy = updateallergies.get(i);

			if (updateallergy.get("Allergy") == null) {
				return; // skip if no allergy
			}

			String updateAllergyName = updateallergy.get("Allergy").toString();

			try {
				Thread.sleep(500);

				if (i > 0) {

					allergiesgrid.get(i).click();
				}

				allergiesdropdownbutton.click();
				Thread.sleep(500);

				dropdownOptions.stream().filter(a -> a.getText().equalsIgnoreCase(updateAllergyName)).findFirst()
						.ifPresent(WebElement::click);

				Thread.sleep(500);

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});
	}

	public void isPagedisplayed() {

		String PatientHistory = patienthistorytext.getText().trim();
		System.out.println(PatientHistory);

	}

	public void addAllergies(List<HashMap<String, Object>> allergieslist, List<HashMap<String, Object>> surgerieslist,
			List<HashMap<String, Object>> medicalhistorylist) throws InterruptedException {

		selectAllergies(allergieslist);
		selectSurgeries(surgerieslist);
		selectMedicalHistory(medicalhistorylist);
		scrollToElement(allergiesbutton, false);
		clickSaveButton();
	}

	public void selectAllergies(List<HashMap<String, Object>> allergies) throws InterruptedException {

		allergies.forEach(allergy -> {
			try {
				// extract the allergy name from HashMap
				String allergyName = allergy.get("Allergy").toString();
				String startdatevalue = allergy.get("Start Date").toString();
				String enddatevalue = allergy.get("End Date").toString();

				allergiesbutton.click(); // Step 1: click "Add Allergy"
				Thread.sleep(500);

				allergiesdropdownbutton.click(); // Step 2: open dropdown
				Thread.sleep(500);

				// Step 3: pick allergy by name
				dropdownOptions.stream().filter(a -> a.getText().equalsIgnoreCase(allergyName)).findFirst()
						.ifPresent(WebElement::click);

				Thread.sleep(500); // small wait before next iteration

				startdate.click();
				Thread.sleep(1000);
				selectDOB(startdateinput, startdatevalue);

				Thread.sleep(500); // small wait before next iteration

				enddate.click();
				Thread.sleep(1000);
				selectDOB(enddateinput, enddatevalue);

				Thread.sleep(500);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

	}

	public void selectSurgeries(List<HashMap<String, Object>> surgeries) throws InterruptedException {
		Thread.sleep(1000);

		for (HashMap<String, Object> surgery : surgeries) {
			if (surgery.get("Surgery") == null) {
				continue; // skip if no surgery
			}

			String surgeryName = surgery.get("Surgery").toString();
			String startdatevalue = surgery.get("Start Date").toString();

			surgeriesbutton.click();
			Thread.sleep(500);

			surgeriesdropdownbutton.click();
			Thread.sleep(500);

			dropdownOptions.stream().filter(a -> a.getText().equalsIgnoreCase(surgeryName)).findFirst()
					.ifPresent(WebElement::click);

			Thread.sleep(700);

			startdate.click();
			Thread.sleep(700);
			selectDOB(startdateinput, startdatevalue);
			Thread.sleep(700);

		}

	}

	public void selectMedicalHistory(List<HashMap<String, Object>> medicalhistory) throws InterruptedException {

		scrollToElement(medicalhistorybutton, true);

		Thread.sleep(1000);

		for (HashMap<String, Object> medical : medicalhistory) {

			if (medical.get("Medical History") == null) {
				continue;
			}

			String medicalhistoryname = medical.get("Medical History").toString();
			String startdatevalue = medical.get("Start Date").toString();

			medicalhistorybutton.click();

			medicalhistorydropdownbutton.click();
			Thread.sleep(500);

			dropdownOptions.stream().filter(m -> m.getText().equalsIgnoreCase(medicalhistoryname)).findFirst()
					.ifPresent(WebElement::click);
			Thread.sleep(500);

			startdate.click();
			Thread.sleep(700);
			selectDOB(startdateinput, startdatevalue);
			Thread.sleep(700);

		}

	}

}
