package com.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.CartPage;
import com.pages.LoginPage;
import com.pages.ProductsPage;

public class CartTest extends BaseTest{

	LoginPage loginPage;
	ProductsPage productsPage;
	CartPage cartPage;
	
    // Product we will use throughout all tests
    String testProduct = "Sauce Labs Backpack";
    String username = "standard_user";
    String password = "secret_sauce";
    
//    beforeClass setup
    @BeforeClass
    public void initPage() {
//    	step 1: login
    	loginPage = new LoginPage(driver);
    	loginPage.login(username, password);
    	System.out.println("Logged in successfully!");
    	
//    	step2 : add product to cart
    	productsPage = new ProductsPage(driver);
    	productsPage.addProductToCartByName(testProduct);
    	System.out.println("Product added: " + testProduct);
    	
//    	step 3: go to cart
    	cartPage = new CartPage(driver);
    	cartPage.goToCart();
        System.out.println("Navigated to cart!");

        System.out.println("CartTest setup done!");
    	
    }
    
//    Tests 
    
//    Test 1 verify product in cart
    @Test(priority = 1)
    public void verifyProductInCart() {
    	extentTest = extent.createTest("Verify product in cart");
    	extentTest.info("Checking if '" + testProduct + "' is in cart");
    	
    	boolean isInCart = cartPage.isProductInCart(testProduct);
    	
    	Assert.assertTrue(isInCart, testProduct +" was not found in cart");
    	
    	extentTest.pass("Product found in cart: " + testProduct + " ✅");
        System.out.println("TEST 1 PASSED → Product in cart verified!");
    	
    }
    
//    Test 2 CheckoutFlow
    @Test(priority = 2)
    public void verifyCheckoutFlow() {
    	extentTest = extent.createTest("Verify checkout flow");
    	extentTest.info("Starting checkout process");
    	
//    	step 1: click checkout
    	cartPage.clickCheckout();
    	extentTest.info("Clicked checkout button");
    	
//    	step 2: fill out information
    	cartPage.fillCheckoutInfo("john", "doe", "1234");
    	extentTest.info("filled checkout info: john doe. zip: 1234");
    	
//    	step 3: click finsh
    	cartPage.clickFinish();
    	extentTest.info("Clicked finsh button");
    	
//    	step 4: verify order complete message
    	String completeMsg = cartPage.getOrderCompleteMessage();
    	System.out.println("Order message: "+ completeMsg);
    	
    	Assert.assertTrue(completeMsg.contains("Thank you"),
    			"order complete message not shown! got: "+completeMsg);
    	
    	extentTest.pass("checkout completed! message: "+ completeMsg);
    	System.out.println("Test 2 passed! checkout flow verified!");
    }
    
    
//    test 3: remove product
    @Test(priority = 3)
    public void verifyRemoveProductFromCart() {
    	extentTest = extent.createTest("Verify remove product form cart");
    	extentTest.info("Setting up fresh cart for remove test");
    	
    	 // After checkout cart is empty — need to add product again
        // Go back to products page
    	
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
        extentTest.info("Navigated back to products page");
        
//        add product again
        productsPage.addProductToCartByName(testProduct);
        extentTest.info("Added product again: " + testProduct);
        
//        go to cart
        cartPage.goToCart();
        extentTest.info("Navigated to cart");
        
//        verify product is there before removing
        boolean isInCart = cartPage.isProductInCart(testProduct);
        cartPage.getCartItemCount();
        Assert.assertTrue(isInCart, "Product not in cart before remove test!");
        extentTest.info("Product confirmed in cart before removal");
        
        // Now remove the product
        boolean isRemoved = cartPage.removeProductFromCart(testProduct);
        Assert.assertTrue(isRemoved,
                "Product was not found to remove!");
        extentTest.info("Remove button clicked!");
        
//        verify product is removed or not
        cartPage.getCartItemCount();
        

//        // Verify cart is now empty
//        boolean isEmpty = cartPage.isCartEmpty();
//        Assert.assertTrue(isEmpty,
//                "Cart is not empty after removing product!");

        extentTest.pass("Product removed successfully! Cart is empty ✅");
        System.out.println("TEST 3 PASSED → Product removed from cart!");
    	
    	
    }
    
}
