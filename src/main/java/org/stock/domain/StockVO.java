package org.stock.domain;

public class StockVO {
    private String stockName;
    private String ticker;
    private int price;
    private int holdingQty;

    public StockVO() {}

    public StockVO(String stockName, String ticker, int price, int holdingQty) {
        this.stockName = stockName;
        this.ticker = ticker;
        this.price = price;
        this.holdingQty = holdingQty;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHoldingQty() {
        return holdingQty;
    }

    public void setHoldingQty(int holdingQty) {
        this.holdingQty = holdingQty;
    }

    @Override
    public String toString() {
        return "[" + stockName + ", " + ticker + ", " + price + ", " + holdingQty + "]";
    }
}
