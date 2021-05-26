package cn.edu.zucc.practiceSystem.service;

import cn.edu.zucc.practiceSystem.entity.StudentLogEntity;
import org.springframework.data.domain.Page;

public interface StudentLogService {
    public int StudentLogSum(String studentId);
    public Page<StudentLogEntity> findByStudentId(int index, int size,String studentId);
    public void insertNewLog(StudentLogEntity studentLogEntity);
    public void deleteMyLog(int id);
    public void reductLog(int id);
    public void updateById(StudentLogEntity studentLogEntity);
    public Page<StudentLogEntity> findByTeacherId(int index,int size,String teacherId,String studentName);
    public Page<StudentLogEntity> findDeletedLogByTeacherId(int index,int size,String teacherId,String studentName);
    public Page<StudentLogEntity> findByDeleteFlag(int index,int size,String studentName);
    public Page<StudentLogEntity> findLogByStudentId(int index,int size,String studentId);
    public Page<StudentLogEntity> findDeletedLogByStudentId(int index,int size,String studentId);
    public Page<StudentLogEntity> findDeletedLogByDeleteFlag(int index,int size,String studentName);
    public Page<StudentLogEntity> findByStudentIdContainingAndTeacherId(int index,int size,String teacherId,String studentId);
    public Page<StudentLogEntity> findDeletedLogByStudentIdContainingAndTeacherId(int index,int size,String teacherId,String studentId);
    public StudentLogEntity findById(int id);
}
