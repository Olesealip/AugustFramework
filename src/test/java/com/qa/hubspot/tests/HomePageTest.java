package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;


//@Epic("Epic-102 : design Home page features")  //epic is the colection of all user stories  //- this is from allure
//@Feature("US- 301: design home page title, header and account name modules...")  //- user story name  // this is from allure

public class HomePageTest extends BaseTest {

	HomePage homePage;
	
 @BeforeClass
 public void homePageSetUp() {
homePage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")); 
 }
 
//@Description ("Verify Title Test on home page...")
//@Severity(SeverityLevel.NORMAL) - this is coming from allure
	
  @Test(priority = 2)
  
  public void verifyHomepageTitleTest() {
String title=homePage.getHomePageTitle();  
	System.out.println("Home Page title is " + title);
	Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	
  }
  
//@Description ("Verify Header Test on home page...")
//@Severity(SeverityLevel.MINOR) - this is coming from allure
	
  @Test(priority = 1)
public void verifyHomePageHeaderTest() {
	String header =homePage.getHomePageHeader();
	System.out.println("Home Page header is " + header);
	Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
  }	
  
//@Description ("Verify loget in user on home page...")
//@Severity(SeverityLevel.MINOR) - this is coming from allure
	
  @Test(priority = 3)
  public void verifyLoginUserTest() {
	String accountName= homePage.getHomePageAccountName();
	System.out.println("Home Page account Name is "+ accountName);
	Assert.assertEquals(accountName, prop.getProperty("accountName"));
  }
	
	
 @AfterTest
 public void tearDown() {
	 driver.quit();
 }


}
