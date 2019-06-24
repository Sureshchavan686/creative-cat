package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DocumentTest {
	
	public WebDriver driver;
	
	
	  @BeforeTest(alwaysRun = true)
	  @Parameters({"Url"})
	  public void setup(String Url)
	  {
		  System.setProperty("webdriver.chrome.driver", "E://chromedriver.exe");
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("disable-infobars");
		  options.addArguments("start-maximized");
		  driver = new ChromeDriver(options);
		  driver.get(Url);
		  
	  }

	  @Parameters({"Username", "Password"})
	  @Test(priority =1)
	  public void login(String username, String password) 
	  {
		  driver.findElement(By.xpath("//INPUT[@id='_loginControl__txtLoginUserName']")).sendKeys(username);
		  
		  driver.findElement(By.xpath("//INPUT[@id='_loginControl__txtLoginPassword']")).sendKeys(password);
		  
		  driver.findElement(By.xpath("//INPUT[@id='_loginControl__btnLogin']")).click();;
		 
	  }
	  
	  @Parameters({"Documentation"})
	  @Test(priority =2)
	  public void document(String documentLink) throws InterruptedException
	  {
		
		  driver.get(documentLink);
		  driver.findElement(By.xpath("//a[@href='WebDocumentOverview.aspx']")).click();
		  driver.findElement(By.xpath("//input[@id='ctl00_masterContent_DocOverview_filterTree__txtSearchBox']")).sendKeys("demo");
		  driver.findElement(By.xpath("//input[@id='ctl00_masterContent_DocOverview_filterTree_ctl00']")).click();
		  
		  Thread.sleep(5000);
		  driver.findElement(By.xpath("//input[@name='ctl00$masterContent$DocOverview$filterTree$ctl01']")).click();
		  Thread.sleep(5000);
		  
		  driver.findElement(By.xpath("(//div[@class='rtIn'])[1]")).click();
		  WebElement view = driver.findElement(By.xpath("//div[@id='_divErrorMessageDisplay']"));
		  System.out.println(view.getText()); 
		  System.out.println("hello output");
		 
		
		  
		  
	  }
}
