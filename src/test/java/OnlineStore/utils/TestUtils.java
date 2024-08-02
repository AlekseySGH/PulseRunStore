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
import java.util.*;

public class TestUtils {

    public static final By SHOW_ALL_BRANDS_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[1]");

    public static final By SHOW_ALL_SIZES_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[2]");

    public static final By SHOW_ALL_COLOR_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[3]");

    public static final By FILTER_BY_BRANDS_ITEMS = By.xpath("//h3[text() = 'Брeнд']/following-sibling::div[1]/div/label");

    public static final By FILTER_BY_SIZE_ITEMS = By.xpath("//h3[text() = 'Розмір']/following-sibling::div[1]/div/label");

    public static final By FILTER_BY_COLOR_ITEMS = By.xpath("//h3[text() = 'Колір']/following-sibling::div[1]/div/label");

    public static final By NOTHING_FOUND_MESSAGE = By.xpath("//div[@class='sc-juusvx jmhMGH']");

    public static final By CANCEL_FILTER_BY_BRANDS = By.xpath("//button[@class='sc-dlDPRo exRHWo']");

    private final static By PRODUCTS_LIST = By.xpath("//p[contains(@class, 'shoes-title') " +
            "and not(ancestor::div[contains(@class, 'swiper-slide')])]");

    private final static By PRICES_LIST = By.xpath("//a[contains(@href, '/online-store-front-pulse') ]" +
            "//span[contains (text(), ' грн') and not(ancestor::div[contains(@class, 'swiper-slide')])]");

    private final static By PRODUCTS_ID_LIST = By.xpath("//li[contains(@style, 'list-style')]/a" +
            "[not(ancestor::div[contains(@class, 'swiper-slide')])]");

    private final static By SIZES_LIST_IN_PRODUCT_PAGE = By.xpath("//li[@class='sc-kIgPtV jRYxGW']/label");

    private final static By SEASON_VALUE_IN_PRODUCT_PAGE = By.xpath("//p/span[text() = 'Сезон:']/following-sibling::span[1]");

    private final static By CATEGORY_VALUE_IN_PRODUCT_PAGE = By.xpath("//p/span[text() = 'Категорія:']/following-sibling::span[1]");

    private final static By H1_HEADER = By.xpath("//h1");

    private final static By PAGE_BUTTON_LIST = By.xpath("//div/ul/li/button");

    public enum Category {
        MEN,
        WOMEN,
        NEW_PRODUCTS
    }

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

    public static void chooseSizeInCheckbox(String sizeValue, WebDriver driver) {
        driver.findElement(SHOW_ALL_SIZES_IN_FILTER).click();
        driver.findElement(By.xpath("//input[@value = '" + sizeValue + "']")).click();
    }

    public static void chooseSeasonInFilter(String seasonValue, WebDriver driver) {
        driver.findElement(By.xpath("//input[@value = '" + seasonValue + "']")).click();
    }

    public static int getCatalogPageQtt(WebDriver driver) {
//        int pageCounter;
//
//        if ((driver.findElements(PAGE_BUTTON_LIST).size() - 2) > 0) {
//            pageCounter = driver.findElements(PAGE_BUTTON_LIST).size() - 2;
//        } else {
//            pageCounter = 1;
//        }

        List<String> pageList = getTexts(PAGE_BUTTON_LIST, driver);

        return Integer.parseInt(pageList.get(pageList.size() - 2));
    }

