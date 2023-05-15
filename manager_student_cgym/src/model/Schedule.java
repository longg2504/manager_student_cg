package model;

import utils.DateUtils;

import java.util.Date;
import java.util.List;

public class Schedule implements IModel<Schedule> {
    private int id ;

    private Date starDate;

    private Date endDate;

    private int idEClass;

    private int idCourse;

    public Schedule(){

    }

    public Schedule(int id, Date starDate, Date endDate, int idEClass, int idCourse) {
        this.id = id;
        this.starDate = starDate;
        this.endDate = endDate;
        this.idEClass = idEClass;
        this.idCourse = idCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        //int id, Date starDate, Date endDate, int idEClass, int idCourse
        String[] items = line.split(",");
        id = Integer.parseInt(items[0]);
        starDate = DateUtils.parse(items[1]);
        endDate = DateUtils.parse(items[2]);
        idEClass = Integer.parseInt(items[3]);
        idCourse = Integer.parseInt(items[4]);

    }


    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",this.id, DateUtils.format(this.starDate),DateUtils.format(this.endDate)
                ,this.idEClass,this.idCourse);
    }
}
