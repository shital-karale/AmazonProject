package test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//port org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Select;

public class AmazonSearchProduct {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement SearchProduct = driver.findElement(By.id("twotabsearchtextbox"));
		SearchProduct.sendKeys("Samsung");
		WebElement search = driver.findElement(By.id("nav-search-submit-button"));
		search.click();

		// List all samsung mobile product
		List<WebElement> productlist = driver.findElements(By.xpath("//div[@class='a-section']//span[starts-with(text(),'Samsung ')]"));
		List<WebElement> pricelist = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		System.out.println("Total products found" +productlist.size());
		
		for (int i=0;i<productlist.size();i++)
		{
			System.out.println(productlist.get(i).getText() + "  " + pricelist.get(i).getText());

		}

		// sort option by:Featured,price high to low, price low to high......
		WebElement SortBy = driver.findElement(By.className("a-dropdown-prompt"));
		SortBy.click();
		String option = "Featured";
		WebElement selectoption = driver.findElement(By.xpath("//a[contains(text(),'" + option + "')]"));
		selectoption.click();

		// to select product
		WebElement selectProduct = driver.findElement(By.xpath("//span[contains(text(),'Samsung Galaxy M53 5G (Mystique Green, 6GB, 128GB Storage)')]"));
		selectProduct.click();

		Set<String> windowside = driver.getWindowHandles();
		Iterator<String> itr = windowside.iterator();
		String mainwindow = itr.next();
		String childwindow = itr.next();
		driver.switchTo().window(childwindow);

		// To add protection plan
		WebElement protectionPlan = driver.findElement(By.xpath("//input[@id='mbb-offeringID-1']"));
		protectionPlan.click();

		// Select Quantity
		WebElement Quantity = driver.findElement(By.xpath("//select[@id='quantity']"));
		Select NumQuantity = new Select(Quantity);
		NumQuantity.selectByIndex(1);

		// To add in the card
		WebElement AddToCard = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		AddToCard.click();

		// To buy
		WebElement BuyProduct = driver.findElement(By.xpath("//input[@id='buy-now-button']"));
		BuyProduct.click();

		// To create Amazon account
		WebElement CreateAc = driver.findElement(By.xpath("//a[@id='createAccountSubmit']"));
		CreateAc.click();

		WebElement FirstName = driver.findElement(By.xpath("//input[@id='ap_customer_name']"));
		FirstName.sendKeys("abcd");
		WebElement PhoneNum = driver.findElement(By.xpath("//input[@id='ap_phone_number']"));
		PhoneNum.sendKeys("123456789");
		WebElement EmailId = driver.findElement(By.xpath("//input[@id='ap_email']"));
		EmailId.sendKeys("abcd123@abc.com");
		WebElement Password = driver.findElement(By.xpath("//input[@id='ap_password']"));
		Password.sendKeys("abc3@a");
		WebElement Continue = driver.findElement(By.xpath("//input[@id='continue']"));
		Continue.click();

		String Error = driver.findElement(By.xpath("//span[@class='a-list-item']")).getText();
		System.out.println(Error);
		
		driver.quit();
	}

}
