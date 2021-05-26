package cn.edu.zucc.practiceSystem.service;

import cn.edu.zucc.practiceSystem.entity.GradeEntity;

public interface GradeService {
//    根据studentId查询学生成绩信息
    public GradeEntity findByStudentId(String studentId);
    public void score(int score,int id);
    public GradeEntity findByGId(int id);
}
