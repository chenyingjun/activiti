package com.chenyingjun.activiti.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 基础信息
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
@Data
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
