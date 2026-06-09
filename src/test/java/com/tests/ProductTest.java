package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.LoginPage;
import com.pages.ProductsPage;

public class ProductTest extends BaseTest {

//	page objects 
	LoginPage loginPage;
	ProductsPage productsPage;
	
	
	@BeforeClass
	public void initPage() {
		loginPage = new LoginPage(driver);
		loginPage.login("standard_user","secret_sauce");
		System.out.println("Logged in successfully!");
		
//		Now create product page object
		productsPage = new ProductsPage(driver);
        System.out.println("ProductTest setup done!");
	}
	
//	1.page loaded
	@Test(priority = 1)
	public void verifyProductPageLoaded() {
		extentTest = extent.createTest("Verify products page loaded");
		extentTest.info("Checking if products page is loaded after login");
		
		boolean isLoaded = productsPage.isPrdouctPageLoaded();
		
		Assert.assertTrue(isLoaded, "products page did not load after Login!");
		
		extentTest.pass("Products page loaded successfully! ✅");
        System.out.println("TEST 1 PASSED → Products page loaded!");
	}
	
//	2.products count
	@Test(priority = 2)
	public void verifyProductCount() {
		extentTest = extent.createTest("Verify Product Count");
        extentTest.info("Checking total number of products displayed");
        
        int count = productsPage.getProductCount();
        
        Assert.assertEquals(count,6, "expected 6 products but found: "+ count);
        
        extentTest.pass("Product count verified! Found: " + count + " products");
        System.out.println("TEST 2 PASSED → Product count: " + count);
	}
	
//	3.sort products
	@Test(priority = 3)
	public void verifySortByPriceLowToHigh() {
		extentTest = extent.createTest("Verify sort by price low to high");
		extentTest.info("Sorting products by price low to high");
		
		productsPage.sortProducts("Price (low to high)");
		extentTest.info("Sort option selected: Price (low to high)");
		
		double firstPrice = productsPage.getFirstProductPrice();
        extentTest.info("First product price after sorting: $" + firstPrice);

        // Cheapest product on SauceDemo is $7.99
        Assert.assertEquals(firstPrice, 7.99,
                "Expected cheapest price $7.99 but got: $" + firstPrice);

        extentTest.pass("Sort verified! Cheapest product price: $" 
                        + firstPrice + " ✅");
        System.out.println("TEST 3 PASSED → First price after sort: $" 
                           + firstPrice);
		
	}
	
//	4.add to cart
	@Test(priority = 4)
	public void verifyAddToCart() {
		extentTest = extent.createTest("Verify add to cart");
		extentTest.info("adding first product to cart");
		
		String productName = productsPage.getFirstProductName();
		extentTest.info("adding product: "+productName);
		
		productsPage.addFirstProductToCart();
		extentTest.info("Clicked add to cart button");
		
		int cartCount = productsPage.getCartCount();
		
		Assert.assertEquals(cartCount, 1,
				"Expected cart count 1 but got: "+ cartCount);
		
		 extentTest.pass("Add to Cart verified! Cart count: " 
                 + cartCount + " ✅ | Product: " + productName);
		 System.out.println("TEST 4 PASSED → Cart count: " + cartCount);
	}
	
//	5.add to cart by product name
	@Test(priority = 5)
	public void addSpecificProductToCart() {
	    extentTest = extent.createTest("Verify Add Specific Product To Cart");
	    extentTest.info("Adding specific product: Sauce Labs Backpack");
	    
	    // Refresh page first to reset cart state
//	    driver.navigate().to("https://www.saucedemo.com/inventory.html");
	    
	    productsPage.addProductToCartByName("Sauce Labs Backpack");
	    
	    // Verify cart count
	    int cartCount = productsPage.getCartCount();
	    Assert.assertTrue(cartCount > 0, 
	            "Cart is empty after adding product!");

	    extentTest.pass("Specific product added successfully!Cart count: " 
	                    + cartCount);
	}
}
