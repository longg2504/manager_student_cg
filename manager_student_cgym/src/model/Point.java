package model;

import utils.DateUtils;

public class Point implements IModel<Point> {
    private int idStudent;

    private EClass eClass;
    private float pointTH;
    private float pointLT;

    public Point()  {

    }

    public Point(int idStudent, EClass eClass, float pointTH, float pointLT) {
        this.idStudent = idStudent;
        this.eClass = eClass;
        this.pointTH = pointTH;
        this.pointLT = pointLT;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public EClass geteClass() {
        return eClass;
    }

    public void seteClass(EClass eClass) {
        this.eClass = eClass;
    }

    public float getPointTH() {
        return pointTH;
    }

    public void setPointTH(float pointTH) {
        this.pointTH = pointTH;
    }

    public float getPointLT() {
        return pointLT;
    }

    public void setPointLT(float pointLT) {
        this.pointLT = pointLT;
    }


    @Override
    public void parseData(String line) {
        //int id, EClass eClasss, float pointTH, float pointLT//
        String[] items = line.split(",");
        int idStudent = Integer.parseInt(items[0]);
        EClass eClassStudent = EClass.getEClassByName(items[1]);
        float pointTHs = Float.parseFloat(items[2]) ;
        float pointLTs = Float.parseFloat(items[3]);
        this.setIdStudent(idStudent);
        this.seteClass(eClassStudent);
        this.setPointTH(pointTHs);
        this.setPointLT(pointLTs);
    }

    @Override
    public String toString() {
        //int id, EClass eClasss, float pointTH, float pointLT//
        return String.format("%s,%s,%s,%s", this.idStudent,this.eClass,this.pointTH,this.pointLT);
    }
}