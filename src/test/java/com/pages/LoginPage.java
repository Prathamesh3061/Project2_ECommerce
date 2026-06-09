package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver driver;
	
//	constructor
//	we pass the driver so this class can use it
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
//	Locators 
//	store locators as variables
	By usernameField = By.id("user-name");
	By passwordField = By.id("password");
	By loginButton = By.id("login-button");
	By errorMessage = By.cssSelector(".error-message-container");
	
	
//	methods
	
//	method for login
	public void login(String username, String password) {
		driver.findElement(usernameField).clear();
		driver.findElement(usernameField).sendKeys(username);
		driver.findElement(passwordField).clear();
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();
	}
	
	// this method read the error message showm on failed login
	public String getErrorMessage() {
		WebElement error = driver.findElement(errorMessage);
		return error.getText(); // return the text as string
	}
	
}
