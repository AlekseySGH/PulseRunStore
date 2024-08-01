package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class WomenCatalogTest extends BaseTest {

    final static By WOMEN_CATALOG_BUTTON = By.xpath("//li/a[text()='Жінкам']");

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

    @Test
    public void presenceOfBrandsItemsInFilterTest() {
        List<String> expectedFilterItemList = List.of("Adidas", "Asics", "Converse", "Dr.Martens", "Hoka",
                "Jordan", "Native", "New Balance", "Nike", "Puma", "Reebok", "Salomon", "Vans");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(WOMEN_CATALOG_BUTTON)).click();
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
        getWait10().until(ExpectedConditions.elementToBeClickable(WOMEN_CATALOG_BUTTON)).click();
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
        getWait10().until(ExpectedConditions.elementToBeClickable(WOMEN_CATALOG_BUTTON)).click();
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
        getWait10().until(ExpectedConditions.elementToBeClickable(WOMEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        String actualResult = getDriver().findElement(TestUtils.NOTHING_FOUND_MESSAGE).getText();

        Assert.assertEquals(actualResult, "За вашим запитом нічого не знайдено");
        Assert.assertTrue(getDriver().findElement(TestUtils.CANCEL_FILTER_BY_BRANDS).getText().contains(brandNames));
    }

    @Test(dataProvider = "addedBrandProvider")
    public void filterByBrandTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(WOMEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        TestUtils.isFilteredByBrandInTheCatalogCorrect(brandNames, getDriver(), getWait10());
    }

    @Test
    public void itemListBySeveralBrandsInFilterTest() {

        int qttBandsInCheckbox = 2;
        List<String> addedBrandNamesList = List.of("Adidas", "New Balance", "Nike", "Salomon");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(WOMEN_CATALOG_BUTTON)).click();
        List<String> randomBrandsList = TestUtils.chooseRandomBrandsInFilter(addedBrandNamesList, qttBandsInCheckbox, getDriver());

        TestUtils.isFilteredByRandomBrandsInTheCatalogCorrect(randomBrandsList, getDriver(), getWait10());
    }

    @Test
    public void itemListBySeveralBrandsAndSizesInFilterTest() {

        int qttBandsInCheckbox = 2;
        int qttSizesInCheckbox = 4;
        List<String> addedBrandNamesList = List.of("Adidas", "New Balance", "Nike", "Salomon");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(WOMEN_CATALOG_BUTTON)).click();
        List<String> randomBandsList = TestUtils.chooseRandomBrandsInFilter(addedBrandNamesList, qttBandsInCheckbox, getDriver());
        List<String> randomSizesList = TestUtils.chooseRandomSizesInFilter("Women", randomBandsList, qttSizesInCheckbox, getDriver());

        TestUtils.isFilteredBySeveralBrandsAndSizesInTheCatalogCorrect(randomSizesList, getDriver(), getWait10());
    }

    @Test(dataProvider = "addedBrandProvider")
    public void sizeListByBrandsTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(WOMEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        TestUtils.isTheSizeListOnTheProductPageCorrect("Women", getDriver(), getWait10());
    }
}
