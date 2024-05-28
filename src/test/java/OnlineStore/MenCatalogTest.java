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

    final static By SHOW_ALL_SIZES = By.xpath("(//span[text() = 'Показати все'])[2]");

    private void chooseBandInCheckbox(String brandName) {
        getDriver().findElement(SHOW_ALL_BRANDS).click();
        getDriver().findElement(By.xpath("//input[@value = '" + brandName + "']")).click();
    }

    private void chooseSizeInCheckbox(String sizeValue) {
        getDriver().findElement(SHOW_ALL_SIZES).click();
        getDriver().findElement(By.xpath("//input[@value = '" + sizeValue + "']")).click();
    }

    private int getCatalogPageQtt() {
        int pageCounter;

        if ((getDriver().findElements(PAGE_BUTTON_LIST).size() - 2) > 0) {
            pageCounter = getDriver().findElements(PAGE_BUTTON_LIST).size() - 2;
        } else {
            pageCounter = 1;
        }
        return pageCounter;
    }

    private void goToNextPageIfItExistsInCatalog(int currentPage, int pageQttInCatalog) {
        if (pageQttInCatalog > currentPage) {
            getDriver().findElements(PAGE_BUTTON_LIST).get(currentPage + 1).click();
        }
    }

    private List<String> getProductsTextsList(By by) {

        return getDriver().findElements(by).stream().map(WebElement::getText).toList();
    }

    private List<String> getSizeLisByModel(String modelName) {

        List<String> sizeList = switch (modelName) {
            case "New Balance 530 White Silver Navy", "Nike Dunk Low Championship Purple" ->
                    List.of("37", "38", "40", "42.5", "44");
            case "Reebok Zig Kinetica 2.5 Edge Grey" -> List.of("42", "43", "44", "45");
            case "Nike Air Max Plus Blue Gradien" -> List.of("41", "42", "43", "44");
            case "Salomon ACS+ CSWP Cement" -> List.of("42", "44");
            case "Nike Air Max 1 PRM Escape Treeline" -> List.of("42", "42.5", "43", "43.5", "44");
            default -> List.of();
        };

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
//                {"43.5"},
        };
    }

    @Test(dataProvider = "notAddedBrandProvider")
    public void filterByNotAddedBrandTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
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
        chooseBandInCheckbox(brandNames);

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                String actualResult = getProductsTextsList(PRODUCTS_LIST).get(j);

                Assert.assertTrue(actualResult.contains(brandNames));
                Assert.assertTrue(getDriver().findElement(
                        By.xpath("//button[@class='sc-imiRDh fjwUyS']")).getText().contains(brandNames));
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
    }

    @Test(dataProvider = "addedBrandProvider")
    public void sizeListByBrandsTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        chooseBandInCheckbox(brandNames);

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

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
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
    }

    @Test(dataProvider = "availableSizesProvider")
    public void filterBySizeTest(String sizeValue) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        chooseSizeInCheckbox(sizeValue);

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                getDriver().findElements(PRODUCTS_LIST).get(j).click();

                List<String> actualSizeList = TestUtils.getTexts(getDriver().findElements(
                        By.xpath("//li[@class='sc-ZaPur lePqnx']/label")));

                Assert.assertTrue(actualSizeList.contains(sizeValue));

                getDriver().navigate().back();
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
    }
}
