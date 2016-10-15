package tests;

import general.classes.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.lang.String;

public class Q5Test extends ConfigHelp
{
	
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception 
  {

		driver = launchBrowser(driver);      
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
  }

  @Test
  public void testQ5() throws Exception 
  {
	  Actions act = new Actions(driver);
	  Action enter = act.sendKeys(Keys.ENTER).build();	  
	  
	  driver.get(getProperty("seleniumPage"));	  
	  
	  ifCookiesDisplayedClose(driver);
	  
	  validateElementDisplayed(driver, getHtml("imoCombo"));
	  validateElementDisplayed(driver, getHtml("typeCombo"));
	  validateElementDisplayed(driver, getHtml("locationPicker"));
  
	  selectCorrectOptionInCombo(driver, getLanguage("flats"), "imo");
	  
	  selectCorrectOptionInCombo(driver, getLanguage("forRent"), "type");	  
	  
	  writeLocation(driver, "Poznan");
	  
	  validateElementDisplayedWithText(driver, getHtml("typeCombo")+getHtml("rendered"), getLanguage("forRent"));
	  validateElementDisplayedWithText(driver, getHtml("imoCombo")+getHtml("rendered"), getLanguage("flats"));
	  validateElementDisplayedWithText(driver, getHtml("locationPicker")+getHtml("rendered"), "Pozna");
	  
	  searchButtonClick(driver);
	  
	  validateElementDisplayed(driver, getHtml("imoCombo"));
	  validateElementDisplayed(driver, getHtml("typeCombo"));
	  validateElementDisplayed(driver, getHtml("locationPicker"));
	  
	  WebElement price = driver.findElements(By.xpath(getHtml("priceTo"))).get(1);
	  
	  validateWebElementDisplayedAndClick(driver, price);
	  
	  enter.perform();
	  
	  validateStringElementDisplayedAndClick(driver, getHtml("rooms"));
	  
	  int roomsNumberElementSize = findElementUsingPageUpDown(driver, getHtml("roomsNo"));
	  WebElement roomsNumber[] = returnWebElementTable(driver, getHtml("roomsNo"), 1, roomsNumberElementSize-1);
	  
	  for(int i =0;i<roomsNumber.length;i++)
	  {
		  validateWebElementDisplayedAndClick(driver, roomsNumber[i]);
	  }
	  
	  validateStringElementDisplayedAndClick(driver, getHtml("rooms"));
	  
	  Thread.sleep(5000);
	  
	  searchButtonClick(driver);
	  
	  Thread.sleep(5000);
	  
	  int numEle = findElementUsingPageUpDown(driver, getHtml("perPage"));
	  
	  WebElement perPageLast[] = returnWebElementTable(driver, getHtml("perPage"), numEle-1, numEle-1);
	  
	  validateWebElementDisplayedAndClick(driver, perPageLast[0]);
	  
	  numEle = findElementUsingPageUpDown(driver, getHtml("item"));
	  
	  WebElement lastItem[] = returnWebElementTable(driver, getHtml("item"), numEle-1, numEle-1);
	  
	  validateWebElementDisplayedAndClick(driver, lastItem[0]);
	  
	  Thread.sleep(5000);
	  
	  String rooms = getOfferParameter(driver, getLanguage("rooms"));
	  
	  if(Integer.parseInt(rooms)!=2)
	  {
		  fail("Number of rooms is not correct, it must be 2 and we got "+rooms);
	  }
	
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
