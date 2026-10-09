package com.nclh.dataProviders;

import com.nclh.constants.GlobalConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    public static String readUserData(String rowCell, String columnCell) {
        File file = new File(System.getProperty("user.dir") + "/testData/userData.xlsx");
        XSSFWorkbook xssfWorkbook = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        XSSFSheet xssfSheet = xssfWorkbook.getSheet("users");
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(GlobalConstants.QA_USERS_FILENAME);

        int rowNumber = 0, colNumber = 0;

        /**
         * Iteration of all rows in the sheet
         */

        for (Row row : xssfSheet) {
            Cell cell = row.getCell(0); // first cell in the row
            if (cell != null
                    && cell.getCellType() == CellType.STRING
                    && cell.getStringCellValue().equals(rowCell)) {
                rowNumber = row.getRowNum();
                System.out.println(rowNumber);
            }
        }

        /**
         * Iteration of cells on header row
         */
        Row headerRow = xssfSheet.getRow(0); // first cell in the header row
        for (Cell cell : headerRow) {
            if (cell != null
                    && cell.getCellType() == CellType.STRING
                    && cell.getStringCellValue().equals(columnCell)) {
                colNumber = cell.getColumnIndex();
                System.out.println(colNumber);
            }

        }

        Cell cellValue = xssfSheet.getRow(rowNumber).getCell(colNumber);
        System.out.println(cellValue);
        String actualCellValue = cellValue.toString();
        return actualCellValue;
    }

    public static String readUserData(String sheetName, String rowCell, String columnCell) {
        File file = new File(System.getProperty("user.dir") + "/testData/userData.xlsx");
        XSSFWorkbook xssfWorkbook = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);

        int rowNumber = 0, colNumber = 0;

        /**
         * Iteration of all rows in the sheet
         */

        for (Row row : xssfSheet) {
            Cell cell = row.getCell(0); // first cell in the row
            if (cell != null
                    && cell.getCellType() == CellType.STRING
                    && cell.getStringCellValue().equals(rowCell)) {
                rowNumber = row.getRowNum();
                System.out.println(rowNumber);
            }
        }

        /**
         * Iteration of cells on header row
         */
        Row headerRow = xssfSheet.getRow(0); // first cell in the header row
        for (Cell cell : headerRow) {
            if (cell != null
                    && cell.getCellType() == CellType.STRING
                    && cell.getStringCellValue().equals(columnCell)) {
                colNumber = cell.getColumnIndex();
                System.out.println(colNumber);
            }

        }

        Cell cellValue = xssfSheet.getRow(rowNumber).getCell(colNumber);
        System.out.println(cellValue);
        String actualCellValue = cellValue.toString();
        return actualCellValue;
    }

    public static String readUserData(String filename, String sheetName, int rowCell, String columnCell) {
        File file = new File(System.getProperty("user.dir") + "/testData/" + filename + ".xlsx");
        XSSFWorkbook xssfWorkbook = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);

        int rowNumber = 0, colNumber = 0;

        /**
         * Iteration of all rows in the sheet
         */

        for (Row row : xssfSheet) {
            Cell cell = row.getCell(0); // first cell in the row
            if (cell != null
                    && cell.getCellType() == CellType.STRING
                    && cell.getStringCellValue().equals(rowCell)) {
                rowNumber = row.getRowNum();
                System.out.println(rowNumber);
            }
        }

        /**
         * Iteration of cells on header row
         */
        Row headerRow = xssfSheet.getRow(0); // first cell in the header row
        for (Cell cell : headerRow) {
            if (cell != null
                    && cell.getCellType() == CellType.STRING
                    && cell.getStringCellValue().equals(columnCell)) {
                colNumber = cell.getColumnIndex();
                System.out.println(colNumber);
            }

        }

        Cell cellValue = xssfSheet.getRow(rowNumber).getCell(colNumber);
        System.out.println(cellValue);
        String actualCellValue = cellValue.toString();
        return actualCellValue;
    }

    public static String getCellValue(String sheetName, int sNo, String columnName) throws IOException {
        String filePath = System.getProperty("user.dir") + "/testData/guestCrewData.xlsx";
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName); // Get the specified sheet

        if (sheet == null) {
            workbook.close();
            throw new RuntimeException("Sheet '" + sheetName + "' not found.");
        }

        int columnIndex = -1;
        int rowIndex = -1;

        // Get the header row (first row)
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            workbook.close();
            throw new RuntimeException("Header row is missing in sheet '" + sheetName + "'.");
        }

        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                columnIndex = cell.getColumnIndex();
                break;
            }
        }

        if (columnIndex == -1) {
            workbook.close();
            throw new RuntimeException("Column '" + columnName + "' not found.");
        }

        // Find the row where S.No matches
        for (Row row : sheet) {
            Cell firstCell = row.getCell(0); // Assuming S.No is in the first column (index 0)
            if (firstCell != null && firstCell.getCellType() == CellType.NUMERIC) {
                if ((int) firstCell.getNumericCellValue() == sNo) {
                    rowIndex = row.getRowNum();
                    break;
                }
            }
        }

        if (rowIndex == -1) {
            workbook.close();
            throw new RuntimeException("S.No '" + sNo + "' not found.");
        }

        // Get the cell value from the located row and column
        Row dataRow = sheet.getRow(rowIndex);
        Cell cell = dataRow.getCell(columnIndex);

        String cellValue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                default:
                    cellValue = "";
            }
        }

        workbook.close();
        return cellValue;
    }

    public static void writeData(String sheetName, List<String> firstname, List<String> lastName) throws IOException {
        String filePath = System.getProperty("user.dir") + "/testData/guestCrewData.xlsx";
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("S.No");
            headerRow.createCell(1).setCellValue("FirstName");
            headerRow.createCell(2).setCellValue("LastName");

            for (int i = 0; i < firstname.size(); i++) {
                Row row = sheet.createRow(i + 1); // Row index starts from 1 (0 is for header)
                row.createCell(0).setCellValue(i + 1); // S.No (1-based index)
                row.createCell(1).setCellValue(firstname.get(i)); // First Name
                row.createCell(2).setCellValue(lastName.get(i)); // Last Name
            }

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            System.out.println("Data written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getNumberOfRows(String sheetName) {

        String filePath = System.getProperty("user.dir") + "/testData/guestCrewData.xlsx";
        int rowCount = 0;
        try (FileInputStream file = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName); // Get sheet by name

            if (sheet == null) {
                System.out.println("Sheet '" + sheetName + "' not found!");
            }

            for (Row row : sheet) {
                if (!isRowEmpty(row)) {
                    rowCount++; // Increment if row has data
                }
            }
            System.out.println("Total rows with data in sheet '" + sheetName + "': " + rowCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    private static boolean isRowEmpty(Row row) {
        if (row == null) return true;
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private static Map<String, Integer> getHeaderIndexMap(Sheet sheet) {
        Map<String, Integer> map = new HashMap<>();
        Row header = sheet.getRow(0);
        for (Cell cell : header) {
            map.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
        }
        return map;
    }

    public static List<Map<String, String>> getNVSData(String sheetName, String... columnHeaders) throws IOException {
        List<Map<String, String>> result = new ArrayList<>();
        String filePath = System.getProperty("user.dir") + "/testData/KAFKA_DATA.xlsx";

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            Map<String, Integer> headerIndexMap = new HashMap<>();
            for (Cell cell : headerRow) {
                String keyValue = cell.getStringCellValue().replaceAll(" ","");
                headerIndexMap.put(StringUtils.normalizeSpace(keyValue), cell.getColumnIndex());
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (String header : columnHeaders) {
                    int colIndex = headerIndexMap.get(header.trim());
                    Cell cell = row.getCell(colIndex);

                    String[] actualData = getCellValue(cell).split(":");
                    String name = actualData[1].replace("\"", "");
                    rowData.put(header, name);
                }
                result.add(rowData);
            }
        }
       // System.out.println(result);
        return result;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                    ? cell.getDateCellValue().toString()
                    : String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    public static List<Map<String, String>> getSeaWareData(String sheet1, String sheet2, String sheet1_col1, String sheet1_col2, String sheet2_col) {
        List<Map<String, String>> result = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "/testData/KAFKA_DATA.xlsx"));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet reservationSheet = workbook.getSheet("Reservations");
            Sheet guestProfileSheet = workbook.getSheet("Guest_Profile");

            // Get column index maps
            Map<String, Integer> reservationHeader = getHeaderIndexMap(reservationSheet);
            Map<String, Integer> guestHeader = getHeaderIndexMap(guestProfileSheet);

            int resIdCol = reservationHeader.get("RES_ID");
            int profileIdCol = reservationHeader.get("PROFILE_ID");
            int guestProfileIdCol = guestHeader.get("PROFILE_ID");

            // Loop through each row in Reservation sheet to get ReservationId dynamically
            for (int i = 1; i <= reservationSheet.getLastRowNum(); i++) {
                Row resRow = reservationSheet.getRow(i);
                if (resRow == null) continue;

                Cell resIdCell = resRow.getCell(resIdCol);
                Cell profileCell = resRow.getCell(profileIdCol);

                if (resIdCell == null || profileCell == null) continue;

                String reservationId = resIdCell.toString().trim();
                String profileId = profileCell.toString().trim();

                // System.out.println("ReservationId: " + reservationId + ", ProfileID: " + profileId);

                // Look for matching ProfileID in GuestProfile
                Map<String, String> rowData = new HashMap<>();
                for (int j = 1; j <= guestProfileSheet.getLastRowNum(); j++) {
                    Row guestRow = guestProfileSheet.getRow(j);
                    if (guestRow == null) continue;

                    Cell guestProfileCell = guestRow.getCell(guestProfileIdCol);
                    if (guestProfileCell == null) continue;

                    if (guestProfileCell.toString().trim().equals(profileId)) {
                        //System.out.println("→ GuestProfile Data for ProfileID " + profileId + ":");
                        for (Cell cell : guestRow) {
                            // System.out.print(cell.toString() + " | ");
                            String[] actualData = getCellValue(cell).split(":");
                            String name = actualData[1].replace("\"", "");
                            String[] key = cell.toString().split(":");
                            rowData.put(key[0], name);
                        }
                        break;
                    }
                }
                rowData.put("ReservationId",reservationId);
                result.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("********************************************************");
        // System.out.println(result.get(0));
        return result;
    }
}
