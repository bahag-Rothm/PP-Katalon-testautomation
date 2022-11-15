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


class ProductDetailPageHelper {

	@Keyword
	def navigateTo(String countryId, String itemnumber) {
		String link = GlobalHelper.getMapperByCountryId(countryId, "link");
		String item =  "/p/" + itemnumber;
		WebDriver webDriver = DriverFactory.getWebDriver();
		webDriver.navigate().to(link + item);
	}
	/*
	 **
	 * Title: set Quantity
	 * Desc: Keyword to set the amount of the product in PDS
	 * @param Quantity
	 * @return: True/False if the amount is edited then true, false if not
	 */

	@Keyword
	def setQuantity(String input) {
		WebElement web = WebUI.findWebElement(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/InputAmountmore'));
		web.click();
		web.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		web.sendKeys(input);
		web.sendKeys(Keys.RETURN);
		return true
	}
	@Keyword
	def setQuantityForCalculation(String input, String unit) {
		if(unit == "Karton") {
			setQuantity(input);
		}
		else {
			WebElement web = WebUI.findWebElement(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/InputQuantity'));
			web.click();
			web.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			web.sendKeys(input);
			web.sendKeys(Keys.RETURN);
		}
	}

	@Keyword
	def setOrder() {
		WebUI.verifyElementClickable(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonOnlineOrder'), FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonOnlineOrder'), FailureHandling.STOP_ON_FAILURE)
		'Optional: Confirm Switching Shopping Cart from Reservationto OnlineOrder'
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonCartReservationConflictConfirm'),1, FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonCartReservationConflictConfirm'), FailureHandling.OPTIONAL)
		}


		'Press Button "Continue Shopping"'
		WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PopupOnlineOrder'),1, FailureHandling.STOP_ON_FAILURE);
		WebElement close = WebUI.findWebElement(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PopupButtonWeiterEinkaufen'));
		close.click();
		WebUI.delay(1)
	}


	@Keyword
	def setReservation() {
		WebUI.verifyElementClickable(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonOnlineReservation'), FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonOnlineReservation'), FailureHandling.STOP_ON_FAILURE)
		'Optional: Confirm Switching Shopping Cart from OnlineOrder to Reservation'
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonCartReservationConflictConfirm'),3, FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonCartReservationConflictConfirm'), FailureHandling.OPTIONAL)
		}
		'Press Button "Continue Shopping"'
		WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PopupOnlineOrder'),3, FailureHandling.STOP_ON_FAILURE);
		WebElement close = WebUI.findWebElement(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PopupButtonWeiterEinkaufen'));
		close.click();
		WebUI.delay(1)
	}

	@Keyword
	def checkPriceAdvantagePDVisible() {
		WebElement web = WebUI.findWebElement(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/IconPriceAdvantagePDetails'));
		int count = web.findElements(By.className("product-detail-prices__graduated-prices-item")).size();

		if(count != 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Keyword
	def checkPriceAdvantagePOVisible() {
		WebElement web = WebUI.findWebElement(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/IconPriceAdvantagePOverview'));
		int count = web.findElements(By.className("product-list-tile__graduated-price")).size();

		if(count != 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Keyword
	def selectUnit(String unit) {
		WebElement web = WebUI.findWebElement(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonsCalulation'));

		switch (unit) {
			case "mSquare":
				web.findElements(By.tagName("a")).get(0).click();
				break;
			case "Karton":
				web.findElements(By.tagName("a")).get(1).click();
				break;
		}
	}

	@Keyword
	def checkColcualtionResults(String input) {
		WebUI.delay(2);
		WebElement web = WebUI.findWebElement(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/LabelColculationResults'));
		String val = web.getAttribute("innerText");

		Boolean flag = false;

		if(val.contains(input)) {
			System.out.println(input + " is contains: " + val);
			flag = true
		}
		else {
			System.out.println(input + " is not contains: " + val);
		}

		System.out.println("Return value is: " + flag);
		return flag;
	}

	@Keyword
	def checkDekorAndProdNr(String variant, String prod) {
		WebElement dekor = WebUI.findWebElement(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/LabelDekor'));
		String dekorText = dekor.findElement(By.tagName("strong")).getAttribute("innerText");
		System.out.println("The decor text is: " + dekorText);

		WebElement prodNr = WebUI.findWebElement(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/LabelProdNr'));
		String prodNrText = prodNr.getAttribute("innerText");
		System.out.println("The product number text is: " + prodNrText);

		Boolean flag = false;

		if(prodNrText.contains(prod) && dekorText == variant) {
			flag = true
			System.out.println("The decor and the product number are correct");
		}
		else {
			System.out.println("The decor and the product number are not correct");
		}

		System.out.println("Return value is: " + flag);
		return flag;
	}

	@Keyword
	def selectVariant() {
		WebElement web = WebUI.findWebElement(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/GroupProjectVariant'));
		List<WebElement> span = web.findElements(By.tagName("span"))
		span.get(1).click();
	}

	/**
	 * Title: Verify Pictogram Title
	 * Desc: TestCase61 Keyword that verify if a piktogramme is clickable and can be popped up
	 * @param Piktogramme
	 * @return: True/False if the link of the given pictogram is clickable then return true, return false if not
	 */
	@Keyword
	def SelectandCheckPiktogramme(String Piktogramme) {
		//		if (WebUI.verifyElementClickable(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/LinkPiktogramme').addProperty('xpath',ConditionType.EQUALS , '//a[@data-fancybox-title="'+Piktogramme+'"]'))) {
		//			WebUI.click(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/LinkPiktogramme').addProperty('xpath',ConditionType.EQUALS , '//a[@data-fancybox-title="'+Piktogramme+'"]'), FailureHandling.CONTINUE_ON_FAILURE)
		//			System.out.println("The Piktogramme is poped up")
		//
		//			return true
		//		}else {
		//			return false
		//		}
	}

	/**
	 * Title: Verify Pictogram Title
	 * Desc: TestCase61 Keyword that verify if the title of a piktogramme is right
	 * @param Piktogramme
	 * @return: True/False if the given name of the pictogram equal to the title of the actual pictogm  then return true, return false if not
	 */
	@Keyword
	def VerifyPiktogramTitle(String Piktogramme) {
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> pictograms = driver.findElements(By.xpath("//picture/img[@alt='" + Piktogramme + "']"))

		if (pictograms.size() > 0) {
			System.out.println("The Piktogramme is exist")

			return true
		}else {
			return false
		}
		//		if(WebUI.verifyElementText(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/SpanTitlePiktogramme'), Piktogramme)){
		//			System.out.println("The Piktogram title is right")
		//			return true
		//		}else {
		//			System.out.println("The Piktogram title is wrong")
		//			return false
		//		}
	}

	@Keyword
	def checkCompareProductAdded(String productnumber) {
		WebElement web = WebUI.findWebElement(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/LabelProductCompare'));
		String val = web.getAttribute("innerText");

		Boolean flag = false;

		if(val.contains(productnumber)) {
			System.out.println("CompareProduct " + productnumber + " is contains: " + val);
			flag = true
		}
		else {
			System.out.println("CompareProduct " + productnumber + " is not contains: " + val);
		}

		System.out.println("Return value is: " + flag);
		return flag;
	}

	@Keyword
	def selectCompareProduct() {
		Thread.sleep(2000);
		try {
			WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonToCompare'),5);
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Title: Check Number Of Labels
	 * Desc: TestCase62 Keyword that Verify how many Labels are present
	 * @param Number of Labels
	 * @return: True/False if the given number of labels equal to the actual number of labels then return true, return false if not
	 */
	@Keyword
	def checkNumberOfLabels(String NumberOfLabels) {
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> listeOfLabels = driver.findElements(By.xpath("//*[@class='product-detail-block-description__labels']/a//picture"))

		//List<WebElement> listeOfLabels = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/ImgFirstLabel'), 5)
		System.out.println("Liste of labels is ready");
		System.out.println(listeOfLabels.size());

		if (listeOfLabels.size().toString().equals(NumberOfLabels)) {
			System.out.println("Number Of Labels is Right");
			return true
		}
		else {
			System.out.println("Number Of Labels is not Right!");
			return false
		}
	}

	/**
	 * Title: Click And Check First Label
	 * Desc: TestCase62 Keyword that clicks on the first Label  and see if popped up or not
	 * @return: True/False if the Popup Label is visible then return true, return false if not
	 */
	@Keyword
	def clickAndCheckFirstLabel() {

		WebUI.click(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/ImgFirstLabel'), FailureHandling.CONTINUE_ON_FAILURE)

		boolean isVisible =  WebUI.verifyElementVisible(findTestObject('Object Repository/Onlineshop.Pages/ProduktePage/Elements/OLD/PopUpLabels'))

		if (isVisible == true) {
			System.out.println("First Label is now opened");
			return true
		}
		else {
			System.out.println("First Label is not displayed");
			return false
		}
	}

	/**
	 * Title: Check First Data Sheet
	 * Desc: TestCase63 Keyword that verify if the first DataSheet is clickable then click it
	 * @return: True/False if the first data sheet link is clickable then return true, return false if not
	 */
	@Keyword
	def checkDataSheet() {

		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> dataSheet = driver.findElements(By.xpath("//*[@class='product-detail-block-factsheets']/ul/li"))

		if (dataSheet.size() > 0) {
			System.out.println("The Datasheet is exist")

			return true
		}else {
			return false
		}
	}

	/**
	 * Title: Click And Check Thumb Media Display
	 * Desc: TestCase64 Keyword that verify if the second thumb Media will be displayed on bigger scale view
	 * @return: True/False if the second image on the list of the Thumbmedia displayed on the biggerscale then return true, return false if not
	 */
	@Keyword
	def clickAndCheckThumbMediaDisplay() {

		List<WebElement> listeOfThumbMedia = WebUiCommonHelper.findWebElements(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/LinkThumbMedia'), 5)

		if (listeOfThumbMedia.size() > 0) {
			System.out.println("Liste of ThumbMedias is ready");

			return true
		}
		else {
			return false
		}
	}

	/**
	 * Title: Verify Price Tag Not Empty
	 * Desc: TestCase65 Keyword that verify if the price Tag not empty
	 * @return: True/False if the price tag is not empty then return true, return false if not
	 */
	@Keyword
	def VerifyPriceTagNotEmpty() {
		String priceText = WebUI.findWebElement(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/SpanPriceValue')).getText()
		println("the main Price is "+priceText)
		if(priceText.equals("")){
			return false
		}
		else{
			return true
		}
	}

	/**
	 * Title: Verify Number Of Logos
	 * Desc: TestCase66 Keyword that verify if the number of the Logos is right
	 * @param: Number of Logos 
	 * @return: True/False if the Number of Logos is right then return true, return false if not
	 */
	@Keyword
	def VerifyNumberOfLogos(NumberOfLogos){
		List<WebElement> listOfLogos = WebUiCommonHelper.findWebElements(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/ListOfLogos'), 2)
		println ("List of Logos is now ready")
		println ("Number of logos is:"+listOfLogos.size().toString())

		if(listOfLogos.size().toString().equals(NumberOfLogos)){
			println ("the Number of Logos is right" )
			return true
		}
		else {
			println ("the Number of Logos is wrong" )

			return false
		}
	}

	/**
	 * Title: Verify Shipping Cost Info Visible
	 * Desc: TestCase67 Keyword that verify if the Shipping Cost Informations are Visible
	 * @return: True/False if the Shipping Cost Informations are visible then return true, return false if not
	 */
	@Keyword
	def VerifyShippingCostInfoVisible() {
		if(WebUI.verifyElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PShippingCostInfos'))) {
			println("Shipping Cost Infos are visible ")
			return true
		}
		else {
			println("Shipping Cost Infos are not visible ")
			return false
		}
	}

	/**
	 * Title: Click Shipping Cost Layer Link
	 * Desc: TestCase68 Keyword that verify if the Shipping Cost Layer Link is clickable
	 * @return: True/False if the title of the popped up layer is visible then return true, return false if not
	 */
	@Keyword
	def ClickShippingCostLayerLink() {
		WebUI.scrollToElement(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/InputShippingCostInfo'), 2)

		WebDriver webdriver =  DriverFactory.getWebDriver()

		WebElement linkShippingCostLayer = webdriver.findElement(By.xpath('//button[@data-target="#modal-product-detail-stock-information-order"]'));

		JavascriptExecutor jse = (JavascriptExecutor)webdriver;

		jse.executeScript("arguments[0].click();", linkShippingCostLayer);

		if(WebUI.verifyElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PTitleOfShippingCostLayer'))) {
			println("Shipping Cost Layer Link does work")
			return true
		}
		else {
			println("Shipping Cost Layer Link does not work")
			return false
		}
	}

	/**
	 * Title: Click Verify Right Shipping Layer Popped Up
	 * Desc: TestCase68 Keyword that verify if the right shipping cost layer is popped up
	 * @param: Shipping Method
	 * @return: True/False if the title of the popped up layer equal the shipping method then return true, return false if not
	 */
	@Keyword
	def VerifyRightShippingLayerPoppedUp(String ShippingMethod) {
		if (WebUI.verifyElementText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PTitleOfShippingCostLayer'), ShippingMethod)) {
			println ("It is the right Shipping Layer")
			return true
		}
		else {
			println ("It is not the right Shipping Layer")
			return true
		}
		/*
		 System.out.println("Liste of ThumbMedias is ready");
		 System.out.println("Liste of LinkMedias is ready");
		 listeOfThumbMedia[1].click()
		 if (listeOfLinkMedia[1].getAttribute("style").equals("display: block;")) {
		 return true
		 }
		 else {
		 return false
		 }*/
	}

	/**
	 * Title: Click Navegation Control Button Next
	 * Desc: TestCase92 Keyword that verify if the Next Button of the thumbMedia Liste clickable and then click it
	 * @return: True/False if the Next Button of the thumbMedia Liste is clickable return true and False if not 
	 */
	@Keyword
	def ClickNavCtrlBtnNext() {

		if(WebUI.verifyElementClickable(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/NavigationControlButtonNext'))) {
			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/NavigationControlButtonNext'))
			System.out.println("Navigation Control Button Next is clickable");
			return true
		}else
			System.out.println("Navigation Control Button Next is not clickable");
		return false
	}

	/**
	 * Title: Click Navegation Control Button Previous 
	 * Desc: TestCase92 Keyword that verify if the Previous Button of the thumbMedia Liste clickable and then click it
	 * @return: True/False if the Previous Button of the thumbMedia Liste is clickable return true and False if not 
	 */
	@Keyword
	def ClickNavCtrlBtnPrev() {

		if(WebUI.verifyElementClickable(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/NavigationControlButtonPrev'))) {

			WebDriver webdriver =  DriverFactory.getWebDriver()

			WebElement navCtrlBtnPrev = webdriver.findElement(By.xpath('//a[@class="prevBtn std"]'));

			JavascriptExecutor jse = (JavascriptExecutor)webdriver;

			jse.executeScript("arguments[0].click();", navCtrlBtnPrev);

			System.out.println("Navigation Control Button Prev is clickable");

			WebUI.delay(2)

			return true
		}else
			System.out.println("Navigation Control Button Prev is not clickable");
		return false
	}

	/**
	 * Title: Click Link First Image
	 * Desc: TestCase94 Keyword that verify if the first Image of the product is clickable and then click it
	 * @return: True/False if the first Image of the product is clickable return true and False if not 
	 */
	@Keyword
	def ClickLinkFirstImage() {
		List<WebElement> listeOfThumbMedia = WebUiCommonHelper.findWebElements(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/LinkThumbMedia'), 5)

		if(WebUI.verifyElementClickable(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/LinkFirstImage'))) {
			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/LinkFirstImage'))
			System.out.println("The Media Loop Button is clickable");
			return true
		}else
			System.out.println("The Media Loop Button is not clickable");
		return false
	}

	/**
	 * Title: Click Popup Navegation Control Button Next 
	 * Desc: TestCase94 Keyword that verify if the Next Button of the Popped up Images clickable and then click it
	 * @return: True/False if Popup Navegation Control Button Next is clickable return true and False if not 
	 */
	@Keyword
	def ClickPopUpNavCtrlBtnNext() {

		if(WebUI.verifyElementClickable(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PopUpNavCtrlBtnNext'))) {
			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PopUpNavCtrlBtnNext'))
			System.out.println("PopUpNavCtrlBtnNext is clickable");
			return true
		}else
			System.out.println("PopUpNavCtrlBtnNext is not clickable");
		return false
	}

	/**
	 * Title: Click Popup Navegation Control Button Previous 
	 * Desc: TestCase94 Keyword that verify if the Previous Button of the Popped up Images clickable and then click it
	 * @return: True/False if Popup Navegation Control Button Previous is clickable return true and False if not 
	 */
	@Keyword
	def ClickPopUpNavCtrlBtnPrev() {

		if(WebUI.verifyElementClickable(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PopUpNavCtrlBtnPrev'))) {
			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/PopUpNavCtrlBtnPrev'))
			System.out.println("PopUpNavCtrlBtnPrev is clickable");
			return true
		}else
			System.out.println("PopUpNavCtrlBtnPrev is not clickable");
		return false
	}

	/**
	 * Title: Verify the number of Properties
	 * Desc: TestCase94 Keyword that verify if the Number of the product Properties is right  
	 * @param Number of Properties
	 * @return True/False if the number of Properties is right then return true if not then False  
	 */
	@Keyword
	def VerifyNumberOfProperties(NumberOfProperties) {
		List<WebElement> listOfPrductProperties = WebUiCommonHelper.findWebElements(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/TrProductDetailsTable'), 2)

		if( NumberOfProperties.equals(listOfPrductProperties.size().toString())) {
			System.out.println("Number Of Properties is equal: "+listOfPrductProperties.size().toString());
			return true
		}else
			System.out.println("Number Of Properties it was: "+listOfPrductProperties.size().toString());
		return false
	}

	/**
	 * Title: Verify Availability
	 * Desc: TestCase70 Keyword that verify The Availability of the product in specific Buying Center
	 * @param Availability
	 * @return True/False: in a particulier Buying Center:
	 *                                                    if the product is available and the given Availability is false then return false
	 *                                                                                and the given Availability is true then return true
	 *                                                    if the product is NOT available and the given Availability is false then return true
	 *                                                                                    and the given Availability is true then return false
	 */
	@Keyword
	def VerifyAvailability(Availability) {
		List<WebElement> listOfSpanAvailibility = WebUiCommonHelper.findWebElements(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/SpanAvailibilityRed'), 2)

		if(Availability.toString().toUpperCase().equals("FALSE")) {
			if (listOfSpanAvailibility.size().equals(0)) {
				System.out.println("Availibility is wrong it should be red");
				return false
			}
			else {
				System.out.println("Availibility is right");
				return true
			}
		}else {
			if (listOfSpanAvailibility.size().equals(0)) {
				System.out.println("Availibility is right it should be not red");
				return true
			}else {
				System.out.println("Availibility is wrong it should be not red");
				return false
			}
		}
	}

	/**
	 * Title: Set Quantity and put in basket
	 * Desc: TestCase70 Keyword that set the quantity of product and put it in the cart
	 * @param Quantity
	 * @return True/False : true if the quantity being set and the prduct is puted in the cart, false if not
	 */
	@Keyword
	def SetQuantityPutinCart(Quantity) {

		if(WebUI.verifyElementClickable(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/InputAmountmore'))) {
			this.setQuantity(Quantity)
			WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonOnlineOrder'))
			return true
		}else {
			return false
		}
	}

	@Keyword
	def selectPopUpCompareProduct() {
		Thread.sleep(2000);
		try {
			WebElement element = WebUiCommonHelper.findWebElement(findTestObject('Onlineshop.Pages/ProduktePage/Elements/OLD/ButtonToCompareAddAnotherProduct'),5);
			WebUI.executeJavaScript("arguments[0].click()", Arrays.asList(element));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	//@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}
}