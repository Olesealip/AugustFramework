package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage {
private WebDriver driver;
private ElementUtil elementUtil;
By header= By.xpath("//i18n-string[text()='Dashboard Library']");
By account= By.id("account-menu");
By accountName= By.cssSelector("div.user-info-name");

By contactsPrimaryLink= By.id("nav-primary-contacts-branch");
By contactsSecondaryLink= By.xpath("(//a[@id='nav-secondary-contacts'])[position()=1]");


public HomePage(WebDriver driver) {
	this.driver=driver;
	elementUtil= new ElementUtil(this.driver);
	
	
}	
	public String getHomePageTitle() {
		return driver.getTitle();
}
	public String getHomePageHeader() {
	if(elementUtil.doIsDisplayed(header))  // if header is displayed then return text if not visible then return null 
		return elementUtil.doGetText(header);
	return null;
	}	
	
	public String getHomePageAccountName() {
		elementUtil.doActionsClick(account);;
		if(elementUtil.doIsDisplayed(accountName))
		return	elementUtil.doGetText(accountName);
		return null;
	}
	//we did encapsulation
	public  ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
	private void clickOnContacts() {
		elementUtil.waitForElementPresent(contactsPrimaryLink, 20);
		elementUtil.doClick(contactsPrimaryLink);
		//elementUtil.waitForElementPresent(contactsSecondaryLink, 20);
		//elementUtil.doClick(contactsSecondaryLink);
		
		elementUtil.clickWhenReady(contactsSecondaryLink, 20);
		
	}
	}
	






