package com.forteholdings.turncloud.pageobject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.forteholdings.turncloud.commoncode.CommonCode;

public class Insurance extends CommonCode {

	WebDriver driver;

	public Insurance(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ======== Insurance Company WebElements =========

	@FindBy(css = "span.k-picker.k-dropdownlist.k-picker-solid.k-picker-md.k-rounded-md.mt-2")
	WebElement insurancedropdown;

	@FindBy(css = "div.k-popup.k-group.k-reset.k-state-border-up")
	WebElement insurancedropdownvalues;

	@FindBy(css = "ul[class=\"k-list-ul\"] li")
	WebElement insurancecompany;

	@FindBy(css = "span.k-picker.k-dropdownlist.k-picker-solid.k-picker-md.k-rounded-md.mt-2  span.k-input-value-text")
	WebElement insurancecompanyname;

	// ====== Policy Detials WebElements ================

	@FindBy(id = "PatientInsurancePolicyNo")
	WebElement policyno;

	@FindBy(id = "PatientInsuranceidnumber")
	WebElement patientinsuranceidnumber;

	@FindBy(id = "PatientInsurancePlanNo")
	WebElement patientinsuranceplanno;

	@FindBy(id = "PatientInsurancePlanProgramName")
	WebElement patientinsuranceplanprogramname;

	@FindBy(xpath = "//label[normalize-space()='Effective Date']/following-sibling::span//input")
	WebElement effectivedate;

	// ==== Insured Webelements=====

	@FindBy(id = "PatientInsuranceNameFirst")
	WebElement patientinsurancenamefirst;

	@FindBy(id = "PatientInsuranceNameMI")
	WebElement patientinsurancemi;

	@FindBy(id = "PatientInsuranceNameLast")
	WebElement patientinsurancenamelast;

	@FindBy(css = "input[data-bind='value: selected.dob']")
	WebElement dob;

	@FindBy(css = "span[aria-owns='PatientInsuranceRelationshipDropDown_listbox'] button")
	WebElement relationship;

	@FindBy(css = "#PatientInsuranceRelationshipDropDown_listbox li")
	WebElement relationshiplist;

	@FindBy(css = "span[aria-owns='PatientInsuranceSexDropDown_listbox'] button")
	WebElement gender;

	@FindBy(css = "#PatientInsuranceSexDropDown_listbox li")
	WebElement genderoptions;

	@FindBy(id = "PatientInsuranceMobilePhone")
	WebElement patientinsurancenamemobilephone;

	@FindBy(id = "PatientInsuranceEmailAddress")
	WebElement patientinsuranceemail;

	@FindBy(id = "PatientInsuranceaddress")
	WebElement patientinsurancenameaddress;

	@FindBy(id = "PatientInsuranceaddress2")
	WebElement patientinsurancenameaddrees2;

	@FindBy(id = "PatientInsuranceCity")
	WebElement patientinsurancecity;

	@FindBy(css = "span[aria-owns='PatientInsuranceStateDropDown_listbox'] button")
	WebElement state;

	@FindBy(css = "#PatientInsuranceStateDropDown_listbox li")
	WebElement statelist;

	@FindBy(id = "PatientInsuranceZipCode")
	WebElement patientinsurancezipcode;

	// =========Insured Dialog Box======

	@FindBy(xpath = "//div[contains(text(),'Overwrite current insured information')]")
	WebElement overwritemessage;

	@FindBy(css = "div.k-dialog-buttongroup.k-actions button:nth-of-type(1)")
	WebElement okbutton;

	@FindBy(css = "div.k-dialog-buttongroup.k-actions button:nth-of-type(2)")
	WebElement cancelbutton;

	// ===========Insurance row List======================

	@FindBy(css = "tbody[role=rowgroup] tr")
	private List<WebElement> insurancerowlist;

	// ===== delete Insurance==========

	@FindBy(css = "a.k-grid-InsuranceGridRemoveBtn")
	WebElement deletebutton;

	// ================= Methods===========================

	public void isInsurancePage() throws InterruptedException {
		Thread.sleep(1000);
	}

	public void addInsuranceDetails(List<HashMap<String, Object>> listmap) throws InterruptedException {

		clickInsuranceDropdown(listmap);
		fillPolicyDetails(listmap);
		// fillInsuredDetails(listmap);
		overwriteDetails(listmap);
		// overidesInsuredDetails(listmap);
		clickSaveButton();

		Thread.sleep(1000);

	}

	public void addMutipleInsuranceDetails(HashMap<String, Object> map) throws InterruptedException {

		List<String> insuranceValues = Arrays.asList(map.get("Company").toString().split(","));

		insuranceValues.forEach(insuranceValue -> {
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			insurancedropdown.click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectdropdown(insurancecompany, insuranceValue);
			clickSaveButton();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// home.clickAddInsuranceButtonAndRow();
		});
	}

	public void updateInsuranceDetails(List<HashMap<String, Object>> searchlistmap,

			List<HashMap<String, Object>> updatedData) throws InterruptedException {

		Thread.sleep(1000);

		String insuranceToSearch = searchlistmap.get(0).get("Insurance Company").toString();

		updateinsurancecompany(insuranceToSearch);
		updateExistingInsuranceDetails(updatedData);

	}

	public void deleteInsuranceDetails(List<HashMap<String, Object>> searchlistmap) throws InterruptedException {

		Thread.sleep(1000);
		String insuranceToSearch = searchlistmap.get(0).get("Insurance Company").toString();

		deleteExisitngInsurance(insuranceToSearch);
	}

	// ==== Select Insurance=====
	public void clickInsuranceDropdown(List<HashMap<String, Object>> listMap) throws InterruptedException {
		Thread.sleep(800);
		insurancedropdown.click();
		Thread.sleep(1000);
		selectdropdown(insurancecompany, listMap.get(0).get("Company").toString());
		String company = insurancecompanyname.getText().trim();
		System.out.println("Selected Insurance Company: " + company);

	}

	// === Enter Policy Details ===

	public void fillPolicyDetails(List<HashMap<String, Object>> listmap) throws InterruptedException {

		waitForVisibility(insurancecompanyname);
		clearAndType(policyno, listmap.get(0).get("PolicyNo").toString());
		clearAndType(patientinsuranceidnumber, listmap.get(0).get("ID").toString());
		clearAndType(patientinsuranceplanno, listmap.get(0).get("Plan").toString());
		clearAndType(patientinsuranceplanprogramname, listmap.get(0).get("Plan Program").toString());
		Thread.sleep(1000);
		effectivedate.click();
		Thread.sleep(1000);
		selectDOB(effectivedate, listmap.get(0).get("Effective Date").toString());

	}

	// ==== Enter Insured details =====

	public void fillInsuredDetails(List<HashMap<String, Object>> listmap) throws InterruptedException

	{
		Thread.sleep(1000);
		clearAndType(patientinsurancenamefirst, listmap.get(0).get("Insured Name").toString());
		clearAndType(patientinsurancemi, listmap.get(0).get("Insured M").toString());
		scrollToElement(patientinsurancenamelast, true);
		clearAndType(patientinsurancenamelast, listmap.get(0).get("Insured Last").toString());
		Thread.sleep(1000);
		// Date of Birth - scroll + wait + type
		dob.click();
		scrollToElement(dob, true);
		waitForClickable(dob);

		selectDOB(dob, listmap.get(0).get("DOB").toString());

		relationship.click();
		Thread.sleep(1000);
		selectdropdown(relationshiplist, listmap.get(0).get("Relation").toString());

		Thread.sleep(1000);
		gender.click();
		Thread.sleep(500);
		selectdropdown(genderoptions, listmap.get(0).get("Gender").toString());

		scrollToElement(patientinsurancenamemobilephone, true);

		// scroll code to view bottom fields

		clearAndType(patientinsurancenamemobilephone, listmap.get(0).get("Insured Mobile").toString());
		clearAndType(patientinsuranceemail, listmap.get(0).get("Insured Email").toString());

		scrollToElement(patientinsurancenameaddress, true);

		clearAndType(patientinsurancenameaddress, listmap.get(0).get("Insured Address1").toString());
		clearAndType(patientinsurancenameaddrees2, listmap.get(0).get("Insured Address2").toString());
		clearAndType(patientinsurancecity, listmap.get(0).get("Insured City").toString());

		Thread.sleep(1000);
		state.click();
		Thread.sleep(500);
		selectdropdown(statelist, listmap.get(0).get("State").toString());
		Thread.sleep(500);

		clearAndType(patientinsurancezipcode, listmap.get(0).get("Insured Zipcode").toString());

	}

	public void overidesInsuredDetails(List<HashMap<String, Object>> listmap) throws InterruptedException {

		scrollToElement(patientinsurancenamelast, true);
		relationship.click();
		Thread.sleep(1000);
		selectdropdown(relationshiplist, listmap.get(0).get("Relation").toString());

	}

	public void overwriteDetails(List<HashMap<String, Object>> listmap) throws InterruptedException {

		Thread.sleep(1000);
		clearAndType(patientinsurancenamefirst, listmap.get(0).get("Insured Name").toString());
		clearAndType(patientinsurancemi, listmap.get(0).get("Insured M").toString());
		scrollToElement(patientinsurancenamelast, true);
		clearAndType(patientinsurancenamelast, listmap.get(0).get("Insured Last").toString());
		Thread.sleep(1000);
		// Date of Birth - scroll + wait + type
		dob.click();
		scrollToElement(dob, true);
		waitForClickable(dob);

		selectDOB(dob, listmap.get(0).get("DOB").toString());

		scrollToElement(patientinsurancenamelast, true);
		relationship.click();
		Thread.sleep(1000);
		selectdropdown(relationshiplist, listmap.get(0).get("Relation").toString());

		waitForVisibility(overwritemessage);

		okbutton.click();
		Thread.sleep(1000);

	}

	// ===================Update Code============================

	public void updateinsurancecompany(String value) {

		insurancerowlist.stream().filter(x -> x.getText().contains(value)).collect(Collectors.toList()).get(0).click();

	}

	public void updateExistingInsuranceDetails(List<HashMap<String, Object>> updatedData) throws InterruptedException {
		// Select new company
		insurancedropdown.click();
		Thread.sleep(1000);
		selectdropdown(insurancecompany, updatedData.get(0).get("Company").toString());
		clickSaveButton();
	}

	// ====================Delete Method=========================

	public void deleteExisitngInsurance(String insuranceToSearch) throws InterruptedException {

		Thread.sleep(1000);

		// Ensure the insurance row exists
		insurancerowlist.stream().filter(row -> row.getText().contains(insuranceToSearch)).findFirst()
				.orElseThrow(() -> new RuntimeException("Insurance company not found: " + insuranceToSearch));

		// Click delete
		deletebutton.click();
		Thread.sleep(500);
		okbutton.click();

	}

}
