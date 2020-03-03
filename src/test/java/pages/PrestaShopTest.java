package pages;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PrestaShopTest {
    static WebDriver driver;
    @BeforeClass
    public static void startBrowser() {
        System.out.println("Starting the browser");
    }
    @Before
    public void goHome() {
        driver = new ChromeDriver();
        System.out.println("Going home");
        driver.get("http://demo.prestashop.com/#/en/front");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String actual=driver.getTitle();
        String expected= "PrestaShop Live Demo";
        Assert.assertEquals(actual,expected);
        System.out.println("Assert Passed");
    }
    @Test
    //As a new user i would like to be able sign In and click on no account so that I am able to be a member
    public void signInAndNewUserAccount() {
        WebDriver driver1 = driver.switchTo().frame(driver.findElement(By.id("framelive")));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
        driver.findElement(By.cssSelector(".no-account")).click();
    }

    @Test
    //As a new user ,I want to be able to fill my details so taht I can be a member
    public void creatingAccount() throws InterruptedException {
        driver.switchTo().frame("framelive");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
        driver.findElement(By.cssSelector(".no-account")).click();
        driver.findElement(By.name("id_gender")).click();
        driver.findElement(By.name("firstname")).sendKeys("Max");
        driver.findElement(By.name("lastname")).sendKeys("Johansson");
        driver.findElement(By.name("email")).sendKeys("oogieboogie@hotmail.com");
        driver.findElement(By.name("password")).sendKeys("Autotesting");
        driver.findElement(By.name("birthday")).sendKeys("10/15/1999");
        driver.findElement(By.name("psgdpr")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".form-control-submit")).click();

    }

    @Test
    //As a user,I want to select accessories category so that I can shop the required item
    public void accessoriesSelectionAndAddAShopToCart() {
        driver.switchTo().frame("framelive");
        driver.manage().window().maximize();
        driver.findElement(By.id("_desktop_top_menu")).click();
        driver.findElement(By.cssSelector(".thumbnail-container")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']")).click();

    }

    @Test
    // As a user I want to contact customer service so that I can provide my feedback or complaint where required
    public void contactUs() throws InterruptedException {
        driver.switchTo().frame("framelive");
        driver.manage().window().maximize();
        driver.findElement(By.id("contact-link")).click();
        driver.findElement(By.cssSelector("div.col-md-6 input.form-control")).sendKeys("tishubanga@hotmail.com");
        driver.findElement(By.name("message")).sendKeys("Enquiry regarding my order");
        Thread.sleep(5000);
        driver.findElement(By.tagName("form")).submit();
    }

    @Test
    //As a user,I am able to select my price range so that I can shop within my budget
    public void priceRangeAdjust() {
        driver.switchTo().frame("framelive");
        driver.manage().window().maximize();
        driver.findElement(By.id("_desktop_top_menu")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector(".ui-slider-handle"));
       // By locator = By.cssSelector(".ui-slider-handle");
        wait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(".ui-slider-handle"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".ui-slider-handle"))));
        WebElement sliderRight = driver.findElement((By.cssSelector(".ui-slider-handle")));
        Actions a = new Actions(driver);
        a.dragAndDropBy(sliderRight, 40, 0).build().perform();
    }

    @Test
    //As a new user,I want to be able to search what i want so that i can save time surfing each element
    public void searchItem() {
        driver.switchTo().frame("framelive");
        driver.manage().window().maximize();
        driver.findElement(By.className("ui-autocomplete-input")).sendKeys("art");
        driver.findElement(By.cssSelector("#search_widget > form > button > i")).click();

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}

