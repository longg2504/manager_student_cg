package service;

import model.EClass;
import model.Student;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static final String PATH  = "./src/data/student.csv";
    public static List<Student> students;
     static {
        students = CSVUtils.readFile(PATH , Student.class);

    }
    public List<Student> findAllStudent() {
        return students;
    }
    //tìm student theo id
    public Student findStudentByID(int idStudent){
        for(int i = 0 ; i<students.size() ; i++){
            if(students.get(i).getId() == idStudent){
                 return students.get(i);
            }
        }
        return null;
    }
    // thêm 1 student
    public void addStudent(Student s){
        students.add(s);
        CSVUtils.writeFile(PATH,students);
    }
    //sửa thông tin của student theo id
    public void editStudent(int idStudent ){
        for(int i = 0 ; i<students.size();i++){
            if(students.get(i).getId() == idStudent){
                //int id, String name, String gender, Date bod, String address, EClass eClass //
                students.get(i).setName(students.get(i).getName());
                students.get(i).seteGender(students.get(i).geteGender());
                students.get(i).setBod(students.get(i).getBod());
                students.get(i).setAddress(students.get(i).getAddress());
                students.get(i).seteClass(students.get(i).geteClass());
            }
        }
        CSVUtils.writeFile(PATH,students);
    }

    // xoá sinh viên theo id
    public void deleteStudent(int idStudent){
        for(int i =0 ; i <students.size();i++){
            if(students.get(i).getId() == idStudent) {
                students.remove(students.get(i));
            }
        }
        CSVUtils.writeFile(PATH,students);
    }
    // kiểm tra sinh viên theo id
    public boolean existStudent(int id){
        for (Student student : students) {
            if (student.getId() == id){
                return true;
            }
        }
        return false;
    }
    //kiểm tra sinh viên theo name
    public boolean existStudentName(String name){
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    //tìm sinh viên theo tên
    public Student findStudentByName(String name){
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)){
                return student;
            }
        }
        return null;
    }

    //hiển thị danh sách sinh viên theo class
    public List<Student> findStudentByClass(EClass eClass){
        List<Student> result = new ArrayList<>();
        for(Student student :students){
            if(student.geteClass().getId() == eClass.getId()){
                result.add(student);
            }
        }
        return result;
    }
}
