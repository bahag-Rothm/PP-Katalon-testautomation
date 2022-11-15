package bahag

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

import static com.kms.katalon.core.model.FailureHandling.CONTINUE_ON_FAILURE

//import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.logging.KeywordLogger
//import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

public class AssertHelper {


	/**
	 *
	 */
	@Keyword
	static def assertTrue(String message, Boolean condition,
			FailureHandling flowControl = CONTINUE_ON_FAILURE) {
		if (!condition) {
			stepFailed(message, flowControl)
		}
	}

	@Keyword
	static def assertFalse(String message, Boolean condition,
			FailureHandling flowControl = CONTINUE_ON_FAILURE) {
		if (condition) {
			stepFailed(message, flowControl)
		}
	}


	/**
	 *
	 */
	@Keyword
	static def assertEquals(String message, Number expected, Number actual,
			FailureHandling flowControl = CONTINUE_ON_FAILURE) {
		if (expected != actual) {
			stepFailed(message, flowControl)
		}
	}


	static def stepFailed(String message, FailureHandling flowControl) {
		if (flowControl == FailureHandling.OPTIONAL) {
			//println "#stepFailed('${message}',FailureHandling.OPTIONAL)"
			KeywordUtil.logInfo(message)
		} else if (flowControl == FailureHandling.CONTINUE_ON_FAILURE) {
			//println "#stepFailed('${message}',FailureHandling.CONTINUE_ON_FAILURE)"
			KeywordUtil.markFailed(message)
		} else {
			// in the case where flowControl == FailureHandling.STOP_ON_FAILURE
			//println "#stepFailed('${message}',FailureHandling.STOP_ON_FAILURE)"
			KeywordUtil.markFailedAndStop(message)
		}
	}
}
