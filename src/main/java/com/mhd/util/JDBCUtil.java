package com.mhd.util;

import java.sql.*;

/**
 * @author MouHongDa
 * @date 2023/11/25 14:30
 */
public class JDBCUtil {
    private static String URL = "jdbc:sqlite:C:/Users/MHD/FullStackProgramming.db";
    private static Connection connection = null;

    public static Connection getConnection() throws Exception {
        // 加载SQLite驱动程序
        Class.forName("org.sqlite.JDBC");
        // 创建数据库连接
        connection = DriverManager.getConnection(URL);
        return connection;
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
