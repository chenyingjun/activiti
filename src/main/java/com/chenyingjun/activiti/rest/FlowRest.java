package com.chenyingjun.activiti.rest;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工作流相关api
 *
 * @author chenyingjun
 * @version 2017年06月19日
 * @since 1.0
 */
@Api(description = "工作流相关api")
@RestController
@RequestMapping("/api/flows")
public class FlowRest {

    private static final Logger logger = LoggerFactory.getLogger(FlowRest.class);


}
