package com.mhd.controller;

import com.mhd.domain.Result;
import com.mhd.domain.User;
import com.mhd.service.UserService;
import com.mhd.service.impl.UserServiceImpl;
import com.mhd.util.JSONUtil;
import com.mhd.util.MySessionContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author MouHongDa
 * @date 2023/11/25 16:25
 */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private MySessionContext myc = MySessionContext.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Result<String> result = new Result<String>();
        if (username == null || password == null) {
            result.setCode(202);
            result.setMsg("登录失败,用户名或者密码为空");
            JSONUtil.returnJSON(resp, result);
            return;
        }
        User user = new User(username, password);
        try {
            boolean flag = userService.login(user);
            if (flag) {
                result.setCode(200);
                result.setMsg("登录成功");
                //服务端记录登录状态
                HttpSession session = req.getSession();
                session.setAttribute("User", user);
                myc.addSession(session);
                result.setData(session.getId());
            } else {
                result.setCode(201);
                result.setMsg("登录失败,用户名或密码错误");
            }
            JSONUtil.returnJSON(resp, result);
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("服务器出错，请联系管理员");
            JSONUtil.returnJSON(resp, result);
            e.printStackTrace();
        }
    }
}
