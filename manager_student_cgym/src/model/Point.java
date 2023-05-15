package model;

public class Point implements IModel<Point> {
    private int idStudent;
    private int idEClass;

    private Float pointTH;
    private Float pointLT;

    private Float caseStudyPoint;

    private Float interviewPoint;

    private int idSchedule;

    private Double pointAVG;

    private EPass isPass;


    public Point() {

    }

    public Point(int idStudent, int idEClass, Float pointTH, Float pointLT, Float caseStudyPoint,
                 Float interviewPoint, int idSchedule, Double pointAVG, EPass isPass) {
        this.idStudent = idStudent;
        this.idEClass = idEClass;
        this.pointTH = pointTH;
        this.pointLT = pointLT;
        this.caseStudyPoint = caseStudyPoint;
        this.interviewPoint = interviewPoint;
        this.idSchedule = idSchedule;
        this.pointAVG = pointAVG;
        this.isPass = isPass;
    }


    public int getIdEClass() {
        return idEClass;
    }

    public void setIdEClass(int idEClass) {
        this.idEClass = idEClass;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public float getPointTH() {
        return pointTH;
    }

    public void setPointTH(Float pointTH) {
        this.pointTH = pointTH;
    }

    public float getPointLT() {
        return pointLT;
    }

    public void setPointLT(Float pointLT) {
        this.pointLT = pointLT;
    }

    public float getCaseStudyPoint() {
        return caseStudyPoint;
    }

    public void setCaseStudyPoint(Float caseStudyPoint) {
        this.caseStudyPoint = caseStudyPoint;
    }

    public float getinterviewPoint() {
        return interviewPoint;
    }

    public void setinterviewPoint(Float interviewPoint) {
        this.interviewPoint = interviewPoint;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public double getPointAVG() {
        return pointAVG;
    }

    public void setPointAVG(Double pointAVG) {
        this.pointAVG = pointAVG;
    }

    public EPass getIsPass() {
        return isPass;
    }

    public void setIsPass(EPass isPass) {
        this.isPass = isPass;
    }

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
//        int idStudent, int EClass, float pointTH, float pointLT, float caseStudy, float interview,
//        int idSchedule, double pointAVG, boolean isPass
        idStudent = Integer.parseInt(items[0]);
        idEClass =Integer.parseInt(items[1]);
        pointTH = Float.parseFloat(items[2]);
        pointLT = Float.parseFloat(items[3]);
        caseStudyPoint = Float.parseFloat(items[4]);
        interviewPoint = Float.parseFloat(items[5]);
        idSchedule = Integer.parseInt(items[6]);
        pointAVG = Double.parseDouble(items[7]);
        isPass = EPass.valueOf(items[8]);

    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",this.idStudent,this.idEClass ,this.pointTH, this.pointLT
                , this.caseStudyPoint, this.interviewPoint, this.idSchedule, this.pointAVG, this.isPass);

    }
}