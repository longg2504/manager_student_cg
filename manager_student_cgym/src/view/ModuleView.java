package view;

import model.Course;
import model.EClass;
import service.*;

import java.util.List;
import java.util.Scanner;

public class ModuleView {
    private final static String PATH = "./src/data/class.csv";
    private Scanner scanner = new Scanner(System.in);
    private PointService pointService;

    private StudentService studentService;


    private ClassService classService;

    private CourseService courseService;

    public ModuleView() {
        pointService = new PointService();
        studentService = new StudentService();
        classService = new ClassService();
        courseService = new CourseService();
    }

    public void launcherCourse() {
        boolean actionCheck = false;
        do {
            System.out.println("                ╔════════════════════════════════════════╗");
            System.out.println("                ║             Menu chương trình          ║");
            System.out.println("                ╠════════════════════════════════════════╣");
            System.out.println("                ║  1.Xem danh sách Module                ║");
            System.out.println("                ║  2.Thêm Module học                     ║");
            System.out.println("                ║  3.Xóa Module Học                      ║");
            System.out.println("                ║  4.Sửa Module Học                      ║");
            System.out.println("                ║  5.Quay lại Menu                       ║");
            System.out.println("                ╚════════════════════════════════════════╝");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showListCourse(courseService.findAllCourse());
                    actionCheck = checkActionContinue();
                    break;
                case 2:
                    showCreateCourse();
                    actionCheck = checkActionContinue();
                    break;
                case 3:
                    removeCourse();
                    actionCheck = checkActionContinue();
                    break;
                case 4:
                    editCourse();
                    actionCheck = checkActionContinue();
                    break;
                case 5:
                    actionCheck= true;
                    break;
            }
        } while (!actionCheck);
        if(actionCheck){
            AdminView adminView = new AdminView();
            adminView.menuView();
        }
    }

    public void showListCourse(List<Course> allCourse) {
        //int idClass , String name
        System.out.println("+---------------+------------------------------+");
        System.out.printf("| %-13s | %-28s |\n", "ID", "Name");
        System.out.println("+---------------+------------------------------+");
        for (Course p : allCourse) {
            //int idClass , String name
            System.out.printf("| %-13s | %-28s |\n", p.getId(), p.getName());
            System.out.println("+---------------+------------------------------+");

        }
    }

    private void editCourse() {
        showListCourse(courseService.findAllCourse());
        int idCourse = 0;
        boolean checkAction = true;
        do {
            System.out.println("Nhập ID muốn sửa ");
            try {
                idCourse = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID nhập vào không đúng");
                continue;
            }
            if (idCourse <= 0) {
                System.out.println("ID nhập vào phải lớn hơn 0");
                checkAction = true;
            }
            else {
                Course course = courseService.findCourseByID(idCourse);
                System.out.println("Nhập tên module muốn sửa");
                String name = scanner.nextLine();
                course.setName(name);

                courseService.editCourse((int) course.getId(),course);
                System.out.println("đã sửa thành công thành công");
                showListCourse(courseService.findAllCourse());
                checkAction = checkAgaint();
            }
        }while (checkAction);
    }

    private void showCreateCourse() {
        int idCourse = 0;
        boolean checkAction = true;
        do {
            System.out.println("Nhập ID ");
            try{
                idCourse = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("ID nhập vào không đúng");
                continue;
            }
            if(idCourse <= 0 ){
                System.out.println("ID nhập vào phải lớn hơn 0");
                checkAction = true;
            }else {
                if(courseService.existCourse(idCourse)){
                    System.out.println("lớp này đã có vui lòng nhập lại");
                    checkAction = true;
                }
                else{
                    System.out.println("Nhập Tên Module muốn tạo");
                    String name = scanner.nextLine();
                    Course course = new Course(idCourse , name);
                    courseService.addCourse(course);
                    System.out.println("Đã tạo thêm lớp thành công");
                    showListCourse(courseService.findAllCourse());
                    checkAction = checkAgaint();
                }
            }
        }while (checkAction);

    }

    public void removeCourse() {
        showListCourse(courseService.findAllCourse());
        int idCourse = 0;
        boolean checkAction = true;
        do {
            System.out.println("Nhập ID muốn xoá ");
            try {
                idCourse = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID nhập vào không đúng");
                continue;
            }
            if (idCourse <= 0) {
                System.out.println("ID nhập vào phải lớn hơn 0");
                checkAction = true;
            }
            else {
                courseService.deleteCourse(idCourse);
                System.out.println("đã xoá thành công");
                showListCourse(courseService.findAllCourse());
                checkAction = checkAgaint();
            }
        }while (checkAction);
    }


    public boolean checkAgaint() {
        boolean checkActionContinue = false;
        do {
            System.out.println("Nhập \"Y\" để tiếp tục, nhập \"N\" để quay về giao diện menu trước");
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Nhập sai lựa chọn");
                    checkActionContinue = false;
                    break;
            }
        } while (!checkActionContinue);
        return true;
    }

    public boolean checkActionContinue() {
        boolean checkActionContinue = false;
        do {
            System.out.println("Nhập \"Y\" để quay về giao diện trước đó, nhập \"N\" để quay về giao diện Menu!");
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "Y":
                    return false;
                case "N":
                    return true;
                default:
                    checkActionContinue = false;
            }
        } while (!checkActionContinue);
        return true;
    }
}
