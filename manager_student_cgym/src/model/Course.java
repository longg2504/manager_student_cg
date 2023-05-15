package model;

import java.beans.JavaBean;
import java.util.concurrent.RecursiveAction;

public class Course implements IModel<Course> {
    private  long id;

    private  String name;

    public Course(){

    }

    public Course(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        id = Integer.parseInt(items[0]);
        name = items[1];
    }

    @Override
    public String toString() {
        return String.format("%s,%s",this.id,this.name);
    }
}
