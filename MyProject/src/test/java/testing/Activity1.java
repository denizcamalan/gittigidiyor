package testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class Activity1 {

		public static void main(String[] args) {
			
			// www.gittigidiyor.com sitesi açılır.
				
				WebDriver driver = new ChromeDriver();
				driver.manage().window().setSize(new Dimension(1440, 875));
				System.setProperty("webdriver.chrome.driver", "/Users/*/chromedriver");
				driver.get("https://www.gittigidiyor.com");
				
			// Ana sayfanın açıldığı kontrol edilir.
				
				String expected_title = "GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi";
				String actual_title1 = driver.getTitle();
				
				if (expected_title.equals(actual_title1)) {
					System.out.println("Opened Site Succesful");
				}else {
					System.out.println("Failed");
				}
			
			//  Siteye login olunur.
				
				driver.get("https://www.gittigidiyor.com/uye-girisi");
				driver.findElement(By.id("L-UserNameField")).sendKeys("e-mail");
				driver.findElement(By.id("L-PasswordField")).sendKeys("password");
				driver.findElement(By.id("gg-login-enter")).click();
				
			// Login işlemi kontrol edilir.
					
				if (expected_title.equals(actual_title1)) {
					System.out.println("Login Succesfull");
				}else {
					System.out.println("Login Failled");
				} 
			
			// Arama kutucuğuna bilgisayar kelimesi girilir. 
			
			    WebElement searchbtn = driver.findElement(By.cssSelector(".psxmjs-2"));
			    Actions builder = new Actions(driver);
			    builder.moveToElement(searchbtn).perform();
			    driver.findElement(By.name("k")).click();
			    driver.findElement(By.name("k")).sendKeys("bilgisayar");
			    driver.findElement(By.name("k")).sendKeys(Keys.ENTER);
			    
			// Arama sonuçları sayfasından 2.sayfa açılır. 

			    JavascriptExecutor js = (JavascriptExecutor) driver; 
			    WebElement tab2 = driver.findElement(By.xpath("//div[@id='best-match-right']/div[5]/ul/li[2]/a"));
			    js.executeScript("arguments[0].click();", tab2);
				String actual_title3 = driver.getTitle();
				
			// 2.sayfanın açıldığı kontrol edilir.
			    
			    String expected_title2 = "Bilgisayar - GittiGidiyor - 2/100";
			    
			    if (expected_title2.equals(actual_title3)) {
					System.out.println("Opened 2 Succesful");
				}else {
					System.out.println("Opened 2 Failled");
				} 
			    
			 // Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
			    
			   WebElement randomProd = driver.findElement(By.xpath("//div[@id='best-match-right']/div[3]/div[2]/ul"));
			   List<WebElement> l1 = randomProd.findElements(By.tagName("a"));
			   Random r = new Random();
			   l1.get(r.nextInt(l1.size())).click();

			 // Seçilen ürün sepete eklenir.
			   
			   WebElement addBasket = driver.findElement(By.xpath("//div[@id='sp-addbasket-button']/form/button"));
			   js.executeScript("arguments[0].click();", addBasket);
			   WebDriverWait wait = new WebDriverWait(driver, 100);
			   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='header_wrapper']/div[4]/div[3]/div/div/div/div[2]/div[4]/div/a"))).click();
			   WebElement price = driver.findElement(By.xpath("//div[@id='cart-price-container']/div[3]/p"));
			   String priceText= price.getText(); 
			   
			 // Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.
			   
			   WebElement basketPrice = driver.findElement(By.xpath("//div[@id='cart-price-container']/div[3]/p"));
		       String actual_price = basketPrice.getText();

		       if(actual_price.equals(priceText)){
		       	System.out.println("Price Equal");
		       }else 
		       System.out.println("Price Failed");
		      
		      // Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.

		       driver.findElement(By.cssSelector(".gg-input-select > .amount")).sendKeys("2");
		       
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[. = '2 Adet']")));
			   WebElement twoPrice = driver.findElement(By.xpath("//p[contains(. ,'2 Adet')]"));
		       String tot_price = twoPrice.getText();
		       System.out.println( tot_price );

		       if (tot_price.equals("2 Adet")) {
					System.out.println("2 Product Succesful");
		       }else {
					System.out.println("2 Product Failled");
				} 

			  // Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.
		       
			    WebElement clearBasket = driver.findElement(By.cssSelector(".row > .btn-delete > .gg-icon"));
			    clearBasket.click();
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),('Sepetinizde ürün bulunmamaktadır.'))]")));
			    WebElement checkbasket = driver.findElement(By.xpath("//h2[contains(text(),('Sepetinizde ürün bulunmamaktadır.'))]"));
			    String check_prod = checkbasket.getText();
			    String actual_prod = "Sepetinizde ürün bulunmamaktadır.";
		       
		        if(actual_prod.equals(check_prod)){
				       System.out.println("Basket Clear Succesful");
		        }else
				       System.out.println("Basket Clear Failled");				 
		}
	
}
