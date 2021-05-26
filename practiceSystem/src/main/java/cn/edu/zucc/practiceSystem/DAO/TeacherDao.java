package cn.edu.zucc.practiceSystem.DAO;

import cn.edu.zucc.practiceSystem.entity.StudentEntity;
import cn.edu.zucc.practiceSystem.entity.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherDao extends JpaRepository<TeacherEntity,Long>, JpaSpecificationExecutor<TeacherEntity> {
    //  通过teacherId查询老师
    TeacherEntity findByTeacherIdAndDeleteFlag(String teacherId,int flag);
    //  通过tId查询老师 
    TeacherEntity findBytId(int tId);
    TeacherEntity findByTeacherId(String teacherId);
    List<TeacherEntity> findByDeleteFlag(int flag);
    //  修改老师密码
    @Modifying
    @Query(value = "UPDATE teacher SET teacher_passwd = ? WHERE teacher_id = ? AND delete_flag = 0",nativeQuery = true)
    void modifyTeacherPasswd(String teacherPasswd,String teacherId);

    Page<TeacherEntity> findByTeacherNameContaining(String teacherName, Pageable pageable);
}
