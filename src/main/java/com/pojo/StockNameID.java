package com.pojo;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StockNameID {

    private Map<String,String> map;

    public Map<String,String> getMap() {
        return map;
    }

    public void setMap(Map<String,String> map) {
        this.map = map;
    }
}
