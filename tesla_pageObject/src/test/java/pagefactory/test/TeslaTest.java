package pagefactory.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.page.*;

public class TeslaTest {
    private WebDriver driver;
    @BeforeTest
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver", "C:/Webdriver/chromedriver.exe");
        driver=new ChromeDriver();
    }
    @Test
    public void testPayment(){
        String textFromPage = new TeslaHome(driver)
                .openPage()
                .choiseRegionOnMainPage()
                .choiseModelAndAddToOrder()
                .fillInAllInputs("Sasha","Kachan","test@gmail.com","+375297052890","5189967822379436","1021","123","210210")
                .confirm()
                .getTextFromConfirm();
        Assert.assertTrue( textFromPage.equals("There was a problem processing your payment. Please check and try a different payment method or contact your card issuing bank."),"lol");
    }
    @AfterTest
    public void browserKill(){
        driver.quit();
        driver=null;
    }

    @Test
    public void testShop(){
        String textFromLink = new TeslaShop(driver).openPage().findItem("Mode Onesie").getTextFromLink();
        Assert.assertTrue( textFromLink.equals("Kids Insane Mode Onesie"),"lol");
    }

}
