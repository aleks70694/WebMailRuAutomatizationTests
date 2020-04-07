package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BasePage {
    private WebDriver driver;
    private By notifyMessageText = By.xpath("//*[@class = 'notify__message__text']");
    private By notifyMessageAction = By.xpath("//*[@class = 'notify__action']");
    private By collapsedDtaft = By.xpath("//*[@class = 'compose-collapsed__item']//*[text() = 'Тестирование отправки письма']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public String getTextNotifyMessage () {
        return driver.findElement(notifyMessageText).getText();
    }

    public void clickActionNotifyMessage () {
        driver.findElement(notifyMessageAction).click();
    }

    public EmailPopapPage openDraft () {
        driver.findElement(collapsedDtaft).click();
        return new EmailPopapPage(driver);
    }
}
