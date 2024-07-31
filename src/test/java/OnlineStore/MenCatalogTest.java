package OnlineStore;

import OnlineStore.runner.BaseTest;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;


public class MenCatalogTest extends BaseTest {

    final static By MEN_CATALOG_BUTTON = By.xpath("//li/a[text()='Чоловікам']");

    final static By PAGE_BUTTON_LIST = By.xpath("//div/ul/li/button");

    final static By PRODUCTS_LIST = By.xpath("//p[contains(@class, 'shoes-title') " +
            "and not(ancestor::div[contains(@class, 'swiper-slide')])]");

    final static By PRICES_LIST = By.xpath("//a[contains(@href, '/online-store-front-pulse') ]" +
            "//span[contains (text(), ' грн') and not(ancestor::div[contains(@class, 'swiper-slide')])]");

    final static By PRODUCTS_ID_LIST = By.xpath("//li[contains(@style, 'list-style')]/a" +
            "[not(ancestor::div[contains(@class, 'swiper-slide')])]");

    final static By SIZES_LIST_IN_PRODUCT_PAGE = By.xpath("//li[@class='sc-kIgPtV jRYxGW']/label");

    final static By SEASON_VALUE_IN_PRODUCT_PAGE = By.xpath("//p/span[text() = 'Сезон:']/following-sibling::span[1]");

    final static By CATEGORY_VALUE_IN_PRODUCT_PAGE = By.xpath("//p/span[text() = 'Категорія:']/following-sibling::span[1]");

    private List<String> chooseRandomBandsInCheckbox(int brandQttInCheckbox) {
        List<String> addedBrandNamesList = List.of("New Balance", "Nike", "Reebok", "Salomon");
        List<String> randomBrandNamesList = new ArrayList<>();

        Random r = new Random();

        if (brandQttInCheckbox <= 0) {
            brandQttInCheckbox = 1;
        } else if (brandQttInCheckbox >= addedBrandNamesList.size()) {
            brandQttInCheckbox = addedBrandNamesList.size();
        }

        getDriver().findElement(TestUtils.SHOW_ALL_BRANDS_IN_FILTER).click();

        int i = r.nextInt(addedBrandNamesList.size());

        while (randomBrandNamesList.size() != brandQttInCheckbox) {
            if (!randomBrandNamesList.contains(addedBrandNamesList.get(i))) {
                randomBrandNamesList.add(addedBrandNamesList.get(i));
                getDriver().findElement(By.xpath("//input[@value = '" + addedBrandNamesList.get(i) + "']")).click();
            }
            i = r.nextInt(addedBrandNamesList.size());
        }
        return randomBrandNamesList;
    }

    private List<String> chooseRandomSizesInCheckbox(List<String> brandNamesList, int sizeQttInCheckbox) {
        HashSet<String> sizeSetByBrand = new HashSet<>();

        for (String s : brandNamesList) {
            sizeSetByBrand.addAll(getSizeLisByBrand(s));
        }

        List<String> randomSizeListByBrand = new ArrayList<>();

        List<String> sizeListByBrand = new ArrayList<>(sizeSetByBrand);

        Random r = new Random();

        if (sizeQttInCheckbox <= 0) {
            sizeQttInCheckbox = 1;
        } else if (sizeQttInCheckbox >= sizeListByBrand.size()) {
            sizeQttInCheckbox = sizeListByBrand.size();
        }

        getDriver().findElement(TestUtils.SHOW_ALL_BRANDS_IN_FILTER).click();

        int i = r.nextInt(sizeListByBrand.size());

        while (randomSizeListByBrand.size() != sizeQttInCheckbox) {
            if (!randomSizeListByBrand.contains(sizeListByBrand.get(i))) {
                randomSizeListByBrand.add(sizeListByBrand.get(i));
                getDriver().findElement(By.xpath("//input[@value = '" + sizeListByBrand.get(i) + "']")).click();
            }
            i = r.nextInt(sizeListByBrand.size());
        }
        return randomSizeListByBrand;
    }

    private void chooseSizeInCheckbox(String sizeValue) {
        getDriver().findElement(TestUtils.SHOW_ALL_SIZES_IN_FILTER).click();
        getDriver().findElement(By.xpath("//input[@value = '" + sizeValue + "']")).click();
    }

