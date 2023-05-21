package service;

import model.Course;
import model.EClass;
import utils.CSVUtils;

import java.util.List;

public class CourseService {
    private static final String PATH  = "./src/data/course.csv";

    public List<Course> findAllCourse() {
        return CSVUtils.readFile(PATH,Course.class);
    }

    public void addCourse(Course s){
        List<Course> list = findAllCourse();
        list.add(s);
        CSVUtils.writeFile(PATH,list);
    }

    public Course findCourseByID(int idCourse){
        List<Course> list = findAllCourse();
        for(Course items :list){
            if(items.getId() == idCourse){
                return items;
            }
        }
        return null;
    }

    public void editCourse(int idCourse , Course course){
        List<Course> list = findAllCourse();
        for(Course items : list)
            if(items.getId() == idCourse){
                items.setName(course.getName());
            }

        CSVUtils.writeFile(PATH,list);
    }

    public void deleteCourse(int idCourse){
        List<Course> list = findAllCourse();
        list.removeIf(items -> items.getId() == idCourse);
        CSVUtils.writeFile(PATH,list);
    }

    public boolean existCourse(int idCourse){
        List<Course> list = findAllCourse();
        for (Course items : list) {
            if (items.getId() == idCourse){
                return true;
            }
        }
        return false;
    }
}
