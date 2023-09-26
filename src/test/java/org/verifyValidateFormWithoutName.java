package org;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.pageObjects.android.ProductCatalogue;

import org.TestUtils.AndroidBaseTest;
import org.testng.annotations.BeforeMethod;


public class verifyValidateFormWithoutName extends AndroidBaseTest{

	
	@BeforeMethod(alwaysRun=true)
	public void preSetup()
	{
	
		formPage.setActivity();
				
	}
	
	@Test
	public void verifyValidateFormWithoutName() throws InterruptedException
	{
		String expectedErrormessage = "Please enter your name";
		formPage.setGender("Female");
		formPage.setCountrySelection("Andorra");
		ProductCatalogue productCatalogue = formPage.submitForm();
		String actualtMessage = formPage.getErrorMessage().getAttribute("name");
		AssertJUnit.assertEquals(actualtMessage,expectedErrormessage);			
	}
	

}
