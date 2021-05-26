package cn.edu.zucc.practiceSystem.service.impl;

import cn.edu.zucc.practiceSystem.DAO.StudentLogDao;
import cn.edu.zucc.practiceSystem.entity.StudentLogEntity;
import cn.edu.zucc.practiceSystem.service.StudentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentLogServiceImpl implements StudentLogService {
    @Autowired
    StudentLogDao studentLogDao;

    @Override
    public int StudentLogSum(String studentId) {
        List<StudentLogEntity> result = studentLogDao.findByStudentIdAndDeleteFlag(studentId,0);
        return result.size();
    }

    @Override
    public Page<StudentLogEntity> findByStudentId(int index, int size, String studentId) {
        Pageable pageable = PageRequest.of(index,size);
        return studentLogDao.findByStudentIdContainingAndDeleteFlag(studentId,pageable,0);
    }

    @Override
    public void insertNewLog(StudentLogEntity studentLogEntity) {
        studentLogDao.insertNewLog(studentLogEntity.getStudentId(),studentLogEntity.getStudentName(),studentLogEntity.getLogTitle(),
                studentLogEntity.getStudentLog(),studentLogEntity.getTeacherId(),studentLogEntity.getTeacherName(),0);
    }

    @Override
    public void deleteMyLog(int id) {
        studentLogDao.deleteMyLog(1,id);
    }

    @Override
    public void reductLog(int id) {
        studentLogDao.deleteMyLog(0,id);
    }

    @Override
    public void updateById(StudentLogEntity studentLogEntity) {
        studentLogDao.updateLogById(studentLogEntity.getLogTitle(),studentLogEntity.getStudentLog(),studentLogEntity.getId());
    }

    @Override
    public Page<StudentLogEntity> findByTeacherId(int index, int size, String teacherId, String studentName) {
        Pageable pageable = PageRequest.of(index,size);
        return studentLogDao.findByStudentNameContainingAndTeacherIdAndDeleteFlag(studentName,teacherId,pageable,0);
    }

    @Override
    public Page<StudentLogEntity> findDeletedLogByTeacherId(int index, int size, String teacherId, String studentName) {
        Pageable pageable = PageRequest.of(index,size);
        return studentLogDao.findByStudentNameContainingAndTeacherIdAndDeleteFlag(studentName,teacherId,pageable,1);
    }

    @Override
    public Page<StudentLogEntity> findByDeleteFlag(int index, int size, String studentName) {
        Pageable pageable = PageRequest.of(index,size);
        return studentLogDao.findByStudentNameContainingAndDeleteFlag(studentName,pageable,0);
    }

    @Override
    public Page<StudentLogEntity> findLogByStudentId(int index, int size, String studentId) {
        Pageable pageable = PageRequest.of(index,size);
        return studentLogDao.findByStudentIdContainingAndDeleteFlag(studentId,pageable,0);
    }

    @Override
    public Page<StudentLogEntity> findDeletedLogByStudentId(int index, int size, String studentId) {
        Pageable pageable = PageRequest.of(index,size);
        return studentLogDao.findByStudentIdContainingAndDeleteFlag(studentId,pageable,1);
    }

    @Override
    public Page<StudentLogEntity> findDeletedLogByDeleteFlag(int index, int size, String studentName) {
        Pageable pageable = PageRequest.of(index,size);
        return studentLogDao.findByStudentNameContainingAndDeleteFlag(studentName,pageable,1);
    }

    @Override
    public Page<StudentLogEntity> findByStudentIdContainingAndTeacherId(int index, int size, String teacherId, String studentId) {
        Pageable pageable = PageRequest.of(index,size);
        return studentLogDao.findByStudentIdContainingAndDeleteFlagAndTeacherId(studentId,pageable,0,teacherId);
    }

    @Override
    public Page<StudentLogEntity> findDeletedLogByStudentIdContainingAndTeacherId(int index, int size, String teacherId, String studentId) {
        Pageable pageable = PageRequest.of(index,size);
        return studentLogDao.findByStudentIdContainingAndDeleteFlagAndTeacherId(studentId,pageable,1,teacherId);
    }

    @Override
    public StudentLogEntity findById(int id) {
        return studentLogDao.findById(id);
    }
}
