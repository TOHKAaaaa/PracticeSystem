package cn.edu.zucc.practiceSystem.result;

import java.sql.Timestamp;

public class StudentSatausResult {
    private int sId;
    private String studentId;
    private String studentName;
    private String studentClass;
    private String teacherId;
    private String studentTeacher;
    private String studentWorkplace;
    private String studentInternship;
    private Timestamp startTime;
    private Timestamp endTime;
    private int remark;
    private int studentGrade;
    private Timestamp gradeTime;

    public StudentSatausResult(){
    }

    public StudentSatausResult(int sId, String studentId, String studentName, String studentClass, String teacherId, String studentTeacher, String studentWorkplace, String studentInternship, Timestamp startTime, Timestamp endTime, int remark, int studentGrade, Timestamp gradeTime) {
        this.sId = sId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.teacherId = teacherId;
        this.studentTeacher = studentTeacher;
        this.studentWorkplace = studentWorkplace;
        this.studentInternship = studentInternship;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remark = remark;
        this.studentGrade = studentGrade;
        this.gradeTime = gradeTime;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentTeacher() {
        return studentTeacher;
    }

    public void setStudentTeacher(String studentTeacher) {
        this.studentTeacher = studentTeacher;
    }

    public String getStudentWorkplace() {
        return studentWorkplace;
    }

    public void setStudentWorkplace(String studentWorkplace) {
        this.studentWorkplace = studentWorkplace;
    }

    public String getStudentInternship() {
        return studentInternship;
    }

    public void setStudentInternship(String studentInternship) {
        this.studentInternship = studentInternship;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    public int getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }

    public Timestamp getGradeTime() {
        return gradeTime;
    }

    public void setGradeTime(Timestamp gradeTime) {
        this.gradeTime = gradeTime;
    }
}
