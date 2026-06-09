package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Cart page locators
    By cartItemNames    = By.className("inventory_item_name");
    By cartItemPrices   = By.className("inventory_item_price");
    By removeButtons    = By.cssSelector(".cart_button");
    By checkoutButton   = By.id("checkout");
    By cartIcon         = By.className("shopping_cart_link");
    By continueShopping = By.id("continue-shopping");

    // Checkout Step 1 — Your Information
    By firstNameField   = By.id("first-name");
    By lastNameField    = By.id("last-name");
    By zipCodeField     = By.id("postal-code");
    By continueButton   = By.id("continue");

    // Checkout Step 2 — Overview
    By finishButton     = By.id("finish");

    // Checkout Step 3 — Complete
    By orderCompleteMsg = By.className("complete-header");
    
    
//    Methods
    
    // Method 1 — Navigate to cart page
    public void goToCart() {
        driver.findElement(cartIcon).click();
        System.out.println("Navigated to cart page!");
    }
    
    // Method 2 — Check if specific product is in cart
    public boolean isProductInCart(String productName) {

        List<WebElement> items = driver.findElements(cartItemNames);

        for (WebElement item : items) {
            String name = item.getText();
            System.out.println("Cart item found: " + name);

            if (name.equalsIgnoreCase(productName)) {
                System.out.println("Product found in cart: " + productName);
                return true;
            }
        }

        System.out.println("Product NOT found in cart: " + productName);
        return false;
    }
    
    
    // Method 3 — Get total items count in cart
    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItemNames);
        System.out.println("Total items in cart: " + items.size());
        return items.size();
    }
    
    
    // Method 4 — Remove specific product from cart
    public boolean removeProductFromCart(String productName) {

        // Find all cart item containers
        List<WebElement> cartItems = driver.findElements(
            By.className("cart_item")
        );

        for (WebElement cartItem : cartItems) {

            // Get name of this cart item
            String name = cartItem.findElement(
                By.className("inventory_item_name")
            ).getText();

            System.out.println("Checking cart item: " + name);

            // Does name match?
            if (name.equalsIgnoreCase(productName)) {

                // Click remove button inside this cart item
                cartItem.findElement(
                    By.cssSelector(".cart_button")
                ).click();

                System.out.println("Removed from cart: " + productName);
                return true;
            }
        }

        System.out.println("Product not found to remove: " + productName);
        return false;
    }

    // Method 5 — Click checkout button
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
        System.out.println("Clicked checkout button!");
    }

    // Method 6 — Fill checkout information
    public void fillCheckoutInfo(String firstName, 
                                  String lastName, 
                                  String zipCode) {

        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);

        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);

        driver.findElement(zipCodeField).clear();
        driver.findElement(zipCodeField).sendKeys(zipCode);

        System.out.println("Checkout info filled!");

        // Click continue to go to overview
        driver.findElement(continueButton).click();
        System.out.println("Clicked continue button!");
    }

    // Method 7 — Click finish to place order
    public void clickFinish() {
        driver.findElement(finishButton).click();
        System.out.println("Clicked finish button!");
    }

    // Method 8 — Get order complete message
    public String getOrderCompleteMessage() {
        WebElement msg = driver.findElement(orderCompleteMsg);
        String message = msg.getText();
        System.out.println("Order complete message: " + message);
        return message;
    }

    // Method 9 — Check if cart is empty
    public boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(cartItemNames);
        boolean isEmpty = items.size() == 0;
        System.out.println("Is cart empty: " + isEmpty);
        return isEmpty;
    }
}
    
    
    
 
