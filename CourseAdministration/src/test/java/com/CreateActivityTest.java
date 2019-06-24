package com;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import net.bytebuddy.asm.Advice.Local;

public class CreateActivityTest {

	
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
	
  
  @Test(priority =2)
  public void createActivityFromTemplate() throws InterruptedException
  {
	  	/*Mouse hover activity*/
	  	  WebElement activity = driver.findElement(By.linkText("Activity"));
		 
		  /* Find activity search menu and click on it */
	  	  Actions action = new Actions(driver);
		  action.moveToElement(activity).perform();
		  
		  WebElement activitySearch = driver.findElement(By.linkText("Activity search"));
		  
		  action.moveToElement(activitySearch);
		  action.click();
		  action.perform();
		  
		  driver.findElement(By.xpath("//input[@value='New from Template']")).click();
		  Thread.sleep(5000);
		  driver.findElement(By.xpath("//input[@id='ctl00_masterContent__fplActivitySearch__ucActivityFromTemplate__txtName']")).sendKeys("course");
		  driver.findElement(By.xpath("//a[@id='ctl00_masterContent__fplActivitySearch__ucActivityFromTemplate__btnSearch']")).click();
		  
		  Thread.sleep(5000);
		  driver.findElement(By.xpath("//input[@name='ctl00$masterContent$_fplActivitySearch$_ucActivityFromTemplate$_gridTemplates$ctl00$ctl04$_btnSelect']")).click();
		  
		  Thread.sleep(10000);
		  WebElement activityName = driver.findElement(By.xpath("//INPUT[@name='ctl00$masterContent$_fpActivityEditor$_txtActivityName']")); 
		  activityName.click();
		  activityName.clear();
		  activityName.sendKeys("Selenium activity" +LocalTime.now());
		  

		  driver.findElement(By.xpath("//input[@id='ctl00_masterContent__fpActivityEditor__btnSave']")).click();
		  
		  Thread.sleep(5000);
		  
		  //Instantiate calendar function
		 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(new Date());
		 cal.add(Calendar.DATE, 10);
		 String actFromDate = dateFormat.format(cal.getTime());
		 
		 WebElement fromDate = driver.findElement(By.xpath("//input[@id ='ctl00_masterContent__fpActivityEditor__dateFrom_dateInput']"));
		 fromDate.sendKeys(actFromDate);
		 
		 cal.add(Calendar.DATE, 20);
		 String actToDate = dateFormat.format(cal.getTime());
		 WebElement toDate = driver.findElement(By.xpath("//input[@id ='ctl00_masterContent__fpActivityEditor__dateTo_dateInput']"));
		 toDate.sendKeys(actToDate);
		 
		 WebElement deadLineDate = driver.findElement(By.xpath("//input[@id ='ctl00_masterContent__fpActivityEditor__radEnrollDeadLine_dateInput']"));
		 deadLineDate.sendKeys(actFromDate);
		  //driver.findElement(By.xpath("//input[@id='ctl00__loginStatus']")).click();
		 
		 WebElement cancellationDeadLineDate = driver.findElement(By.xpath("//input[@id ='ctl00_masterContent__fpActivityEditor__radCancellationDeadline_dateInput']"));
		 cancellationDeadLineDate.sendKeys(actFromDate); 
		 
		 driver.findElement(By.xpath("//input[@id ='ctl00_masterContent__fpActivityEditor__txtMaxParticipants']")).sendKeys("35");
		 driver.findElement(By.xpath("//input[@id ='ctl00_masterContent__fpActivityEditor__txtMaxDepartmentParticipants']")).sendKeys("10");
		 driver.findElement(By.xpath("//input[@id ='ctl00_masterContent__fpActivityEditor__txtMinParticipants']")).sendKeys("30");
		 
		
		 Select sel = new Select(driver.findElement(By.xpath("//select[@id='ctl00_masterContent__fpActivityEditor__cboActivityStatus']")));
		 sel.selectByVisibleText("Approved");
		 Thread.sleep(5000);
		 
		//WebElement popUp = driver.findElement(By.xpath("//div[@id='ctl00_masterContent__fpActivityEditor__popupFrameCode']"));
		 
		//Alert alt = driver.switchTo().alert();
		//alt.accept();
		 
		//driver.switchTo().frame("ctl00_masterContent__fpActivityEditor__popupFrameCode");
		 driver.findElement(By.xpath("//input[@id='ctl00_masterContent__fpActivityEditor__btnStatusChangeOk_input']")).click();
		 
		 
		 driver.findElement(By.xpath("//input[@id='ctl00_masterContent__fpActivityEditor__btnSave']")).click();
		  
		
		
  }
  
  
  
  
}
