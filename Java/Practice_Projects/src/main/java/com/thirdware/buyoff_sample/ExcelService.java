package com.thirdware.buyoff_sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {

	private final Path root = Paths.get("C:\\Users\\BCP\\Desktop\\Practice Projects\\Excel Manipulation\\File from Angular");

	public int excelFile(MultipartFile excelFile) {
		try {
			Files.copy(excelFile.getInputStream(), this.root.resolve(excelFile.getOriginalFilename()));
			String filename = excelFile.getOriginalFilename();
			String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
			if (extension.equals("xls")) {
				if (readAndWriteXlsfile(excelFile, filename))
					return 0;
			}
			if (extension.equals("xlsx")) {
				if (readAndWriteXlsxfile(excelFile, filename))
					return 0;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private boolean readAndWriteXlsfile(MultipartFile xlsFile, String fileName) {
		try {
			CopyOneWBookToOther(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private boolean readAndWriteXlsxfile(MultipartFile xlsxFile, String fileName) {
		try {
			CopyOneWBookToOther(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	private void CopyOneWBookToOther(String fileName) throws IOException {
		// Step #1 : Locate path and file name of target and output excel.
		String TargetSheetPathAndName = "C:\\Users\\BCP\\Desktop\\Practice Projects\\Excel Manipulation\\File from Angular\\"+fileName;
		String NewSheetPathAndName = "C:\\Users\\BCP\\Desktop\\Practice Projects\\Excel Manipulation\\New Copied Files\\"+fileName;
		if (TargetSheetPathAndName != null && !"".equals(TargetSheetPathAndName.trim())) {
		try {
			File targetFile = new File(TargetSheetPathAndName.trim());
			FileInputStream inputStream = new FileInputStream(targetFile);
			XSSFWorkbook inputWorkbook = new XSSFWorkbook(inputStream);
			int targetSheetCount = inputWorkbook.getNumberOfSheets();
			System.out.println("Total no. of sheet(s) in the Target Workbook: " + targetSheetCount);
			File outputFile = new File(NewSheetPathAndName.trim());
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			XSSFWorkbook outputWorkbook = new XSSFWorkbook();
			// Step #2 : Creating sheets with the same name as appearing in target workbook.
			for (int i = 0; i < targetSheetCount; i++) {
			XSSFSheet targetSheet = inputWorkbook.getSheetAt(i);
			String inputSheetName = inputWorkbook.getSheetName(i);
			XSSFSheet outputSheet = outputWorkbook.createSheet(inputSheetName);
			copyExcelWB(targetSheet, outputSheet);
		}
		// Step #4 : Write all the sheets in the new Workbook using FileOutStream Object (Step 3 is mentioned below)
		outputWorkbook.write(outputStream);
		outputStream.close();
		inputStream.close();
		}
		catch (Exception ex) {
		System.out.println("Please check the target sheet given path and name: " + TargetSheetPathAndName);
		System.out.println();
		ex.printStackTrace();
		}
		}
}

	private void copyExcelWB(XSSFSheet targetSheet, XSSFSheet outputSheet) {
		int rowCount = targetSheet.getLastRowNum();
		System.out.println("There are " + rowCount + " rows in the Target workbook with sheet name -" + "‘"
				+ targetSheet.getSheetName() + "‘");
		int currentRowIndex = 0;
		if (rowCount > 0) {
			Iterator<Row> rowIterator = targetSheet.iterator();
			while (rowIterator.hasNext()) {
				int currentCellIndex = 0;
				Iterator<Cell> cellIterator = ((Row) rowIterator.next()).cellIterator();
				while (cellIterator.hasNext()) {
					// Step #3: Creating new Row, Cell and Input value in the newly created sheet.
					String cellData = cellIterator.next().toString();
					if (currentCellIndex == 0)
						outputSheet.createRow(currentRowIndex).createCell(currentCellIndex).setCellValue(cellData);
					else
						outputSheet.getRow(currentRowIndex).createCell(currentCellIndex).setCellValue(cellData);
					currentCellIndex++;
				}
				currentRowIndex++;
			}
			System.out
					.println("Total " + (currentRowIndex - 1) + " rows are Copied in the new Workbook with sheet name- "
							+ "'" + outputSheet.getSheetName() + "‘");
		}
	}
	
	
}