    private void chooseSeasonInCheckbox(String seasonValue) {
        getDriver().findElement(By.xpath("//input[@value = '" + seasonValue + "']")).click();
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

    private List<String> getSizeLisByModel(String modelName) {

        List<String> getSizeByModelList = switch (modelName) {
            case "New Balance 530 White Silver Navy", "Nike Dunk Low Championship Purple" ->
                    List.of("37", "38", "40", "42.5", "44");
            case "Reebok Zig Kinetica 2.5 Edge Grey" -> List.of("42", "43", "44", "45");
            case "Nike Air Max Plus Blue Gradien" -> List.of("41", "42", "43", "44");
            case "Salomon ACS+ CSWP Cement" -> List.of("42", "44");
            case "Nike Air Max 1 PRM Escape Treeline" -> List.of("42", "42.5", "43", "43.5", "44");
            default -> List.of();
        };

        return getSizeByModelList;
    }

    private List<String> getModelsLisBySeason(String seasonName) {

        List<String> getModelList = switch (seasonName) {
            case "Весна/Осінь" -> List.of("Adidas ADI2000 X Blue Dawn", "Adidas Campus 00s Scarlet Gum",
                    "New Balance 610 Angora", "Nike Air Max 1 PRM Escape Treeline", "Nike Blazer Low Platform pink",
                    "Nike Dunk Low Championship Purple", "Nike Gamma Force Rise", "Salomon XT-6 Ghost Grey",
                    "Salomon XT-6 Gore-Tex Desert Sage");
            case "Зима" -> List.of("Reebok Zig Kinetica 2.5 Edge Grey", "Salomon ACS+ CSWP Cement");
            case "Літо" -> List.of("New Balance 530 White Silver Navy", "Adidas Response W Cloud White Grey Five",
                    "Nike Air Max Plus Blue Gradien");
            default -> List.of();
        };

        return getModelList;
    }

    private List<String> getSizeLisByBrand(String brandName) {

        List<String> getSizeByBrandList = switch (brandName) {
            case "New Balance" -> List.of("37", "38", "40", "42.5", "44");
            case "Nike" -> List.of("37", "38", "40", "41", "42", "42.5", "43", "43.5", "44");
            case "Reebok" -> List.of("42", "43", "44", "45");
            case "Salomon" -> List.of("42", "44");
            default -> List.of();
        };

        return getSizeByBrandList;
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
                {"43.5"},
        };
    }

    @DataProvider(name = "availableSeasonValuesProvider")
    public Object[][] availableSeasonValuesProvider() {
        return new Object[][]{
                {"Весна/Осінь"},
                {"Зима"},
                {"Літо"},
        };
    }

    @Test
    public void presenceOfBrandsItemsInFilterTest() {
        List<String> expectedFilterItemList = List.of("Adidas", "Asics", "Converse", "Dr.Martens", "Hoka",
                "Jordan", "Native", "New Balance", "Nike", "Puma", "Reebok", "Salomon", "Vans");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
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
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
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
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
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
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        String actualResult = getDriver().findElement(TestUtils.NOTHING_FOUND_MESSAGE).getText();

        Assert.assertEquals(actualResult, "За вашим запитом нічого не знайдено");
        Assert.assertTrue(getDriver().findElement(TestUtils.CANCEL_FILTER_BY_BRANDS).getText().contains(brandNames));
    }

    @Test(dataProvider = "addedBrandProvider")
    public void filterByBrandTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                String actualResult = TestUtils.getTexts(PRODUCTS_LIST, getDriver()).get(j);

