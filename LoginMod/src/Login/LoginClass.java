package Login;


import org.testng.Assert;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;



public class LoginClass {


    private WebDriver driver;

    private StringBuffer verificationErrors = new StringBuffer();


    @BeforeClass
    public void setUp() throws Exception {

            driver = new FirefoxDriver();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
    }
    @AfterClass
    public void tearDown() throws Exception {

        driver.quit();

        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }
    @Test(dataProviderClass = TestDataProvider.class,dataProvider = "createData1")
    public void testLogin(String Login, String Password) throws Exception {
        LoginPage page = new LoginPage(driver);
        page.login(Login, Password);
        page.Test(driver,Login,Password);


    }



}