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


public class ProductListingPageHelper {

	@Keyword
	def setViewPortsizeToMobile(){
		WebUI.setViewPortSize(658, 900);
		KeywordUtil.logInfo("New Width is: " + WebUI.getViewportWidth());
	}

	@Keyword
	def navigateToOverview(String countryId, String category,String productNr  ) {
		String link = GlobalHelper.getMapperByCountryId(countryId, "link");
		String item =  productNr;
		String categoryLink = "/" + category + "/c/"  ;
		WebDriver webDriver = DriverFactory.getWebDriver();
		webDriver.navigate().to(link + categoryLink + item);
	}

	@Keyword
	def checkCategoryTitle() {

		String flag = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/LabelsCategory/LabelCategory'), 5)
		KeywordUtil.logInfo("Title is visible: "+flag);

		if(flag == "true") {
			KeywordUtil.logInfo("Category Title exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Category Title not exist");
			return false
		}
	}

	@Keyword
	def checkNumberOfProducts() {

		String flag = WebUI.verifyElementVisibleInViewport(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/LabelsCategory/LabelNumberOfProducts'), 5);
		KeywordUtil.logInfo("Title is visible: "+ flag);

		if(flag == "true") {
			KeywordUtil.logInfo("Number of products exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Number of products  not exist");
			return false
		}
	}

	@Keyword
	def checkSubCategoryTitle() {

		String flag = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/LabelsCategory/SubCategory/SubCategoryLabel'), 5)
		KeywordUtil.logInfo("Title is visible: "+flag);

		if(flag == "true") {
			KeywordUtil.logInfo("Subcategory Title exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Subcategory Title not exist");
			return false
		}
	}
	@Keyword
	def checkNumberOfVariants() {

		String test = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/LabelsCategory/LabelNumberOfVariants'), 5);
		int width = WebUI.getViewportWidth();
		int mobileViewPort = 660;
		if( test == true) {
			KeywordUtil.logInfo("Variants are Visible: " + test);
		}
		else {
			KeywordUtil.logInfo("Variants are Visible: " + test);
		}

		KeywordUtil.logInfo("The Width is: " + width);

		if(test == "true"){
			if(width > mobileViewPort) {
				KeywordUtil.logInfo("Number of variants exist");
				return true;
			}
			else if(width < mobileViewPort){
				KeywordUtil.logInfo("Number of variants should not exist");
				return false;
			}
		}
		else if (test == "false") {
			if(width < mobileViewPort) {
				KeywordUtil.logInfo("Number of variants not exist");
				return true;
			}
			else if(width > mobileViewPort){
				KeywordUtil.logInfo("Number of variants should exist");
				return false;
			}
		}
	}



	@Keyword
	def checkproductImage() {

		String flag = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP//ProductTile/ProductImage'), 5)
		KeywordUtil.logInfo("Image is visible: "+flag);

		if(flag == "true") {
			KeywordUtil.logInfo("Image Title exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Image Title not exist");
			return false
		}
	}

	@Keyword
	def checkIfStickyOrSlider(){
		String normal = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterbarNormal'), 5);
		String sticky = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterbarSticky'), 5);
		int width = WebUI.getViewportWidth();
		int mobileViewPort = 660;
		if( normal == true && sticky == false && mobileViewPort < width ) {
			KeywordUtil.logInfo("Filterbar ist visible and not sticky " + normal);
			return true;
		}
		else if (normal == true && sticky == false && mobileViewPort > width) {
			KeywordUtil.logInfo("Filterbar should be sticky " + normal);
			return false;
		}
		else if (normal == false && sticky == true && mobileViewPort > width) {
			KeywordUtil.logInfo("Filterbar ist visible and sticky " + normal);
			return true;
		}
		else if (normal == false && sticky == true && mobileViewPort < width) {
			KeywordUtil.logInfo("Filterbar should not be sticky " + normal);
			return false;
		}
		else {
			KeywordUtil.logInfo("Error" + normal + sticky);
			return false;
		}

		KeywordUtil.logInfo("The Width is: " + width);

		if(test == "true"){
			if(width > mobileViewPort) {
				KeywordUtil.logInfo("Number of variants exist");
				return true;
			}
			else if(width < mobileViewPort){
				KeywordUtil.logInfo("Number of variants should not exist");
				return false;
			}
		}
		else if (test == "false") {
			if(width < mobileViewPort) {
				KeywordUtil.logInfo("Number of variants not exist");
				return true;
			}
			else if(width > mobileViewPort){
				KeywordUtil.logInfo("Number of variants should exist");
				return false;
			}
		}
	}

	@Keyword
	def checkCategoryButton() {

		//Insert Object
		String categoryButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/CategoryButton'), 5)
		KeywordUtil.logInfo("Category button is visible: "+ categoryButton);

		if(categoryButton == "true") {
			KeywordUtil.logInfo("Category button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Category button not exist");
			return false
		}
	}

	@Keyword
	def checkMaterialButton() {

		//Insert Object
		String materialButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/MaterialButton'), 5)
		KeywordUtil.logInfo("Material button is visible: "+ materialButton);

		if(materialButton == "true") {
			KeywordUtil.logInfo("Material button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Material button not exist");
			return false
		}
	}

	@Keyword
	def checkOffersButton() {


		//Insert Object
		String OffersButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/OffersButton'), 5)
		KeywordUtil.logInfo("Offers button is visible: "+ OffersButton);

		if(OffersButton == "true") {
			KeywordUtil.logInfo("Offers button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Offers button not exist");
			return false
		}
	}

	@Keyword
	def checkPriceButton() {

		//Insert Object
		String priceButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/PriceButton'), 5)
		KeywordUtil.logInfo("Price button is visible: "+ priceButton);

		if(priceButton == "true") {
			KeywordUtil.logInfo("Price button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Price button not exist");
			return false
		}
	}

	@Keyword
	def checkProductTypButton() {

		//Insert Object
		String producttypButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/ProducttypeButton'), 5)
		KeywordUtil.logInfo("Producttyp button is visible: "+ producttypButton);

		if(producttypButton == "true") {
			KeywordUtil.logInfo("Producttyp button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Producttyp button not exist");
			return false
		}
	}

	@Keyword
	def checkFilterAndSortButton() {

		//Insert Object
		String filterAndSortButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterAndSortButton'), 5)
		KeywordUtil.logInfo("Filter and sort button is visible: "+ filterAndSortButton);

		if(filterAndSortButton == "true") {
			KeywordUtil.logInfo("Filter and sort button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Filter and sort button not exist");
			return false
		}
	}

	@Keyword
	def checkBrandButton() {

		String brandButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/BrandButton'), 5)
		KeywordUtil.logInfo("Brand button is visible: "+ brandButton);

		if(brandButton == "true") {
			KeywordUtil.logInfo("Brand button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Brand button not exist");
			return false
		}
	}



}

