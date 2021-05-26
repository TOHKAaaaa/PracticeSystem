package cn.edu.zucc.practiceSystem.DAO;

import cn.edu.zucc.practiceSystem.entity.AdminEntity;
import cn.edu.zucc.practiceSystem.entity.StudentLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentLogDao extends JpaRepository<StudentLogEntity,Long>, JpaSpecificationExecutor<StudentLogEntity> {
    List<StudentLogEntity> findByStudentIdAndDeleteFlag(String studentId,int flag);
    Page<StudentLogEntity> findByStudentIdContainingAndDeleteFlag(String studentId, Pageable pageable,int flag);
    Page<StudentLogEntity> findByStudentNameContainingAndTeacherIdAndDeleteFlag(String studentName,String teacherId, Pageable pageable,int flag);
    Page<StudentLogEntity> findByTeacherId(String teacherId, Pageable pageable);
    Page<StudentLogEntity> findByStudentNameContainingAndDeleteFlag(String studentName, Pageable pageable,int flag);
    Page<StudentLogEntity> findByStudentIdContainingAndDeleteFlagAndTeacherId(String studentId, Pageable pageable,int flag,String teacherId);
    StudentLogEntity findById(int id);

    @Modifying
    @Query(value = "INSERT INTO student_log(student_id,student_name,log_title,student_log,teacher_id,teacher_name,delete_flag)\n" +
            "VALUES(?,?,?,?,?,?,?)",nativeQuery = true)
    void insertNewLog(String studentId,String studentName,String logTitle,String studentLog,String teacherId,String teacherName,int flag);

    @Modifying
    @Query(value = "UPDATE student_log SET delete_flag = ? WHERE id = ?",nativeQuery = true)
    void deleteMyLog(int flag,int id);

    @Modifying
    @Query(value = "UPDATE student_log SET log_title = ?,student_log = ? WHERE id = ?",nativeQuery = true)
    void updateLogById(String logTitle,String studentLog,int id);

}
