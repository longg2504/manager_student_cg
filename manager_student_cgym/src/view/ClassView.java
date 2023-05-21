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
            System.out.println("                ╔════════════════════════════════════════╗");
            System.out.println("                ║             Menu chương trình          ║");
            System.out.println("                ╠════════════════════════════════════════╣");
            System.out.println("                ║  1.Xem danh sách Lớp                   ║");
            System.out.println("                ║  2.Thêm lớp học                        ║");
            System.out.println("                ║  3.Xóa lớp Học                         ║");
            System.out.println("                ║  4.Sửa Lớp Học                         ║");
            System.out.println("                ║  5.Quay lại Menu                       ║");
            System.out.println("                ╚════════════════════════════════════════╝");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showListClass(classService.findAllEClass());
                    actionCheck = checkActionContinue();
                    break;
                case 2:
                    showCreateClass();
                    actionCheck = checkActionContinue();
                    break;
                case 3:
                    removeClass();
                    actionCheck = checkActionContinue();
                    break;
                case 4:
                    editClass();
                    actionCheck = checkActionContinue();
                    break;
                case 5:
                    actionCheck= true;
                    actionCheck = checkActionContinue();
                    break;
            }
        } while (!actionCheck);
        if(actionCheck){
            AdminView adminView = new AdminView();
            adminView.menuView();
        }
    }

    private void editClass() {
        showListClass(classService.findAllEClass());
        int idEClass = 0;
        boolean checkAction = true;
        do {
            System.out.println("Nhập ID muốn sửa ");
            try {
                idEClass = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID nhập vào không đúng");
                continue;
            }
            if (idEClass <= 0) {
                System.out.println("ID nhập vào phải lớn hơn 0");
                checkAction = true;
            }
            else {
                EClass eClass = classService.findClassByID(idEClass);
                System.out.println("Nhập tên lớp muốn sửa");
                String name = scanner.nextLine();
                eClass.setName(name);

                classService.editEClass(eClass.getId(),eClass);
                System.out.println("đã sửa thành công thành công");
                showListClass(classService.findAllEClass());
                checkAction = checkAgaint();
            }
        }while (checkAction);
    }

    private void showCreateClass() {
        int idEClass = 0;
        boolean checkAction = true;
        do {
            System.out.println("Nhập ID ");
            try{
                idEClass = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("ID nhập vào không đúng");
                continue;
            }
            if(idEClass <= 0 ){
                System.out.println("ID nhập vào phải lớn hơn 0");
                checkAction = true;
            }else {
                if(classService.existEClass(idEClass)){
                    System.out.println("lớp này đã có vui lòng nhập lại");
                    checkAction = true;
                }
                else{
                    System.out.println("Nhập Tên lớp muốn tạo");
                    String name = scanner.nextLine();
                    EClass eClass = new EClass(idEClass , name);
                    classService.addClass(eClass);
                    System.out.println("Đã tạo thêm lớp thành công");
                    showListClass(classService.findAllEClass());
                    checkAction = checkAgaint();
                }
            }
        }while (checkAction);

    }

    public void removeClass() {
        showListClass(classService.findAllEClass());
        int idEClass = 0;
        boolean checkAction = true;
        do {
            System.out.println("Nhập ID muốn xoá ");
            try {
                idEClass = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID nhập vào không đúng");
                continue;
            }
            if (idEClass <= 0) {
                System.out.println("ID nhập vào phải lớn hơn 0");
                checkAction = true;
            }
            else {
                classService.deleteEClass(idEClass);
                System.out.println("đã xoá thành công");
                showListClass(classService.findAllEClass());
                checkAction = checkAgaint();
            }
        }while (checkAction);
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
