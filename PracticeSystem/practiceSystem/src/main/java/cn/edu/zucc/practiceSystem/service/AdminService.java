package cn.edu.zucc.practiceSystem.service;

import cn.edu.zucc.practiceSystem.entity.AdminEntity;
import cn.edu.zucc.practiceSystem.entity.StudentEntity;
import cn.edu.zucc.practiceSystem.entity.TeacherEntity;
import cn.edu.zucc.practiceSystem.result.StudentSatausResult;

import java.util.List;
import java.util.Map;

public interface AdminService {
//    管理员登录
    public AdminEntity AdminLogin(String adminName, String adminPasswd);
    public AdminEntity FindByAdminId(int adminId);
    public void modifyAdminPasswd(String adminPasswd,String adminName);
    public void insertStudent(StudentEntity studentEntity);
    public void insertTeacher(TeacherEntity teacherEntity);
    public void modifyStudent(StudentEntity studentEntity);
    public void modifyTeacher(TeacherEntity teacherEntity);
    public List<Map<String,Object>> listStudentStatus(String studentName, int index, int size);
    public int countStudentStates();
}
