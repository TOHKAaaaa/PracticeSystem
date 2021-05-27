package cn.edu.zucc.practiceSystem.DAO;

import cn.edu.zucc.practiceSystem.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GradeDao extends JpaRepository<GradeEntity,Long>,JpaSpecificationExecutor<GradeEntity>{
    GradeEntity findByStudentIdAndDeleteFlag(String studentId,int flag);
    GradeEntity findBygId(int id);

    @Modifying
    @Query(value = "INSERT INTO grade(student_id,student_name,teacher_id,teacher_name,student_grade,\n" +
            "grade_time,delete_flag)\n" +
            "VALUES(?,?,?,?,?,NOW(),?)",nativeQuery = true)
    void score(String studentId,String studentName,String teacherId,String teacherName,int studentGrade,int flag);
}