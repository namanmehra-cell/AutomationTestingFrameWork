package com.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pageobjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException

	{
		// properties class

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\Automation testing\\demo\\src\\main\\java\\com\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName =System.getProperty("browser")!=null?System.getProperty("browser"): prop.getProperty("browser");
		

		if (browserName.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			//ChromeDriver driver = new ChromeDriver(options);
			System.setProperty("webdriver.chrome.driver", "D:\\Automation testing\\demo\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver(options);

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// Firefox
		} else if (browserName.equals("msedge")) {
			// Edge
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	//@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonConvertToMap(String pathAddress) throws IOException{//"D:\\Automation testing\\SeleniumFrameworkDesign\\src\\test\\java\\com\\Data\\DataFile.json"
		 String jsonConvert=FileUtils.readFileToString(new File(pathAddress),StandardCharsets.UTF_8);
		 ObjectMapper obj = new ObjectMapper();
		 List<HashMap<String,String>> data = obj.readValue(jsonConvert, new TypeReference<List<HashMap<String,String>>>() {});
		 return data;
		 
	 }

	 public String getScreenShot(String testCaseName,WebDriver driver) throws IOException{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";

	 }
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	
		
	}
	
	@AfterMethod(alwaysRun=true)
	
	public void tearDown()
	{
		driver.close();
	}
}
