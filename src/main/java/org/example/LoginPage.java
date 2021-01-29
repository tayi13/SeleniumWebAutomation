package org.example;


import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.*;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.function.*;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;






public class LoginPage {

    WebDriver webDriver;
    WebDriverWait webDriverWait;

    static final Logger logger = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver,  30,  150);

    }

    public void login(String username,String password) {

            DOMConfigurator.configure("log4j.xml");
            logger.info("Test başlatıldı.");
            //Ana sayfanın açılması
            webDriver.get("https://www.gittigidiyor.com/");
            logger.info("Test Edilecek Sayfa : " + webDriver.getTitle());
            //Ana sayfanın kontrolünün yapılması
            Assert.assertEquals("GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi", webDriver.getTitle());
            WebElement searchBtn = webDriver.findElement(By.xpath("//*[text()='Giriş Yap']"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Kullanıcı girişi yapılması
             logger.info("Kullanıcı girişi yapılması");
            Actions action = new Actions(webDriver);
            action.moveToElement(searchBtn).perform();
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Giriş Yap'])[2]"))).click();
            Assert.assertEquals("Üye Girişi - GittiGidiyor", webDriver.getTitle());
            webDriver.findElement(By.id("L-UserNameField")).clear();
            webDriver.findElement(By.id("L-UserNameField")).sendKeys(username);
            webDriver.findElement(By.id("L-PasswordField")).clear();
            webDriver.findElement(By.id("L-PasswordField")).sendKeys(password);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("gg-login-enter"))).click();
            logger.info("Yönlendirilen Sayfa : " + webDriver.getTitle());
            //Ana sayfanın kontrolünün yapılması
            Assert.assertEquals("GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi", webDriver.getTitle());


            // Arama çubuğunda 'Bilgisayar' ifadesinin aranması
            logger.info("Arama çubuğunda 'Bilgisayar' ifadesinin aranması");
            WebElement searchData = webDriver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input"));
            searchData.sendKeys("Bilgisayar");
            searchData.sendKeys(Keys.ENTER);

            //2.Sayfanın açılması
            logger.info("2.Sayfanın açılması");
            WebElement sayfa2 = webDriver.findElement(By.xpath("//*[text()='2']"));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", sayfa2);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='2']"))).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            //2.sayfada olduğunun kontrol edilmesi
             logger.info("2.sayfada olduğunun kontrol edilmesi");
             Assert.assertEquals("Bilgisayar - GittiGidiyor - 2/100", webDriver.getTitle());



            //Sayfa fark etmeksizin,rastgele şekilde ilk ürünün seçilmesi
             logger.info("Sayfa fark etmeksizin,rastgele şekilde ilk ürünün seçilmesi");
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='gg-w-24 gg-d-24 gg-t-24 gg-m-24 pl0 pr0 product-info-details']"))).click();

            //Ürün fiyatının hafızaya alınması
             logger.info("Ürün fiyatının hafızaya alınması");
            WebElement element = webDriver.findElement(By.id("sp-price-highPrice"));
            String urundegeri = element.getAttribute("value");

            //Ürünün sepete eklenmesi
            logger.info("Ürünün sepete eklenmesi");
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-basket"))).click();

            //Ürünün,internet yavaşlığından kaynaklı sepete eklenmemesini engellemek için konulan süre
             logger.info("Ürünün,internet yavaşlığından kaynaklı sepete eklenmemesini engellemek için konulan süre");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='basket-container robot-header-iconContainer-cart']"))).click();
            WebElement element3 = webDriver.findElement(By.xpath("//*[@class='gg-d-8 detail-price']"));
            String sepetdegeri = element3.getAttribute("value");

            //Ürünün fiyatıyla sepetteki halinin fiyat karşılaştırılması
            logger.info("Ürünün fiyatıyla sepetteki halinin fiyat karşılaştırılması");
            org.testng.Assert.assertEquals(urundegeri, sepetdegeri);

            //Ürün sayısının artırılması
            logger.info("Ürün sayısının artırılması");
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='plus icon-plus gg-icon gg-icon-plus']"))).click();
            WebElement element4 = webDriver.findElement(By.xpath("//*[@class='amount']"));

            //Ürün sayısının kontrol edilmesi
            logger.info("Ürün sayısının kontrol edilmesi");
            String urunsayisi = element4.getAttribute("value");
            String urunsayisi2= "2";
            org.testng.Assert.assertEquals(urunsayisi, urunsayisi2);





            //İşlemi görmek için beklenmesi gereken süre
             logger.info("İşlemi görmek için beklenmesi gereken süre");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Ürünlerin sepetten silinmesi ve işlemin gerçekleşmesi için internet yavaşlığının beklenmesi
            logger.info("Ürünlerin sepetten silinmesi ve işlemin gerçekleşmesi için internet yavaşlığının beklenmesi");
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn-delete btn-update-item hidden-m']"))).click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Sepetin boş olduğunun kontrolünün yapılması
            logger.info("Sepetin boş olduğunun kontrolünün yapılması");
            Assert.assertEquals("Sepetinizde ürün bulunmamaktadır.", webDriver.findElement(By.tagName("h2")).getText());
             logger.info("Test Tamamlandı.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

    }
}


