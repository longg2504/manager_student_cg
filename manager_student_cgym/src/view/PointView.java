package view;

import model.Point;
import model.Student;
import service.PointService;
import service.StudentService;
import utils.DateUtils;

import java.util.List;
import java.util.Scanner;

public class PointView {
    private final static String PATH = "./src/data/point.csv";
    private Scanner scanner = new Scanner(System.in);
    private PointService pointService;

    private StudentService studentService;

    public PointView() {
        pointService = new PointService();
        studentService = new StudentService();
    }

    public void launcherPoint(){
        do{
            System.out.println("Menu chương trình:");
            System.out.println("Nhập 1: Xem danh sách điểm sinh viên");
            System.out.println("Nhập 2: Thêm điểm sinh viên");
            System.out.println("Nhập 3: Xóa điểm sinh viên");
            System.out.println("Nhập 4: Sửa điểm sinh viên");
            System.out.println("Nhập 5: Hiển thị danh sách điểm theo lớp");
            System.out.println("Nhập 6: Hiển thị điểm của sinh viên theo ID");
            System.out.println("Nhập 7: Hiển thị danh sách sinh viên đã đạt");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showListPoint(pointService.findAllStudentPoint());
                    break;
                case 2:
                    showCreateStudent();
                    break;
                case 3:

                    break;
                case 4:


                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;

            }
        }while (true);

    }

    private void showCreateStudent() {
        boolean flag = false;
        do{
            System.out.println("Nhập vào ID muốn thêm điểm");
            int idStudent = Integer.parseInt(scanner.nextLine());
            if(pointService.existStudentPoint(idStudent)){
                System.out.println("Đã có sinh viên này trong danh sách điểm");
                flag = true;
            }
            else {
                System.out.println("");
            }

        }while (flag);




            }




    private void showListPoint(List<Point> allPoint) {
        //int idStudent, EClass eClasss, float pointTH, float pointLT //
        System.out.printf("%-15s %-30s %-10s %-15s %-20s \n", "ID","Name" ,"Class", "pointTH", "pointLT");
        for (Point p : allPoint) {
                    //int idStudent, EClass eClasss, float pointTH, float pointLT //
                    System.out.printf("%-15s %-30s %-10s %-15s %-20s\n", p.getIdStudent(),studentService.findStudentByID(p.getIdStudent()).getName(),p.geteClass(),
                           p.getPointTH(),p.getPointLT());
                }
            }
}


