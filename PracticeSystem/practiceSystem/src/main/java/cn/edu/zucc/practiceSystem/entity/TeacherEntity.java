package cn.edu.zucc.practiceSystem.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher", schema = "practice_system", catalog = "")
public class TeacherEntity {
    private int tId;
    private String teacherId;
    private String teacherPasswd;
    private String teacherName;
    private int deleteFlag;

    @Id
    @Column(name = "t_id")
    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
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
    @Column(name = "teacher_passwd")
    public String getTeacherPasswd() {
        return teacherPasswd;
    }

    public void setTeacherPasswd(String teacherPasswd) {
        this.teacherPasswd = teacherPasswd;
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
        TeacherEntity that = (TeacherEntity) o;
        return tId == that.tId &&
                deleteFlag == that.deleteFlag &&
                Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(teacherPasswd, that.teacherPasswd) &&
                Objects.equals(teacherName, that.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tId, teacherId, teacherPasswd, teacherName, deleteFlag);
    }
}
