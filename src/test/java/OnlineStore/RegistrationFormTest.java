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

    final static By EMAIL_FIELD_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Email*']/following-sibling::div[1]/p");

    final static By PASSWORD_INPUT_FIELD = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/input[@name= 'password']");

    final static By SHOW_PASSWORD_ICON = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]//button");

    final static By PASSWORD_FIELD_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/p");

    final static By FIRST_NAME_INPUT_FIELD = By.xpath("//label[text() = 'Ім’я*']/following-sibling::div[1]/input[@name= 'firstName']");

    final static By LAST_NAME_INPUT_FIELD = By.xpath("//label[text() = 'Прізвище*']/following-sibling::div[1]/input[@name= 'lastName']");

    final static By FIRST_NAME_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Ім’я*']/following-sibling::div[1]/p");

    final static By LAST_NAME_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Прізвище*']/following-sibling::div[1]/p");

    @Ignore
    @Test
    public void emailFieldWithValidDataTest() {

        List<String> validEmailsList = TestUtils.VALID_EMAILS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Ignore
    @Test
    public void emailFieldWithInvalidDataTest() {

        List<String> invalidEmailsList = TestUtils.INVALID_EMAILS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }

    @Ignore
    @Test
    public void passwordFieldWithValidDataTest() {

        List<String> validPasswordsList = TestUtils.VALID_PASSWORDS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Ignore
    @Test
    public void passwordFieldWithInvalidDataTest() {

        List<String> invalidPasswordsList = TestUtils.INVALID_PASSWORDS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }

    @Ignore
    @Test
    public void firstNameFieldWithValidDataTest() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, FIRST_NAME_INPUT_FIELD, FIRST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Ignore
    @Test
    public void firstNameFieldWithInvalidDataTest() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, FIRST_NAME_INPUT_FIELD, FIRST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }

    @Ignore
    @Test
    public void lastNameFieldWithValidDataTest() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, LAST_NAME_INPUT_FIELD, LAST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Ignore
    @Test
    public void lastNameFieldWithInvalidDataTest() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(REGISTRATION_FORM_SECTION)).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, LAST_NAME_INPUT_FIELD, LAST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }
}
