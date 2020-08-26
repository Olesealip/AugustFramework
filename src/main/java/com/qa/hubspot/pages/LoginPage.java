package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class LoginPage extends BasePage {
// we need to create on private webdriver i dont want anyone to access this login page driver 
	private WebDriver driver;
	ElementUtil elementUtil;
	
	
	//By locators this will behave like object repository 
	By email= By.id("username");
	By password = By.id("password") ;
	By loginButton=  By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
			
	// we create constructor of the page class:
	
	public LoginPage(WebDriver driver) { //we give the WebDRiver 	
		this.driver= driver;
	
		elementUtil= new ElementUtil(this.driver);
		}		
			
			//page actions:
	
	public String getPageTitle() {
	//return	driver.getTitle();
	
	return elementUtil.doGetPageTitleWithIsTitle(10, Constants.LOGIN_PAGE_TITLE);
	
	
	}	
	
	public boolean isSighUpLinkExist() {
	//return	driver.findElement( signUpLink ).isDisplayed();
	return elementUtil.doIsDisplayed(signUpLink);	
	}
	
	public HomePage doLogin(String username, String pwd) {
//		driver.findElement(email).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//        driver.findElement(loginButton).click();
	System.out.println("Login to app. with ---> " + username + ":"  + pwd);
		
		elementUtil.waitForElementToBeVisible(email, 10);
		elementUtil.doActionsSendKeys(email, username);
		elementUtil.doActionsSendKeys(password, pwd);
		elementUtil.doActionsClick(loginButton);
		
		
		
		return new HomePage(driver);
		
	}
	
			
			
			
			
	

}
