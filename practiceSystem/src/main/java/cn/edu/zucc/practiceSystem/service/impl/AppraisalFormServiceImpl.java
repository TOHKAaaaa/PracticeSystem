package cn.edu.zucc.practiceSystem.service.impl;

import cn.edu.zucc.practiceSystem.DAO.AppraisalFormDao;
import cn.edu.zucc.practiceSystem.entity.AppraisalFormEntity;
import cn.edu.zucc.practiceSystem.service.AppraisalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppraisalFormServiceImpl implements AppraisalFormService {
    @Autowired
    AppraisalFormDao appraisalFormDao;

    @Override
    public AppraisalFormEntity findByStudentIdAndDeleteFlag(String studentId) {
        return appraisalFormDao.findByStudentIdAndDeleteFlag(studentId,0);
    }

    @Override
    public AppraisalFormEntity findByStudentId(String studentId) {
        return appraisalFormDao.findByStudentId(studentId);
    }

    @Override
    public AppraisalFormEntity findById(int id) {
        return appraisalFormDao.findById(id);
    }

    @Override
    public void insertNewAppraisalForm(AppraisalFormEntity appraisalFormEntity) {
        appraisalFormDao.insertNewAppraisalForm(appraisalFormEntity.getStudentId(),appraisalFormEntity.getStudentName(),
                appraisalFormEntity.getAppraisalForm(),appraisalFormEntity.getAppraisalFormState(),appraisalFormEntity.getTeacherId(),
                appraisalFormEntity.getTeacherName(),0);
    }

    @Override
    public Page<AppraisalFormEntity> findByStudentIdContainingAndTeacherId(int index, int size, String teacherId, String studentId) {
        Pageable pageable = PageRequest.of(index,size);
        return appraisalFormDao.findByStudentIdContainingAndTeacherIdAndDeleteFlag(studentId,teacherId,0,pageable);
    }

    @Override
    public Page<AppraisalFormEntity> findByStudentIdContainingAndDeleteFlag(int index, int size, String studentId) {
        Pageable pageable = PageRequest.of(index,size);
        return appraisalFormDao.findByStudentIdContainingAndDeleteFlag(studentId,pageable,0);
    }

    @Override
    public void updateByStudentId(int appraisalFormState, String teacherId, String studentId) {
        appraisalFormDao.updateByStudentId(appraisalFormState,teacherId,studentId);
    }

    @Override
    public void refuseAppraisalForm(int id) {
        appraisalFormDao.refuseAppraisalForm(id);
    }

    @Override
    public void updateAppraisalForm(String appraisalForm, String appraisalFormState, int id) {
        appraisalFormDao.updateAppraisalForm(appraisalForm,appraisalFormState,id);
    }
}
