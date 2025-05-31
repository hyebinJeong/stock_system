//package org.stock.common;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class JDBCUtil {
//    private static final Logger logger = Logger.getLogger(JDBCUtil.class.getName());
//
//    // 매번 새 Connection을 생성해 반환한다
//    public static Connection getConnection() {
//        try {
//            Properties properties = new Properties();
//            properties.load(JDBCUtil.class.getResourceAsStream("/application.properties"));
//
//            String driver = properties.getProperty("driver");
//            String url = properties.getProperty("url");
//            String user = properties.getProperty("id");
//            String password = properties.getProperty("password");
//
//            Class.forName(driver);
//            Connection conn = DriverManager.getConnection(url, user, password);
//            logger.info("DB 연결 성공");
//            return conn;
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "DB 연결 실패", e);
//            return null;
//        }
//    }
//
//    // connection close 메서드
//    public static void close(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//                logger.info("DB 연결 해제 성공");
//            } catch (Exception e) {
//                logger.log(Level.SEVERE, "DB 연결 해제 실패", e);
//            }
//        }
//    }
//}
