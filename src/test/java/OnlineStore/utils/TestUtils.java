package OnlineStore.utils;

import OnlineStore.runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestUtils {

    public static final By SHOW_ALL_BRANDS_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[1]");

    public static final By SHOW_ALL_SIZES_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[2]");

    public static final By SHOW_ALL_COLOR_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[3]");

    public static final By FILTER_BY_BRANDS_ITEMS = By.xpath("(//div[@class='sc-fzuLxF iSOZAC'])[1]//label");

    public static final By FILTER_BY_SIZE_ITEMS = By.xpath("(//div[@class='sc-fzuLxF iSOZAC'])[3]//label");

    public static final By FILTER_BY_COLOR_ITEMS = By.xpath("(//div[@class='sc-fzuLxF iSOZAC'])[4]//label");

    public static final By NOTHING_FOUND_MESSAGE = By.xpath("//div[@class='sc-juusvx jmhMGH']");

    public static final By CANCEL_FILTER_BY_BRANDS = By.xpath("//button[@class='sc-dlDPRo exRHWo']");

    public static final By PRODUCTS_LIST = By.xpath("//p[contains(@class, 'shoes-title') " +
            "and not(ancestor::div[contains(@class, 'swiper-slide')])]");

    private final static By H1_HEADER = By.xpath("//h1");

    private final static By PAGE_BUTTON_LIST = By.xpath("//div/ul/li/button");

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

    public static List<String> getTexts(By by, WebDriver driver) {
        return driver.findElements(by).stream().map(WebElement::getText).toList();
    }

    public static void chooseBandInCheckbox(String brandName, WebDriver driver) {
        driver.findElement(SHOW_ALL_BRANDS_IN_FILTER).click();
        driver.findElement(By.xpath("//input[@value = '" + brandName + "']")).click();
    }

    public static int getCatalogPageQtt(WebDriver driver) {
        int pageCounter;

        if ((driver.findElements(PAGE_BUTTON_LIST).size() - 2) > 0) {
            pageCounter = driver.findElements(PAGE_BUTTON_LIST).size() - 2;
        } else {
            pageCounter = 1;
        }
        return pageCounter;
    }

    public static void goToNextPageIfItExistsInCatalog(int currentPage, int pageQttInCatalog, WebDriver driver) {
        if (pageQttInCatalog > currentPage) {
            driver.findElements(PAGE_BUTTON_LIST).get(currentPage + 1).click();
        }
    }

    public static List<String> chooseRandomBrandsInFilter(List<String> brandNamesList, int brandQttInCheckbox, WebDriver driver) {
        List<String> randomBrandNamesList = new ArrayList<>();

        Random r = new Random();

        if (brandQttInCheckbox <= 0) {
            brandQttInCheckbox = 1;
        } else if (brandQttInCheckbox >= brandNamesList.size()) {
            brandQttInCheckbox = brandNamesList.size();
        }

        driver.findElement(TestUtils.SHOW_ALL_BRANDS_IN_FILTER).click();

        int i = r.nextInt(brandNamesList.size());

        while (randomBrandNamesList.size() != brandQttInCheckbox) {
            if (!randomBrandNamesList.contains(brandNamesList.get(i))) {
                randomBrandNamesList.add(brandNamesList.get(i));
                driver.findElement(By.xpath("//input[@value = '" + brandNamesList.get(i) + "']")).click();
            }
            i = r.nextInt(brandNamesList.size());
        }
        return randomBrandNamesList;
    }

    public static void isFilteredByBrandInTheCatalogCorrect(String brandName, WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                String actualResult = TestUtils.getTexts(PRODUCTS_LIST, driver).get(j);

                Assert.assertTrue(actualResult.contains(brandName));
                Assert.assertTrue(driver.findElement(CANCEL_FILTER_BY_BRANDS).getText().contains(brandName));
            }
            TestUtils.goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
    }

    public static void isFilteredByRandomBrandsInTheCatalogCorrect(List<String> randomBrandsList, WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                boolean containsAnyBrandInList = false;
                String actualResult = TestUtils.getTexts(PRODUCTS_LIST, driver).get(j);

                for (String randomBandName : randomBrandsList) {
                    if (actualResult.contains(randomBandName)) {
                        containsAnyBrandInList = true;
                        break;
                    }
                }

                Assert.assertTrue(containsAnyBrandInList);
            }
            TestUtils.goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
    }
}
