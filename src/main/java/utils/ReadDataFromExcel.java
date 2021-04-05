package utils;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {

	public static List<LinkedHashMap<String, String>> getExcelDataAsMap(String excelFilePath, String sheetName)
			throws EncryptedDocumentException, IOException {

		// Initialize the list to the data in the same order 
		List<LinkedHashMap<String, String>> datasetList = new ArrayList<>();;
		
		try {
			
			// Create a Workbook
			Workbook wb = WorkbookFactory.create(new File(excelFilePath));			
			// Get the sheet of the specific test case
			Sheet s = wb.getSheet(sheetName);				
			// Get the column count
			int colCount = s.getRow(0).getPhysicalNumberOfCells();	
			
			// Ignore first label column 			
			for (int i = 1; i < colCount; i++) {				
				// Create a map to store each data set
				LinkedHashMap<String, String> dataset = new LinkedHashMap<>();				
				// Get the row count
				int rowCount = s.getPhysicalNumberOfRows();			
				// Iterate for all the column (Each column is new dataset)
				for (int j = 0; j < rowCount; j++) {
					Row r = s.getRow(j);
					// Get field name from first column (index = 0)
					String fieldName = r.getCell(0).getStringCellValue();
					String fieldValue = r.getCell(i).getStringCellValue();
					// Add name=value pair in map
					dataset.put(fieldName, fieldValue);
				}
				// Add all mapping to the list 
				datasetList.add(dataset);
			}
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Remove dataset where TC_EXECUTION=SKIP
		datasetList = removeSkipTest(datasetList);
		return datasetList;
	}

	
	public static List<LinkedHashMap<String, String>> removeSkipTest(
			List<LinkedHashMap<String, String>> mapDataList) {
		mapDataList.removeIf(p -> p.toString().contains("TC_EXECUTION=SKIP"));
		return mapDataList;
	}


    
}