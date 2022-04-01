package com.dao;

import com.pojo.StockInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockInfoDao {
    List <StockInfo> getStockInfoList(String id);

    List <StockInfo> getStockInfoListLimited(@Param(value = "id")String id, @Param(value = "limit")Integer limit);

    List <StockInfo> getStockInfoListByDate(@Param(value = "id")String id, @Param(value = "t1")String t1,
                                            @Param(value = "t2")String t2);
    void createTable(String id);
}
