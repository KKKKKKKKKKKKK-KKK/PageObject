package pagefactory.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomWait;
import org.openqa.selenium.support.PageFactory;
public class TeslaShop {

    public static final String DEFAULT_URL= "https://www.tesla.com/";
    private WebDriver driver;
    @FindBy (xpath = "//li[@class='region-item i18n-en_us']/child::a[@title='United States' and text()='United States']")
    private WebElement choiseRegion;

    @FindBy (xpath = "//ol[@class='tds-menu-header-nav--list tds-menu-header-nav--primary_right']/descendant::a[@class='tds-menu-header-nav--list_link' and text()='Shop']")
    private WebElement shop;

    @FindBy (xpath = "//input[@id='searchTerm']")
    private WebElement searchInput;

    @FindBy (xpath = "//form[@id='searchform']")
    private WebElement searchInputButton;

    @FindBy (xpath = "//a[@class='product-tile__link active' and text()='Kids Insane Mode Onesie']")
    private WebElement searchResault;

    public TeslaShop(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public TeslaShop openPage() {
        driver.get(DEFAULT_URL);
        new WebDriverWait(driver,10).until(CustomWait.jQueryAJAXsCompleted());
        return this;
    }
    public TeslaShop choiseRegion(){
        (new WebDriverWait(driver, 50L)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='locale-modal' and @open]")));
        choiseRegion.click();
        return this;
    }
    public TeslaShop findItem(String item){
        new WebDriverWait(driver, 10L).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ol[@class='tds-menu-header-nav--list tds-menu-header-nav--primary_right']/descendant::a[@class='tds-menu-header-nav--list_link' and text()='Shop']")));
        shop.click();
        new WebDriverWait(driver, 10L).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='searchform']")));
        searchInputButton.click();
        searchInput.sendKeys(item);
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }
    public String getTextFromLink(){
        new WebDriverWait(driver, 10L).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='product-tile__link active' and text()='Kids Insane Mode Onesie']")));
        return searchResault.getText();
    }
}
