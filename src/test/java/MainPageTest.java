import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainPageTest extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void checkIsLogin() {
        if (app.getHelperLogin().validateLoginSuccess2()) { // will be res = true or res=false
            app.getHelperLogout().logout();
        } else {
            app.navigateToMainPage();
        }
    }

    @Test
    public void testTitleH1() {
        Assert.assertTrue(app.getHelperMainPage().validateH1Correct());
    }

}
