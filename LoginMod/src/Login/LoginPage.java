package Login;
import org.openqa.selenium.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: эрик
 * Date: 30.07.13
 * Time: 23:51
 * To change this template use File | Settings | File Templates.
 */

public class LoginPage {

//    @FindBy (className="enter")
//    public WebElement enterLocator;
  //  By enterLocator = By.className("enter");

    @FindBy(xpath = "//*[@name='username']")
    public WebElement usernameLocator;

    @FindBy (name="password")
    public WebElement passwordLocator;

    By ErrorLocator =By.xpath(".//*[@id='error']");


    public LoginPage(WebDriver driver)
    {
        super();
        PageFactory.initElements(driver, this);
        driver.get("http://truba.com");
        driver.findElement(By.className("enter")).click();
    //    enterLocator.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.switchTo().frame("");
    }

    public void login(String username, String password) {
        // In case if there are previously entered values,
        // clear the fields
        usernameLocator.clear();
        passwordLocator.clear();

        // Type user name and password
        usernameLocator.sendKeys(username);
        passwordLocator.sendKeys(password);

        // Submit user name and password
        passwordLocator.submit();
    }
    public void Test(WebDriver driver, String username, String password) {
        if(!isElementPresent_1(driver,ErrorLocator)){
            System.out.println("Логин "+username+" и пароль "+password+" - верны!");
            driver.findElement(By.className("exit")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else
        {
            System.out.println("Логин "+username+" и пароль "+password+" - неверны!");
        }


    }

    public boolean isElementPresent_1(WebDriver driver, By element) {
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
