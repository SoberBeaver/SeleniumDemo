import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WikiPageObject {
    private final WebDriver driver;
    private final WebElement searchInputElement;
    private WebElement searchInputButton;
    private WebDriverWait wait;

    public WikiPageObject(WebDriver driver) {
        this.driver = driver;
        this.searchInputElement = driver.findElement(By.cssSelector("#searchInput"));
        this.wait = new WebDriverWait(driver, 2);
    }

    public void setSearchText(String text) {
        searchInputElement.click(); // without this div.suggestions-special doesn't appear
        searchInputElement.sendKeys(text);
    }

    public void clickToSearch() {
        searchInputButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.suggestions-special")));
        searchInputButton.click();
    }

    public int getSearchResultSize(){
        return driver.findElements(By.cssSelector("li.mw-search-result")).size();
    }
}
