package cn.edu.zucc.practiceSystem.DAO;

import cn.edu.zucc.practiceSystem.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.sql.Timestamp;

public interface StudentDao extends JpaRepository<StudentEntity,Long>, JpaSpecificationExecutor<StudentEntity> {
    //  通过studentId查询学生
    StudentEntity findByStudentIdAndDeleteFlag(String studentId,int flag);
    //  通过sId查询学生
    StudentEntity findBysId(int sId);
    StudentEntity findByStudentName(String studentName);
    StudentEntity findByStudentId(String studentId);
    //  修改学生密码
    @Modifying
    @Query(value = "UPDATE student SET student_passwd = ? WHERE student_id = ? AND delete_flag = 0",nativeQuery = true)
    void modifyStudentPasswd(String studentPasswd,String studentId);

    Page<StudentEntity> findByStudentNameContaining(String studentName, Pageable pageable);
    Page<StudentEntity> findByStudentNameContainingAndStudentTeacherAndDeleteFlag(String studentName,String studentTeacher,Pageable pageable,int flag);

    //  增加
    @Modifying
    @Query(value = "UPDATE student SET student_teacher = ?,student_workplace = ?,student_Internship = ?,\n" +
            "strat_time = ?,end_time = ?,remark = ?\n" +
            "WHERE s_id = ?",nativeQuery = true)
    void updateStudentBysId(String studentTeacher, String studentWorkplace, String studentInternship,
                                     Timestamp startTime, Timestamp endTime,String remark,int sId);
}
