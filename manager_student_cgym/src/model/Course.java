package model;

public class Course implements IModel<Course> {
    private  int id;

    private  String name;

    public Course(){

    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
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
