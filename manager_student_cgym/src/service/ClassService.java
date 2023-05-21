package service;

import model.EClass;
import model.Student;
import utils.CSVUtils;

import java.util.List;

public class ClassService {
    private static final String PATH  = "./src/data/class.csv";


    public List<EClass> findAllEClass() {
        return CSVUtils.readFile(PATH , EClass.class);
    }

    public void addClass(EClass s){
        List<EClass> list = findAllEClass();
        list.add(s);
        CSVUtils.writeFile(PATH,list);
    }

    public EClass findClassByID(int idEClass){
        List<EClass> list = findAllEClass();
        for(int i = 0 ; i<list.size() ; i++){
            if(list.get(i).getId() == idEClass){
                return list.get(i);
            }
        }
        return null;
    }

    public void editEClass(int idEClass , EClass eClass){
        List<EClass> list = findAllEClass();
        for(EClass items : list)
            if(items.getId() == idEClass){
                items.setName(eClass.getName());
            }

        CSVUtils.writeFile(PATH,list);
    }

    public void deleteEClass(int idEClass){
        List<EClass> list = findAllEClass();
        list.removeIf(items -> items.getId() == idEClass);
        CSVUtils.writeFile(PATH,list);
    }

    public boolean existEClass(int idEClass){
        List<EClass> list = findAllEClass();
        for (EClass items : list) {
            if (items.getId() == idEClass){
                return true;
            }
        }
        return false;
    }
}
