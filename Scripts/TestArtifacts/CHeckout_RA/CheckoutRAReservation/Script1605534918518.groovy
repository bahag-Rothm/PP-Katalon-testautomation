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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('null'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('null'), [('searchTerm') : 'wohlgelegen'], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('null'), [('searchTerm') : '10090072', ('amount') : '2'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.setText(findTestObject('null'), 
    'ebubahag@gmx.de')

WebUI.setEncryptedText(findTestObject('null'), 
    'lspSERZT+7wCMGCOhdthtA==')

WebUI.click(findTestObject('null'))

WebUI.click(findTestObject('null'))

WebUI.verifyElementText(findTestObject('null'), 
    '9910145006')

WebUI.verifyElementText(findTestObject('null'), 
    'BAUHAUS Mannheim-Wohlgelegen')

WebUI.verifyElementText(findTestObject('null'), 
    '62,00\n\t\t\t€')

WebUI.verifyElementText(findTestObject('null'), 
    'Ihre Reservierung ist bei uns eingegangen und wird schnellstmöglich bearbeitet.')

WebUI.closeBrowser()

