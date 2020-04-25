package com.testdatareader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    String pathname = System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx";
    private static final Logger logger = LogManager.getLogger(ExcelReader.class);
    private FileInputStream inputStream;
    private String aut;
    private String userName;
    private String password;
    private String browser;
    private boolean headlessflg;

    public ExcelReader() {
        try {
            inputStream = new FileInputStream(new File(pathname));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();
            Row row;
            int numberOFColumns;
            while (iterator.hasNext()) {
                iterator.next();
                int physicalNumberOfRows = firstSheet.getPhysicalNumberOfRows();
                for (int rowIndex = 0; rowIndex < physicalNumberOfRows - 1; rowIndex++) {
                    row = firstSheet.getRow(rowIndex);
                    numberOFColumns = row.getPhysicalNumberOfCells();

                    for (int col = 1; col < numberOFColumns; col++) {
                        Cell keyCell = row.getCell(col-1);
                        Cell valueCell = row.getCell(col-1);

                        if (Objects.nonNull(valueCell)) {
                            switch (valueCell.getCellType()) {
                                case BOOLEAN:
                                    this.headlessflg = valueCell.getBooleanCellValue();
                                    break;
                                case STRING:
                                    switch (valueCell.getStringCellValue()) {
                                        case "aut": {
                                            this.aut = row.getCell(col).getStringCellValue();
                                            break;
                                        }
                                        case "username": {
                                            this.userName = row.getCell(col).getStringCellValue();
                                            break;
                                        }
                                        case "password": {
                                            this.password = Base64.getEncoder().encodeToString(row.getCell(col).getStringCellValue().getBytes());
                                            break;
                                        }
                                        case "browser": {
                                            this.browser = row.getCell(col).getStringCellValue();
                                            break;
                                        }
                                        default:
                                            break;
                                    }
                                    break;
                                case ERROR:
                                    System.out.println(row.getCell(col).getErrorCellValue());
                                    break;
                            }


                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "ExcelReader{" +
                "inputStream=" + inputStream +
                ", aut='" + aut + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", browser='" + browser + '\'' +
                ", headlessflg=" + headlessflg +
                '}';
    }

    public String getAut() {
        return aut;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBrowser() {
        return browser;
    }

    public boolean isHeadlessflg() {
        return headlessflg;
    }
}