package org.stock.vo;

// VO (Value Object) 클래스: DB 테이블의 하나의 행을 표현하는 객체
public class StockVO {
    private int id;                 // 주식 고유 ID (기본키)
    private String stockName;      // 주식 이름
    private String ticker;         // 주식 식별자 (예: "1001")
    private int price;             // 주식 가격
    private int holdingQty;        // 보유 수량

    // 기본 생성자
    public StockVO() {}

    // 모든 필드를 초기화하는 생성자 (Read 기능에만 사용)
    /*
    public StockVO(int id, String stockName, String ticker, int price, int holdingQty) {
        this.id = id;
        this.stockName = stockName;
        this.ticker = ticker;
        this.price = price;
        this.holdingQty = holdingQty;
    }
    */

    // 각 필드에 대한 getter (Read 기능)
    public int getId() {
        return id;
    }

    public String getStockName() {
        return stockName;
    }

    public String getTicker() {
        return ticker;
    }

    public int getPrice() {
        return price;
    }

    public int getHoldingQty() {
        return holdingQty;
    }

    // setter 메서드들은 주석 처리
    /*
    public void setId(int id) {
        this.id = id;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHoldingQty(int holdingQty) {
        this.holdingQty = holdingQty;
    }
    */

    // 객체 정보를 문자열로 출력할 때 사용 (Read)
    @Override
    public String toString() {
        return "StockVO{" +
                "id=" + id +
                ", stockName='" + stockName + '\'' +
                ", ticker='" + ticker + '\'' +
                ", price=" + price +
                ", holdingQty=" + holdingQty +
                '}';
    }
}
