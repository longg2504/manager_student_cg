package service;

import model.EClass;
import model.Point;
import model.Student;
import utils.CSVUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PointService {
    private static final String PATH  = "./src/data/point.csv";
    public static List<Point> points;
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

    public void addStudentPoint(Point p){
        points.add(p);
        CSVUtils.writeFile(PATH,points);
    }

    public void editStudentPoint(int idStudentPoint){
        for(int i = 0 ; i<points.size();i++){
            if(points.get(i).getIdStudent() == idStudentPoint){
                //int idStudent, EClass eClasss, float pointTH, float pointLT //
                points.get(i).seteClass(points.get(i).geteClass());
                points.get(i).seteClass(points.get(i).geteClass());
                points.get(i).setPointTH(points.get(i).getPointTH());
                points.get(i).setPointLT(points.get(i).getPointLT());
            }
        }
        CSVUtils.writeFile(PATH,points);
    }

    public void deletePointStudent(int idStudent){
        for(int i =0 ; i <points.size();i++){
            if(points.get(i).getIdStudent() == idStudent) {
                points.remove(points.get(i));
            }
        }
        CSVUtils.writeFile(PATH,points);
    }

    public List<Point> findStudentPointByClass(EClass eClass){
        List<Point> result = new ArrayList<>();
        for(Point pointss :points){
            if(pointss.geteClass().getId() == eClass.getId()){
                result.add(pointss);
            }
        }
        return result;
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
