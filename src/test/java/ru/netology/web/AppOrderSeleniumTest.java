package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppOrderSeleniumTest {

    private WebDriver driver;


    @BeforeAll
     static void setUpAll() {
    // убедитесь, что файл chromedriver.exe расположен именно в каталоге
    //    System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
    @Test
    @DisplayName("Selenium UI Test")
    void shouldTestSomething() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=\"name\"] input"))
                .sendKeys("Василий Иванов");
        driver.findElement(By.cssSelector("[data-test-id=\"phone\"] input")).sendKeys("+79997654321");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("[type=\"button\"]")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText().trim();
        assertEquals(expected, actual);
    }
}

