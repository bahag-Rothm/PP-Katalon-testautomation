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
	def setViewPortsizeToMobileforslider(){
		WebUI.setViewPortSize(500, 900);
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
	def navigateToProducts(String countryId, String countryLink,String productNr) {
		String link = GlobalHelper.getMapperByCountryId(countryId,"link");
		String item =  productNr;
		String categoryLink = "/" + countryLink ;
		KeywordUtil.logInfo(link);
		KeywordUtil.logInfo(item);
		KeywordUtil.logInfo(categoryLink);
		String finishedlink = link  + categoryLink +item
		KeywordUtil.logInfo(finishedlink);
		WebDriver webDriver = DriverFactory.getWebDriver();
		webDriver.navigate().to(finishedlink);
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
	def checkIfNormalBar(){
		String normal = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterbarNormal'), 5, FailureHandling.OPTIONAL);
		KeywordUtil.logInfo(normal);
		if( normal == 'true') {
			KeywordUtil.logInfo("Filterbar is visible and not sticky " + normal);
			return true;
		}

		else {
			KeywordUtil.logInfo("Error"  + normal);
			return false;
		}
	}

	@Keyword
	def checkIfStickyBar(){
		String sticky = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterbarSticky'), 5, FailureHandling.OPTIONAL);
		if( sticky == 'true') {
			KeywordUtil.logInfo("Filterbar is visible and sticky " + sticky);
			return true;
		}

		else {
			KeywordUtil.logInfo("Error"  + sticky);
			return false;
		}
	}

	@Keyword
	def checkIfSliderBar(){
		String slider = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterbarSlider'), 5, FailureHandling.OPTIONAL);
		String slider2 = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterBarSilder2'), 5, FailureHandling.OPTIONAL);
		String slider3 = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterBarSilder3'), 5, FailureHandling.OPTIONAL);
		String slider4 = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterBarSilder4'), 5, FailureHandling.OPTIONAL);
		KeywordUtil.logInfo(slider + slider2 + slider3 +slider4 );
		if( slider == "true" || slider2 == "true" || slider3 == "true" || slider4 == "true" ) {
			KeywordUtil.logInfo("Filterbar is a slider " + slider + slider2 + slider3+slider4 );
			return true;
		}

		else {
			KeywordUtil.logInfo("Error"  + slider + slider2 + slider3+slider4 );
			return false;
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
	def clickCategoryButton() {

		//Insert Object
		String categoryButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/CategoryButton'), 5)
		KeywordUtil.logInfo("Category button is visible: "+ categoryButton);

		if(categoryButton == "true") {
			KeywordUtil.logInfo("Category button exist");

			String clickButton = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/CategoryButton'))


			return true

		}
		else {
			KeywordUtil.logInfo("Category button not exist");
			return false
		}
	}
	@Keyword
	def clickSubcategoryButton() {

		//Insert Object
		String subCategoryButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSidebar/ToGardenFurniture'), 5)
		KeywordUtil.logInfo("Subcategory button is visible: "+ subCategoryButton);

		if(subCategoryButton == "true") {
			KeywordUtil.logInfo("Category button exist");

			String clickButton = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSidebar/ToGardenFurniture'))
			return true
		}
		else {
			KeywordUtil.logInfo("Category button not exist");
			return false
		}
	}

	@Keyword
	def clickBacktoGardenButton() {

		//Insert Object
		String backToGarden = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSidebar/BackToGarden'), 5)
		KeywordUtil.logInfo("Subcategory button is visible: "+ backToGarden);

		if(backToGarden == "true") {
			KeywordUtil.logInfo("Back to garden button exist");

			String backToGarden2 = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSidebar/BackToGarden'))
			return true
		}
		else {
			KeywordUtil.logInfo("Back to garden button not exist");
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
	def checkFilterBarPriceButton() {


		String PriceButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/SideBarPriceButton'), 5)
		KeywordUtil.logInfo("Price button is visible: "+ PriceButton);

		if(PriceButton == "true") {
			String PriceButton2 = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/SideBarPriceButton'))

			return true
		}
		else {
			KeywordUtil.logInfo("Price button not exist");
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
	def getProductNumberButtonText() {

		//Insert Object
		String verifyProductNumberButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/ShowProductsButton'),3)

		String ProductNumberButton = WebUI.getText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/ShowProductsButton'))

		KeywordUtil.logInfo(ProductNumberButton);

		if(verifyProductNumberButton == "true") {
			KeywordUtil.logInfo("Numbers are Shown");
			return true
		}
		else {
			KeywordUtil.logInfo("Numbers are not Shown");
			return false
		}
	}

	@Keyword
	def clickProductShown() {

		//Insert Object
		String verifyProductNumberButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/ShowProductsButton'),3)

		String ProductNumberButton = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/ShowProductsButton'))


		if(verifyProductNumberButton == "true") {

			KeywordUtil.logInfo("Numbers are Shown");
			return true
		}
		else {
			KeywordUtil.logInfo("Numbers are not Shown");
			return false
		}
	}


	@Keyword
	def toTheTopIcon() {

		//Insert Object
		String toTheTop = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Sidebar/ToTheTop'),3)
		KeywordUtil.logInfo(toTheTop);
		if(toTheTop == "true") {
			String toTheTopbutton = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Sidebar/ToTheTop'))


			KeywordUtil.logInfo("To the top icon works");
			return true
		}
		else {
			KeywordUtil.logInfo("To the top icon dosen't work");
			return false
		}
	}

	@Keyword
	def clickFilterAndSortButton() {

		//Insert Object
		String filterAndSortButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterAndSortButton'), 5)
		KeywordUtil.logInfo("Filter and sort button is visible: "+ filterAndSortButton);

		if(filterAndSortButton == "true") {
			String filterAndSortButton2 = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FilterAndSortButton'))
			return true
		}
		else {
			KeywordUtil.logInfo("Filter and sort button not exist");
			return false
		}
	}

	@Keyword
	def clickMinPriceTextBox() {

		//Insert Object
		String minPrice = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/PriceminSlider'), 5)
		KeywordUtil.logInfo("Filter and sort button is visible: "+ minPrice);

		if(minPrice == "true") {

			WebUI.setText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/PriceminSlider'), "54")

			return true
		}
		else {
			KeywordUtil.logInfo("Filter and sort button not exist");
			return false
		}
	}

	@Keyword
	def clickMaxPriceTextBox() {

		//Insert Object
		String maxPrice = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/PriceMaxSlider'), 5)
		WebUI.setText(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/PriceMaxSlider'), "999")
		if(maxPrice == "true") {

			return true
		}
		else {
			KeywordUtil.logInfo("Filter and sort button not exist");
			return false
		}
	}

	@Keyword
	def checkTextBoxFilters() {

		//Insert Object
		String highlightedFilters = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/HighlightedFilters'), 5)

		if(highlightedFilters == "true") {
			KeywordUtil.logInfo("Highlighted filters exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Highlighted filters not exist");
			return false
		}
	}

	@Keyword
	def clickPriceButton() {

		//Insert Object
		String Price = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/PriceButton'), 5)
		KeywordUtil.logInfo("Price button is visible: "+ Price);

		if(Price == "true") {
			String Price2 = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/PriceButton'))
			return true
		}
		else {
			KeywordUtil.logInfo("Price button not exist");
			return false
		}
	}


	@Keyword
	def clickBrandButton() {

		//Insert Object
		String brandButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/BrandButton'), 5)
		KeywordUtil.logInfo("Brand button is visible: "+ brandButton);

		if(brandButton == "true") {
			String brandButton2 = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/BrandButton'))
			return true
		}
		else {
			KeywordUtil.logInfo("Brand button not exist");
			return false
		}
	}

	@Keyword
	def clickFirstBrandButton() {

		//Insert Object
		String brandButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FirstBrandButton'), 5)
		KeywordUtil.logInfo("Brand button is visible: "+ brandButton);

		if(brandButton == "true") {
			String brandButton2 = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/FirstBrandButton'))
			return true
		}
		else {
			KeywordUtil.logInfo("Brand button not exist");
			return false
		}
	}

	@Keyword
	def clickBrandDeleteButton() {

		//Insert Object
		String Button = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/DeleteAllBrandFilters'), 5)
		KeywordUtil.logInfo("Brand delete button is visible: "+ Button);

		if(Button == "true") {
			String Button2 = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/DeleteAllBrandFilters'))
			return true
		}
		else {
			KeywordUtil.logInfo("Brand delete button not exist");
			return false
		}
	}


	@Keyword
	def clickSortButton() {

		//Insert Object
		String SortButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/SortingButton'), 5)
		KeywordUtil.logInfo("Sort button is visible: "+ SortButton);

		if(SortButton == "true") {
			String SortButton2 = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Filterbar/SortingButton'))
			return true
		}
		else {
			KeywordUtil.logInfo("Sort button not exist");
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
	@Keyword
	def closeSideBar() {

		String closeSidebar = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Sidebar/CloseIconSidebar'), 5)

		String close = WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/Sidebar/CloseIconSidebar'))

		if(closeSidebar == "true") {
			KeywordUtil.logInfo("Brand button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Brand button not exist");
			return false
		}
	}

	@Keyword
	def clickColorFilter() {

		//Insert Object
		String Color = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/Color'), 5)
		WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/Color'))
		if(Color == "true") {

			return true
		}
		else {
			KeywordUtil.logInfo("Color button not exist");
			return false
		}
	}

	@Keyword
	def clickFirstColor() {

		//Insert Object
		String firstColor = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/FirstColor'), 5)
		WebUI.click(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/FirstColor'))
		if(firstColor == "true") {

			return true
		}
		else {
			KeywordUtil.logInfo("firstColor not exist");
			return false
		}
	}


	@Keyword
	def checkSortingNameAscButton() {

		String SortingNameAscButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/SortingFilter/SortingNameAsc'), 5)
		KeywordUtil.logInfo("Sorting name Asc button button is visible: "+ SortingNameAscButton);

		if( SortingNameAscButton == "true") {
			KeywordUtil.logInfo("Sorting name ASC button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Sorting name ASC button not exist");
			return false
		}
	}


	@Keyword
	def checkSortingPriceAscButton() {
		WebUI.delay(5)
		String SortingPriceAscButton = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/SortingFilter/SortPriceAsc'), 5)
		KeywordUtil.logInfo("Sorting price Asc button button is visible: "+ SortingPriceAscButton);

		if( SortingPriceAscButton == "true") {
			KeywordUtil.logInfo("Sorting price ASC button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Sorting price ASC button not exist");
			return false
		}
	}

	@Keyword
	def checkSortingOnlineAvailableButton() {

		String SortingOnlineAvailable = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/SortingFilter/SortingOnlineAvailable'), 5)
		KeywordUtil.logInfo("Brand button is visible: "+ SortingOnlineAvailable);

		if(SortingOnlineAvailable == "true") {
			KeywordUtil.logInfo("Sorting Online Available button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Sorting Online Available button not exist");
			return false
		}
	}


	@Keyword
	def checkPriceDescButton() {

		String PriceDESC = WebUI.waitForElementVisible(findTestObject('Onlineshop.Pages/ProduktePage/Elements/PLP/FilterSideBar/SortingFilter/SortPriceDESC'), 5)
		KeywordUtil.logInfo("PriceDESC button is visible: "+ PriceDESC);

		if(PriceDESC == "true") {
			KeywordUtil.logInfo("Brand button exist");
			return true
		}
		else {
			KeywordUtil.logInfo("Brand button not exist");
			return false
		}
	}



}

