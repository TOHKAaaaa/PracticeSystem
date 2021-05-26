package cn.edu.zucc.practiceSystem.service.impl;

import cn.edu.zucc.practiceSystem.DAO.InternshipLibraryDao;
import cn.edu.zucc.practiceSystem.entity.InternshipLibraryEntity;
import cn.edu.zucc.practiceSystem.service.InternshipLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipLibraryServiceImpl implements InternshipLibraryService {
    @Autowired
    InternshipLibraryDao internshipLibraryDao;

    @Override
    public List<InternshipLibraryEntity> findAll() {
        return internshipLibraryDao.findAll();
    }

    @Override
    public List<InternshipLibraryEntity> findAllByDeleteFlag() {
        List<InternshipLibraryEntity> result = internshipLibraryDao.findAllByDeleteFlag(0);
        return result;
    }

    @Override
    public Page<InternshipLibraryEntity> findByInternshipLibraryNameAndDeleteFlag(int index, int size, String internshipLibraryName) {
        Pageable pageable = PageRequest.of(index,size);
        return internshipLibraryDao.findByInternshipNameContainingAndDeleteFlag(internshipLibraryName,0,pageable);
    }

    @Override
    public void insertInternshipLibrary(InternshipLibraryEntity internshipLibraryEntity) {
        internshipLibraryDao.insertInternshipLibrary(internshipLibraryEntity.getInternshipName(),internshipLibraryEntity.getInternshipAddress(),
                internshipLibraryEntity.getTeacherName(), internshipLibraryEntity.getConsultationName(),internshipLibraryEntity.getConsultationDuty(),
                internshipLibraryEntity.getConsultationSmartphone(),internshipLibraryEntity.getConsultationFixedphone(),
                internshipLibraryEntity.getConsultationMail(),internshipLibraryEntity.getDeleteFlag());
    }

    @Override
    public Page<InternshipLibraryEntity> findByInternshipLibraryNameAndDeleteFlagAndTeacherName(int index, int size, String internshipLibraryName, String teacherName) {
        Pageable pageable = PageRequest.of(index,size);
        return internshipLibraryDao.findByInternshipNameContainingAndTeacherName(internshipLibraryName,pageable,teacherName);
    }

    @Override
    public Page<InternshipLibraryEntity> findByInternshipNameContaining(int index, int size, String internshipLibraryName) {
        Pageable pageable = PageRequest.of(index,size);
        return internshipLibraryDao.findByInternshipNameContaining(internshipLibraryName,pageable);
    }

    @Override
    public InternshipLibraryEntity findByInternshipLibraryId(int InternshipLibraryId) {
        return internshipLibraryDao.findByInternshipId(InternshipLibraryId);
    }

    @Override
    public void modifyInternshipLibrary(InternshipLibraryEntity internshipLibraryEntity) {
        internshipLibraryDao.modifyInternshipInformation(internshipLibraryEntity.getInternshipName(),internshipLibraryEntity.getInternshipAddress(),
                internshipLibraryEntity.getTeacherName(), internshipLibraryEntity.getConsultationName(),internshipLibraryEntity.getConsultationDuty(),
                internshipLibraryEntity.getConsultationSmartphone(),internshipLibraryEntity.getConsultationFixedphone(),
                internshipLibraryEntity.getConsultationMail(),internshipLibraryEntity.getDeleteFlag(),internshipLibraryEntity.getInternshipId());
    }

    @Override
    public InternshipLibraryEntity findByInternshipNameAndDeleteFlag(String internshipLibraryName) {
        return internshipLibraryDao.findByInternshipNameAndDeleteFlag(internshipLibraryName,0);
    }

}
