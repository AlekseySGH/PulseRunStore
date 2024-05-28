package OnlineStore.utils;

import OnlineStore.runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestUtils {

    private final static By H1_HEADER = By.xpath("//h1");

    public static void loadBaseUrlPage(WebDriver driver, WebDriverWait wait) {
        driver.get(BaseTest.getBaseUrl());
        waitForPageLoaded(driver);
    }

    public static void waitForPageLoaded(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    public static void reLoadBaseUrlPage(WebDriver driver, WebDriverWait wait) {
        int count = 0;
        while (count <= 3 && !(isH1HeaderExists(driver))) {
            loadBaseUrlPage(driver, wait);
            count++;
        }
    }

    public static boolean isH1HeaderExists(WebDriver driver) {
        boolean isExists = true;
        try {
            driver.findElement(H1_HEADER);
        } catch (NoSuchElementException e) {
            isExists = false;
        }
        return isExists;
    }

    public static List<String> getTexts(List<WebElement> elementList) {
        return elementList.stream().map(WebElement::getText).toList();
    }

    public static  List<String> getTexts(By by, WebDriver driver) {
        return driver.findElements(by).stream().map(WebElement::getText).toList();
    }
}
