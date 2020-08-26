package com.qa.hubspot.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;

public class LoginPageWithBeforeMethod {
WebDriver driver;
Properties prop;
BasePage basePage;
LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
    basePage= new BasePage();
    prop= new Properties();
    driver= new BasePage().init_driver(prop);
    loginPage= new LoginPage(driver);
    
    }
    
    @Test(priority=1)
	public void verifySighUpLinkTest() {
	Assert.assertEquals(loginPage.isSighUpLinkExist(), true);	
	}
	
	@Test(priority = 2)
	public void verifyloginPageTitleTest() {
	String title =loginPage.getPageTitle();
	System.out.println("Login page title is " + title  );
	Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE); 
	}
	@Test (priority = 3)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

     @AfterMethod
   public void tearDown() {
	driver.quit();
}

}

