package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class BaseTestCases extends ReadProperty {

	private MobileDriver<MobileElement> driver;

	public void readexcel() throws IOException {
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "/TestData.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheetAt(0);
		FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		for (Row row : sheet) {
			for (Cell cell : row) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING: 
					System.out.print(cell.getStringCellValue() + "\t\t\t");
					break;
				case Cell.CELL_TYPE_NUMERIC: 
					System.out.print(cell.getNumericCellValue() + "\t\t\t");
					break;
				default:
				}
			}
			System.out.println();
		}
	}
}
