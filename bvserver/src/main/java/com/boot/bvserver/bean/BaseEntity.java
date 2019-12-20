package com.boot.bvserver.bean;

import lombok.Data;

import java.util.Date;

/**
 * bean 对象基本属性
 */
@Data
public class BaseEntity {
    private Long id;           // 主键
    private Date createdTime;  // 创建时间
    private Date updatedTime;  // 创建时间
    private Long createdBy;    // 创建人
    private Long updatedBy;    // 修改人
}
