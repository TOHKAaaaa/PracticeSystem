package cn.edu.zucc.practiceSystem.service;

import cn.edu.zucc.practiceSystem.entity.AppraisalFormEntity;
import org.springframework.data.domain.Page;

public interface AppraisalFormService {
    public AppraisalFormEntity findByStudentIdAndDeleteFlag(String studentId);
    public AppraisalFormEntity findByStudentId(String studentId);
    public void insertNewAppraisalForm(AppraisalFormEntity appraisalFormEntity);
    public Page<AppraisalFormEntity> findByStudentIdContainingAndTeacherId(int index,int size,String teacherId,String studentId);
    public void updateByStudentId(int appraisalFormState,String teacherId,String studentId);
    public void refuseAppraisalForm(int id);
    public void updateAppraisalForm(String appraisalForm,int appraisalFormState,int id);
}
