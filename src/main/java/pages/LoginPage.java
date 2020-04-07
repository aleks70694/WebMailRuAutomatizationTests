package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    private String titlePage = "Mail.ru: почта, поиск в интернете, новости, игры";
    private By title = By.xpath("//title[text() = 'Mail.ru: почта, поиск в интернете, новости, игры']");
    private By loginLocator = By.xpath("//*[@id = 'mailbox:login']");
    private By enterPasswordButtonLocator = By.xpath("//*[text() = 'Ввести пароль']");
    private By passwordLocator = By.xpath("//*[@id = 'mailbox:password']");
    private By enterButton = By.xpath("//*[text() = 'Войти']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(title));

//        if (!driver.getTitle().equals(titlePage)) {
//            throw new IllegalStateException("Заголовок страницы не соответствует эталону");
//        }
    }

    public LoginPage typeLogin(String login) {
        driver.findElement(loginLocator).sendKeys(login);
        return this;
    }

    public LoginPage clickEnterPasswordButton () {
        driver.findElement(enterPasswordButtonLocator).click();
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public EmailsListPage clickSubmitEnterButton () {
        driver.findElement(enterButton).click();
        return new EmailsListPage(driver);
    }

    public EmailsListPage loginToMailRu (String login, String password) {
        typeLogin(login);
        clickEnterPasswordButton();
        typePassword(password);
        clickSubmitEnterButton();
        return new EmailsListPage(driver);
    }
}
