package com.simon;

import com.simon.mapper.BrandMapper;
import com.simon.mapper.UserMapper;
import com.simon.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// 直接配置资源路径来实现
public class MyBatisDemo_SelUser {
    public static void main(String[] args) throws IOException {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";//资源名称，参考官网，默认
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。
        // 而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例。
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3. 获取单个用户
//        User user = sqlSession.selectOne("com.simon.mapper.UserMapper.selectById", 2);
//        System.out.println("单个用户信息：" + user);
        //单个用户信息：User{id=2, username='李四', password='234', gender='女', addr='天津'}

        //4. 执行sql
        //List<User> users = sqlSession.selectList("test.selectAll");

        String username = "zhangsan";
        String password = "123";

        //3. 获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.select(username, password);

        System.out.println(user);

//        Map map = new HashMap();
//        // map.put("status" , status);
//        map.put("username", username);
//        map.put("param2" , password);

        //3. 获取Mapper接口的代理对象

//        User user1 = userMapper.select(new HashSet());
//        System.out.println(user1);

        //4. 释放资源
        sqlSession.close();
    }
}