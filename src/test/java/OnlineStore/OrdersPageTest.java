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
import java.util.Random;

@Ignore
public class OrdersPageTest extends BaseTest {

    final static By EMAIL_INPUT_FIELD = By.xpath("(//label[text() = 'Email*']/following-sibling::div[1]/input[@name= 'email'])[2]");

    final static By EMAIL_FIELD_VALIDATION_MESSAGE = By.xpath("//label[text() = 'Email*']/following-sibling::div[1]/p");

    final static By PASSWORD_INPUT_FIELD = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/input[@name= 'password']");

    final static By SHOW_PASSWORD_ICON = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]//button");

    final static By PASSWORD_FIELD_VALIDATION_MESSAGE = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/p");

    final static By FIRST_NAME_INPUT_FIELD = By.xpath("//label[text() = 'Ім’я*']/following-sibling::div[1]/input[@name= 'firstName']");

    final static By LAST_NAME_INPUT_FIELD = By.xpath("(//label[text() = 'Прізвище*']/following-sibling::div[1]/input[@name= 'lastName'])[2]");

    final static By FIRST_NAME_VALIDATION_MESSAGE = By.xpath("//label[text() = 'Ім’я*']/following-sibling::div[1]/p");

    final static By LAST_NAME_VALIDATION_MESSAGE = By.xpath("//label[text() = 'Прізвище*']/following-sibling::div[1]/p");

    final static By REGISTRATION_FORM_SECTION = By.xpath("//p[text() = 'РЕЄСТРАЦІЯ']");

    final static By ADD_TO_CART_BUTTON = By.xpath("//button[text() = 'Додати в кошик']");

    final static By ORDER_BUTTON = By.xpath("//a[text() = 'Оформити']");

    final static By USER_DATA_FIRST_NAME_INPUT_FIELD = By.xpath(
            "//label[contains(text(), 'Ім')]/following-sibling::div[1]/input[@name= 'firstName']");

    final static By USER_DATA_FIRST_NAME_VALIDATION_MESSAGE= By.xpath(
            "//label[contains(text(), 'Ім')]/following-sibling::div[1]/p");
    private static void chooseRandomSize(WebDriver driver, WebDriverWait wait) {

        Random r = new Random();
        int i = r.nextInt(wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(TestUtils.SIZES_LIST_IN_PRODUCT_PAGE)).size());

        driver.findElements(TestUtils.SIZES_LIST_IN_PRODUCT_PAGE).get(i).click();
    }

    @Test
    public void testLoginFormEmailFieldWithValidData() {

        List<String> validEmailsList = TestUtils.VALID_EMAILS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Test
    public void testLoginFormEmailFieldWithInvalidData() {

        List<String> invalidEmailsList = TestUtils.INVALID_EMAILS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }

    @Test
    public void testLoginFormPasswordFieldWithValidData() {

        List<String> validPasswordsList = TestUtils.VALID_PASSWORDS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Test
    public void testLoginFormPasswordFieldWithInvalidData() {

        List<String> invalidPasswordsList = TestUtils.INVALID_PASSWORDS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }

    @Test
    public void testRegistrationFormEmailFieldWithValidData() {

        List<String> validEmailsList = TestUtils.VALID_EMAILS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Test
    public void testRegistrationFormEmailFieldWithInvalidData() {

        List<String> invalidEmailsList = TestUtils.INVALID_EMAILS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }

    @Test
    public void testRegistrationFormPasswordFieldWithValidData() {

        List<String> validPasswordsList = TestUtils.VALID_PASSWORDS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Test
    public void testRegistrationFormPasswordFieldWithInvalidData() {

        List<String> invalidPasswordsList = TestUtils.INVALID_PASSWORDS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }

    @Test
    public void testFirstNameFieldWithValidData() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, FIRST_NAME_INPUT_FIELD, FIRST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Test
    public void testFirstNameFieldWithInvalidData() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, FIRST_NAME_INPUT_FIELD, FIRST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }

    @Test
    public void testLastNameFieldWithValidData() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, LAST_NAME_INPUT_FIELD, LAST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

    @Test
    public void testLastNameFieldWithInvalidData() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, LAST_NAME_INPUT_FIELD, LAST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }

    @Test
    public void testUserDataFirstNameFieldWithValidData() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();

        Map<String, Object> isValidationMessageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, USER_DATA_FIRST_NAME_INPUT_FIELD, USER_DATA_FIRST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageNotShown = (boolean) isValidationMessageNotShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageNotShownMap.get("message");

        Assert.assertTrue(isValidationMessageNotShown, resultMessage);
    }

       @Test
    public void testUserDataFirstNameFieldWithInvalidData() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();

        Map<String, Object> isValidationMessageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, USER_DATA_FIRST_NAME_INPUT_FIELD, USER_DATA_FIRST_NAME_VALIDATION_MESSAGE, getDriver());

        boolean isValidationMessageShown = (boolean) isValidationMessageShownMap.get("actualResult");
        String resultMessage = (String) isValidationMessageShownMap.get("message");

        Assert.assertTrue(isValidationMessageShown, resultMessage);
    }
}
