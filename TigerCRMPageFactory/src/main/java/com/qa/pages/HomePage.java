package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.master.MasterClass;

public class HomePage extends MasterClass{

	@FindBy(xpath="[//span[contains(text(),'Administrator')]")
	WebElement eleUserNameDisplay;
	
	//@FindBy(xpath="//a[contains(text(),'Leads')][1]")
	@FindBy(linkText="Leads")
	WebElement eleLeadsTab;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement eleContacts;
	
	/*@FindBy(xpath="//span[contains(text(),'Administration')]")
	WebElement eleUserLabel;*/
	
	@FindBy(xpath="//a[contains(text(),'Organizations')]")
	WebElement eleOrganizations;
	
	@FindBy(xpath="//a[contains(text(),'Products')]")
	WebElement eleProducts;
	
	@FindBy(xpath="//a[contains(text(),'Documents')]")
	WebElement eleDocuments;
	
	@FindBy(linkText="Home")
	WebElement eleHomeLabel;
	
	@FindBy(css="span.userName")
	WebElement eleUserNameLabel;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public LeadsPage navigateToLeadsPage() {
		eleLeadsTab.click();
		return new LeadsPage();
	}
	
	public ContactsPage navigateToContactsPage() {
		eleContacts.click();
		return new ContactsPage();
	}
	
	public OrganisationsPage navigateToOrganisationsPage() {
		eleOrganizations.click();
		return new  OrganisationsPage();
	}
	
	public String userNameLabelTest() {
		return eleUserNameLabel.getText();
	}
	
	public boolean homeLinkDisplayed() {
		return eleHomeLabel.isDisplayed();
	}
}
