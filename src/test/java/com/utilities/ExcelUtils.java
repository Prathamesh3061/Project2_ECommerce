package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    // ========================
    // Variables
    // ========================

    private Workbook workbook;    // represents the whole Excel file
    private Sheet sheet;          // represents one sheet inside Excel

    // ========================
    // Constructor
    // ========================

    // When ExcelUtils object is created, we open the file immediately
    public ExcelUtils(String filePath, String sheetName) throws IOException {

        // Open the Excel file
        FileInputStream fis = new FileInputStream(filePath);

        // Load it into workbook object
        workbook = new XSSFWorkbook(fis);

        // Select the sheet by name
        sheet = workbook.getSheet(sheetName);

        System.out.println("Excel file loaded: " + filePath);
        System.out.println("Sheet selected: " + sheetName);
    }

    // ========================
    // Method 1 — Get Row Count
    // ========================

    // Returns how many DATA rows exist (excluding header row)
    public int getRowCount() {
        return sheet.getLastRowNum(); // row 0 = header, so this gives data rows count
    }

    // ========================
    // Method 2 — Get Cell Data
    // ========================

    // Returns value of a specific cell as String
    // rowIndex 1 = first data row (row 0 is header)
    public String getCellData(int rowIndex, int colIndex) {

        Row row = sheet.getRow(rowIndex);

        // If row is empty (blank row in excel)
        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(colIndex);

        // If cell is empty
        if (cell == null) {
            return "";
        }

        // Return cell value as String
        return cell.toString();
    }

    // ========================
    // Method 3 — Close File
    // ========================

    public void closeFile() throws IOException {
        workbook.close();
        System.out.println("Excel file closed!");
    }
}
