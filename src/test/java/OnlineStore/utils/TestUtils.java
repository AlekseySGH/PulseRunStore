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

    public static final By SHOW_ALL_BRANDS_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[1]");

    public static final By SHOW_ALL_SIZES_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[2]");

    public static final By SHOW_ALL_COLOR_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[3]");

    public static final By FILTER_BY_BRANDS_ITEMS = By.xpath("(//div[@class='sc-fzuLxF iSOZAC'])[1]//label");

    public static final By FILTER_BY_SIZE_ITEMS = By.xpath("(//div[@class='sc-fzuLxF iSOZAC'])[3]//label");

    public static final By FILTER_BY_COLOR_ITEMS = By.xpath("(//div[@class='sc-fzuLxF iSOZAC'])[4]//label");

    public static final By NOTHING_FOUND_MESSAGE = By.xpath("//div[@class='sc-juusvx jmhMGH']");

    public static final By CANCEL_FILTER_BY_BRANDS = By.xpath("//button[@class='sc-dlDPRo exRHWo']");

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

    public static void chooseBandInCheckbox(String brandName, WebDriver driver) {
        driver.findElement(SHOW_ALL_BRANDS_IN_FILTER).click();
        driver.findElement(By.xpath("//input[@value = '" + brandName + "']")).click();
    }
}
