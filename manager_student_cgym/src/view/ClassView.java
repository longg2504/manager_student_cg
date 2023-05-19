package view;

import model.EClass;
import service.ClassService;
import service.PointService;
import service.StudentService;

import java.util.List;
import java.util.Scanner;

public class ClassView {
    private final static String PATH = "./src/data/class.csv";
    private Scanner scanner = new Scanner(System.in);
    private PointService pointService;

    private StudentService studentService;


    private ClassService classService;

    public ClassView() {
        pointService = new PointService();
        studentService = new StudentService();
        classService = new ClassService();
    }




    public void launcherClass() {
        boolean actionCheck = false;
        do {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║             Menu chương trình          ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  1.Xem danh sách Lớp                   ║");
            System.out.println("║  2.Nhập điểm sinh viên                 ║");
            System.out.println("║  3.Xóa điểm sinh viên                  ║");
            System.out.println("║  4.Sửa điểm sinh viên                  ║");
            System.out.println("║  5.Hiển thị danh sách điểm theo lớp    ║");
            System.out.println("║  6.Hiển thị điểm của sinh viên theo ID ║");
            System.out.println("║  7.Hiển thị danh sách sinh viên đã đạt ║");
            System.out.println("║  8.Quay lại Menu                       ║");
            System.out.println("╚════════════════════════════════════════╝");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showListClass(classService.findAllEClass());
                    break;
//                case 2:
//                    showCreateStudentPoint();
//                    break;
//                case 3:
//                    deleteStudentPoint();
//                    break;
//                case 4:
//                    showListPoint(pointService.findAllStudentPoint());
//                    updateStudentPoint();
//                    break;
//                case 5:
//                    showListPointStudentByClass();
//                    break;
//                case 6:
//                    showStudentPointByID();
//                    break;
//                case 7:
//                    showListStudentPass();
//                    break;
                case 8:
                    actionCheck= true;
                    break;


            }

        } while (!actionCheck);
        if(actionCheck){
            Menu menu = new Menu();
            menu.menuView();
        }
    }

    public void showListClass(List<EClass> allClass) {
        //int idClass , String name
        System.out.println("+---------------+------------------------------+");
        System.out.printf("| %-13s | %-28s |\n", "ID", "Name");
        System.out.println("+---------------+------------------------------+");
        for (EClass p : allClass) {
            //int idClass , String name
            System.out.printf("| %-13s | %-28s |\n",p.getId(),p.getName());
            System.out.println("+---------------+------------------------------+");

        }
    }
}
