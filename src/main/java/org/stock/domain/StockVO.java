package org.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockVO {
    private int id;
    private String stock_name;
    private String ticker;
    private int price;
    private int holding_qty;

}