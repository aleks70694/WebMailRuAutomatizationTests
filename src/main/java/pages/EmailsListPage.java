package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class EmailsListPage extends BasePage{
    private final WebDriver driver;
    private Actions actions;
    private String titlePage = "Почта Mail.ru";
    private By emailList = By.xpath("//*[contains(@class, 'js-letter-list-item')]");
    private By inbox = By.xpath("//a[contains(@title, 'Входящие')]");
    private By createEmailButton = By.xpath("(//*[text() = 'Написать письмо'])[1]");
    private By emailSendedMessage = By.xpath("//span[text() = 'отправлено']");
    private By contextMenuDelete = By.xpath("//*[@class = 'contextmenu__container']//*[text() = 'Удалить']");

    public EmailsListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        actions = new Actions(driver);
        By title = By.xpath("//title[contains(text(), 'Почта Mail.ru')]");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(title));

//        if (!driver.getTitle().contains(titlePage)) {
//            throw new IllegalStateException("Заголовок страницы не соответствует эталону");
//        }
    }

    public List<WebElement> getEmailList() {
        List<WebElement> list = driver.findElements(emailList);
        return list;
    }

    public Integer getCountEmailList() {
        String textTitle = driver.findElement(inbox).getAttribute("title");
        String[] array = textTitle.split(",");
        Integer countEmails = Integer.parseInt(array[1].replaceAll("\\D", ""));
        return countEmails;
    }

    public EmailsListPage rightClickByEmail(List<WebElement> emailList, Integer pos) {
        actions.contextClick(emailList.get(pos)).build().perform();
        return this;
    }

    public EmailPopapPage clickCreateEmailButton() {
        driver.findElement(createEmailButton).click();
        return new EmailPopapPage(driver);
    }


    public String getTextSendedMessage () {
        return driver.findElement(emailSendedMessage).getText();
    }

    public EmailsListPage deleteEmail () {
        driver.findElement(contextMenuDelete).click();
        return new EmailsListPage(driver);
    }

}
