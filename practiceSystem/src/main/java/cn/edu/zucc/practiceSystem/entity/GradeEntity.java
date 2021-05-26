package cn.edu.zucc.practiceSystem.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "grade", schema = "practice_system", catalog = "")
public class GradeEntity {
    private int gId;
    private String studentId;
    private String studentName;
    private String teacherId;
    private String teacherName;
//    1 2 3 4 5
    private Integer studentGrade;
    private Timestamp gradeTime;
    private int deleteFlag;

    @Id
    @Column(name = "g_id")
    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
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
    @Column(name = "student_grade")
    public Integer getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(Integer studentGrade) {
        this.studentGrade = studentGrade;
    }

    @Basic
    @Column(name = "grade_time")
    public Timestamp getGradeTime() {
        return gradeTime;
    }

    public void setGradeTime(Timestamp gradeTime) {
        this.gradeTime = gradeTime;
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
        GradeEntity that = (GradeEntity) o;
        return gId == that.gId &&
                deleteFlag == that.deleteFlag &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(studentGrade, that.studentGrade) &&
                Objects.equals(gradeTime, that.gradeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gId, studentId, studentName, teacherId, teacherName, studentGrade, gradeTime, deleteFlag);
    }
}
