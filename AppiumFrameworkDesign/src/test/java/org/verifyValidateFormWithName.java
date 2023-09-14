package org;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.pageObjects.android.ProductCatalogue;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.TestUtils.AndroidBaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class verifyValidateFormWithName extends AndroidBaseTest{

	
	
	@BeforeMethod(alwaysRun=true)
	public void preSetup()
	{
	
		formPage.setActivity();
				
	}

	@Test(dataProvider="getData")
	public void verifyValidateFormWithName(String name, String gender, String country) throws InterruptedException	
	{		
		formPage.setNameField(name);
		formPage.setGender(gender);
		formPage.setCountrySelection(country);
		ProductCatalogue productCatalogue = formPage.submitForm();	
		assertTrue(productCatalogue.isCartDisplay());
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		return new Object[][] {{"Quy Bui", "Female", "Andorra"}};
	}
}
