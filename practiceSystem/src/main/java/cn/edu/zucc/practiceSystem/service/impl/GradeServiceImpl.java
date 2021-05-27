package cn.edu.zucc.practiceSystem.service.impl;

import cn.edu.zucc.practiceSystem.DAO.GradeDao;
import cn.edu.zucc.practiceSystem.entity.GradeEntity;
import cn.edu.zucc.practiceSystem.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeDao gradeDao;

    @Override
    public GradeEntity findByStudentId(String studentId) {
        return gradeDao.findByStudentIdAndDeleteFlag(studentId,0);
    }

    @Override
    public void score(String studentId,String studentName,String teacherId,String teacherName,int studentGrade) {
        gradeDao.score(studentId,studentName,teacherId,teacherName,studentGrade,0);
    }

    @Override
    public GradeEntity findByGId(int id) {
        return gradeDao.findBygId(id);
    }
}
