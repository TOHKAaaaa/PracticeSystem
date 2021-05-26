package cn.edu.zucc.practiceSystem.controller;

import cn.edu.zucc.practiceSystem.entity.InternshipLibraryEntity;
import cn.edu.zucc.practiceSystem.result.ExceptionMsg;
import cn.edu.zucc.practiceSystem.result.ResponseData;
import cn.edu.zucc.practiceSystem.service.InternshipLibraryService;
import cn.edu.zucc.practiceSystem.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api("实习库模块")
@Transactional
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/company")
public class InternshipLibraryController {
    @Autowired
    InternshipLibraryService internshipLibraryService;

    @ApiOperation(value = "显示所有实习库汇总公司")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseData listInternshipLibrary(HttpServletRequest httpServletRequest, @RequestParam String name,
                                              @RequestParam Integer pageIndex,@RequestParam Integer pageSize){
        String token = httpServletRequest.getHeader("token");
        if(!JwtUtil.verity(token))
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        else{
            return new ResponseData(ExceptionMsg.SUCCESS,internshipLibraryService.findByInternshipLibraryNameAndDeleteFlag(pageIndex,pageSize,name));
        }
    }
}
