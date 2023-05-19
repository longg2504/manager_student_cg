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
}
