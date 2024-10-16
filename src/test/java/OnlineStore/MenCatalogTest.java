package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.*;


public class MenCatalogTest extends BaseTest {

    @DataProvider(name = "notAddedBrandProvider")
    public Object[][] notAddedBrandProvider() {
        return new Object[][]{
                {"Adidas"},
                {"Asics"},
                {"Hoka"},
                {"Jordan"},
                {"Native"},
                {"Vans"},
                {"Dr.Martens"},
                {"Converse"},
                {"Puma"},
        };
    }

    @DataProvider(name = "addedBrandProvider")
    public Object[][] addedBrandProvider() {
        return new Object[][]{
                {"New Balance"},
                {"Nike"},
                {"Reebok"},
                {"Salomon"},
        };
    }

    @DataProvider(name = "availableSizesProvider")
    public Object[][] availableSizesProvider() {
        return new Object[][]{
                {"37"},
                {"38"},
                {"40"},
                {"41"},
                {"42"},
                {"43"},
                {"44"},
                {"42.5"},
                {"43.5"},
        };
    }

    @DataProvider(name = "availableSeasonValuesProvider")
    public Object[][] availableSeasonValuesProvider() {
        return new Object[][]{
                {"Весна/Осінь"},
                {"Зима"},
                {"Літо"},
        };
    }

    @Test
    public void testPresenceOfBrandsItemsInFilter() {
        List<String> expectedFilterItemList = List.of("Adidas", "Asics", "Converse", "Dr.Martens", "Hoka",
                "Jordan", "Native", "New Balance", "Nike", "Puma", "Reebok", "Salomon", "Vans");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        getDriver().findElement(TestUtils.SHOW_ALL_BRANDS_IN_FILTER).click();

        List<String> filterByBrandItemList = TestUtils.getTexts(TestUtils.FILTER_BY_BRANDS_ITEMS, getDriver());

        boolean isFilterListContainsItem;

        for (String expectedFilterItem : expectedFilterItemList) {
            isFilterListContainsItem = filterByBrandItemList.contains(expectedFilterItem);

            Assert.assertTrue(isFilterListContainsItem, "Brand - " + expectedFilterItem + " not found in filter");
        }
    }

    @Test
    public void testPresenceOfSizeItemsInFilter() {
        List<String> expectedFilterItemList = List.of("36", "37", "38", "39", "40", "41", "42", "43", "44",
                "45", "36.5", "37.5", "38.5", "39.5", "40.5", "41.5", "42.5", "43.5", "44.5", "45.5", "46.5", "47.5");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        getDriver().findElement(TestUtils.SHOW_ALL_SIZES_IN_FILTER).click();

        List<String> filterBySizeItemList = TestUtils.getTexts(TestUtils.FILTER_BY_SIZE_ITEMS, getDriver());

        boolean isFilterListContainsItem;

        for (String expectedFilterItem : expectedFilterItemList) {
            isFilterListContainsItem = filterBySizeItemList.contains(expectedFilterItem);

            Assert.assertTrue(isFilterListContainsItem, "Size - " + expectedFilterItem + " not found in filter");
        }
    }

    @Test
    public void testPresenceOfColorItemsInFilter() {
        List<String> expectedFilterItemList = List.of("Бежевий", "Білий", "Зелений", "Рожевий", "Синій",
                "Сірий", "Срібний", "Фіолетовий", "Червоний", "Чорний");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        getDriver().findElement(TestUtils.SHOW_ALL_COLOR_IN_FILTER).click();

        List<String> filterByColorItemList = TestUtils.getTexts(TestUtils.FILTER_BY_COLOR_ITEMS, getDriver());

        boolean isFilterListContainsItem;

        for (String expectedFilterItem : expectedFilterItemList) {
            isFilterListContainsItem = filterByColorItemList.contains(expectedFilterItem);

            Assert.assertTrue(isFilterListContainsItem, "Color - " + expectedFilterItem + " not found in filter");
        }
    }

    @Test(dataProvider = "notAddedBrandProvider")
    public void testFilteringByNotAddedBrands(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        String actualResult = getDriver().findElement(TestUtils.NOTHING_FOUND_MESSAGE).getText();

        Assert.assertEquals(actualResult, "За вашим запитом нічого не знайдено");
        Assert.assertTrue(getDriver().findElement(TestUtils.CANCEL_FILTER_BY_BRANDS).getText().contains(brandNames));
    }

