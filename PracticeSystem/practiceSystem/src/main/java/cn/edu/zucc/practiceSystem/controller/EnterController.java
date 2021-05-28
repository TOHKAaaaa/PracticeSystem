package cn.edu.zucc.practiceSystem.controller;

import cn.edu.zucc.practiceSystem.entity.*;
import cn.edu.zucc.practiceSystem.result.ExceptionMsg;
import cn.edu.zucc.practiceSystem.result.Response;
import cn.edu.zucc.practiceSystem.result.ResponseData;
import cn.edu.zucc.practiceSystem.result.ResultEntity;
import cn.edu.zucc.practiceSystem.service.AdminService;
import cn.edu.zucc.practiceSystem.service.StudentService;
import cn.edu.zucc.practiceSystem.service.TeacherService;
import cn.edu.zucc.practiceSystem.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

@Api("登录模块")
@Transactional
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/enter")
public class EnterController {
//    private int adminId; //管理员序号
//    private String adminName; //管理员账号
//    private String adminPasswd; //管理员密码
    protected Response result(ExceptionMsg msg){
        return new Response(msg);
    }
    protected Response result(){
        return new Response();
    }

    @Autowired
    AdminService adminService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseData Login(@RequestBody ResultEntity resultEntity){
//        管理员 role=3
//        指导老师 role=2
//        学生 role=1
        int role = resultEntity.getRole();
        AdminEntity adminEntity = null;
        StudentEntity studentEntity = null;
        TeacherEntity teacherEntity = null;

        if(role==1)
            studentEntity = studentService.StudentLogin(resultEntity.getUsername(),resultEntity.getPassword());
        else if(role==2)
            teacherEntity = teacherService.TeacherLogin(resultEntity.getUsername(),resultEntity.getPassword());
        else if(role==3)
            adminEntity = adminService.AdminLogin(resultEntity.getUsername(),resultEntity.getPassword());

        if(adminEntity==null&&teacherEntity==null&&studentEntity==null){
            return new ResponseData(new Response("405","账号不存在或密码错误"),null);
        }
        else {
            String token = null;
            String name = null;
            JSONObject jsonObject = new JSONObject();
            try {
                if(role==3){
                    token = JwtUtil.sign(adminEntity.getAdminId(),resultEntity.getRole());
                    name = adminEntity.getAdminName();
                }
                else if(role==2) {
                    token = JwtUtil.sign(teacherEntity.gettId(), resultEntity.getRole());
                    name = teacherEntity.getTeacherName();
                }
                else if(role==1) {
                    token = JwtUtil.sign(studentEntity.getsId(), resultEntity.getRole());
                    name = studentEntity.getStudentName();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            jsonObject.put("token",token);
            jsonObject.put("name",name);
            return new ResponseData(ExceptionMsg.SUCCESS,jsonObject);
        }
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/passwordchanging",method = RequestMethod.POST)
    public ResponseData modifyPassword(@RequestBody PasswordEntity passwordEntity,HttpServletRequest httpServletRequest){
        //        管理员 role=3
        //        指导老师 role=2
        //        学生 role=1
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            String oldpwd = passwordEntity.getOldpwd();
            String newpwd = passwordEntity.getNewpwd();
//            System.out.println(oldpwd);
//            System.out.println(newpwd);
            int id = JwtUtil.verifyToken(token).get("userid").asInt();
//            System.out.println(id);
//            System.out.println(JwtUtil.verifyToken(token).get("role").asInt());
            if(JwtUtil.verifyToken(token).get("role").asInt()==1){
                StudentEntity studentEntity = studentService.FindBySId(id);
                if(studentEntity==null || !studentEntity.getStudentPasswd().equals(oldpwd))
                    return new ResponseData(new Response("405","用户不存在或密码错误"),null);
                studentService.modifyStudentPasswd(newpwd,studentEntity.getStudentId());
                return new ResponseData(ExceptionMsg.SUCCESS,null);
            }
            else if(JwtUtil.verifyToken(token).get("role").asInt()==2){
                TeacherEntity teacherEntity = teacherService.FindBytId(id);
                if(teacherEntity==null || !teacherEntity.getTeacherPasswd().equals(oldpwd))
                    return new ResponseData(new Response("405","用户不存在或密码错误"),null);
                teacherService.modifyTeacherPasswd(newpwd,teacherEntity.getTeacherId());
                return new ResponseData(ExceptionMsg.SUCCESS,null);

            }
            else if(JwtUtil.verifyToken(token).get("role").asInt()==3){
                AdminEntity adminEntity = adminService.FindByAdminId(id);
                if(adminEntity==null || !adminEntity.getAdminPasswd().equals(oldpwd))
                    return new ResponseData(new Response("405","用户不存在或密码错误"),null);
                adminService.modifyAdminPasswd(newpwd,adminEntity.getAdminName());
                return new ResponseData(ExceptionMsg.SUCCESS,null);
            }
            else
                return new ResponseData(ExceptionMsg.FAILED,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }
}
