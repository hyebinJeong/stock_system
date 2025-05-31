package org.stock.dao;

import org.stock.common.JDBCUtil;
import org.stock.vo.StockVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDaoImpl implements StockDao {

    // 모든 주식 조회
    @Override
    public List<StockVO> getAllStocks() {
        List<StockVO> list = new ArrayList<>();
        String sql = "SELECT * FROM stocks";

        // try-with-resources로 커넥션, 스테이트먼트, 결과셋 자동 close
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                StockVO vo = new StockVO();
                vo.setId(rs.getInt("id"));
                vo.setStockName(rs.getString("stock_name"));
                vo.setTicker(rs.getString("ticker"));
                vo.setPrice(rs.getInt("price"));
                vo.setHoldingQty(rs.getInt("holding_qty"));
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ID로 주식 조회
    @Override
    public StockVO getStockById(int id) {
        String sql = "SELECT * FROM stocks WHERE id = ?";
        StockVO vo = null;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    vo = new StockVO();
                    vo.setId(rs.getInt("id"));
                    vo.setStockName(rs.getString("stock_name"));
                    vo.setTicker(rs.getString("ticker"));
                    vo.setPrice(rs.getInt("price"));
                    vo.setHoldingQty(rs.getInt("holding_qty"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }

    /*
    // 주식 삽입
    @Override
    public void insertStock(StockVO stock) {
        String sql = "INSERT INTO stocks(stock_name, ticker, price, holding_qty) VALUES (?, ?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, stock.getStockName());
            pstmt.setString(2, stock.getTicker());
            pstmt.setInt(3, stock.getPrice());
            pstmt.setInt(4, stock.getHoldingQty());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 주식 수정
    @Override
    public void updateStock(StockVO stock) {
        String sql = "UPDATE stocks SET stock_name = ?, ticker = ?, price = ?, holding_qty = ? WHERE id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, stock.getStockName());
            pstmt.setString(2, stock.getTicker());
            pstmt.setInt(3, stock.getPrice());
            pstmt.setInt(4, stock.getHoldingQty());
            pstmt.setInt(5, stock.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 주식 삭제
    @Override
    public void deleteStock(int id) {
        String sql = "DELETE FROM stocks WHERE id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */
}