    @Test(dataProvider = "addedBrandProvider")
    public void testFilteringByBrand(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        boolean isFilteredCorrect = TestUtils.checkFilteredBrands(brandNames, getDriver(), getWait10());

        Assert.assertTrue(isFilteredCorrect, "Item is not found");
    }

    @Ignore
    @Test
    public void testFilteringBySeveralBrands() {

        int qttBandsInCheckbox = 2;
        List<String> addedBrandNamesList = List.of("New Balance", "Nike", "Reebok", "Salomon");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        List<String> randomBrandsList = TestUtils.chooseRandomBrandsInFilter(
                addedBrandNamesList, qttBandsInCheckbox, getDriver());

        boolean isFilteredCorrect = TestUtils.checkFilteredBrands(randomBrandsList, getDriver(), getWait10());

        Assert.assertTrue(isFilteredCorrect, "Filter is not working correctly");
    }

    @Ignore
    @Test
    public void testFilteringSeveralBrandsAndSizes() {

        int qttBandsInCheckbox = 2;
        int qttSizesInCheckbox = 4;
        boolean isFilteredCorrect = false;
        List<String> addedBrandNamesList = List.of("New Balance", "Nike", "Reebok", "Salomon");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        List<String> randomBrandsList = TestUtils.chooseRandomBrandsInFilter(
                addedBrandNamesList, qttBandsInCheckbox, getDriver());
        List<String> randomSizesList = TestUtils.chooseRandomSizesInFilter(
                TestUtils.Catalog.MEN, randomBrandsList, qttSizesInCheckbox, getDriver());

        boolean isFilteredByBrandsCorrect = TestUtils.checkFilteredBrands(randomBrandsList, getDriver(), getWait10());

        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/ul/li/button[text() = '1']"))).click();

        boolean isFilteredBySizesCorrect = TestUtils.checkFilteredSize(
                randomSizesList, TestUtils.SIZES_LIST_IN_PRODUCT_PAGE, getDriver(), getWait30());

        if ((isFilteredByBrandsCorrect) && (isFilteredBySizesCorrect)) {
            isFilteredCorrect = true;
        }

        Assert.assertTrue(isFilteredCorrect, "Filter is not working correctly");
    }

    @Test(dataProvider = "availableSizesProvider")
    public void testFilteringBySize(String sizeValue) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        TestUtils.chooseSizeInCheckbox(sizeValue, getDriver());

        boolean isFilteredCorrect = TestUtils.checkFilteredSize(
                sizeValue, TestUtils.SIZES_LIST_IN_PRODUCT_PAGE, getDriver(), getWait30());

