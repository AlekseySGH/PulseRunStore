package OnlineStore.utils;

import org.openqa.selenium.By;

public class LocatorUtils {

    public static final By SHOW_ALL_BRANDS_IN_FILTER = By.xpath("(//span[text() = 'Показати все'])[1]");

    public static final By FILTER_BY_BRANDS_ITEMS = By.xpath("(//div[@class='sc-fzuLxF iSOZAC'])[1]//label");

}
