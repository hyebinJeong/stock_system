package org.stock.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// 데이터베이스 연결을 관리하는 유틸리티 클래스
public class JDBCUtil {
    // // DB 연결을 위한 Connection 객체 변수 선언
    static Connection conn = null;

    // DB 연결 요청 메서드
    public static Connection getConnection(){
        // 이미 연결되어 있다면 기존 연결을 바로 반환 (새 연결하지 않음)
        if(conn != null) return conn;
        try{
            // properties 파일을 읽기 위한 객체 생성
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getResourceAsStream("/application.properties"));
            // properties 파일의 설정값 꺼내기
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String id = properties.getProperty("id");
            String password = properties.getProperty("password");

            // jdbc 드라이버 클래스를 메모리에 로딩
            // -> 드라이버를 JVM이 인식하게 함.
            Class.forName(driver);

            conn = DriverManager.getConnection(url, id, password);
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(){
        try{
            // 연결이 존재하면 close()로 닫고
            // 연결 객체를 다시 null로 만들어서 연결 초기화
            if(conn != null){
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
