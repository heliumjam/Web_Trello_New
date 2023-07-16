import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInTests extends TestBase{

UserDTO userDtoPositive = UserDTO.builder()
        .email("dykestones@gmail.com")
        .password("12131415$Aa")
        .build();

    @Test
    public void loginTestPositive() {
        app.getHelperLogin().login(userDtoPositive, app.getWait());
        Assert.assertTrue(app.getHelperLogin().validateLoginSuccess());
    }

    @Test
    public void loginTestPasswordInput() {
        logger.info("start test loginTestPasswordInput");
        app.getHelperLogin().openLoginPage();
        app.getHelperLogin().enterEmailLogin(userDtoPositive);
        app.getHelperLogin().clickContinueLogin();
        Assert.assertTrue(app.getHelperLogin()
                .validatePasswordInputEnable(app.getWait()));
    }

}
