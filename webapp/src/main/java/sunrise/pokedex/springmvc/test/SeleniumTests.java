package sunrise.pokedex.springmvc.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumTests {

    private SeleniumConfig seleniumConfig;
    private WebDriverWait wait;
    private WebElement username;
    private WebElement password;
    private WebElement loginBtn;

    @BeforeSuite
    public void setUp() {
        seleniumConfig = new SeleniumConfig();
        wait = new WebDriverWait(seleniumConfig.getDriver(), Duration.ofSeconds(1000L));
    }

    @AfterSuite
    public void tearDown() {
        seleniumConfig.close();
    }

    @BeforeTest
    public void beforeEach() {
        doLogin();
    }

    // This test includes testing the Read and Create Functionality of Pokedex
    @Test
    public void navigateToPokedex_AddPokemonBulbasaur_VerifyBulbusaurWasAddedAndDisplays() {
        seleniumConfig.getDriver().findElement(By.id("addPokemonLink")).click();
        seleniumConfig.getDriver().findElement(By.id("pokemonNameInput")).sendKeys("Bulbasaur");
        seleniumConfig.getDriver().findElement(By.id("pokemonAttackInput")).sendKeys("1");
        seleniumConfig.getDriver().findElement(By.id("pokemonDefenseInput")).sendKeys("1");
        seleniumConfig.getDriver().findElement(By.id("addButton")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Bulbasaur")));
        Assert.assertTrue(seleniumConfig.getDriver().findElement(By.id("Bulbasaur")).isDisplayed());
    }

    @Test
    public void readAllPokemonInPokedex_mewtwo_mew_SuperSaiyanPickachu_metapod() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mewtwo")));
        Assert.assertTrue(seleniumConfig.getDriver().findElement(By.id("mewtwo")).isDisplayed());
        Assert.assertTrue(seleniumConfig.getDriver().findElement(By.id("mew")).isDisplayed());
        Assert.assertTrue(seleniumConfig.getDriver().findElement(By.id("Super Saiyan Pickachu")).isDisplayed());
        Assert.assertTrue(seleniumConfig.getDriver().findElement(By.id("metapod")).isDisplayed());
    }

    @Test
    public void updatePokemonBulbasaurInPokedex_VerifyBulbusaurWasUpdatedAndDisplaysUpdatedInformation_ThenDeleteBulbasaur() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("editButtonBulbasaur")));
        seleniumConfig.getDriver().findElement(By.id("editButtonBulbasaur")).click();
        seleniumConfig.getDriver().findElement(By.id("pokemonAttackInput")).sendKeys("9001");
        seleniumConfig.getDriver().findElement(By.id("pokemonDefenseInput")).sendKeys("5000");
        seleniumConfig.getDriver().findElement(By.id("addButton")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Bulbasaur19001")));
        Assert.assertTrue(seleniumConfig.getDriver().findElement(By.id("Bulbasaur19001")).isDisplayed());
        // Delete Bulbasaur
        seleniumConfig.getDriver().findElement(By.id("removeButtonBulbasaur")).click();
    }

    public void doLogin() {
        seleniumConfig.getDriver().navigate().to("http://localhost:8080");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userName")));
        username = seleniumConfig.getDriver().findElement(By.id("userName"));
        password = seleniumConfig.getDriver().findElement(By.id("password"));
        loginBtn = seleniumConfig.getDriver().findElement(By.id("loginPageSubmitButton"));
        username.sendKeys("chris");
        password.sendKeys("chris");
        loginBtn.click();
    }

}
