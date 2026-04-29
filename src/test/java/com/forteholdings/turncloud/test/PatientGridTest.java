package com.forteholdings.turncloud.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.forteholdings.turncloud.base.TestComponents.BasePage;
import com.forteholdings.turncloud.config.DataReader.DataReader;
import com.forteholdings.turncloud.pageobject.Demographics;
import com.forteholdings.turncloud.pageobject.PatientLookupGrid;

public class PatientGridTest extends BasePage {

//	@Test
	public void patientListGrid1() throws IOException, InterruptedException {

		// -------------------- Login --------------------
		List<HashMap<String, Object>> loginDataList = DataReader.getjsondatamap("Logindata.json");
		home.PageCredentials(loginDataList.get(0).get("email").toString(),
				loginDataList.get(0).get("password").toString(), loginDataList.get(0).get("Account").toString());
		home.ispagedisplayed();

		// -------------------- Patient Selection --------------------
		List<HashMap<String, Object>> patientGrid = DataReader.getjsondatamap("patientgrid.json");
		PatientLookupGrid lookupGrid = new PatientLookupGrid(driver);
		lookupGrid.selectPatient(patientGrid.get(0).get("Account").toString());
		lookupGrid.waitForPatientTabVisibility();
		lookupGrid.clickDemographicsTab();
		lookupGrid.waitForDemographicsPage();

		// -------------------- Update Demographics --------------------
		Demographics updateDemographics = new Demographics(driver);

		// Example: If clear method is implemented in Demographics
		// updateDemographics.clearAndType();

		List<HashMap<String, Object>> updatePatientData = DataReader.getjsondatamap("updatepatient.json");
		updateDemographics.addPatient(updatePatientData);
	}

}
