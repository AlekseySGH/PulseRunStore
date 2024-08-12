package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

@Ignore
public class WinterSalesPageTest extends BaseTest {

    public final static By WINTER_SALE_CATALOG = By.xpath("//a[contains(@href, 'sales') and text()='Каталог']");

    @Test
    public void testProductItemsBySeason() {

        String expectedSeason = "Зима";

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(WINTER_SALE_CATALOG)).click();

        List<String> seasonOnProductPageList = TestUtils.collectDataFromProductPage(
                TestUtils.SEASON_VALUE_IN_PRODUCT_PAGE, getDriver(), getWait30());

        boolean actualResult = TestUtils.areAllItemsInListEqualsValue(seasonOnProductPageList, expectedSeason);

        Assert.assertTrue(actualResult, "Item is not found");
    }
}
