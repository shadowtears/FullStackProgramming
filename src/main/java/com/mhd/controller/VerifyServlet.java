package com.mhd.controller;

import com.mhd.domain.Captcha;
import com.mhd.domain.Result;
import com.mhd.util.JSONUtil;
import com.mhd.util.MyCaptchaUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MouHongDa
 * @date 2023/11/25 21:10
 */
@WebServlet("/user/captcha")
public class VerifyServlet extends HttpServlet {
    private MyCaptchaUtil myCaptchaUtil = new MyCaptchaUtil();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Result<Captcha> result = new Result<Captcha>();
        Captcha captcha = myCaptchaUtil.generateCaptchaUtil();
        try {
            if (captcha.getImg() != null && captcha.getCode() != null) {
                result.setCode(200);
                result.setMsg("验证码生成成功");
                result.setData(captcha);
            } else {
                result.setCode(201);
                result.setMsg("验证码生成失败");
            }
            JSONUtil.returnJSON(resp, result);
        } catch (IOException e) {
            result.setCode(500);
            result.setMsg("服务器出错，请联系管理员");
            JSONUtil.returnJSON(resp, result);
            e.printStackTrace();
        }
    }
}
