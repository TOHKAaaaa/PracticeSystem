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
    @Query(value = "UPDATE grade SET student_grade = ? AND grade_time = NOW() WHERE g_id = ?",nativeQuery = true)
    void score(int score,int id);
}