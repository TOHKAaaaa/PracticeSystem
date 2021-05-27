package cn.edu.zucc.practiceSystem.DAO;

import cn.edu.zucc.practiceSystem.entity.AdminEntity;
import cn.edu.zucc.practiceSystem.entity.TeacherEntity;
import cn.edu.zucc.practiceSystem.result.StudentSatausResult;
import org.bouncycastle.asn1.x509.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface AdminDao extends JpaRepository<AdminEntity,Long>, JpaSpecificationExecutor<AdminEntity> {
//  通过adminName查询管理员
    AdminEntity findByAdminNameAndDeleteFlag(String adminName,int flag);
    //  通过tId查询老师
    AdminEntity findByAdminId(int adminId);
    //  修改管理员密码
    @Modifying
    @Query(value = "UPDATE admin SET admin_passwd = ? WHERE admin_name = ? AND delete_flag = 0",nativeQuery = true)
    void modifyAdminPasswd(String adminPasswd,String adminName);
    //管理员添加学生
    @Modifying
    @Query(value = "INSERT INTO student(student_id,student_passwd,student_name,student_class,\n" +
            "student_teacher,student_workplace,student_Internship,strat_time,end_time,\n" +
            "remark,delete_flag) VALUES(?,?,?,?,?,?,?,?,?,?,?)",nativeQuery = true)
    void insertStudent(String studentId, String studentPasswd, String studentName, String studentClass,
                       String studentTeacher, String studentWorkplace, String studentInternship,
                       Timestamp startTime, Timestamp endTime,String remark,int flag);
    @Modifying
    @Query(value = "INSERT INTO teacher(teacher_id,teacher_passwd,teacher_name,delete_flag) VALUES(?,?,?,?)",nativeQuery = true)
    void insertTeacher(String teacherId,String teacherPasswd,String teahcerName,int flag);

    @Modifying
    @Query(value = "UPDATE student SET student_id = ?,student_passwd = ?,student_name = ?,student_class = ?,\n" +
            "student_teacher = ?,student_workplace = ?,student_Internship = ?,\n" +
            "strat_time = ?,end_time = ?,remark = ?,delete_flag = ? WHERE s_id = ?",nativeQuery = true)
    void modifyStudent(String studentId,String studentPasswd,String studentName,String studentClass,String studentTeacher,String studentWorkplace,
                       String studentInternship,Timestamp startTime,Timestamp endTime,String remark,int flag,int sId);

    @Modifying
    @Query(value = "UPDATE teacher SET teacher_id = ?,teacher_passwd = ?," +
            "teacher_name = ?,delete_flag = ? WHERE t_id = ?",nativeQuery = true)
    void modifyTeacher(String teacherId,String teacherPasswd,String teacherName,int flag,int tId);

    @Modifying
    @Query(value = "SELECT student.s_id,student.student_id,student.student_name,student.student_class,\n" +
            "grade.teacher_id,student.student_teacher,student.student_workplace,\n" +
            "student.student_Internship,student.strat_time,student.end_time,\n" +
            "student.remark,grade.student_grade,grade.grade_time\n" +
            "FROM student LEFT JOIN grade\n" +
            "ON student.student_id = grade.student_id\n" +
            "WHERE student.student_name LIKE \"%?%\"\n" +
            "LIMIT ?,?",nativeQuery = true)
    List<Object> listStudentStatus(String studentName, int start, int end);

    @Modifying
    @Query(value = "SELECT COUNT(1)\n" +
            "FROM student LEFT JOIN grade\n" +
            "ON student.student_id = grade.student_id",nativeQuery = true)
    int countStudentStates();
}
