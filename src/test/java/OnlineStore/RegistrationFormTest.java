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


public class RegistrationFormTest extends BaseTest {

    final static By USER_PROFILE_ICON = By.xpath("//button[contains(@class, 'user__actions-profile')]");

    final static By REGISTRATION_FORM_SECTION = By.xpath("//button[text() = 'Реєстрація']");

    final static By EMAIL_INPUT_FIELD = By.xpath("//label[text() = 'Email*']/following-sibling::div[1]/input[@name= 'email']");

    final static By EMAIL_FIELD_VALIDATION_MESSAGE = By.xpath("//label[text() = 'Email*']/following-sibling::div[2]");

    final static By PASSWORD_INPUT_FIELD = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/input[@name= 'password']");

    final static By SHOW_PASSWORD_ICON = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]//button");

    final static By PASSWORD_FIELD_VALIDATION_MESSAGE = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/p");

    final static By FIRST_NAME_INPUT_FIELD = By.xpath("//label[text() = 'Ім’я*']/following-sibling::div[1]/input[@name= 'firstName']");

    final static By LAST_NAME_INPUT_FIELD = By.xpath("//label[text() = 'Прізвище*']/following-sibling::div[1]/input[@name= 'lastName']");

    final static By FIRST_NAME_VALIDATION_MESSAGE = By.xpath("//label[text() = 'Ім’я*']/following-sibling::div[1]/p");

    final static By LAST_NAME_VALIDATION_MESSAGE = By.xpath("//label[text() = 'Прізвище*']/following-sibling::div[1]/p");

    @Ignore
    @Test
    public void testEmailFieldWithValidData() {

        List<String> validEmailsList = TestUtils.VALID_EMAILS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

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
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }

    @Ignore
    @Test
    public void testPasswordFieldWithValidData() {

        List<String> validPasswordsList = TestUtils.VALID_PASSWORDS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Ignore
    @Test
    public void testPasswordFieldWithInvalidData() {

        List<String> invalidPasswordsList = TestUtils.INVALID_PASSWORDS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }

    @Ignore
    @Test
    public void testFirstNameFieldWithValidData() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, FIRST_NAME_INPUT_FIELD, FIRST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Ignore
    @Test
    public void testFirstNameFieldWithInvalidData() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, FIRST_NAME_INPUT_FIELD, FIRST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }

    @Ignore
    @Test
    public void testLastNameFieldWithValidData() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, LAST_NAME_INPUT_FIELD, LAST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Ignore
    @Test
    public void testLastNameFieldWithInvalidData() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, LAST_NAME_INPUT_FIELD, LAST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }
}
