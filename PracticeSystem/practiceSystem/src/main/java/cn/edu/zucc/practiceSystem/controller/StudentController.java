package cn.edu.zucc.practiceSystem.controller;

import cn.edu.zucc.practiceSystem.entity.*;
import cn.edu.zucc.practiceSystem.result.*;
import cn.edu.zucc.practiceSystem.service.*;
import cn.edu.zucc.practiceSystem.util.JwtUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

@Api("学生模块")
@Transactional
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class StudentController {
    protected Response result(ExceptionMsg msg){
        return new Response(msg);
    }
    protected Response result(){
        return new Response();
    }

    @Autowired
    StudentService studentService;
    @Autowired
    TripleAgreementService tripleAgreementService;
    @Autowired
    StudentLogService studentLogService;
    @Autowired
    AppraisalFormService appraisalFormService;
    @Autowired
    GradeService gradeService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    InternshipLibraryService internshipLibraryService;

    @ApiOperation("查看个人信息")
    @RequestMapping(value = "/listmyinfo",method = RequestMethod.GET)
    public Response listMyInfo(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);

            StudentInfoResultEntity studentInfoResultEntity = new StudentInfoResultEntity();
            StudentEntity studentEntity = studentService.FindBySId(JwtUtil.verifyToken(token).get("userid").asInt());
            TeacherEntity teacherEntity = teacherService.findByTeacherId(studentEntity.getStudentTeacher());
            studentInfoResultEntity.setStudentId(studentEntity.getStudentId());
            studentInfoResultEntity.setStudentName(studentEntity.getStudentName());
            studentInfoResultEntity.setStudentClass(studentEntity.getStudentClass());
            if(teacherEntity==null)
                studentInfoResultEntity.setStudentTeacher(null);
            else
                studentInfoResultEntity.setStudentTeacher(teacherEntity.getTeacherName());
            if(studentEntity.getStudentWorkplace()!=null)
                studentInfoResultEntity.setStudentWorkplace(studentEntity.getStudentWorkplace());
            if(studentEntity.getStudentInternship()!=null)
                studentInfoResultEntity.setStudentInternship(studentEntity.getStudentInternship());

            TripleAgreementEntity tripleAgreementEntity = tripleAgreementService.findByStudentId(studentEntity.getStudentId());
            studentInfoResultEntity.setTripleAgreement(tripleAgreementEntity != null);
//            System.out.println("这是个测试行");
//            System.out.println(studentInfoResultEntity.isTripleAgreement());
//            System.out.println("测试结束");

//            三方协议
//            未上传:0 上传:2 通过:3 未通过:1 管理员通过:4
//            studentInfoResultEntity
//            初始:0 提交审核:2 审核被退回:1 开始实习（审核通过）:3
//            实习结束（日志填完）:4  等待结果（提交实习报告）:5  实习完成（成绩出来）:6
//            鉴定表退回:7  管理员确认:8
            if(tripleAgreementEntity==null)
                studentInfoResultEntity.setState(0);
            else{
                int state = Integer.parseInt(tripleAgreementEntity.getState());
                if(state==1)
                    studentInfoResultEntity.setState(1);
                else if(state==2)
                    studentInfoResultEntity.setState(2);
                else if(state==3)
                    studentInfoResultEntity.setState(8);
                else if(state==4)
                    studentInfoResultEntity.setState(3);
                else
                    studentInfoResultEntity.setState(1);
            }
//            System.out.println(tripleAgreementEntity.getState());

            int sum = studentLogService.StudentLogSum(studentEntity.getStudentId());
            studentInfoResultEntity.setStudentLog(sum >= 12);
            if(sum>=12)
                studentInfoResultEntity.setState(4);

            AppraisalFormEntity appraisalFormEntity = appraisalFormService.findByStudentIdAndDeleteFlag(studentEntity.getStudentId());
            studentInfoResultEntity.setAppraisalForm(appraisalFormEntity != null);
//            appraisalFormEntity
//            初始:0 未通过:1 通过:2
            if(appraisalFormEntity!=null){
                if(appraisalFormEntity.getAppraisalFormState()==1)
                    studentInfoResultEntity.setState(7);
                else if(appraisalFormEntity.getAppraisalFormState()==2)
                    studentInfoResultEntity.setState(6);
                else
                    studentInfoResultEntity.setState(5);
            }

            GradeEntity gradeEntity = gradeService.findByStudentId(studentEntity.getStudentId());
            if(gradeEntity==null)
                studentInfoResultEntity.setStudentGrade(0);
            else
                studentInfoResultEntity.setStudentGrade(gradeEntity.getStudentGrade());

            return new ResponseData(ExceptionMsg.SUCCESS,studentInfoResultEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("显示所有老师")
    @RequestMapping(value = "/liststudentteacher",method = RequestMethod.GET)
    public ResponseData listAllTeacher(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            List<TeacherEntity> result = teacherService.findByDeleteFlag();
            return new ResponseData(ExceptionMsg.SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("申报实习")
    @RequestMapping(value = "/askPractice",method = RequestMethod.POST)
    public ResponseData askPractice(HttpServletRequest httpServletRequest, @RequestBody PracticeResultEntity practiceResultEntity){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            int id = JwtUtil.verifyToken(token).get("userid").asInt();
            StudentEntity studentEntity = studentService.FindBySId(id);
            TeacherEntity teacherEntity = teacherService.FindBytId(practiceResultEntity.getTeacher());
            studentEntity.setRemark(practiceResultEntity.getStates());
            studentEntity.setStudentWorkplace(practiceResultEntity.getCompany());
            studentEntity.setStudentInternship(practiceResultEntity.getPosition());
            Timestamp time1 = Timestamp.valueOf(practiceResultEntity.getDate1());
            Timestamp time2 = Timestamp.valueOf(practiceResultEntity.getDate2());
            studentEntity.setStratTime(time1);
            studentEntity.setEndTime(time2);
            TripleAgreementEntity tripleAgreementEntity = new TripleAgreementEntity();
            tripleAgreementEntity.setStudentId(studentEntity.getStudentId());
            tripleAgreementEntity.setStudentName(studentEntity.getStudentName());
            tripleAgreementEntity.setTeacherId(teacherEntity.getTeacherId());
            tripleAgreementEntity.setTeacherName(teacherEntity.getTeacherName());
            tripleAgreementEntity.setTripleAgreement(1);
            tripleAgreementEntity.setState("2");
            if(tripleAgreementService.findByStudentId(studentEntity.getStudentId())==null)
                tripleAgreementService.insertIntoTripleAgreement(tripleAgreementEntity);
            else
                tripleAgreementService.updateTripleAgreement(tripleAgreementEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,studentEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("显示申报信息")
    @RequestMapping(value = "/listPracticeInfo",method = RequestMethod.GET)
    public ResponseData listPracticeInfo(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            PracticeResultEntity practiceResultEntity1 = new PracticeResultEntity();
            StudentEntity studentEntity = studentService.FindBySId(JwtUtil.verifyToken(token).get("userid").asInt());
            if(studentEntity==null)
                return new ResponseData("405","无法找到该用户",null);
            TeacherEntity teacherEntity = teacherService.findByTeacherId(studentEntity.getStudentTeacher());
            PracticeResultEntity practiceResultEntity = new PracticeResultEntity();
            if(studentEntity.getStudentWorkplace()!=null)
                practiceResultEntity.setCompany(studentEntity.getStudentWorkplace());
            if(studentEntity.getStudentInternship()!=null)
                practiceResultEntity.setPosition(studentEntity.getStudentInternship());
            if(studentEntity.getStratTime()!=null)
                practiceResultEntity.setDate1(String.valueOf(studentEntity.getStratTime()));
            if(studentEntity.getEndTime()!=null)
                practiceResultEntity.setDate2(String.valueOf(studentEntity.getEndTime()));
            if(studentEntity.getRemark()!=null)
                practiceResultEntity.setStates(studentEntity.getRemark());
            if(teacherEntity==null)
                return new ResponseData(ExceptionMsg.SUCCESS,practiceResultEntity);
            if(teacherEntity.gettId()!=0)
                practiceResultEntity.setTeacher(teacherEntity.gettId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",practiceResultEntity);
            jsonObject.put("teacher",teacherEntity.getTeacherName());
            return new ResponseData(ExceptionMsg.SUCCESS,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("学生查看实习日志")
    @RequestMapping(value = "/listMyLog",method = RequestMethod.GET)
    public ResponseData listMyLog(HttpServletRequest httpServletRequest,@RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            StudentEntity studentEntity = studentService.FindBySId(JwtUtil.verifyToken(token).get("userid").asInt());
            return new ResponseData(ExceptionMsg.SUCCESS,studentLogService.findByStudentId(pageIndex,pageSize,studentEntity.getStudentId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("学生增加实习日志")
    @RequestMapping(value = "/addMyLog",method = RequestMethod.POST)
    public ResponseData addMyLog(HttpServletRequest httpServletRequest,@RequestBody StudentLogEntity studentLogEntity){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            StudentEntity studentEntity = studentService.FindBySId(JwtUtil.verifyToken(token).get("userid").asInt());
            TeacherEntity teacherEntity = teacherService.findByTeacherId(studentEntity.getStudentTeacher());
            studentLogEntity.setStudentId(studentEntity.getStudentId());
            studentLogEntity.setStudentName(studentEntity.getStudentName());
            studentLogEntity.setTeacherId(studentEntity.getStudentTeacher());
            studentLogEntity.setTeacherName(teacherEntity.getTeacherName());
            studentLogService.insertNewLog(studentLogEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("学生删除实习日志")
    @RequestMapping(value = "/deleteMyLog",method = RequestMethod.GET)
    public ResponseData deleteMyLog(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            studentLogService.deleteMyLog(id);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("学生修改实习日志")
    @RequestMapping(value = "/changeMyLog",method = RequestMethod.POST)
    public ResponseData changeMyLog(HttpServletRequest httpServletRequest,@RequestBody StudentLogEntity studentLogEntity){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            studentLogService.updateById(studentLogEntity);
            return new ResponseData(ExceptionMsg.SUCCESS,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("学生查看修改信息")
    @RequestMapping(value = "/listChangedLog",method = RequestMethod.GET)
    public ResponseData listChangedLog(HttpServletRequest httpServletRequest,@RequestParam int id){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            StudentLogEntity studentLogEntity = studentLogService.findById(id);
            return new ResponseData(ExceptionMsg.SUCCESS,studentLogEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("学生提交实习鉴定")
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public ResponseData submit(HttpServletRequest httpServletRequest,@RequestBody String report){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            StudentEntity studentEntity = studentService.FindBySId(JwtUtil.verifyToken(token).get("userid").asInt());
            int sum = studentLogService.StudentLogSum(studentEntity.getStudentId());
            if(sum<12)
                return new ResponseData(ExceptionMsg.FAILED,"实习日志不足12篇");
            else {
                AppraisalFormEntity result = appraisalFormService.findByStudentId(studentEntity.getStudentId());
                if(result==null){
                    TeacherEntity teacherEntity = teacherService.findByTeacherId(studentEntity.getStudentTeacher());
                    AppraisalFormEntity appraisalFormEntity = new AppraisalFormEntity();
                    appraisalFormEntity.setStudentId(studentEntity.getStudentId());
                    appraisalFormEntity.setStudentName(studentEntity.getStudentName());
                    appraisalFormEntity.setTeacherId(studentEntity.getStudentTeacher());
                    appraisalFormEntity.setTeacherName(teacherEntity.getTeacherName());
                    appraisalFormEntity.setAppraisalForm(report);
                    appraisalFormEntity.setAppraisalFormState(0);
                    appraisalFormService.insertNewAppraisalForm(appraisalFormEntity);
                    return new ResponseData(ExceptionMsg.SUCCESS,null);
                }
                else{
                    if(result.getDeleteFlag()==0&&result.getAppraisalFormState()!=1)
                        return new ResponseData(ExceptionMsg.FAILED,"不能重复提交");
                    else{
                        result.setAppraisalForm(report);
                        result.setAppraisalFormState(0);
                        result.setDeleteFlag(0);
                        appraisalFormService.updateAppraisalForm(result.getAppraisalForm(),String.valueOf(result.getAppraisalFormState()),result.getId());
                        return new ResponseData(ExceptionMsg.SUCCESS,null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }

    @ApiOperation("学生查看自己上次提交的鉴定表")
    @RequestMapping(value = "/listMyAppraisalForm",method = RequestMethod.GET)
    public ResponseData listMyAppraisalForm(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        try {
            if(JwtUtil.verifyToken(token).get("role").asInt()!=1)
                return new ResponseData(ExceptionMsg.PERMISSION,null);
            StudentEntity studentEntity = studentService.FindBySId(JwtUtil.verifyToken(token).get("userid").asInt());
            AppraisalFormEntity appraisalFormEntity = appraisalFormService.findByStudentIdAndDeleteFlag(studentEntity.getStudentId());
            if(appraisalFormEntity==null)
                return new ResponseData(ExceptionMsg.SUCCESS,null);
            return new ResponseData(ExceptionMsg.SUCCESS,appraisalFormEntity.getAppraisalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseData(ExceptionMsg.FAILED,null);
    }
}
