package Olx_scrapping;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Scraping {
	public static WebDriver driver;
	public static String Xpath = "/html/body/div[1]/div/main/div/div/section/div/div[2]/div[5]/div[2]/div[1]/div[2]/ul/li";
	
	public static String Xpath1 = "/a/div[1]/div[2]/div[2]";
	public static String Xpath2 ="/div/button";
	public static String File = System.getProperty("user.home") + "\\Desktop\\q.xlsx";
	private static XSSFWorkbook workbook;
	public static Row row;
	public static Cell cell;
	public static XSSFSheet sheet;

	public static String time() {
		LocalDateTime now = LocalDateTime.now(); // Get current date and time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Define the format of the
		String formattedDateTime = now.format(formatter);
		System.out.print(formattedDateTime);
		return formattedDateTime;
	}

	public static void FileOutputStream() {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("olx");

		System.out.println("sheet " + sheet.getSheetName());
		for (int i = 1; i < 41; i++) {
			try {
				System.out.println("forloop --> " + i);
				WebElement Insurance = driver.findElement(By.xpath(Xpath + "[" + i + "]" + Xpath1));
				System.out.println(Insurance.getText());
				sheet.createRow(i).createCell(0).setCellValue(Insurance.getText());
			} catch (Exception ex) {
				System.out.println("Exception occurred at the index - " + i + " and the exception is " + ex.getMessage());
			}
		}

	}

	public static void initiateExcelSetup() {
		try {
			System.out.println("Excel setup has been started...");
			FileInputStream fs = new FileInputStream(File);
			workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheetAt(0);
			row = sheet.createRow(sheet.getLastRowNum() + 2);
			System.out.println("Excel setup has been Ended...");
		} catch (Exception ex) {
			System.out.println("Some error has occurred in Excel setup process. The error is " + ex.getMessage());
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.out.println("File Path --> " + File);

		System.setProperty("webdriver.chrome.driver", "C:\\New folder (3)\\chromedriver_win32 (5)\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
//		options.addArguments("--headless"); //uncomment to view the browser
		driver = new ChromeDriver(options);
		driver.get("https://www.olx.in/coimbatore_g4059164/cars_c84");
		driver.manage().window().maximize();

		
		initiateExcelSetup();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("olx");

		System.out.println("sheet " + sheet.getSheetName());
		
		
		
		
		for (int i = 41; i < 43; i++) {
			try {
//				Thread.sleep(2000);
			
				System.out.println("forloop --> " + i);
				WebElement Insurance = driver.findElement(By.xpath(Xpath + "[" + i + "]" + Xpath1));
				
				
				if(Insurance.isDisplayed()) {
					System.out.println(Insurance.getText());
					sheet.createRow(i).createCell(0).setCellValue(Insurance.getText());
				}
//				WebElement button = driver.findElement(By.xpath(Xpath + "[" + i + "]" + Xpath2));
				else if(Insurance.isDisplayed()){
					Insurance.click();
				System.out.println("button clicked");
				
				}
				 else  {
					
////					 WebElement button = driver.findElement(By.xpath(Xpath + "[" + i + "]" + Xpath2));
//					 Thread.sleep(3000);
					 System.out.println("button-----------not------------- clicked");
				}
//				
			} catch (Exception ex) {
				System.out.println("Exception occurred at the index - " + i + " and the exception is " + ex.getMessage());
			}
		}

		FileOutputStream outputStream = new FileOutputStream(File);
		workbook.write(outputStream);
		workbook.close();
		System.out.println("Workbook has been ended!");

	}

}
