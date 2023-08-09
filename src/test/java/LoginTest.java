import data.ProviderDataLogin;
import dto.UserDTO;
import manager.TestNgListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)

public class LoginTest extends TestBase{

    UserDTO userDtoPositive = UserDTO.builder()
            .email("dykestones@gmail.com")
            .password("12131415$Aa")
            .build();

    UserDTO userDtoFromProperties;

    @BeforeMethod(alwaysRun = true)
    public void checkIsLogin() {
        if(userDtoFromProperties == null) {
            userDtoFromProperties = UserDTO.builder()
                    .email(app.getEmail())
                    .password(app.getPassword())
                    .build();
        }
//        boolean res;
//        try {
//            res = app.getHelperLogin().validateLoginSuccess();
//        }
//        catch(Exception e) {
//            res = false;
//        }
        // if(true)   if(1==1)
        if (app.getHelperLogin().validateLoginSuccess2()) { // will be res = true or res=false
            app.getHelperLogout().logout();
        } else {
            app.navigateToMainPage();
        }
    }

    @Test(invocationCount = 2)
    public void loginTestPositive() {
        app.getHelperLogin().login(userDtoPositive, app.getWait());
        Assert.assertTrue(app.getHelperLogin().validateLoginSuccess());
    }

    @Test(groups = {"smoke"})
    public void loginTestPositiveProperties() {
        app.getHelperLogin().login(userDtoFromProperties, app.getWait());
        Assert.assertTrue(app.getHelperLogin().validateLoginSuccess());
    }

    @Test(groups = {"smoke"})
    public void loginTestPasswordInput() {
        logger.info("start test loginTestPasswordInput");
        app.getHelperLogin().openLoginPage();
      //  app.getHelperLogin().enterEmailLogin(userDtoPositive);
        app.getHelperLogin().enterEmailLogin(userDtoFromProperties);
        app.getHelperLogin().clickContinueLogin();
       // Assert.assertTrue(app.getHelperLogin().validatePasswordInputEnable(app.getWait()));
        WebDriverWait wait = app.getWait();
        Assert.assertTrue(app.getHelperLogin().validatePasswordInputEnable(wait));
        /*
        app.getWait()
        int varRes = 55;
        someFun(1, "str", varRes, callFunc() - return)
         */

    }
    @Test(groups = {"smoke"},
            dataProvider = "userDtoWrongPassword",
            dataProviderClass = ProviderDataLogin.class
            )
    public void loginWithIncorrectPassword(UserDTO userFromDataProvider) {
        app.getHelperLogin().login(userFromDataProvider, app.getWait());
        Assert.assertTrue(app.getHelperLogin().validatePasswordIncorrect());
    }

    @Test(dataProvider = "userDtoLogin", dataProviderClass = ProviderDataLogin.class)
    public void loginProviderData(UserDTO userFromDataProvider) {
        app.getHelperLogin().login(userFromDataProvider, app.getWait());
        Assert.assertTrue(app.getHelperLogin().validateLoginSuccess());
    }

    @Test(dataProvider = "userDtoCSV", dataProviderClass = ProviderDataLogin.class)
    public void loginTest(UserDTO userFromCSVFile) {
        app.getHelperLogin().login(userFromCSVFile, app.getWait());
        Assert.assertTrue(app.getHelperLogin().validateLoginSuccess());
    }

}
