package manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

 // changed for WD listener
    EventFiringWebDriver driver;
	
    HelperLogin helperLogin;
 
	HelperLogout helperLogout;
 
    HelperMainPage helperMainPage;

    WebDriverWait wait;

    String browser;

	public ApplicationManager(String browser) {
			this.browser = browser;
		}

    public WebDriverWait getWait() {
        return wait;
		}

    public HelperLogin getHelperLogin(){
        return helperLogin;
		}
  
	public HelperLogout getHelperLogout() {return helperLogout;}

    public HelperMainPage getHelperMainPage() { return helperMainPage;}

    @BeforeSuite
    public void init(){
        //   driver = new ChromeDriver();
		//  driver = new EventFiringWebDriver(new ChromeDriver());

			// before  WD Listener:
			// ChromeOptions chromeOptions = new ChromeOptions();
			// WebDriverManager.chromedriver().setup();
			// driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));

			// FirefoxOptions firefoxOptions = new FirefoxOptions();
			// WebDriverManager.firefoxdriver().setup();
			// driver = new EventFiringWebDriver(new FirefoxDriver(firefoxOptions));

// changed for WD Listener:
       if(browser.equals(BrowserType.CHROME)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
        } else if (browser.equals(BrowserType.FIREFOX)) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            driver = new EventFiringWebDriver(new FirefoxDriver(firefoxOptions));
        }	


        driver.register(new WebDriverListener());
        helperLogin = new HelperLogin(driver);
        helperLogout = new HelperLogout(driver);
        helperMainPage = new HelperMainPage(driver);
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


    public void navigateToMainPage() {
        driver.navigate().to("https://trello.com/");
    }

 //   @AfterSuite
    public void tearDown(){
        driver.quit();
    }

}
