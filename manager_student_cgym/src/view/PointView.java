package view;

import model.*;
import service.*;

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

    public void menuPoint() {
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
                    break;
                case 2:
                    showCreateStudentPoint();
                    break;
                case 3:
                    deleteStudentPoint();
                    break;
                case 4:
                    showListPointStudentByClass();
                    break;
                case 5:
                    showListStudentPass();
                    break;
                case 6:
                    showHistory();
                    break;
                case 7:
                    actionCheck = true;
                    break;
                default:
                    System.out.println("chọn chức năng không đúng vui lòng chọn lại");
                    actionCheck = true;
                    break;
            }
        } while (!actionCheck);
        if (actionCheck) {
            AdminView adminView = new AdminView();
            adminView.menuView();
        }
    }

    public void showHistory(){
        int id = 0;
        boolean checkID = true;
        do {
            System.out.println("Nhập vào id của sinh viên muốn xem lịch sử học");
            try{
                 id = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("ID định dạng không đúng vui lòng nhập lại");
                continue;
            }
            if(id<=0){
                System.out.println("ID phải lớn hơn không");
                checkID= true;
            }
            else{
                if(studentService.isStudentExist(id)){
                    showListPoint(pointService.findClassStudent(id));
                    checkID = checkAgaint();
                }
                else{
                    System.out.println("Không có sinh viên này trong danh sách");
                    checkID = checkAgaint();
                }
            }
        }while (checkID);
    }

    private void showListStudent() {
        boolean checkAction = true;
        int idCourse = 0;
        do {
            System.out.println("Nhập Module muốn xem danh sách điểm");
            moduleView.showListCourse(courseService.findAllCourse());
            try {
                idCourse = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("nhập sai định dạng vui lòng nhập lại");
                continue;
            }
            if (idCourse <= 0 && idCourse > 6) {
                System.out.println("không có module trên vui lòng chọn module có trong chương trình dạy ");
                checkAction = true;
            } else {
                checkAction = false;
            }
        } while (checkAction);
        showListPoint(pointService.findStudentByIDAndModule(idCourse));
    }

    private void showListStudentPass() {
        boolean checkAction = true;
        int idCourse = 0;
        do {
            System.out.println("Nhập vào Module muốn xem danh sách");
            moduleView.showListCourse(courseService.findAllCourse());
            try {
                idCourse = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Nhập sai định dạng vui lòng nhập lại");
                continue;
            }
            if (idCourse <= 0 && idCourse > 6) {
                System.out.println("không có module trên vui lòng chọn module có trong chương trình dạy ");
                checkAction = true;
            } else {
                checkAction = false;
            }
        } while (checkAction);
        showListPoint(pointService.findListPointByIsPass(EPass.PASS, idCourse));
    }

    private void showListPointStudentByClass() {
        boolean checkModule = true;
        boolean checkClass = true;
        int idCourse = 0;
        int idEClass = 0;
        do {
            System.out.println("Nhập Module muốn xem danh sách điểm");
            moduleView.showListCourse(courseService.findAllCourse());
            try {
                idCourse = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("nhập sai định dạng vui lòng nhập lại");
                continue;
            }
            if (idCourse <= 0 && idCourse > 6) {
                System.out.println("không có module trên vui lòng chọn module có trong chương trình dạy ");
                checkModule = true;
            } else {
                checkModule = false;
            }
        } while (checkModule);
        do {
            System.out.println("Chọn Lớp muốn xem danh sách điểm");
            try {
                idEClass = Integer.parseInt(scanner.nextLine());
                classView.showListClass(classService.findAllEClass());
            } catch (NumberFormatException e) {
                System.out.println("nhập sai định dạng vui lòng nhập lại");
                continue;
            }
            if (idEClass <= 0 && idEClass > 10) {
                System.out.println("không có class trên vui lòng chọn class có trong chương trình dạy ");
                checkClass = true;
            } else {
                checkClass = false;
            }
        } while (checkClass);
        showListPoint(pointService.findStudentByClass(idEClass, idCourse));

    }

    private void deleteStudentPoint() {
        boolean checkModule = true;
        boolean checkIdStudent = true;
        int idCourse = 0;
        int idStudent = 0;
        do {
            System.out.println("Chọn Module ");
            moduleView.showListCourse(courseService.findAllCourse());
            try {
                idCourse = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("nhập sai định dạng vui lòng nhập lại");
                continue;
            }
            if (idCourse <= 0 && idCourse > 6) {
                System.out.println("không có module trên vui lòng chọn module có trong chương trình dạy ");
                checkModule = true;
            } else {
                checkModule = false;
            }
        } while (checkModule);
        do {
            showListPoint(pointService.findStudentByIDAndModule(idCourse));
            System.out.println("Chọn ID muốn xoá");
            try {
                idStudent = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("nhập sai định dạng vui lòng nhập lại");
                continue;
            }
            if (studentService.isStudentExist(idStudent)) {
                checkIdStudent = false;
            } else {
                System.out.println("không có sinh viên này ");
                checkIdStudent = true;
            }
        } while (checkIdStudent);
        pointService.deletePointStudent(idStudent, idCourse);
        System.out.println("Đã xoá thành công");
        showListPoint(pointService.findStudentByIDAndModule(idCourse));
    }


    // update lai
    private void showCreateStudentPoint() {
        inputPoint();
    }

    private void inputPoint() {
        boolean flag = false;
        do{
            int idCourse = 0;
            int idStudent = 0;
            boolean checkid = true;
            boolean checkIdStudent = true;
            do {
                System.out.println("chọn Module");
                moduleView.showListCourse(courseService.findAllCourse());
                try {
                    idCourse = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Định dạng không đúng vui lòng nhập lại");
                    continue;
                }
                if (idCourse <= 0 && idCourse > 6) {
                    System.out.println("chỉ được chọn các module có trong danh sách");
                    checkid = true;
                } else {
                    checkid = false;
                }
            } while (checkid);
            showListPoint(pointService.findStudentByIDAndModule(idCourse));
            do {
                System.out.println("Nhập vào ID muốn nhập điểm");
                try {
                    idStudent = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Định dạng không đúng vui lòng nhập lại");
                    continue;
                }
                if (idStudent <= 0) {
                    System.out.println("ID nhập phải bắt đầu từ 1 ");
                    checkIdStudent = true;
                } else {
                    checkIdStudent = false;
                }
            } while (checkIdStudent);
            Point point = pointService.checkSTUDING(idStudent, idCourse);
            if (studentService.isStudentExist(idStudent) && point != null) {
                boolean checkPointTH = true;
                boolean checkPointLT = true;
                boolean checkPointCase = true;
                boolean checkPointInterview = true;
                do{
                    System.out.println("Nhập Điểm Thực Hành");
                    try{
                        point.setPointTH(Float.parseFloat(scanner.nextLine()));
                    }catch (Exception e){
                        System.out.println("nhập điểm sai định dạng vui lòng nhập lại");
                        continue;
                    }
                    if(Float.parseFloat(scanner.nextLine()) < 0 && Float.parseFloat(scanner.nextLine()) > 10){
                        System.out.println("nhập điểm quá với giới hạn điểm (0-10)");
                        checkPointTH = true;
                    }
                    else{
                        checkPointTH = false;
                    }
                }while (checkPointTH);
                do{
                    System.out.println("Nhập Điểm Lý Thuyết");
                    try{
                        point.setPointLT(Float.parseFloat(scanner.nextLine()));
                    }catch (Exception e){
                        System.out.println("nhập điểm sai định dạng vui lòng nhập lại");
                        continue;
                    }
                    if(Float.parseFloat(scanner.nextLine()) < 0 && Float.parseFloat(scanner.nextLine()) > 10){
                        System.out.println("nhập điểm quá với giới hạn điểm (0-10)");
                        checkPointLT = true;
                    }
                    else{
                        checkPointLT = false;
                    }
                }while (checkPointLT);
                do{
                    System.out.println("Nhập Điểm CaseStudy");
                    try{
                        point.setCaseStudyPoint(Float.parseFloat(scanner.nextLine()));
                    }catch (Exception e){
                        System.out.println("nhập điểm sai định dạng vui lòng nhập lại");
                        continue;
                    }
                    if(Float.parseFloat(scanner.nextLine()) < 0 && Float.parseFloat(scanner.nextLine()) > 10){
                        System.out.println("nhập điểm quá với giới hạn điểm (0-10)");
                        checkPointCase = true;
                    }
                    else{
                        checkPointCase = false;
                    }
                }while (checkPointCase);
                do{
                    System.out.println("Nhập Điểm Phỏng Vấn");
                    try{
                        point.setinterviewPoint(Float.parseFloat(scanner.nextLine()));
                    }catch (Exception e){
                        System.out.println("nhập điểm sai định dạng vui lòng nhập lại");
                        continue;
                    }
                    if(Float.parseFloat(scanner.nextLine()) < 0 && Float.parseFloat(scanner.nextLine()) > 10){
                        System.out.println("nhập điểm quá với giới hạn điểm (0-10)");
                        checkPointInterview = true;
                    }
                    else{
                        checkPointInterview = false;
                    }
                }while (checkPointInterview);
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
            } else {
                System.out.println("Sinh viên này không có trong danh sánh của codegym");
                flag = true;
            }
        }while (flag);

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



