package onlineshop.Keywords
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.RenderingHints.Key
import java.util.concurrent.ConcurrentHashMap.KeySetView

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

import internal.GlobalVariable as Mapper

import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByTagName
import org.openqa.selenium.WebDriver
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.support.ui.Select


import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.Cookie

class HomePageHelper {

	@Keyword
	def openBrowser(String countryId) {
		String link = GlobalHelper.getMapperByCountryId(countryId, "link");
		WebUI.openBrowser(link);
		//WebUI.maximizeWindow();
		acceptCookies(countryId);
		closePupup();  				// New -> for QS
	}

	@Keyword
	def closeBrowser() {
		WebUI.closeBrowser();
	}

	@Keyword
	def navigateTo(String countryId) {
		String link = GlobalHelper.getMapperByCountryId(countryId, "link");
		WebDriver webDriver = DriverFactory.getWebDriver();
		webDriver.navigate().to(link);
	}

	@Keyword
	def checkCountOfMenuElements(String input) {

		boolean flag = false;

		String count = getMenuElementsCount();
		if(count.equals(input)) {
			flag = true;
			System.out.println("Menu elements count is equal to: " + input + " value is: " + flag);
		}
		else {
			System.out.println("Menu elements count is not equal to: " + input + ". count is:" + count );
		}

		System.out.println("Return value is: " + flag);
		return flag;
	}


	@Keyword (keywordObject = "<ITEMNUMBER>")
	def setSearch(String input) {
		WebUI.setText(findTestObject('Onlineshop.Pages/HomePage/Elements/InputSearch'), input)
		//WebElement web = WebUI.findWebElement(findTestObject('Onlineshop.Pages/HomePage/Elements/InputSearch'));
		//web.sendKeys(input);
	}

	@Keyword
	def selectSearchElement() {
		WebElement web = WebUI.findWebElement(findTestObject('Object Repository/Onlineshop.Pages/HomePage/Elements/SearchedElement'));
		WebUI.waitForElementVisible(findTestObject('Object Repository/Onlineshop.Pages/HomePage/Elements/SearchedElement'), 5);
		web.click();
	}

	@Keyword
	def selectSearchElementByRETURN() {
		WebElement web = WebUI.findWebElement(findTestObject('Onlineshop.Pages/HomePage/Elements/InputSearch'));
		web.sendKeys(Keys.RETURN);
	}

	@Keyword
	def checkCurrentURL(String countryId) {
		WebUI.delay(2);
		WebDriver webDriver = DriverFactory.getWebDriver();
		String link = GlobalHelper.getMapperByCountryId(countryId, "link");
		String currentURL = WebUI.getUrl()

		boolean flag = false;

		String count = getMenuElementsCount();
		if(link == currentURL) {
			flag = true;
			System.out.println("Current URL and Homepage URL are identical");
		}
		else {
			System.out.println("Current URL and Homepage URL are identical. Current URL is: " + currentURL);
		}

		System.out.println("Return value is: " + flag);
		return flag;
	}

	// Private
	def getMenuElementsCount() {
		WebElement web = WebUI.findWebElement(findTestObject('Onlineshop.Pages/HomePage/Elements/NavigationElements'));
		int count = web.findElements(By.tagName("li")).size();
		return count;
	}

	def acceptCookies(countryId) {
		WebUI.delay(5)
		// Added object for recognizing Cookies Popup to avoid failures caused by delay.
		//WebUI.waitForElementPresent(findTestObject('Onlineshop.Pages/HomePage/Elements/CookiesPopup'), 5)
		WebUI.executeJavaScript("UC_UI.acceptAllConsents().then(UC_UI.closeCMP)", null)
	}

	def addCookies() {
		List<Cookie> ck = new ArrayList<Cookie>();
		ck.add(new Cookie("ANON", "A=6B1FC3CBC605848134DE60F6FFFFFFFF&E=1a40&W=1"))
		ck.add(new Cookie("NAP", "V=1.9&E=19e6&C=ucLpdnJcyxadIwkAUuxGoEJkneUrHWuxt5Sihw3QBgQrcsYwb-BqRQ&W=1"))
		ck.add(new Cookie("MUID", "1EB806DD332669CE3ACA0A2437266A25"))
		ck.add(new Cookie("s_vi", "[CS]v1|2F1779480515DC1A-600008E9481049C9[CE]"))
		ck.add(new Cookie("_cs_s", "1.5.0.1639777263021"))
		ck.add(new Cookie("_cs_id", "c76f1c00-119a-a877-a711-8f400ae4d0b6.1639775462.1.1639775462.1639775462.1.1673939462706"))
		ck.add(new Cookie("_cs_mk", "0.47524059695363285_1639775461350"))
		ck.add(new Cookie("s_invisit", "true"))
		ck.add(new Cookie("_uetvid", "d65437a05f7d11eca128dd377a9652c7"))
		ck.add(new Cookie("acceleratorSecureGUID", "a3c1213f41e6fbc9ba87c786d69c134af2301105"))
		ck.add(new Cookie("shopping", ""))
		ck.add(new Cookie("_uetsid", "d65407205f7d11ec98a3091e52748bf1"))
		ck.add(new Cookie("adb_appServer", "server1"))
		ck.add(new Cookie("AMCV_9C7CECFF5660226E7F000101%40AdobeOrg", "-1124106680%7CMCIDTS%7C18979%7CMCMID%7C25621814929563009088785778436987821165%7CvVersion%7C5.2.0"))

		WebDriver driver = DriverFactory.getWebDriver()
		for (String cookie : ck) {
			driver.manage().addCookie(cookie)
		}
	}

	def closePupup() {
		List<WebElement> listOfBtnpopup = WebUiCommonHelper.findWebElements(findTestObject('Onlineshop.Pages/HomePage/Elements/ButtonPopupClose'), 2)
		int  size  = listOfBtnpopup.size()

		if (size > 0) {
			WebUI.click(findTestObject('Onlineshop.Pages/HomePage/Elements/ButtonPopupClose'))

			System.out.println("ButtonPopup is exist");
			return true
		}else {
			System.out.println("ButtonPopup is not exist ");
			return false
		}
	}

}