//package overview;
//
//import com.letkodit.base.WebDriverFactory;
//import com.letskodit.utilities.Constants;
//import org.junit.AfterClass;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
//public class LoginTest {
//
//    WebDriver driver;
//    String baseURL;
//
//    @BeforeClass
//    public void before() {
//        driver = WebDriverFactory.getInstance().getDriver("chrome");
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        baseURL = "https://www.letskodeit.com/";
//        driver.get(baseURL);
//
//    }
//
//
//    @Test
//    public  void testLogin(){
//     driver.findElement(By.xpath("//a[contains(@href,'login')]")).click();
//     driver.findElement(By.id("email")).clear();
//     driver.findElement(By.id("email")).sendKeys(Constants.DEFAULT_USERNAME);
//     WebElement passwrd = driver.findElement(By.id("login-password"));
//     passwrd.clear();
//     passwrd.sendKeys(Constants.DEFAULT_PASSWORD);
//     driver.findElement(By.id("login")).click();
//     WebElement wb = null;
//     try {
//         wb = driver.findElement(By.cssSelector(".zl-navbar-rhs-img"));
//     }catch (Exception e){
//
//     }
//        Assert.assertNotNull(wb);
//    }
//
//
//    @AfterClass
//    public void driverClose(){
//        driver.close();
//        driver.quit();
//    }
//
//}
