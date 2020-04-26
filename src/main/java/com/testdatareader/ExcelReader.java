package com.testdatareader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private static Logger logger = LogManager.getLogger(ExcelReader.class.getName( ));
    String pathname = System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx";
    private FileInputStream inputStream;

    public Object[][] extractDataFromExcel() throws Exception {
        FileInputStream fis = new FileInputStream(pathname);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        wb.close( );
        int lastRowNum = sheet.getLastRowNum( );
        int lastCellNum = sheet.getRow(0).getLastCellNum( );
        Object[][] obj = new Object[lastRowNum][1];
        for (int i = 0; i < lastRowNum; i++) {
            Map<Object, Object> datamap = new HashMap<>( );
            for (int j = 0; j < lastCellNum; j++) {
                datamap.put(sheet.getRow(0).getCell(j).toString( ), sheet.getRow(i + 1).getCell(j).toString( ));
            }
            obj[i][0] = datamap;

        }
        logger.info("TestData from Excel:\n" + obj);
        return obj;
    }
}

