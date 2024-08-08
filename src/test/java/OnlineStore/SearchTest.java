package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchFieldWithInvalidData() {

        String expectedResult = "За вашим запитом нічого не знайдено";

        String randomName = TestUtils.getRandomName(252);

        openBaseURL();
        TestUtils.inputSearchCriteriaIntoSearchFieldAndClickEnter(randomName, getDriver());

        String actualResult = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(TestUtils.NOTHING_FOUND_MESSAGE)).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSearchFieldWithValidBrandsNameParts() {

        String expectedResult = "Nike Air Max 1 PRM Escape Treeline";
        List<String> searchRequest = List.of("Nike", "Max", "Escape");
        String searchResponse = null;

        openBaseURL();
        getDriver().findElement(TestUtils.CATALOG_BUTTON).click();

        List<String> productItemsList = TestUtils.collectDataFromCatalog(TestUtils.PRODUCTS_NAME_LIST, getDriver(), getWait30());

        for (int i = 0; i < searchRequest.size(); i++) {
            HashSet<String> searchRequestSet = new HashSet<>();

            for (int j = 0; j < productItemsList.size(); j++) {
                if (productItemsList.get(j).contains(searchRequest.get(i))) {
                    searchRequestSet.add(productItemsList.get(j));
                }
            }
            if (!(searchRequestSet.size() == 0)) {
                productItemsList = new ArrayList<>(searchRequestSet);
            } else {
                searchResponse = "За вашим запитом нічого не знайдено";
                break;
            }
            searchResponse = productItemsList.get(0);
        }

        Assert.assertEquals(searchResponse, expectedResult);
    }
}
