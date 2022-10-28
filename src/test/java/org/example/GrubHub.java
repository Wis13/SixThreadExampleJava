package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GrubHub {

    static
    {
        WebDriverManager.chromedriver().setup();
    }

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }


    @Test
    public void testLoginPage(){
        driver.get("https://www.grubhub.com/");
        driver.findElement(By.cssSelector("[data-testid='prettyhomepagesignin']")).click();
        driver.findElement(By.cssSelector(".ghs-goToCreateAccount")).click();
        driver.findElement(By.id("firstName")).sendKeys("Vasya");
        driver.findElement(By.id("lastName")).sendKeys("Piterskiy");
        driver.findElement(By.id("email")).sendKeys("vasiliy@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Ababgalamaga1!");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//img[@class='captchaMediaImage']")).isDisplayed());
    }
}
