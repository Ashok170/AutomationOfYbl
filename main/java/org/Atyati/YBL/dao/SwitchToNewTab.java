package org.Atyati.YBL.dao;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SwitchToNewTab {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		System.setProperty("webdriver.edge.driver", "./Drivers/msedgedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://10.0.0.5/internal_test/Base_App/Main_Navigation/GanasevaLogin.aspx");
		driver.findElement(By.xpath("//button[@id=\"details-button\"]")).click();
		driver.findElement(By.linkText("Proceed to 10.0.0.5 (unsafe)")).click();
		driver.findElement(By.xpath("//input[@name=\"txtLogin\"]")).sendKeys("rakesh");
		driver.findElement(By.xpath("//input[@name=\"txtPassword\"]")).sendKeys("Atyati@123");
		driver.findElement(By.xpath("//input[@id=\"txtCatpcha\"]")).sendKeys("testing");
		driver.findElement(By.xpath("//input[@name=\"ImgBtnLogin\"]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame("frame1");
		WebElement hoverElement = driver.findElement(By.linkText("Admin"));

		Actions actions = new Actions(driver);
		actions.doubleClick(hoverElement).perform();

		WebElement userAdminBtn = driver.findElement(By.linkText("User Admin"));
		actions.moveToElement(userAdminBtn).perform();

		WebElement UserDetailsbtn = driver.findElement(By.linkText("User Details"));
		actions.doubleClick(UserDetailsbtn).perform();
		driver.switchTo().parentFrame();

		driver.switchTo().frame("main");

		driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$btnSearchLoan\"]")).click();
		Set<String> windowHandles = driver.getWindowHandles();

		// Switch to the new tab
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		driver.findElement(By.xpath("//input[@name=\"txtUsrCode\"]")).sendKeys("1022");
		driver.findElement(By.xpath("//input[@name=\"btnSearch\"]")).click();
	}
}
