package practise;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PractiseTest {
	
	public static void main(String[] args) {
		
		// declare chromeDriver
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		String baseUrl = "https://alza.cz/EN/";
		
		driver.get(baseUrl);
		
		// click on category
		driver.findElement(By.xpath("//a[@title='Laptops']")).click();
		
		/* sort by most expensive
		 * wait for loader to disappear
		 */
		driver.findElement(By.id("ui-id-4")).click();
		PractiseTest.waitForLoader(driver, 5000);
		
		// add first product to cart
		driver.findElement(By.xpath("//a[@class='btnk1']")).click();
			
		// wait for back button
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='arrow left']")));
		
		// navigate back to list
		driver.findElement(By.xpath("//span[@class='arrow left']")).click();
		
		/* sort by most expensive
		 * wait for loader to disappear
		 */
		driver.findElement(By.id("ui-id-4")).click();
		PractiseTest.waitForLoader(driver, 5000);
		
		// add second product to cart
		driver.findElement(By.xpath("(//a[@class='btnk1'])[2]")).click();
		
		// proceed to checkout
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='arrow right']")));
		driver.findElement(By.xpath("//span[@class='arrow right']")).click();
		
		// assert both exists in cart
		WebElement firstProduct = driver.findElement(By.xpath("//a[contains(text(), 'Laptop Lenovo ThinkPad P73')]"));
		WebElement secondProduct = driver.findElement(By.xpath("//a[contains(text(), 'Laptop Acer ConceptD 9 Pro Black All-metal')]"));
		
		if (firstProduct.isDisplayed() && secondProduct.isDisplayed()) {
			System.out.println("Test Passed");
		}
		
		else {
			System.out.println("Test Failed");
		}
		
		// close chrome
		driver.close();
		
	}
	
	public static void waitForLoader(WebDriver driver, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		WebElement loader = driver.findElement(By.xpath("//span[@class='circle-loader-container']"));
		
		wait.until(ExpectedConditions.invisibilityOf(loader));
		
		}

}