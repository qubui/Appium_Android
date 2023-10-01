package org.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class CartPage extends AndroidActions {
	AndroidDriver driver;
	public CartPage(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	public WebElement terms;
	
	@AndroidFindBy(id="android:id/button1")
	public WebElement acceptButton;
	
	@AndroidFindBy(xpath="//android.widget.Toast[1]")
	private WebElement messageAddCartWithoutProduct;

	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	public WebElement proceed;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	public WebElement checkBox;
	
	public List<WebElement> getProductList()
	{
		
		return productList;
	}
	
	public double getProductsSum()
	{
		int count = productList.size();
		double totalSum =0;
		for(int i =0; i< count; i++)
		{
			String amountString =productList.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalSum = totalSum + price;  //160.97 + 120 =280.97
				
		}
		return totalSum;
	}
	
	public Boolean isTermsDisplay() 
	
	{
		Boolean terms =  getTerms().isDisplayed();
		return terms;

	}
	
	public Boolean isSubmitOrderButtonDisplay() 
	
	{
		Boolean acceptButton =  getAcceptButton().isDisplayed();
		return acceptButton;

	}
	
	public WebElement getTotalAmount() {
		return totalAmount;
	}

	public WebElement getTerms() {
		return terms;
	}

	public WebElement getAcceptButton() {
		return acceptButton;
	}

	public WebElement getProceed() {
		return proceed;
	}

	public Double getTotalAmountDisplayed()
	{
		return getFormattedAmount(totalAmount.getText());
	}
	
	public void acceptTermsConditions()
	{
		checkBox.click();
	}
	

	public void submitOrder()
	{
		checkBox.click();
		proceed.click();
	}
	

	public WebElement getMessageAddCartWithoutProduct() {
		return messageAddCartWithoutProduct;
	}
	
	public String getTextMessageAddCartWithoutProduct()
	{
		waitForElementToAppear(messageAddCartWithoutProduct, driver);
		return messageAddCartWithoutProduct.getText();
	}
	
	
}
