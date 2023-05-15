package view;
import org.w3c.dom.ls.LSOutput;
import view.PointView;
import view.StudentView;

import java.util.Scanner;

public class Menu {

    static PointView pointView ;
    static StudentView studentView;
    public static Scanner scanner = new Scanner(System.in);
    public Menu(){
        pointView = new PointView();
        studentView = new StudentView();
    }
        public void menuView(){
            System.out.println("Menu");
            System.out.println("1.Quản Lý Sinh Viên");
            System.out.println("2.Quảmn Lý Điểm");
            int actionChoice = Integer.parseInt(scanner.nextLine());
            boolean flap = false;
            do {
                switch (actionChoice){
                    case 1 :
                        studentView.launcherStudent();
                        flap = false;
                        break;
                    case 2:
                        pointView.launcherPoint();
                        flap = false;
                        break;
                    default:
                        System.out.println("chỉ nhập 1 trong 2 ");
                        flap = true;
                        break;
                }
            }while (flap);

        }
}

