package com.TESTCASE;

import com.BASE.DriverInstance;
import com.POM.ActionFormSubmission;
import com.UTILS.ReadTestData;
import com.UTILS.ReadUIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.*;
import java.time.Duration;

public class Test_FormSubmisstion extends DriverInstance {

    @Test (priority = 0)
    public void TC01_isPageLoadedSuccessfully() {
        /*
        Considering the appearance of the "Name", "Message" textbox and "Submit" button
            are a result of a successful loading.
        It's a simple site but due to device conditions when performing cross-browser testing,
            I think we'd better set the ImplicitlyWait at 15s to advoid TimeOutExc.
        */
        driver.manage().window().maximize();
        driver.get(ReadTestData.getTestData("URL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        WebElement textboxName = driver.findElement(
                By.xpath(ReadUIElement.getUIElement("textBoxName01")));

        WebElement textboxMessage = driver.findElement(
                By.xpath(ReadUIElement.getUIElement("textBoxMessage01")));

        WebElement submitButton = driver.findElement(
                By.xpath(ReadUIElement.getUIElement("submitBtn01")));

        //Using SoftAssert to avoid the break as HardAssert does
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(textboxName.isDisplayed(), "Textbox \"Name\" not found!!");
        softAssert.assertTrue(textboxMessage.isDisplayed(), "Textbox \"Message\" not found!!");
        softAssert.assertTrue(submitButton.isDisplayed(), "Button \"Submit\" not found!!");
        softAssert.assertAll();
    }

    @Test (priority = 1)
    public void TC02_isMessageDisplayedAfterSubmittingForm() throws Exception {
        ActionFormSubmission AFS = new ActionFormSubmission(driver);
        AFS.inputName(ReadTestData.getTestData("name"));
        AFS.inputMessage(ReadTestData.getTestData("message"));
        //Just giving a 1000ms-break right after having filled out the Message box, then hit Submit.
        Thread.sleep(1000);
        AFS.clickSubmitBtn();

        //ExplicitWait for a specific element: Notification message appears only after a successful submission.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement notiMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(ReadUIElement.getUIElement("notiLocation01"))));

        assertTrue(notiMessage.isDisplayed(), "Notification not Found!!");
    }

    @Test (priority = 2, dependsOnMethods = "TC02_isMessageDisplayedAfterSubmittingForm")
    public void TC03_isContentMessageAsRequirement() {
        String expectedMessage = ReadTestData.getTestData("expectedMessage");

        WebElement notiMessage = driver.findElement(
                By.xpath(ReadUIElement.getUIElement("notiLocation01")));
        String actualMessage = notiMessage.getText();

        assertEquals(expectedMessage, actualMessage, "The content of Message is incorrect!!");
    }
}
