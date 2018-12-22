import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



import junit.framework.Assert;

/**
 * 
 */

/**
 * @author win8
 *
 */
public class Sportskeeda {

	
	/**
	 * @param args
	 */
	
//	Xpath for the User trying to Register from the Sportskeeda Website
	private String xpathLogin="//div[@id='keeda-profile-container']/span";
	private String xpathRegister="//div[contains(@id, 'sign-in-box')]/descendant::span[contains(@class,'register')]";
	private String xpathEmailRegister="//div[contains(@class,'email-input-div')]/descendant::input[@id='register-email-input']";
	private String xpathNameRegister="//div[contains(@class,'name-input-div')]/descendant::input[@id='register-name-input']";
	private String xpathPasswordRegister="//div[contains(@class,'password-input-div')]/descendant::input[@id='register-password-input']";
	private String xpathConfirmPasswordRegister="//div[contains(@class,'password-input-div')]/descendant::input[@id='register-confirm-password-input']";
	private String xpathTermsAndConditions="//label[contains(@class,'custom-input-element')]/input[contains(@id,'register-terms-checkbox')]";
	private String xpathRegisterButton="//button[contains(@class,'register-btn')]";
	private String xpathNotRobot ="//div[@id='rc-imageselect']";
	
//	Xpath for the user trying to login from already registered user
	private String xpathEmailLogin="//div[contains(@class,'email-input-div')]/input";
	private String xpathPasswordLogin="//div[contains(@class,'password-input-div')]/input";
	private String xpathSignInButton="//button[contains(@class,'sign-in active')]";
	
//	Xpath for the USer logging Via Google
	private String xpathSigninWithGoogle="//a[contains(@class,'sso-google-button active')]";
	private String xpathUseAnotherAccount="//div[contains(@class,'vR13fe k6Zj8d SmR8')]/descendant::div[contains(@class,'BHzsHc')]";
	private String xpathSignInWithGoogleEmail="//div[contains(@class,'Xb9hP')]/descendant::input[@type='email']";
	private String xpathNextButton="//div[@id='identifierNext']/descendant::span[contains(@class,'RveJvd snByac')]";
	private String xpathSignInWithGooglePassword="//div[contains(@class,'Xb9hP')]/input[@type='password']";
	private String xpathPasswordNext="//div[@id='passwordNext']/descendant::span[contains(@class,'RveJvd snByac')]";
	
//	Xpath for the user to create Article
	private String xpathCreateButton="//span[contains(@class,'write-an-article')]";
	private String xpathProceedToTC="//span[contains(@class,'proceed-content')]";
	private String xpathAcceptTerms="//button[contains(@class,'accept-terms')]";
	private String xpathCreateNewDropDown="//div[contains(@class,'create-button no-select')]";
	private String xpathCreateArticle="//div[contains(@class,'create-expando')]/descendant::a[@href='/creator/create/article']";
	
	//Variable to assign values to different input fields
	private String emailAddress;
	private String userName;
	private String password;
	private String confirmPassword;
	
	//Function to validate Registering to the website
	public void register(WebDriver driver) {
		driver.findElement(By.xpath(this.xpathLogin)).click();
		driver.findElement(By.xpath(this.xpathRegister)).click();
		driver.findElement(By.xpath(this.xpathEmailRegister)).sendKeys(this.emailAddress);
		driver.findElement(By.xpath(this.xpathNameRegister)).sendKeys(this.userName);
		driver.findElement(By.xpath(this.xpathPasswordRegister)).sendKeys(this.password);
		driver.findElement(By.xpath(this.xpathConfirmPasswordRegister)).sendKeys(this.confirmPassword);
		driver.findElement(By.xpath(this.xpathTermsAndConditions)).click();
		driver.findElement(By.xpath(this.xpathRegisterButton)).click();
		
		//Trying to Register the websites will display popup to Verify If Human or Not
		try {
			if(driver.findElement(By.xpath(this.xpathNotRobot)).isDisplayed()) {
				Assert.assertEquals(true, true);
			}
		}catch(Exception e) {
			
		}
	}
	
