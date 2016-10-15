package general.classes;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


public class ConfigHelp{


	public String getHtml(String element) throws Exception 
	{
		String htmlPath = new File("resources/html.properties").getAbsolutePath();
		
		Properties prop = new Properties();
		InputStream input = null;

		String value="";
		
		try 
		{
			input = new FileInputStream(htmlPath);

			prop.load(input);
			
			value = prop.getProperty(element);
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} finally 
		{
			if (input != null) 
			{
				try {
					input.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return value;

		
	}
	
	public String getLanguage(String element) throws Exception 
	{
		String langPath = "";
				
		if(getProperty("language").equals("pl"))
		{
			langPath = new File("resources/polish.properties").getAbsolutePath();
		}
		
		Properties prop = new Properties();
		InputStream input = null;

		String value="";
		
		try 
		{
			input = new FileInputStream(langPath);

			prop.load(input);
			
			value = prop.getProperty(element);
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} finally 
		{
			if (input != null) 
			{
				try {
					input.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return value;

		
	}
	public String getProperty(String property) throws Exception
	{
		
		String configPath = new File("resources/config.properties").getAbsolutePath();
		
		Properties prop = new Properties();
		InputStream input = null;

		String value="";
		
		try 
		{
			input = new FileInputStream(configPath);

			prop.load(input);
			
			value = prop.getProperty(property);
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		} finally 
		{
			if (input != null) 
			{
				try {
					input.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return value;
		
	}

	
	public WebDriver launchBrowser(WebDriver driver) throws Exception
	{
		
		String path = new File("resources/chromedriver.exe").getAbsolutePath();
		
		ChromeOptions co = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver(co);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  
		return driver;
		
	}
	
	public void imoClick(WebDriver driver) throws Exception
	{ 
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Imo Combobox was not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(getHtml("imoCombo"))).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at imo ComboBox displayed validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
			driver.findElement(By.xpath(getHtml("imoCombo"))).click();
		} catch (Exception e) 
		{
			System.out.println("Exception at imo ComboBox click validation : "+e);
		}

	}
	
	public void searchButtonClick(WebDriver driver) throws Exception
	{ 
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Search Button was not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(getHtml("searchButton"))).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at Search Button displayed validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
			driver.findElement(By.xpath(getHtml("searchButton"))).click();
		} catch (Exception e) 
		{
			System.out.println("Exception at Search Button click validation : "+e);
		}

	}
	
	public void typeClick(WebDriver driver) throws Exception
	{ 
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Type Combobox was not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(getHtml("typeCombo"))).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at type ComboBox displayed validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
			driver.findElement(By.xpath(getHtml("typeCombo"))).click();
		} catch (Exception e) 
		{
			System.out.println("Exception at type ComboBox click validation : "+e);
		}

	}
	
	public void writeLocation(WebDriver driver, String location) throws Exception
	{
		Actions act = new Actions(driver);
		Action enter = act.sendKeys(Keys.ENTER).build();
		
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Location Box was not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(getHtml("locationPicker"))).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at Location Box displayed validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
			driver.findElement(By.xpath(getHtml("locationPicker"))).click();
		} catch (Exception e) 
		{
			System.out.println("Exception at Location Box click validation : "+e);
		}
		
		Thread.sleep(1000);

	    
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Location Input box was not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(getHtml("locationInput"))).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at Location Input Box displayed validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
		    driver.findElement(By.xpath(getHtml("locationInput"))).clear();
		    driver.findElement(By.xpath(getHtml("locationInput"))).sendKeys(location);
		} catch (Exception e) 
		{
			System.out.println("Exception at Location Input Box : "+e);
		}
  	  	
		Thread.sleep(3000);
		
