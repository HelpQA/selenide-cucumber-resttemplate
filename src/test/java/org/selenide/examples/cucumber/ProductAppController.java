package org.selenide.examples.cucumber;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;


import com.codeborne.selenide.Configuration;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductAppController {
	
	@Given("Open browser with {string}")
	  public void openBrowser(String url) {
	    Configuration.reportsFolder = "target/surefire-reports";
	    Configuration.headless = false;
	    open(url);
	  }
	
	@When("Click on Available Product button to view all products available")
	public void viewAllProducts() {
		$(byXpath("//a[@href='/get_products']")).click();
	}
	
	@Then("Verify on page Available Products")
	public void validateAvailablePage() {
		$("h2").shouldHave(text("Available products"));
	}
	
	@Then("Verify on page Available Products Add New Product Button available")
	public void addNewButton() {
		$(byXpath("//a[@href='/add_product']")).exists();
	}
	
	@Then("Verify product number {string}")
	public void previewProduct(String productNumber) {
		String xpathOfProduct = "//a[@href='/show/" + productNumber + "']";
		System.out.println("xpathOfProduct=>" + xpathOfProduct);
		$(byXpath(xpathOfProduct)).click();
	}
	
	@Then("Verify you can view single product")
	public void verifySingleProduct() {
		$("h2").shouldHave(text("Product manipulation completed"));
	}
	
	@Then("Verify on page Single Product detail have button To Product list")
	public void verifyButtonToProductList() {
		$(byXpath("//a[@href='/get_products']")).exists();
	}
	
	@When("Click on Add New Product")
	public void addNewProduct() {
		$(byXpath("//a[@href='/add_product']")).click();
	}
	
	@Then("Enter product Details Name , Price , Description {string} {string} {string}")
	public void addNewProduct(String name, String price, String description) {
		$("#name").setValue(name);
		$("#price").setValue(price);
		$("#description").setValue(description);
		$x("//button[text()=\"Save\"]").click();
	}
	
	@When("Edit product item number {string}")
	public void editProduct(String productNumber) {
		String xpathToEditProduct = "//a[@href='/edit/" + productNumber + "']";
		$(byXpath(xpathToEditProduct)).click();
	}
	
	@When("Edit price of product to {string}")
	public void udpatePrice(String newPrice){
		$("#price").setValue(newPrice);
		$x("//button[text()=\"Update\"]").click();
	}
	
	@When("Delete product item number {string}")
	public void deleteProduct(String productNumber){
		String xpathdeleteProduct = "//a[@href='/edit/" + productNumber + "']";
		$x(xpathdeleteProduct).click();
	}
}
