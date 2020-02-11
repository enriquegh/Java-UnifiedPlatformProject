import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class MyUnifiedPlatformTest {
    public Boolean result;
    private String username = System.getenv("SAUCE_USERNAME");
    private String accessKey = System.getenv("SAUCE_ACCESS_KEY");

    @Tag("android-web")
    @Tag("unified-platform")
    @Tag("emusim")
    @DisplayName("UnifiedPlatformWebEMUSIM-Java")
    @Test
    public void UnifiedPlatformWebEMUSIM(TestInfo testInfo) {

        AndroidDriver<WebElement> driver = null;
        String methodName = testInfo.getDisplayName();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android GoogleAPI Emulator");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("tags", testInfo.getTags());


        String sauceUrl = "https://%s:%s@ondemand.us-west-1.saucelabs.com:443/wd/hub";
        URL url = null;
        try {
            url = new URL(String.format(sauceUrl, username, accessKey));

        driver = new AndroidDriver<>(url, capabilities);


        driver.navigate().to("https://www.saucedemo.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='text']")));
        userNameField.sendKeys("standard_user");

        driver.findElement(By.cssSelector("[type='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[type='submit']")).click();
        WebElement inventoryPageLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));

        Assert.assertTrue(inventoryPageLocator.isDisplayed());

        driver.executeScript("sauce:job-result=" + (result ? "passed" : "failed"));
        driver.quit();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            driver.quit();
        }
    }

    @Tag("android-native")
    @Tag("unified-platform")
    @Tag("emusim")
    @DisplayName("UnifiedPlatformNativeEMUSIM-Java")
    @Test
    public void UnifiedPlatformNativeEMUSIM(TestInfo testInfo) throws MalformedURLException {
        AndroidDriver<WebElement> driver = null;
        String methodName = testInfo.getDisplayName();

        // Download app from https://github.com/saucelabs/sample-app-mobile/releases/download/2.2.1/Android.SauceLabs.Mobile.Sample.app.2.2.1.apk
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android GoogleAPI Emulator");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("app", "sauce-storage:sample-sauce.apk");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");

        try {
        String sauceUrl = "https://%s:%s@ondemand.us-west-1.saucelabs.com:443/wd/hub";
        URL url = new URL(String.format(sauceUrl, username, accessKey));
        driver = new AndroidDriver<>(url, capabilities);

        String source = driver.getPageSource();
        System.out.println(source);

        driver.executeScript("sauce:job-result=" + (result ? "passed" : "failed"));
        driver.quit();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            driver.quit();
        }


    }

    @Tag("android-native")
    @Tag("unified-platform")
    @Tag("rdc")
    @DisplayName("UnifiedPlatformNativeRDC-Java")
    @Test
    public void UnifiedPlatformNativeRDC(TestInfo testInfo) throws MalformedURLException {
        AndroidDriver<WebElement> driver = null;
        String methodName = testInfo.getDisplayName();

        // Download app from https://github.com/saucelabs/sample-app-mobile/releases/download/2.2.1/Android.SauceLabs.Mobile.Sample.app.2.2.1.apk
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Samsung Galaxy.*");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("app", "sauce-storage:sample-sauce.apk");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");

        try {
            String sauceUrl = "https://%s:%s@ondemand.us-west-1.saucelabs.com:443/wd/hub";
            URL url = new URL(String.format(sauceUrl, username, accessKey));
            driver = new AndroidDriver<>(url, capabilities);

            String source = driver.getPageSource();
            System.out.println(source);

            driver.executeScript("sauce:job-result=" + (result ? "passed" : "failed"));
            driver.quit();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }


    }
}
