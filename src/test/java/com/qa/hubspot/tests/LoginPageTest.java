package com.qa.hubspot.tests;
//in testNG class we will never write main method 

import org.testng.Assert;


import org.testng.annotations.Test;


import com.qa.hubspot.base.BaseTest;

import com.qa.hubspot.utils.Constants;

//@Epic("Epic-101 : design login page features")  //epic is the colection of all user stories  //- this is from allure
//@Feature("US- 201: design login page title, sign up link and login form module...")  //- user story name  // this is from allure
public class LoginPageTest extends BaseTest {
	
	//@Description("Verify sign up link on login page....")- this is from allure
	//@Severity(SeverityLevel.CRITICAL) - this is coming from allure
	@Test(priority=1)
	public void verifySighUpLinkTest() {
	Assert.assertEquals(loginPage.isSighUpLinkExist(), true);	
	}
	
	//@Description("Verify login page title on login page....")- this is from allure
	//@Severity(SeverityLevel.NORMAL) - this is coming from allure
	@Test(priority = 2)
	public void verifyloginPageTitleTest() {
	String title =loginPage.getPageTitle();
	System.out.println("Login page title is " + title  );
	Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE); 
	}
	
	//@Description("Verify user is able  to login page....")- this is from allure
		//@Severity(SeverityLevel.BLOCKER) - this is coming from allure
	@Test (priority = 3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	
}

