package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage {

	
//	Webdriver
	WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		}
	
//	Locators
	By productCards = By.className("inventory_item");
	By productNames = By.className("inventory_item_name");
	By sortDropdown = By.className("product_sort_container");
	By addToCartbtns = By.cssSelector(".btn_inventory");
	By cartBadge = By.className("shopping_cart_badge");
	By productPrices = By.className("inventory_item_price");
	
// methods
	
//	1.check if product page loaded
	public boolean isPrdouctPageLoaded() {
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current url: "+currentUrl);
		return currentUrl.contains("inventory.html");
	}
	
//	2.get Total product counts
	public int getProductCount() {
		List<WebElement> products = driver.findElements(productNames);
		System.out.println("Total products found: "+ products.size());
		return products.size();
	}
	
	// 3.sort products by given option
	public void sortProducts(String sortOption) {
//		select class handles dropdowns
		Select select = new Select(driver.findElement(sortDropdown));
		select.selectByVisibleText(sortOption);
		System.out.println("Sorted By: "+sortOption);
	}
	
//	4.getFirst product price after sorting
	public double getFirstProductPrice() {
		List<WebElement> prices = driver.findElements(productPrices);
//		price.get(0) = frist price element
//		getText() returns 7.99
		String priceText = prices.get(0).getText().replace("$","");
		System.out.println("First product price: " + priceText);
		return Double.parseDouble(priceText);
	}
	
//	5.add first prodouct to cart
	public void addFirstProductToCart() {
		List<WebElement> buttons = driver.findElements(addToCartbtns);
		buttons.get(0).click();
		System.out.println("First product added to cart");
	}
	
	
//	6.get cart count from badge
	public int getCartCount() {
		try {
			WebElement badge = driver.findElement(cartBadge);
			int count = Integer.parseInt(badge.getText());
			System.out.println("Cart count: "+ count);
			return count;
		}catch(Exception e){
			// if badge not visible cart is empty
			System.out.println("cart is empty");
			return 0;
		}
	}
	
	// 7.get first product name
	public String getFirstProductName() {
		List<WebElement> products = driver.findElements(productNames);
		String name = products.get(0).getText();
		System.out.println("First product name: "+name);
		return name;
	}
	
//	8.add products whatever you want
	public void addProductToCartByName(String productName) {
		List<WebElement> cards = driver.findElements(productCards);
		
		for(WebElement card : cards) {
			String name = card.findElement(By.className("inventory_item_name")).getText();
			
			if(name.equalsIgnoreCase(productName)) {
				card.findElement(By.cssSelector(".btn_inventory")).click();
				System.out.println("added to cart: "+ name);
				return;
			}
		}
		throw new RuntimeException("Prdouct not found: "+productName);
	}
	
}
