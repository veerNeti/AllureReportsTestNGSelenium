package com.testdatareader;

import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CommaSeperatedValueReader {
    private static Logger logger = LogManager.getLogger(CommaSeperatedValueReader.class.getName());
    String pathname = System.getProperty("user.dir") + "/src/test/resources/testdata.csv";

    public List getCommaSeperatedValueReader() throws IOException {
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(pathname));
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
        List<String[]> strings = csvReader.readAll();
        return strings;
    }


}
