package com.boot.bvserver.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 活动实体类
 *
 */
@Data
@Accessors(chain = true)
public class Activity {
    private Long id;
    private String ActivityName;
    private int status;
}
