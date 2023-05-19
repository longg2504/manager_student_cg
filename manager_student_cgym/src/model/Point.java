package model;

public class Point implements IModel<Point> {
    private int idStudent;
    private int idEClass;

    private int idCourse;

    private Float pointTH;
    private Float pointLT;

    private Float caseStudyPoint;

    private Float interviewPoint;


    private Double pointAVG;

    private EPass isPass;


    public Point() {

    }

    public Point(int idStudent, int idEClass,int idCourse, Float pointTH, Float pointLT, Float caseStudyPoint,
                 Float interviewPoint, Double pointAVG, EPass isPass) {
        this.idStudent = idStudent;
        this.idEClass = idEClass;
        this.idCourse = idCourse;
        this.pointTH = pointTH;
        this.pointLT = pointLT;
        this.caseStudyPoint = caseStudyPoint;
        this.interviewPoint = interviewPoint;
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

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
//        int idStudent, int EClass,int idCourse, float pointTH, float pointLT, float caseStudy, float interview,
//        double pointAVG, boolean isPass
        idStudent = Integer.parseInt(items[0]);
        idEClass =Integer.parseInt(items[1]);
        idCourse = Integer.parseInt(items[2]);
        pointTH = Float.parseFloat(items[3]);
        pointLT = Float.parseFloat(items[4]);
        caseStudyPoint = Float.parseFloat(items[5]);
        interviewPoint = Float.parseFloat(items[6]);
        pointAVG = Double.parseDouble(items[7]);
        isPass = EPass.valueOf(items[8]);

    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",this.idStudent,this.idEClass,this.idCourse,this.pointTH, this.pointLT
                ,this.caseStudyPoint, this.interviewPoint, this.pointAVG, this.isPass);

    }
}