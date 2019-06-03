package uat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class UATParcelsize {
    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeEach
    public void setUp() throws Exception {
        String homeDir = System.getProperty("user.home");
        System.setProperty("webdriver.gecko.driver", homeDir + "/data/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    public void testUATParcelsize() throws Exception {
        driver.get("http://localhost:8080/ParcelWebserver/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("cfg-recp-name")).click();
        driver.findElement(By.id("cfg-recp-name")).clear();
        driver.findElement(By.id("cfg-recp-name")).sendKeys("5");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Breite'])[1]/following::input[1]")).clear();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Breite'])[1]/following::input[1]")).sendKeys("5");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Hoehe'])[1]/following::input[1]")).clear();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Hoehe'])[1]/following::input[1]")).sendKeys("5");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Groesse: Teeeeset'])[1]/following::h4[1]")).click();
        Thread.sleep(3000);
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
