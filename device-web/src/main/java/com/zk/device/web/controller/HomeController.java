package com.zk.device.web.controller;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.zk.device.model.common.CommonResponse;
import com.zk.device.model.enums.ResponseEnum;
import com.zk.device.model.exception.BaseException;
import com.zk.device.util.constant.ConstantString;
import com.zk.device.web.base.BaseController;
import com.zk.device.web.base.ValidateCode;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author device
 * @Title:
 * @Description:
 * @date 2018/7/13.
 */

@Controller
public class HomeController extends BaseController {

    @GetMapping(path = "/home")
    public String index(ModelMap model) {
        model.addAttribute("user", getCurrentUser());
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("doLogin")
    @ResponseBody
    public String doLogin(String userName, String password, String remember) {
        CommonResponse<String> responseDto = new CommonResponse<>();
        responseDto.setResponseEnum(ResponseEnum.Code_1000);
        try {
            //Object objCode = request.getSession().getAttribute("code");
            //String _code = objCode.toString().toLowerCase();
            //if (!code.equals(_code)) {
            //    throw new BaseException(ResponseEnum.Code_900);
            //}
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            if (ConstantString.STRING_1.equals(remember)) {
                token.setRememberMe(true);
            }
            currentUser.login(token);
            Session session = currentUser.getSession();
            session.setAttribute("userName", userName);
        } catch (BaseException e) {
            responseDto.setCode(e.getCode());
            responseDto.setMessage(e.getMessage());
        } catch (UnknownAccountException e) {
            responseDto.setResponseEnum(ResponseEnum.Code_901);
        } catch (IncorrectCredentialsException e) {
            responseDto.setResponseEnum(ResponseEnum.Code_902);
        } catch (Exception e) {
            responseDto.setResponseEnum(ResponseEnum.Code_999);
        }
        return JSON.toJSONString(responseDto);
    }

    /**
     * 登出
     *
     * @return
     */
    @PostMapping("logout")
    @ResponseBody
    public String logout() {
        CommonResponse<String> responseDto = new CommonResponse<>();
        responseDto.setResponseEnum(ResponseEnum.Code_1000);
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            responseDto.setResponseEnum(ResponseEnum.Code_999);
        }
        return JSON.toJSONString(responseDto);
    }

    /**
     * 获取随机验证码
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 设置响应的类型格式为图片格式
            response.setContentType("image/jpeg");
            //禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            HttpSession session = request.getSession();

            ValidateCode vCode = new ValidateCode(120, 40, 4, 100);
            session.setAttribute("code", vCode.getCode());
            vCode.write(response.getOutputStream());
        } catch (Exception e) {
            response.setStatus(409);
        }
        return null;
    }
}
