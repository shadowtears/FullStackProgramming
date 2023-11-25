package com.mhd.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mhd.domain.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MouHongDa
 * @date 2023/11/25 16:32
 */
public class JSONUtil {

    public static void returnJSON(HttpServletResponse resp, Result result) throws IOException {
        PrintWriter writer = resp.getWriter();
        Gson gson = new GsonBuilder()
                .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
                .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS") //时间转化为特定格式
                .setPrettyPrinting() //对结果进行格式化，增加换行
                .disableHtmlEscaping() //防止特殊字符出现乱码
                .create();
        String json = gson.toJson(result);
        writer.print(json);
    }
}

