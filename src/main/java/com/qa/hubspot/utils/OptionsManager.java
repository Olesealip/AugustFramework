package com.qa.hubspot.utils;


import java.util.Properties;

	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.firefox.FirefoxOptions;

	public class OptionsManager {
		//we declare this at the class level so that i can access anytime
		Properties prop;
		ChromeOptions co;
		FirefoxOptions fo;
		
		public OptionsManager(Properties prop){
			this.prop = prop;
		}
		//we will define one method fpor Chrome 
		public ChromeOptions getChromeOptions(){ //it m eans give me the prop options 
			
			//we need chropmr option class
			co = new ChromeOptions();
			
			//now, which arguments i have to add
			if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");   //we accesesed config properties -  we need to convert this from string into a boolean - we need to parsed//
			// we need to wrap it . so we use boolean wraper class (Boolean.parseBoolean)
			if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
			
			//when is done you return this:
			return co;
		}
		
		public FirefoxOptions getFirefoxOptions(){
			fo = new FirefoxOptions();
			if(Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless");
			return fo;
		}
		
	}


