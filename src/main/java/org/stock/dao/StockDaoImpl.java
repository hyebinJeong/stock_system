package org.stock.dao;

import org.stock.common.JDBCUtil;
import org.stock.domain.StockVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDaoImpl implements StockDao {

    Connection conn = JDBCUtil.getConnection();

    // SQL 명령어 정리
    // 상수로 명확히 선언하기 위해 final 사용 (다시는 변경되지 않음을 알리기)
    // SQL 같은 변경되지 않아야 할 고정 문자열은 항상 private static final로 선언하는 것이 실무에서의 표준 관례
    private static final String STOCK_INSERT = "INSERT INTO stocks (stock_name, ticker, price, holding_qty) VALUES (?, ?, ?, ?)";
    private static final String STOCK_LIST = "SELECT * FROM stocks";
    private static final String STOCK_GET = "SELECT * FROM stocks WHERE id = ?";
    private static final String STOCK_UPDATE = "UPDATE stocks SET stock_name = ?, ticker = ?, price = ?, holding_qty = ? WHERE id = ?";
    private static final String STOCK_DELETE = "DELETE FROM stocks WHERE id = ?";


    @Override
    public int create(StockVO stock) throws SQLException {

        // SQL 실행을 위핸 PreparedStatement 생성
        PreparedStatement pstmt = conn.prepareStatement(STOCK_INSERT);

        // ?자리에 값 세팅
        pstmt.setString(1, stock.getStock_name());
        pstmt.setString(2, stock.getTicker());
        pstmt.setInt(3, stock.getPrice());
        pstmt.setInt(4, stock.getHolding_qty());
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
    public List<StockVO> getList() throws SQLException {
        // ⚠️ 기존에는 SQL을 직접 문자열로 선언했으나, 상수 재사용으로 일관성 향상
        PreparedStatement pstmt = conn.prepareStatement(STOCK_LIST);
        ResultSet rs = pstmt.executeQuery();

        List<StockVO> list = new ArrayList<>();
        while (rs.next()) {
            StockVO stock = new StockVO();
            stock.setId(rs.getInt("id"));
            stock.setStock_name(rs.getString("stock_name"));
            stock.setTicker(rs.getString("ticker"));
            stock.setPrice(rs.getInt("price"));
            stock.setHolding_qty(rs.getInt("holding_qty"));
            list.add(stock);  // 한 줄씩 리스트에 추가
        }

        rs.close();
        pstmt.close();
        return list;
    }


    @Override
    public StockVO get(int id) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STOCK_GET);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        StockVO stock = null;
        if (rs.next()) {
            stock = new StockVO();
            stock.setId(rs.getInt("id"));
            stock.setStock_name(rs.getString("stock_name"));
            stock.setTicker(rs.getString("ticker"));
            stock.setPrice(rs.getInt("price"));
            stock.setHolding_qty(rs.getInt("holding_qty"));
        }

        rs.close();
        pstmt.close();
        return stock;
    }

    @Override
    public int update(StockVO stock) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STOCK_UPDATE);
        pstmt.setString(1, stock.getStock_name());
        pstmt.setString(2, stock.getTicker());
        pstmt.setInt(3, stock.getPrice());
        pstmt.setInt(4, stock.getHolding_qty());
        pstmt.setInt(5, stock.getId());
        int result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }

    @Override
    public int delete(int id) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STOCK_DELETE);
        pstmt.setInt(1, id);
        int result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }
}
