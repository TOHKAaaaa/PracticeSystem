package cn.edu.zucc.practiceSystem.service;

import cn.edu.zucc.practiceSystem.entity.InternshipLibraryEntity;
import cn.edu.zucc.practiceSystem.entity.TripleAgreementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TripleAgreementService {
    public TripleAgreementEntity findByStudentId(String studentId);
    public void insertIntoTripleAgreement(TripleAgreementEntity tripleAgreementEntity);
    public void updateTripleAgreement(TripleAgreementEntity tripleAgreementEntity);
    public TripleAgreementEntity findByTeacherId(String teacherId);
    public Page<TripleAgreementEntity> findByTeacherIdAndDeleteFlag(int index, int size, String teacherId,String studentName,String state);
    public Page<TripleAgreementEntity> findByStudentNameContainingAndDeleteFlagAndState(int index, int size,String studentName,String state);
    public TripleAgreementEntity findById(int id);
    public void updateState(String state,int id);
}
