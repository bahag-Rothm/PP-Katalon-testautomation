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

CustomKeywords.'onlineshop.Keywords.HomePageHelper.openBrowser'(COUNTRY)

'Search for an item and display the results on the product overview page'
WebUI.callTestCase(findTestCase('SharedSteps/Onlineshop/SetAndSelectSearchElement'), [('ITEMNUMBER') : ITEM], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'onlineshop.Keywords.ComparisonPageHelper.selectCompareProductAddSearchSelect'()

CustomKeywords.'onlineshop.Keywords.ComparisonPageHelper.selectPopUpCompareProductAddSearchSelect'()

CustomKeywords.'bahag.AssertHelper.assertTrue'('The product is not on the Comparison page', CustomKeywords.'onlineshop.Keywords.ProduktePageHelper.checkCompareProductAdded'(ITEM),FailureHandling.CONTINUE_ON_FAILURE) 