    public static void goToNextPageIfItExistsInCatalog(int currentPage, int pageQttInCatalog, WebDriver driver) {
        if (pageQttInCatalog >= currentPage) {
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

        driver.findElement(SHOW_ALL_BRANDS_IN_FILTER).click();

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

    private static List<String> getSizeLisByBrand(Category category, String brandName) {

        return switch (category) {
            case MEN -> switch (brandName) {
                case "New Balance" -> List.of("37", "38", "40", "42.5", "44");
                case "Nike" -> List.of("37", "38", "40", "41", "42", "42.5", "43", "43.5", "44");
                case "Reebok" -> List.of("42", "43", "44", "45");
                case "Salomon" -> List.of("42", "44");
                default -> List.of();
            };
            case WOMEN -> switch (brandName) {
                case "Adidas" -> List.of("36", "37", "37.5", "38", "38.5", "39", "40");
                case "New Balance" -> List.of("37.5", "38", "39", "40", "42");
                case "Nike" -> List.of("36", "36.5", "37", "38", "39", "40");
                case "Salomon" -> List.of("36.5", "37", "38", "39", "40", "42", "43");
                default -> List.of();
            };
            case NEW_PRODUCTS -> switch (brandName) {
                case "Adidas" -> List.of("37", "37.5", "38", "38.5", "39", "40");
                case "New Balance" -> List.of("37", "38", "40", "42.5", "44");
                case "Nike" -> List.of("36", "37", "38", "39", "40", "41", "42", "42.5", "43", "43.5", "44");
                case "Reebok" -> List.of("42", "43", "44", "45");
                case "Salomon" -> List.of("36.5", "37", "38", "39", "40", "42", "44");

                default -> List.of();
            };
        };
    }

    private static List<String> getSizeLisByModel(Category category, String modelName) {

        return switch (category) {
            case MEN -> switch (modelName) {
                case "New Balance 530 White Silver Navy", "Nike Dunk Low Championship Purple" ->
                        List.of("37", "38", "40", "42.5", "44");
                case "Reebok Zig Kinetica 2.5 Edge Grey" -> List.of("42", "43", "44", "45");
                case "Nike Air Max Plus Blue Gradien" -> List.of("41", "42", "43", "44");
                case "Salomon ACS+ CSWP Cement" -> List.of("42", "44");
                case "Nike Air Max 1 PRM Escape Treeline" -> List.of("42", "42.5", "43", "43.5", "44");
                default -> List.of();
            };
            case WOMEN -> switch (modelName) {
                case "Adidas ADI2000 X Blue Dawn", "Nike Blazer Low Platform pink" -> List.of("36", "37", "38", "39");
                case "Adidas Campus 00s Scarlet Gum" -> List.of("37", "37.5", "38", "38.5");
                case "Adidas Response W Cloud White Grey Five" -> List.of("37", "38", "39", "40");
                case "New Balance 610 Angora" -> List.of("37.5", "38", "39", "40", "42");
                case "Nike Gamma Force Rise", "Salomon XT-6 Ghost Grey" -> List.of("36.5", "37", "38", "39", "40");
                case "Salomon XT-6 Gore-Tex Desert Sage" -> List.of("42", "43");
                default -> List.of();
            };
            case NEW_PRODUCTS -> switch (modelName) {
                case "Adidas Campus 00s Scarlet Gum" -> List.of("37", "37.5", "38", "38.5");
                case "Adidas Response W Cloud White Grey Five" -> List.of("37", "38", "39", "40");
                case "New Balance 530 White Silver Navy", "Nike Dunk Low Championship Purple" -> List.of("37", "38", "40", "42.5", "44");
                case "Nike Air Max 1 PRM Escape Treeline" -> List.of("42", "42.5", "43", "43.5", "44");
                case "Nike Air Max Plus Blue Gradien" -> List.of("41", "42", "43", "44");
                case "Nike Blazer Low Platform pink" -> List.of("36", "37", "38", "39");
                case "Reebok Zig Kinetica 2.5 Edge Grey" -> List.of("42", "43", "44", "45");
                case "Salomon ACS+ CSWP Cement" -> List.of("42", "44");
                case "Salomon XT-6 Ghost Grey" -> List.of("36.5", "37", "38", "39", "40");
                default -> List.of();
            };
        };
    }

    public static List<String> chooseRandomSizesInFilter(
            Category category, List<String> brandNamesList, int sizeQttInCheckbox, WebDriver driver) {
        HashSet<String> sizeSetByBrand = new HashSet<>();

        for (String brandName : brandNamesList) {
            sizeSetByBrand.addAll(getSizeLisByBrand(category, brandName));
        }

        List<String> randomSizeListByBrand = new ArrayList<>();

        List<String> sizeListByBrand = new ArrayList<>(sizeSetByBrand);

        Random r = new Random();

        if (sizeQttInCheckbox <= 0) {
            sizeQttInCheckbox = 1;
        } else if (sizeQttInCheckbox >= sizeListByBrand.size()) {
            sizeQttInCheckbox = sizeListByBrand.size();
        }

        driver.findElement(SHOW_ALL_BRANDS_IN_FILTER).click();

        int i = r.nextInt(sizeListByBrand.size());

        while (randomSizeListByBrand.size() != sizeQttInCheckbox) {
            if (!randomSizeListByBrand.contains(sizeListByBrand.get(i))) {
                randomSizeListByBrand.add(sizeListByBrand.get(i));
                driver.findElement(By.xpath("//input[@value = '" + sizeListByBrand.get(i) + "']")).click();
            }
            i = r.nextInt(sizeListByBrand.size());
        }
        return randomSizeListByBrand;
    }

    public static void isFilteredByBrandInTheCatalogCorrect(String brandName, WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                String actualResult = getTexts(PRODUCTS_LIST, driver).get(j);

                Assert.assertTrue(actualResult.contains(brandName));
                Assert.assertTrue(driver.findElement(CANCEL_FILTER_BY_BRANDS).getText().contains(brandName));
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
    }

    public static void isFilteredByRandomBrandsInTheCatalogCorrect(List<String> randomBrandsList, WebDriver
            driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                boolean containsAnyBrandInList = false;
                String actualResult = getTexts(PRODUCTS_LIST, driver).get(j);

                for (String randomBandName : randomBrandsList) {
                    if (actualResult.contains(randomBandName)) {
                        containsAnyBrandInList = true;
                        break;
                    }
                }

                Assert.assertTrue(containsAnyBrandInList);
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
    }

    public static void isFilteredBySeveralBrandsAndSizesInTheCatalogCorrect
            (List<String> randomSizesList, WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                boolean containsAnySizeInList = false;
                driver.findElements(PRODUCTS_LIST).get(j).click();

                List<String> actualSizeList = getTexts(driver.findElements(SIZES_LIST_IN_PRODUCT_PAGE));

                for (String randomSizesValue : randomSizesList) {
                    if (actualSizeList.contains(randomSizesValue)) {
                        containsAnySizeInList = true;
                    } else if (containsAnySizeInList) {
                        break;
                    }
                }

                Assert.assertTrue(containsAnySizeInList);

                driver.navigate().back();
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
    }

    public static void isTheSizeListOnTheProductPageCorrect(Category category, WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                String currentItemName = driver.findElements(PRODUCTS_LIST).get(j).getText();
                driver.findElements(PRODUCTS_LIST).get(j).click();

                List<String> actualSizeList = getTexts(driver.findElements(SIZES_LIST_IN_PRODUCT_PAGE));

                List<String> expectedSizeList = getSizeLisByModel(category, currentItemName);

                driver.navigate().back();

                Assert.assertEquals(actualSizeList, expectedSizeList);
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
    }

    public static void isFilteredBySizeInTheCatalogCorrect(String sizeValue, WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                driver.findElements(PRODUCTS_LIST).get(j).click();

                List<String> actualSizeList = getTexts(wait
                        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(SIZES_LIST_IN_PRODUCT_PAGE)));

                Assert.assertTrue(actualSizeList.contains(sizeValue));

                driver.navigate().back();
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
    }

    public static void isFilteredBySeasonInTheCatalogCorrect(String seasonValue, WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                driver.findElements(PRODUCTS_LIST).get(j).click();

                String actualSeasonValue = driver.findElement(SEASON_VALUE_IN_PRODUCT_PAGE).getText();

                Assert.assertEquals(actualSeasonValue, seasonValue);

                driver.navigate().back();
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
    }

    public static void isFilteredByCategoryInTheCatalogCorrect(String expectedCategoryValue, WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                driver.findElements(PRODUCTS_LIST).get(j).click();

                String actualCategoryValue = driver.findElement(CATEGORY_VALUE_IN_PRODUCT_PAGE).getText();

                Assert.assertEquals(actualCategoryValue, expectedCategoryValue);

                driver.navigate().back();
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }

    }

