package OnlineStore;

import OnlineStore.runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class LoginFormTest extends BaseTest {

    final static By USER_PROFILE_ICON = By.xpath("//button[contains(@class, 'user__actions-profile')]");

    final static By EMAIL_INPUT_FIELD = By.xpath("//label[text() = 'Email*']/following-sibling::div[1]/input[@name= 'email']");

    @Test
    public void emailFieldWithValidDataTest() {

        List<String> validEmailsList = List.of("test1@auto.com", "test1@auto-1.com", "test_1@auto.com",
                "test+1@auto.com", "test~1@auto.com", "test1@auto_1.com", "TEST1@AUTO.COM", "test-1@auto.com",
                "test@auto1.com", "test.1@auto.com", "test1@auto.example.com",
                "test1test1test1test1test1test1test1te@exampleexampleexamplee.com", "a@b.co", "Zz1!#$%&'*+-/=?^_.{|}~`@auto.ua");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();

        for (int i = 0; i < validEmailsList.size(); i++) {
            getDriver().findElement(EMAIL_INPUT_FIELD).sendKeys(validEmailsList.get(i));
            getDriver().findElement(EMAIL_INPUT_FIELD).submit();
            getDriver().findElement(EMAIL_INPUT_FIELD).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
    }
}
