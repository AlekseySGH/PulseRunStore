package OnlineStore;

import OnlineStore.runner.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test
    public void mainPageHeaderTest() {

        final String expectedResult = "ОБИРАЙ КОМФОРТ ТА СВОБОДУ";

        openBaseURL();

        String actualResult = getDriver().findElement(By.xpath("//h1")).getText().toUpperCase();

        Assert.assertEquals(actualResult, expectedResult);

    }
}
