package com.menonvarun.test.automation.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.menonvarun.test.automation.framework.config.DefaultConfig;

/**
 * In-build driver provider class which creates and returns the driver object based on the property 
 * <b><i>test.name</i></b> inside <b><i>taf.properties</i></b> file.
 * @author  Varun Menon
 *
 */
class InbuiltDriverProvider implements IDriverProvider{
	DefaultConfig config = DefaultConfig.getDefaultConfig();
	
	@Override
	public WebDriver getDriver() {		
		String driverName = config.getConfigValue("driver.name");
		return getInbuildDriver(driverName);
	}
	
	private WebDriver getInbuildDriver(String driverName){
		WebDriver driver = null;
		switch (driverName) {
		case "firefox":
			driver = new FirefoxDriver();			
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "htmlunit":
			driver = new HtmlUnitDriver();
			break;
		default:
			throw new DriverNotAvailableException("Driver name: " + driverName +" set to the property 'driver.name' is not supported");			
		}
		return driver;
	}
	
	@SuppressWarnings("serial")
	private class DriverNotAvailableException extends RuntimeException{
		public DriverNotAvailableException(String message){
			super(message);
		}
	}

}
