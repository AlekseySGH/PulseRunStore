package OnlineStore.runner;

import OnlineStore.utils.ReportUtils;
import OnlineStore.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {

    private final static String BASE_URL = "https://viktoriarosovska.github.io/online-store-front-pulse/";

    private WebDriver driver;

    private WebDriverWait webDriverWait;

    public static String getBaseUrl() {

        return BASE_URL;
    }

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {

        Reporter.log(ReportUtils.getReportHeader(context), true);
    }

    @BeforeMethod
    protected void beforeMethod(Method method, ITestResult result) {
        driver = BaseUtils.createDriver();

        Reporter.log(ReportUtils.END_LINE, true);
        Reporter.log("TEST RUN", true);
        Reporter.log(ReportUtils.getClassNameTestName(method, result), true);
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult result) {
        Reporter.log(ReportUtils.getTestStatistics(method, result), true);
        if (driver != null) {
            driver.close();
            driver.quit();

            driver = null;
        }
        webDriverWait = null;
    }


//        try {
//            webDriverWait = null;
//        } finally {
//            if (driver != null) {
//                driver.quit();
//
//                driver = null;
//            }
//        }
//    }

    protected WebDriver getDriver() {

        return driver;
    }

    protected WebDriverWait getWait2() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        }

        return webDriverWait;
    }

    protected WebDriverWait getWait5() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }

        return webDriverWait;
    }

    protected WebDriverWait getWait10() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        return webDriverWait;
    }

    protected WebDriverWait getWait30() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        }

        return webDriverWait;
    }

    public void openBaseURL() {
        TestUtils.loadBaseUrlPage(getDriver(), getWait30());

        if (TestUtils.isH1HeaderExists(getDriver())) {
            Reporter.log("BaseURL page was loaded successfully ", true);
        } else {
            TestUtils.reLoadBaseUrlPage(getDriver(), getWait10());
        }
    }

}
