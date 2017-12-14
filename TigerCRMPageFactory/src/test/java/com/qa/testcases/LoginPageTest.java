package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.master.MasterClass;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginPageTest extends MasterClass {

	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeClass
	public void init() {
		browserInitialisation();
		loginPage = new LoginPage();
	}
	
	@Test (priority=1)
	public void loginTitleValidation(){
		Assert.assertEquals(driver.getTitle(), "vtiger CRM 5 - Commercial Open Source CRM", "LoginPage Title not matched");
	}
	
	@Test (priority=2)
	public void loginPageTest() {
		
		homePage= loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority=3)
	public void homePagetitleValidation() {
		Assert.assertEquals(driver.getTitle(), "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM", "HomePage title not matched");
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}

