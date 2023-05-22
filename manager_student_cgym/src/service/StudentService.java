package service;

import model.*;
import utils.CSVUtils;
import view.StudentView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private static final String PATH = "./src/data/student.csv";
    private StudentService studentService;


    public List<Student> findAllStudent() {
        return CSVUtils.readFile(PATH, Student.class);
    }

    public List<Student> findStudentList(int idCourse) {
        List<Student> list = findAllStudent();
        List<Student> result = new ArrayList<>();
        for (Student item : list) {
            if (item.getIdCourse() == idCourse) {
                result.add(item);
            }
        }
        return result;
    }
    public List<Student> findStudentlListByClassAndModule(int idCourse , int idEClass){
        List<Student> list = findAllStudent();
        List<Student> result = new ArrayList<>();
        for (Student item : list) {
            if (item.getIdCourse() == idCourse && item.getIdEClass() == idEClass) {
                result.add(item);
            }
        }
        return result;
    }

    public void addStudent(Student student) {
        List<Student> list = findAllStudent();
        list.add(student);
        CSVUtils.writeFile(PATH, list);
    }

    public void editStudent(int idStudent, Student student) {
        List<Student> list = findAllStudent();
        for (Student item : list) {
            if (item.getId() == idStudent) {
//                int id, String name, EGender eGender, Date bod, String address, int idEClass,int idCourse
                item.setName(student.getName());
                item.seteGender(student.geteGender());
                item.setBod(student.getBod());
                item.setAddress(student.getAddress());
                item.setIdEClass(student.getIdEClass());
                item.setIdCourse(student.getIdCourse());
            }
        }
        CSVUtils.writeFile(PATH, list);
    }



    public Student findStudentByID(int idStudent) {
        List<Student> list = findAllStudent();
        for (Student item : list) {
            if (item.getId() == idStudent) {
                return item;
            }
        }
        return null;
    }

    public void deleteStudent(int idStudent) {
        List<Student> list = findAllStudent();
        list.removeIf(item -> item.getId() == idStudent);
        CSVUtils.writeFile(PATH, list);
    }
    public void deleteStudentAllList(int idStudent){
        List<Student> listStudent = findAllStudent();
        List<Point> list1 = CSVUtils.readFile("./src/data/pointmodule1.csv",Point.class);
        List<Point> list2 = CSVUtils.readFile("./src/data/pointmodule2.csv",Point.class);
        List<Point> list3 = CSVUtils.readFile("./src/data/pointmodule3.csv",Point.class);
        List<Point> list4 = CSVUtils.readFile("./src/data/pointmodule4.csv",Point.class);
        List<Point> list5 = CSVUtils.readFile("./src/data/pointmodule5.csv",Point.class);
        list1 = list1.stream().filter(e -> e.getIdStudent() != idStudent).collect(Collectors.toList());
        list2 = list2.stream().filter(e -> e.getIdStudent() != idStudent).collect(Collectors.toList());
        list3 = list3.stream().filter(e -> e.getIdStudent() != idStudent).collect(Collectors.toList());
        list4 = list4.stream().filter(e -> e.getIdStudent() != idStudent).collect(Collectors.toList());
        list5 = list5.stream().filter(e -> e.getIdStudent() != idStudent).collect(Collectors.toList());
        listStudent = listStudent.stream().filter(e -> e.getId() != idStudent).collect(Collectors.toList());
        CSVUtils.writeFile(PATH, listStudent);
        CSVUtils.writeFile("./src/data/pointmodule1.csv", list1);
        CSVUtils.writeFile("./src/data/pointmodule2.csv", list2);
        CSVUtils.writeFile("./src/data/pointmodule3.csv", list3);
        CSVUtils.writeFile("./src/data/pointmodule4.csv", list4);
        CSVUtils.writeFile("./src/data/pointmodule5.csv", list5);
    }

    public Student findStudentByName(String name){
        List<Student> list = findAllStudent();
        for(Student item : list){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }
    public boolean isStudentExist(int idStudent) {
        List<Student> list = findAllStudent();
        for (Student item : list) {
            if (item.getId() == idStudent) {
                return true;
            }
        }
        return false;
    }
}











