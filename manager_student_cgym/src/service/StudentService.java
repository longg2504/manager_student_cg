package service;

import model.Course;
import model.EClass;
import model.Schedule;
import model.Student;
import utils.CSVUtils;
import view.StudentView;

import java.util.ArrayList;
import java.util.List;

public class StudentService {


    private static final String PATH  = "./src/data/student.csv";
    public static List<Student> studentList;
     static {
         studentList = CSVUtils.readFile(PATH , Student.class);

    }
    public List<Student> findAllStudent() {
        return studentList;
    }
    //tìm student theo id
    public Student findStudentByID(int idStudent){
        for(int i = 0 ; i<studentList.size() ; i++){
            if(studentList.get(i).getId() == idStudent){
                 return studentList.get(i);
            }
        }
        return null;
    }

    public Student findStudentByName(String name){
        for (Student student : studentList) {
            if (student.getName().equalsIgnoreCase(name)){
                return student;
            }
        }
        return null;
    }

    public List<Student> findStudentByClass(int idEClass){
        List<Student> result = new ArrayList<>();
        for(Student student :studentList){
            if(student.getIdEClass() == idEClass){
                result.add(student);
            }
        }
        return result;
    }


    public void addStudent(Student s){
        studentList.add(s);
        CSVUtils.writeFile(PATH,studentList);
    }

    public void editStudent(int idStudent ){
        for(int i = 0 ; i<studentList.size();i++){
            if(studentList.get(i).getId() == idStudent){
                //int id, String name, EGender eGender, Date bod, String address, int idEClass //
                studentList.get(i).setName(studentList.get(i).getName());
                studentList.get(i).seteGender(studentList.get(i).geteGender());
                studentList.get(i).setBod(studentList.get(i).getBod());
                studentList.get(i).setAddress(studentList.get(i).getAddress());
                studentList.get(i);
            }
        }
        CSVUtils.writeFile(PATH,studentList);
    }


    public void deleteStudent(int idStudent){
        for(int i =0 ; i <studentList.size();i++){
            if(studentList.get(i).getId() == idStudent) {
                studentList.remove(studentList.get(i));
            }
        }
        CSVUtils.writeFile(PATH,studentList);
    }

    public boolean existStudent(int idStudent){
        for (Student items : studentList) {
            if (items.getId() == idStudent){
                return true;
            }
        }
        return false;
    }

}
