package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;


public class ForgotPasswordFormTest extends BaseTest {

    final static By USER_PROFILE_ICON = By.xpath("//button[contains(@class, 'user__actions-profile')]");

    final static By FORGOT_PASSWORD_LINK = By.xpath("//button[text() = 'Забули пароль?']");

    final static By EMAIL_INPUT_FIELD = By.xpath("//label[text() = 'Email']/following-sibling::div[1]/input[@name= 'email']");

    final static By EMAIL_FIELD_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Email']/following-sibling::div[1]/p");

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
        getWait10().until(ExpectedConditions.elementToBeClickable(FORGOT_PASSWORD_LINK)).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Ignore
    @Test
    public void emailFieldWithInvalidDataTest() {

        List<String> invalidEmailsList = List.of("test1@почта.уа", ".test1@auto1.com", "test1.@auto.com",
                "test..1@auto.com", "test1@-test.com", "test1@auto.com-", "test1@auto..com", "test1@.auto.com",
                "test1@auto.com.", "test1auto.com", "@auto.com", "  test@auto.com  ", "test1@", "test1@autocom",
                "fyghyjghjhijijkijodfhddfhkjkookkdhddhddtloklkfhfhhkljkgtfjfjfjfh@jbhggffffffffkfgfffffffffffffgggggghjjbjnghfcgmhlhbjnjgyufygygyg.com",
                "@test.com", "test1@", "test1@auto", "fyghyjghjhijijkijodfhddfhkjkookkdhddhddtloklkfhfhhkljkgtfjfjfjhgh@hgjgkg.com",
                "acvb@b.c", "te(st)@auto.ua", "te[st]@auto.ua", "te<st>@auto.ua", "te;st@auto.ua", "te,st@auto.ua",
                "te t@auto.ua", "test@@auto.ua", "te st@auto.ua", "test@auto.ru", "       ");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(FORGOT_PASSWORD_LINK)).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }
}
