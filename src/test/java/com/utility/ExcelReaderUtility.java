package com.utility;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {

    public static Iterator<User> readExcelFile(String fileName)  {
        File xlsxFile = new File(System.getProperty("user.dir")+"/testdata/"+fileName);
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(xlsxFile);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<User> userList = new ArrayList<>();
        User userData;
        Iterator<Row> rowIterator= sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell emailAddressCell = row.getCell(0);
            Cell passwordCell = row.getCell(1);
            userData = new User(emailAddressCell.toString(),passwordCell.toString());
            userList.add(userData);
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList.iterator();
    }
}
