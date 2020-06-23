package com.liweiyang.lwyFrame.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


/**
 * @Description: 商户商城首页
 * @Author: tyb-dev
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Data
@Builder
public class TybMerchantMailHome implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**热卖商品*/
    private String hotProductIds;
    /**推荐商品*/
    private String referralProductIds;
    /**轮播图*/
    private String slideShow;
    /**品牌图*/
    private String brandImages;
    /**模块属性**/
    private String modelProps;
    /**代理商Id*/
    private String agentId;
    /**平台商户id*/
    private String platMerchantId;
}
