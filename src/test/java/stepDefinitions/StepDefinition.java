package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
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
		    
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		
		

			/*@Given("I like to use this {string} browser")
			public void i_like_to_use_this(String browser) {
				
				DriveCreator creator = new DriveCreator();
			    
			    driver = creator.createBrowser(browser);
			}*/



	
		/*Creating random e-mail address*/
		@Given("I add my {string} to create an Mialchimp account")
		public void i_add_my_want_to_create_an_mialchimp_account(String email) {
						
			if (email.contains("email4")) {
				System.out.println("Nu är jag i mailmetoden");
				
				driver.findElement(By.id("email")).sendKeys(" ");
				
			}else {
				String userEmail = (createUsername() + "@test.com");
				driver.findElement(By.id("email")).sendKeys(userEmail);
			}
		}

		//Using same user-id as in the e-mail address 
		@Given("I also add {string} to create my account")
		public void i_also_add_to_create_my_account(String userName) {			
			
			if (userName.contains("userid1")) {
					driver.findElement(By.id("new_username")).sendKeys(createUsername());
					System.out.println("Skapar userid1, som är unikt");
					
			}else if (userName.contains("userid2")) {
					System.out.println("Skapar userid2, som innehåller hundra tecken");
					driver.findElement(By.id("new_username")).sendKeys(createUsername() +
					"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa123");
			}else if (userName.contains("userid3")) {
				System.out.println("Skapar userid3, ett username som redan finns");
				driver.findElement(By.id("new_username")).sendKeys("20210404160900@test.com");
			
			}else if (userName.contains("userid4")){
				System.out.println("Skapar userid4, ingen e-mail");
				driver.findElement(By.id("new_username")).sendKeys(createUsername());
			}
		}
		
		@Given("I enter password {string}")
		public void i_enter_password(String userName) {
			
			String password = (createUsername() + "beA@");
			driver.findElement(By.id("new_password")).sendKeys(password);
		}

		@When("I click on the Sign-up button")
		public void i_click_on_the_sign_up_button() {
			
			WebElement signUpButton = driver.findElement(By.id("create-account"));
			signUpButton.click();
		}
				
		@Then("The account creation should be {string} for user {string}")
		public void the_account_creation_should_be_for_user(String verification, String userName) {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			
			try {
					if(userName.contains("userid1")) {
						System.out.println("userid1");
						WebElement element = driver.findElement(By.id("login"));
						assertEquals(verification, element.getAttribute("id"));
						System.out.println("Your selected username is OK");
						
					}else if (userName.contains("userid2")) {
						System.out.println("userid2");
						WebElement element = driver.findElement(By.cssSelector("class[invalid-error$='100 characters long']"));
						assertEquals(verification, element.getAttribute("class"));
						System.out.println("Too many characters, use less then 100 characters");
						
					}else if (userName.contains("userid3"))	{
						System.out.println("userid3");
						WebElement element = driver.findElement(By.cssSelector("class[invalid-error$ = 'Spooky.']"));
						assertEquals(verification, element.getAttribute("class"));
						System.out.println("The username is already taken");
					
					}else {
						System.out.println("userid4");
						WebElement element = driver.findElement(By.cssSelector("class[invalid-error = 'Please enter a value']"));
						assertEquals(verification, element.getAttribute("id"));
						System.out.println("No e-mail");
					}
					
				}catch (Exception e) {
					System.out.println("You have type in wrong username or forgot e-mail");
				}
			}
				
		    
			//driver.close();
	
		
		private void click(WebDriver driver, By by) {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
					driver.findElement(by).click();
		}


		public static void sendKeys(WebDriver driver, By by, String keys) {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
				driver.findElement(by).sendKeys(keys);				
		}
		
			
		private String createUsername() {
			//Collect current time
			NowRightDate = LocalDateTime.now();  
		    //Format the date to desired format   
		    formDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
		    
		    String date = NowRightDate.format(formDate);  
		   		return date;
		}
		
					
		/*private WebDriver createBrowser(String browser) {
			
			WebDriver driver;
			
			if(browser.equals("chrome")) {
			
				System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}else if(browser.equals("firefox")) {
		    
				System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Selenium\\geckodriver.exe");
				driver = new FirefoxDriver();
			}else {
				System.setProperty("webdriver.edge.driver", "C:\\Program Files\\Selenium\\msedgedriver.exe");
				driver = new EdgeDriver();			
			}
			
		    return driver; 
		}*/
	
	
}









