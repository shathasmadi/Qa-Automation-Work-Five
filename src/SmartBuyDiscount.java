import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SmartBuyDiscount {
	public WebDriver driver;
	SoftAssert softAssert = new SoftAssert();

	@BeforeTest
	public void Login() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://smartbuy-me.com/smartbuystore/");
		driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();
		driver.manage().window().maximize();
	}

	@Test
	public void Descount() {
		String Before_discount = driver
				.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[2]"))
				.getText();
		String[] the_update_all = Before_discount.split("JOD");
		String The_Before_All_item_prise = the_update_all[0].trim();
		Double Before_price = Double.parseDouble(The_Before_All_item_prise);

		String discount = driver
				.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[1]"))
				.getText();
		String updated = discount.replace("%", "").trim();
		Double discount_price = Double.parseDouble(updated);

		String origin_number = driver
				.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[3]"))
				.getText();
		String[] origin_number_split = origin_number.split("JOD");
		String origin_number_trim = origin_number_split[0].trim();
		Double origin_parse = Double.parseDouble(origin_number_trim);
		System.out.println(origin_parse);

		double discounts = discount_price * Before_price / 100;
		double Expected = (Before_price) - (discounts);
		double Acual = origin_parse;

		softAssert.assertEquals(Acual, Expected);

	}
}
