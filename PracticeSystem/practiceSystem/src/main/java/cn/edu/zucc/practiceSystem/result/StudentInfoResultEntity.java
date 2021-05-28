package cn.edu.zucc.practiceSystem.result;

public class StudentInfoResultEntity {
    private String studentId;
    private String studentName;
    private String studentClass;
//    初始:0 提交审核:2 审核被退回:1 开始实习（审核通过）:3
//    实习结束（日志填完）:4  等待结果（提交实习报告）:5  实习完成（成绩出来）:6
//    鉴定表退回:7  延后实习:8
    private int state;
    private boolean tripleAgreement;    //三方协议
    private String studentWorkplace;    //学生工作地点
    private String studentTeacher;      //学生指导老师
    private String studentInternship;   //学生实习岗位
    private boolean studentLog;         //实习日志
    private boolean appraisalForm;     //鉴定表
    private int studentGrade;           //成绩

    public StudentInfoResultEntity(){

    }

    public StudentInfoResultEntity(String studentId, String studentName, String studentClass, int state, boolean tripleAgreement, String studentWorkplace, String studentTeacher, String studentInternship, boolean studentLog, boolean appraisal_form, int studentGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.state = state;
        this.tripleAgreement = tripleAgreement;
        this.studentWorkplace = studentWorkplace;
        this.studentTeacher = studentTeacher;
        this.studentInternship = studentInternship;
        this.studentLog = studentLog;
        this.appraisalForm = appraisal_form;
        this.studentGrade = studentGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isTripleAgreement() {
        return tripleAgreement;
    }

    public void setTripleAgreement(boolean tripleAgreement) {
        this.tripleAgreement = tripleAgreement;
    }

    public String getStudentWorkplace() {
        return studentWorkplace;
    }

    public void setStudentWorkplace(String studentWorkplace) {
        this.studentWorkplace = studentWorkplace;
    }

    public String getStudentTeacher() {
        return studentTeacher;
    }

    public void setStudentTeacher(String studentTeacher) {
        this.studentTeacher = studentTeacher;
    }

    public String getStudentInternship() {
        return studentInternship;
    }

    public void setStudentInternship(String studentInternship) {
        this.studentInternship = studentInternship;
    }

    public boolean isStudentLog() {
        return studentLog;
    }

    public void setStudentLog(boolean studentLog) {
        this.studentLog = studentLog;
    }

    public boolean isAppraisalForm() {
        return appraisalForm;
    }

    public void setAppraisalForm(boolean appraisalForm) {
        this.appraisalForm = appraisalForm;
    }

    public int getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }
}
