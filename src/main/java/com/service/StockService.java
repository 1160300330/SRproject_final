package com.service;

import com.pojo.StockInfo;

import java.util.List;

public interface StockService {
    List<StockInfo> queryAll(String id);
    List<StockInfo> queryByNum(String id,int num);
    List<StockInfo> queryByTime(String id,String t1,String t2);
    StockInfo queryByIdRealTime(String id);//查
}
