import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class WikiPageTest {
    private final String url = "https://ru.wikipedia.org";
    private WebDriver driver;

    @BeforeClass
    public static void initProperties(){
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
    }

    @Before
    public void initWebDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        driver = new FirefoxDriver(capabilities);

        driver.manage().
                timeouts().
                implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().
                window().
                maximize();

        driver.navigate().to(url);
    }

    @Test
    public void test() {
        WikiPageObject wikiPageObject = new WikiPageObject(driver);

        wikiPageObject.setSearchText("Hello");

        wikiPageObject.clickToSearch();

        assertTrue(wikiPageObject.getSearchResultSize() > 1);
    }

    @After
    public void closeDriver(){
        driver.quit();
    }
}
