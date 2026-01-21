package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
// import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
// import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;

// import javax.swing.Action;
// import javax.swing.text.DateFormatter;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    Wrappers wrappers =new Wrappers();

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void testCase01() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

        Thread.sleep(4000);

        wrappers.enterName(driver,"Crio Learner" ); //Enter Name
      
        wrappers.entermsgWithTime(driver,"I want to be the best QA Engineer!"); //Enter Msg

        wrappers.selectExperience(driver,"0 - 2"); //SelectExperience
       
       
        List<String>subjectsTobeSelected=List.of("Java","Selenium","TestNG");//selectSubject
        wrappers.selectSubjects(driver, subjectsTobeSelected);

        
        wrappers.selectAdress(driver,"Mrs"); //selectAddress
       
      
        wrappers.selectDate(driver,7);  // Date-Calender-select givendays before

        wrappers.setTime(driver, "07", "30"); //set givenTime

        System.out.println("Form Successfully filled");
        Thread.sleep(2000);
        WebElement Submit_btn = driver.findElement(By.xpath("//span[text()='Submit']"));
        wrappers.clickOnBtn(driver, Submit_btn);
  

        System.out.println("Form Submitted Successfully");
        Thread.sleep(4000);
        WebElement successMsg = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vHW8K']")));
        String msg = successMsg.getText();

        System.out.println(msg);
   
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