package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static final String path = "src/test/resources/TestData.xlsx";
	private static final String sheetName = "Sheet1";
	
	
	public static Map<String, String> getTestData(String testName){
		
		Map<String,String> data = new HashMap<>();
		
		try {
			FileInputStream fis = new FileInputStream(path);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheet(sheetName);
			
			Row header = sheet.getRow(0);
			String key, value ="";
			
			
			for(Row row : sheet) {
				//row in column A matches the value of the test case name 
				if(row.getCell(0).getStringCellValue().equalsIgnoreCase(testName)) {
					
					for(int i=2;i<=row.getLastCellNum();i++) {
						key=header.getCell(i).toString();
						
						try {
							value = row.getCell(i).toString();
						}catch(NullPointerException e) {
							value ="";
						}
						data.put(key, value);
					}
					break;
				}
			}
			
			wb.close();
			fis.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void updateResult(String testName, String status) {
		try {
			FileInputStream fis = new FileInputStream(path);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheet(sheetName);

			for(Row row: sheet) {
				if(row.getCell(0).getStringCellValue().equalsIgnoreCase(testName)) {
					row.createCell(1).setCellValue(status);
					break;
				}
			}
			
			try(FileOutputStream fos = new FileOutputStream(path)){
				wb.write(fos);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			wb.close();
			fis.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
