package model;

import utils.DateUtils;
import utils.ValidateUtils;

import java.util.Date;

public class Student implements IModel<Student> {
    private int id;
    private String name;
    private EGender eGender;
    private Date bod;

    private String address;

    private int idEClass;;

    private int idCourse;

    public Student(){

    }

    public Student(int id, String name, EGender eGender, Date bod, String address, int idEClass,int idCourse) {
        this.id = id;
        this.name = name;
        this.eGender = eGender;
        this.bod = bod;
        this.address = address;
        this.idEClass = idEClass;
        this.idCourse = idCourse;
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

    public int getIdEClass() {
        return idEClass;
    }

    public void setIdEClass(int idEClass) {
        this.idEClass = idEClass;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public void parseData(String line) {
        //int id, String name, EGender eGender, Date bod, String address, int eClass //
        String[] items = line.split(",");
        id = Integer.parseInt(items[0]);
        name = items[1];
        eGender = EGender.getEGenderByName(items[2]);
        bod = DateUtils.parse(items[3]);
        address = ValidateUtils.parseCharToComma(items[4]);
        idEClass = Integer.parseInt(items[5]);
        idCourse = Integer.parseInt(items[6]);

    }

    public String toString() {
        //int id, String name, EGender gender, Date bod, String address, int idEClass , int idCourse //
        return String.format("%s,%s,%s,%s,%s,%s,%s",this.id, this.name,this.eGender.getName(),DateUtils.format(this.bod),
                this.address,this.idEClass,this.idCourse);
    }
}
