package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class NewItemsCatalogTest extends BaseTest {

    final static By NEW_ITEMS_CATALOG_BUTTON = By.xpath("//li/a[text()='Новинки']");

    final static By NEW_ITEM_BADGE = By.xpath(
            "//li[contains(@style, 'list-style')]/a//span[@color][not(ancestor::div[contains(@class, 'swiper-slide')])]");

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
                {"Vans"},
        };
    }

    @DataProvider(name = "addedBrandProvider")
    public Object[][] addedBrandProvider() {
        return new Object[][]{
                {"Adidas"},
                {"New Balance"},
                {"Nike"},
                {"Reebok"},
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
    public void presenceOfBrandsItemsInFilterTest() {
        List<String> expectedFilterItemList = List.of("Adidas", "Asics", "Converse", "Dr.Martens", "Hoka",
                "Jordan", "Native", "New Balance", "Nike", "Puma", "Reebok", "Salomon", "Vans");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        getDriver().findElement(TestUtils.SHOW_ALL_BRANDS_IN_FILTER).click();

        List<String> filterByBrandItemList = TestUtils.getTexts(TestUtils.FILTER_BY_BRANDS_ITEMS, getDriver());

        boolean isFilterListContainsItem;

        for (String expectedFilterItem : expectedFilterItemList) {
            isFilterListContainsItem = filterByBrandItemList.contains(expectedFilterItem);

            Assert.assertTrue(isFilterListContainsItem, "Brand - " + expectedFilterItem + " not found in filter");
        }
    }

    @Test
    public void presenceOfSizeItemsInFilterTest() {
        List<String> expectedFilterItemList = List.of("36", "37", "38", "39", "40", "41", "42", "43", "44",
                "45", "36.5", "37.5", "38.5", "39.5", "40.5", "41.5", "42.5", "43.5", "44.5", "45.5", "46.5", "47.5");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        getDriver().findElement(TestUtils.SHOW_ALL_SIZES_IN_FILTER).click();

        List<String> filterBySizeItemList = TestUtils.getTexts(TestUtils.FILTER_BY_SIZE_ITEMS, getDriver());

        boolean isFilterListContainsItem;

        for (String expectedFilterItem : expectedFilterItemList) {
            isFilterListContainsItem = filterBySizeItemList.contains(expectedFilterItem);

            Assert.assertTrue(isFilterListContainsItem, "Size - " + expectedFilterItem + " not found in filter");
        }
    }

    @Test
    public void presenceOfColorItemsInFilterTest() {
        List<String> expectedFilterItemList = List.of("Бежевий", "Білий", "Зелений", "Рожевий", "Синій",
                "Сірий", "Срібний", "Фіолетовий", "Червоний", "Чорний");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        getDriver().findElement(TestUtils.SHOW_ALL_COLOR_IN_FILTER).click();

        List<String> filterByColorItemList = TestUtils.getTexts(TestUtils.FILTER_BY_COLOR_ITEMS, getDriver());

        boolean isFilterListContainsItem;

        for (String expectedFilterItem : expectedFilterItemList) {
            isFilterListContainsItem = filterByColorItemList.contains(expectedFilterItem);

            Assert.assertTrue(isFilterListContainsItem, "Color - " + expectedFilterItem + " not found in filter");
        }
    }

    @Test(dataProvider = "notAddedBrandProvider")
    public void filterByNotAddedBrandsTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        String actualResult = getDriver().findElement(TestUtils.NOTHING_FOUND_MESSAGE).getText();

        Assert.assertEquals(actualResult, "За вашим запитом нічого не знайдено");
        Assert.assertTrue(getDriver().findElement(TestUtils.CANCEL_FILTER_BY_BRANDS).getText().contains(brandNames));
    }

    @Test(dataProvider = "addedBrandProvider")
    public void filterByBrandTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        boolean isFilteredCorrect = TestUtils.isFilteredByBrandInTheCatalogCorrect(brandNames, getDriver(), getWait10());

        Assert.assertTrue(isFilteredCorrect);
    }

    @Test
    public void itemListBySeveralBrandsInFilterTest() {

        int qttBandsInCheckbox = 2;
        List<String> addedBrandNamesList = List.of("Adidas", "New Balance", "Nike", "Reebok", "Salomon");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        List<String> randomBrandsList = TestUtils.chooseRandomBrandsInFilter(addedBrandNamesList, qttBandsInCheckbox, getDriver());

        boolean isFilteredCorrect = TestUtils.isFilteredByRandomBrandsInTheCatalogCorrect(randomBrandsList, getDriver(), getWait10());

        Assert.assertTrue(isFilteredCorrect);
    }

    @Test
    public void itemListBySeveralBrandsAndSizesInFilterTest() {

        int qttBandsInCheckbox = 2;
        int qttSizesInCheckbox = 4;
        List<String> addedBrandNamesList = List.of("Adidas", "New Balance", "Nike", "Reebok", "Salomon");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        List<String> randomBandsList = TestUtils.chooseRandomBrandsInFilter(addedBrandNamesList, qttBandsInCheckbox, getDriver());
        List<String> randomSizesList = TestUtils.chooseRandomSizesInFilter(TestUtils.Category.NEW_PRODUCTS, randomBandsList, qttSizesInCheckbox, getDriver());

        boolean isFilteredCorrect = TestUtils.isFilteredBySeveralBrandsAndSizesInTheCatalogCorrect(randomSizesList, getDriver(), getWait10());

        Assert.assertTrue(isFilteredCorrect);
    }

    @Test(dataProvider = "addedBrandProvider")
    public void sizeListByBrandsTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        boolean isFilteredCorrect = TestUtils.isTheSizeListOnTheProductPageCorrect(TestUtils.Category.WOMEN, getDriver(), getWait10());

        Assert.assertTrue(isFilteredCorrect);
    }

    @Test(dataProvider = "availableSizesProvider")
    public void filterBySizeTest(String sizeValue) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        TestUtils.chooseSizeInCheckbox(sizeValue, getDriver());

        TestUtils.isFilteredBySizeInTheCatalogCorrect(sizeValue, getDriver(), getWait10());
    }

    @Test(dataProvider = "availableSeasonValuesProvider")
    public void filterBySeasonTest(String seasonValue) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        TestUtils.chooseSeasonInFilter(seasonValue, getDriver());

        TestUtils.isFilteredBySeasonInTheCatalogCorrect(seasonValue, getDriver(), getWait10());
    }

    @Test
    public void productsListInAscendingOderTest() {
        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Від дешевших']")).click();

        List<Integer> actualPricesList = TestUtils.getAllPricesInTheCatalogList(getDriver(), getWait10());

        List<Integer> expectedPricesList = TestUtils.sortInAscendingOder(actualPricesList);

        Assert.assertEquals(actualPricesList, expectedPricesList);
    }

    @Test
    public void productsListInDescOderTest() {
        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Від дорожчих']")).click();

        List<Integer> actualPricesList = TestUtils.getAllPricesInTheCatalogList(getDriver(), getWait10());

        List<Integer> expectedPricesList = TestUtils.sortInDescendingOder(actualPricesList);

        Assert.assertEquals(actualPricesList, expectedPricesList);
    }

    @Test
    public void presenceOfAllItemsInCatalog() {

        List<String> expectedProductIdList = List.of(
                "65df7b2495aaba554cab83b2", "65f8a643c11d83d79ea7e89b", "66152cd72295ced5df7b6105",
                "66152d0f2295ced5df7b611a", "66152d0f2295ced5df7b6111", "65df894fa018734b655645cc",
                "66152d0f2295ced5df7b6114", "66152cd72295ced5df7b60ed", "65e5bb20cc4afedcaaa6fab1",
                "66152cd72295ced5df7b60fe", "66152cd72295ced5df7b6101", "66152cd72295ced5df7b60fb",
                "66152d0f2295ced5df7b6110", "65f8a6e7c11d83d79ea7e8a1", "66152cd72295ced5df7b6103",
                "66152cd72295ced5df7b60ee");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();

        List<String> actualProductIdList = TestUtils.getAllProductsIdInTheCatalog(getDriver(), getWait10());

        for (int i = 0; i < expectedProductIdList.size(); i++) {
            Assert.assertTrue(actualProductIdList.contains(expectedProductIdList.get(i)));
        }
    }

    @Test
    public void onlyNewItemsAreShownTest() {

        String expectedBadgeValue = "NEW";

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(NEW_ITEMS_CATALOG_BUTTON)).click();

        int currentPage = 1;
        int pageQttInCatalog = TestUtils.getCatalogPageQtt(getDriver());

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(NEW_ITEM_BADGE)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                String actualBadgeValue = getWait10().until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(NEW_ITEM_BADGE)).get(j).getText();

                Assert.assertEquals(actualBadgeValue, expectedBadgeValue);

            }
            TestUtils.goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog, getDriver());
            currentPage += currentPage;
        }
    }
}
