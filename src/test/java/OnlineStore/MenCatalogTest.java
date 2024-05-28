package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;


public class MenCatalogTest extends BaseTest {

    final static By MEN_CATALOG_BUTTON = By.xpath("//li/a[text()='Чоловікам']");

    final static By PAGE_BUTTON_LIST = By.xpath("//div/ul/li");

    final static By PRODUCTS_LIST = By.xpath("//div/a[contains(@href, '/online-store-front-pulse') " +
            "and not(ancestor::div[contains(@class, 'header__inner')])]//p");

    final static By SHOW_ALL_BRANDS = By.xpath("(//span[text() = 'Показати все'])[1]");

    private void showAllBrands() {
        getDriver().findElement(SHOW_ALL_BRANDS).click();
    }

    private void chooseBandInCheckbox(String brandName) {
        getDriver().findElement(By.xpath("//input[@value = '" + brandName + "']")).click();

    }

    private List<String> getProductsTextsList(By by) {

        return getDriver().findElements(by).stream().map(WebElement::getText).toList();
    }

    private List<String> getSizeLisByModel(String modelName) {

        List<String> sizeList;

        switch (modelName) {

            case "New Balance 530 White Silver Navy":
                sizeList = List.of("37", "38", "40", "42.5", "44");
                break;

            case "Reebok Zig Kinetica 2.5 Edge Grey":
                sizeList = List.of("42", "43", "44", "45");
                break;

            case "Nike Air Max Plus Blue Gradien":
                sizeList = List.of("41", "42", "43", "44");
                break;

            case "Nike Dunk Low Championship Purple":
                sizeList = List.of("37", "38", "40", "42.5", "44");
                break;

            case "Salomon ACS+ CSWP Cement":
                sizeList = List.of("42", "44");
                break;

            case "Nike Air Max 1 PRM Escape Treeline":
                sizeList = List.of("42", "42.5", "43", "43.5", "44");
                break;

            default:
                sizeList = List.of();
                break;
        }
        return sizeList;
    }


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

    @Test(dataProvider = "notAddedBrandProvider")
    public void filterByNotAddedBrandTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        showAllBrands();
        chooseBandInCheckbox(brandNames);

        String actualResult = getDriver().findElement(
                By.xpath("//div[@class='sc-jIYCZY fclvYI']")).getText();

        Assert.assertEquals(actualResult, "За вашим запитом нічого не знайдено");
        Assert.assertTrue(getDriver().findElement(
                By.xpath("//button[@class='sc-imiRDh fjwUyS']")).getText().contains(brandNames));

    }

    @Test(dataProvider = "addedBrandProvider")
    public void filterByBrandTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        showAllBrands();
        chooseBandInCheckbox(brandNames);

        int pageCounter = 0;

        if ((getDriver().findElements(PAGE_BUTTON_LIST).size() - 2) > 0) {
            pageCounter = getDriver().findElements(PAGE_BUTTON_LIST).size() - 2;
        } else {
            pageCounter = 1;
        }

        int currentPage = 1;

        for (int i = 0; i < pageCounter; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                String actualResult = getProductsTextsList(PRODUCTS_LIST).get(j);

                Assert.assertTrue(actualResult.contains(brandNames));
                Assert.assertTrue(getDriver().findElement(
                        By.xpath("//button[@class='sc-imiRDh fjwUyS']")).getText().contains(brandNames));
            }
            if (pageCounter > currentPage) {
                getDriver().findElements(PAGE_BUTTON_LIST).get(currentPage + 1).click();
                currentPage += currentPage;
            }
        }
    }

    @Test(dataProvider = "addedBrandProvider")
    public void sizeListByBrandsTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        showAllBrands();
        chooseBandInCheckbox(brandNames);

        int pageCounter = 0;

        if ((getDriver().findElements(PAGE_BUTTON_LIST).size() - 2) > 0) {
            pageCounter = getDriver().findElements(PAGE_BUTTON_LIST).size() - 2;
        } else {
            pageCounter = 1;
        }

        int currentPage = 1;

        for (int i = 0; i < pageCounter; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                String currentItemName = getDriver().findElements(PRODUCTS_LIST).get(j).getText();
                getDriver().findElements(PRODUCTS_LIST).get(j).click();

                List<String> actualSizeList = TestUtils.getTexts(getDriver().findElements(
                        By.xpath("//li[@class='sc-ZaPur lePqnx']/label")));

                List<String> expectedSizeList = getSizeLisByModel(currentItemName);

                getDriver().navigate().back();

                Assert.assertEquals(actualSizeList, expectedSizeList);
            }
            if (pageCounter > currentPage) {
                getDriver().findElements(PAGE_BUTTON_LIST).get(currentPage + 1).click();
                currentPage += currentPage;
            }
        }

    }
}
