package bahag
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


import java.util.List

class BahagHelper {
	/**
	 * Refresh browser
	 */
	@Keyword
	public def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	public def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Scroll to an Object and another "offset" Pixels up
	 * @param to Katalon test object
	 */
	@Keyword
	public static void scrollToObject(TestObject to, int offset){
		WebDriver driver = DriverFactory.getWebDriver();
		WebElement element = WebUiBuiltInKeywords.findWebElement(to);
		//WebElement element = driver.findElement(By.xpath('//*/table/tbody/tr/td[contains(@id,"Value")][contains(text(), "' + valueEdit + '")]'));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Object topOffset = ((JavascriptExecutor) driver).executeScript("return window.pageYOffset");

		((JavascriptExecutor) driver).executeScript("window.scroll(0, " + ((int)topOffset-offset) + ");");
	}
	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	/**
	 * get a Float value out of a string (e.g. Price like 'Preis: 4,50€'
	 */
	//@Keyword
	public static Float getPrice(String str) {
		println("getPrice of: "+str)
		if(str.length()>0){
			str = str.replaceAll("[^0-9,]", "")
		}
		str=str.replace(",",".").trim()
		println("getPrice of cleaned Text: "+str)

		return Float.parseFloat(str.trim())
	}
	/**
	 * get a Float value out of a string (e.g. Price like 'Preis: 4,50€'
	 */
	//@Keyword
	public static Float getPriceOnPUES(Integer index) {
		List<WebElement> pending = WebUI.findWebElements(findTestObject('Old/Page_OnSiteSearch/SortByPriceAndName/div_nnnPrice'), 30)

		int closetabsSize = pending.size()

		if (closetabsSize > 0) {
			WebElement priceTag = pending.get(index-1)

			TestObject to = WebUI.convertWebElementToTestObject(priceTag)
			String text=WebUI.getText(to)
			if(priceTag.findElements(By.className("price-tag__strikethrough")).size()>0){
				WebElement uvp = priceTag.findElement(By.className("price-tag__strikethrough"));
				text=text.replaceAll(WebUI.getText(WebUI.convertWebElementToTestObject(uvp)), "") ;
			}

			if(priceTag.findElements(By.xpath(".//div[@class='price-tag__sales-unit']")).size()>0) {
				WebElement salesUnit = priceTag.findElement(By.xpath(".//div[@class='price-tag__sales-unit']"));
				String pattern = WebUI.getText(WebUI.convertWebElementToTestObject(salesUnit))
				text=text.replace(pattern, "");
			}
			if(priceTag.findElements(By.className("sr-only")).size()>0) {
				WebElement currency = priceTag.findElement(By.className("sr-only"));
				text=text.replaceAll(WebUI.getText(WebUI.convertWebElementToTestObject(currency)), "") ;
			}
			return getPrice(text.trim())
		}

		return Float.parseFloat(-1)
	}

	/**
	 * check Alphabetical Sort Order of 3 strings
	 */
	//@Keyword
	public static Boolean checkSortOrder(String str1, String str2, String str3) {
		List <String> arr = new ArrayList<>()
		List <String> arrsorted = new ArrayList<>()

		arr.add(str1)
		arr.add(str2)
		arr.add(str3)

		arrsorted.add(str1)
		arrsorted.add(str2)
		arrsorted.add(str3)

		print arr
		print arrsorted

		//Collections.sort(arr, Collections.reverseOrder());
		Collections.sort(arrsorted, Collections.reverseOrder());

		println(arr.toArray().toString())
		println(arrsorted.toArray().toString())

		if(arr[0]!=arrsorted[0]) return false;
		if(arr[1]!=arrsorted[1]) return false;
		if(arr[2]!=arrsorted[2]) return false;
		return true;

	}
	/**
	 * expandMoreProduct Options using More Link
	 */
	@Keyword
	public static int clickMoreProductOptionButtons() {
		String xpath = '//*[@class="button load-more btn-more"]/span'
		TestObject findTestObject = new TestObject().addProperty('xpath', ConditionType.EQUALS, xpath)
		List<WebElement> wel = WebUiCommonHelper.findWebElements(findTestObject, 5)

		int retval=0

		int more_counts = wel.size()

		//for (int i=0; i < more_counts; i++) {
		for (int i=more_counts-1; i >= 0; i--) {
			WebElement we = wel.get(i)
			TestObject to = WebUI.convertWebElementToTestObject(we)

			if( WebUI.verifyElementClickable(to, FailureHandling.OPTIONAL)) {

				if( WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL)) {
					WebUI.scrollToElement(to, 1)
					WebUI.scrollToPosition(0, WebUI.getViewportTopPosition()-300)

					WebUI.click(to)
					retval++
				}
			}
		}

