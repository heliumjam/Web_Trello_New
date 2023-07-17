package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelperBase {

    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

//    public void type(By locator, String text) {
//        WebElement element = wd.findElement(locator);
//
////        ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(locator);
////        int size = list.size();
////        list.get(size).clear();
//
//        //element.click();
//        element.clear();
//        element.sendKeys(text);
//    }


    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // add for WD listener also need to create folder screenshots in the folder test - if not exist

     public void takeScreenshot(String link){
        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File(link);
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void type (By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void changeImplicitlyTime(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
	
    public boolean isElementEnable(By locator) {
// first solution
		// WebElement element = null;
		// 	try {
		// 		element = driver.findElement(locator);
		// 	}
		// catch(Exception e) {
		// 		return false;
		// }
		// return element.isEnabled();
//___________________________________________________________________________
        // second solution
	changeImplicitlyTime(0); //TODO
        // here need to be carefully and to be sure that we get Implicitly Time back to 60 seconds
        // and to use it only - when we know that the page downloaded and we do not need to wait it
        // get run time for all tests 41 seconds, without changing -> 2 m 42 sec
        List<WebElement> elements = driver.findElements(locator);
        changeImplicitlyTime(60);
        return elements.size() != 0;
	
	}
	
	    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }
	
}
