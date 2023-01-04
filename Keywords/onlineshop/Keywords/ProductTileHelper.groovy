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

public class ProductTileHelper {

	String tilewidth = 550;

	String tileheight = 320;


	@Keyword
	def checkproductImage() {
		String flag = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductImage'), 3)
		String flagwidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductImage'))
		String flagheight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductImage'))
		KeywordUtil.logInfo(flagwidth);
		KeywordUtil.logInfo(flagheight);
		if( flagwidth <= tilewidth && flagheight <= tileheight) {

			KeywordUtil.logInfo("Image is visible: "+flag);

			if(flag == "true") {
				KeywordUtil.logInfo("Image Title exist");
				return true
			}
			else {
				KeywordUtil.logInfo("Image Title not exist");
				return false
			}
		}else {
			KeywordUtil.logInfo("Image Title not exist, CSS isn't right as well");
			return false
		}
	}

	@Keyword
	def checkVariants() {
		//change for string contains:
		String flag = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), 3)
		String flagwidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'))
		String flagheight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'))
		String resultPrimary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Variants_Number'), FailureHandling.OPTIONAL)


		KeywordUtil.logInfo("Variants are visible: "+resultPrimary);
		KeywordUtil.logInfo("Variants icon are visible: "+flag);
		if( flagwidth <= tilewidth && flagheight <= tileheight) {
			if(flag == "true") {
				KeywordUtil.logInfo("Variants icon exist");
				return true
			}
			else {
				KeywordUtil.logInfo("Variants icon not exist");
				return false
			}
		}else {
			KeywordUtil.logInfo("Variants icon not exist, CSS isn't right as well");
			return false
		}
	}

	@Keyword
	def checkSetIcon() {


		String primary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), 3, FailureHandling.OPTIONAL)


		String secondary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'),3, FailureHandling.OPTIONAL)


		String third = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Third'),3, FailureHandling.OPTIONAL)


		if(primary == "true" || secondary == "true" || third == "true" ) {

			String resultPrimary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), FailureHandling.OPTIONAL)
			String flagwidthPrimary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
			String flagheightPrimary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)

			String resultSecondary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'), FailureHandling.OPTIONAL)
			String flagwidthSecondary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
			String flagheightSecondary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)

			String resultThird = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Third'), FailureHandling.OPTIONAL)
			String flagwidthThird = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
			String flagheightThird = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)


			if( flagwidthPrimary <= tilewidth && flagheightPrimary <= tileheight || flagwidthSecondary <= tilewidth && flagheightSecondary <= tileheight || flagwidthThird <= tilewidth && flagheightThird <= tileheight) {

				if(resultPrimary == "Set" || resultPrimary == "set" || resultPrimary == "Conjunto") {
					KeywordUtil.logInfo("Set icon exist, the text is right and its on the first colum");
					return true
				} else if (resultSecondary == "Set" || resultSecondary == "set" || resultSecondary == "Conjunto") {
					KeywordUtil.logInfo("Set icon exist, the text is right and its on the second colum");
					return true
				} else if(resultThird == "Set" || resultThird == "set" || resultThird == "Conjunto") {
					KeywordUtil.logInfo("Set icon exist, the text is right and its on the third colum");
					return true
				}else {
					KeywordUtil.logInfo("Set icon dosen't exist");
					return false
				}

			}else {
				KeywordUtil.logInfo("Set icon dosen't exist");
				return false
			}
		}
	}


	@Keyword
	def checkRecommendationIcon() {
		String primary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), 3, FailureHandling.OPTIONAL)
		String resultPrimary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), FailureHandling.OPTIONAL)
		String secondary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'),3, FailureHandling.OPTIONAL)
		String resultSecondary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'), FailureHandling.OPTIONAL)
		String third = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Third'),3, FailureHandling.OPTIONAL)
		String resultThird = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Third'), FailureHandling.OPTIONAL)



		String flagwidthPrimary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
		String flagheightPrimary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)


		String flagwidthSecondary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
		String flagheightSecondary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)


		if( flagwidthPrimary <= tilewidth && flagheightPrimary <= tileheight && flagwidthSecondary <= tilewidth && flagheightSecondary <= tileheight) {

			if(primary == "true" || secondary == "true" || third == "true" ) {
				if(resultPrimary == "Empfehlung" || resultPrimary == "empfehlung" || resultPrimary == "Istaknuto" || resultPrimary == "OPORTUNIDAD") {
					KeywordUtil.logInfo("Recommendation icon exist, the text is right and its on the first colum");
					return true
				} else if (resultSecondary == "Empfehlung" || resultSecondary == "empfehlung" || resultSecondary == "Istaknuto" || resultSecondary == "OPORTUNIDAD") {
					KeywordUtil.logInfo("Recommendation icon exist, the text is right and its on the second colum");
					return true
				} else if(resultThird == "Empfehlung" || resultThird == "empfehlung" || resultThird == "Istaknuto" || resultThird == "OPORTUNIDAD") {
					KeywordUtil.logInfo("Recommendation icon exist, the text is right and its on the third colum");
					return true
				}else {
					KeywordUtil.logInfo("Recommendation icon dosen't exist");
					return false
				}

			}else {
				KeywordUtil.logInfo("Recommendation icon dosen't exist");
				return false
			}
		}
	}

	@Keyword
	def checkQuantitiesAdvantageIcon() {
		//change for string contains:
		String flag = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_quantitiesAdvantage'), 3)
		String flagWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_quantitiesAdvantage'))
		String flagHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_quantitiesAdvantage'))

		if( flagWidth <= tilewidth && flagHeight <= tileheight) {

			KeywordUtil.logInfo("Quantities advantage icon is visible: "+flag);

			if(flag == "true") {
				KeywordUtil.logInfo("Quantities advantage icon exist");
				return true
			}
			else {
				KeywordUtil.logInfo("Quantities advantage icon not exist");
				return false
			}}else {
			KeywordUtil.logInfo("Quantities advantage icon not exist, CSS isn't right as well");
			return false
		}
	}

	@Keyword
	def checkNewIcon() {
		String primary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), 3, FailureHandling.OPTIONAL)
		String resultPrimary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), FailureHandling.OPTIONAL)
		String secondary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'),3, FailureHandling.OPTIONAL)
		String resultSecondary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'), FailureHandling.OPTIONAL)
		String third = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Third'),3, FailureHandling.OPTIONAL)
		String resultThird = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Third'), FailureHandling.OPTIONAL)


		String flagwidthPrimary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
		String flagheightPrimary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)


		String flagwidthSecondary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
		String flagheightSecondary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)

		if( flagwidthPrimary <= tilewidth && flagheightPrimary <= tileheight && flagwidthSecondary <= tilewidth && flagheightSecondary <= tileheight) {

			if(primary == "true" || secondary == "true" || third == "true" ) {
				if(resultPrimary == "Neu" || resultPrimary == "Nuevo" || resultPrimary == "NUEVO" || resultPrimary == "NEU") {
					KeywordUtil.logInfo("New icon exist, the text is right and its on the first colum");
					return true
				} else if (resultSecondary == "Neu" || resultSecondary == "Nuevo" || resultSecondary == "NUEVO" || resultSecondary == "NEU") {
					KeywordUtil.logInfo("New icon exist, the text is right and its on the second colum");
					return true
				} else if(resultThird == "Neu" || resultThird == "Nuevo" || resultThird == "NUEVO"|| resultThird == "NEU") {
					KeywordUtil.logInfo("New icon exist, the text is right and its on the third colum");
					return true
				}else {
					KeywordUtil.logInfo("New icon dosen't exist");
					return false
				}

			}else {
				KeywordUtil.logInfo("New icon dosen't exist");
				return false
			}
		}else {
			KeywordUtil.logInfo("New icon dosen't exist or the CSS isnt't right");
			return false
		}


	}

	@Keyword
	def checkGuaranteeIcon() {

		String primary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), 3, FailureHandling.OPTIONAL)
		String resultPrimary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), FailureHandling.OPTIONAL)
		String secondary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'), 3, FailureHandling.OPTIONAL)
		String resultSecondary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'), FailureHandling.OPTIONAL)
		String third = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Third'), 3, FailureHandling.OPTIONAL)
		String resultThird = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Third'), FailureHandling.OPTIONAL)


		String flagwidthPrimary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
		String flagheightPrimary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)


		String flagwidthSecondary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
		String flagheightSecondary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)

		if( flagwidthPrimary <= tilewidth && flagheightPrimary <= tileheight && flagwidthSecondary <= tilewidth && flagheightSecondary <= tileheight) {

			if(primary == "true" || secondary == "true" || third == "true" ) {
				if(resultPrimary == "GARANTIE" || resultPrimary == "BAUHAUS JAMSTVO" || resultPrimary == "Garantie" || resultPrimary == "GARANTÍA BAUHAUS") {
					KeywordUtil.logInfo("guarantee icon exist, the text is right and its on the first colum");
					return true
				} else if (resultSecondary == "GARANTIE" || resultSecondary == "BAUHAUS JAMSTVO" || resultSecondary == "Garantie" || resultSecondary == "GARANTÍA BAUHAUS") {
					KeywordUtil.logInfo("guarantee icon exist, the text is right and its on the second colum");
					return true
				} else if(resultThird == "GARANTIE" || resultThird == "BAUHAUS JAMSTVO" || resultThird == "Garantie" || resultThird == "GARANTÍA BAUHAUS") {
					KeywordUtil.logInfo("guarantee icon exist, the text is right and its on the third colum");
					return true
				}else {
					KeywordUtil.logInfo("guarantee icon dosen't exist");
					return false
				}

			}else {
				KeywordUtil.logInfo("guarantee icon dosen't exist");
				return false
			}
		}
		else {
			KeywordUtil.logInfo("guarantee icon dosen't exist or the CSS isn't write");
			return false
		}
	}


	@Keyword
	def checkAdvertisingIcon() {

		String primary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), 3, FailureHandling.OPTIONAL)
		String resultPrimary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Primary'), FailureHandling.OPTIONAL)
		String secondary = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'), 3, FailureHandling.OPTIONAL)
		String resultSecondary = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_Secondary'), FailureHandling.OPTIONAL)

		String flagwidthPrimary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
		String flagheightPrimary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)


		String flagwidthSecondary = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)
		String flagheightSecondary = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductIcons/ProductIcon_variants'), FailureHandling.OPTIONAL)

		KeywordUtil.logInfo(primary);
		KeywordUtil.logInfo(secondary);
		KeywordUtil.logInfo(resultPrimary);
		KeywordUtil.logInfo(resultSecondary);

		if( flagwidthPrimary <= tilewidth && flagheightPrimary <= tileheight || flagwidthSecondary <= tilewidth && flagheightSecondary <= tileheight) {

			if(primary == "true" || secondary == "true") {

				if(resultPrimary == "Werbung") {
					KeywordUtil.logInfo("Ad icon exist, the text is right and its on the first colum");
					return true
				} else if (resultSecondary == "Werbung") {
					KeywordUtil.logInfo("Ad icon exist, the text is right and its on the second colum");
					return true
				}

				else {
					KeywordUtil.logInfo("Ad icon doesn't exist");
					return false
				}

			}
		}else {
			KeywordUtil.logInfo("Ad icon doesn't exist or the CSS isn't right");
			return false
		}


	}

	@Keyword
	def checkProductName() {
		//change for string contains:
		String flag = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductName'), 3)
		String flagWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductName'))
		String flagHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductName'))

		if( flagWidth <= tilewidth && flagHeight <= tileheight) {

			KeywordUtil.logInfo("Product name is visible: "+flag);

			if(flag == "true") {
				KeywordUtil.logInfo("Product name exist");
				return true
			}
			else {
				KeywordUtil.logInfo("Product name not exist");
				return false
			}}else {
			KeywordUtil.logInfo("Product name not exist, CSS isn't right as well");
			return false
		}
	}

	@Keyword
	def checkProductBrand() {
		//change for string contains:
		String flag = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductBrand'), 3)
		String flagWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductBrand'))
		String flagHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductBrand'))

		if( flagWidth <= tilewidth && flagHeight <= tileheight) {

			KeywordUtil.logInfo("Product brand is visible: "+flag);

			if(flag == "true") {
				KeywordUtil.logInfo("Product brand exist");
				return true
			}
			else {
				KeywordUtil.logInfo("Product brand not exist");
				return false
			}}else {
			KeywordUtil.logInfo("Product brand not exist, CSS isn't right as well");
			return false
		}
	}

	@Keyword
	def checkProductAttributes() {
		//change for string contains:

		String flag = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductAttributes'), 3)
		String flagWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductAttributes'))
		String flagHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ProductAttributes'))

		if( flagWidth <= tilewidth && flagHeight <= tileheight) {

			KeywordUtil.logInfo("Product attributes is visible: "+flag);

			if(flag == "true") {
				KeywordUtil.logInfo("Product attributes exist");
				return true
			}
			else {
				KeywordUtil.logInfo("Product attributes not exist");
				return false
			}}else {
			KeywordUtil.logInfo("Product attributes not exist, CSS isn't right as well");
			return false
		}
	}

	@Keyword
	def checkReseravable() {

		String reservable = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), 3, FailureHandling.OPTIONAL)
		String resultReservable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), FailureHandling.OPTIONAL)
		String reservableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), FailureHandling.OPTIONAL)
		String reservableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), FailureHandling.OPTIONAL)

		String available = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), 3, FailureHandling.OPTIONAL)
		String resultavailable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), FailureHandling.OPTIONAL)
		String availableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), FailureHandling.OPTIONAL)
		String availableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), FailureHandling.OPTIONAL)

		String orderable = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), 3, FailureHandling.OPTIONAL)
		String resultOrderable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), FailureHandling.OPTIONAL)
		String orderableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), FailureHandling.OPTIONAL)
		String orderableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), FailureHandling.OPTIONAL)


		String checkText = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSCheckText'), FailureHandling.OPTIONAL)


		//checkText.contains("reservable")
		KeywordUtil.logInfo(checkText);
		KeywordUtil.logInfo(resultReservable);
		KeywordUtil.logInfo(resultavailable);
		KeywordUtil.logInfo(resultOrderable);

		if( reservableWidth <= tilewidth && reservableHeight <= tileheight || availableWidth <= tilewidth && availableHeight <= tileheight || orderableWidth <= tilewidth && orderableHeight <= tileheight) {

			if(reservable == "true" || available == "true" || orderable == "true" ) {
				if(checkText.contains("reservierbar") || checkText.contains("") || checkText.contains("Reservable") || checkText.contains("reserveerbaar")) {
					KeywordUtil.logInfo("Product is reserable, the text is right");
					return true
				}else if(available == "true") {
					KeywordUtil.logInfo("Product is available, but should be reservable");
					return false

				}else if(orderable == "true") {
					KeywordUtil.logInfo("Product is orderable, but should be reservable");
					return false
				}else {
					KeywordUtil.logInfo("The Product isn't orderable, available and reseravle, there is a problem");
					return false
				}

			}else {
				KeywordUtil.logInfo("The Product isn't orderable or available or reserable, something went wrong");
				return false
			}
		}else {
			KeywordUtil.logInfo("The Product isn't orderable or available or reserable, something went wrong, the Css is wrong as well");
			return false
		}
	}

	@Keyword
	def checkOrderable() {

		String reservable = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), 3, FailureHandling.OPTIONAL)
		String resultReservable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), FailureHandling.OPTIONAL)
		String reservableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), FailureHandling.OPTIONAL)
		String reservableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), FailureHandling.OPTIONAL)

		String available = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), 3, FailureHandling.OPTIONAL)
		String resultavailable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), FailureHandling.OPTIONAL)
		String availableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), FailureHandling.OPTIONAL)
		String availableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), FailureHandling.OPTIONAL)

		String orderable = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), 3, FailureHandling.OPTIONAL)
		String resultOrderable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), FailureHandling.OPTIONAL)
		String orderableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), FailureHandling.OPTIONAL)
		String orderableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), FailureHandling.OPTIONAL)


		String checkText = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSCheckText'), FailureHandling.OPTIONAL)


		//checkText.contains("reservable")
		KeywordUtil.logInfo(checkText);
		KeywordUtil.logInfo(resultReservable);
		KeywordUtil.logInfo(resultavailable);
		KeywordUtil.logInfo(resultOrderable);


		if( reservableWidth <= tilewidth && reservableHeight <= tileheight || availableWidth <= tilewidth && availableHeight <= tileheight || orderableWidth <= tilewidth && orderableHeight <= tileheight) {

			if(reservable == "true" || available == "true" || orderable == "true" ) {
				if(checkText.contains("nicht verfügbar") || checkText.contains("Nije dostupno") || checkText.contains("Niet beschikbaar")) {
					KeywordUtil.logInfo("Product is orderable, the text is right");
					return true
				}else if(available == "true") {
					KeywordUtil.logInfo("Product is available, but should be orderable");
					return false

				}else if(reservable == "true") {
					KeywordUtil.logInfo("Product is reservable, but should be orderable");
					return false
				}else {
					KeywordUtil.logInfo("The Product isn't orderable, available and reseravle, there is a problem");
					return false
				}

			}else {
				KeywordUtil.logInfo("The Product isn't orderable or available or reserable, something went wrong");
				return false
			}
		}else {
			KeywordUtil.logInfo("The Product isn't orderable or available or reserable, something went wrong, the CSS is wrong as well");
			return false
		}
	}

	@Keyword
	def checkAvailable() {

		String reservable = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), 3, FailureHandling.OPTIONAL)
		String resultReservable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), FailureHandling.OPTIONAL)
		String reservableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), FailureHandling.OPTIONAL)
		String reservableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSReservable'), FailureHandling.OPTIONAL)

		String available = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), 3, FailureHandling.OPTIONAL)
		String resultavailable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), FailureHandling.OPTIONAL)
		String availableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), FailureHandling.OPTIONAL)
		String availableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSAvailable'), FailureHandling.OPTIONAL)

		String orderable = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), 3, FailureHandling.OPTIONAL)
		String resultOrderable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), FailureHandling.OPTIONAL)
		String orderableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), FailureHandling.OPTIONAL)
		String orderableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSOrderable'), FailureHandling.OPTIONAL)


		String checkText = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSCheckText'), FailureHandling.OPTIONAL)
		KeywordUtil.logInfo("__________________");
		KeywordUtil.logInfo(checkText);
		KeywordUtil.logInfo("__________________");
		KeywordUtil.logInfo(resultReservable);
		KeywordUtil.logInfo(resultavailable);
		KeywordUtil.logInfo(resultOrderable);
		KeywordUtil.logInfo("__________________");
		if( reservableWidth <= tilewidth && reservableHeight <= tileheight || availableWidth <= tilewidth && availableHeight <= tileheight || orderableWidth <= tilewidth && orderableHeight <= tileheight) {

			if(reservable == "true" || available == "true" || orderable == "true" ) {
				if( checkText.contains("bestellbar") || checkText.contains("Moguće rezervirati") || checkText.contains("available")) {
					KeywordUtil.logInfo("Product is available, the text is right");
					return true
				}else if(orderable == "true") {
					KeywordUtil.logInfo("Product is orderable, but should be available");
					return false

				}else if(reservable == "true") {
					KeywordUtil.logInfo("Product is reservable, but should be available");
					return false
				}else {
					KeywordUtil.logInfo("The Product isn't orderable, available and reseravle, there is a problem");
					return false
				}

			}else {
				KeywordUtil.logInfo("The Product isn't orderable or available or reserable, something went wrong");
				return false
			}}else {
			KeywordUtil.logInfo("The Product isn't orderable or available or reserable, something went wrong");
			return false
		}
	}

	@Keyword
	def checkOnlineAvailable() {


		String Available = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/OnlineStatus/OnlineAvailable'), 3, FailureHandling.OPTIONAL)
		String resultAvailable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/OnlineStatus/OnlineAvailable'), FailureHandling.OPTIONAL)

		String AvailableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/OnlineStatus/OnlineAvailable'))
		String AvailableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/OnlineStatus/OnlineAvailable'))

		if( AvailableWidth <= tilewidth && AvailableHeight <= tileheight) {

			if(Available == "true") {
				if(resultAvailable == "Online verfügbar" || resultAvailable == "Disponible online" || resultAvailable == "Dostupno za dostavu" || resultAvailable == "Thuisbezorgen") {
					KeywordUtil.logInfo("The product is orderable! and the tag is shown! ");
					return true
				}else {
					KeywordUtil.logInfo("The product is not orderable but it should be orderable");
					return false
				}

			}else {
				KeywordUtil.logInfo("The product is not orderable but it should be orderable");
				return false
			}}else {
			KeywordUtil.logInfo("The product is not orderable but it should be orderable, CSS isn't right as well");
			return false
		}

	}


	@Keyword
	def checkOnlineNotAvailable() {

		String notAvailable = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/OnlineStatus/OnlineNotAvailable'), 3, FailureHandling.OPTIONAL)
		String resultnotAvailable = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/OnlineStatus/OnlineNotAvailable'), FailureHandling.OPTIONAL)


		String notAavailableWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/OnlineStatus/OnlineNotAvailable'))
		String notAvailableHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/OnlineStatus/OnlineNotAvailable'))

		if( availableWidth <= tilewidth && AvailableHeight <= tileheight) {

			if(notAvailable == "true") {
				if(resultnotAvailable == "Niet online bestelbaar" || resultnotAvailable == "Online nicht verfügbar" || resultnotAvailable == "Online nicht verfügbar") {
					KeywordUtil.logInfo("The product is not orderable! and the tag is shown!  ");
					return true
				}else {
					KeywordUtil.logInfo("The product is orderable but it should be unorderable");
					return false
				}

			}else {
				KeywordUtil.logInfo("The product is orderable but it should be unorderable");
				return false
			}}else {
			KeywordUtil.logInfo("The product is orderable but it should be unorderable, CSS isn't right as well");
			return false
		}

	}

	@Keyword
	def checkEnergyEfficiencyClass() {

		String energyefficiencyclass = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/EnergyEfficiencyClassBox'), 3)

		String energyefficiencyclassWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/EnergyEfficiencyClassBox'))
		String energyefficiencyclassHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/EnergyEfficiencyClassBox'))

		if( energyefficiencyclassWidth <= tilewidth && energyefficiencyclassHeight <= tileheight) {


			if(energyefficiencyclass == "true") {
				KeywordUtil.logInfo("Energy efficiency class box is shown");
				WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/EnergyEfficiencyClassBox'))
				KeywordUtil.logInfo("Clicked energy efficiency class box" );
				String picture = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/EnergyEfficiencyImg'), 5)
				KeywordUtil.logInfo(picture );
				if(picture == "true") {
					KeywordUtil.logInfo("Energy efficiency image is shown too");
					return true
				}
			}
			else {
				KeywordUtil.logInfo("Energy efficiency image isn't shown, there is a problem");
				return false
			}}else {
			KeywordUtil.logInfo("Energy efficiency image isn't shown, there is a problem, CSS isn't right as well ");
			return false
		}
	}

	@Keyword
	def checkBAUHAUSChoose() {

		String BAUHAUSChoose = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSChoose'), 3)
		String resultBAUHAUSChoose = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSChoose'), FailureHandling.OPTIONAL)

		String BAUHAUSChooseWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSChoose'))
		String BAUHAUSChooseHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BAUHAUSAvailability/BAUHAUSChoose'))

		if( BAUHAUSChooseWidth <= tilewidth && BAUHAUSChooseHeight <= tileheight) {

			if(BAUHAUSChoose == "true") {
				KeywordUtil.logInfo("Choose BAUHAUS box is shown");

				if(resultBAUHAUSChoose == "BAUHAUS selecteren" || resultBAUHAUSChoose == "BAUHAUS wählen"|| resultBAUHAUSChoose == "Seleccionar tu BAUHAUS" || resultBAUHAUSChoose == "Odaberite BAUHAUS"   ) {
					KeywordUtil.logInfo("Choose BAUHAUS box is shown and the Text is right");
					return true
				}
			}
			else {
				KeywordUtil.logInfo("Choose BAUHAUS box isn't shown, there is a problem");
				return false
			}}
		else {
			KeywordUtil.logInfo("Choose BAUHAUS box isn't shown, there is a problem, CSS isn't right as well ");
			return false
		}
	}

	@Keyword
	def checkComparisonIcon() {

		String comparison = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ComparisonIcon/ProductComparisonButton'), 3, FailureHandling.OPTIONAL)
		String resultComparison = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ComparisonIcon/ProductComparisonButton'), FailureHandling.OPTIONAL)

		String comparisonWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ComparisonIcon/ProductComparisonButton'), FailureHandling.OPTIONAL)
		String comparisonHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ComparisonIcon/ProductComparisonButton'), FailureHandling.OPTIONAL)

		if( comparisonWidth <= tilewidth && comparisonHeight <= tileheight) {

			if(comparison == "true" ) {
				if(resultComparison == "Vergleichen" || resultComparison == "Vergelijken" || resultComparison == "Comparar" || resultComparison == "Usporedi") {
					KeywordUtil.logInfo("Comparison button exist and the text is right");
					WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ComparisonIcon/ProductComparisonButton'))
					WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Sidebar/CloseSideBar'))
					String comparisonpage = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ComparisonIcon/goToComparisonButton'), 3, FailureHandling.OPTIONAL)
					if(comparisonpage == "true") {
						String resultComparisonpage = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ComparisonIcon/goToComparisonButton'), FailureHandling.OPTIONAL)
						KeywordUtil.logInfo(resultComparisonpage);
						if(resultComparisonpage == "Zum Vergleich" || resultComparisonpage == "Naar vergelijk" || resultComparisonpage == "A comparación" || resultComparisonpage == "Pogledaj usporedbu") {
							String comparisonIcon = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/ComparisonIcon/Comparison'), 3, FailureHandling.OPTIONAL)
							if(comparisonIcon == "true") {
								KeywordUtil.logInfo("To comparison page button exist, the text is right and the comparison Icon exists");
								return true
							}
							KeywordUtil.logInfo("To comparison page button exist and the text is right, but the comparison Icon dosen't exists");
							return false
						}else {
							KeywordUtil.logInfo("Comparison button exist but the Text isn't right");
							return false
						}
					}
				}
			}else {
				KeywordUtil.logInfo("Comparison button dosen't exist");
				return false
			}
		}
		else {
			KeywordUtil.logInfo("Comparison button dosen't exist or the CSS isn't write");
			return false
		}
	}

	@Keyword
	def checkBookmarkIcon() {

		String bookmark = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BookmarkIcon/ProductWatchlistButton'), 3, FailureHandling.OPTIONAL)
		String resultBookmark = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BookmarkIcon/ProductWatchlistButton'), FailureHandling.OPTIONAL)

		String bookmarkWidth = WebUI.getElementWidth(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BookmarkIcon/ProductWatchlistButton'), FailureHandling.OPTIONAL)
		String bookmarkHeight = WebUI.getElementHeight(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BookmarkIcon/ProductWatchlistButton'), FailureHandling.OPTIONAL)

		KeywordUtil.logInfo(bookmarkWidth);
		KeywordUtil.logInfo(tilewidth);
		KeywordUtil.logInfo('---------');
		KeywordUtil.logInfo(bookmarkHeight);
		KeywordUtil.logInfo(tileheight);
		if( bookmarkWidth <= tilewidth && bookmarkHeight <= tileheight) {

			if(bookmark == "true" ) {
				if(resultBookmark == "Označi" || resultBookmark == "Merken" || resultBookmark == "Op verlanglijstje" || resultBookmark == "Favoritos") {
					KeywordUtil.logInfo("Bookmark button exist and the text is right");
					WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BookmarkIcon/ProductWatchlistButton'))
					WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Sidebar/CloseSideBar'))
					String resultBookmarkpage = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BookmarkIcon/goToWatchlistButton'), FailureHandling.OPTIONAL)
					if(resultBookmarkpage == "Nastavi na popis" || resultBookmarkpage == "Zur Merkliste" || resultBookmarkpage == "Naar verlanglijstje" || resultBookmarkpage == "Mis favoritos") {
						String bookmarkIcon = WebUI.verifyElementPresent(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/ProductTile/BookmarkIcon/Watchlist'), 3, FailureHandling.OPTIONAL)
						if(bookmarkIcon == "true") {
							KeywordUtil.logInfo("To comparison page button exist, the text is right and the bookmark icon exists");
							return true
						}
						KeywordUtil.logInfo("To bookmark page button exist and the text is right, but the bookmark icon dosen't exists");
						return false
					}
				}else {
					KeywordUtil.logInfo("Bookmark button exist but the Text isn't right");
					return false
				}

			}else {
				KeywordUtil.logInfo("Bookmark button dosen't exist");
				return false
			}
		}
		else {
			KeywordUtil.logInfo("Bookmark button dosen't exist or the CSS isn't write");
			return false
		}

	}
}