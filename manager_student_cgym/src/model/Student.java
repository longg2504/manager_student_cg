package model;

import utils.DateUtils;

import java.util.Date;
import java.util.List;

public class Student implements IModel<Student> {
    private int id;
    private String name;
    private EGender eGender;
    private Date bod;

    private String address;

    private EClass eClass;

    private List<Point> pointList;

    public Student(){

    }

    public Student(int id, String name, EGender eGender, Date bod, String address, EClass eClass) {
        this.id = id;
        this.name = name;
        this.eGender = eGender;
        this.bod = bod;
        this.address = address;
        this.eClass = eClass;
    }

    public Student(int id, String name, EGender eGender, Date bod, String address, EClass eClass, List<Point> pointList) {
        this.id = id;
        this.name = name;
        this.eGender = eGender;
        this.bod = bod;
        this.address = address;
        this.eClass = eClass;
        this.pointList = pointList;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setStudentPoint(List<Point> pointList) {
        this.pointList = pointList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EGender geteGender() {
        return eGender;
    }

    public void seteGender(EGender eGender) {
        this.eGender = eGender;
    }

    public Date getBod() {
        return bod;
    }

    public void setBod(Date bod) {
        this.bod = bod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EClass geteClass() {
        return eClass;
    }

    public void seteClass(EClass eClass) {
        this.eClass = eClass;
    }

    @Override
    public void parseData(String line) {
        //int id, String name, EGender gender, Date bod, String address, EClass eClass //
        String[] items = line.split(",");
        int idStudent = Integer.parseInt(items[0]);
        EGender eGenderStudent = EGender.getEGenderByName(items[2]);
        Date bodStudent = DateUtils.parse(items[3]);
        EClass eClassStudent = EClass.getEClassByName(items[5]);
        this.setId(idStudent);
        this.setName(items[1]);
        this.seteGender(eGenderStudent);
        this.setBod(bodStudent);
        this.setAddress(items[4]);
        this.seteClass(eClassStudent);
    }

    public String toString() {
        //int id, String name, EGender gender, Date bod, String address, EClass eClass //
        return String.format("%s,%s,%s,%s,%s,%s",this.id, this.name,this.eGender.getName(),DateUtils.format(this.bod),
                this.address,this.eClass);
    }
}
