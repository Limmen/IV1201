/*
 * Classname: ViewTest
 * Version: 0.1
 * Date: 20-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.view;

import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This class contains acceptance-tests for the application.
 * It will run a firefox instance to tests usual use-cases and
 * edge-cases.
 * @author kim
 */
public class ViewTest 
{
    private WebDriver driver;
    
    /**
     * Class constructor
     */
    public ViewTest() 
    {
    }
    
    /**
     * This method is called before the tests are executed
     */
    @Before
    public void setUp() 
    {
        driver  = new FirefoxDriver();
    }

    //This test need to be run after deployment to application server
    // utöka misslyckad inloggning
    // tomma strängar, konstiga värden,
    //@Ignore


    /**
     * This test need to be run after deployment to application server (@Ignore)
     */    
    @Ignore
    @Test
    public void testView()
    {
        testIndex();
        testApplicantLogin("root", "rootroot");
        testApplicantLogOut();
        testRecruitLogin("recruiter", "recruiter");
        testRecruitLogOut();
        testFailedLogin("root", "rootrootroot");
        testFailedLogin("wrongUsername10", "wrongPassword21511215");
        testFailedLogin("root", "wrongPassword21511215");
        testFailedLogin("wrongUsername10", "rootroot");
        testNavigationFilters();
        testLocale();
        testFailedRegistration("root", "wrongPassword21511215");
        testRegistration();
    }
    
    /* Tests that home-page is rendered properly */
    private void testIndex()
    {
        driver.navigate().to("http://localhost:8080/IV1201/");
        Assert.assertEquals("IV1201 - Recruitment System", driver.getTitle());
        /* Check if jumbotron is visible */
        Assert.assertTrue(driver.findElements(By.id("j_idt7")).size() > 0);
        /* Check that main container is visible */
        Assert.assertTrue(driver.findElements(By.id("j_idt12")).size() > 0);
        /* Check that footer is visible */
        Assert.assertTrue(driver.findElements(By.className("footer")).size() > 0);
        /* Check that the url is correct */
        Assert.assertEquals("http://localhost:8080/IV1201/", driver.getCurrentUrl());
    }
    
    /* Test login with username and password. */
    private void testApplicantLogin(String username, String password)
    {
        driver.navigate().to("http://localhost:8080/IV1201/");
        /* Click "Ansökande" page */
        driver.findElement(By.id("j_idt19")).click();
        /* Make sure user is directed to login-page. */
        Assert.assertEquals("https://localhost:8181/IV1201/login.xhtml", 
                driver.getCurrentUrl());
        /* Insert username */
        driver.findElement(By.id("loginUsername")).sendKeys(username);
        /* Insert password */
        driver.findElement(By.id("loginPassword")).sendKeys(password);
        /* Click login-button */
        driver.findElement(By.id("j_idt30")).click();
        /* Check that user-profile well is visible */
        Assert.assertTrue(driver.findElements(By.id("j_idt18")).size() > 0);
        /* Check that url is correct and that https is used */
        Assert.assertEquals("https://localhost:8181/IV1201/applicant/index.xhtml", 
                driver.getCurrentUrl());
    }
    
    /* Test login with username and password. */
    private void testRecruitLogin(String username, String password)
    {
        driver.navigate().to("http://localhost:8080/IV1201/");
        /* Click "Ansökande" page */
        driver.findElement(By.id("j_idt19")).click();
        /* Make sure user is directed to login-page. */
        Assert.assertEquals("https://localhost:8181/IV1201/login.xhtml", 
                driver.getCurrentUrl());
        /* Insert username */
        driver.findElement(By.id("loginUsername")).sendKeys(username);
        /* Insert password */
        driver.findElement(By.id("loginPassword")).sendKeys(password);
        /* Click login-button */
        driver.findElement(By.id("j_idt30")).click();
        /* Check that user-profile well is visible */
        Assert.assertTrue(driver.findElements(By.id("j_idt18")).size() > 0);
        /* Check that url is correct and that https is used */
        Assert.assertEquals("https://localhost:8181/IV1201/recruit/index.xhtml", 
                driver.getCurrentUrl());
    }
    
    private void testApplicantLogOut()
    {
        driver.findElement(By.id("j_idt24:j_idt25")).click();
        Assert.assertEquals("https://localhost:8181/IV1201/index.xhtml", driver.getCurrentUrl());
    }
    
    private void testRecruitLogOut()
    {
        driver.findElement(By.id("j_idt23:j_idt24")).click();
        Assert.assertEquals("https://localhost:8181/IV1201/index.xhtml", driver.getCurrentUrl());
    }
    
