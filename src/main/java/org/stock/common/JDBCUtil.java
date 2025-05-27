package org.stock.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCUtil {
    private static final Logger logger = Logger.getLogger(JDBCUtil.class.getName());
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn != null) {
            return conn; // 기존 연결 재사용
        }

        try {
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getResourceAsStream("/application.properties"));

            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("id");
            String password = properties.getProperty("password");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);

            logger.info("DB 연결 성공");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "DB 연결 실패", e);
        }
        return conn;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.info("DB 연결 해제 성공");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "DB 연결 해제 실패", e);
            }
        }
    }

    // 테스트용 main 메서드
    public static void main(String[] args) {
        Connection conn = JDBCUtil.getConnection();
        if (conn != null) {
            logger.info("DB 연결 테스트 성공");
            JDBCUtil.close(conn);
        } else {
            logger.severe("DB 연결 테스트 실패");
        }
    }
}
