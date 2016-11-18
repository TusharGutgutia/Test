import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.awt.AWTException;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

public class Gmail 
{
    WebDriver driver = null;
    WebElement element = null;

    @BeforeMethod
	public void setUp() throws Exception 
    {
        File file = new File("/home/tusharg/Downloads/Selenium/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(dataProvider="getData")
    public void test(String Username, String Password) throws InterruptedException, AWTException 
    {
    	
        driver.get("https://www.gmail.com");
        
        WebDriverWait wait = new WebDriverWait(driver, 20);
        
        driver.findElement(By.id("Email")).sendKeys(Username);
        driver.findElement(By.id("next")).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Passwd")));
        
        driver.findElement(By.id("Passwd")).sendKeys(Password);



        driver.findElement(By.id("signIn")).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'COMPOSE')]")));
        
        driver.findElement(By.xpath("//div[contains(text(),'COMPOSE')]")).click();
                 

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='to']")));
        driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("t.gutgutia@gmail.com");
        
        
        driver.findElement(By.xpath("//span[contains(@class,'aB gQ pE')]")).click();
        driver.findElement(By.xpath("//textarea[@name='cc']")).sendKeys("tushar.g@vserv.com");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("TEST MAIL");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        element = driver.findElement(By.xpath("//div[@class='Ar Au']//div"));
        element.click();
        element.sendKeys("\n Name: Tushar Gutgutia \n Contact No: 9619537302 \n Link to github : https://github.com/TusharGutgutia/Tushar");
        
        
        
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[contains(text(),'Send')]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}
     @AfterMethod
	public void teardown() throws Exception 
    {
       driver.quit();
    }



@DataProvider
public Object[][] getData(){
	
	Object data[][] = new Object[2][2];
	// row 1
	data[0][0]="tusharvserv@gmail.com";
	data[0][1]="123456";
	
	// row 2
	data[1][0]="t.gutgutia@gmail.com";
	data[1][1]="987654";
	
	
	return data;
	
}
}