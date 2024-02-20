package org.Atyati.YBL.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginLooping {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://10.0.0.5/internal_test/Base_App/Main_Navigation/GanasevaLogin.aspx");
		driver.get("https://10.0.0.5/internal_test/Base_App/Main_Navigation/GanasevaLogin.aspx");
		driver.findElement(By.xpath("//button[@id=\"details-button\"]")).click();
		driver.findElement(By.linkText("Proceed to 10.0.0.5 (unsafe)")).click();
		FileInputStream file = null;
		Workbook workbook = null;
		Sheet sheet = null;
		try {
			file = new FileInputStream(new File("LoginLooping.xlsx"));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheetAt(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
		int i = 0;
		int row = 0;
		Row r1 = sheet.getRow(row);
		while ((r1.getCell(i)) != null && (r1.getCell(i + 1)) != null) {
		
			driver.findElement(By.xpath("//input[@name=\"txtLogin\"]")).sendKeys(r1.getCell(i).getStringCellValue());
			driver.findElement(By.xpath("//input[@name=\"txtPassword\"]"))
					.sendKeys(r1.getCell(i + 1).getStringCellValue());
			driver.findElement(By.xpath("//input[@id=\"txtCatpcha\"]")).sendKeys("testing");
			driver.findElement(By.xpath("//input[@name=\"ImgBtnLogin\"]")).click();
			if (driver.getTitle().equalsIgnoreCase("MainNavigation")) {
				System.out.println("Loged in successfully " + row +"times");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.switchTo().frame("frame1");
				driver.findElement(By.linkText("LogOut")).click();
			} else {
				System.out.println("Invalid username or pasword");
				driver.findElement(By.xpath("//input[@name=\"txtLogin\"]")).clear();
			}
			row++;
			r1 = sheet.getRow(row);
			if(r1==null) {
				break;
			}
		
			
		}
	}
}
