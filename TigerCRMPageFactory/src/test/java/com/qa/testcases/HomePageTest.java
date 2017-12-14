package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.master.MasterClass;
import com.qa.pages.HomePage;
import com.qa.pages.LeadsPage;
import com.qa.pages.LoginPage;

public class HomePageTest extends MasterClass{

	LoginPage loginPage;
	HomePage homePage;
	LeadsPage leadsPage;
	
	@BeforeClass
	public void init() {
		browserInitialisation();
		leadsPage = new LeadsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));		
	}
	
	@Test
	public void test1() {
		Assert.assertTrue(homePage.homeLinkDisplayed());
	}
	
	@Test
	public void test2() {
		System.out.println(homePage.userNameLabelTest());
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
