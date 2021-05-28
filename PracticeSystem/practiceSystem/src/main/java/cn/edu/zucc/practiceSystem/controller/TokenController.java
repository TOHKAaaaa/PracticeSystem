package cn.edu.zucc.practiceSystem.controller;

import cn.edu.zucc.practiceSystem.result.ExceptionMsg;
import cn.edu.zucc.practiceSystem.result.ResponseData;
import cn.edu.zucc.practiceSystem.util.JwtUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api("登录模块")
@Transactional
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class TokenController {
    @ApiOperation(value = "token解析")
    @RequestMapping(value = "/token",method = RequestMethod.GET)
    public ResponseData loginAdmin(HttpServletRequest request){
        String token = request.getHeader("token");
        boolean result = JwtUtil.verity(token);
        JSONObject jsonObject = new JSONObject();
        if(!result){
            return new ResponseData(ExceptionMsg.TOKENFAILED,null);
        }
        else{
            jsonObject.put("token",token);
            return new ResponseData(ExceptionMsg.SUCCESS,jsonObject);
        }
    }
}
