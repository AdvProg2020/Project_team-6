package server.controller.sellerPanels;
import server.Server;
import server.controller.Parent;
import server.model.account.Account;
import java.io.IOException;
public class ReceiveBuyerInfo implements Parent {
    private Server server = null;
    @Override
    public void start(Server server) throws IOException {
        Account tempAccount = server.getCurrentlyLoggedInUsers();
        String emailAddress = tempAccount.getEmailAddress();
        String firstName = tempAccount.getFirstName();
        String lastName = tempAccount.getLastName();
        String phoneNumber = tempAccount.getPhoneNumber();
        String username = tempAccount.getUsername();
        //username---firstName---lastName---emailAddress---phoneNumber
        sendMessage(username + "---" + firstName + "---" + lastName + "---" + emailAddress + "---" + phoneNumber);
        new VerifyDiscountCode().start(server);
    }
    private void sendMessage(String message) throws IOException {
        server.sendMessage("16-" + message);
    }
}
