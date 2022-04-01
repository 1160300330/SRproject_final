package com.service.impl;

import com.dao.StockInfoDao;
import com.pojo.StockInfo;
import com.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockInfoDao stockInfoDao;

    @Override
    public List<StockInfo> queryAll(String id) {
        return stockInfoDao.getStockInfoList(id);
    }

    @Override
    public List<StockInfo> queryByNum(String id, int num) {
        return stockInfoDao.getStockInfoListLimited(id,num);
    }

    @Override
    public List<StockInfo> queryByTime(String id, String t1, String t2) {

        return stockInfoDao.getStockInfoListByDate(id,"\'"+t1+"\'","\'"+t2+"\'");
    }

    @Override
    public StockInfo queryByIdRealTime(String id) {
        return null;
    }

}
