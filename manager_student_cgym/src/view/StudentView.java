package view;

import model.*;
import service.ClassService;
import service.CourseService;
import service.PointService;
import service.StudentService;
import utils.DateUtils;
import utils.ValidateUtils;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final static String PATH = "./src/data/student.csv";
    private Scanner scanner = new Scanner(System.in);
    private StudentService studentService;
    private ClassService classService;

    private PointService pointService;
    private CourseService courseService;
    private ClassView classView;

    private ModuleView moduleView;

    public StudentView() {
        studentService = new StudentService();
        classService = new ClassService();
        pointService = new PointService();
        courseService = new CourseService();
        classView = new ClassView();
        moduleView = new ModuleView();
    }

    public void menuStudent() {
        System.out.println("                    ╔════════════════════════════════════════════════════╗");
        System.out.println("                    ║                  MENU CHƯƠNG TRÌNH                 ║");
        System.out.println("                    ╠════════════════════════════════════════════════════╣");
        System.out.println("                    ║        1: Xem danh sách sinh viên                  ║");
        System.out.println("                    ║        2: Thêm sinh viên                           ║");
        System.out.println("                    ║        3: Xóa sinh viên                            ║");
        System.out.println("                    ║        4: Sửa thông tin sinh viên                  ║");
        System.out.println("                    ║        5: Tìm sinh viên theo tên                   ║");
        System.out.println("                    ║        6: Tìm kiếm sinh viên theo ID               ║");
        System.out.println("                    ║        7: Quay lại Menu                            ║");
        System.out.println("                    ╚════════════════════════════════════════════════════╝");
        System.out.print("Vui lòng chọn một lựa chọn: ");
    }

    public void launcherStudent() {
        boolean checkAction = false;
        do {
            menuStudent();
            try {
                int actionMenu = Integer.parseInt(scanner.nextLine());
                switch (actionMenu) {
                    case 1:
                        showlistStudent();
                        checkAction = checkActionContinue();
                        break;
                    case 2:
                        showCreateStudent();
                        checkAction = checkActionContinue();
                        break;
                    case 3:
                        inputDelete();
                        checkAction = checkActionContinue();
                        break;
                    case 4:
                        showStudent(studentService.findAllStudent());
                        updateStudent();
                        checkAction = checkActionContinue();
                        break;
                    case 5:
                        findNameStudent();
                        checkAction = checkActionContinue();
                        break;
                    case 6:
                        findStudentById();
                        checkAction = checkActionContinue();
                        break;
                    case 7:
                        checkAction = true;
                        break;

                }
            } catch (NumberFormatException E) {
                System.out.println("thông tin chọn không đúng vui lòng chọn lại");
                checkAction = true;
            }

        } while (!checkAction);
        if (checkAction) {
            Menu menu = new Menu();
            menu.menuView();
        }
    }

    public void showlistStudent() {
        boolean checkAction = true;
        int idCourse = 0;
        do {
            System.out.println("Nhập vào ID Module muốn xem danh sách");
            moduleView.showListCourse(courseService.findAllCourse());
            try {
                idCourse = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID không đúng định dạng vui lòng nhập lại!!!!");
                continue;
            }
            if(idCourse <= 0 || idCourse > 6) {
                System.out.println("chỉ được chọn Module 1-5");
                checkAction = true;
            }
            else {
                checkAction = false;
            }

        } while (checkAction);
        showStudent(studentService.findStudentList(idCourse));
        checkAction = checkAgaint();
    }


    private void showStudent(List<Student> listStudent) {
        //int id, String name, String gender, Date bod, String address, EClass eClass //
        System.out.println("+---------------+------------------------------+------------+---------------+----------------------+----------------------+---------------+");
        System.out.printf("| %-13s | %-28s | %-10s | %-13s | %-20s | %-20s | %-13s |\n",
                "ID", "Name", "Gender", "Birthday", "Address", "Class", "Module");
        System.out.println("+---------------+------------------------------+------------+---------------+----------------------+----------------------+---------------+");
        for (Student p : listStudent) {
            //int id, String name, byte age, String address, EBlock eBlock, ERole eRole// {
            System.out.printf("| %-13s | %-28s | %-10s | %-13s | %-20s | %-20s | %-13s |\n", p.getId(), p.getName(),
                    p.geteGender().getName(), DateUtils.format(p.getBod()), p.getAddress(),
                    classService.findClassByID(p.getIdEClass()).getName(), p.getIdCourse());
            System.out.println("+---------------+------------------------------+------------+---------------+----------------------+----------------------+---------------+");
        }
    }

    private void findStudentById() {
        System.out.println("Nhập vào ID của sinh viên muốn tìm");
        int idStudent = Integer.parseInt(scanner.nextLine());
        Student student = studentService.findStudentByID(idStudent);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("không có sinh viên");
        }
    }

    private void findNameStudent() {
        inputNameStudent();

    }

    private void inputNameStudent() {
        boolean checkAction = false;
        do {
            try {
                System.out.println("Nhập vào Họ tên muốn tìm");
                String input = scanner.nextLine();
                Student student = studentService.findStudentByName(input);
                if (student != null) {
                    System.out.println(student);
                    checkAction = checkAgaint();
                } else {
                    System.out.println("Không có sinh viên ");
                    checkAction = checkAgaint();
                }
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng vui lòng nhập lại");
                checkAction = false;
            }
        } while (checkAction);
    }

    public void showCreateStudent() {
        System.out.println("Thêm sinh viên mới");
        boolean checkID = true;
        int id = 0;
        do {
            System.out.println("Nhập ID của sinh viên");
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException E) {
                System.out.println("nhập sai định dạng vui lòng nhập lại");
                continue;
            }
            if (id <= 0) {
                System.out.println("ID phải lớn hơn 0");
                checkID = true;
            } else {
                if(studentService.isStudentExist(id)){
                    System.out.println("ID này đã có trong danh sách vui lòng nhập ID khác");
                    checkID = true;
                }else {
                    checkID = false;
                }
            }
        } while (checkID);
        String name = null;
        boolean checkName = false;
        do {

            System.out.println("Họ và Tên");
            name = scanner.nextLine();
            if (ValidateUtils.isNameValid(name)) {
                checkName = false;
            } else {
                System.out.println("họ tên không hợp lệ vui lòng nhập lại");
                checkName = true;
            }
        } while (checkName);
        int idEGender = 0;
        boolean checkGender = true;
        do {
            System.out.println("Chọn giới tính");
            for (EGender eGender : EGender.values()) {
                System.out.println("+---------+--------------+");
                System.out.printf("| %-7s | %-12s |\n", eGender.getId(), eGender.getName());
                System.out.println("+---------+--------------+");
            }
                try {
                    idEGender = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("nhập ID phải là số ");
                    continue;
                }
                if(idEGender != 1 && idEGender != 2){
                    System.out.println("chỉ được chọn 1 trong 2 giới tính");
                    checkGender = true;
                }
                else{
                    checkGender = false;
                }

        } while (checkGender);
        EGender eGender = EGender.getEGenderById(idEGender);
        Date bod = DateUtils.parse(DateUtils.getDateOfBirth());
        System.out.println("Nhập vào địa chỉ");
        String address = scanner.nextLine();
        String addressFormat = ValidateUtils.parseCommaToChar(address);
        int idEClass = 0;
        boolean checkidEClass = true;
        do {
            System.out.println("Chọn Lớp Học");
            classView.showListClass(classService.findAllEClass());

            try{
                idEClass = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("nhập ID sai định dạng vui lòng nhập lại");
                continue;
            }
            if (idEClass<=0 && idEClass>5){
                System.out.println("nên chọn 1-5");
                checkidEClass = true;
            }
            else {
                checkidEClass = false;
            }
        }while (checkidEClass);
        Student student = new Student(id, name, eGender, bod, address, idEClass, 1);
        Point point = new Point(id, idEClass, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0d, EPass.STUDING);
        studentService.addStudent(student);
        pointService.addStudentPoint(point);
        showStudent(studentService.findAllStudent());


