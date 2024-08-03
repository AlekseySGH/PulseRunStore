package OnlineStore;

import OnlineStore.runner.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class LoginFormTest extends BaseTest {

    final static By USER_PROFILE_ICON = By.xpath("//button[contains(@class, 'user__actions-profile')]");

    final static By EMAIL_INPUT_FIELD = By.xpath("//label[text() = 'Email*']/following-sibling::div[1]/input[@name= 'email']");

    final static By EMAIL_FIELD_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Email*']/following-sibling::div[1]/p");

    @Ignore
    @Test
    public void emailFieldWithValidDataTest() {

        List<String> validEmailsList = List.of("test1@auto.com", "test1@auto-1.com", "test_1@auto.com",
                "test+1@auto.com", "test~1@auto.com", "test1@auto_1.com", "TEST1@AUTO.COM", "test-1@auto.com",
                "test@auto1.com", "test.1@auto.com", "test1@auto.example.com",
                "test1test1test1test1test1test1test1te@exampleexampleexamplee.com", "a@b.co", "test`@auto.com",
                "test!#@auto.com", "test#$@auto.com", "test$%&@auto.com", "test%@auto.com", "test&@auto.com",
                "test'@auto.com", "test*@auto.com", "test+@auto.com", "test-@auto.com", "test/@auto.com",
                "test=@auto.com", "test?@auto.com", "test^@auto.com", "test_@auto.com", "test{@auto.com",
                "test|@auto.com", "test}@auto.com", "test~@auto.com");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();

        List<String> notAcceptedMailList = new ArrayList<>();
        boolean noValidationMassage = true;

        for (int i = 0; i < validEmailsList.size(); i++) {
            getDriver().findElement(EMAIL_INPUT_FIELD).sendKeys(validEmailsList.get(i));
            getDriver().findElement(EMAIL_INPUT_FIELD).submit();

            try {
                if (getDriver().findElement(EMAIL_FIELD_VALIDATION_MASSAGE).isDisplayed()) {
                    notAcceptedMailList.add(validEmailsList.get(i));
                    noValidationMassage = false;
                }
            } catch (NoSuchElementException ignored) {

            }

            getDriver().findElement(EMAIL_INPUT_FIELD).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
        String notAcceptedMailMassage = String.join("\n", notAcceptedMailList + " - Не принято системой");
        Assert.assertTrue(noValidationMassage, notAcceptedMailMassage);
    }
}
