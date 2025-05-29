package org.stock.dao;

import org.stock.common.JDBCUtil;
import org.stock.domain.StockVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

public class StockDaoImpl implements StockDao {

    Connection conn = JDBCUtil.getConnection();

    /*
    private final String STOCK_LIST = "SELECT * FROM stocks";
    private final String STOCK_GET = "SELECT * FROM stocks WHERE ticker = ?";
    private final String STOCK_INSERT = "INSERT INTO stocks VALUES (?, ?, ?, ?)";
    private final String STOCK_UPDATE = "UPDATE stocks SET stock_name = ?, price = ?, holding_qty = ? WHERE ticker = ?";
    */
    private final String STOCK_DELETE = "DELETE FROM stocks WHERE ticker = ?";

    /*
    @Override
    public int create(StockVO stock) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STOCK_INSERT);
        pstmt.setString(1, stock.getStockName());
        pstmt.setString(2, stock.getTicker());
        pstmt.setInt(3, stock.getPrice());
        pstmt.setInt(4, stock.getHoldingQty());
        int count = pstmt.executeUpdate();
        pstmt.close();
        return count;
    }

    @Override
    public List<StockVO> getList() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STOCK_LIST);
        ResultSet rs = pstmt.executeQuery();
        List<StockVO> list = new ArrayList<>();

        while (rs.next()) {
            StockVO stock = new StockVO();
            stock.setStockName(rs.getString("stock_name"));
            stock.setTicker(rs.getString("ticker"));
            stock.setPrice(rs.getInt("price"));
            stock.setHoldingQty(rs.getInt("holding_qty"));
            list.add(stock);
        }

        pstmt.close();
        rs.close();
        return list;
    }

    @Override
    public StockVO get(String ticker) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STOCK_GET);
        pstmt.setString(1, ticker);
        ResultSet rs = pstmt.executeQuery();
        StockVO stock = new StockVO();

        if (rs.next()) {
            stock.setStockName(rs.getString("stock_name"));
            stock.setTicker(rs.getString("ticker"));
            stock.setPrice(rs.getInt("price"));
            stock.setHoldingQty(rs.getInt("holding_qty"));
        }

        pstmt.close();
        rs.close();
        return stock;
    }

    @Override
    public int update(StockVO stock) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STOCK_UPDATE);
        pstmt.setString(1, stock.getStockName());
        pstmt.setInt(2, stock.getPrice());
        pstmt.setInt(3, stock.getHoldingQty());
        pstmt.setString(4, stock.getTicker());
        int count = pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
    */

    @Override
    public int delete(String ticker) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STOCK_DELETE);
        pstmt.setString(1, ticker);
        int count = pstmt.executeUpdate();
        pstmt.close();
        return count;
    }
}
