
package org.Atyati.YBL.dao;

	import java.util.Date;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.text.DateFormat;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.concurrent.TimeUnit;

	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.Select;
	import org.Atyati.YBL.AgeCalculator;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	public class YblAutomationSecond extends Thread{
		
//		public static void main(String[] args) {
		public void run() {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			System.setProperty("webdriver.edge.driver", "./Drivers/msedgedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			
			FileInputStream file = null;
			Sheet sheet = null;
			Row row = null;
			Sheet sheet2 = null;
			Row row1 = null;
			Workbook workbook = null;
			try {
				file = new FileInputStream(new File("./YBLcredentialsthird.xlsx"));
				workbook = new XSSFWorkbook(file);
//				sheet = workbook.getSheetAt(0);
				sheet2 = workbook.getSheetAt(0);
				row1 = sheet2.getRow(1);

				System.out.println(row1.getCell(0).getStringCellValue());
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver.get("https://10.0.0.5/internal_test/Base_App/Main_Navigation/GanasevaLogin.aspx");
			driver.findElement(By.xpath("//button[@id=\"details-button\"]")).click();
			driver.findElement(By.linkText("Proceed to 10.0.0.5 (unsafe)")).click();
			driver.findElement(By.xpath("//input[@name=\"txtLogin\"]")).sendKeys("rakesh");
			driver.findElement(By.xpath("//input[@name=\"txtPassword\"]")).sendKeys("Atyati@123");
			driver.findElement(By.xpath("//input[@id=\"txtCatpcha\"]")).sendKeys("testing");
			driver.findElement(By.xpath("//input[@name=\"ImgBtnLogin\"]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// driver.findElement(By.name("Button1")).click();
			driver.switchTo().frame("frame1");

			WebElement hoverElement = driver.findElement(By.linkText("Admin"));

			Actions actions = new Actions(driver);
			actions.doubleClick(hoverElement).perform();

			WebElement userAdminBtn = driver.findElement(By.linkText("User Admin"));
			actions.moveToElement(userAdminBtn).perform();

			WebElement UserDetailsbtn = driver.findElement(By.linkText("User Details"));
			actions.doubleClick(UserDetailsbtn).perform();

			driver.switchTo().parentFrame();
			driver.switchTo().frame(2);
			WebElement newbtn = driver.findElement(
					By.xpath("//input[@type=\"submit\" and @onclick=\"return confirm('Press OK to Create');\"]"));
			newbtn.click();
			driver.switchTo().alert().accept();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.switchTo().parentFrame();
			driver.switchTo().frame("main");
			driver.findElement(By.xpath("//input[@id=\"ctl00_TxnContentPage_CheckBox2\"]")).click();
			WebElement Agentdropdown = driver
					.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$DropDownList2\"]"));
			Select selectAgent = new Select(Agentdropdown);
			selectAgent.selectByValue("BC Agent");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement statusDropDown = driver.findElement(By.id("ctl00_TxnContentPage_ddlActive"));

			Select selectStatus = new Select(statusDropDown);
			selectStatus.selectByValue("1");

			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtOperatorCode\"]"))
					.sendKeys("" + row1.getCell(20).getNumericCellValue());
			WebElement entity = driver.findElement(By.xpath("//select[@name='ctl00$TxnContentPage$ddlBank']"));
			Select entitySelect = new Select(entity);
			entitySelect.selectByValue("56");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement title = driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlTitle\"]"));
			Select titlselect = new Select(title);
			titlselect.selectByValue("11");
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtUserName\"]"))
					.sendKeys(row1.getCell(0).getStringCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtUserName1\"]"))
					.sendKeys(row1.getCell(1).getStringCellValue());

			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtLoaginName\"]"))
					.sendKeys(row1.getCell(2).getStringCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtPassword\"]"))
					.sendKeys(row1.getCell(3).getStringCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtConfrmPsswd\"]"))
					.sendKeys(row1.getCell(3).getStringCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtDesignation\"]"))
					.sendKeys(row1.getCell(4).getStringCellValue());

			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtDOJ\"]"))
					.sendKeys(row1.getCell(5).getStringCellValue());

			driver.findElement(By.name("ctl00$TxnContentPage$txtAddress1")).sendKeys(row1.getCell(6).getStringCellValue());
			driver.findElement(By.name("ctl00$TxnContentPage$txtAddress2")).sendKeys(row1.getCell(7).getStringCellValue());
			driver.findElement(By.name("ctl00$TxnContentPage$txtAddress3")).sendKeys(row1.getCell(8).getStringCellValue());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement StateDropDown = driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlState\"]"));
			Select stateselect = new Select(StateDropDown);
			stateselect.selectByValue("30379");
			WebElement city = driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlCity\"]"));
			Select cityselect = new Select(city);
			cityselect.selectByValue("30380");
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtPincode\"]"))
					.sendKeys("" + row1.getCell(9).getNumericCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtPincode\"]"))
					.sendKeys("" + row1.getCell(9).getNumericCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtMobileNo\"]"))
					.sendKeys("" + row1.getCell(10).getNumericCellValue());

			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtOfficePhNo\"]"))
					.sendKeys("" + row1.getCell(11).getNumericCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtFaxNo\"]"))
					.sendKeys("" + row1.getCell(12).getNumericCellValue());

			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtEmail\"]"))
					.sendKeys(row1.getCell(13).getStringCellValue());
			WebElement gender = driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlGender\"]"));
			Select genderselect = new Select(gender);
			genderselect.selectByVisibleText((row1.getCell(22).getStringCellValue().substring(0, 1).toUpperCase())
					+ (row1.getCell(22).getStringCellValue().substring(1)));
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtdob\"]"))
					.sendKeys(row1.getCell(14).getStringCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtFatherName\"]"))
					.sendKeys(row1.getCell(15).getStringCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtSpouseName\"]"))
					.sendKeys(row1.getCell(16).getStringCellValue());
			driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtBAC\"]"))
					.sendKeys(row1.getCell(18).getStringCellValue());

			WebElement shgMenber = driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlSHGMember\"]"));

			Select yesorno = new Select(shgMenber);
			String memberornot = null;
			if (row1.getCell(21).getStringCellValue().equalsIgnoreCase("yes")) {
				memberornot = "1";
				yesorno.selectByValue(memberornot);
				driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtShgName\"]"))
						.sendKeys(row1.getCell(17).getStringCellValue());
			} else {
				memberornot = "2";
			}

			WebElement document = driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlIDProof\"]"));
			Select documentselection = new Select(document);
			if (Integer.parseInt(row1.getCell(23).getStringCellValue()) > 0) {
				documentselection.selectByValue("" + row1.getCell(23).getStringCellValue());
				driver.findElement(By.xpath("//input[@name=\"ctl00$TxnContentPage$txtDocumentNo\"]"))
						.sendKeys(row1.getCell(19).getStringCellValue() + "");
			}
	int age=AgeCalculator.calculateAge(row1.getCell(14).getStringCellValue());
			driver.findElement(By.xpath("//input[@id=\"ctl00_TxnContentPage_txtAge\"]")).sendKeys(""+age);
	WebElement caste=driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlCast\"]"));
	Select casteSelect=new Select(caste);
	casteSelect.selectByValue(row1.getCell(24).getStringCellValue());
	WebElement maritalStatus=driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlMaritualStatus\"]"));
	Select marital=new Select(maritalStatus);
	marital.selectByVisibleText(row1.getCell(25).getStringCellValue());

	WebElement education=driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlEduQua\"]"));
	Select eductionselction=new Select(education);
	eductionselction.selectByValue(""+(int)row1.getCell(26).getNumericCellValue());
	WebElement physicalCha=driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlPhyCha\"]"));
	Select physical= new Select(physicalCha);
	physical.selectByVisibleText(row1.getCell(27).getStringCellValue());
	driver.findElement(By.xpath("//input[@id=\"ctl00_TxnContentPage_chkBioCertify\"]")).click();
	WebElement compnay= driver.findElement(By.xpath("//select[@name=\"ctl00$TxnContentPage$ddlCmpnyname\"]"));
	Select cmpnyselect=new Select(compnay);
	cmpnyselect.selectByVisibleText(row1.getCell(28).getStringCellValue());
	driver.findElement(By.linkText("Level Settings")).click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//	driver.quit();
		}
	}


