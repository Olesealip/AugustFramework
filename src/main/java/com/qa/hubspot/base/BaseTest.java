package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import com.qa.hubspot.pages.LoginPage;

public class BaseTest {

public WebDriver driver; //we changed to public so we can access from child class 
public Properties prop;
public BasePage basePage;
public LoginPage loginPage;


//TestNG ----unit testing framework  
	//preconditionds in test : the browser should be lounch 
	//PreConditions---> Test cases(steps)(Actual vs expected)---->Assertions-----> Tear down 
//BeforeTest--->@Test---->Assertions	
	@BeforeTest   //before your test what you want to do 
	
public void setUp() {
	basePage= new BasePage();
	 prop= basePage.init_prop();
	driver=basePage.init_driver(prop);
	
	loginPage= new LoginPage(driver);
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
