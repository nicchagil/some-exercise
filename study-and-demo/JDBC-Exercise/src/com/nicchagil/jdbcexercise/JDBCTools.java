package com.nicchagil.jdbcexercise;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JDBCTools {
    
    public static void main(String[] args) throws Exception {
        JDBCTools.query("select * from t_balance t");
        JDBCTools.execute("update t_balance t set t.balance = ? where t.user_id = ?", new Object[] {Integer.valueOf("1000"), Integer.valueOf("123456")});
        JDBCTools.query("select * from t_balance t");
    }
    
    public static String HOST = "xx.xx.xx.xx";
    public static String PORT = "3306";
    public static String DATABASE_NAME = "mysql-db";
    public static String USER_NAME = "xxx";
    public static String PASSWORD = "xxx";
    
    /**
     * 获取数据库连接
     * @return 数据库连接
     */
    public static Connection getConn() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("成功加载驱动");
        
        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME + "?user=" + USER_NAME + "&password=" + PASSWORD + "&useUnicode=true&characterEncoding=UTF8";
        Connection connection = DriverManager.getConnection(url);
        System.out.println("成功获取连接");
        return connection;
    }
    
    /**
     * 关闭资源
     */
    public static void closeResource(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO 处理异常
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                // TODO 处理异常
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO 处理异常
                e.printStackTrace();
            }
        }
        System.out.println("成功关闭资源");
    }

    /**
     * 查询SQL
     * @param sql 查询语句
     * @return 数据集合
     * @throws SQLException
     */
    public static List<Map<String, String>> query(String sql) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Map<String, String>> resultList = null;
        
        try {
            connection = JDBCTools.getConn();
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            String[] columnNames = new String[columnCount + 1];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i] = resultSetMetaData.getColumnName(i);
            }

            resultList = new ArrayList<Map<String, String>>();
            Map<String, String> resultMap = new HashMap<String, String>();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    resultMap.put(columnNames[i], resultSet.getString(i));
                }
                resultList.add(resultMap);
            }
            System.out.println("成功查询数据库，查得数据：" + resultList);
        } catch(Throwable t) {
            // TODO 处理异常
            t.printStackTrace();
        } finally {
            JDBCTools.closeResource(connection, statement, resultSet);
        }
        
        return resultList;
    }
    
    /**
     * 执行SQL
     * @param sql 执行的SQL
     * @return 操作条数
     */
    public static int execute(String sql, Object[] objects) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement  = null;
        ResultSet resultSet = null;
        int num = 0;
        
        try {
            connection = JDBCTools.getConn();
            connection.setAutoCommit(false);
            
            System.out.println("sql -> " + sql);
            preparedStatement = connection.prepareStatement(sql);
            if (objects != null && objects.length != 0) {
            	
            	Integer realIndex = null;
            	for (int i = 0; i < objects.length; i++) {
            		if (objects[i] == null) {
            			throw new RuntimeException("参数为空");
            		}
            		
            		realIndex = i + 1;
            		/* 目前只支持Integer、String、Date */
            		if (objects[i] instanceof Integer) {
            			preparedStatement.setInt(realIndex, (Integer)objects[i]);
            		} else if (objects[i] instanceof String) {
            			preparedStatement.setString(realIndex, (String)objects[i]);
            		} else if (objects[i] instanceof Date) {
            			preparedStatement.setDate(realIndex, (Date)objects[i]);
            		}
            	}
            }
            
            num = preparedStatement.executeUpdate();
            System.out.println("成功操作数据库，影响条数：" + num);
            
            // 模拟异常，用于测试事务
            /*
            if (1 == 1) {
                throw new RuntimeException();
            }
            */
            
            connection.commit();
        } catch(Exception e) {
            // 处理异常：回滚事务后抛出异常
            e.printStackTrace();
            connection.rollback();
            System.out.println("事务回滚");
            throw e;
        } finally {
            JDBCTools.closeResource(connection, preparedStatement, resultSet);
        }
        
        return num;
    }
    
}
