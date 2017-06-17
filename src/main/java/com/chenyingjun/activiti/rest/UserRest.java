package com.chenyingjun.activiti.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value="删除指定id用户", notes="根据id来删除用户信息")
    @ApiImplicitParam(name = "id",value = "用户id", dataType = "java.lang.Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id){
        System.out.println("delete user:"+id);
        return "OK";
    }
}
