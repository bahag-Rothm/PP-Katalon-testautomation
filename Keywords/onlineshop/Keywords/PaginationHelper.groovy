package onlineshop.Keywords
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.RenderingHints.Key
import java.lang.invoke.SwitchPoint
import java.sql.Driver
import java.util.concurrent.Delayed

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByTagName
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
public class PaginationHelper {


	//Checks if the last seen icon is visible
	@Keyword
	def lastSeenPage() {
		String lastSeenIcon = WebUI.verifyElementInViewport(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Sidebar/LastSeen'), 3, FailureHandling.OPTIONAL)
		KeywordUtil.logInfo(lastSeenIcon);
		if(lastSeenIcon == 'true') {
			//click last seen Icon
			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Sidebar/LastSeen'))
			KeywordUtil.logInfo('Sidebar opened');
			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Sidebar/CloseIconSideBar'))
			KeywordUtil.logInfo('Sidebar closed');
			return true
		}else {
			KeywordUtil.logInfo('Sidebar did not open');
			return false
		}
	}

	//Checks if the right number is shown
	@Keyword
	def showPagination() {
		String numES = 104;
		String num = 108;
		String paginationNum = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/NumberOfShownProducts'), FailureHandling.OPTIONAL)
		KeywordUtil.logInfo(num);
		KeywordUtil.logInfo(paginationNum);
		if(paginationNum == num || paginationNum == numES) {
			KeywordUtil.logInfo('Pagination shows the right Number');
			return true
		}else {
			KeywordUtil.logInfo('Pagination shows the wrong Number');
			return false
		}
	}
	
	//clicks on a product in the first block
	@Keyword
	def clickProductInTheFirstBlock() {

		String product = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/ClickedRandomProducts'), 3, FailureHandling.OPTIONAL)
		if(product == 'true') {

			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/ClickedRandomProducts'))
			return true
		}
	}

	//clicks on a product in the third block
	@Keyword
	def clickProductInTheThirdBlock() {

		String product = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/ClickedProductInTheFirstBlock'), 3, FailureHandling.OPTIONAL)
		if(product == 'true') {

			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/ClickedProductInTheFirstBlock'))
			return true
		}
	}

	//checks the text from the load more button
	@Keyword
	def checkLoadMoreProducts() {
		//First it is the "load more Products" button, after the second call it is the "see previous products button"
		String button = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Test'),FailureHandling.OPTIONAL)
		KeywordUtil.logInfo(button);
		if(button.contains("Mehr Produkte anzeigen")|| button.contains("Mostrar más productos") || button.contains("Bekijk meer producten") || button.contains("Prikaži više proizvoda") ) {

			KeywordUtil.logInfo(button);
			return true
		}


	}
	
	//checks the text from the load previous button
	@Keyword
	def checkLoadPreviousProduct() {
		//First it is the "load more Products" button, after the second call it it the "see previous products button"
		String button = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Test'),FailureHandling.OPTIONAL)
		KeywordUtil.logInfo(button);
		if(button.contains("Mostrar productos anteriores")|| button.contains("Vorherige Produkte anzeigen") || button.contains("Bekijk eerdere producten") || button.contains("Prikaži prethodne proizvode") ) {

			KeywordUtil.logInfo(button);
			return true
		}


	}
	
	//clicks on a product in the fourth block
	@Keyword
	def clickProductInTheFourthBlock() {

		String product = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/ClickedProductInTheFourthBlock'), 3, FailureHandling.OPTIONAL)
		if(product == 'true') {

			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/ClickedProductInTheFourthBlock'))
			return true
		}
	}
	//checks if a product is still present
	@Keyword
	def verifyProductPresent() {

		String product = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/ClickedRandomProducts'), 3, FailureHandling.OPTIONAL)
		if(product == 'true') {

			KeywordUtil.logInfo('Products is still present');
			return true
		}
	}

	//checks if a product is still present in the third block
	@Keyword
	def verifyProductPresentThirdBlock() {

		String product = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/ClickedProductInTheFirstBlock'), 3, FailureHandling.OPTIONAL)
		if(product == 'true') {

			KeywordUtil.logInfo('Products is still present');
			return true
		}
	}

	//checks if a product is still present in the fourth block
	@Keyword
	def verifyProductPresentFourth() {

		String product = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/Pagination/ClickedProductInTheFourthBlock'), 3, FailureHandling.OPTIONAL)
		if(product == 'true') {

			KeywordUtil.logInfo('Products is still present');
			return true
		}
	}



}
