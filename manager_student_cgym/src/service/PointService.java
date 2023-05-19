package service;

import model.EClass;
import model.EPass;
import model.Point;
import model.Student;
import utils.CSVUtils;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PointService {
    private static final String PATH = "./src/data/pointmodule1.csv";
    private static final String PATHSTUDENT ="./src/data/student.csv" ;
    public static List<Student> students;
    private static StudentService studentService;

    private static PointService pointService;
    public List<Point> findAllStudentPoint(int idCourse) {
        return CSVUtils.readFile("./src/data/pointmodule" +idCourse +".csv", Point.class);
    }

    public List<Point> findStudentByIDAndModule(int idCourse){
        List<Point> list = findAllStudentPoint(idCourse);
        List<Point> result = new ArrayList<>();
        for(Point pointList : list){
            if(pointList.getIdCourse()==idCourse){
                result.add(pointList);
            }
        }
        return result;
    }

   public List<Point> findStudentByClass(int idEClass,int idCourse){
       List<Point> list = findAllStudentPoint(idCourse);
        List<Point> result = new ArrayList<>();
        for(Point pointlist :list){
            if(pointlist.getIdEClass() == idEClass && pointlist.getIdCourse()==idCourse){
                result.add(pointlist);
            }
        }
        return result;
    }
    public List<Point> findListPointByIsPass(EPass ePass , int idCourse){
        List<Point> list = findAllStudentPoint(idCourse);
        List<Point> result = new ArrayList<>();
        for(Point items :list){
            if(items.getIsPass() == ePass && items.getIdCourse()==idCourse ){
                result.add(items);
            }
        }
        return result;
    }

    public Point checkSTUDING(int id,int idCourse){
        List<Point> list = findAllStudentPoint(idCourse);
        for (Point items : list) {
            if (items.getIdStudent() == id && items.getIdCourse() == idCourse){
                if(items.getIsPass() == EPass.STUDING){
                    return items;
                }
            }
        }
        return null;
    }


    public void addStudentPoint(Point p){
        List<Point> list = findAllStudentPoint(1);
        list.add(p);
        CSVUtils.writeFile(PATH,list);
    }

    public void editStudentPoints(Point point , int idCourse){
        List<Point> list = findAllStudentPoint(idCourse);
        for(int i = 0 ; i<list.size();i++){
            if(list.get(i).getIdStudent() == point.getIdStudent() && list.get(i).getIsPass().equals(EPass.STUDING)){
                //int pointID, int idStudent, float pointTH, float pointLT, float caseStudyPoint, float interviewPoint,
                //                 int schedule.csv, double pointAVG, boolean isPass //
                list.get(i).setIdEClass(point.getIdEClass());
                list.get(i).setIdCourse(point.getIdCourse());
                list.get(i).setPointTH(point.getPointTH());
                list.get(i).setPointLT(point.getPointLT());
                list.get(i).setCaseStudyPoint(point.getCaseStudyPoint());
                list.get(i).setinterviewPoint(point.getinterviewPoint());
                list.get(i).setPointAVG(point.getPointAVG());
                list.get(i).setIsPass(point.getIsPass());
            }
        }
        CSVUtils.writeFile("./src/data/pointmodule"+point.getIdCourse()+".csv",list);
    }

    public void checkIsPass(Point point){
        int idCourse = point.getIdCourse()+1;
        if(point.getIsPass().equals(EPass.PASS)){
            Point point1 = new Point(point.getIdStudent(), point.getIdEClass(), point.getIdCourse()+1, 0.0f,0.0f,0.0f,0.0f,0.0d,EPass.STUDING);
            if(point.getIdCourse() == 5){
                System.out.println("da tot nghiep");
            }
            else{
                CSVUtils.writeData("./src/data/pointmodule"+idCourse+".csv",point1);
            }

        }
        else if(point.getIsPass().equals(EPass.FAIL)){
            Point point1 = new Point(point.getIdStudent(),point.getIdEClass()+1, point.getIdCourse(), 0.0f,0.0f,0.0f,0.0f,0.0d,EPass.STUDING);
            CSVUtils.writeData("./src/data/pointmodule"+point.getIdCourse()+".csv",point1);
        }
    }

    public void deletePointStudent(int idStudent , int idCourse){
        List<Point> list = findAllStudentPoint(idCourse);
        for(Point items :list){
            if(items.getIdStudent() == idStudent && items.getIdCourse() == idCourse){
                list.remove(items);
            }
        }
        CSVUtils.writeFile("./src/data/pointmodule" +idCourse +".csv",list);
    }

    public boolean existStudentPoint(int id,int idCourse){
        List<Point> list = findAllStudentPoint(idCourse);
        for (Point items : list) {
            if (items.getIdStudent() == id && items.getIdCourse() == idCourse){
                return true;
            }
        }
        return false;
    }

    public List<Point> findClassStudent(int idStudent){
        List<Point> list1 = CSVUtils.readFile("./src/data/pointmodule1.csv",Point.class);
        List<Point> list2 = CSVUtils.readFile("./src/data/pointmodule2.csv",Point.class);
        List<Point> list3 = CSVUtils.readFile("./src/data/pointmodule3.csv",Point.class);
        List<Point> list4 = CSVUtils.readFile("./src/data/pointmodule4.csv",Point.class);
        List<Point> list5 = CSVUtils.readFile("./src/data/pointmodule5.csv",Point.class);
        List<Point> list = new ArrayList<>();
        for(Point item : list1){
            if(idStudent == item.getIdStudent()){
                list.add(item);
            }
        }
        for(Point item : list2){
            if(idStudent == item.getIdStudent()){
                list.add(item);
            }
        }
        for(Point item : list3){
            if(idStudent == item.getIdStudent()){
                list.add(item);
            }
        }
        for(Point item : list4){
            if(idStudent == item.getIdStudent()){
                list.add(item);
            }
        }
        for(Point item : list5){
            if(idStudent == item.getIdStudent()){
                list.add(item);
            }
        }
        return list;
    }



}
