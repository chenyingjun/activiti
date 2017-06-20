/******************************************************************************
 * Copyright (C) 2016 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/
package com.chenyingjun.activiti.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;

/**
 * activiti  listener模版
 *
 * @author chenyingjun
 * @version 2017年06月19日
 * @since 1.0
 */
public class TestExecutionListener implements ExecutionListener {

    private Expression flow;

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("--------------------TestExecutionListener----------------start");
        String businessKey = delegateExecution.getProcessBusinessKey();
        System.out.println("--------------------TestExecutionListener----------------businessKey:" + businessKey);
//        flow.getValue(delegateExecution).toString();
        System.out.println("--------------------TestExecutionListener----------------flow:" + flow.getExpressionText());

    }
}
