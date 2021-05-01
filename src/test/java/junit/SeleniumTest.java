package junit;

import Bot.Util.MessageParser;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class SeleniumTest {

    @Ignore
    @Test
    public void printMessage(){
       run();
    }

    private void run(){
        System.setProperty("webdriver.gecko.driver", "src/main/resource/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("https://web.telegram.org/#/im?p=g518834710");
            while (true) {
                ArrayList<String> lines = new ArrayList<>(Arrays.asList(MessageParser.readLineByLine("src/main/resource/info.txt").split("\n")));

                for(String line : lines){
                    driver.findElement(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[2]/div/div[2]/div[3]/div/div[3]/div[2]/div/div/div/form/div[2]/div[5]"))
                            .sendKeys( line.replace("\uE000",""));
                    Thread.sleep(1  * 1000);
                    driver.findElement(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[2]/div/div[2]/div[3]/div/div[3]/div[2]/div/div/div/form/div[2]/div[5]"))
                            .sendKeys(Keys.chord(Keys.SHIFT, Keys.ENTER).replace("\uE000",""));
                }
                driver.findElement(By.xpath("//*[@id=\"ng-app\"]/body/div[1]/div[2]/div/div[2]/div[3]/div/div[3]/div[2]/div/div/div/form/div[2]/div[5]"))
                        .sendKeys(Keys.ENTER);
                Thread.sleep(5 * 60  * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
