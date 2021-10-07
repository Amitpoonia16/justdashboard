package smoke;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Add_customer {
	WebDriver driver;
	String user_name="Amit Poonia";
	String user_mobilenumber="1021212133";
	
	@BeforeTest
	public void openbrowser()
	{
		
		System.setProperty("webdriver.chrome.driver", "D:\\Amit Data\\chromedriver.exe");  
		driver = new ChromeDriver();
		System.out.println("Test");
		driver.manage().window().maximize();
	}
	@BeforeClass
	public void login() throws InterruptedException
	{
		driver.get("https://dev.justapi.in/admin/home");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/form[1]/div[1]/input[1]")).sendKeys("7000000000");
		driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/form[1]/div[2]/input[1]")).sendKeys("just1234");
		driver.findElement(By.id("submit_btn")).click();
	}
	@Test
	public void addCustomer() throws InterruptedException
	{
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class=\"nav-item has-treeview\"][2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@href=\"https://dev.justapi.in/admin/customer/add_customer/\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("customer_name")).sendKeys(user_name);
		driver.findElement(By.id("customer_mobile")).sendKeys(user_mobilenumber);
		driver.findElement(By.id("save-customers")).click();
		
		String title="View Customer | JUST : Admin Panel";
		
		Thread.sleep(2000);
		String exp_title=driver.getTitle();
		boolean title_status=title.equalsIgnoreCase(exp_title);
		
		if(title_status==true)
				{
			System.out.println("******************************************");
			System.out.println("New user is created successfully");
			System.out.println("Customer name is: " +user_name);
			System.out.println("Customer Mobile number is: " +user_mobilenumber);
			System.out.println("******************************************");
				}
		
	}
	@Test
	public void createOrder() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@href='https://dev.justapi.in/admin/orders/']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='https://dev.justapi.in/admin/orders/add-order']")).click();
		Thread.sleep(2000);
		WebElement autoSuggest_user= driver.findElement(By.id("customer_search"));
		autoSuggest_user.sendKeys("707334815");
		Thread.sleep(2000);
		autoSuggest_user.sendKeys(Keys.ARROW_DOWN);
		autoSuggest_user.sendKeys(Keys.ENTER);
		
		
	        Thread.sleep(2000);
	      	
	    WebElement   autoSuggest_product=  driver.findElement(By.id("product_search"));
	    autoSuggest_product.sendKeys("Mango");
	    Thread.sleep(2000);
	    autoSuggest_product.sendKeys(Keys.ARROW_DOWN);
	    autoSuggest_product.sendKeys(Keys.ARROW_DOWN);
	    autoSuggest_product.sendKeys(Keys.ENTER);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.findElement(By.id("submit_btn")).click();
        Thread.sleep(2000);
        
        String order_success_url="https://dev.justapi.in/admin/orders/";
        Thread.sleep(2000);
        String order_success=driver.getCurrentUrl();
        System.out.println(order_success);
        if(order_success.equalsIgnoreCase(order_success_url))
        {
        	System.out.println("Order created successfully");
        	String order_number=driver.findElement(By.xpath("//*[@class=\"table-striped table table-bordered table-hover\"]/tbody/tr/td[2]")).getText();
        	 System.out.println(order_number);
        }
        else
        {
        	System.out.println("Order is failed");
        }
	}
	
	@Test
	public void uploadFile() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class=\"nav nav-pills nav-sidebar flex-column nav-child-indent nav-flat\"]/li[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@href=\"https://dev.justapi.in/admin/product/bulk-upload\"]")).click();
		Thread.sleep(2000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    Thread.sleep(2000);
	   WebElement file= driver.findElement(By.xpath("//*[@name=\"upload_file\"]"));
	   file.sendKeys("D:\\Amit Data\\Inventory_List (100).xlsx");
	   driver.findElement(By.id("submit_btn")).click();
	   Thread.sleep(500);
		/*
		 * boolean
		 * status=driver.findElement(By.xpath("//*[@class=\"p-3 msg_success\"]")).
		 * isDisplayed();
		 * 
		 * System.out.println(status);
		 */
	}
	@AfterTest
	public void closeBrowser()
	{
		driver.close();
	}

}
