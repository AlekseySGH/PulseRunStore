package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Ignore
public class UserPageTest extends BaseTest {

    final static By USER_PROFILE_ICON = By.xpath("//button[contains(@class, 'user__actions-profile')]");

    final static By EMAIL_INPUT_FIELD = By.xpath("//label[text() = 'Email']/following-sibling::div[1]/input[@name= 'email']");

    final static By EMAIL_FIELD_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Email']/following-sibling::div[1]/p");

    final static By PASSWORD_INPUT_FIELD = By.xpath("//label[text() = 'Пароль']/following-sibling::div[1]/input[@name= 'password']");

    final static By SHOW_PASSWORD_ICON = By.xpath("//label[text() = 'Пароль']/following-sibling::div[1]//button");

    final static By PASSWORD_FIELD_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Пароль']/following-sibling::div[1]/p");

    final static By FIRST_NAME_INPUT_FIELD = By.xpath("//label[text() = 'Ім’я']/following-sibling::div[1]/input[@name= 'firstName']");

    final static By LAST_NAME_INPUT_FIELD = By.xpath("//label[text() = 'Прізвище']/following-sibling::div[1]/input[@name= 'lastName']");

    final static By FIRST_NAME_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Ім’я']/following-sibling::div[1]/p");

    final static By LAST_NAME_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Прізвище']/following-sibling::div[1]/p");

    private static void signIn(WebDriver driver, WebDriverWait wait) {
        driver.findElement(By.xpath("//label[text() = 'Email*']/following-sibling::div[1]/input[@name= 'email']"))
                .sendKeys("test4@test.com");
        driver.findElement(By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/input[@name= 'password']"))
                .sendKeys("Qwerty123#");
        driver.findElement(By.xpath("//button[text() = 'Увійти']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
    }

    private static void logOut(WebDriver driver, WebDriverWait wait) {
//        wait.until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();

        driver.findElement(By.xpath("//label[text() = 'Email']/following-sibling::div[1]/input[@name= 'email']"))
                .sendKeys("test4@test.com");
        driver.findElement(By.xpath("//label[text() = 'Email']/following-sibling::div[1]/input[@name= 'email']")).submit();
        driver.findElement(By.xpath("//button/p[text() = 'Вихід']")).click();
    }

    @Test
    public void testEmailFieldWithValidData() {

        List<String> validEmailsList = TestUtils.VALID_EMAILS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        signIn(getDriver(), getWait10());

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        logOut(getDriver(), getWait10());

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Test
    public void testEmailFieldWithInvalidData() {

        List<String> invalidEmailsList = TestUtils.INVALID_EMAILS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        signIn(getDriver(), getWait10());

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        logOut(getDriver(), getWait10());

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }

    @Test
    public void testPasswordFieldWithValidData() {

        List<String> validPasswordsList = TestUtils.VALID_PASSWORDS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        signIn(getDriver(), getWait10());

        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        logOut(getDriver(), getWait10());

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Test
    public void testPasswordFieldWithInvalidData() {

        List<String> invalidPasswordsList = TestUtils.INVALID_PASSWORDS_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        signIn(getDriver(), getWait10());

        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        logOut(getDriver(), getWait10());

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }

    @Test
    public void testFirstNameFieldWithValidData() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        signIn(getDriver(), getWait10());

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, FIRST_NAME_INPUT_FIELD, FIRST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        logOut(getDriver(), getWait10());

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Test
    public void testFirstNameFieldWithInvalidData() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        signIn(getDriver(), getWait10());

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, FIRST_NAME_INPUT_FIELD, FIRST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        logOut(getDriver(), getWait10());

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }

    @Test
    public void testLastNameFieldWithValidData() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        signIn(getDriver(), getWait10());

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, LAST_NAME_INPUT_FIELD, LAST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        logOut(getDriver(), getWait10());

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Test
    public void testLastNameFieldWithInvalidData() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        signIn(getDriver(), getWait10());

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, LAST_NAME_INPUT_FIELD, LAST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        logOut(getDriver(), getWait10());

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }
}
