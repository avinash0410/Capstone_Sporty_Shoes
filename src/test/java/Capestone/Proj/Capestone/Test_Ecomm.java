package Capestone.Proj.Capestone;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test_Ecomm {

	public WebDriver driver;
	@Test
	public void visit_site() {

		driver = new ChromeDriver();
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	@Test
	public void login() {

		driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("flowk00@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("Flowk07@");
		driver.findElement(By.xpath("//*[@id=\"send2\"]/span")).click();
	}
	@Test
	public void Search() {

		WebElement e = driver.findElement(By.xpath("//*[@id=\"search\"]"));
		e.sendKeys("Shirts for men");
		e.sendKeys(Keys.ENTER);

	}
	@Test
	public void select__First_Product()

	{
		driver.findElement(
				By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/div[2]/ol/li[4]/div/a/span/span/img")).click();
		driver.findElement(By.xpath("//*[@id=\"option-label-size-143-item-168\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"option-label-color-93-item-59\"]")).click();
		// Add to cart
		driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]/span")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().back();
	}
	@Test
	public void select_second_product() {

		driver.findElement(
				By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/div[2]/ol/li[3]/div/a/span/span/img")).click();
		driver.findElement(By.xpath("//*[@id=\"option-label-size-143-item-168\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"option-label-color-93-item-56\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"option-label-color-93-item-56\"]")).click();

	}
	@Test
	public void navigateToMyprofile() {

		driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).click();
		driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[1]/a")).click();

	}
	@Test
	public void GotoCart_checkout() {
		driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"top-cart-btn-checkout\"]")).click();
	}
	@Test
	public void fill_address_details() {

		driver.findElement(By.xpath("//*[@id=\"L5TE8LA\"]")).sendKeys("78,9th avenue,sector IV");
		driver.findElement(By.xpath("//*[@id=\"BGCX75G\"]")).sendKeys("Bangalore");
		driver.findElement(By.xpath("//*[@id=\"BGCX75G\"]")).sendKeys("Bangalore");
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"P1Q0PK8\"]"));
		ele.click();
		ele.sendKeys(Keys.DOWN);
		ele.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"WGN69UF\"]")).sendKeys("700506");
		driver.findElement(By.xpath("//*[@id=\"YKBD1I1\"]")).sendKeys("+19282899289");
		driver.findElement(By.xpath("//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[1]/td[1]/input"))
				.click();
		driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button/span")).click();
	}
	@Test
	public void PlaceOrder() {
		driver.findElement(
				By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button/span"))
				.click();
	}
	public void get_req() {
			//for products
			Response res = RestAssured.given().contentType(ContentType.JSON).baseUri("https://fakestoreapi.com/products")
					.when().get().then()
					.assertThat().log().body()
					.statusCode(200)
					.header("Content-Type", "application/json; charset=utf-8").extract().response();
			System.out.println("Status : " + res.getStatusLine());
			//for users
			Response res2 = RestAssured.given().contentType(ContentType.JSON).baseUri("https://fake-store-api.mock.beeceptor.com/api/users")
					.when().get().then()
					.assertThat().log().body()
					.statusCode(200)
					.header("Content-Type", "application/json; charset=utf-8").extract().response();
			System.out.println("Status : " + res2.getStatusLine());
	}
}
