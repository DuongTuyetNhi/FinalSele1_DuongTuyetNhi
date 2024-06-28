import base.DriverManagement;
import enums.Locations;
import enums.SeatType;
import models.Ticket;
import models.User;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.*;

import static base.DateUtils.getDateAdd;
import static base.DriverManagement.getDriver;

public class MyTicketTest {
    MailPage mailPage = new MailPage();
    RegisterPage registerPage = new RegisterPage();
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    SuccessPage successPage = new SuccessPage();
    MyTicketPage myTicketPage = new MyTicketPage();
    String password = "12345678";
    String pid = "12345678";
    String date1 = getDateAdd(3);
    String date2 = getDateAdd(4);
    String date3 = getDateAdd(5);
    String date4 = getDateAdd(6);
    String date5 = getDateAdd(7);
    String date6 = getDateAdd(8);
    String arriveAt = "Sài Gòn";
    Ticket ticket = new Ticket(date6, Locations.NHA_TRANG, Locations.SAI_GON, SeatType.HS, "1");

    @BeforeClass
    public void PreCondition(){
        DriverManagement.initDriver();
        DriverManagement.openMailPage();
        String mail = mailPage.getEmail();
        User newAccountUser = new User(mail, password, pid);
        String MailWindow = getDriver().getWindowHandle();
        getDriver().switchTo().newWindow(WindowType.TAB);

        DriverManagement.openRailwayPage();
        homePage.clickCreateAnAccountLink();

        String RailwayWindow = getDriver().getWindowHandle();

        registerPage.fillRegisterForm(newAccountUser);
        registerPage.clickBtnRegister();
        Assert.assertTrue(registerPage.isMessageDisplayed());

        getDriver().switchTo().window(MailWindow);
        getDriver().navigate().refresh();

        mailPage.confirmAccount();

        DriverManagement.switchToTab(MailWindow, RailwayWindow);

        Assert.assertTrue(registerPage.isConfirmMessageDisplayed());

        homePage.openLoginTab();
        loginPage.submitLoginForm(newAccountUser);
    }

    @Test(description = "User can filter Manage ticket table with both Arrive station and Depart date")
    public void FilterTicket(){
        homePage.openTab("Book ticket");
        bookTicketPage.bookTicketFollowDepartDate(date1);
        successPage.openTab("Book ticket");
        bookTicketPage.bookTicketFollowDepartDate(date2);
        successPage.openTab("Book ticket");
        bookTicketPage.bookTicketFollowDepartDate(date3);
        successPage.openTab("Book ticket");
        bookTicketPage.bookTicketFollowDepartDate(date4);
        successPage.openTab("Book ticket");
        bookTicketPage.bookTicketFollowDepartDate(date5);
        successPage.openTab("Book ticket");
        bookTicketPage.bookTicket(ticket);
        bookTicketPage.clickBookTicketButton();

        successPage.openTab("My ticket");
        myTicketPage.filterByArriveAtAndDepartDate(arriveAt, date6);
        myTicketPage.clickButtonFilter();
        Assert.assertTrue(myTicketPage.isTicketDisplay(ticket));
    }
}
