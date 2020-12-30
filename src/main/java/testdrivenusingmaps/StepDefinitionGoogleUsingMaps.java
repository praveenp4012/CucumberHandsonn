package testdrivenusingmaps;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.KeyDownAction;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class StepDefinitionGoogleUsingMaps {

	public WebDriver driver = null;

	@Given("^URL of the Website$")
	public void url_of_the_Website() {
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.google.co.in/");
	}

	// Test data from data table
	@When("^Some text is entered in the search box$")
	public void some_text_is_entered_in_the_search_box(DataTable data) {
		for (Map<String, String> input : data.asMaps(String.class, String.class)) {

			driver.findElement(By.xpath("//input[@title='Search']")).clear();
			driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(input.get("text"));
			driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.ENTER);
			// driver.findElement(By.xpath("//input[@value='Google Search']")).click();
			//driver.navigate().back();
		}
	}

	@Then("^Home page should be opened$")
	public void home_page_should_be_opened() {
		String text = driver.findElement(By.xpath("//h2[@class='JolIg mfMhoc']")).getText();

		Assert.assertEquals("People also ask", text);
	}

}