    private void testFailedLogin(String username, String password)
    {
        driver.navigate().to("http://localhost:8080/IV1201/");
        /* Click "Rekryterare" page */
        driver.findElement(By.id("j_idt20")).click();
        /* Make sure user is directed to login-page. */
        Assert.assertEquals("https://localhost:8181/IV1201/login.xhtml", 
                driver.getCurrentUrl());
        /* Insert username */
        driver.findElement(By.id("loginUsername")).sendKeys(username);
        /* Insert password */
        driver.findElement(By.id("loginPassword")).sendKeys(password);
        /* click login button */
        driver.findElement(By.id("j_idt30")).click();
        /* Check that login-failed-well is visible */
        Assert.assertTrue(driver.findElements(By.id("j_idt17")).size() > 0);
        /* Check that url is correct and that https is used */
        Assert.assertEquals("https://localhost:8181/IV1201/loginerror.xhtml", 
                driver.getCurrentUrl());
    }
    
    private void testNavigationFilters()
    {
        driver.navigate().to("https://localhost:8181/IV1201/applicant/index.xhtml");
        /* Make sure user is directed to login-page. */
        Assert.assertEquals("https://localhost:8181/IV1201/login.xhtml", 
                driver.getCurrentUrl());
        driver.navigate().to("https://localhost:8181/IV1201/recruit/index.xhtml");
        /* Make sure user is directed to login-page. */
        Assert.assertEquals("https://localhost:8181/IV1201/login.xhtml", 
                driver.getCurrentUrl());
    }
    
    private void testRegistration()
    {
        driver.navigate().to("http://localhost:8080/IV1201/");
        /* Click Login/Register page */
        driver.findElement(By.id("j_idt21")).click();
        /* Make sure user is directed to login-page. */
        Assert.assertEquals("https://localhost:8181/IV1201/login.xhtml", 
                driver.getCurrentUrl());
        /* Click Register link */
        driver.findElement(By.id("registerLink")).click();
        /* Check that registerTitle is visible */
        Assert.assertTrue(driver.findElements(By.id("registerTitle")).size() > 0);
        /* Check that URL is correct */
        Assert.assertEquals("https://localhost:8181/IV1201/register.xhtml", 
                driver.getCurrentUrl());
        String username = randomString();
        String password = randomString();
        /* Insert name */
        driver.findElement(By.id("regName")).sendKeys("test");
        /* Insert surnamep */
        driver.findElement(By.id("regSurname")).sendKeys("test");
        /* Insert email */
        driver.findElement(By.id("regEmail")).sendKeys("test@test.com");
        /* insert username */
        driver.findElement(By.id("regUsername")).sendKeys(username);
        /* Insert password */
        driver.findElement(By.id("regPassword")).sendKeys(password);        
        /* click register-button */
        driver.findElement(By.id("j_idt37")).click();        
        /* Check that registration was successful */
        Assert.assertEquals("https://localhost:8181/IV1201/index.xhtml", 
                driver.getCurrentUrl());
        /* Test login with new user */
        testApplicantLogin(username, password);
        
    }
    
    private void testFailedRegistration(String username, String password)
    {
        driver.navigate().to("http://localhost:8080/IV1201/");
        /* Click Login/Register page */
        driver.findElement(By.id("j_idt21")).click();
        /* Make sure user is directed to login-page. */
        Assert.assertEquals("https://localhost:8181/IV1201/login.xhtml",
                driver.getCurrentUrl());
        /* Click Register link */
        driver.findElement(By.id("registerLink")).click();
        /* Check that registerTitle is visible */
        Assert.assertTrue(driver.findElements(By.id("registerTitle")).size() > 0);
        /* Check that URL is correct */
        Assert.assertEquals("https://localhost:8181/IV1201/register.xhtml",
                driver.getCurrentUrl());
        /* Insert name */
        driver.findElement(By.id("regName")).sendKeys("test");
        /* Insert surnamep */
        driver.findElement(By.id("regSurname")).sendKeys("test");
        /* Insert email */
        driver.findElement(By.id("regEmail")).sendKeys("test@test.com");
        /* insert username */
        driver.findElement(By.id("regUsername")).sendKeys(username);
        /* Insert password */
        driver.findElement(By.id("regPassword")).sendKeys(password);        
        /* click register-button */
        driver.findElement(By.id("j_idt37")).click();        
        /* Check that registration failed (username already taken). */
        Assert.assertEquals("https://localhost:8181/IV1201/registererror.xhtml",
                driver.getCurrentUrl());        
    }
    
    private void testLocale()
    {
        driver.navigate().to("http://localhost:8080/IV1201/");
        /* Click swedish flag */
        driver.findElement(By.id("j_idt28:changeLocaleSv")).click();
        /* Check that the page is translated */
        Assert.assertEquals("Rekryteringssystem",driver.findElement(By.className("link_deco")).getText());
        /* Click english flag */
        driver.findElement(By.id("j_idt28:changeLocaleEn")).click();
        /* Check that the page is translated */
        Assert.assertEquals("Recruitment System",driver.findElement(By.className("link_deco")).getText());
    } 
    
    private String randomString()
    {
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();        
    }
}
