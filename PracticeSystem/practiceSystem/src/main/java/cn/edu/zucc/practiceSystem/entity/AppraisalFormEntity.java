package cn.edu.zucc.practiceSystem.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "appraisal_form", schema = "practice_system", catalog = "")
public class AppraisalFormEntity {
    private int id;
    private String studentId;
    private String studentName;
    private String appraisalForm;
    private int appraisalFormState;
    private String teacherId;
    private String teacherName;
    private int deleteFlag;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "appraisal_form")
    public String getAppraisalForm() {
        return appraisalForm;
    }

    public void setAppraisalForm(String appraisalForm) {
        this.appraisalForm = appraisalForm;
    }

    @Basic
    @Column(name = "appraisal_form_state")
    public int getAppraisalFormState() {
        return appraisalFormState;
    }

    public void setAppraisalFormState(int appraisalFormState) {
        this.appraisalFormState = appraisalFormState;
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
        AppraisalFormEntity that = (AppraisalFormEntity) o;
        return id == that.id &&
                appraisalFormState == that.appraisalFormState &&
                Objects.equals(teacherId, that.teacherId) &&
                deleteFlag == that.deleteFlag &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(appraisalForm, that.appraisalForm) &&
                Objects.equals(teacherName, that.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, studentName, appraisalForm, appraisalFormState, teacherId, teacherName, deleteFlag);
    }
}
