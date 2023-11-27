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
//            String str = "CREATE TABLE AnimalPreference (id INTEGER PRIMARY KEY AUTOINCREMENT,username VARCHAR(255) NOT NULL,favorite_animal VARCHAR(255) NOT NULL,reason TEXT NOT NULL,image_url VARCHAR(255) NOT NULL);";
//            statement.executeUpdate(str);
//            System.out.println("创建表成功");
//            String str = "INSERT INTO UserTable (Username, UserPassword)VALUES ('李四', 'MTIzNDU2');";
//            statement.executeUpdate(str);
//            System.out.println("插入成功");
//            String str = "SELECT * FROM AnimalPreference";
//            ResultSet resultSet = statement.executeQuery(str);
//            int tol = 0;
//            while (resultSet.next()){
//                tol++;
//            }
//            System.out.println(tol);
//            System.out.println(resultSet.getString(4));
//            String str = "DROP TABLE AnimalPreference";
//            statement.executeUpdate(str);
        } else {
            System.out.println("连接失败");
        }
    }
}