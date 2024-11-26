package com.POM;

import com.UTILS.ReadUIElement;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionFormSubmission {

    public WebDriver driver;
    public ActionFormSubmission (WebDriver driver) {
        this.driver = driver;
    }
    public void inputName (String name) throws Exception{
        WebElement textBoxName = driver.findElement(
                By.xpath(ReadUIElement.getUIElement("textBoxName01")));
        textBoxName.sendKeys(name);
        Thread.sleep(500);
    }

    public void inputMessage (String message) throws Exception{
        WebElement textBoxMessage = driver.findElement(
                By.xpath(ReadUIElement.getUIElement("textBoxMessage01")));
        textBoxMessage.sendKeys(message);
        Thread.sleep(500);
    }

    public void clickSubmitBtn() {
        WebElement submitBtn = driver.findElement(
                By.xpath(ReadUIElement.getUIElement("submitBtn01")));
        submitBtn.click();
    }
}
