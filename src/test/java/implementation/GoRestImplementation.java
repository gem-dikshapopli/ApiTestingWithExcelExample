package implementation;

import implementation.logger.Log;
import implementation.restutils.RestUtils;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/*
 Get the data from the api
 make the workbook
 make a sheet
 used .path method to add only specific values id, name, Email
 * @author-Diksha Popli
 * */
public class GoRestImplementation {
    @Test
    public static <JSONObject> void getData() throws IOException {
        Log.info("************************Get Data From The Excel*************************");
        Response response = RestUtils.getDataGoRest("users");
        XSSFWorkbook workbook = new XSSFWorkbook();
        // spreadsheet object
        XSSFSheet spreadsheet = workbook.createSheet(" Go Rest Data ");

        // creating a row object
        XSSFRow row;
        Map<String, Object[]> goRestData = new TreeMap<String, Object[]>();

        // Setting up the first row
        goRestData.put("1", new Object[]{"Id", "Name", "Email"});


        int count = 2;
        for (int i = 0; i < 10; i++) {
            goRestData.put("" + count, new Object[]{(response.path("[" + i + "].id")), (response.path("[" + i + "].name")), (response.path("[" + i + "].email"))});
            count++;
        }

        // writing on the Excel
        Set<String> keyId = goRestData.keySet();
        int rowId = 0;
        for (String key : keyId) {

            row = spreadsheet.createRow(rowId++);
            Object[] objectArr = goRestData.get(key);
            int cellId = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellId++);
                cell.setCellValue(obj.toString());
            }
        }
        // writing the workbook into the file...
        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Diksha.Popli\\Desktop\\excelSheetExample4.xlsx"));

        // writing on the file
        workbook.write(out);
        out.close();

    }
}
