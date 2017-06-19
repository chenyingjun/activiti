package com.chenyingjun.activiti.rest;


import com.chenyingjun.activiti.service.FlowService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/process")
public class ActivitiRest {

    @Resource
    ProcessEngine engine;

    /**

     * 部署流程

     */
    @RequestMapping(value = "deploy", method = RequestMethod.GET)
    @ApiOperation(value="部署流程", notes="部署流程")
    @ResponseBody
    public String deploy(String processName, String tenantId) {
        RepositoryService service = engine.getRepositoryService();
        if (null != processName) {
            DeploymentBuilder builder = service.createDeployment();
            builder.addClasspathResource("bpmn/" + processName + ".bpmn");
            builder.name(processName);
            builder.tenantId(tenantId);
            builder.deploy();
        }
        List<ProcessDefinition> list = service.createProcessDefinitionQuery()
                .list();
        return list.toString();
    }

    /**

     * 已部署流程列表

     */
    @RequestMapping(value = "deployed", method = RequestMethod.GET)
    @ApiOperation(value="已部署流程列表", notes="已部署流程列表")
    @ResponseBody
    public String deployed() {
        RepositoryService service = engine.getRepositoryService();
        List<ProcessDefinition> list = service.createProcessDefinitionQuery()
                .list();
        return list.toString();
    }

    /**
     * 启动一个流程实例
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "start", method = RequestMethod.GET)
    @ApiOperation(value="启动一个流程实例", notes="启动一个流程实例")
    @ResponseBody
    public String start(String key, String testId, String flow) {
        Map<String, Object> map = new HashMap<>();
        map.put("flow", flow);
        RuntimeService service = engine.getRuntimeService();
        service.startProcessInstanceByKey(key, testId, map);
        List<ProcessInstance> list = service.createProcessInstanceQuery()
                .list();
        return list.toString();
    }

    /**
     * 所有已启动流程实例
     */
    @RequestMapping(value = "started", method = RequestMethod.GET)
    @ApiOperation(value="所有已启动流程实例", notes="所有已启动流程实例")
    @ResponseBody
    public String started() {
        RuntimeService service = engine.getRuntimeService();
        List<ProcessInstance> list = service.createProcessInstanceQuery()
                .list();
        return list.toString();
    }

    /**
     * 进入任务列表
     */
    @RequestMapping(value = "task", method = RequestMethod.GET)
    @ApiOperation(value="进入任务列表", notes="进入任务列表")
    @ResponseBody
    public String task(){
        TaskService service=engine.getTaskService();
        List<Task> list=service.createTaskQuery().list();
        return list.toString();
    }
    /**
     *完成当前节点
     */
    @RequestMapping(value = "complete", method = RequestMethod.GET)
    @ApiOperation(value="完成当前节点 ", notes="完成当前节点 ")
    public TaskService complete(String id, String flow){
        Map<String, Object> map = new HashMap<>();
        map.put("flow", flow);
        TaskService service=engine.getTaskService();
        service.complete(id, map);
        return service;
    }
}