package cn.edu.zucc.practiceSystem.service.impl;

import cn.edu.zucc.practiceSystem.DAO.TripleAgreementDao;
import cn.edu.zucc.practiceSystem.entity.InternshipLibraryEntity;
import cn.edu.zucc.practiceSystem.entity.TripleAgreementEntity;
import cn.edu.zucc.practiceSystem.service.TripleAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TripleAgreementServiceImpl implements TripleAgreementService {
    @Autowired
    TripleAgreementDao tripleAgreementDao;

    @Override
    public TripleAgreementEntity findByStudentId(String studentId) {
        return tripleAgreementDao.findByStudentIdAndDeleteFlag(studentId,0);
    }

    @Override
    public void insertIntoTripleAgreement(TripleAgreementEntity tripleAgreementEntity) {
        tripleAgreementDao.insertIntoTripleAgreement(tripleAgreementEntity.getStudentId(),tripleAgreementEntity.getStudentName(),
                tripleAgreementEntity.getTeacherId(),tripleAgreementEntity.getTeacherName(),tripleAgreementEntity.getTripleAgreement(),
                tripleAgreementEntity.getState(),0);
    }

    @Override
    public void updateTripleAgreement(TripleAgreementEntity tripleAgreementEntity) {
        tripleAgreementDao.updateTripleAgreement(tripleAgreementEntity.getTeacherId(),tripleAgreementEntity.getTeacherName(),
                tripleAgreementEntity.getTripleAgreement(),tripleAgreementEntity.getState(),tripleAgreementEntity.getDeleteFlag(),
                tripleAgreementEntity.getStudentId());
    }

    @Override
    public TripleAgreementEntity findByTeacherId(String teacherId) {
        return tripleAgreementDao.findByTeacherIdAndDeleteFlag(teacherId,0);
    }

    @Override
    public Page<TripleAgreementEntity> findByTeacherIdAndDeleteFlag(int index, int size, String teacherId,String studentName,String state) {
        Pageable pageable = PageRequest.of(index,size);
        return tripleAgreementDao.findByStudentNameContainingAndDeleteFlagAndStateAndTeacherId(studentName,pageable,0,state,teacherId);
    }

    @Override
    public Page<TripleAgreementEntity> findByStudentNameContainingAndDeleteFlagAndState(int index, int size, String studentName, String state) {
        Pageable pageable = PageRequest.of(index,size);
        return tripleAgreementDao.findByStudentNameContainingAndDeleteFlagAndState(studentName,pageable,0,state);
    }

    @Override
    public TripleAgreementEntity findById(int id) {
        return tripleAgreementDao.findById(id);
    }

    @Override
    public void updateState(String state, int id) {
        tripleAgreementDao.updateState(state,id);
    }
}
