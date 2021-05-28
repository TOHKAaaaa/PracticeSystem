package cn.edu.zucc.practiceSystem.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "practice_system", catalog = "")
public class StudentEntity {
    private int sId;
    private String studentId;
    private String studentPasswd;
    private String studentName;
    private String studentClass;
    private String studentTeacher;
    private String studentWorkplace;
    private String studentInternship;
    private Timestamp stratTime;
    private Timestamp endTime;
    private String remark;
    private int deleteFlag;

    @Id
    @Column(name = "s_id")
    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    @Basic
    @Column(name = "student_id")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_passwd")
    public String getStudentPasswd() {
        return studentPasswd;
    }

    public void setStudentPasswd(String studentPasswd) {
        this.studentPasswd = studentPasswd;
    }

    @Basic
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_class")
    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    @Basic
    @Column(name = "student_teacher")
    public String getStudentTeacher() {
        return studentTeacher;
    }

    public void setStudentTeacher(String studentTeacher) {
        this.studentTeacher = studentTeacher;
    }

    @Basic
    @Column(name = "student_workplace")
    public String getStudentWorkplace() {
        return studentWorkplace;
    }

    public void setStudentWorkplace(String studentWorkplace) {
        this.studentWorkplace = studentWorkplace;
    }

    @Basic
    @Column(name = "student_Internship")
    public String getStudentInternship() {
        return studentInternship;
    }

    public void setStudentInternship(String studentInternship) {
        this.studentInternship = studentInternship;
    }

    @Basic
    @Column(name = "strat_time")
    public Timestamp getStratTime() {
        return stratTime;
    }

    public void setStratTime(Timestamp stratTime) {
        this.stratTime = stratTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "delete_flag")
    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return sId == that.sId &&
                deleteFlag == that.deleteFlag &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentPasswd, that.studentPasswd) &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(studentClass, that.studentClass) &&
                Objects.equals(studentTeacher, that.studentTeacher) &&
                Objects.equals(studentWorkplace, that.studentWorkplace) &&
                Objects.equals(studentInternship, that.studentInternship) &&
                Objects.equals(stratTime, that.stratTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sId, studentId, studentPasswd, studentName, studentClass, studentTeacher, studentWorkplace, studentInternship, stratTime, endTime, remark, deleteFlag);
    }
}
