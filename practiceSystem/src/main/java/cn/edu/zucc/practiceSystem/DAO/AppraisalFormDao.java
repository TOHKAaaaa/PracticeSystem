package cn.edu.zucc.practiceSystem.DAO;

import cn.edu.zucc.practiceSystem.entity.AdminEntity;
import cn.edu.zucc.practiceSystem.entity.AppraisalFormEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AppraisalFormDao extends JpaRepository<AppraisalFormEntity,Long>, JpaSpecificationExecutor<AppraisalFormEntity> {
    AppraisalFormEntity findByStudentIdAndDeleteFlag(String studentId,int flag);
    AppraisalFormEntity findByStudentId(String studentId);

    @Modifying
    @Query(value = "INSERT INTO appraisal_form(student_id,student_name,appraisal_form,appraisal_form_state,\n" +
            "teacher_id,teacher_name,delete_flag)\n" +
            "VALUES(?,?,?,?,?,?,?)",nativeQuery = true)
    void insertNewAppraisalForm(String studentId,String studentName,String appraisalForm,int appraisalFormState,
                                String teacherId,String teacherName,int flag);

    Page<AppraisalFormEntity> findByStudentIdContainingAndTeacherIdAndDeleteFlag(String studentId, String teacherId, int flag, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE appraisal_form SET appraisal_form_state = ? WHERE teacher_id = ? AND student_id = ? AND delete_flag = 0",nativeQuery = true)
    void updateByStudentId(int appraisalFormState,String teacherId,String studentId);

    @Modifying
    @Query(value = "UPDATE appraisal_form SET appraisal_form_state = 1 AND delete_flag = 1 WHERE id = ?",nativeQuery = true)
    void refuseAppraisalForm(int id);

    @Modifying
    @Query(value = "UPDATE appraisal_form SET appraisal_form = ?,appraisal_form_state = ? WHERE id = ?",nativeQuery = true)
    void updateAppraisalForm(String appraisalForm,int appraisalFormState,int id);
}
