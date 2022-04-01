package com.dao;

import com.pojo.StockPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockPriceDao {
    List<StockPrice> getStockPriceList();
    StockPrice getStockPriceInfoById(@Param(value = "id") String id);
}
