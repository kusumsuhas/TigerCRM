package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.master.MasterClass;

public class LoginPage extends MasterClass{
	
	

	@FindBy(name="user_name")
	WebElement eleUsername;
	
	@FindBy (name="user_password")
	WebElement elePassword;
	
	@FindBy (id="submitButton")
	WebElement eleLogin;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String uName, String pwd) {
		eleUsername.sendKeys(uName);
		elePassword.sendKeys(pwd);
		eleLogin.click();
		return new HomePage();
	}
	
	public String getLoginPagetitle() {
		return driver.getTitle();
	}
	
}
