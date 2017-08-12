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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JDBCUtil {
	
	private static Logger LOG = LoggerFactory.getLogger(JDBCUtil.class);
    
    public static void main(String[] args) throws Exception {
        JDBCUtil.query("select * from t_balance t");
        JDBCUtil.execute("update t_balance t set t.balance = ? where t.user_id = ?", new Object[] {Integer.valueOf("1000"), Integer.valueOf("123456")});
        JDBCUtil.query("select * from t_balance t");
    }
    
    public static String HOST = "rm-wz97ap7j1u0csxu0yo.mysql.rds.aliyuncs.com";
    public static String PORT = "3306";
    public static String DATABASE_NAME = "blog";
    public static String USER_NAME = "root";
    public static String PASSWORD = "AlyDB((((((";
    
    /**
     * 获取数据库连接
     * @return 数据库连接
     * @throws  
     */
    public static Connection getConn() {
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
        LOG.info("成功加载驱动");
        
        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME + "?user=" + USER_NAME + "&password=" + PASSWORD + "&useUnicode=true&characterEncoding=UTF8";
        Connection connection;
		try {
			connection = DriverManager.getConnection(url);
			LOG.info("成功获取连接");
	        return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
     * 关闭资源
     */
    public static void closeResource(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            	LOG.error("关闭异常：{}", e);
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
            	LOG.error("关闭异常：{}", e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            	LOG.error("关闭异常：{}", e);
            }
        }
        LOG.info("成功关闭资源");
    }

    /**
     * 查询SQL
     * @param sql 查询语句
     * @return 数据集合
     * @throws SQLException
     */
    public static List<Map<String, String>> query(String sql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Map<String, String>> resultList = null;
        
        try {
            connection = JDBCUtil.getConn();
            
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
            LOG.info("成功查询数据库，查得数据：" + resultList);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeResource(connection, statement, resultSet);
        }
        
        return resultList;
    }
    
    /**
     * 执行SQL
     * @param sql 执行的SQL
     * @return 操作条数
     */
    public static int execute(String sql, Object[] objects) {
        Connection connection = null;
        PreparedStatement preparedStatement  = null;
        ResultSet resultSet = null;
        int num = 0;
        
        try {
            connection = JDBCUtil.getConn();
            connection.setAutoCommit(false);
            
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
            LOG.info("成功操作数据库，影响条数：" + num);
            
            // 模拟异常，用于测试事务
            /*
            if (1 == 1) {
                throw new RuntimeException();
            }
            */
            
            connection.commit();
        } catch(SQLException e) {
            // 处理异常：回滚事务后抛出异常
            try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
            LOG.info("事务回滚");
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement, resultSet);
        }
        
        return num;
    }
    
}
