package service;

import model.EClass;
import model.Schedule;
import utils.CSVUtils;

import java.util.List;

public class ScheduleService {
    private static final String PATH  = "./src/data/schedule.csv";
    public static List<Schedule> scheduleList;
    static {
        scheduleList = CSVUtils.readFile(PATH , Schedule.class);

    }

    public List<Schedule> findAllCourse() {
        return scheduleList;
    }

    public void addSchedule(Schedule s){
        scheduleList.add(s);
        CSVUtils.writeFile(PATH,scheduleList);
    }

    public Schedule findScheduleByID(int idSchedule){
        for(int i = 0 ; i<scheduleList.size() ; i++){
            if(scheduleList.get(i).getId() == idSchedule){
                return scheduleList.get(i);
            }
        }
        return null;
    }

    public void editSchedule(int idSchedule){
        for(int i = 0 ; i<scheduleList.size();i++){
            if(scheduleList.get(i).getId() == idSchedule){
                //int id, Date starDate, Date endDate, int idEClass, int idCourse //
                scheduleList.get(i).setStarDate(scheduleList.get(i).getStarDate());
                scheduleList.get(i).setEndDate(scheduleList.get(i).getEndDate());
                scheduleList.get(i).setIdEClass(scheduleList.get(i).getIdEClass());
                scheduleList.get(i).setIdCourse(scheduleList.get(i).getIdCourse());
            }
        }
        CSVUtils.writeFile(PATH,scheduleList);
    }

    public void deleteSchedule(int idSchedule){
        for(int i =0 ; i <scheduleList.size();i++){
            if(scheduleList.get(i).getId() == idSchedule) {
                scheduleList.remove(scheduleList.get(i));
            }
        }
        CSVUtils.writeFile(PATH,scheduleList);
    }

    public boolean existSchedule(int idSchedule){
        for (Schedule items : scheduleList) {
            if (items.getId() == idSchedule){
                return true;
            }
        }
        return false;
    }

}
