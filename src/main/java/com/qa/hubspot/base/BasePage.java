 package com.qa.hubspot.base;
 


 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.util.Properties;
 import java.util.concurrent.TimeUnit;

 import org.apache.commons.io.FileUtils;
 import org.openqa.selenium.OutputType;
 import org.openqa.selenium.TakesScreenshot;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.chrome.ChromeDriver;
 import org.openqa.selenium.firefox.FirefoxDriver;
 import org.openqa.selenium.safari.SafariDriver;

 import com.qa.hubspot.utils.OptionsManager;

 import io.github.bonigarcia.wdm.WebDriverManager;

 public class BasePage {

 	public WebDriver driver;
 	public Properties prop;
 	public OptionsManager optionsManager;
 	
 	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

 	/**
 	 * This method is used to initialize the driver on the basis of given browser name
 	 * @param browserName
 	 * @return driver
 	 */
 	public WebDriver init_driver(Properties prop) {
 		
 		String browserName = prop.getProperty("browser").trim();

 		System.out.println("browser name is : " + browserName);
 		optionsManager = new OptionsManager(prop);

 		if (browserName.equalsIgnoreCase("chrome")) {
 			WebDriverManager.chromedriver().setup();
 			//driver = new ChromeDriver(optionsManager.getChromeOptions());
 			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
 		} else if (browserName.equalsIgnoreCase("firefox")) {
 			WebDriverManager.firefoxdriver().setup();
 			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
 			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
 		} else if (browserName.equalsIgnoreCase("safari")) {
 			WebDriverManager.getInstance(SafariDriver.class).setup();
 			//driver = new SafariDriver();
 			tlDriver.set(new SafariDriver());
 		} else {
 			System.out.println(browserName + " is not found, please pass the correct browser....");
 		}

 		getDriver().manage().deleteAllCookies();
 		getDriver().manage().window().maximize();
 		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

 		getDriver().get(prop.getProperty("url"));

 		return getDriver();
 	}
 	
 	
 	public static synchronized WebDriver getDriver(){
 		return tlDriver.get();
 	}
 	
 	

 	/**@author NaveenKhunteta
 	 * this method is used to initialize the properties from config.properties file
 	 * @return prop
 	 */
 	public Properties init_prop() {
 		prop = new Properties();
 		String path = null;
 		String env = null;
 		
 		try {
 			env = System.getProperty("env");
 			System.out.println("Running on Environment: ---->" + env);
 			if(env == null){
 				path = "./src/main/java/com/qa/hubspot/config/config.properties";
 			}
 			else{
 				switch (env) {
 				case "qa":
 					path = "./src/main/java/com/qa/hubspot/config/qa.config.properties";
 					break;
 				case "dev":
 					path = "./src/main/java/com/qa/hubspot/config/dev.config.properties";
 					break;
 				case "stage":
 					path = "./src/main/java/com/qa/hubspot/config/stage.config.properties";
 					break;
 				default:
 					System.out.println("Please pass the correct env value....");
 					break;
 				}
 			}
 			FileInputStream ip = new FileInputStream(path);
 			prop.load(ip);
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}

 		return prop;
 	}
 	
 	//take screenshot:
 	public String getScreenshot(){
 		File src  = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
 		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
 		File destination = new File(path);
 		try {
 			FileUtils.copyFile(src, destination);
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		return path;
 	}
 	

 }


//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//import javax.sql.rowset.spi.SyncResolver;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
//
//import com.qa.hubspot.utils.OptionsManager;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class BasePage {
//public WebDriver driver;//we created reference for Webdriver
//public Properties prop;
//public OptionsManager optionsManager; // so we created an object so we can call the methods from objectMsanager.
//
//public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); //give the generics of webdriver
///**
// * This method is used to initialize the driver on the basis of given browser name
// * @param browserName
// * @return
// */
//
//public WebDriver init_driver(Properties prop) { //this method says you give me the browser name and i will give you the driver
//String browserName= prop.getProperty("browser").trim();
//	System.out.println("browser name is : " + browserName);
//  
//	optionsManager= new OptionsManager(prop);
//	
//	
//	if(browserName.equalsIgnoreCase("chrome"))	{
//	WebDriverManager.chromedriver().setup();
//	 //driver= new ChromeDriver(optionsManager.getChromeOptions());
//	
//	 tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions())); // set says you give me the webdriver values 
//	}
//   else if(browserName.equalsIgnoreCase("firefox")) {
//		
//		WebDriverManager.firefoxdriver().setup();
//		// driver= new FirefoxDriver(optionsManager.getFirefoxOptions());
//		 
//		 tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions())); 
//		}
//	
//	else if (browserName.equalsIgnoreCase("safari")) {
//		
//	WebDriverManager.getInstance(SafariDriver.class).setup();
//	//driver= new SafariDriver();
//	
//	 tlDriver.set(new SafariDriver());
//	}
//	else {
//		System.out.println(browserName + " is not found please pass the correct browser...");
//		}
//	
//	getDriver().manage().deleteAllCookies();
//	getDriver().manage().window().maximize();
//	getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //later on we will remove implicitly wait we will never use implicitly wait in our framework PPOM_2 31:28
//	
//	getDriver().get(prop.getProperty("url"));
//	return getDriver( );
//}
//
//public static synchronized WebDriver getDriver() {
//	return tlDriver.get();
//}
///**
// *this method is used to initialize the properties from config.properties file  
// * @return
// */
////we wil create a separate method initilize the property which will interact with config.properties (make a connection between property and config. properties )
//
//  public Properties init_prop() { // wnen ever you have to interact with config files you have to create an object of properties class after you have to create object file imput stream class and make the connection to the file 
////	  once the connection has been established you  simple load the properties (all the properties will be stored inside the prop object )
//	prop= new Properties ();
//	String path= null;
//	String env= null;
//	
//	try {
//		
//	    env= System.getProperty(env);  //will read the property from the run time// it means on whic environment you want to run it  
//		System.out.println("Running on Environment ----->" + env);
//	    if(env == null) {//if im not passing anything 
//	    path =	"./ src/main/java/com/qa/hubspot/config/config.properties"; // then you take the properties from "./src/main/java/com/qa/hubspot/config/config.properties"
//	    	 }
//	    
//	    else {
//	    	switch (env) {
//			case "qa":
//				path =	"./ src/main/java/com/qa/hubspot/config/qa.config.properties";
//				break;
//			case "dev":
//				path =	"./ src/main/java/com/qa/hubspot/config/dev.config.properties";
//				break;
//			case "stage":
//				path =	"./ src/main/java/com/qa/hubspot/config/stage.config.properties";
//				break;
//				
//          default:
//        	  System.out.println("Please pass the correct env value....");
//				break;
//			}
//	    	
//	    }
//	    
//	    FileInputStream ip= new FileInputStream(path); //(file)- you have to give the path of the file
//	prop.load(ip);
//		
//	}catch (FileNotFoundException e) {
//	
//		e.printStackTrace();
//	
//} catch(IOException e) {
//	e.printStackTrace();
//   }
//  return prop;
//
//}
////take screenshot :
//  
//public String getScreenshot() {
//	//first get the driver:
//	File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); //(OutputType.FILE)- which type you want to generate? file type 
//	//first you get the driver convert into screenshot and then we wil give you a method getScreenshotAs. this method will take the screenshot . it will generate a screenshot i form of file . Src - is file object reference 
//
//   String path= System.getProperty("user.dir")+"/screenshots/" +System.currentTimeMillis()+ ".png";  //user.dir - it means user directory . it means give me exactly in which project you are , give me 5the location
//// first you go to this particular director -("user.dir"). Creater one - /screenshots/ - folder and take the screenshot whith current time stamp .png //we want to give an uniq name of the file we can pass some timestamp 
////curent screenshot is available here :File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
////you cannot transfer file to string so we have to create one destination file :
//   File destination = new File(path);
//   
//   try {
//	FileUtils.copyFile(src, destination);
//} catch (IOException e) {
//	e.printStackTrace();
//}
//	//now my screenshot is available in path variable so return path:
//	return path;
//}
//}



