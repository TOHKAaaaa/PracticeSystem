package cn.edu.zucc.practiceSystem.service;

import cn.edu.zucc.practiceSystem.entity.StudentEntity;
import cn.edu.zucc.practiceSystem.entity.TeacherEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    //    指导老师登录
    public TeacherEntity TeacherLogin(String teacherId, String teacherPasswd);
    public TeacherEntity FindBytId(int tId);
    public TeacherEntity findByTeacherId(String teacherId);
    public void modifyTeacherPasswd(String teacherPasswd,String teahcerId);
    Page<TeacherEntity> findByTeacherNameContaining(int index, int size, String teacherName);
    public List<TeacherEntity> findByDeleteFlag();
}
