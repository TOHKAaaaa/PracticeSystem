package cn.edu.zucc.practiceSystem.service.impl;

import cn.edu.zucc.practiceSystem.DAO.StudentDao;
import cn.edu.zucc.practiceSystem.entity.StudentEntity;
import cn.edu.zucc.practiceSystem.entity.TripleAgreementEntity;
import cn.edu.zucc.practiceSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public StudentEntity StudentLogin(String studentId, String studentPasswd) {

        StudentEntity studentEntity = studentDao.findByStudentIdAndDeleteFlag(studentId,0);

        if(studentEntity==null || !studentPasswd.equals(studentEntity.getStudentPasswd()))
            return null;
        else
            return studentEntity;
    }

    @Override
    public StudentEntity FindBySId(int sId) {
        StudentEntity studentEntity = studentDao.findBysId(sId);
        if(studentEntity==null)
            return null;
        else
            return studentEntity;
    }

    @Override
    public StudentEntity findByStudentName(String studentName) {
        return studentDao.findByStudentName(studentName);
    }

    @Override
    public StudentEntity findByStudentId(String studentId) {
        return studentDao.findByStudentId(studentId);
    }

    @Override
    public void modifyStudentPasswd(String studentPasswd, String studentId) {
        studentDao.modifyStudentPasswd(studentPasswd,studentId);
    }

    @Override
    public Page<StudentEntity> findByStudentNameContaining(int index, int size, String studentName) {
        Pageable pageable = PageRequest.of(index,size);
        return studentDao.findByStudentNameContaining(studentName,pageable);
    }

    @Override
    public TripleAgreementEntity updateStudentBysId(StudentEntity studentEntity) {
        studentDao.updateStudentBysId(studentEntity.getStudentTeacher(),studentEntity.getStudentWorkplace(),
                studentEntity.getStudentInternship(),studentEntity.getStratTime(),studentEntity.getEndTime(),
                studentEntity.getRemark(),studentEntity.getsId());
        return null;
    }

    @Override
    public Page<StudentEntity> findByStudentNameContainingAndTeacherId(int index, int size, String studentName, String teacherId) {
        Pageable pageable = PageRequest.of(index,size);
        return studentDao.findByStudentNameContainingAndStudentTeacherAndDeleteFlag(studentName,teacherId,pageable,0);
    }
}
