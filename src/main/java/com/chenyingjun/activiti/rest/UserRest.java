package com.chenyingjun.activiti.rest;

import com.chenyingjun.activiti.entity.UserTest;
import com.chenyingjun.activiti.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户相关api
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
@Api(description = "用户相关api")
@RestController
@RequestMapping("/api/users")
public class UserRest {

    private static final Logger logger = LoggerFactory.getLogger(UserRest.class);

    @Autowired
    private UserService userService;


    @ApiOperation(value="删除指定id用户", notes="根据id来删除用户信息")
    @ApiImplicitParam(name = "id",value = "用户id", dataType = "java.lang.Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id){
        System.out.println("delete user:"+id);
        return "OK";
    }

    @ApiOperation(value="获取指定id用户详细信息", notes="根据user的id来获取用户详细信息")
    @ApiImplicitParam(name = "id",value = "用户id", dataType = "String", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserTest getUser(@PathVariable Integer id){
        logger.info("--------------------------getUser");
        return userService.getUserTestByPrimaryKey(id);
    }


    @ApiOperation(value="获取用户详细信息列表", notes="获取用户详细信息列表")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public PageInfo<UserTest> page(UserTest user, @RequestParam int pageNum, @RequestParam int pageSize){
        List<UserTest> userList = userService.page(user, pageNum, pageSize);
        return new PageInfo<UserTest>(userList);
    }

    @ApiOperation(value="模糊查询用户信息", notes="模糊查询用户信息")
    @RequestMapping(value = "/like", method = RequestMethod.GET)
    public List<UserTest> like(UserTest user){
        List<UserTest> userList = userService.like(user);
        return userList;
    }
}