		enter.perform();
		
	}
	
	public void selectCorrectOptionInCombo(WebDriver driver, String searchText, String combo) throws Exception
	{
		String currText="curr";
		String prevText="prev";
  	  	Actions act = new Actions(driver);
  	  	Action down = act.sendKeys(Keys.ARROW_DOWN).build();
  	  	Action enter = act.sendKeys(Keys.ENTER).build();
  	  	
  	  	for(;;)
  	  	{
	  	  	if(combo.equals("imo"))
	  	  	{
	  	  		currText = driver.findElement(By.xpath(getHtml("imoCombo")+getHtml("rendered"))).getText();
	  	  		if(currText.equals(prevText))
	  	  		{
	  	  			fail("The wanted imo("+searchText+") was not found!");
	  	  		}
	  	  		
		  	  	if(currText.equals(searchText))
		  	  	{
		  	  		break;
		  	  	}
		  	  	else
		  	  	{
		  	  		imoClick(driver);
		  	  	}
	  	  	}
	  	  	else
	  	  		if(combo.equals("type"))
	  	  		{
	  	  			currText = driver.findElement(By.xpath(getHtml("typeCombo")+getHtml("rendered"))).getText();
	  	  	  		if(currText.equals(prevText))
		  	  		{
		  	  			fail("The wanted type("+searchText+") was not found!");
		  	  		}
	  	  	  		
			  	  	if(currText.equals(searchText))
			  	  	{
			  	  		break;
			  	  	}
			  	  	else
			  	  	{			  	  		
			  	  		typeClick(driver);			  	  		
			  	  	}
	  	  		}
	  	  	
  	  		down.perform();
  	  		enter.perform();
  	  		prevText=currText;
  	  		
	  	  	Thread.sleep(1000);
  	  	}
	}
	
	public void validateElementDisplayed(WebDriver driver, String element) throws Exception
	{
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Element("+element+") not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(element)).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at element("+element+") validation : "+e);
			}
			
			Thread.sleep(1000);
		}
	}
	
	public void validateElementDisplayedWithText(WebDriver driver, String element, String text) throws Exception
	{
		String currText="curr";
		
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Element("+element+") not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(element)).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at element("+element+") validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
			
			currText = driver.findElement(By.xpath(element)).getText();
		} catch (Exception e) 
		{
			System.out.println("Exception validating element("+element+") text : "+e);
		}
		
		if(!currText.contains(text))
		{
			fail("Element("+element+") does not have the correct text, it has "+currText);
		}
		
		
		
	}
	
	public void validateStringElementDisplayedAndClick(WebDriver driver, String element) throws Exception
	{
		
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Element("+element+") not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(element)).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at element("+element+") validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
			driver.findElement(By.xpath(element)).click();
		} catch (Exception e) 
		{
			System.out.println("Exception validating element("+element+") text : "+e);
		}
		
		
	}
	
	public void validateWebElementDisplayedAndClick(WebDriver driver, WebElement element) throws Exception
	{
		
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Web Element("+element+") not found");
			}
		   	
			try 
			{
				if (element.isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at Web element("+element+") validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
			element.click();
		} catch (Exception e) 
		{
			System.out.println("Exception validating Web element("+element+") text : "+e);
		}
		
		
	}
	
	
	public void ifCookiesDisplayedClose(WebDriver driver) throws Exception
	{
		boolean displayed = false;
	    String actualTab = driver.getWindowHandle();
	    
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				break;
			}
		   	
			try 
			{
				if (driver.findElement(By.xpath(getHtml("cookiesBarClose"))).isDisplayed())
				{
					displayed = true;
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at Web element("+getHtml("cookiesBarClose")+") validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		if(displayed== true)
		{
			try 
			{ 
				driver.findElement(By.xpath(getHtml("cookiesBarClose"))).click();
			} catch (Exception e) 
			{
				System.out.println("Exception validating Web element("+getHtml("cookiesBarClose")+") text : "+e);
			}
		}

	    for(String tab : driver.getWindowHandles()) 
	    {
	        if (!tab.equals(actualTab)) 
	        {
	            driver.switchTo().window(tab);
	            driver.close();
	        }
	    }

	    driver.switchTo().window(actualTab);
		
	}
	
	
	public WebElement[] returnWebElementTable(WebDriver driver, String element, int from, int to) throws Exception
	{
		//Be aware to element 0 when configuring from and to
		int wElementLength = to - from +1;
		int count = 0;
		WebElement webElements[] = new WebElement[wElementLength];
		
		for(int i=from;i<=to;i++)
		{		
			webElements[count] = driver.findElements(By.xpath(element)).get(i);
			count=count+1;
		}
		
		return webElements;		
		
	}
	
	public int findElementUsingPageUpDown(WebDriver driver, String element) throws Exception
	{	
		
		int numEle = 0;
		Actions act = new Actions(driver);
		Action pageDown = act.sendKeys(Keys.PAGE_DOWN).build();	  	
		Action pageUp = act.sendKeys(Keys.PAGE_UP).build();
		boolean down = true;
		
		for (int second = 0;; second++) 
		{
			try 
			{ 
				if (driver.findElement(By.xpath(element)).isDisplayed())
				{
					numEle = driver.findElements(By.xpath(element)).size();
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at element("+element+") validation : "+e);
			}
			
			try 
			{ 
				if (driver.findElement(By.xpath(getHtml("copyright"))).isDisplayed())
				{
					down=false;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at element("+element+") validation : "+e);
			}
			
			if(down==true)
			{
				pageDown.perform();
			}
			else
			{
				pageUp.perform();
			}		
			
			if(second == 100)
			{
				fail("Element("+element+") was not found(Page Down, Page Up)!");
			}
			Thread.sleep(1000);
			
		}
		
		return numEle;
	}
	
	public int getElementTableSize(WebDriver driver, String element) throws Exception
	{
		List<WebElement> rows = null;
		int size;
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Element("+element+") not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(element)).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at element("+element+") validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
			rows = driver.findElements(By.xpath(element));
		} catch (Exception e) 
		{
			System.out.println("Exception validating element("+element+") text : "+e);
		}
		
		size = rows.size();
		
		return size;
		
		
	}
	
	public String getOfferParameter(WebDriver driver, String parameter) throws Exception
	{
		String text = null;
		for (int second = 0;; second++) 
		{
			if (second >= 10)
			{
				fail("Element("+getHtml("offerParameters")+"//li[contains(text(),'"+parameter+"')]//strong) not found");
			}
		   	
			try 
			{ 
				if (driver.findElement(By.xpath(getHtml("offerParameters")+"//li[contains(text(),'"+parameter+"')]//strong")).isDisplayed())
				{
					break;
				}
			} catch (Exception e) 
			{
				System.out.println("Exception at element("+getHtml("offerParameters")+"//li[contains(text(),'"+parameter+"')]//strong) validation : "+e);
			}
			
			Thread.sleep(1000);
		}
		
		try 
		{ 
			text = driver.findElement(By.xpath(getHtml("offerParameters")+"//li[contains(text(),'"+parameter+"')]//strong")).getText();
		} catch (Exception e) 
		{
			System.out.println("Exception validating element("+getHtml("offerParameters")+"//li[contains(text(),'"+parameter+"')]//strong) text : "+e);
		}
		
		
		return text;
		
	}
	
	public String getResponseMsgId(String msg) throws Exception
	{
		String[] strs = null;
		try
		{
			strs = msg.split("id\":\"");
				
			strs = strs[1].split("\"");
		}
		catch(Exception e)
		{
			System.out.println("An exception was thrown while checking response message -> "+e);
			strs[0]="Exception";
		}
		
		return strs[0];
		
	}

}
