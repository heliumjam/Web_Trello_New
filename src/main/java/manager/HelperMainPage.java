package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperMainPage extends HelperBase {

    public HelperMainPage(WebDriver driver) {
        super(driver);
    }

    public By TITLE_H1 = By.xpath("//h1");

    public boolean validateH1Correct() {
        return driver.findElement(TITLE_H1)
                .getText().toUpperCase().trim()
                .contains("teammates".toUpperCase());
    }
}
