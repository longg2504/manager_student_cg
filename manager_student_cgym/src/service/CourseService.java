package service;

import model.Course;
import utils.CSVUtils;

import java.util.List;

public class CourseService {
    private static final String PATH  = "./src/data/course.csv";
    public static List<Course> courseList;
     static {
         courseList = CSVUtils.readFile(PATH , Course.class);

    }
    public List<Course> findAllCourse() {
        return courseList;
    }

    public void addCourse(Course s){
        courseList.add(s);
        CSVUtils.writeFile(PATH,courseList);
    }

    public Course findCourseByID(int idCourse){
        for(int i = 0 ; i<courseList.size() ; i++){
            if(courseList.get(i).getId() == idCourse){
                return courseList.get(i);
            }
        }
        return null;
    }

    public void editCourse(int idCourse){
        for(int i = 0 ; i<courseList.size();i++){
            if(courseList.get(i).getId() == idCourse){
                courseList.get(i).setName(courseList.get(i).getName());
            }
        }
        CSVUtils.writeFile(PATH,courseList);
    }

    public void deleteCourse(int idCourse){
        for(int i =0 ; i <courseList.size();i++){
            if(courseList.get(i).getId() == idCourse) {
                courseList.remove(courseList.get(i));
            }
        }
        CSVUtils.writeFile(PATH,courseList);
    }

    public boolean existCourse(int idCourse){
        for (Course items : courseList) {
            if (items.getId() == idCourse){
                return true;
            }
        }
        return false;
    }
}
