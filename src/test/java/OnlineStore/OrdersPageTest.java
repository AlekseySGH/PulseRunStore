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

public class OrdersPageTest extends BaseTest {

    final static By EMAIL_INPUT_FIELD = By.xpath("(//label[text() = 'Email*']/following-sibling::div[1]/input[@name= 'email'])[2]");

    final static By EMAIL_FIELD_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Email*']/following-sibling::div[1]/p");

    final static By PASSWORD_INPUT_FIELD = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/input[@name= 'password']");

    final static By SHOW_PASSWORD_ICON = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]//button");

    final static By PASSWORD_FIELD_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/p");

    final static By FIRST_NAME_INPUT_FIELD = By.xpath("//label[text() = 'Ім’я*']/following-sibling::div[1]/input[@name= 'firstName']");

    final static By LAST_NAME_INPUT_FIELD = By.xpath("(//label[text() = 'Прізвище*']/following-sibling::div[1]/input[@name= 'lastName'])[2]");

    final static By FIRST_NAME_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Ім’я*']/following-sibling::div[1]/p");

    final static By LAST_NAME_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Прізвище*']/following-sibling::div[1]/p");

    final static By REGISTRATION_FORM_SECTION = By.xpath("//p[text() = 'РЕЄСТРАЦІЯ']");

    final static By ADD_TO_CART_BUTTON = By.xpath("//button[text() = 'Додати в кошик']");

    final static By ORDER_BUTTON = By.xpath("//a[text() = 'Оформити']");

    private static void chooseRandomSize(WebDriver driver, WebDriverWait wait) {

        Random r = new Random();
        int i = r.nextInt(wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(TestUtils.SIZES_LIST_IN_PRODUCT_PAGE)).size());

        driver.findElements(TestUtils.SIZES_LIST_IN_PRODUCT_PAGE).get(i).click();
    }

    @Ignore
    @Test
    public void loginFormEmailFieldWithValidDataTest() {

        List<String> validEmailsList = TestUtils.VALID_EMAILS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Ignore
    @Test
    public void loginFormEmailFieldWithInvalidDataTest() {

        List<String> invalidEmailsList = TestUtils.INVALID_EMAILS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }

    @Ignore
    @Test
    public void loginFormPasswordFieldWithValidDataTest() {

        List<String> validPasswordsList = TestUtils.VALID_PASSWORDS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Ignore
    @Test
    public void loginFormPasswordFieldWithInvalidDataTest() {

        List<String> invalidPasswordsList = TestUtils.INVALID_PASSWORDS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }

    @Ignore
    @Test
    public void registrationFormEmailFieldWithValidDataTest() {

        List<String> validEmailsList = TestUtils.VALID_EMAILS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Ignore
    @Test
    public void registrationFormEmailFieldWithInvalidDataTest() {

        List<String> invalidEmailsList = TestUtils.INVALID_EMAILS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidEmailsList, EMAIL_INPUT_FIELD, EMAIL_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }

    @Test
    public void registrationFormPasswordFieldWithValidDataTest() {

        List<String> validPasswordsList = TestUtils.VALID_PASSWORDS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validPasswordsList, PASSWORD_INPUT_FIELD, PASSWORD_FIELD_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Ignore
    @Test
    public void registrationFormPasswordFieldWithInvalidDataTest() {

        List<String> invalidPasswordsList = TestUtils.INVALID_PASSWORDS_LIST;

        openBaseURL();

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();
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

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

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

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

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

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

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

        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.Category.MEN, getDriver(), getWait10());

        chooseRandomSize(getDriver(), getWait2());

        getDriver().findElement(ADD_TO_CART_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(ORDER_BUTTON).click();
        getDriver().findElement(REGISTRATION_FORM_SECTION).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, LAST_NAME_INPUT_FIELD, LAST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }
}
