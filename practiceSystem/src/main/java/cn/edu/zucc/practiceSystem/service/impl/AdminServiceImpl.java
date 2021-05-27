package cn.edu.zucc.practiceSystem.service.impl;

import cn.edu.zucc.practiceSystem.DAO.AdminDao;
import cn.edu.zucc.practiceSystem.entity.AdminEntity;
import cn.edu.zucc.practiceSystem.entity.StudentEntity;
import cn.edu.zucc.practiceSystem.entity.TeacherEntity;
import cn.edu.zucc.practiceSystem.result.StudentSatausResult;
import cn.edu.zucc.practiceSystem.service.AdminService;
import cn.edu.zucc.practiceSystem.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public AdminEntity AdminLogin(String adminName, String adminPasswd) {
        AdminEntity adminEntity = adminDao.findByAdminNameAndDeleteFlag(adminName,0);
      //  System.out.println(adminEntity);
//        System.out.println(adminPasswd);
//        adminPasswd = MD5Util.GetMD5Code(adminPasswd);
//        String passwd = MD5Util.GetMD5Code(adminPasswd);

        if(adminEntity==null || !adminPasswd.equals(adminEntity.getAdminPasswd()))
            return null;
        else
            return adminEntity;
    }

    @Override
    public AdminEntity FindByAdminId(int adminId) {
        AdminEntity adminEntity = adminDao.findByAdminId(adminId);
        if(adminEntity==null)
            return null;
        else
            return adminEntity;
    }

    @Override
    public void modifyAdminPasswd(String adminPasswd, String adminName) {
        adminDao.modifyAdminPasswd(adminPasswd,adminName);
    }

    @Override
    public void insertStudent(StudentEntity studentEntity) {
        adminDao.insertStudent(studentEntity.getStudentId(), studentEntity.getStudentPasswd(),
                studentEntity.getStudentName(), studentEntity.getStudentClass(), studentEntity.getStudentTeacher(),
                studentEntity.getStudentWorkplace(), studentEntity.getStudentInternship(), studentEntity.getStratTime(),
                studentEntity.getEndTime(),studentEntity.getRemark(),0);
    }

    @Override
    public void insertTeacher(TeacherEntity teacherEntity) {
        adminDao.insertTeacher(teacherEntity.getTeacherId(),teacherEntity.getTeacherPasswd(),
                teacherEntity.getTeacherName(),0);
    }

    @Override
    public void modifyStudent(StudentEntity studentEntity) {
        adminDao.modifyStudent(studentEntity.getStudentId(),studentEntity.getStudentPasswd(),
                studentEntity.getStudentName(),studentEntity.getStudentClass(),
                studentEntity.getStudentTeacher(),studentEntity.getStudentWorkplace(),
                studentEntity.getStudentInternship(),studentEntity.getStratTime(),
                studentEntity.getEndTime(),studentEntity.getRemark(),0,studentEntity.getsId());
    }

    @Override
    public void modifyTeacher(TeacherEntity teacherEntity) {
        adminDao.modifyTeacher(teacherEntity.getTeacherId(),teacherEntity.getTeacherPasswd(),
                teacherEntity.getTeacherName(),0,teacherEntity.gettId());
    }

    @Override
    public List<StudentSatausResult> listStudentStatus(String studentName, int index, int size) {
        int start = index * size;
        int end = (index + 1) * size;
        List<StudentSatausResult> result = new ArrayList<>();
        List<Object> list = adminDao.listStudentStatus(studentName,start,end);
        for (Object o:list){
            Object[] rowArray = (Object[]) o;
            StudentSatausResult studentSatausResult = new StudentSatausResult();
            studentSatausResult.setsId((int)rowArray[0]);
            studentSatausResult.setStudentId((String)rowArray[1]);
            studentSatausResult.setStudentName((String)rowArray[2]);
            studentSatausResult.setTeacherId((String)rowArray[3]);
            studentSatausResult.setStudentTeacher((String)rowArray[4]);
            studentSatausResult.setStudentWorkplace((String)rowArray[5]);
            studentSatausResult.setStudentInternship((String)rowArray[6]);
            studentSatausResult.setStartTime((Timestamp)rowArray[7]);
            studentSatausResult.setEndTime((Timestamp)rowArray[8]);
            studentSatausResult.setRemark((int)rowArray[9]);
            studentSatausResult.setStudentGrade((int)rowArray[10]);
            studentSatausResult.setGradeTime((Timestamp)rowArray[11]);
            result.add(studentSatausResult);
        }
        return result;
    }

    @Override
    public int countStudentStates() {
        return adminDao.countStudentStates();
    }
}
