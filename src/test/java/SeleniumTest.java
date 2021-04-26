import Util.MessageParser;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SeleniumTest {

    @Ignore
    @Test
    public void test() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\xyzer\\Documents\\geckodriver-v0.29.1-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver,30);
        try {
            driver.get("https://web.telegram.org/#/im?p=g518834710");
            driver.findElement(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div/div[3]/div[2]/form/div[2]/div[2]/input")).sendKeys("4802835760" + Keys.ENTER);
            driver.get("https://web.telegram.org/#/im?p=g518834710");
            Thread.sleep( 60  * 1000);
            //Send Message
            while (true) {
                driver.findElement(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[2]/div/div[2]/div[3]/div/div[3]/div[2]/div/div/div/form/div[2]/div[5]")).sendKeys( MessageParser.readLineByLine("src/main/resource/info.txt") + Keys.ENTER);
                Thread.sleep(5 * 60  * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
