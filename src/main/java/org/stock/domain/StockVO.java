package org.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockVO {
    private int id;
    private String stockName;
    private String ticker;
    private int price;
    private int holdingQty;

}