        Assert.assertTrue(isFilteredCorrect, "Item is not found");
    }

    @Test(dataProvider = "availableSeasonValuesProvider")
    public void testFilteringBySeason(String seasonValue) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        TestUtils.chooseSeasonInFilter(seasonValue, getDriver());

        List<String> seasonOnProductPageList = TestUtils.collectDataFromProductPage(
                TestUtils.SEASON_VALUE_IN_PRODUCT_PAGE, getDriver(), getWait30());

        boolean actualResult = TestUtils.areAllItemsInListEqualsValue(seasonOnProductPageList, seasonValue);

        Assert.assertTrue(actualResult, "Item is not found");
    }

    @Test
    public void testSortingInAscendingOder() {
        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Від дешевших']")).click();

        List<Integer> actualPricesList = TestUtils.getAllPricesInTheCatalog(getDriver(), getWait30());
        List<Integer> expectedPricesList = TestUtils.sortInAscendingOder(actualPricesList);

        Assert.assertEquals(actualPricesList, expectedPricesList);
    }

    @Test
    public void testSortingInDescendingOder() {
        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Від дорожчих']")).click();

        List<Integer> actualPricesList = TestUtils.getAllPricesInTheCatalog(getDriver(), getWait10());
        List<Integer> expectedPricesList = TestUtils.sortInDescendingOder(actualPricesList);

        Assert.assertEquals(actualPricesList, expectedPricesList);
    }

    @Test
    public void testSortingByNew() {

        List<String> expectedProductList = List.of(
                "65de2dd5ae9bb15396c0fb9a", "65df7b2495aaba554cab83b2", "65f8a62fc11d83d79ea7e89a",
                "65f8a643c11d83d79ea7e89b", "66152cd72295ced5df7b60fd", "65e9fc153113032e940ae831",
                "66152d0f2295ced5df7b611a", "66152d0f2295ced5df7b6109", "66152cd72295ced5df7b60fa",
                "65f8a706c11d83d79ea7e8a2", "66152d0f2295ced5df7b6114", "66152cd72295ced5df7b60ed",
                "66152cd72295ced5df7b60ef", "66152d0f2295ced5df7b6108", "65de2a7dae9bb15396c0fb86",
                "66152d0f2295ced5df7b611b", "66152d0f2295ced5df7b6116", "66152cd72295ced5df7b6101",
                "66152cd72295ced5df7b60fb", "66152cd72295ced5df7b6102", "66152d0f2295ced5df7b6110",
                "65de32105e90b6233fe92633", "65ef2292e9f197360880b0f6", "66152cd72295ced5df7b60fc",
                "66152cd72295ced5df7b60f0", "65f8a6e7c11d83d79ea7e8a1", "66152cd72295ced5df7b6103",
                "66152d0f2295ced5df7b6107", "66152cd72295ced5df7b60f7", "65f8a66ac11d83d79ea7e89c",
                "66152d0f2295ced5df7b611c", "66152d0f2295ced5df7b6113", "65f8a6d0c11d83d79ea7e8a0",
                "66152cd72295ced5df7b60ee", "66152d0f2295ced5df7b6115", "66152d0f2295ced5df7b6106");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Новинки']")).click();

        List<String> actualProductIdList = TestUtils.getAllProductsIdInTheCatalog(getDriver(), getWait10());

        Assert.assertEquals(actualProductIdList, expectedProductList);
    }

    @Test
    public void testPresenceOfAllProducts() {

        List<String> expectedProductIdList = List.of(
                "65de2dd5ae9bb15396c0fb9a", "65df7b2495aaba554cab83b2", "65f8a62fc11d83d79ea7e89a",
                "65f8a643c11d83d79ea7e89b", "66152cd72295ced5df7b60fd", "65e9fc153113032e940ae831",
                "66152d0f2295ced5df7b611a", "66152d0f2295ced5df7b6109", "66152cd72295ced5df7b60fa",
                "65f8a706c11d83d79ea7e8a2", "66152d0f2295ced5df7b6114", "66152cd72295ced5df7b60ed",
                "66152cd72295ced5df7b60ef", "66152d0f2295ced5df7b611b", "66152d0f2295ced5df7b6108",
                "65de2a7dae9bb15396c0fb86", "66152d0f2295ced5df7b6116", "66152cd72295ced5df7b6101",
                "66152cd72295ced5df7b60fb", "66152cd72295ced5df7b6102", "66152d0f2295ced5df7b6110",
                "65de32105e90b6233fe92633", "65ef2292e9f197360880b0f6", "66152cd72295ced5df7b60fc",
                "66152cd72295ced5df7b60f0", "65f8a6e7c11d83d79ea7e8a1", "66152cd72295ced5df7b6103",
                "66152d0f2295ced5df7b6107", "66152cd72295ced5df7b60f7", "65f8a66ac11d83d79ea7e89c",
                "66152d0f2295ced5df7b611c", "66152d0f2295ced5df7b6113", "65f8a6d0c11d83d79ea7e8a0",
                "66152cd72295ced5df7b60ee", "66152d0f2295ced5df7b6115", "66152d0f2295ced5df7b6106");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();

        List<String> actualProductIdList = TestUtils.getAllProductsIdInTheCatalog(getDriver(), getWait10());

        for (int i = 0; i < expectedProductIdList.size(); i++) {
            Assert.assertTrue(actualProductIdList.contains(expectedProductIdList.get(i)), "Item is not found");
        }
    }

    @Test
    public void testOnlyMenItemsAreShown() {

        String expectedCategoryValue = "Чоловіче взуття";

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();

        List<String> seasonOnProductPageList = TestUtils.collectDataFromProductPage(
                TestUtils.CATEGORY_VALUE_IN_PRODUCT_PAGE, getDriver(), getWait30());

        boolean actualResult = TestUtils.areAllItemsInListEqualsValue(seasonOnProductPageList, expectedCategoryValue);

        Assert.assertTrue(actualResult, "Item is not found");
    }
}



