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

    final static By PASSWORD_INPUT_FIELD = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/input[@name= 'password']");

    final static By SHOW_PASSWORD_ICON = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]//button");

    final static By PASSWORD_FIELD_VALIDATION_MASSAGE = By.xpath("//label[text() = 'Пароль*']/following-sibling::div[1]/p");

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

        List<String> notAcceptedValuesList = new ArrayList<>();
        boolean noValidationMassage = true;

        for (int i = 0; i < validEmailsList.size(); i++) {
            getDriver().findElement(EMAIL_INPUT_FIELD).sendKeys(validEmailsList.get(i));
            getDriver().findElement(EMAIL_INPUT_FIELD).submit();

            try {
                if (getDriver().findElement(EMAIL_FIELD_VALIDATION_MASSAGE).isDisplayed()) {
                    notAcceptedValuesList.add(validEmailsList.get(i));
                    noValidationMassage = false;
                }
            } catch (NoSuchElementException ignored) {

            }

            getDriver().findElement(EMAIL_INPUT_FIELD).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
        String notAcceptedMailMassage = String.join("\n", notAcceptedValuesList + " - Не принято системой");
        Assert.assertTrue(noValidationMassage, notAcceptedMailMassage);
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

        List<String> notAcceptedValuesList = new ArrayList<>();
        boolean validationMassage = true;

        for (int i = 0; i < invalidEmailsList.size(); i++) {
            getDriver().findElement(EMAIL_INPUT_FIELD).sendKeys(invalidEmailsList.get(i));
            getDriver().findElement(EMAIL_INPUT_FIELD).submit();

            try {
                getDriver().findElement(EMAIL_FIELD_VALIDATION_MASSAGE).isDisplayed();
            } catch (NoSuchElementException ignored) {
                notAcceptedValuesList.add(invalidEmailsList.get(i));
                validationMassage = false;
            }

            getDriver().findElement(EMAIL_INPUT_FIELD).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
        String notAcceptedMailMassage = String.join("\n", notAcceptedValuesList + " - Не принято системой");
        Assert.assertTrue(validationMassage, notAcceptedMailMassage);
    }

    @Test
    public void passwordFieldWithValidDataTest() {

        List<String> validPasswordsList = List.of("Qwerty12", "QwertQwerty12378", "Qwerty123", "Qwerty123!",
                "Qwerty123@", "Qwerty123#", "Qwerty123$", "Qwerty123%", "Qwerty123^", "Qwerty123&", "Qwerty123-",
                "Qwerty123_", "Qwerty123+", "Qwerty123=", "Qwerty123|", "Qwerty123`", "Qwerty123~", "Qwerty123{",
                "Qwerty123}", "Qwerty123*", "Qwerty123(", "Qwerty123)", "Qwerty123;", "Qwerty123:", "Qwerty123,",
                "Qwerty123/", "Qwerty123?", "Qwerty123\\", "Qwerty123[", "Qwerty123]", "Qwerty123.", "Qwerty123<",
                "Qwerty123>", "Qwerty123\"", "Qwerty123\'");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        List<String> notAcceptedValuesList = new ArrayList<>();
        boolean noValidationMassage = true;

        for (int i = 0; i < validPasswordsList.size(); i++) {
            getDriver().findElement(PASSWORD_INPUT_FIELD).sendKeys(validPasswordsList.get(i));
            getDriver().findElement(PASSWORD_INPUT_FIELD).submit();

            try {
                if (getDriver().findElement(PASSWORD_FIELD_VALIDATION_MASSAGE).isDisplayed()) {
                    notAcceptedValuesList.add(validPasswordsList.get(i));
                    noValidationMassage = false;
                }
            } catch (NoSuchElementException ignored) {

            }

            getDriver().findElement(PASSWORD_INPUT_FIELD).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
        String notAcceptedMailMassage = String.join("\n", notAcceptedValuesList + " - Не принято системой");
        Assert.assertTrue(noValidationMassage, notAcceptedMailMassage);
    }

    @Ignore
    @Test
    public void passwordFieldWithInvalidDataTest() {

        List<String> invalidPasswordsList = List.of("Йцукен123", "ЙЦУКЕН123", "qwerty123", "Qwerty1", "QwertyQwerty014567890",
                "Qwerty 123", "  Qwerty123  ", "          ");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(USER_PROFILE_ICON)).click();
        getDriver().findElement(SHOW_PASSWORD_ICON).click();

        List<String> notAcceptedValuesList = new ArrayList<>();
        boolean validationMassage = true;

        for (int i = 0; i < invalidPasswordsList.size(); i++) {
            getDriver().findElement(PASSWORD_INPUT_FIELD).sendKeys(invalidPasswordsList.get(i));
            getDriver().findElement(PASSWORD_INPUT_FIELD).submit();

            try {
                getDriver().findElement(PASSWORD_FIELD_VALIDATION_MASSAGE).isDisplayed();
            } catch (NoSuchElementException ignored) {
                notAcceptedValuesList.add(invalidPasswordsList.get(i));
                validationMassage = false;
            }

            getDriver().findElement(PASSWORD_INPUT_FIELD).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }
        String notAcceptedMailMassage = String.join("\n", notAcceptedValuesList + " - Не валидируется системой");
        Assert.assertTrue(validationMassage, notAcceptedMailMassage);
    }

}
