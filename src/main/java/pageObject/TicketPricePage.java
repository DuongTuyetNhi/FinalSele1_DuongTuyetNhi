package pageObject;

import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class TicketPricePage extends BasePage{

    private String rowPrice = "//table[@class='MyTable MedTable']//th[normalize-space()='Price (VND)']//following-sibling::td[count(//td[text()='%s']/preceding-sibling::td)+1]";
    private String lnkCheckPriceTicket = "//tr[td/li[text()='%s']]//a[contains(@href, 'TicketPricePage')]";
    private String lnkSeatType = "//tr[td[text()='%s']]//a[contains(@href, 'BookTicketPage')]";

    public String getPriceBySeatType(String seatType){
        By ticketPrice = By.xpath(String.format(rowPrice, seatType));
        return getText(ticketPrice);
    }

    public void clickTicketPrice(String departFrom, String arriveAt){
        String str = departFrom + " to " + arriveAt;
        By lnkTicket = By.xpath(String.format(lnkCheckPriceTicket, str));
        click(lnkTicket);
    }
    public void clickSeatType(String seatType){
        By linkSeatType = By.xpath(String.format(lnkSeatType, seatType));
        click(linkSeatType);
    }

}