    public static List<Integer> getAllPricesInTheCatalogList(WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        List<Integer> actualPricesList = new ArrayList<>();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRICES_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                int currentItemPrice = Integer.parseInt(driver.findElements(PRICES_LIST).get(j).getText().
                        replaceAll(" грн", ""));
                actualPricesList.add(currentItemPrice);

            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
        return actualPricesList;
    }

    public static List<String> getAllProductsIdInTheCatalog(WebDriver driver, WebDriverWait wait) {
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt(driver);

        List<String> productIdList = new ArrayList<>();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_ID_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                String hrefValue = driver.findElements(PRODUCTS_ID_LIST).get(j).getAttribute("href");
                productIdList.add(hrefValue.substring(hrefValue.lastIndexOf("/") + 1));
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, driver);
            currentPage += currentPage;
        }
        return productIdList;
    }

    public static List<Integer> sortInAscendingOder(List<Integer> randomSortedList) {
        List<Integer> sortedInAscendingOderList = new ArrayList<>(randomSortedList);
        Collections.sort(sortedInAscendingOderList);

        return sortedInAscendingOderList;
    }

    public static List<Integer> sortInDescendingOder(List<Integer> randomSortedList) {
        List<Integer> sortedInDescOderList = new ArrayList<>(randomSortedList);
        sortedInDescOderList.sort(Collections.reverseOrder());

        return sortedInDescOderList;
    }
}
