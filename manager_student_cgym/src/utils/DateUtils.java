package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class DateUtils {
    public static Scanner scanner  = new Scanner(System.in);
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static String format(Date date) {
        return formatter.format(date);
    }

    public static Date parse(String strDate) {
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public class CheckInput {
        public static Scanner scanner = new Scanner(System.in);

        public static String getPassword() {
            while (true) {
                try {
                    System.out.println("Nhập mật khẩu đăng nhập?");
                    String pass = scanner.nextLine();
                    if (pass != null && !pass.trim().isEmpty() && pass.matches("[\\w]+")) {
                        return pass;
                    } else {
                        System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                    }
                } catch (Exception e) {
                    System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                }
            }
        }
    }

        public static String getDateOfBirth(){
            while (true){
                try {
                    System.out.println("Nhập ngày-tháng-năm");
                    System.out.println("Ví dụ: 17-01-2000");
                    String pass=scanner.nextLine();
                    if(pass !=null && !pass.trim().isEmpty() ){
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate date = LocalDate.parse(pass, formatter);
                        LocalDate now = LocalDate.now();
                        if(date.compareTo(now)<=0){
                            return pass;
                        }else {
                            System.out.println("Ngày nhập vào không được quá ngày hôm nay");
                        }
                    }else {
                        System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                    }
                }catch (Exception e){
                    System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                }
            }
        }
    }
