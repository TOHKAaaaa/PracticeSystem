package cn.edu.zucc.practiceSystem.controller;

import cn.edu.zucc.practiceSystem.DAO.StudentDao;
import cn.edu.zucc.practiceSystem.entity.*;
import cn.edu.zucc.practiceSystem.result.ExceptionMsg;
import cn.edu.zucc.practiceSystem.result.ResponseData;
import cn.edu.zucc.practiceSystem.result.StudentInfoResultEntity;
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

@Api("老师模块")
@Transactional
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class TeacherController {
    //        管理员 role=3
    //        指导老师 role=2
    //        学生 role=1
    @Autowired
    TeacherService teacherService;
    @Autowired
    InternshipLibraryService internshipLibraryService;
    @Autowired
    TripleAgreementService tripleAgreementService;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentLogService studentLogService;
    @Autowired
    AppraisalFormService appraisalFormService;
    @Autowired
    GradeService gradeService;

    @ApiOperation(value = "老师新增公司")
    @RequestMapping(value = "/operateCompany",method = RequestMethod.POST)
    public ResponseData insertCompany(@RequestBody InternshipLibraryEntity internshipLibraryEntity,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()==1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            int id = JwtUtil.verifyToken(token).get("userid").asInt();
            TeacherEntity teacherEntity = teacherService.FindBytId(id);
            internshipLibraryEntity.setTeacherName(teacherEntity.getTeacherName());
            internshipLibraryEntity.setDeleteFlag(1);
            internshipLibraryService.insertInternshipLibrary(internshipLibraryEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "查看老师自己添加的公司")
    @RequestMapping(value = "/mycompanyList",method = RequestMethod.GET)
    public ResponseData listMyCompany(HttpServletRequest httpServletRequest, @RequestParam String name,
                                      @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        else{
            try {
                if(JwtUtil.verifyToken(token).get("role").asInt()==1)
                    return new ResponseData(ExceptionMsg.PERMISSION,null);
                int id = JwtUtil.verifyToken(token).get("userid").asInt();
                String teacherName = teacherService.FindBytId(id).getTeacherName();
                return new ResponseData(ExceptionMsg.SUCCESS,internshipLibraryService.findByInternshipLibraryNameAndDeleteFlagAndTeacherName(pageIndex,pageSize,name,teacherName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("显示学生申报信息")
    @RequestMapping(value = "/listStudentInfo",method = RequestMethod.GET)
    public ResponseData listStudentInfo(HttpServletRequest httpServletRequest,@RequestParam String name,
                                        @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            TeacherEntity teacherEntity = teacherService.FindBytId(JwtUtil.verifyToken(token).get("userid").asInt());
            return new ResponseData(ExceptionMsg.SUCCESS,tripleAgreementService.findByTeacherIdAndDeleteFlag(pageIndex,pageSize,teacherEntity.getTeacherId(),name,"2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师同意请求")
    @RequestMapping(value = "/passStudentAsk",method = RequestMethod.GET)
    public ResponseData passStudent(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            tripleAgreementService.updateState("3",id);
            TeacherEntity teacherEntity = teacherService.FindBytId(JwtUtil.verifyToken(token).get("userid").asInt());
            StudentEntity studentEntity = studentService.findByStudentId(tripleAgreementService.findById(id).getStudentId());
            studentEntity.setStudentTeacher(teacherEntity.getTeacherId());
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师拒绝申请")
    @RequestMapping(value = "/refuseStudentAsk",method = RequestMethod.GET)
    public ResponseData refuseStudent(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            tripleAgreementService.updateState("1",id);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师查看学生所有信息")
    @RequestMapping(value = "/teacherListAllStudent",method = RequestMethod.GET)
    public ResponseData listAllStudent(HttpServletRequest httpServletRequest,@RequestParam String name,
                                       @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            TeacherEntity teacherEntity = teacherService.FindBytId(JwtUtil.verifyToken(token).get("userid").asInt());
            Page<StudentEntity> result = studentService.findByStudentNameContainingAndTeacherId(pageIndex,pageSize,name,teacherEntity.getTeacherId());
            return new ResponseData(ExceptionMsg.SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师显示学生实习日志（不含删除）")
    @RequestMapping(value = "/teacherListLog",method = RequestMethod.GET)
    public ResponseData teacherListLog(HttpServletRequest httpServletRequest,@RequestParam String studentId,
                                       @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            TeacherEntity teacherEntity = teacherService.FindBytId(JwtUtil.verifyToken(token).get("userid").asInt());
            return new ResponseData(ExceptionMsg.SUCCESS,studentLogService.findByStudentIdContainingAndTeacherId(pageIndex,pageSize,teacherEntity.getTeacherId(),studentId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师查看所有被删除的学生日志")
    @RequestMapping(value = "/teacherListDeletedLog",method = RequestMethod.GET)
    public ResponseData teacherListDeletedLog(HttpServletRequest httpServletRequest,@RequestParam String studentId,
                                              @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            TeacherEntity teacherEntity = teacherService.FindBytId(JwtUtil.verifyToken(token).get("userid").asInt());
            return new ResponseData(ExceptionMsg.SUCCESS,studentLogService.findDeletedLogByStudentIdContainingAndTeacherId(pageIndex,pageSize,teacherEntity.getTeacherId(),studentId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师查看指定学生日志")
    @RequestMapping(value = "/teacherEditLog",method = RequestMethod.GET)
    public ResponseData teacherEditLog(HttpServletRequest httpServletRequest, @RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
//            studentLogService.updateById(studentLogEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,studentLogService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师删除学生实习日志")
    @RequestMapping(value = "/teacherDeleteLog",method = RequestMethod.GET)
    public ResponseData teacherDeleteLog(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            studentLogService.deleteMyLog(id);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师恢复被删除的日志")
    @RequestMapping(value = "/teacherReductLog",method = RequestMethod.GET)
    public ResponseData teacherReductLog(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            studentLogService.reductLog(id);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师查看学生实习鉴定表")
    @RequestMapping(value = "/teacherListAppraisalForm",method = RequestMethod.GET)
    public ResponseData teacherListAppraisalForm(HttpServletRequest httpServletRequest,@RequestParam String studentId,
                           @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            TeacherEntity teacherEntity = teacherService.FindBytId(JwtUtil.verifyToken(token).get("userid").asInt());
            return new ResponseData(ExceptionMsg.SUCCESS,appraisalFormService.findByStudentIdContainingAndTeacherId(pageIndex,pageSize,teacherEntity.getTeacherId(),studentId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师驳回鉴定表")
    @RequestMapping(value = "/refuseAppraisalForm",method = RequestMethod.GET)
    public ResponseData refuseAppraisalForm(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            appraisalFormService.refuseAppraisalForm(id);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("老师给学生打分")
    @RequestMapping(value = "/score",method = RequestMethod.GET)
    public ResponseData score(HttpServletRequest httpServletRequest,@RequestParam int score,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=2)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            AppraisalFormEntity appraisalFormEntity = appraisalFormService.findById(id);
            appraisalFormService.updateByStudentId(2,appraisalFormEntity.getTeacherId(),appraisalFormEntity.getStudentId());
            GradeEntity gradeEntity = gradeService.findByStudentId(appraisalFormEntity.getStudentId());
            if(gradeEntity!=null)
                return new ResponseData(ExceptionMsg.FAILED,"不能重复打分");
            gradeService.score(appraisalFormEntity.getStudentId(),appraisalFormEntity.getStudentName(),
                    appraisalFormEntity.getTeacherId(),appraisalFormEntity.getTeacherName(),score);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }
}
