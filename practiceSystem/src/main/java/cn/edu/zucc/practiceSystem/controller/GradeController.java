package cn.edu.zucc.practiceSystem.controller;

import cn.edu.zucc.practiceSystem.entity.GradeEntity;
import cn.edu.zucc.practiceSystem.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("成绩信息模块")
@RestController
@RequestMapping("/grade")
public class GradeController {
}