	// User Logging in via already registerd Email
	public void loginEmail(WebDriver driver) {
		driver.findElement(By.xpath(this.xpathLogin)).click();
		driver.findElement(By.xpath(this.xpathEmailLogin)).sendKeys(this.emailAddress);
		driver.findElement(By.xpath(this.xpathPasswordLogin)).sendKeys(this.password);
		driver.findElement(By.xpath(this.xpathSignInButton)).click();
		this.reviewPoliciesOfSportsKeeda(driver);
	}
	
	//User Logging via Gmail if none of the account is logged in
	public void loginGmail(WebDriver driver) {
		driver.findElement(By.xpath(this.xpathLogin)).click();
		driver.findElement(By.xpath(this.xpathSigninWithGoogle)).click();
		driver.findElement(By.xpath(this.xpathUseAnotherAccount)).click();
		driver.findElement(By.xpath(this.xpathSignInWithGoogleEmail)).sendKeys(this.emailAddress);
		driver.findElement(By.xpath(this.xpathNextButton)).click();
		driver.findElement(By.xpath(this.xpathSignInWithGooglePassword)).sendKeys(this.password);
		driver.findElement(By.xpath(this.xpathPasswordNext)).click();
		this.reviewPoliciesOfSportsKeeda(driver);
		
		
	}
	
	//User reviewing the policies and creating an article to submit
	public void reviewPoliciesOfSportsKeeda(WebDriver driver) {
		driver.findElement(By.xpath(this.xpathCreateButton)).click();
		if(this.ifElementPresent(driver,this.xpathProceedToTC)) {
			driver.findElement(By.xpath(this.xpathProceedToTC)).click();
			if(driver.getCurrentUrl().equalsIgnoreCase("https://www.sportskeeda.com/creator/terms-and-conditions")) {
				System.out.println("User is viewing Terms and Conditions");
				driver.findElement(By.xpath(this.xpathAcceptTerms)).click();
				
			}
		}
		
		driver.findElement(By.xpath(this.xpathCreateNewDropDown)).click();
		driver.findElement(By.xpath(this.xpathCreateArticle)).click();
		
		
	}
	
	
	// Common function to check if a particular element is displayed in the page
	public boolean ifElementPresent(WebDriver driver, String xpath) {
		try {
			if(driver.findElement(By.xpath(xpath)).isDisplayed()) {
				return true;
			}
		}catch(Exception e) {
			return false;
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\win8\\Desktop\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		String url ="https://www.sportskeeda.com";
		driver.get(url);
		driver.manage().window().maximize();
		
		 
		try {
			File src=new File("G:/SportskeedaData.xlsx");
			FileInputStream fis=new FileInputStream(src);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sh1= wb.getSheetAt(0); 
			Sportskeeda testValidate= new Sportskeeda();
			
			//Checking for New Register
			if(sh1.getRow(0).getCell(5).getStringCellValue().equalsIgnoreCase("TRUE")) {  
				testValidate.emailAddress=sh1.getRow(0).getCell(1).getStringCellValue().toString();
				testValidate.userName=sh1.getRow(0).getCell(2).getStringCellValue();
				testValidate.password=sh1.getRow(0).getCell(3).getStringCellValue();
				testValidate.confirmPassword=sh1.getRow(0).getCell(4).getStringCellValue();
				testValidate.register(driver);
			}
			//Loging via Already registerd email
			else if(sh1.getRow(1).getCell(3).getStringCellValue().equalsIgnoreCase("TRUE")) {
				testValidate.emailAddress=sh1.getRow(1).getCell(1).getStringCellValue().toString();
				testValidate.password=sh1.getRow(1).getCell(2).getStringCellValue();
				testValidate.loginEmail(driver);
			}
			
			//Loging via Gmail
			else if(sh1.getRow(2).getCell(3).getStringCellValue().equalsIgnoreCase("TRUE")) {
				testValidate.emailAddress=sh1.getRow(2).getCell(1).getStringCellValue().toString();
				testValidate.password=sh1.getRow(2).getCell(2).getStringCellValue();
				testValidate.loginGmail(driver);
			}
			
			System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		

	}

}
