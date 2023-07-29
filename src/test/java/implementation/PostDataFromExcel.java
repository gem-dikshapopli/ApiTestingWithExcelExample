package implementation;

import implementation.logger.Log;
import implementation.restutils.RestUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

/*
 * Read the Excel file from the system
 * add the dependency appache-poi from the maven
 * XSSFWorkbook to make the workbook on Excel
 * XSSFSheet to make the sheet on the Excel
 * Read the data from excel and put it in the map
 * adding data on the map LinkedHashMap for not changing the sequence of the data
 * added all the maps in a List and then post the payload using post method
 * @author-Diksha Popli
 * */
public class PostDataFromExcel {
    @Test
    public static void postData() throws IOException {

        Log.info("***************************Post Data From The Excel**********************");

        FileInputStream file = new FileInputStream("C:\\Users\\Diksha.Popli\\Desktop\\userdata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        LinkedHashMap<String,String> user1Data=new LinkedHashMap<String,String>();
        LinkedHashMap<String,String> user2Data=new LinkedHashMap<String,String>();
        LinkedHashMap<String,String> user3Data=new LinkedHashMap<String,String>();
        LinkedHashMap<String,String> user4Data=new LinkedHashMap<String,String>();

        for (int i=0;i<sheet.getLastRowNum();i++){
                String key = sheet.getRow(0).getCell(i).getStringCellValue();
                String value = sheet.getRow(1).getCell(i).getStringCellValue();
                user1Data.put(key, value);
        }
        for (int i=0;i<sheet.getLastRowNum();i++){
            String key = sheet.getRow(0).getCell(i).getStringCellValue();
            String value = sheet.getRow(2).getCell(i).getStringCellValue();
            user2Data.put(key, value);
        }
        for (int i=0;i<sheet.getLastRowNum();i++){
            String key = sheet.getRow(0).getCell(i).getStringCellValue();
            String value = sheet.getRow(3).getCell(i).getStringCellValue();
            user3Data.put(key, value);
        }
        for (int i=0;i<sheet.getLastRowNum();i++){
            String key = sheet.getRow(0).getCell(i).getStringCellValue();
            String value = sheet.getRow(4).getCell(i).getStringCellValue();
            user4Data.put(key, value);
        }

        RestUtils.postDataGoRest(user1Data,"users");
        RestUtils.postDataGoRest(user2Data,"users");
        RestUtils.postDataGoRest(user3Data,"users");
        RestUtils.postDataGoRest(user4Data,"users");
    }
}
