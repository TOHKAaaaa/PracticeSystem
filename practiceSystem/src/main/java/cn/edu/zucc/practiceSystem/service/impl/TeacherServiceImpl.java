package cn.edu.zucc.practiceSystem.service.impl;

import cn.edu.zucc.practiceSystem.DAO.TeacherDao;
import cn.edu.zucc.practiceSystem.entity.TeacherEntity;
import cn.edu.zucc.practiceSystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public TeacherEntity TeacherLogin(String teacherId, String teacherPasswd) {
        TeacherEntity teacherEntity = teacherDao.findByTeacherIdAndDeleteFlag(teacherId,0);
        if(teacherEntity==null || !teacherPasswd.equals(teacherEntity.getTeacherPasswd()))
            return null;
        else
            return teacherEntity;
    }

    @Override
    public TeacherEntity FindBytId(int tId) {
        TeacherEntity teacherEntity = teacherDao.findBytId(tId);
        if(teacherEntity==null)
            return null;
        else
            return teacherEntity;
    }

    @Override
    public TeacherEntity findByTeacherId(String teacherId) {
        return teacherDao.findByTeacherId(teacherId);
    }

    @Override
    public void modifyTeacherPasswd(String teacherPasswd, String teahcerId) {
        teacherDao.modifyTeacherPasswd(teacherPasswd,teahcerId);
    }

    @Override
    public Page<TeacherEntity> findByTeacherNameContaining(int index, int size, String teacherName) {
        Pageable pageable = PageRequest.of(index,size);
        return teacherDao.findByTeacherNameContaining(teacherName,pageable);
    }

    @Override
    public List<TeacherEntity> findByDeleteFlag() {
        return teacherDao.findByDeleteFlag(0);
    }
}
