package view;

import model.EClass;
import model.EGender;
import model.Student;
import service.StudentService;
import utils.DateUtils;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final static String PATH = "./src/data/student.csv";
    private Scanner scanner = new Scanner(System.in);
    private StudentService studentService;

    public StudentView() {
        studentService = new StudentService();
    }

    public void launcherStudent() {
        do{
            System.out.println("Menu chương trình:");
            System.out.println("Nhập 1: Xem danh sách sinh viên");
            System.out.println("Nhập 2: Thêm sinh viên");
            System.out.println("Nhập 3: Xóa sinh viên");
            System.out.println("Nhập 4: Sửa thông tin sinh viên");
            System.out.println("Nhập 5: Tìm sinh viên theo tên");
            System.out.println("Nhập 6: Tìm kiếm sinh viên theo ID");
            System.out.println("Nhập 7: Hiện danh sách sinh viên theo lớp");

            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    showStudent(studentService.findAllStudent());
                    break;
                case 2:
                    showCreateStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    showStudent(studentService.findAllStudent());
                    updateStudent();
                    break;
                case 5:
                    findNameStudent();
                    break;
                case 6:
                    findStudentById();
                    break;
                case 7:
                    showStudentByClass();
                    break;

            }
        }while (true);

    }

    private void showStudentByClass() {
        System.out.println("chọn lớp muốn hiện danh sách sinh viên");
        for (EClass eClass : EClass.values()) {
            System.out.printf("chọn %-5s %-5s \n", eClass.getId(), eClass.getName());
        }
        int idEClass = Integer.parseInt(scanner.nextLine());
        EClass eClass = EClass.getEClassById(idEClass);
        var students = studentService.findStudentByClass(eClass);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void findStudentById() {
        System.out.println("Nhập vào ID của sinh viên muốn tìm");
        int idStudent  = Integer.parseInt(scanner.nextLine());
        Student student = studentService.findStudentByID(idStudent);
        if(student!=null){
            System.out.println(student);
        }else{
            System.out.println("không có sinh viên");
        }
    }

    private void findNameStudent() {
        System.out.println("Nhập vào Họ tên muốn tìm");
        String input = scanner.nextLine();
        Student student = studentService.findStudentByName(input);
        if(student != null){
            System.out.println(student);
        }else{
            System.out.println("Không có sinh viên ");
        }
    }

    private void updateStudent() {
        Student student = inputIdStudent();
        if(student != null){
            boolean checkActionEdit;
            do{
                //int id, String name, EGender gender, Date bod, String address, EClass eClass //
                checkActionEdit = false;
                System.out.println("Bạn muốn sửa thông tin gì: ");
                System.out.println("1.Họ tên");
                System.out.println("2.Giới tính");
                System.out.println("3.Ngày Sinh");
                System.out.println("4.Địa chỉ");
                System.out.println("5.Lớp Học");
                System.out.println("6.Exits");
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
                        inputClassStudent(student);
                        break;
                    case 6:
                        checkActionEdit = false;
                        break;
                    default:
                        System.out.println("Nhập sai. Vui lòng nhập lại ");
                        checkActionEdit = true;
                }


            }while(checkActionEdit);
        }
    }

    private void inputClassStudent(Student student) {
        System.out.println("Thông tin sinh viên: ");
        System.out.println(student);

        System.out.println("Chọn lớp mới: ");
        for (EClass eClass : EClass.values()) {
            System.out.printf("chọn %-5s %-5s \n", eClass.getId(), eClass.getName());
        }
        int idEClass = Integer.parseInt(scanner.nextLine());
        EClass eClass = EClass.getEClassById(idEClass);

        student.seteClass(eClass);
        studentService.editStudent(student.getId());

        System.out.println("Sửa thành công: ");
        System.out.println(student);
    }

    private void inputAddressStudent(Student student) {
        System.out.println("Thông tin sinh viên: ");
        System.out.println(student);

        System.out.println("Nhập địa chỉ mới: ");
        String address = scanner.nextLine();

        student.setAddress(address);
        studentService.editStudent(student.getId());

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
        studentService.editStudent(student.getId());

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
        studentService.editStudent(student.getId());

        System.out.println("Sửa thành công: ");
        System.out.println(student);
    }

    private void inputNameStudent(Student student) {
        System.out.println("Thông tin sinh viên: ");
        System.out.println(student);

        System.out.println("Nhập Họ tên mới: ");
        String name = scanner.nextLine();

        student.setName(name);
        studentService.editStudent(student.getId());

        System.out.println("Sửa thành công: ");
        System.out.println(student);
    }

    private Student inputIdStudent() {
        Student student = null;
        boolean checkEditStudentValid = false;
        do{
            try{
                System.out.println("Nhập ID sinh viên bạn muốn sửa");
                int idStudent = Integer.parseInt(scanner.nextLine());
                student = studentService.findStudentByID(idStudent);
                if(student == null){
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
        return student;
    }

    private void removeStudent() {
        showStudent(studentService.findAllStudent());
        System.out.println("Nhập vào ID của sinh viên muốn xoá");
        int idStudent = Integer.parseInt(scanner.nextLine());
        studentService.deleteStudent(idStudent);
        System.out.println("Đã xoá thành công");
        showStudent(studentService.findAllStudent());
    }


    public void showCreateStudent() {
        //int id, String name, String gender, Date bod, String address, EClass eClass //
        System.out.println("Thêm sinh viên mới");
        System.out.println("Nhập ID của sinh viên");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Họ và Tên");
        String name = scanner.nextLine();
        System.out.println("Chọn giới tính");
        for (EGender eGender : EGender.values()) {
            System.out.printf("chọn %-5s %-5s \n", eGender.getId(), eGender.getName());
        }
        int idEGender = Integer.parseInt(scanner.nextLine());
        EGender eGender = EGender.getEGenderById(idEGender);

        System.out.println("Ngày sinh(Nhập theo định dạng dd-MM-yyyy)");
        Date bod = DateUtils.parse(scanner.nextLine());
        System.out.println("Nhập vào địa chỉ");
        String address = scanner.nextLine();
        System.out.println("chọn lớp học");
        for (EClass eClass : EClass.values()) {
            System.out.printf("chọn %-5s %-5s \n", eClass.getId(), eClass.getName());
        }
        int idEClass = Integer.parseInt(scanner.nextLine());
        EClass eClass = EClass.getEClassById(idEClass);

        Student student = new Student(id, name, eGender, bod, address, eClass);
        studentService.addStudent(student);

        showStudent(studentService.findAllStudent());

    }

    private void showStudent(List<Student> allStudent) {
        //int id, String name, String gender, Date bod, String address, EClass eClass //
        System.out.printf("%-15s %-30s %-10s %-15s %-20s %-20s\n", "ID", "Name", "Gender", "Birthday", "Address", "Class");
        for (Student p : allStudent) {
            //int id, String name, byte age, String address, EBlock eBlock, ERole eRole// {
            System.out.printf("%-15s %-30s %-10s %-15s %-20s %-20s\n", p.getId(), p.getName(), p.geteGender().getName(),
                    DateUtils.format(p.getBod()), p.getAddress(), p.geteClass());
        }


    }
}
