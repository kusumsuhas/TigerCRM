package com.qa.master;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.util.CommonUtil;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class MasterClass {

	public static WebDriver driver;
	public Properties prop;
	private FileInputStream FIS;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Logger log = Logger.getLogger(MasterClass.class.getName());

	public void loadProperties() {
		try {
			String path = "E:\\Temp Eclipse Repository\\TigerCRMPageFactory\\src\\main\\java\\com\\qa\\config\\config.properties";
			FIS = new FileInputStream(path);
			prop = new Properties();
			prop.load(FIS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectBrowser() {		
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			/*String PROXY="";
			Proxy proxy = new Proxy();
			proxy.setProxyType(Proxy.ProxyType.MANUAL);
			proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
			DesiredCapabilities capabilities =  DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
			System.setProperty("webdriver.chrome.driver","E:\\Trainings\\Selenium\\Drivers\\chromedriver_win32_V2.33\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			//options.setExperimentalOption("useAutomtionExtension", false);
			options.addArguments("disbale-infobars");			
			driver = new ChromeDriver(options);	
		} else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\Trainings\\Selenium\\Drivers\\geckodriver-v0.19.0\\geckodriver.exe");
			File pathBinary=new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			//driver = new FirefoxDriver();
		}
		log.info(browserName+" is selected");
	}
	
	public void browserInitialisation() {
		loadProperties();
		log.info("Properties are loaded.");
		selectBrowser();
		log.info("Browser loaded");
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(CommonUtil.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);		
		driver.manage().timeouts().implicitlyWait(CommonUtil.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
	}
	
	@BeforeSuite
	public void initextent() {
		
		String logConfigPath="E:\\Git Repository\\TigerCRMPageFactory\\Log4J.properties";
		PropertyConfigurator.configure(logConfigPath);
		
		htmlReporter = new ExtentHtmlReporter("E:\\Temp Eclipse Repository\\TigerCRMPageFactory\\test-output\\TigerCRMTestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("SIT Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	@AfterMethod() 
	public void getResult(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test case Passed", ExtentColor.GREEN));
		} else if (result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" TEst case Skipped",ExtentColor.AMBER));
			test.skip(result.getThrowable());
		}
	}
	
	@BeforeMethod()
	public void initExtent(Method method) {
		test= extent.createTest(method.getName());
	}
}
