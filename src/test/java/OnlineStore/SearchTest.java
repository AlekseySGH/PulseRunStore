package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchFieldWithInvalidData() {

        String expectedResult = "За вашим запитом нічого не знайдено";

        String randomName = TestUtils.getRandomName(252);

        openBaseURL();
        TestUtils.inputSearchCriteriaIntoSearchFieldAndClickEnter(randomName, getDriver());

        String actualResult = getWait10().until(ExpectedConditions.presenceOfElementLocated(TestUtils.NOTHING_FOUND_MESSAGE)).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
