package com.forteholdings.turncloud.pageobject;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.forteholdings.turncloud.commoncode.CommonCode;

public class Visitscreen extends CommonCode {

	private WebDriver driver;
	private WebDriverWait wait;

	public Visitscreen(WebDriver driver) {

		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	// ==================VisitTab========================

	@FindBy(css = "#PatientVisitEHR .k-panelbar-item-text")
	private WebElement visitTabName;

	// ==============add visit button====================

	@FindBy(xpath = "//span[normalize-space()='Add New Visit']")
	private WebElement addVisitButton;

	// =================Visit Screen Maximize====================

	@FindBy(xpath = "//a[@aria-label='window-Maximize']")
	private WebElement maximizeButton;

	// ===============Visit Header Section=========================

	// ***************Doctors*************************
	@FindBy(css = "#EHRVisitHeaderDiv [aria-owns='EHRVisitDoctorDropDown_listbox'] >button")
	private WebElement doctorbutton;

	private By doctorList = By
			.xpath("//div[@id='EHRVisitDoctorDropDown-list']//ul[@id='EHRVisitDoctorDropDown_listbox']//li//span");

	// ****************Type********************************
	@FindBy(css = "#EHRVisitHeaderDiv [aria-owns='EHRVisitTypeDropDown_listbox'] > button")
	private WebElement type;

	@FindBy(xpath = "//div[@id='EHRVisitTypeDropDown-list']//li//span")
	private WebElement typeOptions;

	// *****************Status**********************************

	@FindBy(css = "#EHRVisitHeaderDiv [aria-owns='EHRVisitStatusDropDown_listbox'] > button")
	private WebElement statusbutton;

//	@FindBy(css = "#EHRVisitStatusDropDown_listbox li span")
	private WebElement statusOptionss;

	private By statusOptions = By.cssSelector("#EHRVisitStatusDropDown_listbox li span");

	// ***************Patient Complaint***********

	@FindBy(xpath = "(//input[@data-bind='value: visitInitialComplaint'])[last()]")
	private WebElement patientComplaint;

	// *************************Update**************************************
	@FindBy(xpath = "//button[.//span[normalize-space()='Update']]")
	private WebElement updateButton;

	@FindBy(xpath = "//button[.//span[normalize-space()='Cancel']]")
	private WebElement cancelButton;

	// ==============Vital=======================

	@FindBy(css = "#EHRTabStrip-tab-1")
	private WebElement vitalTab;

	// ===========Vitals==============================

	@FindBy(xpath = "(//label[normalize-space()='Height']/following::input[@role='spinbutton' and not(contains(@style,'display:none'))])[1]")
	private WebElement height_ft;

	@FindBy(xpath = "(//label[normalize-space()='Weight']/following::input[@role='spinbutton' and not(contains(@style,'display:none'))])[1]")
	private WebElement weight;

	@FindBy(xpath = "(//label[normalize-space()='Temperature']/following::input[@role='spinbutton' and not(contains(@style,'display:none'))])[1]")
	private WebElement temperature;

	@FindBy(xpath = "(//label[normalize-space()='Heart Rate']/following::input[@role='spinbutton' and not(contains(@style,'display:none'))])[1]")
	private WebElement heartRate;

	@FindBy(xpath = "(//label[normalize-space()='Respiration']/following::input[@role='spinbutton' and not(contains(@style,'display:none'))])[1]")
	private WebElement respiration;

	@FindBy(xpath = "(//label[normalize-space()='Oxygen Level']/following::input[@role='spinbutton' and not(contains(@style,'display:none'))])[1]")
	private WebElement oxygenLevel;

	@FindBy(xpath = "(//label[normalize-space()='Oxygen Concentration']/following::input[@role='spinbutton' and not(contains(@style,'display:none'))])[1]")
	private WebElement oxygenConcentration;

	@FindBy(xpath = "(//label[normalize-space()='Potassium']/following::input[@role='spinbutton' and not(contains(@style,'display:none'))])[1]")
	private WebElement potassium;

	// ==============Patient History=======================

	// ******Allergies****************
	@FindBy(css = "#EHRTabStrip-tab-2")
	private WebElement patientHistoryTab;

	@FindBy(css = "div[id='EHRHistoryAllergiesGrid'] label[class='ms-2']")
	private WebElement allergyText;

	@FindBy(css = "#EHRHistoryAllergiesGrid button.k-grid-add")
	private WebElement addAllergyButton;

	@FindBy(css = "#EHRHistoryAllergiesGrid span.k-picker.k-dropdownlist.k-picker-solid button")
	private WebElement allergyDropdownButton;

	@FindBy(css = "div.k-list-md li span")
	List<WebElement> allergyList;

	// ******Surgeries************

	@FindBy(css = "#EHRHistorySurgeriesGrid button")
	private WebElement surgery;

	@FindBy(css = "#EHRHistorySurgeriesGrid label")
	private WebElement surgeryName;

	@FindBy(css = "#EHRHistorySurgeriesGrid span[role='listbox'] button")
	private WebElement surgeryDropdownButton;

	@FindBy(css = "div.k-list-md li span")
	List<WebElement> surgeryList;

	// ********Medical history************

	@FindBy(css = "#EHRHistoryMedicalHistoryGrid button")
	private WebElement addMedicalhistorybutton;

	@FindBy(css = "#EHRHistoryMedicalHistoryGrid label")
	private WebElement medicalhistoryName;

	@FindBy(css = "#EHRHistoryMedicalHistoryGrid span[role='listbox'] button")
	private WebElement medicalhistoryDropdownButton;

	@FindBy(css = "div.k-list-md li span")
	List<WebElement> medicalList;

	// ***************Complaint Screen*******************

	@FindBy(css = "#EHRTabStrip-tab-3")
	private WebElement complaintTab;

	// ====================Visit============================

	public void visitsPagedisyed() {
		System.out.println("Visit Tab Name: " + visitTabName.getText().trim());

	}

	public void clickAddVisit() throws InterruptedException {
		Thread.sleep(1000);
		waitForVisibility(addVisitButton);
		addVisitButton.click();
		Thread.sleep(1000);
		waitForVisibility(maximizeButton);
		maximizeButton.click();

	}

	public void visitHeader(List<HashMap<String, Object>> listmap) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(1000);
		selectVisitHeaderdropdowns(listmap);
		Thread.sleep(500);
		enterVitalsRecord(listmap);

	}

