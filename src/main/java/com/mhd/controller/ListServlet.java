package com.mhd.controller;

import com.mhd.domain.Result;
import com.mhd.service.ListService;
import com.mhd.service.impl.listServiceImpl;
import com.mhd.util.JSONUtil;
import com.mhd.util.MySessionContext;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

/**
 * @author MouHongDa
 * @date 2023/11/25 00:25
 */
@WebServlet("/user/list")
@MultipartConfig
public class ListServlet extends HttpServlet {
    private final MySessionContext myc = MySessionContext.getInstance();
    private ListService listService = new listServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result = new Result();
        //判断用户上传的文件是普通表单还是带文件的表单，如果是普通文件直接返回
        if (!ServletFileUpload.isMultipartContent(request)) {
            return;
        }
        try {
            boolean flag = listService.upload(request);
            if (flag) {
                result.setCode(200);
                result.setMsg("上传成功！");
            } else {
                result.setCode(203);
                result.setMsg("上传失败");
            }
            JSONUtil.returnJSON(response, result);
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("服务器出错，请联系管理员");
            JSONUtil.returnJSON(response, result);
            e.printStackTrace();
        }
    }
}
