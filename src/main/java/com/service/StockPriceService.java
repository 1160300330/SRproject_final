package com.service;

import com.pojo.StockPrice;

import java.util.List;

public interface StockPriceService {
    List<StockPrice> getStockPriceList();
    StockPrice getStockPriceById(String id);
}
