package onlineshop.Keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.microsoft.schemas.office.visio.x2012.main.CellType
import com.sun.java.util.jar.pack.Instruction
import com.kms.katalon.core.util.KeywordUtil


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import internal.GlobalVariable as Mapper

public class GlobalHelper {

	public static getCountryId(String countryId) {
		Map<String,String> countryIds = new HashMap<String,String>();
		countryIds.put("DE", "https://pps-qs-intern.bauhaus.info");
		countryIds.put("AT", "https://pps-qs-intern.bauhaus.at");
		countryIds.put("NL", "https://pps-qs-intern.nl.bauhaus");
		countryIds.put("ES", "https://pps-qs-intern.bauhaus.es");
		countryIds.put("HR", "https://pps-qs-intern.bauhaus.hr");
		countryIds.put("CZ", "https://pps-qs-intern.bauhaus.cz");

		return countryIds.get(countryId);
	}


	public static getMapperByCountryId(String countryId, String key) {
		Map links = new HashMap();
		String link = new String();

		switch (countryId) {
			case "DE":
				links =  (Map) Mapper.DE;
				System.out.println(links);
				link = links.get(key);
				System.out.print(link);
				break;
			case "AT":
				links =  (Map) Mapper.AT;
				System.out.println(links);
				link = links.get(key);
				System.out.print(link);
				break;
			case "NL":
				links =  (Map) Mapper.NL;
				System.out.println(links);
				link = links.get(key);
				System.out.print(link);
				break;
			case "ES":
				links =  (Map) Mapper.ES;
				System.out.println(links);
				link = links.get(key);
				System.out.print(link);
				break;
			case "HR":
				links =  (Map) Mapper.HR;
				System.out.println(links);
				link = links.get(key);
				System.out.print(link);
				break;
			case "CZ":
				links =  (Map) Mapper.CZ;
				System.out.println(links);
				link = links.get(key);
				System.out.print(link);
				break;
		}

		return link;
	}

	public static List<List<Object>> getDataFormExcelSheet(String filePath, String sheetName) {
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		String sheetNameTrim = sheetName.trim();
		XSSFSheet sheet = workbook.getSheet(sheetNameTrim);
		System.out.print("\nSheet name is: '" + sheet.getSheetName()+"'")

		System.out.print("\nFirst cell in this sheet is: " + sheet.getRow(0).getCell(0).getStringCellValue())

		int rowCount = sheet.getLastRowNum();
		List rowCollection = []

		for (int i = 1; i < rowCount + 1; i++){
			Row row
			row = sheet.getRow(i)
			List values = []
			short size = row.getLastCellNum()
			for (int j = 0; j < size ; j++){
				Cell cell = row.getCell(j)
				String value;
				def type = cell.getCellTypeEnum().name();
				if(type == "NUMERIC") {
					int intValue = (int)cell.getNumericCellValue()
					value = Integer.toString(intValue)
				}
				else {
					value = cell.getStringCellValue()
				}
				values.add(value)
			}
			rowCollection.add(values)
		}

		fis.close();
		System.out.print("\nFound data are: " + rowCollection.size())

		return rowCollection
	}

	@Keyword
	def checkTextIfContains(String givenText, String checkedString) {

		Boolean flag = false

		if (checkedString.contains(givenText) && checkedString!=null && checkedString!="") {
			System.out.println((givenText + ' is contains: ') + checkedString)
			flag = true
		} else {
			System.out.println(givenText + ' is not contains: ' + checkedString)
			KeywordUtil.markFailedAndStop(givenText + ' is not contains: ' + checkedString);
		}

		System.out.println('Return value is: ' + flag)
		return flag;
	}
}
