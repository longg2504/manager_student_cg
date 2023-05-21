package service;

import model.Customer;
import model.ERole;
import model.Student;
import utils.CSVUtils;
import utils.DateUtils;
import utils.ValidateUtils;

import java.util.*;

public class CustomerService {
    private static Scanner scanner = new Scanner(System.in);

    private Map<String, String> accountList = new HashMap<>();

    private static final String PATH = "./src/data/customer.csv";

    public List<Customer> findAllCustomer() {
        return CSVUtils.readFile(PATH, Customer.class);
    }
    public Map<String, String> getAccountList() {
        List<Customer> customerList  = findAllCustomer();
        for (Customer customer : customerList) {
            accountList.put(customer.getUserName(), customer.getPassWord());
        }
        return accountList;
    }
    public Customer getCustomerByUserName(String userName) {
        List<Customer> customerList  = findAllCustomer();
        for (Customer customer : customerList) {
            if (customer.getUserName().equals(userName)) {
                return customer;
            }
        }
        return null;
    }

    public int getNewCustomerId() {
        List<Customer> customerList  = findAllCustomer();
        int maxId = 0;
        for (Customer customer : customerList) {
            if (customer.getCustomerId() > maxId) {
                maxId = customer.getCustomerId();
            }
        }
        return maxId + 1;
    }
    public String getInputCustomerName(){
        boolean isInvalidName;
        String customerName;

        do {
            isInvalidName = false;
            System.out.println("Nhập vào tên khách hàng: ");
            customerName = scanner.nextLine();
            if (!ValidateUtils.customerNameValidate(customerName)){
                System.out.println("Tên nhập vào không hợp lệ");
                isInvalidName = true;
            }
        }while (isInvalidName);

        return customerName;
    }
    public String getInputUsername(){
        boolean isInvalidUsername;
        String username;

        do {
            isInvalidUsername = false;
            System.out.println("Nhập username: ");
            username = scanner.nextLine();
            if (getAccountList().containsKey(username)){
                System.out.println("Username đã tồn tại. Xin nhập lại");
                isInvalidUsername = true;
            }else {
                if (!ValidateUtils.userameValidate(username)){
                    System.out.println("Username nhập vào không hợp lệ. Xin nhập lại");
                    isInvalidUsername = true;
                }
            }
        }while (isInvalidUsername);

        return username;
    }
    public String getInputPassword(){
        boolean isInvalidPassword;
        String password;

        do {
            isInvalidPassword = false;
            System.out.println("Nhập password: ");
            password = scanner.nextLine();
            if (!ValidateUtils.passwordValidate(password)){
                System.out.println("Password phải chứa it nhất 1 chữ viết hoa, 1 chữ thường, 1 ký tự số, 1 Ký tự đặc biệt. Xin nhập lại");
                isInvalidPassword = true;
            }
        }while (isInvalidPassword);

        return password;
    }
    public Date getInputDOB(){
        boolean isInvalidDate;
        Date DOB = DateUtils.parse(DateUtils.getDateOfBirth());
        return DOB;
    }
    public Customer createCustomer(){
        int customerId = getNewCustomerId();
        String customerName = getInputCustomerName();
        String username = getInputUsername();
        String password = getInputPassword();
        Date DOB = getInputDOB();
        int age = DateUtils.getAgeByDOB(DOB);

        return new Customer(customerId,customerName,username,password,DOB,age,ERole.ADMIN);
    }

}
