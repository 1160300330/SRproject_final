package com.dao;

import com.pojo.StockNameID;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class StockNameIDTest {


    @Test
    public void testCGS(){
        String classpath = "src/main/resources/spring";
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
        StockNameID mfr = (StockNameID)ac.getBean("stockNameID");
        Map map = mfr.getMap();
        System.out.println(map.get("浦发银行"));
    }
}
