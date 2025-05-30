package org.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //기본 생성자 생성
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 생성

public class StockVO {
    private int id;
    private String stockName;
    private String ticker;
    private int price;
    private int holdingQty;
}