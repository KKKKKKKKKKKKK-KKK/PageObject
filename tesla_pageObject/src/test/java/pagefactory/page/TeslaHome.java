package pagefactory.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomWait;
import org.openqa.selenium.support.PageFactory;
public class TeslaHome {

    public static final String DEFAULT_URL= "https://www.tesla.com/";
    private WebDriver driver;
    @FindBy (xpath = "//li[@class='region-item i18n-en_us']/child::a[@title='United States' and text()='United States']")
    private WebElement choiseRegion;

    @FindBy (xpath = "//nav[@id='block-mainheadernavigation']/descendant::li[@class='tds-menu-header-nav--list_item']/child::a[text()='Model 3']")
    private WebElement choiseModel;

    @FindBy (xpath = "//a[@class='tds-btn tds-o-btn tds-btn--outline tds-btn--white tcl-button' and text()='Order Now']/parent::div[@class='hero-callouts--button cmp-animate--to_reveal cmp-animate--revealed']")
    private WebElement orderNow;

    @FindBy (xpath = "//h2[@class='packages-options--nav-title']/child::span[text()='Payment']")
    private WebElement payment;

    public TeslaHome(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public TeslaHome openPage() {
        driver.get(DEFAULT_URL);
        new WebDriverWait(driver,10).until(CustomWait.jQueryAJAXsCompleted());
        return this;
    }
    public TeslaHome choiseRegionOnMainPage(){
        (new WebDriverWait(driver, 50L)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='locale-modal' and @open]")));
        new WebDriverWait(driver, 50L).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='region-item i18n-en_us']/child::a[@title='United States' and text()='United States']")));
        new WebDriverWait(driver,10).until(CustomWait.jQueryAJAXsCompleted());
        choiseRegion.click();
        return this;
    }
    public TeslaOrderPage choiseModelAndAddToOrder(){
        new WebDriverWait(driver,10).until(CustomWait.jQueryAJAXsCompleted());
        choiseModel.click();
        orderNow.click();
        payment.click();
        return new TeslaOrderPage(driver);
    }
}
