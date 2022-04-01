package com.dao;

import com.pojo.Trade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TradeDao {
    List<Trade> getUserTradeList(@Param(value = "userid") String userid);
    Trade getUserTradeById(@Param(value = "userid") String userid, @Param(value = "stockid") String stockid);
    void updateTrade(@Param(value = "userid") String userid, @Param(value = "stockid") String stockid, @Param(value = "amount") double amount);
    void openTrade(@Param(value = "userid") String userid, @Param(value = "stockid") String stockid, @Param(value = "amount") double amount);
    int deleteTrade(@Param(value="userid")String userid, @Param(value = "stockid") String stockid);
}
