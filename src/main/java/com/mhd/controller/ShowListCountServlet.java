package com.mhd.controller;

import com.mhd.domain.AnimalPreference;
import com.mhd.domain.Result;
import com.mhd.service.ListService;
import com.mhd.service.impl.listServiceImpl;
import com.mhd.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author MouHongDa
 * @date 2023/11/27 2:02
 */
@WebServlet("/user/showListCount")
public class ShowListCountServlet extends HttpServlet {
    private ListService listService = new listServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result<Integer> result = new Result();
        int total = 0;
        try {
            int  totalPages = listService.count();
            result.setCode(200);
            result.setMsg("获取总页数成功！");
            result.setData(totalPages);
            JSONUtil.returnJSON(response, result);
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("服务器出错，请联系管理员");
            JSONUtil.returnJSON(response, result);
            e.printStackTrace();
        }
    }
}
