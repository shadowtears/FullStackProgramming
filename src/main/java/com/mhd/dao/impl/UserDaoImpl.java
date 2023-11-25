package com.mhd.dao.impl;

import com.mhd.dao.UserDao;
import com.mhd.domain.User;
import com.mhd.util.JDBCUtil;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author MouHongDa
 * @date 2023/11/25 16:07
 */
public class UserDaoImpl implements UserDao {
    public boolean login(User user) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from UserTable where Username =? and userPassword=?";

            ps = conn.prepareStatement(sql);
//        String passWord = decode(user.getPassword());
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            rs = ps.executeQuery();

            if (rs.next()) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return flag;
    }

    public static String decode(String base64Str) {
        // 解码后的字符串
        String str = "";
        // 非空字符串才进行解码
        if (base64Str != null && base64Str.length() > 0) {
            // 解码
            byte[] base64Bytes = DatatypeConverter.parseBase64Binary(base64Str);
            try {
                // byte[] 转 String
                str = new String(base64Bytes, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}
