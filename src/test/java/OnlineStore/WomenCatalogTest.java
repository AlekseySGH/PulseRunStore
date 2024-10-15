package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

public class WomenCatalogTest extends BaseTest {

    @DataProvider(name = "notAddedBrandProvider")
    public Object[][] notAddedBrandProvider() {
        return new Object[][]{
                {"Asics"},
                {"Converse"},
                {"Dr.Martens"},
                {"Hoka"},
                {"Jordan"},
                {"Native"},
                {"Puma"},
                {"Reebok"},
                {"Vans"},
        };
    }

    @DataProvider(name = "addedBrandProvider")
    public Object[][] addedBrandProvider() {
        return new Object[][]{
                {"Adidas"},
                {"New Balance"},
                {"Nike"},
                {"Salomon"},
        };
    }

    @DataProvider(name = "availableSizesProvider")
    public Object[][] availableSizesProvider() {
        return new Object[][]{
                {"36"},
                {"36.5"},
                {"37"},
                {"37.5"},
                {"38"},
                {"38.5"},
                {"39"},
                {"40"},
                {"42"},
                {"43"},
        };
    }

    @DataProvider(name = "availableSeasonValuesProvider")
    public Object[][] availableSeasonValuesProvider() {
        return new Object[][]{
                {"Весна/Осінь"},
                {"Літо"},
        };
    }

    @Test
    public void testPresenceOfBrandsItemsInFilter() {
        List<String> expectedFilterItemList = List.of("Adidas", "Asics", "Converse", "Dr.Martens", "Hoka",
                "Jordan", "Native", "New Balance", "Nike", "Puma", "Reebok", "Salomon", "Vans");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
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
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
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
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
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
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        String actualResult = getDriver().findElement(TestUtils.NOTHING_FOUND_MESSAGE).getText();

        Assert.assertEquals(actualResult, "За вашим запитом нічого не знайдено");
        Assert.assertTrue(getDriver().findElement(TestUtils.CANCEL_FILTER_BY_BRANDS).getText().contains(brandNames));
    }

    @Test(dataProvider = "addedBrandProvider")
    public void testFilteringByBrand(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        boolean isFilteredCorrect = TestUtils.checkFilteredBrands(brandNames, getDriver(), getWait10());

        Assert.assertTrue(isFilteredCorrect, "Item is not found");
    }

    @Ignore
    @Test
    public void testFilteringBySeveralBrands() {

        int qttBandsInCheckbox = 2;
        List<String> addedBrandNamesList = List.of("Adidas", "New Balance", "Nike", "Salomon");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
        List<String> randomBrandsList = TestUtils.chooseRandomBrandsInFilter(addedBrandNamesList, qttBandsInCheckbox, getDriver());

        boolean isFilteredCorrect = TestUtils.checkFilteredBrands(randomBrandsList, getDriver(), getWait10());

        Assert.assertTrue(isFilteredCorrect, "Filter is not working correctly");
    }

    @Ignore
    @Test
    public void testFilteringSeveralBrandsAndSizes() {

        int qttBandsInCheckbox = 2;
        int qttSizesInCheckbox = 4;
        boolean isFilteredCorrect = false;
        List<String> addedBrandNamesList = List.of("Adidas", "New Balance", "Nike", "Salomon");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
        List<String> randomBrandsList = TestUtils.chooseRandomBrandsInFilter(
                addedBrandNamesList, qttBandsInCheckbox, getDriver());
        List<String> randomSizesList = TestUtils.chooseRandomSizesInFilter(
                TestUtils.Catalog.WOMEN, randomBrandsList, qttSizesInCheckbox, getDriver());

        boolean isFilteredByBrandsCorrect = TestUtils.checkFilteredBrands(randomBrandsList, getDriver(), getWait10());

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
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
        TestUtils.chooseSizeInCheckbox(sizeValue, getDriver());

        boolean isFilteredCorrect = TestUtils.checkFilteredSize(
                sizeValue, TestUtils.SIZES_LIST_IN_PRODUCT_PAGE, getDriver(), getWait30());

        Assert.assertTrue(isFilteredCorrect, "Item is not found");
    }

    @Test(dataProvider = "availableSeasonValuesProvider")
    public void testFilteringBySeason(String seasonValue) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
        TestUtils.chooseSeasonInFilter(seasonValue, getDriver());

        List<String> seasonOnProductPageList = TestUtils.collectDataFromProductPage(
                TestUtils.SEASON_VALUE_IN_PRODUCT_PAGE, getDriver(), getWait30());

        boolean actualResult = TestUtils.areAllItemsInListEqualsValue(seasonOnProductPageList, seasonValue);

