package com.realsteel.casestudy.lib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataHandlers {
	static Workbook book;
	static Sheet sheet;
	public static String TESTDATA_SHEET_PATH  = "F:\\Ashray\\Selenium\\Projects\\Casestudy\\Test-Data\\data.xlsx";
	public static String TESTDATA_SHEET_PATH2 = "F:\\Ashray\\Selenium\\Projects\\Casestudy\\Test-Data\\data2.xlsx";
	public static String CONFIGURATION_PATH   = "F:\\Ashray\\Selenium\\Projects\\Casestudy\\src\\main\\java\\com\\realsteel\\test\\config\\configuration.properties";
	public static String outputPath = System.getProperty("user.dir") + "\\Output_Files";
	public static void setDataToExcel(String fileName, String sheetName, int rowIndex, int cellIndex, String data) {
		try {
			File f = new File(TESTDATA_SHEET_PATH);
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet st = wb.getSheet(sheetName);
			Row r = st.getRow(rowIndex);
			Cell c = r.createCell(cellIndex);
			c.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(f);
			wb.write(fos);
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDataFromExcel(String filename, String sheetName, int rowIndex, int cellIndex) {
		String data = null;
		try {
			File f = new File(TESTDATA_SHEET_PATH);
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet st = wb.getSheet(sheetName);
			Row r = st.getRow(rowIndex);
			Cell c = r.getCell(cellIndex);
			data = c.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	public static void setDataToProperties(String fileName, String key, String value, String comment)

	{
		try {
			File f = new File(CONFIGURATION_PATH);
			FileInputStream fis = new FileInputStream(f);
			Properties p = new Properties();
			p.load(fis);
			p.put(key, value);
			FileOutputStream fos = new FileOutputStream(f);
			p.store(fos, comment);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static String getDataFromProperties(String fileName, String key) {
		String data = null;
		try {
			File f = new File(CONFIGURATION_PATH);
			FileInputStream fis = new FileInputStream(f);
			Properties p = new Properties();
			p.load(fis);
			data = p.getProperty(key).toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return data;

	}

	public static Object[][] getTestData(String sheetName)

	{
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
	
	public static Object[][] getTestData2(String sheetName)

	{
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH2);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

}
