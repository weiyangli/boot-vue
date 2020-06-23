package com.liweiyang.lwyFrame.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liweiyang.lwyFrame.bean.TybMerchantMailHome;
import org.apache.commons.lang3.StringUtils;

public class Java8Inter<T> {

    /**
     * 去除不显示的模块
     *
     * @param tybMerchantMailHome
     */
    private static void mailDataFilter(TybMerchantMailHome tybMerchantMailHome) {
        if (StringUtils.isNotBlank(tybMerchantMailHome.getModelProps())) {
            JSONArray jsonArray = JSONObject.parseArray(tybMerchantMailHome.getModelProps());
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                if (item.getString("code").equals("slideShow") && !item.getBoolean("showType")) {
                    tybMerchantMailHome.setSlideShow(null);
                } else if (item.getString("code").equals("hot") && !item.getBoolean("showType")) {
                    tybMerchantMailHome.setHotProductIds(null);
                } else if (item.getString("code").equals("brand") && !item.getBoolean("showType")) {
                    tybMerchantMailHome.setBrandImages(null);
                } else if (item.getString("code").equals("referral") && !item.getBoolean("showType")) {
                    tybMerchantMailHome.setReferralProductIds(null);
                }
            }
        }
    }

    public static void main(String[] args) {
        TybMerchantMailHome tybMerchantMailHome = TybMerchantMailHome.builder()
                .platMerchantId("111").agentId("222").brandImages("[{\"imageUrl\":\"https://taoyibao-detailpicture.oss-cn-zhangjiakou.aliyuncs.com/tyb/1268425693016223746.jpg\",\"link\":\"\",\"type\":0,\"title\":\"\",\"productId\":\"\",\"name\":\"\"}]")
                .slideShow("[{\"imageUrl\":\"\",\"link\":\"\",\"type\":0,\"title\":\"\",\"productId\":\"\",\"name\":\"\"},{\"imageUrl\":\"\",\"link\":\"\",\"type\":0,\"title\":\"\",\"productId\":\"\",\"name\":\"\"},{\"imageUrl\":\"\",\"link\":\"\",\"type\":0,\"title\":\"\",\"productId\":\"\",\"name\":\"\"}]")
                .modelProps("[{\"code\":\"slideShow\",\"showType\":true},{\"code\":\"hot\",\"showType\":false},{\"code\":\"brand\",\"showType\":false},{\"code\":\"referral\",\"showType\":false}]")
                .hotProductIds("1268368311884050434,1268371204154454017,1268424467394457602,1268427557656981505,1268465834640404481")
                .referralProductIds("1268363751903850497,1268368311884050434,1268371204154454017,1268424467394457602,1268465834640404481").build();
        mailDataFilter(tybMerchantMailHome);
        System.out.println(JSONObject.toJSONString(tybMerchantMailHome));
    }
}
