package com.service.impl;

import com.dao.TradeDao;
import com.pojo.Trade;
import com.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeDao tradeDao;
    public List<Trade> getUserTradeList(String userid){
        return tradeDao.getUserTradeList(userid);
    }
    public Trade getUserTradeById(String userid,String stockid){
        return tradeDao.getUserTradeById(userid,stockid);
    }
    public void updateTrade(String userid,String stockid, double amount){
        tradeDao.updateTrade(userid,stockid,amount);
    }
    public void openTrade(String userid,String stockid,double amount){
        tradeDao.openTrade(userid,stockid,amount);
    }

    public void setTradeDao(TradeDao tradeDao) {
        this.tradeDao = tradeDao;
    }

    public int deleteTrade(String userid, String stockid) {
        return tradeDao.deleteTrade(userid, stockid);
    }
}
