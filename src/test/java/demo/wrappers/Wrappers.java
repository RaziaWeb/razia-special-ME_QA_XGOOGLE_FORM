package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
 // this sample Method created by me for minimal method to satisfy compiler 
    public void sampleMethod()
    {
        System.out.println("Wrappers class ready");
    }

    public void enterName(WebDriver driver,String name)
    {
        WebElement nameBox = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        clickOnBtn(driver,nameBox);
        sendInput(driver, nameBox, name);
      
    }

    public void entermsgWithTime(WebDriver driver,String msg)
    {
        WebElement participateTextArea = driver.findElement(By.xpath("//textarea"));
        long timeStamp = System.currentTimeMillis();
        String particateTextBoxValue=msg+timeStamp;

        clickOnBtn(driver,participateTextArea);
        sendInput(driver, participateTextArea,particateTextBoxValue);
    }
    

    public void clickOnBtn(WebDriver driver,WebElement element)
    { 
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(element));
       element.click();
    }

    public void sendInput(WebDriver driver,WebElement element,String value)
    {
        element.sendKeys(value);
    }

    public void selectExperience(WebDriver driver, String experience)
    {
        WebElement expRadio = driver.findElement(By.xpath("//span[@class='H2Gmcc tyNBNd']//span[contains(text(),'"+experience+"')]"));    
        clickOnBtn(driver,expRadio);
    }

    public void selectSubjects(WebDriver driver,List<String>expectedSubjects)
    {
            List<WebElement> allSubjects = driver.findElements(By.xpath("//div[@class='ulDsOb']/span[contains(@class,'n5vBHf')]"));
        for (WebElement Subject : allSubjects) 
        {
            String subjectName = Subject.getText();
            // if (subjectName.equals("Java") || subjectName.equals("Selenium") || subjectName.equals("TestNG"))
           if(expectedSubjects.contains(subjectName))
                {
               clickOnBtn(driver,Subject);
            }
        }

    }


    public void selectAdress(WebDriver driver,String givenAddress)throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement drpAddressed = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='vRMGwf oJeWuf'][text()='Choose']")));
            clickOnBtn(driver,drpAddressed);
        Thread.sleep(4000);

       
        List<WebElement> allAddresses=driver.findElements(By.xpath("//div[contains(@class,'ncFHed')]//div[contains(@class,'OIC90c')]"));
         for(WebElement address: allAddresses)
         {
           String addressValue=address.getText();
           if(givenAddress.equals(addressValue))
           {
            clickOnBtn(driver,address);
           }
         }
        Thread.sleep(4000);
    }



    public void selectDate(WebDriver driver,int givenDay)
    {
        LocalDate expectedDate = LocalDate.now().minusDays(givenDay);
        DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatedDate = expectedDate.format(datetimeFormat);

        WebElement Date = driver.findElement(By.xpath("//input[@type='date']"));
       sendInput(driver, Date, formatedDate);
    }

    public void setTime(WebDriver driver,String hour,String min)
    {        
        WebElement Hour_txt = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        sendInput(driver, Hour_txt, "07");

        WebElement Minute_txt = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        sendInput(driver, Minute_txt, "30");
    }

    
}
