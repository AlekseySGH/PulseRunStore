package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test
    public void testMainPageHeader() {

        final String expectedResult = "ОБИРАЙ КОМФОРТ ТА СВОБОДУ";

        openBaseURL();

        String actualResult = TestUtils.getText(getWait10().until(
                ExpectedConditions.presenceOfElementLocated(TestUtils.H1_HEADER))).toUpperCase();

        Assert.assertEquals(actualResult, expectedResult);

    }
}
