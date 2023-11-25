package com.mhd.util;

import com.mhd.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author MouHongDa
 * @date 2023/11/25 14:40
 */
public class JDBCUtilTest {
    @Test
    public void testGetConnection() throws Exception {
        JDBCUtil jdbcUtil = new JDBCUtil();
        Connection connection = JDBCUtil.getConnection();
        if (connection != null) {
            Statement statement = connection.createStatement();
//            String str = "CREATE TABLE IF NOT EXISTS UserTable (UserID INTEGER PRIMARY KEY AUTOINCREMENT, Username VARCHAR(100) UNIQUE NOT NULL, UserPassword VARCHAR(100) NOT NULL)";
//            statement.executeUpdate(str);
//            System.out.println("创建表成功");
//            String UserPasswords = DatatypeConverter.printBase64Binary("123456".getBytes("utf-8"));
//            String insertSQL = "INSERT INTO UserTable(Username, UserPassword) VALUES ('张三' ,'MTIzNDU2')";
//            System.out.println(insertSQL);
//            statement.executeUpdate(insertSQL);
//            System.out.println("插入成功");
            ResultSet resultSet = statement.executeQuery("select * from UserTable");
            System.out.println(resultSet.getString(3));
        } else {
            System.out.println("连接失败");
        }
    }
}