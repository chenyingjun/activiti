package com.chenyingjun.activiti.service;

import com.chenyingjun.activiti.entity.UserTest;
import com.chenyingjun.activiti.mapper.UserTestMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试用户信息服务
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserTestMapper userTestMapper;

    public UserTest getUserTestByPrimaryKey(Integer id){
        UserTest user = new UserTest();
        user.setUsername("333bb");
        List<UserTest> userList = userTestMapper.select(user);
        for (UserTest user1 : userList) {
            System.out.println("---------------------------" + user1.getPassword() + "--" + user1.getUsername());
        }
        return userTestMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询用户列表
     * @param user 查询用户信息
     * @param pageNum 当前页码
     * @param pageSize 每页数量
     * @return
     */
    public List<UserTest> page(UserTest user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userTestMapper.select(user);
    }

    /**
     * 模糊查询用户信息
     * @param user 查询用户信息
     * @return
     */
    public List<UserTest> like(UserTest user) {
        return userTestMapper.selectLike(user);
    }

}
