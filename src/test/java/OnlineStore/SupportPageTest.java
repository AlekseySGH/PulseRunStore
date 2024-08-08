package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Ignore
public class SupportPageTest extends BaseTest {

    final static By USER_DATA_FIRST_NAME_INPUT_FIELD = By.xpath(
            "//label[contains(text(), 'Ваше ім’я*')]/following-sibling::div[1]/input[@name= 'name']");

    final static By USER_DATA_FIRST_NAME_VALIDATION_MASSAGE= By.xpath(
            "//label[contains(text(), 'Ваше ім’я*')]/following-sibling::div[1]/p");

    @Test
    public void testUserDataFirstNameFieldWithValidData() {

        List<String> validNameList = TestUtils.VALID_NAMES_LIST;

        openBaseURL();
        getDriver().findElement(By.xpath("//a[text() = 'Підтримка']")).click();

        Map<String, Object> isValidationMassageNotShownMap = TestUtils.checkFieldWithValidData(
                validNameList, USER_DATA_FIRST_NAME_INPUT_FIELD, USER_DATA_FIRST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageNotShown = (boolean) isValidationMassageNotShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageNotShownMap.get("massage");

        Assert.assertTrue(isValidationMassageNotShown, resultMassage);
    }

    @Test
    public void testUserDataFirstNameFieldWithInvalidData() {

        List<String> invalidNameList = TestUtils.INVALID_NAMES_LIST;

        openBaseURL();
        getDriver().findElement(By.xpath("//a[text() = 'Підтримка']")).click();

        Map<String, Object> isValidationMassageShownMap = TestUtils.checkFieldWithInvalidData(
                invalidNameList, USER_DATA_FIRST_NAME_INPUT_FIELD, USER_DATA_FIRST_NAME_VALIDATION_MASSAGE, getDriver());

        boolean isValidationMassageShown = (boolean) isValidationMassageShownMap.get("actualResult");
        String resultMassage = (String) isValidationMassageShownMap.get("massage");

        Assert.assertTrue(isValidationMassageShown, resultMassage);
    }
}
