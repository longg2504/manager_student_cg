package model;

import utils.DateUtils;

import java.util.Date;

public class Student implements IModel<Student> {
    private int id;
    private String name;
    private EGender eGender;
    private Date bod;

    private String address;

    private int idEClass;

    public Student(){

    }

    public Student(int id, String name, EGender eGender, Date bod, String address, int idEClass) {
        this.id = id;
        this.name = name;
        this.eGender = eGender;
        this.bod = bod;
        this.address = address;
        this.idEClass = idEClass;
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

    @Override
    public void parseData(String line) {
        //int id, String name, EGender eGender, Date bod, String address, int eClass //
        String[] items = line.split(",");
        id = Integer.parseInt(items[0]);
        name = items[1];
        eGender = EGender.getEGenderByName(items[2]);
        bod = DateUtils.parse(items[3]);
        address = items[4];
        idEClass = Integer.parseInt(items[5]);

    }

    public String toString() {
        //int id, String name, EGender gender, Date bod, String address, EClass eClass //
        return String.format("%s,%s,%s,%s,%s,%s",this.id, this.name,this.eGender.getName(),DateUtils.format(this.bod),
                this.address,this.idEClass);
    }
}
