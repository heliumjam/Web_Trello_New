package manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    EventFiringWebDriver driver;
    HelperLogin helperLogin;
    WebDriverWait wait;

    HelperLogout helperLogout;

    public WebDriverWait getWait() {
        return wait;
    }

    public HelperLogin getHelperLogin(){
        return helperLogin;
    }

    public HelperLogout getHelperLogout(){ return helperLogout;}

    @BeforeSuite
    public void init(){
        //   driver = new ChromeDriver();
      //  driver = new EventFiringWebDriver(new ChromeDriver());

        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));

//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        WebDriverManager.firefoxdriver().setup();
//        driver = new EventFiringWebDriver(new FirefoxDriver(firefoxOptions));


        // driver.register(new WebDriverListener());
        helperLogin = new HelperLogin(driver);
        helperLogout = new HelperLogout(driver);
        driver.manage().window().maximize();
        driver.navigate().to("https://trello.com/");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 90);

//                WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement element =
//                wait.until(ExpectedConditions
////                        .alertIsPresent()
//                .visibilityOfElementLocated(By.id("elementId")));

    }


    @AfterSuite
    public void tearDown(){
       // driver.quit();
    }
}