//            }catch (Exception e){
//                System.out.println("nhập sai định dạng vui lòng nhập lại");
//                checkAction = true;
//            }
        //int id, String name, String gender, Date bod, String address, EClass eClass //


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

    private void updateStudent() {
        Student student = inputIdStudent();
        if (student != null) {
            boolean checkActionEdit;
            do {
                //int id, String name, EGender gender, Date bod, String address, EClass eClass //
                checkActionEdit = false;
                System.out.println("Bạn muốn sửa thông tin gì: ");
                System.out.println("1.Họ tên");
                System.out.println("2.Giới tính");
                System.out.println("3.Ngày Sinh");
                System.out.println("4.Địa chỉ");
                System.out.println("5.Exits");
                int actionEdit = Integer.parseInt(scanner.nextLine());
                switch (actionEdit) {
                    case 1:
                        inputNameStudent(student);
                        break;
                    case 2:
                        inputGenderStudent(student);
                        break;
                    case 3:
                        inputBODStudent(student);
                        break;
                    case 4:
                        inputAddressStudent(student);
                        break;
                    case 5:
                        checkActionEdit = false;
                        break;
                    default:
                        System.out.println("Nhập sai. Vui lòng nhập lại ");
                        checkActionEdit = true;
                }
            } while (checkActionEdit);
        }
    }

    private void inputClassStudent(Student student) {
        System.out.println("Thông tin sinh viên: ");
        System.out.println(student);

        System.out.println("Chọn lớp mới: ");
        System.out.println(classService.findAllEClass());
        int idEClass = Integer.parseInt(scanner.nextLine());
        student.setIdEClass(idEClass);
        System.out.println("Nhập địa chỉ mới: ");
        String address = scanner.nextLine();

        student.setAddress(address);
        studentService.editStudent(student.getId(), student);

        System.out.println("Sửa thành công: ");
        System.out.println(student);
    }

    private void inputAddressStudent(Student student) {
        System.out.println("Thông tin sinh viên: ");
        System.out.println(student);


        studentService.editStudent(student.getId(), student);

        System.out.println("Sửa thành công: ");
        System.out.println(student);
    }

    private void inputBODStudent(Student student) {
        System.out.println("Thông tin sinh viên: ");
        System.out.println(student);

        System.out.println("Nhập ngày sinh mới: ");
        System.out.println("Ngày sinh(Nhập theo định dạng dd-MM-yyyy)");
        Date bod = DateUtils.parse(scanner.nextLine());

        student.setBod(bod);
        studentService.editStudent(student.getId(), student);

        System.out.println("Sửa thành công: ");
        System.out.println(student);
    }

    private void inputGenderStudent(Student student) {
        System.out.println("Thông tin sinh viên: ");
        System.out.println(student);

        System.out.println("Chọn lại giới tính: ");
        for (EGender eGender : EGender.values()) {
            System.out.printf("chọn %-5s %-5s \n", eGender.getId(), eGender.getName());
        }
        int idEGender = Integer.parseInt(scanner.nextLine());
        EGender eGender = EGender.getEGenderById(idEGender);

        student.seteGender(eGender);
        studentService.editStudent(student.getId(), student);

        System.out.println("Sửa thành công: ");
        System.out.println(student);
    }

    private void inputNameStudent(Student student) {
        System.out.println("Thông tin sinh viên: ");
        System.out.println(student);

        System.out.println("Nhập Họ tên mới: ");
        String name = scanner.nextLine();

        student.setName(name);
        studentService.editStudent(student.getId(), student);

        System.out.println("Sửa thành công: ");
        System.out.println(student);
    }

    private Student inputIdStudent() {
        Student student = null;
        boolean checkEditStudentValid = false;
        do {
            try {
                System.out.println("Nhập ID sinh viên bạn muốn sửa");
                int idStudent = Integer.parseInt(scanner.nextLine());
                student = studentService.findStudentByID(idStudent);
                if (student == null) {
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
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Id không đúng định dạng vui lòng nhập lại");
                checkEditStudentValid = true;
            }
        } while (checkEditStudentValid);
        return student;
    }

    private void removeStudent() {
        showStudent(studentService.findAllStudent());
        int idStudent;
        boolean checkId = true;
        do{
            System.out.println("Nhập vào ID của sinh viên muốn xoá");
            try{
                idStudent = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("ID không đúng định dạng vui lòng nhập lại");
                continue;
            }
            if(idStudent <= 0) {
                System.out.println("ID nhập vào phải lớn hơn 0");
                checkId = true;
            }
            else{
                if(studentService.isStudentExist(idStudent)){
                studentService.deleteStudent(idStudent);
                System.out.println("Đã xoá thành công");
                checkId = false;
                }else {
                    System.out.println("không có sinh viên muốn xoá vui lòng nhập lại ID");
                    checkId =checkAgaint();
                }
            }
        }while (checkId);
        showStudent(studentService.findAllStudent());
    }
    public void inputDelete(){
        int input = 0;
        boolean checkInput = true;
        do{
            System.out.println("chọn chức năng xoá");
            System.out.println("1.xoá sinh viên khỏi danh sách sinh viên");
            System.out.println("2.xoá sinh viên khỏi danh sách sinh viên và bảng điểm ");
            try{
                 input = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("chọn chức năng sai vui lòng chọn lại");
                continue;
            }
            if(input <= 0 || input > 2){
                System.out.println("chỉ được chọn 1 trong 2 chức năng trên");
                checkInput = true;
            }
            else {
                checkInput = false;
            }
        }while (checkInput);
        switch (input){
            case 1:
                removeStudent();
                checkInput = false;
                break;
            case 2:
                removeStudentAllFile();
                checkInput = false;
                break;
        }
    }
    private void removeStudentAllFile() {
        showStudent(studentService.findAllStudent());
        int idStudent;
        boolean checkId = true;
        do{
            System.out.println("Nhập vào ID của sinh viên muốn xoá");
            try{
                idStudent = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("ID không đúng định dạng vui lòng nhập lại");
                continue;
            }
            if(idStudent <= 0) {
                System.out.println("ID nhập vào phải lớn hơn 0");
                checkId = true;
            }
            else{
                if(studentService.isStudentExist(idStudent)){
                    studentService.deleteStudentAllList(idStudent);
                    System.out.println("Đã xoá thành công");
                    checkId = false;
                }else {
                    System.out.println("không có sinh viên muốn xoá vui lòng nhập lại ID");
                    checkId =checkAgaint();
                }
            }
        }while (checkId);
        showStudent(studentService.findAllStudent());
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

