package cn.edu.zucc.practiceSystem.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "internship_library", schema = "practice_system", catalog = "")
public class InternshipLibraryEntity {
    private int internshipId;
    private String internshipName;
    private String internshipAddress;
    private String teacherName;
    private String consultationName;
    private String consultationDuty;
    private String consultationSmartphone;
    private String consultationFixedphone;
    private String consultationMail;
    private Integer internshipState;
    private int deleteFlag;

    @Id
    @Column(name = "internship_id")
    public int getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(int internshipId) {
        this.internshipId = internshipId;
    }

    @Basic
    @Column(name = "internship_name")
    public String getInternshipName() {
        return internshipName;
    }

    public void setInternshipName(String internshipName) {
        this.internshipName = internshipName;
    }

    @Basic
    @Column(name = "internship_address")
    public String getInternshipAddress() {
        return internshipAddress;
    }

    public void setInternshipAddress(String internshipAddress) {
        this.internshipAddress = internshipAddress;
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
    @Column(name = "consultation_name")
    public String getConsultationName() {
        return consultationName;
    }

    public void setConsultationName(String consultationName) {
        this.consultationName = consultationName;
    }

    @Basic
    @Column(name = "consultation_duty")
    public String getConsultationDuty() {
        return consultationDuty;
    }

    public void setConsultationDuty(String consultationDuty) {
        this.consultationDuty = consultationDuty;
    }

    @Basic
    @Column(name = "consultation_smartphone")
    public String getConsultationSmartphone() {
        return consultationSmartphone;
    }

    public void setConsultationSmartphone(String consultationSmartphone) {
        this.consultationSmartphone = consultationSmartphone;
    }

    @Basic
    @Column(name = "consultation_fixedphone")
    public String getConsultationFixedphone() {
        return consultationFixedphone;
    }

    public void setConsultationFixedphone(String consultationFixedphone) {
        this.consultationFixedphone = consultationFixedphone;
    }

    @Basic
    @Column(name = "consultation_mail")
    public String getConsultationMail() {
        return consultationMail;
    }

    public void setConsultationMail(String consultationMail) {
        this.consultationMail = consultationMail;
    }

    @Basic
    @Column(name = "internship_state")
    public Integer getInternshipState() {
        return internshipState;
    }

    public void setInternshipState(Integer internshipState) {
        this.internshipState = internshipState;
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
        InternshipLibraryEntity that = (InternshipLibraryEntity) o;
        return internshipId == that.internshipId &&
                deleteFlag == that.deleteFlag &&
                Objects.equals(internshipName, that.internshipName) &&
                Objects.equals(internshipAddress, that.internshipAddress) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(consultationName, that.consultationName) &&
                Objects.equals(consultationDuty, that.consultationDuty) &&
                Objects.equals(consultationSmartphone, that.consultationSmartphone) &&
                Objects.equals(consultationFixedphone, that.consultationFixedphone) &&
                Objects.equals(consultationMail, that.consultationMail) &&
                Objects.equals(internshipState, that.internshipState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internshipId, internshipName, internshipAddress, teacherName, consultationName, consultationDuty, consultationSmartphone, consultationFixedphone, consultationMail, internshipState, deleteFlag);
    }
}
