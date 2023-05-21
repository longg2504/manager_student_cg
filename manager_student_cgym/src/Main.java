import model.Customer;
import view.AdminView;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        Customer customer = loginView.login();

        if (customer.geteRole().name().equals("ADMIN")) {
            AdminView adminView = new AdminView();
            adminView.menuView();
        }
    }
}
