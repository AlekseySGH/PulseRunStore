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

    final static By EMAIL_FIELD_VALIDATION_MESSAGE = By.xpath("//label[text() = 'Email']/following-sibling::div[1]/p");

    @Ignore
    @Test
    public void testEmailFieldWithValidData() {

        List<String> validEmailsList = TestUtils.VALID_EMAILS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(FORGOT_PASSWORD_LINK)).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Ignore
    @Test
    public void testEmailFieldWithInvalidData() {

        List<String> invalidEmailsList = TestUtils.INVALID_EMAILS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(FORGOT_PASSWORD_LINK)).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }
}
