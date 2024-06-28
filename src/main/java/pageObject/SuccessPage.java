package pageObject;

import models.Ticket;
import org.openqa.selenium.By;

import static base.DriverManagement.getDriver;
import static base.DriverManagement.getText;

public class SuccessPage extends BasePage{
    private String rowTicketInfo = "//table//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]";

    private By priceOfTicket = By.xpath("//tr[td[count(//th[text()='Total Price']/preceding-sibling::th)+1]]");
    public Integer getTotalPriceOfTicket(){
        return Integer.parseInt(getText(priceOfTicket));
    }
}
