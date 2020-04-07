import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.EmailPopapPage;
import pages.EmailsListPage;
import pages.LoginPage;

public class SuperTest {
    private WebDriver driver;

    @BeforeMethod
    public void preCondition () {
        //создание объекта драйвера и авторизация
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://mail.ru/");
        LoginPage page = new LoginPage(driver);
        page.loginToMailRu("test_testov2021@mail.ru", "202120212021Tt");
    }

    @Test
    public void createEmailAndSend() {
        EmailsListPage emailsListPage = new EmailsListPage(driver);
        EmailPopapPage emailPopapPage = emailsListPage.clickCreateEmailButton();
        emailPopapPage.setTo("test_testov2021@mail.ru");
        emailPopapPage.setSubject("Тестирование отправки письма");
        emailPopapPage.setTextArea("Пишу какой-то текст");
        emailsListPage = emailPopapPage.clickSendEmailButton();
        Assert.assertTrue("отправлено".equals(emailsListPage.getTextSendedMessage()));
    }

    @Test
    public void deleteEmail() throws InterruptedException{
        EmailsListPage emailsListPage = new EmailsListPage(driver);
        int emailsCountBefore = emailsListPage.getCountEmailList();
        emailsListPage.rightClickByEmail(emailsListPage.getEmailList(), 0);
        emailsListPage = emailsListPage.deleteEmail();
        Thread.sleep(1000);
        int emailsCountAfter = emailsListPage.getCountEmailList();
        Assert.assertTrue((emailsCountBefore-1) == emailsCountAfter);
    }

    @Test
    public void createDraftEmail() throws InterruptedException{
        EmailsListPage emailsListPage = new EmailsListPage(driver);
        EmailPopapPage emailPopapPage = emailsListPage.clickCreateEmailButton();
        emailPopapPage.setTo("test_testov2021@mail.ru");
        emailPopapPage.setSubject("Тестирование отправки письма");
        emailPopapPage.setTextArea("Пишу какой-то текст");
        emailPopapPage.clickSaveEmailButton();
        Thread.sleep(2000);
        Assert.assertTrue(emailPopapPage.getTextNotifyMessage().contains("Сохранено в черновиках"));
        emailPopapPage.clickActionNotifyMessage();
        emailPopapPage.openDraft();
        Thread.sleep(2000);
        Assert.assertTrue("test_testov2021@mail.ru".equals(emailPopapPage.getTextTo()));
        Assert.assertTrue("Тестирование отправки письма".equals(emailPopapPage.getTextSubject()));
        Assert.assertTrue("Пишу какой-то текст".equals(emailPopapPage.getTextArea()));
        emailsListPage = emailPopapPage.clickSendEmailButton();
        Assert.assertTrue("отправлено".equals(emailsListPage.getTextSendedMessage()));
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }
}
