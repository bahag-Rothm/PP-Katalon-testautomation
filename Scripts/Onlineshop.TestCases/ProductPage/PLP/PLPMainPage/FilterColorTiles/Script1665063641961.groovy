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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

CustomKeywords.'onlineshop.Keywords.HomePageHelper.openBrowser'(countryId)

CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.navigateToOverview'(countryId, category, productNr)

boolean result = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.clickFilterAndSortButton'()

assert result == true

String Product = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.getProductNumberButtonText'()

boolean result2 = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.clickColorFilter'()

assert result2 == true

boolean result3 = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.clickFirstColor'()

assert result3 == true

boolean compare = true

String Product2 = CustomKeywords.'onlineshop.Keywords.ProductListingPageHelper.getProductNumberButtonText'()

if (Product == Product2) {
    return compare = false
}

assert compare == true

CustomKeywords.'onlineshop.Keywords.HomePageHelper.closeBrowser'()

