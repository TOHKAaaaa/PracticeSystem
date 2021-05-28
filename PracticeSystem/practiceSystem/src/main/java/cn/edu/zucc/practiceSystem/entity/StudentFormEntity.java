package cn.edu.zucc.practiceSystem.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_form", schema = "practice_system", catalog = "")
public class StudentFormEntity {
    private int fId;
    private String studentId;
    private String studentName;
    private String teacherId;
    private String teacherName;
    private String tripleAgreement;
    private String appraisalForm;
    private String studentLog;
    private int deleteFlag;

    @Id
    @Column(name = "f_id")
    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
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
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "teacher_name")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name = "triple_agreement")
    public String getTripleAgreement() {
        return tripleAgreement;
    }

    public void setTripleAgreement(String tripleAgreement) {
        this.tripleAgreement = tripleAgreement;
    }

    @Basic
    @Column(name = "appraisal_form")
    public String getAppraisalForm() {
        return appraisalForm;
    }

    public void setAppraisalForm(String appraisalForm) {
        this.appraisalForm = appraisalForm;
    }

    @Basic
    @Column(name = "student_log")
    public String getStudentLog() {
        return studentLog;
    }

    public void setStudentLog(String studentLog) {
        this.studentLog = studentLog;
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
        StudentFormEntity that = (StudentFormEntity) o;
        return fId == that.fId &&
                deleteFlag == that.deleteFlag &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(tripleAgreement, that.tripleAgreement) &&
                Objects.equals(appraisalForm, that.appraisalForm) &&
                Objects.equals(studentLog, that.studentLog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fId, studentId, studentName, teacherId, teacherName, tripleAgreement, appraisalForm, studentLog, deleteFlag);
    }
}
