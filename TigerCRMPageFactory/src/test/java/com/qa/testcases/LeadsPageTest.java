package com.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.master.MasterClass;
import com.qa.pages.HomePage;
import com.qa.pages.LeadsPage;
import com.qa.pages.LoginPage;

public class LeadsPageTest extends MasterClass{

	LoginPage loginPage;
	HomePage homePage;
	LeadsPage leadsPage;
	
	
	@BeforeClass
	public void init() {
	browserInitialisation();
	loginPage = new LoginPage();
	homePage= loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	leadsPage = homePage.navigateToLeadsPage();	
	}
	
	
	@Test
	public void displayLeads() {
		leadsPage.leadRecordsDisplay();
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}

