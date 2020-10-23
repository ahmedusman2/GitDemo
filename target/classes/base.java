package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class base {

	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\01_AhmedWork\\01_Study\\06_E2EProject\\E2EProject (1)\\E2EProject\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);
		String browserName = System.getProperty("browser"); // Setting it to System.getProperty as Maven uses system
															// property for running test mvn test -Dbrowser=chrome
		// String browserName=prop.getProperty("browser"); // This line will be used
		// when you want to set property value from properties file
		System.out.println(browserName);

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\01_AhmedWork\\01_Study\\chromedriver.exe");
			driver = new ChromeDriver();
			// execute in chrome driver

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\01_AhmedWork\\01_Study\\geckodriver.exe");

			driver = new FirefoxDriver();
			// firefox code
		} else if (browserName.equals("IE")) {
			// IE code
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}

}
