import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import java.lang.reflect.Method;


public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void stop(){
        app.tearDown();
    }

    @BeforeMethod(alwaysRun = true)
    public void startLogger(Method method){
        logger.info("Method " + method.getName() + " is started");
    }

    @AfterMethod(alwaysRun = true)
    public void end(){
        logger.info("==================================");
    }

}

