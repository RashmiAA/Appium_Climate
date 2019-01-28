/*******************************************
	 * Script Name		: FarmRiseTestNg
	 * Author			: Rashmi
	 * Date Created		: 28/01/2019
	 * Date Reviewed	: NA
	 * Program used		: Core Java
	 * Application Name : Climate Farm Rise
	 * Approach used	: Linear Framework designing
	 ******************************************* 
	 */

package farmRise;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.testng.annotations.BeforeTest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class FarmRiseTestNg {	

	
	@BeforeTest
	public void beforeTest() {
		public static DesiredCapabilities caps = null;
		public static AndroidDriver<AndroidElement> driver = null;
		public static Dimension dim = null;
		public static Dimension size1 = null;
	  }
			
	@Test
	public void MyFirstTestNg() {
	
		try {		
			
			caps = new DesiredCapabilities();
			caps.setCapability("automationName","Appium");
			caps.setCapability("deviceName", "GUROOVAMLNRGUGJ7");//Device Name
			caps.setCapability("platformName", "Android");
			caps.setCapability("platformVersion", "6.0");
			
			//launching the farmrise application by providing package name and activity name
			caps.setCapability("appPackage", "com.climate.farmrise");
			caps.setCapability("appActivity", "com.climate.farmrise.SplashScreen");
			caps.setCapability("noReset", "true");
					
			driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//wait = new WebDriverWait(appiumDriver, 30);
					
			boolean HomeScreen = driver.findElement(By.id("com.climate.farmrise:id/homeScreen_toolBar_Title")).isDisplayed();
			
		
			//Verifying the scenario1 : Home screen->Access Weather details->timings in the horizantal scroll from "now" to 23 hours
			driver.findElement(By.id("com.climate.farmrise:id/checkWeatherDetails")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
					
			dim = driver.manage().window().getSize();
			int height = dim.getHeight();
			int width = dim.getWidth();
			//Here the dimensions are provided wrt to my test device
			int n=1;
			while(n<=5)
			{
			
			driver.swipe(660, 1000, 60, 1000, 3000);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			n=n+1;
			}
			
			System.out.println("Scenario1 completed");

			//Verifying the scenario2 : More Tab->Government schemes->scroll till load more schemes and tapping on search and input "scheme" 
			driver.findElement(By.id("com.climate.farmrise:id/action_more")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("com.climate.farmrise:id/more_govtSchemes")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
				
			size1 = driver.manage().window().getSize();
			System.out.println(size1);
			int starty = (int)(size1.height*0.8);
			int endy = (int)(size1.height *0.1);
			int startx = size1.width/2;
			int endx = size1.width/2;
			
			driver.swipe(startx,starty,endx,endy, 5000);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			
			//Scrolled till button but dint find "Load more schemes button" hence could not verify it
			
			driver.findElement(By.id("android:id/search_button")).click();
			
			//Entering the text "scheme" in the search field
			driver.findElement(By.id("android:id/search_src_text")).sendKeys("scheme");
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Scenario2 completed");
			System.out.println("**********************");

		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
  

  @AfterTest
  public void afterTest() {
	 driver.closeApp();
	 
  }

}