        Assert.assertTrue(actualResult, "Item is not found");
    }

    @Test
    public void testSortingInAscendingOder() {
        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Від дешевших']")).click();

        List<Integer> actualPricesList = TestUtils.getAllPricesInTheCatalog(getDriver(), getWait10());
        List<Integer> expectedPricesList = TestUtils.sortInAscendingOder(actualPricesList);

        Assert.assertEquals(actualPricesList, expectedPricesList);
    }

    @Test
    public void testSortingInDescendingOder() {
        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Від дорожчих']")).click();

        List<Integer> actualPricesList = TestUtils.getAllPricesInTheCatalog(getDriver(), getWait10());
        List<Integer> expectedPricesList = TestUtils.sortInDescendingOder(actualPricesList);

        Assert.assertEquals(actualPricesList, expectedPricesList);
    }

    @Test
    public void testSortingByNew() {

        List<String> expectedProductList = List.of(
                "65f8a6aec11d83d79ea7e89e", "66152cd72295ced5df7b60f3", "66152d0f2295ced5df7b611e",
                "66152d0f2295ced5df7b610d", "66152cd72295ced5df7b60f1", "66152d0f2295ced5df7b610f",
                "66152cd72295ced5df7b6100", "66152cd72295ced5df7b6104", "66152cd72295ced5df7b6105",
                "65df7f7607e96ef8fa1719b4", "66152d0f2295ced5df7b6111", "65df894fa018734b655645cc",
                "66152d0f2295ced5df7b610a", "66152d0f2295ced5df7b6112", "66152cd72295ced5df7b60f5",
                "66152cd72295ced5df7b60f2", "65e5ff1bdaa755d5047b8610", "66152cd72295ced5df7b60f9",
                "65f8a6c9c11d83d79ea7e89f", "65e5bb20cc4afedcaaa6fab1", "66152cd72295ced5df7b60fe",
                "65e9fe8b3113032e940ae844", "66152d0f2295ced5df7b610e", "66152d0f2295ced5df7b611d",
                "66152d0f2295ced5df7b6118", "65e5bf9561d46ef73bab33e6", "65f8a718c11d83d79ea7e8a3",
                "65ef1bf10e19461a6e285cef", "66152cd72295ced5df7b60f6", "66152cd72295ced5df7b60f4",
                "66152cd72295ced5df7b60ff", "66152d0f2295ced5df7b6117", "65f8a724c11d83d79ea7e8a4",
                "66152d0f2295ced5df7b610c", "66152d0f2295ced5df7b6119", "65df816b07e96ef8fa1719c1",
                "66152cd72295ced5df7b60f8", "66152d0f2295ced5df7b610b", "65f8a68bc11d83d79ea7e89d");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Новинки']")).click();

        List<String> actualProductIdList = TestUtils.getAllProductsIdInTheCatalog(getDriver(), getWait10());

        Assert.assertEquals(actualProductIdList, expectedProductList);
    }

    @Test
    public void testPresenceOfAllProducts() {

        List<String> expectedProductIdList = List.of(
                "65f8a6aec11d83d79ea7e89e", "66152d0f2295ced5df7b611e", "66152cd72295ced5df7b60f3",
                "66152cd72295ced5df7b60f1", "66152d0f2295ced5df7b610d", "66152d0f2295ced5df7b610f",
                "66152cd72295ced5df7b6104", "66152cd72295ced5df7b6100", "65df7f7607e96ef8fa1719b4",
                "66152cd72295ced5df7b6105", "66152d0f2295ced5df7b6111", "65df894fa018734b655645cc",
                "66152d0f2295ced5df7b610a", "66152cd72295ced5df7b60f5", "66152d0f2295ced5df7b6112",
                "66152cd72295ced5df7b60f2", "65e5ff1bdaa755d5047b8610", "66152cd72295ced5df7b60f9",
                "65f8a6c9c11d83d79ea7e89f", "65e5bb20cc4afedcaaa6fab1", "66152cd72295ced5df7b60fe",
                "65e9fe8b3113032e940ae844", "66152d0f2295ced5df7b610e", "66152d0f2295ced5df7b611d",
                "66152d0f2295ced5df7b6118", "65e5bf9561d46ef73bab33e6", "65f8a718c11d83d79ea7e8a3",
                "65ef1bf10e19461a6e285cef", "66152cd72295ced5df7b60f6", "66152cd72295ced5df7b60f4",
                "66152cd72295ced5df7b60ff", "66152d0f2295ced5df7b6117", "65f8a724c11d83d79ea7e8a4",
                "66152d0f2295ced5df7b610c", "66152d0f2295ced5df7b6119", "65df816b07e96ef8fa1719c1",
                "66152cd72295ced5df7b60f8", "66152d0f2295ced5df7b610b", "65f8a68bc11d83d79ea7e89d");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();

        List<String> actualProductIdList = TestUtils.getAllProductsIdInTheCatalog(getDriver(), getWait10());

        for (int i = 0; i < expectedProductIdList.size(); i++) {
            Assert.assertTrue(actualProductIdList.contains(expectedProductIdList.get(i)));
        }
    }

    @Test
    public void testOnlyWomenItemsAreShown() {

        String expectedCategoryValue = "Жіноче взуття";

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.WOMEN_CATALOG_BUTTON)).click();

        List<String> seasonOnProductPageList = TestUtils.collectDataFromProductPage(
                TestUtils.CATEGORY_VALUE_IN_PRODUCT_PAGE, getDriver(), getWait30());

        boolean actualResult = TestUtils.areAllItemsInListEqualsValue(seasonOnProductPageList, expectedCategoryValue);

        Assert.assertTrue(actualResult, "Item is not found");
    }
}
