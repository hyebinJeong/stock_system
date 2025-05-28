package org.stock.dao;

import org.stock.domain.StockVO;
import java.sql.SQLException;
import java.util.List;

public interface StockDao {
    // 주식 등록
    int create(StockVO stock) throws SQLException;

    // 주식 목록 조회
    List<StockVO> getList() throws SQLException;

    // 주식 정보 조회
    StockVO get(int id) throws  SQLException;

    // 주식 정보 수정
    int update(StockVO stock) throws SQLException;

    // 주식 삭제
    int delete(int id) throws SQLException;

}
