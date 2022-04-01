package com.service;

import com.pojo.Trade;

import java.util.List;

public interface TradeService {
    List<Trade> getUserTradeList(String userid);
    Trade getUserTradeById(String userid, String stockid);
    void updateTrade(String userid, String stockid, double amount);
    void openTrade(String userid, String stockid, double amount);
    int deleteTrade(String userid, String stockid);
}
