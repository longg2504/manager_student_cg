package view;

import model.Customer;
import service.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LoginView {
    private Scanner scanner = new Scanner(System.in);
    private AdminView adminView;
    private CustomerService customerService;
    private static List<Customer> customerList;

    public  LoginView(){
        customerService = new CustomerService();
    }
    public Customer login(){
        boolean isIncorrectLogin;
        String username;
        String passWord;
        Customer customer = null;

        do {
            Map<String,String> listAccount = customerService.getAccountList();
            isIncorrectLogin = false;
            System.out.println("                    ╔═════════════════════════════════════════════════════╗");
            System.out.println("                    ║-------------------"+"Đăng nhập"+"-------------------------║");
            System.out.println("                    ╠═════════════════════════════════════════════════════╣");
            System.out.println("                    ║Vui lòng nhập tên người dùng và mật khẩu để tiếp tục ║");
            System.out.println("                    ╠═════════════════════════════════════════════════════╣");
            System.out.print("                                 Tên người dùng: " );
            username = scanner.nextLine();
            System.out.print("                                 Mật khẩu: "                          );
            passWord = scanner.nextLine();
            System.out.println("                    ╚═════════════════════════════════════════════════════╝");

            if (listAccount.containsKey(username)){
                if (passWord.equals(listAccount.get(username))){
                    customer = customerService.getCustomerByUserName(username);
                }else {
                    System.out.println("Mật khẩu không đúng. Vui lòng nhập lại");
                    isIncorrectLogin = true;
                }
            }else {
                System.out.println("tài khoản hoặc mật khẩu không đúng . vui lòng nhập lại!!!");
                isIncorrectLogin = true;
            }
        }while (isIncorrectLogin);

        return customer;
    }
}
