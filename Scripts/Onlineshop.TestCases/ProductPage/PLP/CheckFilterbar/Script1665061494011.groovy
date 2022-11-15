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

WebUI.waitForPageLoad(5)

boolean resultnormalbar = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.checkIfStickyOrSlider'()

assert resultnormalbar == true

boolean resultCategory = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.checkCategoryButton'()

assert resultCategory == true

boolean resultMaterial = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.checkMaterialButton'()

assert resultMaterial == true

boolean resultOffers = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.checkOffersButton'()

assert resultOffers == true

boolean resultBrand = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.checkBrandButton'()

assert resultBrand == true

boolean resultPrice = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.checkPriceButton'()

assert resultPrice == true

boolean resultProducttype = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.checkProductTypButton'()

assert resultProducttype == true

boolean resultFilterandSort = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.checkFilterAndSortButton'()

assert resultFilterandSort == true

CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.setViewPortsizeToMobile'()

boolean resultstickybar = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.checkIfStickyOrSlider'()

assert resultstickybar == true

CustomKeywords.'onlineshop.Keywords.HomePageHelper.closeBrowser'()

