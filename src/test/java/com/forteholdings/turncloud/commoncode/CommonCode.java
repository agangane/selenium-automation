package com.forteholdings.turncloud.commoncode;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonCode {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected WebDriverWait wait1;

	// Constructor - initializes driver, wait, and web elements
	public CommonCode(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		this.wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// Web Elements
	@FindBy(id = "SavePatient")
	private WebElement saveButton;

	// ===== Wait Methods =====

	public void waitForVisibility(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForVisibilityby(By locator) {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void waitForVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForVisibilityOfAllElements(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	// ====== Scroll Code for the bottom ====

	public void scrollToElement(WebElement element, boolean scrollDown) {
		if (scrollDown) {
			// Align element to the top of the viewport (scroll down)
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} else {
			// Align element to the bottom of the viewport (scroll up)
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		}
	}

	// ==============JavaScript========================

	// ===================setvaluein HeightKendo==================================

	/*
	 * public void setHeightFtIn(WebElement heightFt, String ft, String inch) {
	 *
	 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 *
	 * // 1️⃣ Focus feet wait.until(ExpectedConditions.visibilityOf(heightFt));
	 * heightFt.click();
	 *
	 * WebElement active = driver.switchTo().activeElement();
	 *
	 * // 2️⃣ Enter feet active.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	 * active.sendKeys(Keys.DELETE); active.sendKeys(ft);
	 *
	 * // 3️⃣ TAB → moves to inch INSIDE SAME WIDGET active.sendKeys(Keys.TAB);
	 *
	 * // 4️⃣ Now active element IS inch WebElement inchActive =
	 * driver.switchTo().activeElement();
	 *
	 * inchActive.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	 * inchActive.sendKeys(Keys.DELETE); inchActive.sendKeys(inch);
	 *
	 * // 5️⃣ Final TAB → commit widget inchActive.sendKeys(Keys.TAB); }
	 */

	// *********Reusable code for All Vital fields kendo*************

	public void setKendoComposite(WebElement startElement, String... values) throws InterruptedException {

		startElement.click();

		for (String value : values) {
			WebElement active = driver.switchTo().activeElement();

			active.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			active.sendKeys(Keys.DELETE);
			active.sendKeys(value);

			active.sendKeys(Keys.TAB);
			Thread.sleep(500);
		}
	}

	// =====ClearAndType =======

	public void clearAndType(WebElement element, String value) {
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(value);
	}

	// ===========================
	// Utility Methods
	// ===========================

	// ===== Assert Demographics State===
	public void assertDropdownValue(String dropdownLabel, String expectedValue) {
		WebElement label = driver.findElement(By.cssSelector("li.k-list-item span"));
		WebElement container = label.findElement(By.xpath("//span[text()='" + expectedValue + "']"));
		String actual = container.getText().trim();
		System.out.println("Selected value for '" + dropdownLabel + "': " + actual);
		Assert.assertEquals(actual, expectedValue, dropdownLabel + " mismatch");
	}

	// === Assert Employer State===
	public void assertdropdownvalues(String expectedValue, String dropdownLabel) {
		By selectedValueLocator = By.xpath("//div[@id='EmployerForm']//span[@class='k-input-value-text']");
		WebElement selectedValueElement = driver.findElement(selectedValueLocator);
		String actualValue = selectedValueElement.getText().trim();
		System.out.println("Selected value for '" + dropdownLabel + "': " + actualValue);
		Assert.assertEquals(actualValue, expectedValue, dropdownLabel + " mismatched");
	}

	// ===== Common Actions =====

	public void clickSaveButton() {
		waitForClickable(saveButton);
		saveButton.click();
	}

	// ====== Dropdown Selections =======

	public void selectdropdown(WebElement element, String value) {
		List<WebElement> options = driver.findElements(By.xpath("//span[text()='" + value + "']"));

		options.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
	}

	/// chatgpt code By Locators

	public void selectdropdowns(By optionsLocator, String value) {

		// small wait for slow UI
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(optionsLocator));

		List<WebElement> options = driver.findElements(optionsLocator);

		// chatgptcode

		options.stream().filter(WebElement::isDisplayed)
				.filter(opt -> opt.getText().trim().equalsIgnoreCase(value.trim())).findFirst()
				.orElseThrow(() -> new RuntimeException("Dropdown value not found: " + value)).click();

	}

// Common Code for the dropdown //
	public void selectdropdownList(List<WebElement> options, String value) {

		WebElement selected = options.stream().filter(WebElement::isDisplayed)
				.filter(opt -> opt.getText().trim().equalsIgnoreCase(value.trim())).findFirst()
				.orElseThrow(() -> new RuntimeException("Dropdown option not found: " + value));

		selected.click();
	}

// Using By locator//
	public void selectdropdownsby(By optionsLocator, String value) {

		List<WebElement> options = driver.findElements(optionsLocator);
		selectdropdownList(options, value);
	}

	// =================Grid selection dropdown =========================

	public void clickOnGrid(List<WebElement> elements, String value) {
		/*
		 * elements.stream().filter(x -> x.getText().trim().contains(value)).findFirst()
		 * .orElseThrow(() -> new RuntimeException("Data not found in grid: " +
		 * value)).click();
		 */

		// STEP 1: wait until grid rows are visible
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));

		// STEP 2: find the required row
		WebElement row = elements.stream().filter(x -> x.getText().trim().contains(value)).findFirst()
				.orElseThrow(() -> new RuntimeException("Data not found in grid: " + value));

		// STEP 3: wait until the row is clickable
		wait.until(ExpectedConditions.elementToBeClickable(row));

		// STEP 4: click the row
		row.click();
	}

	// ==== Selection of DOB =======

	public void selectDOB(WebElement element, String dobValue) throws InterruptedException {
		Thread.sleep(1000);
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);

		String[] parts = dobValue.split("/");
		int month = Integer.parseInt(parts[0]);
		int day = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);

		element.sendKeys(Keys.HOME);
		element.sendKeys("" + month + "/" + day + "/" + year);
	}

}
