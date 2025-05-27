package org.stock.dao;

import org.stock.model.StockVO;
import java.util.List;

// DAO (Data Access Object) 인터페이스: DB 연동을 위한 메서드 정의
public interface StockDao {
    // 전체 주식 목록을 조회
    List<StockVO> getAllStocks();

    // ID로 특정 주식 정보 조회
    StockVO getStockById(int id);

    // 주식 정보 삽입
    void insertStock(StockVO stock);

    // 주식 정보 수정
    void updateStock(StockVO stock);

    // 주식 정보 삭제
    void deleteStock(int id);
}
