package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductPageTest extends BaseTest {

    public static List<String> getSizeListByProductItemName(TestUtils.Catalog catalog, WebDriverWait wait) {

        String productItemName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//h2)[2]"))).getText();

        return TestUtils.getSizeLisByModel(catalog, productItemName);
    }


    @Test
    public void testSizeListByModel() {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(TestUtils.MEN_CATALOG_BUTTON)).click();
        TestUtils.chooseRandomProductItemInTheCatalog(TestUtils.MEN_CATALOG_BUTTON, getDriver(), getWait10());

        List<String> expectedSizeList = getSizeListByProductItemName(TestUtils.Catalog.MEN, getWait10());

        List<String> actualSizeList = TestUtils.getTexts(getWait10().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(TestUtils.SIZES_LIST_IN_PRODUCT_PAGE)));


        Assert.assertEquals(expectedSizeList, actualSizeList, "Item is not found");
    }
}
