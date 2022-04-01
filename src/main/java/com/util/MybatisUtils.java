package com.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try{
            //使用Mybatis获取SQLSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public static SqlSession getSqlsession(){
        if(sqlSessionFactory==null){
            System.out.println("123");
        }
        return sqlSessionFactory.openSession();

    }
}