		return retval
	}

	/**
	 * expandMoreProduct Options using More Link
	 */
	@Keyword
	public static Boolean clickTestObjectViaWebelement(TestObject findTestObject) {
		List<WebElement> wel = WebUiCommonHelper.findWebElements(findTestObject, 5)

		int more_counts = wel.size()

		for (int i=more_counts-1; i >= 0; i--) {
			WebElement we = wel.get(i)
			we.click()
			return true
		}

		return false
	}
	/**
	 * get Product Option on PDS Page 
	 */
	@Keyword
	public static TestObject getPDSOptionByName(String optionText) {
		//
		//String xpath = '//*[@id="lightBoxVarinats"]/div/div/div/a/span[contains(text(),"' + optionText + '")]'
		String xpath = '//*[@id="lightBoxVarinats"]/div/div/div/a/span[contains(@data-code,"' + optionText + '")]'
		return new TestObject().addProperty('xpath', ConditionType.EQUALS, xpath)
	}

	/**
	 * expandMoreProduct Options using More Link
	 */
	@Keyword
	public static Boolean clickProductOptionButtonByName(String optionText) {
		BahagHelper.clickMoreProductOptionButtons()

		//There is a missspelling in the ID, that is exactly like that in the production environment...
		String xpath = '//*[@id="lightBoxVarinats"]/div/div/div/a/span[contains(@data-code,"' + optionText + '")]'
		TestObject findTestObject = new TestObject().addProperty('xpath', ConditionType.EQUALS, xpath)
		List<WebElement> wel = WebUiCommonHelper.findWebElements(findTestObject, 5)

		for (int i=0; i < wel.size(); i++) {
			WebElement we = wel.get(i)
			TestObject to = WebUI.convertWebElementToTestObject(we)

			if( WebUI.verifyElementClickable(to, FailureHandling.OPTIONAL)) {

				if( WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL)) {
					println("Clickable Option found!")
					WebUI.scrollToElement(to, 1)
					WebUI.scrollToPosition(0, WebUI.getViewportTopPosition()-300)

					WebUI.click(to)
					return true
				}
			}
		}

		assert "No Clickable Product Option found!!!" //&& true == false
		return false
	}


	/**
	 * count the Elements, that match the xpath
	 */
	@Keyword
	public static int getXpathMatchCount(String xpath) {
		try{
			TestObject findTestObject = new TestObject().addProperty('xpath', ConditionType.EQUALS, xpath)
			List<WebElement> wel = WebUiCommonHelper.findWebElements(findTestObject, 5)

			return wel.size()
		}
		catch (Exception ex)
		{
			return -1
		}
		return 0
	}


	@Keyword
	public static String compareStrings(String content, String expected) {
		String[] dataContent = content.split("\n");
		String[] dataExpected = content.split("\n");

		int i=0;
		while(i< dataContent.size()){
			if(dataContent[i]!=dataExpected[i]){
				return "strings dont match data:["+dataContent[i]+"] - expected: ["+dataExpected[i]+"]"
			}
			i++
		}
		return ""
	}
}





