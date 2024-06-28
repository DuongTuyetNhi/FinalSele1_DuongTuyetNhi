package base;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

import static config.ConfigReader.getProperty;

public class DriverManagement {
    public static WebDriver getDriver() {
        return driver.get();
    }
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static void initDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        switch (browser) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                getDriver().get(getProperty("railway_url"));
                break;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                getDriver().get(getProperty("railway_url"));
                break;
            }
            default: {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                getDriver().get("http://saferailway.somee.com/");
                break;
            }
        }
    }

    public static void openRailwayPage(){
        getDriver().get(getProperty("railway_url"));
    }

    public static void openMailPage(){
        getDriver().get(getProperty("email_url"));
    }

    public static void enter(By element, String information){
        getDriver().findElement(element).sendKeys(information);
    }

    public static void click(By element){
        getDriver().findElement(element).click();
    }

    public static String getText(By element){
        return getDriver().findElement(element).getText();
    }

    public static void waitForElementVisible(By xpath, int timeout){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    public static void scrollToView(By xpath){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement Element = getDriver().findElement(xpath);
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    public static void switchToTab(String mailTab, String railwayTab){
        Set<String> allTabs = driver.get().getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(mailTab) && !tab.equals(railwayTab)) {
                driver.get().switchTo().window(tab);
                break;
            }
        }
    }
}
