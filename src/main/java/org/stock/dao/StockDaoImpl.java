package org.stock.dao;

import org.stock.common.JDBCUtil;

import org.stock.domain.StockVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class StockDaoImpl implements StockDao {

    Connection conn = JDBCUtil.getConnection();

    //private String STOCK_LIST = "select * from stock_db";
    private String STOCK_GET = "select * from stocks where ticker = ?";
    private String STOCK_INSERT = "insert into stocks(stock_name, ticker, price, holding_qty) values(?,?,?,?)";
    private String STOCK_UPDATE = "update stocks set stock_name  = ?, ticker = ?, price = ?, holding_qty = ? where id = ?";
    private final String STOCK_DELETE = "DELETE FROM stocks WHERE ticker = ?";

    @Override
    public int create(StockVO stock) throws SQLException {

        // SQL 실행을 위핸 PreparedStatement 생성
        PreparedStatement pstmt = conn.prepareStatement(STOCK_INSERT);

        // ?자리에 값 세팅
        pstmt.setString(1, stock.getStockName());
        pstmt.setString(2, stock.getTicker());
        pstmt.setInt(3, stock.getPrice());
        pstmt.setInt(4, stock.getHoldingQty());
        // DB에 SQL문을 실행
        // executeUpdate()는 INSERT, UPDATE, DELETE 같은 데이터 변경 작업에서 사용
        int result = pstmt.executeUpdate();
        // PreparedStatement 자원을 닫아줌
        // DB와의 연결 리소스를 해제하는 중요한 단계
        //닫지 않으면 메모리 누수, 커넥션 누수로 이어질 수 있음
        // 실무에서는 try-with-resources로 자동 닫기를 많이 사용하지만,
        //지금처럼 수동으로 닫는 것도 OK
        pstmt.close();
        // 앞에서 얻은 실행 결과(삽입된 행의 수)를 메서드 호출한 쪽으로 반환함
        return result;
    }
    @Override
    public List<StockVO> getAllStocks() throws SQLException {
        List<StockVO> list = new ArrayList<>();
        String sql = "SELECT * FROM stocks";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            StockVO vo = new StockVO();
            vo.setId(rs.getInt("id"));
            vo.setStockName(rs.getString("stock_name"));
            vo.setTicker(rs.getString("ticker"));
            vo.setPrice(rs.getInt("price"));
            vo.setHoldingQty(rs.getInt("holding_qty"));
            list.add(vo);
        }

        rs.close();
        pstmt.close();
        return list;
    }

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