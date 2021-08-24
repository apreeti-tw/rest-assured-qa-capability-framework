package utils;

import constants.FilePaths;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelUtils {
    private ExcelUtils(){}

    public static List<Map<String,String>> getTestData(String sheetName) throws IOException {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        XSSFWorkbook workbook = null;

        try (FileInputStream fis = new FileInputStream(FilePaths.getUsersExcelFilePath())){
            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int lastRowNum = sheet.getLastRowNum();
            int lastColNum = sheet.getRow(0).getLastCellNum();

            Map<String,String> MAP;

            for (int i = 1; i <= lastRowNum ; i++) {
                MAP = new HashMap<>();
                for (int j = 0; j < lastColNum; j++) {
                    MAP.put(sheet.getRow(0).getCell(j).getStringCellValue(), sheet.getRow(i).getCell(j).getStringCellValue());
                }
                result.add(MAP);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found at " + FilePaths.getUsersExcelFilePath());
        }

        return result;
    }
}
