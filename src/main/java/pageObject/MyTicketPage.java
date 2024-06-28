package pageObject;

import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static base.DriverManagement.*;

public class MyTicketPage extends BasePage{
    private String rowTicketInfo = "//table[@class='MyTable']//tr[td[text()='%s' and following-sibling::td[text()='%s'" +
            " and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' " +
            "and following-sibling::td[text()='%s']]]]]]//input[contains(@onclick, 'Delete')]";

    private String sltArriveStationFilter = "//select[@name='FilterArStation']/option[text()='%s']";
    private String txtDepartDateFilter = "//td/input[@name='FilterDpDate']";
    private String btnFilter = "//input[@type='submit' and @value='Apply filter']";

    public void filterByArriveAtAndDepartDate(String arrvieAt, String departDate){
        By fltArriveAt = By.xpath(String.format(sltArriveStationFilter, arrvieAt));
        click(fltArriveAt);
        By txtDepartDate = By.xpath(txtDepartDateFilter);
        enter(txtDepartDate, departDate);
    }

    public void clickButtonFilter(){
        By btnFilterTicket = By.xpath(btnFilter);
        click(btnFilterTicket);
    }

    public boolean isTicketDisplay(Ticket ticket) {
        By findTicket = By.xpath(String.format(rowTicketInfo, ticket.getDepartFrom().getValueLocation(), ticket.getArriveAt().getValueLocation(),
                ticket.getSeatType().getValueSeatType(), ticket.getDepartDate(), ticket.getAmount()));
        return getDriver().findElement(findTicket).isDisplayed();
    }
}
