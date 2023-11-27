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
 * @date 2023/11/27 0:13
 */
@WebServlet("/user/showList")
public class ShowListServlet extends HttpServlet {
    private ListService listService = new listServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String username = request.getParameter("username");
        String animal = request.getParameter("animal");
        String reason = request.getParameter("reason");
        if (page == null) {
            page = "1";
        }
        Integer pageNum = Integer.parseInt(page);
        Result<List<AnimalPreference>> listResult = new Result<List<AnimalPreference>>();
        try {
            List<AnimalPreference> list = listService.getAnimalPreferenceList(pageNum, username, animal, reason);
            listResult.setCode(200);
            listResult.setMsg("展示成功！");
            listResult.setData(list);
            JSONUtil.returnJSON(response, listResult);
        } catch (Exception e) {
            listResult.setCode(500);
            listResult.setMsg("服务器出错，请联系管理员");
            JSONUtil.returnJSON(response, listResult);
            e.printStackTrace();
        }
    }
}
