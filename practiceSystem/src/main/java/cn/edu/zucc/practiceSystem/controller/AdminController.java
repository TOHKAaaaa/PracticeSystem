package cn.edu.zucc.practiceSystem.controller;

import cn.edu.zucc.practiceSystem.entity.InternshipLibraryEntity;
import cn.edu.zucc.practiceSystem.entity.StudentEntity;
import cn.edu.zucc.practiceSystem.entity.StudentLogEntity;
import cn.edu.zucc.practiceSystem.entity.TeacherEntity;
import cn.edu.zucc.practiceSystem.result.ExceptionMsg;
import cn.edu.zucc.practiceSystem.result.Response;
import cn.edu.zucc.practiceSystem.result.ResponseData;
import cn.edu.zucc.practiceSystem.service.*;
import cn.edu.zucc.practiceSystem.util.JwtUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api("管理员模块")
@Transactional
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class AdminController {
    protected Response result(ExceptionMsg msg){
        return new Response(msg);
    }
    protected Response result(){
        return new Response();
    }
    //  公司增删改查
    @Autowired
    InternshipLibraryService internshipLibraryService;
    @Autowired
    AdminService adminService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    TripleAgreementService tripleAgreementService;
    @Autowired
    StudentLogService studentLogService;
    @Autowired
    AppraisalFormService appraisalFormService;

    @ApiOperation("管理员查看所有公司（包含未审核的）")
    @RequestMapping(value = "/listallcompany",method = RequestMethod.GET)
    public ResponseData listAllCompany(HttpServletRequest httpServletRequest, @RequestParam String name,
                                       @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            return new ResponseData(ExceptionMsg.SUCCESS,internshipLibraryService.findByInternshipNameContaining(pageIndex,pageSize,name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员通过审核")
    @RequestMapping(value = "/pass",method = RequestMethod.GET)
    public ResponseData passCompany(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            InternshipLibraryEntity internshipLibraryEntity = internshipLibraryService.findByInternshipLibraryId(id);
            internshipLibraryEntity.setDeleteFlag(0);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
            return new ResponseData(ExceptionMsg.SUCCESS,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员拒绝审核")
    @RequestMapping(value = "/refuse",method = RequestMethod.GET)
    public ResponseData refuseCompany(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            InternshipLibraryEntity internshipLibraryEntity = internshipLibraryService.findByInternshipLibraryId(id);
            internshipLibraryEntity.setDeleteFlag(1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
            return new ResponseData(ExceptionMsg.SUCCESS,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("查询公司ID")
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public ResponseData listId(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            return new ResponseData(ExceptionMsg.SUCCESS,internshipLibraryService.findByInternshipLibraryId(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("查询学生信息")
    @RequestMapping(value = "/listallstudent",method = RequestMethod.GET)
    public ResponseData listallstudent(HttpServletRequest httpServletRequest,@RequestParam String name,
                                       @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            return new ResponseData(ExceptionMsg.SUCCESS,studentService.findByStudentNameContaining(pageIndex,pageSize,name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("查询老师信息")
    @RequestMapping(value = "/listallteacher",method = RequestMethod.GET)
    public ResponseData listallteacher(HttpServletRequest httpServletRequest,@RequestParam String name,
                                       @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            return new ResponseData(ExceptionMsg.SUCCESS,teacherService.findByTeacherNameContaining(pageIndex,pageSize,name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员编辑公司")
    @RequestMapping(value = "/editCompany",method = RequestMethod.POST)
    public ResponseData editCompany(HttpServletRequest httpServletRequest,@RequestBody InternshipLibraryEntity internshipLibraryEntity){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            internshipLibraryService.modifyInternshipLibrary(internshipLibraryEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员添加学生")
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    public ResponseData addStudent(HttpServletRequest httpServletRequest, @RequestBody StudentEntity studentEntity){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            StudentEntity studentEntity1 = studentService.findByStudentId(studentEntity.getStudentId());
            if(studentEntity1!=null)
                return new ResponseData(new Response("405","学号已存在"),null);
            adminService.insertStudent(studentEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员添加老师")
    @RequestMapping(value = "/addTeacher",method = RequestMethod.POST)
    public ResponseData addTeacher(HttpServletRequest httpServletRequest, @RequestBody TeacherEntity teacherEntity){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            TeacherEntity teacherEntity1 = teacherService.findByTeacherId(teacherEntity.getTeacherId());
            if(teacherEntity1!=null)
                return new ResponseData(new Response("405","教师编号已存在"),null);
            adminService.insertTeacher(teacherEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员编辑学生信息")
    @RequestMapping(value = "/editStudent",method = RequestMethod.POST)
    public ResponseData editStudent(HttpServletRequest httpServletRequest,@RequestBody StudentEntity studentEntity){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            StudentEntity studentEntity1 = studentService.findByStudentId(studentEntity.getStudentId());
            if(studentEntity1==null){
                adminService.modifyStudent(studentEntity);
                return new ResponseData(ExceptionMsg.SUCCESS,null);
            }
            else {
                if(studentEntity1.getsId()!=studentEntity.getsId())
                    return new ResponseData(new Response("405","学号已存在"),null);
                else {
                    adminService.modifyStudent(studentEntity);
                    return new ResponseData(ExceptionMsg.SUCCESS,null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员编辑老师信息")
    @RequestMapping(value = "/editTeacher",method = RequestMethod.POST)
    public ResponseData editTeacher(HttpServletRequest httpServletRequest,@RequestBody TeacherEntity teacherEntity){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            TeacherEntity teacherEntity1 = teacherService.findByTeacherId(teacherEntity.getTeacherId());
            if(teacherEntity1==null){
                adminService.modifyTeacher(teacherEntity);
                return new ResponseData(ExceptionMsg.SUCCESS,null);
            }
            else {
                if(teacherEntity1.gettId()!=teacherEntity.gettId())
                    return new ResponseData(new Response("405","教师编号已存在"),null);
                else {
                    adminService.modifyTeacher(teacherEntity);
                    return new ResponseData(ExceptionMsg.SUCCESS,null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员激活学生账号")
    @RequestMapping(value = "/passStudent",method = RequestMethod.GET)
    public ResponseData passStudent(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            StudentEntity studentEntity = studentService.FindBySId(id);
            studentEntity.setDeleteFlag(0);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员停用学生账号")
    @RequestMapping(value = "/refuseStudent",method = RequestMethod.GET)
    public ResponseData refuseStudent(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            StudentEntity studentEntity = studentService.FindBySId(id);
            studentEntity.setDeleteFlag(1);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员启用老师账号")
    @RequestMapping(value = "/passTeacher",method = RequestMethod.GET)
    public ResponseData passTeacher(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            TeacherEntity teacherEntity = teacherService.FindBytId(id);
            teacherEntity.setDeleteFlag(0);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
            return new ResponseData(ExceptionMsg.SUCCESS,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员停用老师账号")
    @RequestMapping(value = "/refuseTeacher",method = RequestMethod.GET)
    public ResponseData refuseTeacher(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            TeacherEntity teacherEntity = teacherService.FindBytId(id);
            teacherEntity.setDeleteFlag(1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
            return new ResponseData(ExceptionMsg.SUCCESS,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员添加公司")
    @RequestMapping(value = "/addCompany",method = RequestMethod.POST)
    public ResponseData addCompany(HttpServletRequest httpServletRequest,@RequestBody InternshipLibraryEntity internshipLibraryEntity){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            internshipLibraryService.insertInternshipLibrary(internshipLibraryEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("显示学生实习申请")
    @RequestMapping(value = "/listStudentAsk",method = RequestMethod.GET)
    public ResponseData listStudentAsk(HttpServletRequest httpServletRequest,@RequestParam String name,
                                       @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            return new ResponseData(ExceptionMsg.SUCCESS,tripleAgreementService.findByStudentNameContainingAndDeleteFlagAndState(pageIndex,pageSize,name,"3"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员同意学生申请")
    @RequestMapping(value = "/adminPassStudentAsk",method = RequestMethod.GET)
    public ResponseData adminPassStudentAsk(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            tripleAgreementService.updateState("4",id);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员拒绝学生申请")
    @RequestMapping(value = "/adminRefuseStudentAsk",method = RequestMethod.GET)
    public ResponseData adminRefuseStudentAsk(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            tripleAgreementService.updateState("0",id);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员查看学生所有信息")
    @RequestMapping(value = "/adminListAllStudent",method = RequestMethod.GET)
    public ResponseData listAllStudent(HttpServletRequest httpServletRequest,@RequestParam String name,
                                       @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            Page<StudentEntity> result = studentService.findByStudentNameContaining(pageIndex,pageSize,name);
            return new ResponseData(ExceptionMsg.SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员显示学生实习日志（不含删除）")
    @RequestMapping(value = "/adminListLog",method = RequestMethod.GET)
    public ResponseData adminListLog(HttpServletRequest httpServletRequest,@RequestParam String studentId,
                                       @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            return new ResponseData(ExceptionMsg.SUCCESS,studentLogService.findLogByStudentId(pageIndex,pageSize,studentId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员查看所有被删除的学生日志")
    @RequestMapping(value = "/adminListDeletedLog",method = RequestMethod.GET)
    public ResponseData adminListDeletedLog(HttpServletRequest httpServletRequest,@RequestParam String studentId,
                                              @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            return new ResponseData(ExceptionMsg.SUCCESS,studentLogService.findDeletedLogByStudentId(pageIndex,pageSize,studentId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员查看指定学生日志")
    @RequestMapping(value = "/adminEditLog",method = RequestMethod.GET)
    public ResponseData adminEditLog(HttpServletRequest httpServletRequest, @RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
//            studentLogService.updateById(studentLogEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,studentLogService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员删除学生实习日志")
    @RequestMapping(value = "/adminDeleteLog",method = RequestMethod.GET)
    public ResponseData adminDeleteLog(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            studentLogService.deleteMyLog(id);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员恢复被删除的日志")
    @RequestMapping(value = "/adminReductLog",method = RequestMethod.GET)
    public ResponseData adminReductLog(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=3)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            studentLogService.reductLog(id);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("管理员查看学生实习鉴定表")
    @RequestMapping(value = "/adminListAppraisalForm",method = RequestMethod.GET)
    public ResponseData adminListAppraisalForm(HttpServletRequest httpServletRequest,@RequestParam String studentId,
                                                 @RequestParam Integer pageIndex,@RequestParam Integer pageSize) {
        String token = httpServletRequest.getHeader("token");
        if (!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED, null);
        try {
            if (JwtUtil.verifyToken(token).get("role").asInt() != 3)
                return new ResponseData(ExceptionMsg.PERMISSION, null);
//            TeacherEntity teacherEntity = teacherService.FindBytId(JwtUtil.verifyToken(token).get("userid").asInt());
            return new ResponseData(ExceptionMsg.SUCCESS, appraisalFormService.findByStudentIdContainingAndDeleteFlag(pageIndex,pageSize,studentId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED, null);
    }

    @ApiOperation("管理员查看学生当前信息")
    @RequestMapping(value = "/adminListStudentStates",method = RequestMethod.GET)
    public ResponseData adminListStudentStates(HttpServletRequest httpServletRequest,@RequestParam String studentName,
                                               @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if (!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED, null);
        try {
            if (JwtUtil.verifyToken(token).get("role").asInt() != 3)
                return new ResponseData(ExceptionMsg.PERMISSION, null);
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("result",adminService.listStudentStatus(studentName,pageIndex,pageSize));
            jsonObject.put("totalSize",adminService.countStudentStates());
            return new ResponseData(ExceptionMsg.SUCCESS,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }
}
