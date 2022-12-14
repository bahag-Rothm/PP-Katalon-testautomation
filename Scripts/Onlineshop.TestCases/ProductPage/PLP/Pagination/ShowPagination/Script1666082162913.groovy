import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CustomKeywords.'onlineshop.Keywords.HomePageHelper.openBrowser'(countryId)

CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.navigateToOverview'(countryId, category, productNr)

WebUI.callTestCase(findTestCase('Onlineshop.TestCases/SharedFolder/ClickMehrProdukteAnzeigen'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Onlineshop.TestCases/SharedFolder/ClickMehrProdukteAnzeigen'), [:], FailureHandling.STOP_ON_FAILURE)


boolean result = CustomKeywords.'onlineshop.Keywords.PaginationHelper.showPagination'()

assert result == true

boolean result2 = CustomKeywords.'onlineshop.Keywords.PaginationHelper.clickProductInTheFirstBlock'()
assert result2 == true
WebUI.back()

boolean result3 = CustomKeywords.'onlineshop.Keywords.PaginationHelper.verifyProductPresent'()
assert result3 == true
CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.setViewPortsizeToMobile'()

boolean result4 = CustomKeywords.'onlineshop.Keywords.PaginationHelper.showPagination'()

assert result4 == true

boolean result5 = CustomKeywords.'onlineshop.Keywords.PaginationHelper.clickProductInTheFirstBlock'()
assert result5 == true
WebUI.back()

boolean result6 = CustomKeywords.'onlineshop.Keywords.PaginationHelper.verifyProductPresent'()
assert result6 == true

CustomKeywords.'onlineshop.Keywords.HomePageHelper.closeBrowser'()

