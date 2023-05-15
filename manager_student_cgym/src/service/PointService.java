package service;

import model.EClass;
import model.EPass;
import model.Point;
import model.Student;
import utils.CSVUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PointService {
    private static final String PATH  = "./src/data/point.csv";
    private static final String PATH2 = "./src/data/pointmodule2.csv";
    private static final String PATHSTUDENT ="./src/data/student.csv" ;
    public static List<Point> points;
    public static List<Student> students;
    private static StudentService studentService;
    static {
        points = CSVUtils.readFile(PATH , Point.class);
    }
    public List<Point> findAllStudentPoint() {
        return points;
    }
    public Point findPointByIdStudent(int idStudent){
        for(int i = 0 ; i<points.size() ; i++){
            if(points.get(i).getIdStudent() == idStudent){
                return points.get(i);
            }
        }
        return null;
    }


   public List<Point> findStudentByClass(int idEClass){
        List<Point> result = new ArrayList<>();
        for(Point pointlist :points){
            if(pointlist.getIdEClass() == idEClass){
                result.add(pointlist);
            }
        }
        return result;
    }


    public List<Point> findPointById(int idStudent){
        List<Point> result = new ArrayList<>();
        for(Point items :points){
            if(items.getIdStudent() == idStudent){
                result.add(items);
            }
        }
        return result;
    }

    public List<Point> findListPointByIsPass(EPass ePass){
        List<Point> result = new ArrayList<>();
        for(Point items :points){
            if(items.getIsPass() == ePass){
                result.add(items);
            }
        }
        return result;
    }


    public void addStudentPoint(Point p){
        points.add(p);
        CSVUtils.writeFile(PATH,points);
    }

    public void editStudentPoint(int idStudentPoint){
        for(int i = 0 ; i<points.size();i++){
            if(points.get(i).getIdStudent() == idStudentPoint){
                //int pointID, int idStudent, float pointTH, float pointLT, float caseStudyPoint, float interviewPoint,
                //                 int schedule.csv, double pointAVG, boolean isPass //
                points.get(i).setIdEClass(points.get(i).getIdEClass());
                points.get(i).setPointTH(points.get(i).getPointTH());
                points.get(i).setPointLT(points.get(i).getPointLT());
                points.get(i).setinterviewPoint(points.get(i).getCaseStudyPoint());
                points.get(i).setinterviewPoint(points.get(i).getinterviewPoint());
                points.get(i).setIdSchedule(points.get(i).getIdSchedule());
                points.get(i).setPointAVG(points.get(i).getPointAVG());
                points.get(i).setIsPass(points.get(i).getIsPass());
            }
        }
        CSVUtils.writeFile(PATH,points);
    }

    public void editStudentPoints(Point point){
        for(int i = 0 ; i<points.size();i++){
            if(points.get(i).getIdStudent() == point.getIdStudent()){
                //int pointID, int idStudent, float pointTH, float pointLT, float caseStudyPoint, float interviewPoint,
                //                 int schedule.csv, double pointAVG, boolean isPass //
                points.get(i).setIdEClass(point.getIdEClass());
                points.get(i).setPointTH(point.getPointTH());
                points.get(i).setPointLT(point.getPointLT());
                points.get(i).setCaseStudyPoint(point.getCaseStudyPoint());
                points.get(i).setinterviewPoint(point.getinterviewPoint());
                points.get(i).setIdSchedule(point.getIdSchedule());
                points.get(i).setPointAVG(point.getPointAVG());

                points.get(i).setIsPass(point.getIsPass());
            }
        }
        CSVUtils.writeFile(PATH,points);
    }

    public void checkIsPass(Point point){
        if(point.getIsPass().equals(EPass.PASS)){
            Point point1 = new Point(point.getIdStudent(), point.getIdEClass()+1,0.0f,0.0f,0.0f,0.0f,4,0.0d,EPass.STUDING);
            CSVUtils.writeData(PATH2,point1);
        }
    }

    public void deletePointStudent(int idStudent){
        for(int i =0 ; i <points.size();i++){
            if(points.get(i).getIdStudent() == idStudent) {
                points.remove(points.get(i));
            }
        }
        CSVUtils.writeFile(PATH,points);
    }

    public boolean existStudentPoint(int id){
        for (Point pointss : points) {
            if (pointss.getIdStudent() == id){
                return true;
            }
        }
        return false;
    }

}
