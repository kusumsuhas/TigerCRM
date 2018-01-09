package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.master.MasterClass;

public class LeadsPage extends MasterClass{

	@FindBy(xpath="//img[@title='Create Lead...']")
	WebElement eleNewContactLink;
	
	@FindBy(name="salutationtype")
	WebElement eleSalutationSelect;
	
	@FindBy(name="firstname") 
	WebElement elefirstName;
	
	@FindBy(name="lastname") 
	WebElement eleLastName;
	
	@FindBy(name="company")
	WebElement eleCompany;
	
	@FindBy (xpath="//input[@title='Save [Alt+S]']")
	WebElement eleSave;
	
	@FindBy(xpath="//table[@class='lvt small']/tbody")
	WebElement eleTable;
	
	
	public LeadsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void createNewLead() {
		eleNewContactLink.click();
		Select select = new Select(eleSalutationSelect);
		select.selectByIndex(0);
		elefirstName.sendKeys("Tester");
		eleLastName.sendKeys("Tester");
		eleCompany.sendKeys("Home");
		eleSave.click();		
	}
	
	public void leadRecordsDisplay() {
		int noOfRows = eleTable.findElements(By.tagName("tr")).size();
		int noOfCols = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[3]/td")).size();
		
		String firstxpath= "//table[@class='lvt small']/tbody/tr[";
		String secondXpath="]/td[";
		String finalXpath="";
		//table[@class='lvt small']/tbody/tr[3]/td[2]
		
		for( int i=2; i<=noOfRows; i++) {
			for(int j =1; j<=8; j++) {
				finalXpath=firstxpath+i+secondXpath+j+"]";
				System.out.println(finalXpath);
				System.out.print(driver.findElement(By.xpath(finalXpath)).getText()+" ! ");
			}
			System.out.println("");
		}
		
		
	}
}
