package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperLogout extends HelperBase{
    public HelperLogout(WebDriver driver) {
        super(driver);
    }

    public By HEADER_MENU_BTN = By.xpath("//button[@data-testid='header-member-menu-button']");
    public By MENU_LOGOUT_BTN = By.xpath("//button[@data-testid='account-menu-logout']");

    public By LOGOUT_SUBMIT_BTN = By.xpath("//button[@id='logout-submit']");

    public void logout() {
        click(HEADER_MENU_BTN);
        click(MENU_LOGOUT_BTN);
        click(LOGOUT_SUBMIT_BTN);
    }
	
}
