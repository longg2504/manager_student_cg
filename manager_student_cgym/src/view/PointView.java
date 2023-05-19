package view;

import model.*;
import service.*;
import utils.DateUtils;

import java.util.List;
import java.util.Scanner;

public class PointView {
    private final static String PATH = "./src/data/pointmodule1.csv";
    private Scanner scanner = new Scanner(System.in);
    private PointService pointService;

    private StudentService studentService;

    private CourseService courseService;

    private ClassService classService;

    private ClassView classView;

    private ModuleView moduleView;

    public PointView() {
        pointService = new PointService();
        studentService = new StudentService();
        courseService = new CourseService();
        classService = new ClassService();
        classView = new ClassView();
        moduleView = new ModuleView();
    }
    public void menuPoint(){
        System.out.println("                    ╔════════════════════════════════════════╗");
        System.out.println("                    ║             Menu chương trình          ║");
        System.out.println("                    ╠════════════════════════════════════════╣");
        System.out.println("                    ║  1.Xem danh sách điểm sinh viên        ║");
        System.out.println("                    ║  2.Nhập điểm sinh viên                 ║");
        System.out.println("                    ║  3.Xóa điểm sinh viên                  ║");
        System.out.println("                    ║  4.Hiển thị danh sách điểm theo lớp    ║");
        System.out.println("                    ║  5.Hiển thị danh sách sinh viên đã đạt ║");
        System.out.println("                    ║  6.Xem lịch sử học của sinh viên       ║");
        System.out.println("                    ║  7.Exits                               ║");
        System.out.println("                    ╚════════════════════════════════════════╝");
        System.out.print("Vui lòng chọn một lựa chọn: ");
    }
    public void launcherPoint() {
        boolean actionCheck = false;
        do {
            menuPoint();
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showListStudent();
                    actionCheck = checkActionContinue();
                    break;
                case 2:
                    showCreateStudentPoint();
                    actionCheck = checkActionContinue();
                    break;
                case 3:
                    deleteStudentPoint();
                    actionCheck = checkActionContinue();
                    break;
                case 4:
                    showListPointStudentByClass();
                    actionCheck = checkActionContinue();
                    break;
                case 5:
                    showListStudentPass();
                    actionCheck = checkActionContinue();
                    break;
                case 6:
                    System.out.println("Nhập vào id của sinh viên muốn xem lịch sử học");
                    int id = Integer.parseInt(scanner.nextLine());
                    showListPoint(pointService.findClassStudent(id));
                    actionCheck = checkActionContinue();
                    break;
                case 7:
                    actionCheck= true;
                    actionCheck = checkActionContinue();
                    break;
            }
        } while (!actionCheck);
        if(actionCheck){
            Menu menu = new Menu();
            menu.menuView();
        }
    }

    private void showListStudent(){
        boolean checkAction = false;
        do{
            try{
                System.out.println("Nhập Module muốn xem danh sách điểm");
                int idCourse = Integer.parseInt(scanner.nextLine());
                showListPoint(pointService.findStudentByIDAndModule(idCourse));
                checkAction = false;
            }catch (NumberFormatException e){
                System.out.println("nhập sai định dạng vui lòng nhập lại");
                checkAction = true;
            }
        }while (checkAction);
    }

    private void showListStudentPass() {
        boolean checkAction = false;
        do {
            try{
                System.out.println("Nhập vào Module muốn xem danh sách");
                moduleView.showListCourse(courseService.findAllCourse());
                int idCourse = Integer.parseInt(scanner.nextLine());
                showListPoint(pointService.findListPointByIsPass(EPass.PASS,idCourse));
                checkAction = checkAgaint();
            }catch (NumberFormatException e){
                System.out.println("Nhập sai định dạng vui lòng nhập lại");
                checkAction = true;
            }
        }while (checkAction);
    }

    private void showListPointStudentByClass() {
        boolean checkAction = false;
        do {
            try{
                System.out.println("Nhập Module muốn xem danh sách điểm");
                moduleView.showListCourse(courseService.findAllCourse());
                int idCourse = Integer.parseInt(scanner.nextLine());
                System.out.println("Chọn Lớp muốn xem danh sách điểm");
                classView.showListClass(classService.findAllEClass());
                int idEClass = Integer.parseInt(scanner.nextLine());
                showListPoint(pointService.findStudentByClass(idEClass,idCourse));
                checkAction = checkAgaint();
            }catch (NumberFormatException e){
                System.out.println("Nhập sai định dạng vui lòng nhập lại");
                checkAction = true;
            }
        }while (checkAction);
    }

    private void deleteStudentPoint() {
        boolean checkAction = false;
        do{
            try{
                System.out.println("chọn Module");
                moduleView.showListCourse(courseService.findAllCourse());
                int idCourse = Integer.parseInt(scanner.nextLine());
                System.out.println("Nhập vào ID của sinh viên muốn khỏi danh sách điểm");
                int idStudent = Integer.parseInt(scanner.nextLine());
                pointService.deletePointStudent(idStudent,idCourse);
                System.out.println("Đã xoá thành công");
                showListPoint(pointService.findStudentByIDAndModule(idCourse));
                checkAction = checkAgaint();
            }catch (NumberFormatException E){
                System.out.println("nhập sai định dạng vui lòng nhập lại");
                checkAction = true;
            }
        }while (checkAction);
    }



    // update lai
    private void showCreateStudentPoint() {
        inputPoint();
    }
    private void inputPoint() {
        boolean flag = false;
        do {
            try{
                    System.out.println("chọn Module");
                    moduleView.showListCourse(courseService.findAllCourse());
                    int idCourse = Integer.parseInt(scanner.nextLine());
                    showListPoint(pointService.findStudentByIDAndModule(idCourse));
                    System.out.println("Nhập vào ID muốn nhập điểm");
                    int idStudent = Integer.parseInt(scanner.nextLine());
                    Point point = pointService.checkSTUDING(idStudent,idCourse);
                    if (studentService.isStudentExist(idStudent) && point != null){
                        System.out.println("Nhập Điểm Thực Hành");
                        point.setPointTH( Float.parseFloat(scanner.nextLine()));
                        System.out.println("Nhập Điểm Lý Thuyết");
                        point.setPointLT( Float.parseFloat(scanner.nextLine()));
                        System.out.println("Nhập Điểm CaseStudy");
                        point.setCaseStudyPoint( Float.parseFloat(scanner.nextLine()));
                        System.out.println("Nhập Điểm Phỏng Vấn");
                        point.setinterviewPoint( Float.parseFloat(scanner.nextLine()));
                        point.setPointAVG((double) ((point.getPointLT() + point.getPointTH()
                                + point.getinterviewPoint() + point.getCaseStudyPoint()) / 4));
                        EPass isPass;
                        if (point.getPointAVG() > 6.0) {
                            isPass = EPass.PASS;
                        } else {
                            isPass = EPass.FAIL;
                        }
                        point.setIsPass(isPass);
                        //int pointID, int idStudent, float pointTH, float pointLT, float caseStudyPoint, float interviewPoint,
                        //                 int schedule.csv, double pointAVG, EPass isPass
                        pointService.editStudentPoints(point, idCourse);
                        pointService.checkIsPass(point);
                        showListPoint(pointService.findAllStudentPoint(idCourse));
                        flag = checkAgaint();
                    }
                else{
                    System.out.println("Sinh viên này không có trong danh sánh của codegym");
                    flag = true;

                }
            } catch (Exception e){
                System.out.println("nhập sai định dạng vui lòng nhập lại");
                flag = true;
            }
        } while(flag);
    }


    private void showListPoint(List<Point> allPoint) {
        //int idStudent, EClass eClasss, float pointTH, float pointLT //
        System.out.println("+------------+---------------------------+------------+----------+---------------+---------------+-----------------+-----------------+---------------+---------------+");
        System.out.printf("| %-10s | %-25s | %-10s | %-8s | %-13s | %-13s | %-15s | %-15s | %-13s | %-13s |\n",
                "ID", "Name", "Class", "Module", "pointTH", "pointLT", "pointCaseStudy", "pointInterview", "pointAVG", "status");
        System.out.println("+------------+---------------------------+------------+----------+---------------+---------------+-----------------+-----------------+---------------+---------------+");
        for (Point p : allPoint) {
            System.out.printf("| %-10s | %-25s | %-10s | %-8s | %-13s | %-13s | %-15s | %-15s | %-13s | %-13s |\n", p.getIdStudent()
                    , studentService.findStudentByID(p.getIdStudent()).getName()
                    , classService.findClassByID(p.getIdEClass()).getName()
                    , courseService.findCourseByID(p.getIdCourse()).getName()
                    , p.getPointTH(), p.getPointLT()
                    , p.getCaseStudyPoint(), p.getinterviewPoint(), p.getPointAVG(), p.getIsPass());
            System.out.println("+------------+---------------------------+------------+----------+---------------+---------------+-----------------+-----------------+---------------+---------------+");
        }
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



