package org;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.TestUtils.AndroidBaseTest;
import org.pageObjects.android.CartPage;
import org.pageObjects.android.ProductCatalogue;




public class verifyFillFormAndAddToCart extends AndroidBaseTest{

	
	@Test(dataProvider="getData")
	public void verifyFillFormAndAddToCart(HashMap<String,String> input) throws InterruptedException {	
		formPage.setActivity();
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();
		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
		assertFalse(cartPage.isTermsDisplay());
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>>	data =getJsonData(System.getProperty("user.dir")+"//src//test//java//org//testData//eCommerce.json");	
		return new Object[][] { {data.get(0)},{data.get(1)}  };
	}
	

	
}
