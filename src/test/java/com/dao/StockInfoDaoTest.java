package com.dao;

import com.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.pojo.StockInfo;

import java.util.List;

public class StockInfoDaoTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlsession();
        //try{
            StockInfoDao stockInfoDao = sqlSession.getMapper(StockInfoDao.class);
            List<StockInfo> stockinfolist = stockInfoDao.getStockInfoList("sh600000");
            for(StockInfo info:stockinfolist){
                System.out.println(info.getDatetime());
            }
        //}
        //catch(Exception e){

        //}
       // finally{
       //     sqlSession.close();
       // }
    }
//    @Test
//    public void inserttest(){
//        SqlSession sqlSession = MybatisUtils.getSqlsession();
//        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        userDao.createTable("am10000");
//        sqlSession.close();
//    }
}
