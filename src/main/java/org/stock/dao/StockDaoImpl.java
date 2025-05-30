package org.stock.dao;

import org.stock.common.JDBCUtil;
import org.stock.domain.StockVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDaoImpl implements StockDao{
    // JDBCUtil에서 미리 만든 DB 연결 객체를 가져와서 conn 변수에 저장
    Connection conn = JDBCUtil.getConnection();

    // USERS 테이블 관련 SQL 명령어
    // DB에서 실행할 SQL 문장을 미리 문자열 변수로 선언
    private String STOCK_LIST = "select * from stock_db";
    private String STOCK_GET = "select * from stock_db where ticker = ?";
    private String STOCK_INSERT = "insert into stock_db (stock_name, ticker, price, holding_qty) values(?,?,?,?)";
    private String STOCK_UPDATE = "update stock_db set stock_name  = ?, ticker = ?, price = ?, holding_qty = ? where id = ?";
    private String STOCK_DELETE = "delete from stock_db where ticker = ?";

    // 주식 등록
    @Override
    public int create(StockVO stock) throws SQLException{
        // conn.prepareStatement(USER_INSERT)는 insert SQL 문을 실행할 준비
        PreparedStatement stmt = conn.prepareStatement(STOCK_INSERT);
        // ? 자리에 값 하나씩 넣기
        stmt.setString(1, stock.getStockName());
        stmt.setString(2, stock.getTicker());
        stmt.setInt(3, stock.getPrice());
        stmt.setInt(4, stock.getHoldingQty());

        // SQL문 실행
        int result = stmt.executeUpdate();

        // 사용이 끝난 PreparedStatement를 닫고 결과 반환
        stmt.close();
        return result;
    }

    // 주식 목록 조회
    @Override
    public List<StockVO> getList() throws SQLException{
        // 결과를 담을 리스트(stockList) 만들기
        List<StockVO> stockList = new ArrayList<>();
        // DB 연결을 다시 가져오기
        Connection conn = JDBCUtil.getConnection();
        // select * from stock_db 쿼리를 준비 및 실행
        PreparedStatement stmt = conn.prepareStatement(STOCK_LIST);
        // executeQuery() : 조회 결과를 ResultSet 객체로 반환
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            StockVO stock = new StockVO();
            stock.setId(rs.getInt("id"));
            stock.setStockName(rs.getString("stock_name"));
            stock.setTicker(rs.getString("ticker"));
            stock.setPrice(rs.getInt("price"));
            stock.setHoldingQty(rs.getInt("holding_qty"));
            stockList.add(stock);
        }
        stmt.close(); rs.close();
        return stockList;
    }

    // 주식 정보 조회
    @Override
    public StockVO get(String ticker) throws SQLException{
        StockVO stock = new StockVO();
        // select * from stock_db where ticker = ? 쿼리에 주식 이름을 넣어 실행 준비
        PreparedStatement stmt = conn.prepareStatement(STOCK_GET);
        stmt.setString(1, ticker);

        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            stock = new StockVO();
            stock.setId(rs.getInt("id"));
            stock.setStockName(rs.getString("stock_name"));
            stock.setTicker(rs.getString("ticker"));
            stock.setPrice(rs.getInt("price"));
            stock.setHoldingQty(rs.getInt("holding_qty"));
        }
        stmt.close(); rs.close();
        return stock;
    }

    // 주식 수정 메소드
    @Override
    public int update(StockVO stock) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement(STOCK_UPDATE);
        stmt.setString(1, stock.getStockName());
        stmt.setString(2, stock.getTicker());
        stmt.setInt(3, stock.getPrice());
        stmt.setInt(4,stock.getHoldingQty());
        stmt.setInt(5, stock.getId());

        int result = stmt.executeUpdate();
        stmt.close();
        return result;
    }

    // 주식 삭제 메소드
    @Override
    public int delete(String ticker) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement(STOCK_DELETE);
        stmt.setString(1, ticker);

        int result = stmt.executeUpdate();

        stmt.close();
        return result;
    }


}