                Assert.assertTrue(actualResult.contains(brandNames));
                Assert.assertTrue(getDriver().findElement(TestUtils.CANCEL_FILTER_BY_BRANDS).getText().contains(brandNames));
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
    }

    @Test
    public void itemListBySeveralBrandsInFilterTest() {

        int qttBandsInCheckbox = 2;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        List<String> randomBandsList = chooseRandomBandsInCheckbox(qttBandsInCheckbox);

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                boolean containsAnyBrandInList = false;
                String actualResult = TestUtils.getTexts(PRODUCTS_LIST, getDriver()).get(j);

                for (String randomBandName : randomBandsList) {
                    if (actualResult.contains(randomBandName)) {
                        containsAnyBrandInList = true;
                        break;
                    }
                }

                Assert.assertTrue(containsAnyBrandInList);
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
    }

    @Test
    public void productSizeFilteredBySeveralBrandsAndSizesInFilterTest() {

        int qttBandsInCheckbox = 2;
        int qttSizesInCheckbox = 4;

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        List<String> randomBandsList = chooseRandomBandsInCheckbox(qttBandsInCheckbox);
        List<String> randomSizesList = chooseRandomSizesInCheckbox(randomBandsList, qttSizesInCheckbox);


        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {
                boolean containsAnySizeInList = false;
                getDriver().findElements(PRODUCTS_LIST).get(j).click();

                List<String> actualSizeList = TestUtils.getTexts(getDriver().findElements(SIZES_LIST_IN_PRODUCT_PAGE));

                for (String randomSizesValue : randomSizesList) {
                    if (actualSizeList.contains(randomSizesValue)) {
                        containsAnySizeInList = true;
                    } else if (containsAnySizeInList) {
                        break;
                    }
                }

                Assert.assertTrue(containsAnySizeInList);

                getDriver().navigate().back();
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
    }

    @Test(dataProvider = "addedBrandProvider")
    public void sizeListByBrandsTest(String brandNames) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        TestUtils.chooseBandInCheckbox(brandNames, getDriver());

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                String currentItemName = getDriver().findElements(PRODUCTS_LIST).get(j).getText();
                getDriver().findElements(PRODUCTS_LIST).get(j).click();

                List<String> actualSizeList = TestUtils.getTexts(getDriver().findElements(SIZES_LIST_IN_PRODUCT_PAGE));

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

                List<String> actualSizeList = TestUtils.getTexts(getWait10()
                        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(SIZES_LIST_IN_PRODUCT_PAGE)));

                Assert.assertTrue(actualSizeList.contains(sizeValue));

                getDriver().navigate().back();
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
    }

    @Test(dataProvider = "availableSeasonValuesProvider")
    public void filterBySeasonTest(String seasonValue) {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        chooseSeasonInCheckbox(seasonValue);

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                getDriver().findElements(PRODUCTS_LIST).get(j).click();

                String actualSeasonValue = getDriver().findElement(SEASON_VALUE_IN_PRODUCT_PAGE).getText();

                Assert.assertEquals(actualSeasonValue, seasonValue);

                getDriver().navigate().back();
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
    }

    @Test
    public void productsListInAscendingOderTest() {
        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Від дешевших']")).click();

        List<Integer> actualPricesList = new ArrayList<>();

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRICES_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                int currentItemPrice = Integer.parseInt(getDriver().findElements(PRICES_LIST).get(j).getText().
                        replaceAll(" грн", ""));
                actualPricesList.add(currentItemPrice);

            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }

        List<Integer> expectedPricesList = new ArrayList<>(actualPricesList);
        Collections.sort(expectedPricesList);

        Assert.assertEquals(actualPricesList, expectedPricesList);
    }

    @Test
    public void productsListInDescOderTest() {
        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Від дорожчих']")).click();

        List<Integer> actualPricesList = new ArrayList<>();

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRICES_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                int currentItemPrice = Integer.parseInt(getDriver().findElements(PRICES_LIST).get(j).getText().
                        replaceAll(" грн", ""));
                actualPricesList.add(currentItemPrice);

            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }

        List<Integer> expectedPricesList = new ArrayList<>(actualPricesList);
        expectedPricesList.sort(Collections.reverseOrder());

        Assert.assertEquals(actualPricesList, expectedPricesList);
    }

    @Test
    public void productListByNewSortListTest() {

        List<String> expectedProductList = List.of(
                "65de2dd5ae9bb15396c0fb9a", "65df7b2495aaba554cab83b2", "65f8a62fc11d83d79ea7e89a",
                "65f8a643c11d83d79ea7e89b", "66152cd72295ced5df7b60fd", "65e9fc153113032e940ae831",
                "66152d0f2295ced5df7b611a", "66152d0f2295ced5df7b6109", "66152cd72295ced5df7b60fa",
                "66152d0f2295ced5df7b6114", "65f8a706c11d83d79ea7e8a2", "66152cd72295ced5df7b60ed",
                "66152cd72295ced5df7b60ef", "65de2a7dae9bb15396c0fb86", "66152d0f2295ced5df7b611b",
                "66152d0f2295ced5df7b6108", "66152d0f2295ced5df7b6116", "66152cd72295ced5df7b6101",
                "66152cd72295ced5df7b60fb", "66152cd72295ced5df7b6102", "66152d0f2295ced5df7b6110",
                "65de32105e90b6233fe92633", "65ef2292e9f197360880b0f6", "66152cd72295ced5df7b60fc",
                "66152cd72295ced5df7b60f0", "65f8a6e7c11d83d79ea7e8a1", "66152cd72295ced5df7b6103",
                "66152d0f2295ced5df7b6107", "66152cd72295ced5df7b60f7", "65f8a66ac11d83d79ea7e89c",
                "66152d0f2295ced5df7b611c", "66152d0f2295ced5df7b6113", "65f8a6d0c11d83d79ea7e8a0",
                "66152cd72295ced5df7b60ee", "66152d0f2295ced5df7b6115", "66152d0f2295ced5df7b6106");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();
        getDriver().findElement(By.xpath("//span[text()='Сортування']")).click();
        getDriver().findElement(By.xpath("//li[text()='Новинки']")).click();

        List<String> actualProductIdList = new ArrayList<>();
        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_ID_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                String hrefValue = getDriver().findElements(PRODUCTS_ID_LIST).get(j).getAttribute("href");
                actualProductIdList.add(hrefValue.substring(hrefValue.lastIndexOf("/") + 1));
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
        Assert.assertEquals(actualProductIdList, expectedProductList);
    }

    @Test
    public void presenceOfAllItemsInCatalog() {

        List<String> expectedProductIdList = List.of(
                "65de2dd5ae9bb15396c0fb9a", "65df7b2495aaba554cab83b2", "65f8a62fc11d83d79ea7e89a",
                "65f8a643c11d83d79ea7e89b", "66152cd72295ced5df7b60fd", "65e9fc153113032e940ae831",
                "66152d0f2295ced5df7b611a", "66152d0f2295ced5df7b6109", "66152cd72295ced5df7b60fa",
                "65f8a706c11d83d79ea7e8a2", "66152d0f2295ced5df7b6114", "66152cd72295ced5df7b60ed",
                "66152cd72295ced5df7b60ef", "66152d0f2295ced5df7b611b", "66152d0f2295ced5df7b6108",
                "65de2a7dae9bb15396c0fb86", "66152d0f2295ced5df7b6116", "66152cd72295ced5df7b6101",
                "66152cd72295ced5df7b60fb", "66152cd72295ced5df7b6102", "66152d0f2295ced5df7b6110",
                "65de32105e90b6233fe92633", "65ef2292e9f197360880b0f6", "66152cd72295ced5df7b60fc",
                "66152cd72295ced5df7b60f0", "65f8a6e7c11d83d79ea7e8a1", "66152cd72295ced5df7b6103",
                "66152d0f2295ced5df7b6107", "66152cd72295ced5df7b60f7", "65f8a66ac11d83d79ea7e89c",
                "66152d0f2295ced5df7b611c", "66152d0f2295ced5df7b6113", "65f8a6d0c11d83d79ea7e8a0",
                "66152cd72295ced5df7b60ee", "66152d0f2295ced5df7b6115", "66152d0f2295ced5df7b6106");

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();

        List<String> actualProductIdList = new ArrayList<>();

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_ID_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                String hrefValue = getDriver().findElements(PRODUCTS_ID_LIST).get(j).getAttribute("href");
                actualProductIdList.add(hrefValue.substring(hrefValue.lastIndexOf("/") + 1));
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }

        for (int i = 0; i < expectedProductIdList.size(); i++) {
            Assert.assertTrue(actualProductIdList.contains(expectedProductIdList.get(i)));
        }
    }

    @Test
    public void onlyMensItemsAreShownTest() {

        openBaseURL();
        getWait10().until(ExpectedConditions.elementToBeClickable(MEN_CATALOG_BUTTON)).click();

        int currentPage = 1;
        int pageQttInCatalog = getCatalogPageQtt();
        String expectedCategoryValue = "Чоловіче взуття";

        for (int i = 0; i < pageQttInCatalog; i++) {

            int itemQttOnPage = getWait10().until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCTS_LIST)).size();
            for (int j = 0; j < itemQttOnPage; j++) {

                getDriver().findElements(PRODUCTS_LIST).get(j).click();

                String actualCategoryValue = getDriver().findElement(CATEGORY_VALUE_IN_PRODUCT_PAGE).getText();

                Assert.assertEquals(actualCategoryValue, expectedCategoryValue);

                getDriver().navigate().back();
            }
            goToNextPageIfItExistsInCatalog(currentPage, pageQttInCatalog);
            currentPage += currentPage;
        }
    }
}



