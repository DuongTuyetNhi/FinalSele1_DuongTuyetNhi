package pageObject;

import base.DriverManagement;
import org.openqa.selenium.By;

import static base.DriverManagement.*;
import static config.Constant.timeout;

public class MailPage extends BasePage{
    private By cbxScrambleAddress = By.id("use-alias");
    private By txtEmail = By.id("email-widget");
    private By txtEmailConfirm = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
    private By txtLinkConfirm = By.xpath("//*[@id='display_email']//a[contains(@href,'Confirm')]");

    public String getEmail(){
        getDriver().findElement(cbxScrambleAddress).click();
        String email = getDriver().findElement(txtEmail).getText();
        return email;
    }

    public void confirmAccount(){
        DriverManagement.waitForElementVisible(txtEmailConfirm, timeout);
        click(txtEmailConfirm);

        DriverManagement.waitForElementVisible(txtLinkConfirm, timeout);
        click(txtLinkConfirm);
    }
}
