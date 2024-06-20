package overview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Selenium4Syntax {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[@href='/login']")));


        ChromeOptions options = new ChromeOptions();

    }
}
