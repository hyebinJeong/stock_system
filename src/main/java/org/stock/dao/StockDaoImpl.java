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
  
    Connection conn = JDBCUtil.getConnection();

    //private String STOCK_LIST = "select * from stock_db";
    private String STOCK_GET = "select * from stock_db where ticker = ?";
    //private String STOCK_INSERT = "insert into stock_db (stock_name, ticker, price, holding_qty) values(?,?,?,?)";
    private String STOCK_UPDATE = "update stock_db set stock_name  = ?, ticker = ?, price = ?, holding_qty = ? where id = ?";
    private final String STOCK_DELETE = "DELETE FROM stocks WHERE ticker = ?";

    // 특정 주식 정보 조회
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
    public int delete(String ticker) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STOCK_DELETE);
        pstmt.setString(1, ticker);
        int count = pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
}
