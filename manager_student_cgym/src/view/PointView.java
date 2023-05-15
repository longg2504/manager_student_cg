package view;

import model.*;
import service.ClassService;
import service.PointService;
import service.ScheduleService;
import service.StudentService;
import utils.DateUtils;

import java.util.List;
import java.util.Scanner;

public class PointView {
    private final static String PATH = "./src/data/point.csv";
    private Scanner scanner = new Scanner(System.in);
    private PointService pointService;

    private StudentService studentService;

    private ScheduleService scheduleService;

    private ClassService classService;

    public PointView() {
        pointService = new PointService();
        studentService = new StudentService();
        scheduleService = new ScheduleService();
        classService = new ClassService();
    }

    public void launcherPoint(){
        do{
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║             Menu chương trình          ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  1.Xem danh sách điểm sinh viên        ║");
            System.out.println("║  2.Thêm điểm sinh viên                 ║");
            System.out.println("║  3.Xóa điểm sinh viên                  ║");
            System.out.println("║  4.Sửa điểm sinh viên                  ║");
            System.out.println("║  5.Hiển thị danh sách điểm theo lớp    ║");
            System.out.println("║  6.Hiển thị điểm của sinh viên theo ID ║");
            System.out.println("║  7.Hiển thị danh sách sinh viên đã đạt ║");
            System.out.println("╚════════════════════════════════════════╝");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showListPoint(pointService.findAllStudentPoint());
                    break;
                case 2:
                    showCreateStudentPoint();
                    break;
                case 3:
                    deleteStudentPoint();
                    break;
                case 4:
                    showListPoint(pointService.findAllStudentPoint());
                    updateStudentPoint();
                    break;
                case 5:
                    showListPointStudentByClass();
                    break;
                case 6:
                    showStudentPointByID();
                    break;
                case 7:
                    showListStudentPass();
                    break;

            }
        }while (true);

    }

    private void showListStudentPass() {
       showListPoint(pointService.findListPointByIsPass(EPass.PASS));
    }

    private void showStudentPointByID() {
        System.out.println("Nhập ID của sinh viên muốn xem bảng điểm");
        int idStudent = Integer.parseInt(scanner.nextLine());
        showListPoint(pointService.findPointById(idStudent));

    }

    private void showListPointStudentByClass() {
        System.out.println("Chọn Lớp muốn xem danh sách điểm");
        System.out.println(classService.findAllEClass());
        int idEClass = Integer.parseInt(scanner.nextLine());
        showListPoint(pointService.findStudentByClass(idEClass));


        }

    private void updateStudentPoint() {
        Point point = inputIdStudent();
        if(point != null){
            boolean checkActionEdit;
            do{
                //int id, String name, EGender gender, Date bod, String address, EClass eClass //
                checkActionEdit = false;
                System.out.println("Bạn muốn sửa thông tin gì: ");
                System.out.println("1.Điểm Thực Hành");
                System.out.println("2.Điểm Lý Thuyết");
                System.out.println("3.Điểm Case Study");
                System.out.println("4.Điểm Interview");
                System.out.println("0.Exits");
                int actionEdit = Integer.parseInt(scanner.nextLine());
                switch (actionEdit) {
                    case 1:
                        inputPointTH(point);
                        break;
                    case 2:
                        inputPointLT(point);
                        break;
                    case 3:
                        inputPointCaseStudy(point);
                        break;
                    case 4:
                        inputPointInterview(point);
                        break;
                    case 0:
                        checkActionEdit = false;
                        break;
                    default:
                        System.out.println("Nhập sai. Vui lòng nhập lại ");
                        checkActionEdit = true;
                }


            }while(checkActionEdit);
        }
    }


    private void inputPointLT(Point point) {
        System.out.println("Thông tin bảng điểm sinh viên: ");
        System.out.println(point);

        System.out.println("Nhập điểm lý thuyết mới: ");
        Float pointLT = Float.parseFloat(scanner.nextLine());

        point.setPointLT(pointLT);
        pointService.editStudentPoint(point.getIdStudent());

        System.out.println("Sửa thành công: ");
        System.out.println(point);
    }

    private void inputPointTH(Point point) {
        System.out.println("Thông tin bảng điểm sinh viên: ");
        System.out.println(point);

        System.out.println("Nhập điểm thực hành mới: ");
        Float pointTH = Float.parseFloat(scanner.nextLine());

        point.setPointTH(pointTH);
        pointService.editStudentPoint(point.getIdStudent());

        System.out.println("Sửa thành công: ");
        System.out.println(point);
    }
    private void inputPointInterview(Point point) {
        System.out.println("Thông tin bảng điểm sinh viên: ");
        System.out.println(point);

        System.out.println("Nhập điểm phỏng vấn mới: ");
        Float pointInterview = Float.parseFloat(scanner.nextLine());

        point.setinterviewPoint(pointInterview);
        pointService.editStudentPoint(point.getIdStudent());

        System.out.println("Sửa thành công: ");
        System.out.println(point);
    }
    private void inputPointCaseStudy(Point point) {
        System.out.println("Thông tin bảng điểm sinh viên: ");
        System.out.println(point);

        System.out.println("Nhập điểm Case Study mới: ");
        Float PointCaseStudy = Float.parseFloat(scanner.nextLine());

        point.setCaseStudyPoint(PointCaseStudy);
        pointService.editStudentPoint(point.getIdStudent());

        System.out.println("Sửa thành công: ");
        System.out.println(point);
    }

    private Point inputIdStudent() {
        Point point = null;
        boolean checkEditStudentValid = false;
        do{
            try{
                System.out.println("Nhập ID sinh viên bạn muốn sửa bảng điểm");
                int idStudent = Integer.parseInt(scanner.nextLine());
                point = pointService.findPointByIdStudent(idStudent);
                if(point == null){
                    System.out.println("ID sinh viên không hợp lệ");
                    System.out.println("Chọn 1. Nhập lại");
                    System.out.println("Chọn 2. Quay lại");
                    int actionEditId = Integer.parseInt(scanner.nextLine());
                    switch (actionEditId) {
                        case 1:
                            checkEditStudentValid = true;
                            break;
                        case 2:
                            checkEditStudentValid = false;
                            break;
                    }
                }
            }catch (NumberFormatException numberFormatException){
                System.out.println("Id không đúng định dạng vui lòng nhập lại");
                checkEditStudentValid = true;
            }
        }while(checkEditStudentValid);
        return point;
    }


    private void deleteStudentPoint() {
        System.out.println("Nhập vào ID của sinh viên muốn khỏi danh sách điểm");
        int idStudent = Integer.parseInt(scanner.nextLine());
        pointService.deletePointStudent(idStudent);
    }

    private void inputPoint(){
        boolean flag = false;
        do{
            System.out.println("Nhập vào ID muốn thêm điểm");
            int idStudent = Integer.parseInt(scanner.nextLine());
            if(studentService.existStudent(idStudent)){
                int idClass = studentService.findStudentByID(idStudent).getIdEClass();
                System.out.println("Nhập Điểm Thực Hành");
                Float pointTH = Float.parseFloat(scanner.nextLine());
                System.out.println("Nhập Điểm Lý Thuyết");
                Float pointLT = Float.parseFloat(scanner.nextLine());
                System.out.println("Nhập Điểm CaseStudy");
                Float caseStudyPoint = Float.parseFloat(scanner.nextLine());
                System.out.println("Nhập Điểm Phỏng Vấn");
                Float interviewPoint = Float.parseFloat(scanner.nextLine());
                Double pointAVG = (double) ((pointTH+pointLT+caseStudyPoint+interviewPoint)/4);
                EPass isPass;
                if(pointAVG > 6.0){
                    isPass = EPass.PASS;
                }else{
                    isPass = EPass.FAIL;
                }
                //int pointID, int idStudent, float pointTH, float pointLT, float caseStudyPoint, float interviewPoint,
                //                 int schedule.csv, double pointAVG, EPass isPass
                Point point = new Point(idStudent,idClass,pointTH, pointLT,caseStudyPoint,interviewPoint,1,pointAVG,isPass);
                pointService.editStudentPoints(point);
                pointService.checkIsPass(point);
                showListPoint(pointService.findAllStudentPoint());

                flag = false;
            }else{
                System.out.println("Sinh viên này không có trong danh sánh của codegym");
                flag = true;
            }
        }while (flag);
    }
// update lai
    private void showCreateStudentPoint() {
        showListPoint(pointService.findAllStudentPoint());
        inputPoint();
    }




    private void showListPoint(List<Point> allPoint) {
        //int idStudent, EClass eClasss, float pointTH, float pointLT //
        System.out.printf("%-15s %-30s %-10s %-15s %-15s %-15s %-15s %-15s %-15s\n", "ID","Name" ,"Class", "pointTH", "pointLT" , "pointCaseStudy"
                ,"pointInterview","pointAVG","status");
        for (Point p : allPoint) {
                    //int idStudent, EClass eClasss, float pointTH, float pointLT //
                    System.out.printf("%-15s %-30s %-10s %-15s %-15s %-15s %-15s %-15s %-15s\n",p.getIdStudent()
                            , studentService.findStudentByID(p.getIdStudent()).getName()
                            , classService.findClassByID(p.getIdEClass()).getName()
                            , p.getPointTH(),p.getPointLT()
                            ,p.getCaseStudyPoint(),p.getinterviewPoint(),p.getPointAVG(),p.getIsPass());
                }
            }

}


