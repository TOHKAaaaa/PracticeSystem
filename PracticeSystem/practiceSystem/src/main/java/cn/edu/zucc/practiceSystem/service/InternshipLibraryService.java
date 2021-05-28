package cn.edu.zucc.practiceSystem.service;

import cn.edu.zucc.practiceSystem.entity.InternshipLibraryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InternshipLibraryService {
    public List<InternshipLibraryEntity> findAll();
    public List<InternshipLibraryEntity> findAllByDeleteFlag();
    public Page<InternshipLibraryEntity> findByInternshipLibraryNameAndDeleteFlag(int index, int size, String internshipLibraryName);
    public void insertInternshipLibrary(InternshipLibraryEntity internshipLibraryEntity);
    public Page<InternshipLibraryEntity> findByInternshipLibraryNameAndDeleteFlagAndTeacherName(int index, int size, String internshipLibraryName,String teacherName);
    public Page<InternshipLibraryEntity> findByInternshipNameContaining(int index, int size, String internshipLibraryName);
    public InternshipLibraryEntity findByInternshipLibraryId(int InternshipLibraryId);
    public void modifyInternshipLibrary(InternshipLibraryEntity internshipLibraryEntity);
    public InternshipLibraryEntity findByInternshipNameAndDeleteFlag(String internshipLibraryName);
}
