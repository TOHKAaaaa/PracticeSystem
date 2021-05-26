package cn.edu.zucc.practiceSystem.DAO;

import cn.edu.zucc.practiceSystem.entity.InternshipLibraryEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InternshipLibraryDao extends JpaRepository<InternshipLibraryEntity,Long>, JpaSpecificationExecutor<InternshipLibraryEntity> {
    //  增
    @Modifying
    @Query(value = "INSERT INTO internship_library(internship_name,internship_address,\n" +
            "teacher_name,consultation_name,consultation_duty,consultation_smartphone,\n" +
            "consultation_fixedphone,consultation_mail,delete_flag)\n" +
            " VALUES(?,?,?,?,?,?,?,?,?)",nativeQuery = true)
    void insertInternshipLibrary(String internshipName,String internshipAddress,String teacherName,String consultationName,
                                 String consultationDuty,String consultationSmartphone,String consultationFixedphone,String consultationMail,
                                 int deleteFlag);
    //  删

    //  查
    List<InternshipLibraryEntity> findAll();
    List<InternshipLibraryEntity> findAllByDeleteFlag(int flag);
    Page<InternshipLibraryEntity> findByInternshipNameContainingAndDeleteFlag(String internshipLibraryName, int flag, Pageable pageable);
    InternshipLibraryEntity findByInternshipIdAndDeleteFlag(int internshipLibraryId,int flag);
    Page<InternshipLibraryEntity> findByInternshipNameContainingAndTeacherName(String internshipLibraryName, Pageable pageable,String teacherName);
    Page<InternshipLibraryEntity> findByInternshipNameContaining(String internshipLibraryName, Pageable pageable);
    InternshipLibraryEntity findByInternshipId(int internshipLibrarId);
    InternshipLibraryEntity findByInternshipNameAndDeleteFlag(String internshipLibraryName, int flag);
    //  改
    @Modifying
    @Query(value = "UPDATE internship_library SET internship_name = ?,internship_address = ?,\n" +
            "teacher_name = ? ,consultation_name = ?,\n" +
            "consultation_duty = ?,consultation_smartphone = ?,\n" +
            "consultation_fixedphone = ?,consultation_mail = ?,delete_flag = ?\n" +
            "WHERE internship_id = ?",nativeQuery = true)
    void modifyInternshipInformation(String internshipName,String internshipAddress,String teacherName,String consultationName,
                                     String consultationDuty,String consultationSmartphone,String consultationFixedphone,String consultationMail,
                                     int deleteFlag,int internshipId);
}
