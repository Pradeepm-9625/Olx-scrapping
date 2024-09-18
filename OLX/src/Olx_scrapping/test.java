package Olx_scrapping;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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

public class test {
	public static WebDriver driver;
	public static String Xpath ="/html/body/div[1]/div/main/div/div/section/div/div[2]/div[5]/div[2]/div[1]/div[2]/ul/li";
	public static String Xpath1 ="/a/div[1]/div[2]/div[2]";
	public static String File = System.getProperty("user.home") + "\\Desktop\\q.xlsx";
	private static XSSFWorkbook workbook;
	public static Row row;
	public static Cell cell;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\New folder (3)\\chromedriver_win32 (5)\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
//		options.addArguments("--headless"); //uncomment to view the browser
		driver = new ChromeDriver(options);
		driver.get("https://www.olx.in/coimbatore_g4059164/cars_c84");
		driver.manage().window().maximize();
		for (int i = 41; i < 45; i++) {
			try {
//				Thread.sleep(2000);
				System.out.println("forloop --> " + i);
				WebElement button = driver.findElement(By.xpath(Xpath + "[" + i + "]" +"/div/button" ));
				button.click();
				System.out.println("button clicked");
//				sheet.createRow(i).createCell(0).setCellValue(Insurance.getText());}
			
			} catch (Exception ex) {
				System.out.println("Exception occurred at the index - " + i + " and the exception is " + ex.getMessage());
			}
		}

		
		
		
		
		
	}}
		
		
					


