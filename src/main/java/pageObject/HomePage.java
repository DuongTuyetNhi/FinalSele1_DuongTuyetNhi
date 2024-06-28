package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.click;
import static base.DriverManagement.getDriver;

public class HomePage extends BasePage{
    private By lnkCreateAccount = By.xpath("//div[@id='content']//a[contains(@href,'Register')]");

    public void clickCreateAnAccountLink(){
        click(lnkCreateAccount);
    }
}
