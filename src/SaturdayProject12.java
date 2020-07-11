import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

public class SaturdayProject12 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ekrem\\OneDrive\\Masaüstü\\TechnoStudy\\Selenium\\SeleniumDependency\\drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://app.hubspot.com/login");

        driver.findElement(By.cssSelector("input[id='username']")).sendKeys("ilhanberkecan29@gmail.com");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("E125112.c");

        driver.findElement(By.id("loginBtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement sealButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-primary-sales-branch")));
        sealButton.click();
        // driver.findElement(By.id("nav-primary-sales-branch")).click();
        driver.findElement(By.id("nav-secondary-deals")).click();
        driver.navigate().refresh();

        driver.findElement(By.cssSelector("button[data-onboarding='new-object-button']")).click();


        driver.findElement(By.cssSelector("input[data-field='dealname']")).sendKeys("asd");
//        driver.findElement(By.xpath("(//span[text()='Sales Pipeline'])[2]")).click();
        String mainWindowHandle = driver.getWindowHandle();
        driver.findElement(By.xpath("(//span[text()='Sales Pipeline'])[2]")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
            }
        }
        String actualURL = driver.getCurrentUrl();
        String excpectedURL = "https://app.hubspot.com/pricing/8080142/sales?upgradeSource=deals-create-deal-general-create-deal-multiple-pipelines-pql-feature-lock&term=annual&edition=starter";
        //    Assert.assertEquals(excpectedURL, actualURL);

        driver.close();

        driver.switchTo().window(mainWindowHandle);

        driver.findElement(By.xpath("(//span[text()='Termin geplant'])[1]")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("li[class*='selectable']"));
        elements.get(new Random().nextInt(elements.size())).click();

        driver.findElement(By.xpath("(//span[@class='uiDropdown__buttonContents private-dropdown__button__contents'])[11]")).click();
        List<WebElement> elements1 = driver.findElements(By.cssSelector("button[tabindex='-1']"));
        elements1.get(numGen(1, elements1.size()) - 1).click();

        driver.findElement(By.cssSelector("button[data-selenium-test='base-dialog-confirm-btn']")).click();

        driver.findElement(By.xpath("(//span[@data-icon-name='edit'])[1]//span")).click();

        WebElement editButton = driver.findElement(By.xpath("(//input[@class='form-control private-form__control'])[1]"));
        editButton.clear();
        editButton.sendKeys("OHIO");

        driver.findElement(By.cssSelector("button[data-button-use='tertiary']")).click();


        wait.until(ExpectedConditions.textToBe(By.cssSelector("span[tabindex='0']>span"), "OHIO"));

        String text = driver.findElement(By.cssSelector("span[tabindex='0']>span")).getText();
        Assert.assertEquals("OHIO", text);

        driver.findElement(By.id("uiabstractdropdown-button-9")).click();

        driver.findElement(By.xpath("(//ul//li[@class='uiListItem private-list__item'])[18]")).click();
        driver.findElement(By.cssSelector("button[data-button-use='danger']>i18n-string")).click();

        wait.until(ExpectedConditions.titleIs("Deals"));
        Assert.assertNotEquals("OHIO", driver.getTitle());


    }

    public static int numGen(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
