package Login;

import org.testng.Assert;
import org.openqa.selenium.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.io.File;
import jxl.*;



public class LoginClass {



    private WebDriver driver;

    private StringBuffer verificationErrors = new StringBuffer();


    @BeforeTest
    public void setUp() throws Exception {

            driver = new FirefoxDriver();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("Test\\Data\\data1.xls",
                "LogPass", "TestData");
        return(retObjArr);
    }

    @Test(dataProvider = "DP1")
    public void testUntitled(String Login, String Password) throws Exception {

        driver.get("http://truba.com");
        driver.findElement(By.className("enter")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Thread.sleep(2000);  - без этой задержки не работает

        driver.switchTo().frame("");
        Thread.sleep(3000);
        driver.findElement(By.name("username")).sendKeys(Login);

        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.name("password")).submit();


        if(!isElementPresent_1(driver,By.xpath(".//*[@id='error']"))){
            System.out.println("Логин "+Login+" и пароль "+Password+" - верны!");
            driver.findElement(By.className("exit")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else
        {
            System.out.println("Логин "+Login+" и пароль "+Password+" - неверны!");
        }
        }





    @AfterTest
    public void tearDown() throws Exception {

        driver.quit();

        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }
    public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception{
        String[][] tabArray=null;

        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        Sheet sheet = workbook.getSheet(sheetName);
        int startRow,startCol, endRow, endCol,ci,cj;
        Cell tableStart=sheet.findCell(tableName);
        startRow=tableStart.getRow();
        startCol=tableStart.getColumn();

        Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);

        endRow=tableEnd.getRow();
        endCol=tableEnd.getColumn();
        System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                "startCol="+startCol+", endCol="+endCol);
        tabArray=new String[endRow-startRow-1][endCol-startCol-1];
        ci=0;

        for (int i=startRow+1;i<endRow;i++,ci++){
            cj=0;
            for (int j=startCol+1;j<endCol;j++,cj++){
                tabArray[ci][cj]=sheet.getCell(j,i).getContents();
            }
        }


        return(tabArray);
    }
    public boolean isElementPresent_1(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}