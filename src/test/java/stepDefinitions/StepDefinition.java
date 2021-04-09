package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/*Mailchimp*/

public class StepDefinition {

	private WebDriver driver;
	private LocalDateTime NowRightDate;
	private DateTimeFormatter formDate;
	private WebElement element;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://login.mailchimp.com/signup//");
	}

	@Given("I add my {string} to create an Mialchimp account")
	public void addEmail(String email) {
		
			sendKeysById("email", email);
		}
	

	@Given("I also add {string} to create my account")
	public void addUserName(String userName) {
		String manySigns = "";

		if(userName.contains("onehundredusername")) {
			
			for(int i = 0; i < 100; i++) {
				manySigns = manySigns.concat("b");
			}
			sendKeysById("new_username", createUsername() + manySigns);
			
		} else if (userName.contains("occupiedusername")) {
			  sendKeysById("new_username", "20210404160900@test.com");
			  
		} else
			sendKeysById("new_username", createUsername());
	}

	@Given("I enter password")
	public void i_enter_password() {
		
		sendKeysById("new_password","Password1!");
	}


	@When("I click on the Sign-up button")
	public void i_click_on_the_sign_up_button() {
		click(By.id("create-account"));
	}

	@Then("The account creation shold be {string}")
	public void the_account_creation_shold_be(String verification) {
		
		
			if (verification.equals("success")) {
				element = driver.findElement(By.cssSelector("div[class=signup-wrap]"));
				assertEquals("signup-wrap", element.getAttribute("class"));
				

			} else if (verification.equals("toManyCharacters")) {
				element = driver.findElement(By.cssSelector("span[class=invalid-error]"));
				assertEquals("Enter a value less than 100 characters long", element.getText());
				

			} else if (verification.equals("occupied")) {
				element = driver.findElement(By.cssSelector("span[class=invalid-error]"));
				assertEquals("Another user with this username already exists. "
						+ "Maybe it's your evil twin. Spooky.", element.getText());
				
			} else {
				element = driver.findElement(By.cssSelector("span[class=invalid-error]"));
				assertEquals("Please enter a value", element.getText());
			}

			driver.close();
	}
		
	

	private void click(By by) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
	}

	private void sendKeysById(String id, String keys) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
		driver.findElement(By.id(id)).sendKeys(keys);
	}

	private String createUsername() {
		// Collect current time
		NowRightDate = LocalDateTime.now();
		// Format the date to desired format
		formDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		String date = NowRightDate.format(formDate);
		return date;
	}

}
