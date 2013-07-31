package Login;


import org.testng.annotations.*;
import java.io.*;
import jxl.*;
import org.testng.ITestContext;
import org.openqa.selenium.support.PageFactory;
/**
 * Created with IntelliJ IDEA.
 * User: эрик
 * Date: 30.07.13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class TestDataProvider {

    @DataProvider
    public static Object[][] createData1(ITestContext context)
            throws Exception {

        String inputFile = context.getCurrentXmlTest().getParameter(
                "filenamePath");
        String inputSheet = context.getCurrentXmlTest().getParameter(
                "sheetName");
        String inputProvider = context.getCurrentXmlTest().getParameter(
                "provider");

        Object[][] retObjArr=getTableArray(inputFile,
                inputSheet, inputProvider);
        return(retObjArr);
    }
    public static String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception{
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
}
