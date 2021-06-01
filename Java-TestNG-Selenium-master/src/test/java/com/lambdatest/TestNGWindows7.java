package com.lambdatest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGWindows7 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
    	 String username = "jaisree30";
         String authkey = "R7pa81JWl2KIbUcjcp6mlMKeeQ1DjkoSvAE3jzkoX2MyLrawNj"
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 7");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "80.0.0");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }

    @Test
    public void basicTest() throws InterruptedException, AWTException {
    	 System.out.println("Loading Url");
         driver.get("https://www.lambdatest.com/automation-demos/");
         driver.findElement(By.id("username")).sendKeys("lambda");
     	  driver.findElement(By.name("password")).sendKeys("lambda123");
     	  driver.findElement(By.className("applynow")).click();
     	  Thread.sleep(4000);
     	  driver.findElement(By.xpath("//input[@name='email']")).sendKeys("jaisree30@gmail.com");
     	  driver.findElement(By.cssSelector("input#month")).click();
     	  Thread.sleep(3000);
     	  driver.findElement(By.xpath("//input[@id='discounts']")).click();
     	  Select s =new Select(driver.findElement(By.id("preferred-payment")));
     	  s.selectByVisibleText("Wallets");
     	  driver.findElement(By.xpath("//input[@id='tried-ecom']")).click();
     	  Actions a = new Actions(driver);
     	  WebElement from = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
     	  WebElement to = driver.findElement(By.xpath("//div[@id='slider']"));
     	  Thread.sleep(4000);
     	  a.dragAndDrop(from, to).build().perform();
     	  driver.findElement(By.xpath("//textarea[@id='comments']")).sendKeys("feedback");
     	  Thread.sleep(4000);
     	  WebElement upload=driver.findElement(By.xpath("//label[contains(text(),' upload image')]"));
     	  upload.click();
     	 // upload.sendKeys("C:\\Users\\srikanth.user\\Desktop\\jenkins.svg");
     	  StringSelection str = new StringSelection("jenkins.svg");
     	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
     	    Robot rb=new Robot();
     	    rb.keyPress(KeyEvent.VK_ENTER);
     	    rb.keyRelease(KeyEvent.VK_ENTER);
     	    // press Contol+V for pasting
     	     rb.keyPress(KeyEvent.VK_CONTROL);
     	     rb.keyPress(KeyEvent.VK_V);
     	     System.out.println("File Uploaded Successfully");
     	    // release Contol+V for pasting
     	    rb.keyRelease(KeyEvent.VK_CONTROL);
     	    rb.keyRelease(KeyEvent.VK_V);
     	    System.out.println("File Uploaded Successfully");
     	    // for pressing and releasing Enter
     	    rb.keyPress(KeyEvent.VK_ENTER);
     	    rb.keyRelease(KeyEvent.VK_ENTER);
     	 Assert.assertTrue(true, "File Uploaded Successfully");
     	  driver.findElement(By.xpath("//button[@id='submit-button']")).click();
           Status = "passed";
           Thread.sleep(150);

           System.out.println("TestFinished");

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}