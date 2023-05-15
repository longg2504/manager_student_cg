package service;

import model.EClass;
import utils.CSVUtils;

import java.util.List;

public class ClassService {
    private static final String PATH  = "./src/data/class.csv";
    public static List<EClass> eClassList;
    static {
        eClassList = CSVUtils.readFile(PATH , EClass.class);

    }

    public List<EClass> findAllEClass() {
        return eClassList;
    }

    public void addClass(EClass s){
        eClassList.add(s);
        CSVUtils.writeFile(PATH,eClassList);
    }

    public EClass findClassByID(int idEClass){
        for(int i = 0 ; i<eClassList.size() ; i++){
            if(eClassList.get(i).getId() == idEClass){
                return eClassList.get(i);
            }
        }
        return null;
    }

    public void editEClass(int idEClass){
        for(int i = 0 ; i<eClassList.size();i++){
            if(eClassList.get(i).getId() == idEClass){
                eClassList.get(i).setName(eClassList.get(i).getName());
            }
        }
        CSVUtils.writeFile(PATH,eClassList);
    }

    public void deleteEClass(int idEClass){
        for(int i =0 ; i <eClassList.size();i++){
            if(eClassList.get(i).getId() == idEClass) {
                eClassList.remove(eClassList.get(i));
            }
        }
        CSVUtils.writeFile(PATH,eClassList);
    }

    public boolean existEClass(int idEClass){
        for (EClass items : eClassList) {
            if (items.getId() == idEClass){
                return true;
            }
        }
        return false;
    }
}
