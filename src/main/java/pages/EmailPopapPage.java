package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPopapPage  extends BasePage{
    private final WebDriver driver;
    private By to = By.xpath("(//input[@type = 'text'])[2]");
    private By toValue = By.xpath("//*[@data-type = 'to']//span[text() = 'test_testov2021@mail.ru']");
    private By subject = By.xpath("//*[@name = 'Subject']");
    private By textArea = By.xpath("(//*[contains(@class, 'editable')])[2]/div[1]");
    private By sendButton = By.xpath("(//*[text() = 'Отправить'])[1]");
    private By saveDraftButton = By.xpath("//*[text() = 'Сохранить']");

    public EmailPopapPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        By isDisplayedPopap = By.xpath("//*[contains(@class, 'compose-app_popup')]");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(isDisplayedPopap));
    }

    public EmailPopapPage setTo(String text) {
        driver.findElement(to).sendKeys(text);
        return this;
    }

    public String getTextTo() {
        return driver.findElement(toValue).getText();
    }

    public EmailPopapPage setSubject(String text) {
        driver.findElement(subject).sendKeys(text);
        return this;
    }

    public String getTextSubject() {
        return driver.findElement(subject).getAttribute("value");
    }

    public EmailPopapPage setTextArea(String text) {
        driver.findElement(textArea).sendKeys(text);
        return this;
    }

    public String getTextArea() {
        return driver.findElement(textArea).getText();
    }

    public EmailsListPage clickSendEmailButton() {
        driver.findElement(sendButton).click();
        return new EmailsListPage(driver);
    }

    public EmailPopapPage clickSaveEmailButton() {
        driver.findElement(saveDraftButton).click();
        return this;
    }

}
