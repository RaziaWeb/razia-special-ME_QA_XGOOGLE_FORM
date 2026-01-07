package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

import javax.swing.Action;
import javax.swing.text.DateFormatter;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void testCase01() throws InterruptedException {
        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

        Thread.sleep(4000);

        WebElement name = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        // name =
        // driver.findElement(By.xpath("//input[@type='text']/following-sibling::div"));
        name.click();
        name.sendKeys("Crio Learner");

        WebElement participateTextArea = driver.findElement(By.xpath("//textarea"));
        long timeStamp = System.currentTimeMillis();
        participateTextArea.sendKeys("I want to be the best QA Engineer!" + timeStamp);

        WebElement expRadio = driver.findElement(By.xpath("//div[@class='ulDsOb']/span[text()='0 - 2']"));
        expRadio.click();

        List<WebElement> allSubjects = driver
                .findElements(By.xpath("//div[@class='ulDsOb']/span[contains(@class,'n5vBHf')]"));
        for (WebElement Subject : allSubjects) {
            String subjectName = Subject.getText();
            if (subjectName.equals("Java") || subjectName.equals("Selenium") || subjectName.equals("TestNG")) {
                Subject.click();
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement drpAddressed = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='vRMGwf oJeWuf'][text()='Choose']")));
        drpAddressed.click();
        Thread.sleep(4000);
        WebElement addressValue = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@role='option' and @data-value='Mrs']")));
        addressValue.click();
 Thread.sleep(4000);
        // for checking option address

//         WebElement address=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'vRMGwf')]")));
// System.out.println(address);

        // Date-Calender
        LocalDate expectedDate = LocalDate.now().minusDays(7);
        DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatedDate = expectedDate.format(datetimeFormat);

        WebElement Date = driver.findElement(By.xpath("//input[@type='date']"));
        Date.sendKeys(formatedDate);

        // time Selection
        // LocalTime currentTime = LocalTime.now();

        // String hour = currentTime.format(DateTimeFormatter.ofPattern("hh"));
        // String min = currentTime.format(DateTimeFormatter.ofPattern("mm"));
        // String ampm = currentTime.format(DateTimeFormatter.ofPattern("a"));

        WebElement Hour_txt = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        Hour_txt.sendKeys("07");

        WebElement Minute_txt = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        Minute_txt.sendKeys("30");

       System.out.println("Form Successfully filled");

        Thread.sleep(2000);
        WebElement Submit_btn = driver.findElement(By.xpath("//span[text()='Submit']"));
        Submit_btn.click();
        System.out.println("Form Submitted Successfully");
 Thread.sleep(4000);
        WebElement successMsg = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vHW8K']")));
        String msg = successMsg.getText();

        System.out.println(msg);
        //happy coding 
    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() throws InterruptedException {
        Thread.sleep(5000);
         if (driver != null)
         {
        driver.close();
        driver.quit();
         }
    }
}