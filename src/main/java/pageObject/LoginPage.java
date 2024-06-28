package pageObject;

import base.DriverManagement;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static base.DriverManagement.*;

public class LoginPage extends BasePage{

    private By txtUsername = By.id("username");
    private By txtPassword = By.id("password");
    private By btnLogin = By.xpath("//input[@type='submit']");

    public void submitLoginForm(User user){
        enter(txtUsername, user.getUsername());
        enter(txtPassword, user.getPassword());
        DriverManagement.scrollToView(btnLogin);
        click(btnLogin);
    }


}
