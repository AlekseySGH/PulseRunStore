package OnlineStore;

import OnlineStore.runner.BaseTest;
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


    @DataProvider(name = "notExistBrandProvider")
    public Object[][] notExistBrandProvider() {
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

    @DataProvider(name = "existBrandProvider")
    public Object[][] existBrandProvider() {
        return new Object[][]{
                {"New Balance"},
                {"Nike"},
                {"Reebok"},
                {"Salomon"},
        };
    }

    @Test(dataProvider = "notExistBrandProvider")
    public void filterByNotExistBrandTest(String brandNames) {

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

    @Test(dataProvider = "existBrandProvider")
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
}
