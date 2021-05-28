package cn.edu.zucc.practiceSystem.DAO;

import cn.edu.zucc.practiceSystem.entity.AdminEntity;
import cn.edu.zucc.practiceSystem.entity.TripleAgreementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TripleAgreementDao extends JpaRepository<TripleAgreementEntity,Long>, JpaSpecificationExecutor<TripleAgreementEntity> {
    TripleAgreementEntity findByStudentIdAndDeleteFlag(String studentId,int flag);
    TripleAgreementEntity findByTeacherIdAndDeleteFlag(String teacherId,int flag);
    Page<TripleAgreementEntity> findByStudentNameContainingAndDeleteFlagAndStateAndTeacherId(String studentName, Pageable pageable,int flag,String state,String teacherId);
    Page<TripleAgreementEntity> findByStudentNameContainingAndDeleteFlagAndState(String studentName, Pageable pageable,int flag,String state);
    TripleAgreementEntity findById(int id);
    @Modifying
    @Query(value = "INSERT INTO triple_agreement(student_id,student_name,teacher_id,teacher_name,triple_agreement,state,delete_flag)\n" +
            "VALUES(?,?,?,?,?,?,?)",nativeQuery = true)
    void insertIntoTripleAgreement(String studentId,String studentName,String teacherId,String teacherName,
                                   int tripleAgreement,String state,int flag);
    @Modifying
    @Query(value = "UPDATE triple_agreement SET teacher_id = ?,teacher_name = ?,\n" +
            "triple_agreement = ?,state = ?,delete_flag = ?\n" +
            "WHERE student_id = ?",nativeQuery = true)
    void updateTripleAgreement(String teacherId,String teacherName, int tripleAgreement,
                               String state,int flag,String student_id);

    @Modifying
    @Query(value = "UPDATE triple_agreement SET state = ? WHERE id = ?",nativeQuery = true)
    void updateState(String state,int id);
}
