package cn.edu.zucc.practiceSystem.service;

import cn.edu.zucc.practiceSystem.entity.StudentEntity;
import cn.edu.zucc.practiceSystem.entity.TripleAgreementEntity;
import org.springframework.data.domain.Page;

public interface StudentService {
    //    学生登录
    public StudentEntity StudentLogin(String studentId, String studentPasswd);
    public StudentEntity FindBySId(int sId);
    public StudentEntity findByStudentName(String studentName);
    public StudentEntity findByStudentId(String studentId);
    public void modifyStudentPasswd(String studentPasswd,String studentId);
    public Page<StudentEntity> findByStudentNameContaining(int index, int size, String studentName);
    public TripleAgreementEntity updateStudentBysId(StudentEntity studentEntity);
    public Page<StudentEntity> findByStudentNameContainingAndTeacherId(int index, int size, String studentName,String teacherId);
}
