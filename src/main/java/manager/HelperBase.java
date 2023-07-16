package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HelperBase {
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

    public void takeScreenshot(String link) {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File(link);

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
    }
    public boolean isElementEnable(By locator) {
        return driver.findElement(locator).isEnabled();
    }
}