	public void selectVisitHeaderdropdowns(List<HashMap<String, Object>> listmap) throws InterruptedException {
		Thread.sleep(1000);
		waitForClickable(doctorbutton);
		doctorbutton.click();
		waitForVisibility(doctorList);
		selectdropdowns(doctorList, listmap.get(0).get("Doctors").toString());
		waitForClickable(type);
		type.click();
		waitForVisibility(typeOptions);
		selectdropdown(typeOptions, listmap.get(0).get("Type").toString());
		Thread.sleep(500);
		waitForClickable(statusbutton);
		statusbutton.click();
		Thread.sleep(500);
		waitForVisibility(statusOptions);
		selectdropdowns(statusOptions, listmap.get(0).get("Status").toString());

		Thread.sleep(500);
		clearAndType(patientComplaint, listmap.get(0).get("Patient Stated Complaint").toString()); //
		// updateButton.click();

	}

	public void enterVitalsRecord(List<HashMap<String, Object>> listmap) throws InterruptedException {

		Thread.sleep(1000);

		setKendoComposite(height_ft, listmap.get(0).get("Height_ft").toString(),
				listmap.get(0).get("Height_inch").toString());
		setKendoComposite(weight, listmap.get(0).get("Weight").toString());
		setKendoComposite(temperature, listmap.get(0).get("Temperature").toString());
		setKendoComposite(heartRate, listmap.get(0).get("HeartRate").toString());
		setKendoComposite(respiration, listmap.get(0).get("Respiration").toString());
		setKendoComposite(oxygenLevel, listmap.get(0).get("Oxygen").toString());
		setKendoComposite(oxygenConcentration, listmap.get(0).get("Oxygen Concentration").toString());
		setKendoComposite(potassium, listmap.get(0).get("Potassium").toString());
	}

	public void enterPatientHistory(List<HashMap<String, Object>> listmap) throws InterruptedException {

		patientHistoryTab.click();
		waitForVisibility(allergyText);
		Thread.sleep(1000);
		addPatientHistoryAllergy((List<HashMap<String, Object>>) listmap.get(0).get("Allergies"));
		addPatientHistorySurgery((List<HashMap<String, Object>>) listmap.get(0).get("Surgeries"));
		addPatientHistoryMedicalHistory((List<HashMap<String, Object>>) listmap.get(0).get("Medical History"));

	}

	public void addPatientHistoryAllergy(List<HashMap<String, Object>> allergies) {

		allergies.forEach(allergy -> {

			try {
				String allergyName = allergy.get("Allergy").toString();
				// String startDate = allergy.get("Start Date").toString();
				// String endDate = allergy.get("End Date").toString();

				addAllergyButton.click();
				Thread.sleep(500);

				allergyDropdownButton.click();
				Thread.sleep(500);

				allergyList.stream().filter(a -> a.getText().equalsIgnoreCase(allergyName)).findFirst()
						.ifPresent(WebElement::click);

				Thread.sleep(500); // small wait before next iteration

				/*
				 * startdate.click(); Thread.sleep(1000); selectDOB(startdateinput,
				 * startdatevalue);
				 *
				 * Thread.sleep(500); // small wait before next iteration
				 *
				 * enddate.click(); Thread.sleep(1000); selectDOB(enddateinput, enddatevalue);
				 */
				Thread.sleep(500);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

	}

	public void addPatientHistorySurgery(List<HashMap<String, Object>> surgeries) {

		surgeries.forEach(surgey -> {

			String surgeryName = surgey.get("Surgery").toString();

			try {
				Thread.sleep(500);

				surgery.click();
				Thread.sleep(500);

				surgeryDropdownButton.click();
				Thread.sleep(500);

				selectdropdownList(surgeryList, surgeryName);

				/*
				 * surgeryList.stream().filter(s ->
				 * s.getText().equalsIgnoreCase(surgeryName)).findFirst()
				 * .ifPresent(WebElement::click);
				 *
				 * Thread.sleep(500);
				 */

				/*
				 * WebElement selected = surgeryList.stream().filter(WebElement::isDisplayed)
				 * .filter(a ->
				 * a.getText().trim().equalsIgnoreCase(surgeryName.trim())).findFirst()
				 * .orElseThrow(() -> new RuntimeException("Surgey not found:" + surgeryName));
				 *
				 * selected.click();
				 */

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	public void addPatientHistoryMedicalHistory(List<HashMap<String, Object>> medicalHistorylist) {

		medicalHistorylist.forEach(medical -> {

			String medicalName = medical.get("medicalhistory").toString();

			try {
				scrollToElement(addMedicalhistorybutton, true);
				Thread.sleep(1000);

				addMedicalhistorybutton.click();
				Thread.sleep(500);
				medicalhistoryDropdownButton.click();
				Thread.sleep(500);

				selectdropdownList(medicalList, medicalName);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	public void addComplaint() {

		complaintTab.click();
	}

	public void enterComplaints(List<HashMap<String, Object>> complaints) {

	}
}
