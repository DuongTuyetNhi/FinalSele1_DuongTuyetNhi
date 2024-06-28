import base.DriverManagement;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.*;

public class BookTicketTest {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    BookTicketPage bookTicketPage = new BookTicketPage();
    SuccessPage successPage = new SuccessPage();
    TicketPricePage ticketPricePage = new TicketPricePage();
    User validUser = new User("nhiagest@grr.la","12345678");
    String amount = "2";
    @Test(description = "User can book ticket with know price")
    public void BookTicket(){
        DriverManagement.openRailwayPage();
        homePage.openLoginTab();
        loginPage.submitLoginForm(validUser);
        homePage.openTab("Ticket price");
        ticketPricePage.clickTicketPrice("Huế", "Quảng Ngãi");

        String actualPriceOfHS = ticketPricePage.getPriceBySeatType("HS");
        Integer price = Integer.parseInt(actualPriceOfHS);

        ticketPricePage.clickSeatType("Hard seat");
        bookTicketPage.selectInfor("TicketAmount", amount);
        bookTicketPage.clickBookTicketButton();

        Integer expectedPrice = price*(Integer.parseInt(amount));
        Integer actualPrice = successPage.getTotalPriceOfTicket();
        Assert.assertEquals(actualPrice, expectedPrice, "Total price is not the same as expected.");
    }
}
