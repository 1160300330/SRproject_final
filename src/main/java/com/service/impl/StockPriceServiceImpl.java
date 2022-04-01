package com.service.impl;

import com.dao.StockPriceDao;
import com.pojo.StockPrice;
import com.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockPriceServiceImpl implements StockPriceService {
    @Autowired
    private StockPriceDao stockPriceDao;
    public List<StockPrice> getStockPriceList(){
        return stockPriceDao.getStockPriceList();
    }
    public StockPrice getStockPriceById(String id){
        return stockPriceDao.getStockPriceInfoById(id);
    }


    public void setStockPriceDao(StockPriceDao stockPriceDao) {
    }
}
