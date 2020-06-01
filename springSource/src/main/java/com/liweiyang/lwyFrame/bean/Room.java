package com.liweiyang.lwyFrame.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {
    private String name;
    private String coverImg;
    private Long startTime;
    private Long endTime;
    private String anchorName;
    private String anchorWechat;
    private String shareImg;
    private Integer type;
    private Integer screenType;
    private Integer closeLike;
    private Integer closeGoods;
    private Integer closeComment;
}
