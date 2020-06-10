package com.gjsm.dao;

import com.gjsm.pojo.QueryVo;
import com.gjsm.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Date on 2020/05/30  下午 06:27
 */
public interface UserDao {
    /*
    * 查
    * */
    List<User> findAll();
    /*
     * 查（条件）
     * */
    List<User> findAllName(@Param("username")String username);
    /*
    * 增
    * */
    Boolean inseAll(User user);
    /*
    * 删
    * */
    Boolean deleAll(Integer id);
    /*
    * 改
    * */
    Boolean updaAll(User user);
    /*
     * 查(带条件（包装实体类）)
     * */
    List<User> findAllVO(QueryVo vo);
}
