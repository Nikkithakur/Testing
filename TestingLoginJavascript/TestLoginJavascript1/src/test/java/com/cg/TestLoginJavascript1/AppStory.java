package com.cg.TestLoginJavascript1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AppStory {
	
	WebDriver driver;
	Alert alert;
	
	@Given("^Logging in with username and password$")
	public void logging_in_with_username_and_password() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\Users\\tnikhilp\\Desktop\\selenium\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("TestLoginJavascript1\\TestLoginPages\\LoginPage.html");
		
		
	    
	}

	@When("^Enter username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void enter_username_as_and_password_as(String arg1, String arg2) throws Throwable {
	    
		
		WebElement login=driver.findElement(By.id("login_field"));
		login.sendKeys(arg1);
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(arg2);
		WebElement submit=driver.findElement(By.name("submit"));
		submit.click();
	 
	}

	@Then("^Display success page$")
	public void display_success_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	 
	}

	@When("^Enter wrong username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void enter_wrong_username_as_and_password_as(String arg1, String arg2) throws Throwable {
		WebElement login=driver.findElement(By.id("login_field"));
		login.sendKeys(arg1);
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(arg2);
		WebElement submit=driver.findElement(By.name("submit"));
		submit.click();
		
	 
	}

	@Test
	@Then("^Display Alert box as invalid email$")
	public void display_Alert_box_as_invalid_email() throws Throwable {
		alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		assertEquals("Invalid email",alert.getText());
	   
	}

	@When("^Enter username as \"([^\"]*)\" and wrong password as \"([^\"]*)\"$")
	public void enter_username_as_and_wrong_password_as(String arg1, String arg2) throws Throwable {
		WebElement login=driver.findElement(By.id("login_field"));
		login.sendKeys(arg1);
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(arg2);
		WebElement submit=driver.findElement(By.name("submit"));
		submit.click();
	    
	}

	@Test
	@Then("^Display Alert box as invalid password$")
	public void display_Alert_box_as_invalid_password() throws Throwable {
		alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		assertEquals("Invalid password",alert.getText());
	   
	}

}
