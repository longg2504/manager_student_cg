package model;



public enum EGender {
    NAM(1,"NAM") , NỮ(2,"NỮ");
    private int id;
    private String name;

    EGender(int id , String name){
        this.id = id;
        this.name = name;
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

    public static EGender getEGenderById(int idGender){
        for(EGender eGender : EGender.values()){
            if(eGender.getId()==idGender){
                return eGender;
            }
        }
        return null;
    }

    public static EGender getEGenderByName(String nameGender){
        for(EGender eGender : EGender.values()){
            if(eGender.toString().equals(nameGender)){
                return eGender;
            }
        }
        return null;
    }
}
