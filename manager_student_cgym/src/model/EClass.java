package model;

public enum EClass {
    C0223G1(1 , "JAVA") , C0223G2(2,"JAVASCRIPT"),C0223G3(3,"C++"),C0223G4(4,"C#");
    private int id;
    private String Name;

    EClass(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public static EClass getEClassById(int idClass){
        for(EClass eClass : EClass.values()){
            if(eClass.getId()==idClass){
                return eClass;
            }
        }
        return null;
    }

    public static EClass getEClassByName(String nameClass){
        for(EClass eClass : EClass.values()){
            if(eClass.toString().equals(nameClass)){
                return eClass;
            }
        }
        return null;
    }
}
